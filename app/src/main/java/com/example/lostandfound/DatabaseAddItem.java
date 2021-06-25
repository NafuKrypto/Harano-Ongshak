package com.example.lostandfound;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatabaseAddItem {
    String phone,primarykey,category1,category2,what,address,time,timestamp,desc,status;

    public DatabaseAddItem(String phone, String primarykey, String category1, String category2, String what, String address, String time, String timestamp, String desc, String status) {
        this.phone = phone;
        this.primarykey = primarykey;
        this.category1 = category1;
        this.category2 = category2;
        this.what = what;
        this.address = address;
        this.time = time;
        this.timestamp = timestamp;
        this.desc = desc;
        this.status=status;

    }

    protected  void writeNewUser(String phone, String primarykey, String category1, String category2, String what, String address, String time, String timestamp, String desc,String status){
        DatabaseReference mDatabase;
        mDatabase= FirebaseDatabase.getInstance().getReference();
       // String char[6]=[" ",".","+"];

       // Toast.makeText( DatabaseAddItem.class,""+primarykey,Toast.LENGTH_LONG ).show();

        mDatabase.child( "lostItem" ).child( primarykey ).child( "Phone" ).setValue( phone );
        mDatabase.child( "lostItem" ).child( primarykey ).child( "whatItem" ).setValue( what);
        mDatabase.child( "lostItem" ).child( primarykey).child( "address" ).setValue( address );
        mDatabase.child( "lostItem" ).child( primarykey).child( "time" ).setValue( time );
        mDatabase.child( "lostItem" ).child( primarykey).child( "desc" ).setValue( desc );
        mDatabase.child( "lostItem" ).child( primarykey).child( "primaryKey" ).setValue( primarykey);
        mDatabase.child( "lostItem" ).child( primarykey).child( "category1" ).setValue( category1 );
        mDatabase.child( "lostItem" ).child( primarykey).child( "category2" ).setValue( category2 );
        mDatabase.child( "lostItem" ).child( primarykey).child( "timestamp" ).setValue( timestamp );
        mDatabase.child( "lostItem" ).child( primarykey).child( "status" ).setValue( status);

    }

    protected  void writeNewUserFound(String phone, String primarykey, String category1, String category2, String what, String address, String time, String timestamp, String desc,String status){
        DatabaseReference mDatabase;
        mDatabase= FirebaseDatabase.getInstance().getReference();
        // String char[6]=[" ",".","+"];

        // Toast.makeText( DatabaseAddItem.class,""+primarykey,Toast.LENGTH_LONG ).show();

        mDatabase.child( "foundItem" ).child( primarykey ).child( "Phone" ).setValue( phone );
        mDatabase.child( "foundItem" ).child( primarykey ).child( "whatItem" ).setValue( what);
        mDatabase.child( "foundItem" ).child( primarykey).child( "address" ).setValue( address );
        mDatabase.child( "foundItem" ).child( primarykey).child( "time" ).setValue( time );
        mDatabase.child( "foundItem" ).child( primarykey).child( "desc" ).setValue( desc );
        mDatabase.child( "foundItem" ).child( primarykey).child( "primaryKey" ).setValue( primarykey );
        mDatabase.child( "foundItem" ).child( primarykey).child( "category1" ).setValue( category1 );
        mDatabase.child( "foundItem" ).child( primarykey).child( "category2" ).setValue( category2 );
        mDatabase.child( "foundItem" ).child( primarykey).child( "timestamp" ).setValue( timestamp );
        mDatabase.child( "foundItem" ).child( primarykey).child( "status" ).setValue( status );

    }
}
