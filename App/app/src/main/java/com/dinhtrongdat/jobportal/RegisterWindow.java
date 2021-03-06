package com.dinhtrongdat.jobportal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterWindow extends AppCompatActivity {

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    TextInputLayout user, password, passwordRepeat, Phone;
    Button btnReg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register_window);

        Hook();


            btnReg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!validateName() | !validatePass() | !validationPhone()) {
                        return;
                    }
                        rootNode = FirebaseDatabase.getInstance();
                        reference = rootNode.getReference("users");

                        //Get all thhe values
                        String _user = user.getEditText().getText().toString();
                        String _pass = password.getEditText().getText().toString();
                        String _phone = Phone.getEditText().getText().toString();

                        UserHelperClass helperClass = new UserHelperClass(_user, _pass, _phone);

                        reference.push().setValue(helperClass);
                        startActivity(new Intent(RegisterWindow.this, com.dinhtrongdat.jobportal.LoginActivity.class));
                    }

            });

    }

    private void Hook(){
        user = (TextInputLayout)findViewById(R.id.username_signup);
        password = (TextInputLayout)findViewById(R.id.password_signup);
        Phone = (TextInputLayout)findViewById(R.id.phone);
        btnReg = (Button) findViewById(R.id.btnSignUp);
    }

    private Boolean validateName(){
        String val = user.getEditText().getText().toString().trim();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if(val.isEmpty()){
            user.setError("Nh???p t??i kho???n");
            return false;
        }
        else if(val.length()>=15){
            user.setError("kh??ng ???????c qu?? 15 k?? t???");
            return false;
        }
        else if(!val.matches(noWhiteSpace)){
            user.setError("Kh??ng ???????c ch???a kho???ng tr???ng");
            return false;
        }
        else{
            user.setError(null);
            return true;
        }
    }

    private Boolean validatePass(){
        String val = password.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if(val.isEmpty()){
            password.setError("Nh???p m???t kh???u");
            return false;
        }
        else if(val.length()>=15){
            password.setError("Kh??ng ???????c qu?? 15 k?? t???");
            return false;
        }
        else if(!val.matches(noWhiteSpace)){
            password.setError("Kh??ng ???????c ch???a kho???ng tr???ng");
            return false;
        }
        else{
            password.setError(null);
            return true;
        }
    }


    private Boolean validationPhone(){
        String val = Phone.getEditText().getText().toString();

        if(val.isEmpty()){
            Phone.setError("??i???n s??? ??i???n tho???i");
            return false;
        }
        else{
            Phone.setError(null);
            return true;
        }
    }
}