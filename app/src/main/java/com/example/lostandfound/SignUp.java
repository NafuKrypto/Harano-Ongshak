package com.example.lostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

public class SignUp extends AppCompatActivity {
    EditText user_name,password,phone_number;
    Button signup,login;
    String mVerificationId,phonenumber,countrycode;
    String phone,username,user_password;
    PhoneAuthProvider.ForceResendingToken mResendToken;

    FirebaseAuth mAuth;

    private  PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users");
    //Query query;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        /**
         * user input from layout
         */

        signup=(findViewById(R.id.btn));
        //login=findViewById(R.id.login_btn);
        user_name=(findViewById(R.id.name));
        phone_number=(findViewById(R.id.phone));
        password=(findViewById(R.id.pass));

        mAuth=FirebaseAuth.getInstance();

      // FirebaseAuth.getInstance().getFirebaseAuthSettings().forceRecaptchaFlowForTesting(true);



        try {


           signup.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   //Toast.makeText( SignUp.this,"signup",Toast.LENGTH_LONG ).show();
                   phonenumber = phone_number.getText().toString().trim();
                   countrycode = "+880";
                   phone = countrycode + "" + phonenumber;
                   username = user_name.getText().toString().trim();
                   user_password = password.getText().toString().trim();


                   checkUser( phone );

                   if (checkInput()) {
                           /**
                            * phone verifications
                            */
                           //Toast.makeText(SignUp.this,"Button CLicked",Toast.LENGTH_LONG).show();
                           PhoneAuthOptions options = PhoneAuthOptions.newBuilder(mAuth)
                                   .setPhoneNumber(phone)
                                   .setTimeout(60L, TimeUnit.SECONDS)
                                   .setActivity(SignUp.this)
                                   .setCallbacks(mCallbacks)
                                   .build();
                           PhoneAuthProvider.verifyPhoneNumber(options);

                       } else {

                     //  Toast.makeText(SignUp.this,"Invalid Information",Toast.LENGTH_SHORT).show();

                       }






           }
    });}catch (Exception e){
           // Toast.makeText( SignUp.this,"exception: "+e.getMessage(),Toast.LENGTH_LONG ).show();
        }




       mCallbacks=new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
           @Override
           public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
               signin(phoneAuthCredential);
           }

           @Override
           public void onVerificationFailed(@NonNull FirebaseException e) {
              // Toast.makeText(SignUp.this,e.getMessage(),Toast.LENGTH_LONG).show();

           }

           @Override
           @RequiresApi(28)
           public void onCodeSent(@NonNull final String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
               super.onCodeSent(s, forceResendingToken);
               /**
                * if user has to input code manually
                */
               //Toast.makeText(SignUp.this,"Sms code:"+s,Toast.LENGTH_LONG).show();

               new Handler().postDelayed(new Runnable() {
                   /**
                    * to delay so that onverificationcomplete() can run
                    * before this
                    */
                   @Override

                  public void run() {
                       Intent intent = new Intent(SignUp.this,PhoneVerification.class);
                       intent.putExtra("sent_code",s);
                       intent.putExtra("name",username);
                       intent.putExtra("pass",user_password);
                       intent.putExtra("phone",phone);
                       startActivity(intent);
                       finish();
                  }
               },1000);

           }
       };

    }

    public void checkUser(String phone) {

        ref.orderByChild("Phone").equalTo(phone).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue() != null) {
                    //it means user already registered
                    //Add code to show your prompt
                    Toast.makeText(SignUp.this, "You are already regisered", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(SignUp.this, Login.class);
                    startActivity(intent);
                    finish();

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }



    private boolean checkInput() {
        /**
         * Checking user's phone number
         */
        boolean check = true;
        if (phonenumber.isEmpty()){
             phone_number.setError("Phone cannot be empty");
             Toast.makeText(SignUp.this,"Phone cannot be empty",Toast.LENGTH_LONG).show();
             check=false;
        }
        if(username.isEmpty()){
            user_name.setError("User name cannot be empty");
            Toast.makeText(SignUp.this,"User name cannot be empty",Toast.LENGTH_LONG).show();
            check=false;
        }
        if(user_password.isEmpty()){
            password.setError("Password  cannot be empty");
            Toast.makeText(SignUp.this,"Password  cannot be empty",Toast.LENGTH_LONG).show();
            check=false;
        }


        return check;
    }

    @Override
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

            Intent intent = new Intent(SignUp.this,Login.class);
            startActivity(intent);
            finish();
    }

    private  void signin(PhoneAuthCredential credential){
        mAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    sendtoNext();
                    Toast.makeText(SignUp.this,"Successful",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(SignUp.this,"UnSuccessful "+task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void LogIn(View view) {
        Intent intent =new Intent(SignUp.this,Login.class);
        startActivity(intent);
        finish();
    }
}
