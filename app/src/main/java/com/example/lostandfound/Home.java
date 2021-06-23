package com.example.lostandfound;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity {
    FirebaseAuth mAuth= FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //sendtoNext();
        final String useruid_phone= getIntent().getStringExtra("phone");

        final TextView textView=(findViewById(R.id.username));
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
//       final String userid = user.getUid();

       // Toast.makeText(this,userid,Toast.LENGTH_LONG).show();
        String s =user.getPhoneNumber();
       Toast.makeText(this,s+"",Toast.LENGTH_LONG).show();




    }

    private void sendtoNext() {
        /**
         * if the user is logged in or not
         */



    }
}
