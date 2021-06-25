package com.example.lostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LostItem extends AppCompatActivity {
    private List<ItemDataREtreive> listData;
    Query latestquery;
    private DataAdapter adapter;
    RecyclerView recyclerView;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_lost_item );

        toolbar = (Toolbar) findViewById( R.id.toolbar );

        setSupportActionBar( toolbar );
        getSupportActionBar().setHomeAsUpIndicator( R.drawable.back_button );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        getSupportActionBar().setDisplayShowTitleEnabled(false); // hide the current title from the Toolbar



        recyclerView=findViewById( R.id.recyclerFoundItem );
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout( true );
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        listData=new ArrayList<>();
        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference("lostItem");
        latestquery=nm.orderByChild( "timestamp" );
        latestquery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                        ItemDataREtreive l=npsnapshot.getValue(ItemDataREtreive.class);

                        listData.add(l);
                    }
                    adapter=new DataAdapter(getApplicationContext(),listData );
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent( LostItem.this,MainActivity.class );
                startActivity( intent );
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
