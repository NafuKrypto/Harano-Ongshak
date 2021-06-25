package com.example.lostandfound;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Profile extends AppCompatActivity {
 private String Phone,User;
 RecyclerView recyclerView;
    private List<FeedItemDataRetrieve> listDataFeed;
    private FeedDataAdapter adapterFeed;
    Query latestquery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_profile );
        SharedPreferences sharedpreferences = getApplicationContext().getSharedPreferences("MyPref", 0);
        Phone=sharedpreferences.getString( "userPhonenumberSaved",null );
        User=sharedpreferences.getString( "usernameSaved",null );
        TextView name=findViewById( R.id.name );
        name.setText( User );
        recyclerView=findViewById( R.id.recyclerProfile);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout( true );
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        listDataFeed=new ArrayList<>();
        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference("feed");
        //latestquery=nm.orderByChild( "timestamp" );

        nm.orderByChild( "Phone" ).equalTo( Phone ).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                        //keY=npsnapshot.getKey();
                        FeedItemDataRetrieve l=npsnapshot.getValue(FeedItemDataRetrieve.class);
                        //Toast.makeText(MainActivity.this,l.getCategory1(), Toast.LENGTH_LONG).show();
                        listDataFeed.add(l);
                    }

                    adapterFeed=new FeedDataAdapter( getApplicationContext(),listDataFeed );
                    // Collections.reverse( Collections.singletonList( adapter ) );
                    //Toast.makeText( MainActivity.this,""+adapterFeed,Toast.LENGTH_LONG ).show();
                    recyclerView.setAdapter(adapterFeed);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Toast.makeText( MainActivity.this,"databse failed "+databaseError ,Toast.LENGTH_LONG ).show();
            }
        });

    }
}
