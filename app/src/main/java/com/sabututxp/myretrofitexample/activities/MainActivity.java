package com.sabututxp.myretrofitexample.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.sabututxp.myretrofitexample.R;

public class MainActivity extends AppCompatActivity {

    public Button signInButton;
    public Button signUpButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signInButton = (Button) findViewById(R.id.buttonSignIn);
        signUpButton = (Button) findViewById(R.id.buttonSignUp);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(i1);
            }
        });
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(MainActivity.this,SingInActivity.class);
                startActivity(i2);
            }
        });
    }
}
