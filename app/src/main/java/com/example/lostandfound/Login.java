package com.example.lostandfound;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    EditText password,phone_number;
    Button login;
    FirebaseAuth mAuth;
    String phonenumber,user_password,countrycode,phone;
    TextView warning;
    DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users");
    Query query;
    //shared preference

    SharedPreferences sharedpreferences ;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phone_number=findViewById(R.id.editText2);
        password = findViewById(R.id.pass);
        login=findViewById(R.id.btn);
        warning=findViewById(R.id.warning);

        mAuth=FirebaseAuth.getInstance();
        //Context context = getActivity();
        sharedpreferences = getApplicationContext().getSharedPreferences("MyPref", 0);//0 for private mode
        editor=sharedpreferences.edit();
        //login btn starts
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                phonenumber = phone_number.getText().toString().trim();
                countrycode = "+880";
                phone = countrycode + "" + phonenumber;
                user_password = password.getText().toString().trim();
               // Toast.makeText(Login.this,user_password,Toast.LENGTH_LONG).show();

                checkInput();
                if (phone !=null && user_password!=null){
                    checkUser( phone );
                }else{
                    checkInput();
                }

            }
        });
    }

    public boolean checkUser(final String phone) {
        /**
         * Checking user from database through phone number
         */
        final boolean[] check = {false};
        query= ref.orderByChild( "Phone" ).equalTo( phone );
        query.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    //it means user already registered
                    //Add code to show your prompt

                    String passwordFromDB = snapshot.child(phone).child("Password").getValue(String.class);
                    String username=snapshot.child( phone ).child( "Username" ).getValue(String.class);
                    //Toast.makeText(Login.this, passwordFromDB, Toast.LENGTH_LONG).show();
                    if (passwordFromDB.equals( user_password )) {
                        //check = true;
                        //store data to sharedpreference
                        editor.putString( "userPhonenumberSaved",phone );
                        editor.putString("usernameSaved",username);
                        editor.apply();
                        //Toast.makeText( Login.this, ""+ mAuth.getCurrentUser(),Toast.LENGTH_SHORT ).show();
                        Intent intent = new Intent(Login.this,MainActivity.class);
                        intent.putExtra("phone",phone);
                        //intent.putExtra(  )
                        startActivity(intent);
                        finish();
                        //Toast.makeText(Login.this, ""+ check[0] , Toast.LENGTH_LONG).show();
                    } else if (!passwordFromDB.equals( user_password )){
                        //check =false;
                        warning.setVisibility(View.VISIBLE);
                        warning.setText( "Password is incorrect" );
                    }
                }else if(!snapshot.exists()){
                   // Toast.makeText(Login.this, ""+ check[0] , Toast.LENGTH_LONG).show();
                    //check[0] =false;
                   // Toast.makeText(Login.this, ""+ check[0] , Toast.LENGTH_LONG).show();
                    warning.setVisibility(View.VISIBLE);
                    warning.setText("The Number is not registered");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        } );

        return check[0];

    }
    private boolean checkInput() {
        /**
         * Checking user's phone number
         */
        boolean check = true;
        if (phonenumber.isEmpty()){
            phone_number.setError("Phone cannot be empty");
            Toast.makeText(Login.this,"Phone cannot be empty",Toast.LENGTH_LONG).show();
            check=false;
        }

        if(user_password.isEmpty()){
            password.setError("Password  cannot be empty");
            Toast.makeText(Login.this,"Password  cannot be empty",Toast.LENGTH_LONG).show();
            check=false;
        }


        return check;
    }
    protected void onStart() {
        /**
         * Checking if the user is logged in or not
         */
        super.onStart();
        SharedPreferences sharedpreferences = getApplicationContext().getSharedPreferences("MyPref", 0);
        String phoneFromSharedPreference=sharedpreferences.getString( "userPhonenumberSaved",null );
      /*  FirebaseUser user=mAuth.getCurrentUser();

        if(user!=null){
            sendtoNext(user);
        }*/
      if (phoneFromSharedPreference!=null){
          sendtoNext(  );
      }
    }

    private void sendtoNext( ) {

        /**
         * if the user is logged in or not
         */

        Intent intent = new Intent(Login.this,MainActivity.class);
        //intent.putExtra( "user",user );
        startActivity(intent);
        finish();
    }

    public void ResetPass(View view) {
        /**
         * reset password for user
         */
    }


    public void SignUp(View view) {
        /**for creating a new account
         *
         */
        Intent intent =new Intent(Login.this,SignUp.class);
        startActivity(intent);
    }
}
