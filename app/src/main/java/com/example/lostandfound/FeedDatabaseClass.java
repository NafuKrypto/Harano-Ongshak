package com.example.lostandfound;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FeedDatabaseClass {
    String phone,primarykey,category1,category2,what,address,time,timestamp,desc,status,type;
    public FeedDatabaseClass(String phone, String primarykey, String category1, String category2, String what, String address, String time, String timestamp, String desc, String status,String type) {
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
        this.type=type;
    }

    protected  void userFeed(){
        //String phone, String primarykey, String category1, String category2, String what, String address, String time, String timestamp, String desc,String status,String type
        DatabaseReference mDatabase;
        mDatabase= FirebaseDatabase.getInstance().getReference("feed");
        // String char[6]=[" ",".","+"];

        // Toast.makeText( DatabaseAddItem.class,""+primarykey,Toast.LENGTH_LONG ).show();

        mDatabase.child( primarykey ).child( "Phone" ).setValue( phone );
        mDatabase .child( primarykey).child( "type" ).setValue( status);
        mDatabase.child( primarykey ).child( "whatItem" ).setValue( what);
        mDatabase.child( primarykey).child( "address" ).setValue( address );
        mDatabase .child( primarykey).child( "time" ).setValue( time );
        mDatabase .child( primarykey).child( "desc" ).setValue( desc );
        mDatabase .child( primarykey).child( "category1" ).setValue( category1 );
        mDatabase .child( primarykey).child( "category2" ).setValue( category2 );
        mDatabase .child( primarykey).child( "timestamp" ).setValue( timestamp );
        mDatabase .child( primarykey).child( "status" ).setValue( status);
        mDatabase .child( primarykey).child( "type" ).setValue( type);

    }
}
