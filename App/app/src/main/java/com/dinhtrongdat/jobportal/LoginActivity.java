package com.dinhtrongdat.jobportal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin, btnRegistor, btnFogotPass;
    ImageView logo;
    TextView title, slogan;
    TextInputLayout edtUser, edtPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        AnhXa();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, MainWindow.class);
                startActivity(intent);
            }
        });

        btnRegistor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterWindow.class);
                Pair[] pairs = new Pair[7];
                pairs[0] = new Pair<View, String>(logo, "logo_image");
                pairs[1] = new Pair<View, String>(title,"logo_text");
                pairs[2] = new Pair<View, String>(slogan, "slogan_tran");
                pairs[3] = new Pair<View, String>(edtUser, "user_tran");
                pairs[4] = new Pair<View, String>(edtPass, "pass_tran");
                pairs[5] = new Pair<View, String>(btnLogin, "btn_tran");
                pairs[6] = new Pair<View, String>(btnRegistor, "btn_tran");

                ActivityOptions option = ActivityOptions.makeSceneTransitionAnimation(LoginActivity.this, pairs);
                startActivity(intent, option.toBundle());
            }
        });
    }

    private void AnhXa(){
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnFogotPass = (Button) findViewById(R.id.btnFogotPass);
        btnRegistor = (Button) findViewById(R.id.btnSignUp);
        logo = (ImageView)findViewById(R.id.logoImg);
        title = (TextView) findViewById(R.id.txtWelcome);
        slogan = (TextView) findViewById(R.id.txtSignin);
        edtPass = (TextInputLayout) findViewById(R.id.password);
        edtUser = (TextInputLayout) findViewById(R.id.username);
    }
}