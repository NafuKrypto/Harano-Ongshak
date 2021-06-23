package com.example.lostandfound;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class User {
    String name , phone , password;
    private DatabaseReference mDatabase;
    public User(String name, String phone, String password) {
        this.name = name;
        this.phone = phone;
        this.password = password;
        //writeNewUser(name,hone,password);p
    }

    protected  void writeNewUser(String name,String phone,String password){
        mDatabase= FirebaseDatabase.getInstance().getReference();
        mDatabase.child("users").child(phone).child("Username").setValue(name);
        mDatabase.child("users").child(phone).child("Phone").setValue(phone);
        mDatabase.child("users").child(phone).child("Password").setValue(password);

    }

}
