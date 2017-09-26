package com.sabututxp.myretrofitexample.activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.sabututxp.myretrofitexample.APIService;
import com.sabututxp.myretrofitexample.APIUrl;
import com.sabututxp.myretrofitexample.R;
import com.sabututxp.myretrofitexample.model.User;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUpActivity extends AppCompatActivity {
    private Button signUpButton;
    private EditText editTextName,editTextEmail,editTextPhoneNumber,editTextPassword,editTextConfirmPassword;

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
                userSignUp();
            }
        });
    }




    private void userSignUp() {
        //defining a progress dialog to show while signing up
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Signing Up...");
        progressDialog.show();

        String name = editTextName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String phoneNumber = editTextPhoneNumber.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();

        //building retrofit object
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(APIUrl.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //Defining retrofit api service
        APIService service = retrofit.create(APIService.class);

        //Defining the user object as we need to pass it with the call
        //final UserInfo auser = new UserInfo("akash", "akash.cse1088@gmail.com", "4234", "123456", "123456");
      //  System.out.println(auser.getEmail());

        //defining the call
        Call<User> call = service.createUser(name,email,password,phoneNumber,confirmPassword);
        //calling the api
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                //System.out.println(response.body().toString());
                System.out.println(response.body().getEmail().toString());
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"User Register Successfully",Toast.LENGTH_SHORT).show();

                }

                else{
                    Toast.makeText(getApplicationContext(),"404 Not found",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                System.out.println("eerrr");
            }
        });
    }
}
