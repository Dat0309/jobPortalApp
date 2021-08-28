package com.dinhtrongdat.jobportal;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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
                if(!validationUser() | !validationPass()){
                    return;
                }
                    isUser();
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


    private Boolean validationUser(){
        String val = edtUser.getEditText().getText().toString();
        String space = "\\A\\w{4,20}\\z";

        if(val.isEmpty()){
            edtUser.setError("Nhập tài khoản");
            return false;
        }
        else if(val.length()>=15){
            edtUser.setError("không được quá 15 ký tự");
            return false;
        }
        else if(!val.matches(space)){
            edtUser.setError("Không được chứa khoảng trắng");
            return false;
        }
        else{
            edtUser.setError(null);
            return true;
        }
    }

    private Boolean validationPass(){
        String val = edtPass.getEditText().getText().toString();
        String space = "\\A\\w{4,20}\\z";

        if(val.isEmpty()){
            edtPass.setError("Nhập mật khẩu");
            return false;
        }
        else if(val.length()>=15){
            edtPass.setError("không được quá 15 ký tự");
            return false;
        }
        else if(!val.matches(space)){
            edtPass.setError("Không được chứa khoảng trắng");
            return false;
        }
        else{
            edtPass.setError(null);
            return true;
        }
    }

    private void isUser() {
        final String userName = edtUser.getEditText().getText().toString().trim();
        final String passWord = edtPass.getEditText().getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String USER = dataSnapshot.child("user").getValue(String.class);
                String PASS = dataSnapshot.child("pass").getValue(String.class);
                String PHONE = dataSnapshot.child("phone").getValue(String.class);
//                Toast.makeText(LoginActivity.this, USER + PASS + "",Toast.LENGTH_LONG).show();

                if (userName.compareTo(USER)==0){
                    if(passWord.compareTo(PASS)==0){
                        Intent intent = new Intent(getApplicationContext(), MainWindow.class);
                        intent.putExtra("user", USER);
                        intent.putExtra("pass",PASS);
                        intent.putExtra("phone",PHONE);

                        startActivity(intent);
                    }
                    else{
                        edtPass.setError("Sai mật khẩu");
                        edtPass.requestFocus();
                    }
                }
                else{
                    edtUser.setError("Tài khoản không tồn tại");
                    edtPass.requestFocus();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(LoginActivity.this, "Đăng nhập thất bại",Toast.LENGTH_LONG).show();
            }
        });
    }

//        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
//        Query checkUser = reference.orderByChild("user").equalTo(userName);
//
//        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//
//                if(snapshot.exists()){
//
//                    edtUser.setError(null);
//                    edtUser.setErrorEnabled(false);
//
//                    String passwordFromDB = snapshot.child(userName).child("pass").getValue(String.class);
//
//                    if(passwordFromDB.equals(passWord)){
//
//                        edtUser.setError(null);
//                        edtUser.setErrorEnabled(false);
//
//                        String usernameFromDB = snapshot.child(userName).child("user").getValue(String.class);
//                        String phoneFromDB = snapshot.child(userName).child("phone").getValue(String.class);
//
//                        Intent intent = new Intent(getApplicationContext(), MainWindow.class);
//
//                        intent.putExtra("user", usernameFromDB);
//                        intent.putExtra("pass",passwordFromDB);
//                        intent.putExtra("phone",phoneFromDB);
//
//                        startActivity(intent);
//                    }
//                    else{
//                        edtPass.setError("Sai mật khẩu");
//                        edtPass.requestFocus();
//                    }
//                }
//                else{
//                    edtUser.setError("Tài khoản không tồn tại");
//                    edtUser.requestFocus();
//                }
//            }


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