package com.example.lostandfound;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

public class DescriptiveView extends AppCompatActivity {
    Toolbar toolbar;
    TextView textView;
    TextView whatText,addressTitleTxt,detailsTitleText,category1Text,category2Text,addressTxt,dateTxt,detailsTxt;
    private List<FeedItemDataRetrieve> listDataFeed;
    private FeedDataAdapter adapterFeed;
    String what,desc,time,timestampString,primaryKey,category1,category2,address;
    String cat2,timeStamp,type;
    ViewPager viewPager;
    ViewPagerAdapter adapter;
    List<ImageClass> listImage;
    private String [] imageUrls =new String[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_descriptive_view );
        toolbar = (Toolbar) findViewById( R.id.toolbar );

        setSupportActionBar( toolbar );
        getSupportActionBar().setHomeAsUpIndicator( R.drawable.back_button );
        getSupportActionBar().setDisplayHomeAsUpEnabled( true );
        getSupportActionBar().setDisplayShowTitleEnabled(false); // hide the current title from the Toolbar

        //textView=findViewById( R.id.text );
        whatText=findViewById( R.id.whatTxt );
        addressTitleTxt=findViewById( R.id.addressTitleTxt );
        addressTxt=findViewById( R.id.addressTxt );
        dateTxt=findViewById( R.id.dateTxt );
        detailsTitleText=findViewById( R.id.detailsTitleTxt );
        detailsTxt=findViewById( R.id.detailsTxt );
        category1Text=findViewById( R.id.category1Txt );
        category2Text=findViewById( R.id.category2Txt);



        Intent intentFromAdapter=getIntent();
        primaryKey= intentFromAdapter.getStringExtra("primaryKey");
        what=intentFromAdapter.getStringExtra("what");
        time=intentFromAdapter.getStringExtra("time");
        desc=intentFromAdapter.getStringExtra("desc");
        timestampString=intentFromAdapter.getStringExtra("timestamp");
        category1=intentFromAdapter.getStringExtra("category1");
        category2=intentFromAdapter.getStringExtra("category2");
        address=intentFromAdapter.getStringExtra("address");
        //int * positionValue= &primaryKey;

        //ViewPager viewPager = findViewById(R.id.view_pager);

        viewPager=findViewById( R.id.view_pager );
        //ViewPagerAdapter adapter = new ViewPagerAdapter(this, imageUrls);
        //viewPager.setAdapter(adapter);
        retrieveOtherData( primaryKey );
        retrieveImageUrl( primaryKey );

       /* textView.setText(what+" "+ primaryKey+" "+address+"  "+category1+" "+category2+" "
        +desc+" "+timestampString+" "+time+" ");*/

        whatText.setText( what );
        addressTxt.setText( address );
        Toast.makeText( getApplicationContext(),"category 2:"+cat2,Toast.LENGTH_LONG ).show();
        category1Text.setText( category1 );
        if (category2.equalsIgnoreCase( "None" )){
            category2Text.setText( category2 );
            category2Text.setVisibility( View.VISIBLE );
        }else{
            category2Text.setVisibility( View.GONE );
        }
        dateTxt.setText( time );
        detailsTxt.setText( desc );

    }

    private void retrieveImageUrl(String primaryKey) {
        listImage=new ArrayList<>(  );
        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("postImage").child( primaryKey );
        databaseReference.addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int i=0;
                if (snapshot.exists()){
                    for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                         // imageUrls=imageUrls+", "+dataSnapshot.getValue();
                        //imageUrls=dataSnapshot.getValue().toString();
                       ImageClass imageclassUri=dataSnapshot.getValue(ImageClass.class);

                        //listImage.add( imageclassUri);
                        try{ imageUrls[i]=imageclassUri.getImageLink();
                           // Toast.makeText( getApplicationContext(),imageclassUri.getImageLink(),Toast.LENGTH_LONG ).show();
                            //Toast.makeText( getApplicationContext(),imageUrls[i],Toast.LENGTH_LONG ).show();
                         i++;}catch (Exception e){
                           // Toast.makeText( getApplicationContext(), e.getMessage(),Toast.LENGTH_LONG ).show();
                        }
                    }
                     adapter=new ViewPagerAdapter( getApplicationContext(),imageUrls);
                      if(adapter==null){
                        //  Toast.makeText( getApplicationContext(),"null",Toast.LENGTH_SHORT ).show();

                      }
                      try {
                          viewPager.setAdapter( adapter );
                      } catch (Exception e){
                          //Toast.makeText( getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT ).show();
                      }
                     //textView.setText( imageUrls[0]+" "+imageUrls[1]+" "+imageUrls[2] );
                    //textView.setText( String.valueOf( listImage ) );
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        } );


    }

    protected void retrieveOtherData(String primaryKey){

        DatabaseReference databaseReference= FirebaseDatabase.getInstance().getReference("feed");
        databaseReference.orderByChild( primaryKey ).addListenerForSingleValueEvent( new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                   for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                       // cat2=dataSnapshot.child( "category2" ).getValue().toString();
                        timeStamp=dataSnapshot.child( "timestamp" ).getValue().toString();
                        type=dataSnapshot.child( "type" ).getValue().toString();
                   }

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        } );



    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                Intent intent=new Intent( getApplicationContext(),MainActivity.class );
                startActivity( intent );
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public  static class ImageClass{
        public String imageLink;
        public ImageClass() {
        }
        public ImageClass(String imageLink){
            this.imageLink=imageLink;
        }

        public String getImageLink() {
            return imageLink;
        }

        public void setImageLink(String imageLink) {
            this.imageLink = imageLink;
        }
    }
}
