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

public class SignInActivity extends AppCompatActivity implements View.OnClickListener{
    private final String REQUIRE = "Require";
    private final String INVALID = "Wrong username or password";
    private EditText etUsername;
    private EditText etPassword;
    private TextView tvInvalid;
    private TextView tvSignUp;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        binding();
        tvInvalid.setText("");
        btnLogin.setOnClickListener(this);
        tvSignUp.setOnClickListener(this);
    }

    private void binding() {
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvInvalid = findViewById(R.id.tvInvalid);
        tvSignUp = findViewById(R.id.tvSignUp);
    }

    private boolean checkLogin() {
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        if (TextUtils.isEmpty(username)) {
            etUsername.setError(REQUIRE);
            return false;
        }
        if (TextUtils.isEmpty(password)) {
            etPassword.setError(REQUIRE);
            return false;
        }
        if (!TextUtils.equals(username, "pg") || !TextUtils.equals(password, "pg")) {
            tvInvalid.setText(INVALID);
            return false;
        }
        return true;
    }

    private void login() {
        if (!checkLogin()) return;

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View view) {
        final int btnLoginId = R.id.btnLogin;
        final int tvSignUpId = R.id.tvSignUp;
        switch (view.getId()){
            case btnLoginId:
                login();
                break;
            case tvSignUpId:
                Intent intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);
                finish();
        }
    }
}