package com.example.lostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class PhoneVerification extends AppCompatActivity {
    EditText Code;
    Button otpCheck;
    String verificationCode , systemOtp;
    FirebaseAuth mAuth;
   String phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_verification);

        mAuth=FirebaseAuth.getInstance();
        Code=findViewById(R.id.code);
        otpCheck=findViewById(R.id.btn);
        systemOtp = getIntent().getStringExtra("sent_code");
        phone= getIntent().getStringExtra("phone");
        final String password = getIntent().getStringExtra("pass");
        final String username= getIntent().getStringExtra("name");

        otpCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                verificationCode =Code.getText().toString().trim();
                if (!verificationCode.isEmpty()){
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(systemOtp,verificationCode);
                    signIn(credential);
                   // Toast.makeText(PhoneVerification.this,"Verification code:"+verificationCode+" System code "+systemOtp,Toast.LENGTH_LONG).show();

                        /**
                         * user data passing to database class USER
                         */
                       User user = new User(username,phone,password);
                       user.writeNewUser(username,phone,password);
                        Intent intent=new Intent(PhoneVerification.this,Login.class);
                        startActivity(intent);
                        finish();


                }else {
                    //Toast.makeText(PhoneVerification.this,"Please enter your otp",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private  void signIn(PhoneAuthCredential credential){
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    sendtoNext();
                   // Toast.makeText(PhoneVerification.this,"Successful",Toast.LENGTH_LONG).show();
                }else{
                   // Toast.makeText(PhoneVerification.this,"UnSuccessful "+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    protected void onStart() {
        /**
         * Checking if the user is logged in or not
         */
        super.onStart();
        FirebaseUser user=mAuth.getCurrentUser();

        if(user!=null){
            sendtoNext();
        }
    }
    private void sendtoNext() {
        /**
         * if the user is logged in or not
         */

            Intent intent = new Intent(PhoneVerification.this,Login.class);
            //intent.putExtra("phone",phone);
            startActivity(intent);
            finish();

    }
}
