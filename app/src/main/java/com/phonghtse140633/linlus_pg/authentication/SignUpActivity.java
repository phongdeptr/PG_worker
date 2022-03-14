package com.phonghtse140633.linlus_pg.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.phonghtse140633.linlus_pg.MainActivity;
import com.phonghtse140633.linlus_pg.R;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText etUsername;
    private EditText etEmail;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private Button btnSignUp;
    private TextView tvSignIn;
    private final String REQUIRE = "Require";
    private final String NOT_MATCH = "Confirm password not match";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        binding();
        btnSignUp.setOnClickListener(this);
        tvSignIn.setOnClickListener(this);
    }

    private void binding() {
        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        tvSignIn = findViewById(R.id.tvSignIn);
    }

    private boolean checkSignUp() {
        if (TextUtils.isEmpty(etUsername.getText().toString())) {
            etUsername.setError(REQUIRE);
            return false;
        }
        if (TextUtils.isEmpty(etEmail.getText().toString())) {
            etEmail.setError(REQUIRE);
            return false;
        }
        if (TextUtils.isEmpty(etPassword.getText().toString())) {
            etPassword.setError(REQUIRE);
            return false;
        }
        if (TextUtils.isEmpty(etConfirmPassword.getText().toString())) {
            etConfirmPassword.setError(REQUIRE);
            return false;
        }
        if (!TextUtils.equals(etPassword.getText().toString(),
                etConfirmPassword.getText().toString())) {
            etConfirmPassword.setError(NOT_MATCH);
            return false;
        }
        return true;
    }

    private void signUp() {
        if (!checkSignUp()) return;

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View view) {
        final int btnSignUpId = R.id.btnSignUp;
        final int tvSignInId = R.id.tvSignIn;
        switch (view.getId()){
            case btnSignUpId:
                signUp();
                break;
            case tvSignInId:
                Intent intent = new Intent(this, SignInActivity.class);
                startActivity(intent);
                finish();
        }
    }
}