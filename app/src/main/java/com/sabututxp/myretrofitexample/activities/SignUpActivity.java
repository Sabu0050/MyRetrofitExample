package com.sabututxp.myretrofitexample.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sabututxp.myretrofitexample.APIService;
import com.sabututxp.myretrofitexample.APIUrl;
import com.sabututxp.myretrofitexample.R;
import com.sabututxp.myretrofitexample.model.User;


import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {
    private Button signUpButton;
    private EditText editTextName,editTextEmail,editTextPhoneNumber,editTextPassword,editTextConfirmPassword;
    private APIService service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        signUpButton = (Button) findViewById(R.id.buttonSignUpSUA);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPhoneNumber= (EditText) findViewById(R.id.editTextPhoneNumber);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);
        editTextConfirmPassword = (EditText) findViewById(R.id.editTextConfirmPassword);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*userSignUp();*/
                if (userSignUp()) {


                    CountDownTimer countDownTimer = new CountDownTimer(1000, 1000) {
                        @Override
                        public void onTick(long millisUntilFinished) {

                        }

                        @Override
                        public void onFinish() {

                            Toast.makeText(SignUpActivity.this, " Login using correct details ", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(SignUpActivity.this, SingInActivity.class);
                            startActivity(intent);

                        }
                    }.start();

                }
            }
        });
    }





    private boolean userSignUp() {
        //defining a progress dialog to show while signing up
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing Up...");
        progressDialog.show();

        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String phoneNumber = editTextPhoneNumber.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();

        if (name.isEmpty() || email.isEmpty() || phoneNumber.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {

            Toast.makeText(this, "No Fields can be empty!! ", Toast.LENGTH_SHORT).show();

        } else if (!password.equals(confirmPassword) && (!password.isEmpty() || !confirmPassword.isEmpty())) {


            Toast.makeText(this, "Passwords Do not match", Toast.LENGTH_SHORT).show();


        } else {


            return serverOperation(name, email, password, phoneNumber, confirmPassword);

        }

        return false;


    }

    private boolean serverOperation(String name, String email, String phoneNumber, String password, String confirmPassword){

        Call<User> call = service.createUser(name,email,password,phoneNumber,confirmPassword);
        //calling the api
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    String email = response.body().getEmail();
                    Toast.makeText(SignUpActivity.this, email + " has been registered, \n please check email to verify account ", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SignUpActivity.this, SingInActivity.class);
                    startActivity(intent);
                }

                else{
                    Gson gson = new GsonBuilder().create();
                    Error pojo;
                    try {
                        JSONObject errorObj = new JSONObject(response.errorBody().string());
                        pojo = gson.fromJson(errorObj.getJSONObject("error").toString(), Error.class);
                        Toast.makeText(getApplicationContext(), pojo.toString(), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    return false;
    }
}
