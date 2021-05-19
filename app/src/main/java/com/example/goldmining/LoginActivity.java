package com.example.goldmining;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.vishnusivadas.advanced_httpurlconnection.PutData;


public class LoginActivity extends AppCompatActivity {
    EditText edUserName , edPassword;
    Button btnLogin;
    ProgressBar progressBar;
    CheckBox checkBox;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edUserName = findViewById(R.id.edUserName);
        edPassword = findViewById(R.id.editTextNumberPassword);
        btnLogin = findViewById(R.id.btLogin);
        progressBar = findViewById(R.id.progressBar);
        checkBox = findViewById(R.id.checkBox);
        if(checkBox.isChecked()){


        }



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String userName , password;
                userName = String.valueOf(edUserName.getText().toString());
                password = String.valueOf(edPassword.getText().toString());
                if(!(userName.equals("") && password.equals(""))){
                    progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[2];
                            field[0] = "username";
                            field[1] = "passwod";
                            String[] data = new String[2];
                            data[0] =userName;
                            data[1] = password;
                            PutData putData = new PutData("https://egicta.com/Login/login.php", "POST", field, data);
                            if(putData.startPut()){
                               if(putData.onComplete()){
                                   progressBar.setVisibility(View.GONE);
                                   String result = putData.getResult();
                                   if(result.equals("Login Success")){
                                       Toast.makeText(LoginActivity.this, "تم تسجيل الدخول ", Toast.LENGTH_SHORT).show();
                                       Intent intent = new Intent(getApplicationContext(),DashBordActivity.class);
                                       startActivity(intent);
                                       finish();
                                   }
                                   else{
                                       Toast.makeText(LoginActivity.this, result, Toast.LENGTH_SHORT).show();
                                   }
                               }
                            }
                        }
                    });

                }else{

                    Toast.makeText(LoginActivity.this, "ادخل البيانات", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

}