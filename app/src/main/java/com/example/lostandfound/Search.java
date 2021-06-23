package com.example.lostandfound;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class Search extends AppCompatActivity {
    SharedPreferences sharedPreferences2 ;
    TextView textView;
    EditText editText;
    ImageButton submitImageButton;
    RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    private List<FeedItemDataRetrieve> listDataFeed;
    FirebaseRecyclerAdapter  firebaseRecyclerAdapter;
    private FeedDataAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_search );
        textView=findViewById( R.id.txt );
        editText=findViewById( R.id.search_field );
        submitImageButton=findViewById( R.id.searchSubmitBtn );
        recyclerView=findViewById( R.id.recyclerSearchItem);
        mDatabase= FirebaseDatabase.getInstance().getReference( ).child( "feed" );
        /**
         * sharedpreference for language
         */
        sharedPreferences2 = getApplicationContext().getSharedPreferences("MyLang", 0);
        String languageFromSharedPRef = sharedPreferences2.getString( "userLanguageChoice", null );

        languageChangeProg( languageFromSharedPRef );

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        try{
        submitImageButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String searchText=editText.getText().toString().trim();
                //Toast.makeText( getApplicationContext(), searchText,Toast.LENGTH_LONG ).show();
                //recyclerView.setAdapter( "jags" );
                firebaseUserSearch(searchText);
            }


        } );}catch (Exception e){
           // Toast.makeText( getApplicationContext(), e.getMessage(),Toast.LENGTH_LONG ).show();
        }


    }

    private  void firebaseUserSearch(String searchText) {
        /* ItemDataREtreive.class,
                R.layout.activity_item_data_retreive,
                ItemViewHolder.class,
                firebaseSearchQuery*/

        Query firebaseSearchQuery = mDatabase.orderByChild( "category1" ).startAt( searchText ).endAt( searchText + "\uf8ff" );

        FirebaseRecyclerOptions<FeedItemDataRetrieve> options=new FirebaseRecyclerOptions.Builder<FeedItemDataRetrieve>()
                .setQuery( firebaseSearchQuery,FeedItemDataRetrieve.class )
                .setLifecycleOwner( this )
                .build();

          firebaseRecyclerAdapter= new FirebaseRecyclerAdapter<FeedItemDataRetrieve, ItemViewHolder>(options ) {

           protected void onBindViewHolder(@NonNull ItemViewHolder holder, int position, @NonNull FeedItemDataRetrieve model) {
                //model= listDataFeed.get(position);

                holder.what_txt.setText( model.getWhatItem() );
                holder.addressTxt.setText( model.getAddress() );
                holder.descTxt.setText( model.getDesc() );
                holder.date.setText( model.getTime() );
                holder.category.setText( model.getCategory1() );
                Toast.makeText( getApplicationContext(),model.getWhatItem(),Toast.LENGTH_LONG ).show();
               // holder.setDetails( model.getPhone(), model.getAddress(), model.getCategory1(), model.getCategory2(), model.getDesc(), model.getStatus(), model.getTime(), model.getTimestamp(), model.getWhatItem() );

           }

            @NonNull
            @Override
            public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from( parent.getContext() ).inflate( R.layout.activity_item_data_retreive,parent,false );
                return new ItemViewHolder( view );
            }

        };
    // Toast.makeText( getApplicationContext(),"adapter: "+firebaseRecyclerAdapter,Toast.LENGTH_LONG ).show();

        recyclerView.setAdapter( firebaseRecyclerAdapter);


    }
    private void languageChangeProg(String language) {
        /**
         * the language var is passed from shared preference
         */
        //Toast.makeText( MainActivity.this,"languageChangeProg  "+language,Toast.LENGTH_SHORT ).show();
        Context context ;
        Resources resources;
        if (language!=null && language.equalsIgnoreCase( "English"  ) ){

            try {
                //Toast.makeText( MainActivity.this,language,Toast.LENGTH_SHORT ).show();
                context=LocaleHelper.setLocale( Search.this,"en" );
                resources=context.getResources();
                textView.setText( resources.getString( R.string.searchText ) );
                editText.setHint( resources.getString( R.string.search ) );
                //Menu home= findViewById( R.id.all_item );
                //menu.findItem( R.id.all_item ).setTitle( resources.getString( R.string.home ));
                //menu.findItem( R.id.all_item ).setTitle( "lALA");
                //Toast.makeText( MainActivity.this,resources.getString( R.string.home ),Toast.LENGTH_SHORT ).show();

            }catch (Exception e){
                // Toast.makeText( MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT ).show();

            }finally {
                context=LocaleHelper.setLocale( Search.this,"en" );
                resources=context.getResources();
                // Menu home= findViewById( R.id.all_item );
                textView.setText( resources.getString( R.string.searchText ) );
                editText.setHint( resources.getString( R.string.search ) );

                //menu.findItem( R.id.all_item ).setTitle( resources.getString( R.string.home ));
                //menu.findItem( R.id.all_item ).setTitle( "lALA");
                // Toast.makeText( MainActivity.this,resources.getString( R.string.home ),Toast.LENGTH_SHORT ).show();
            }


        }else if(language!=null && language.equals( ("Bengal") )){

            try {
                //Toast.makeText( MainActivity.this,language,Toast.LENGTH_SHORT ).show();
                context=LocaleHelper.setLocale( Search.this,"bn" );
                resources=context.getResources();
                //Menu home= findViewById( R.id.all_item );
                //Toast.makeText( getApplicationContext(),"Yo and Yo",Toast.LENGTH_LONG ).show();
                textView.setText( resources.getString( R.string.searchText ) );
                editText.setHint( resources.getString( R.string.search ) );
                //menu.findItem( R.id.all_item ).setTitle( resources.getString( R.string.home ));
                Toast.makeText( Search.this,resources.getString( R.string.whatisit ),Toast.LENGTH_SHORT ).show();
            }catch (Exception e){
                //Toast.makeText( MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT ).show();
            }
        }

    }
/*
    @Override
    public void onStart() {
        super.onStart();
        firebaseRecyclerAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        firebaseRecyclerAdapter.stopListening();
    }*/
    public class ItemViewHolder extends RecyclerView.ViewHolder{
        View mView;
        private TextView what_txt,category,date,addressTxt,descTxt;
        public ItemViewHolder(@NonNull View itemView) {
            super( itemView );
            mView=itemView;
            what_txt=(TextView)mView.findViewById(R.id.what_id);
            category=(TextView)mView.findViewById(R.id.category);
            date=(TextView)mView.findViewById(R.id.time);
            addressTxt=(TextView)mView.findViewById(R.id.address);
            descTxt=(TextView)mView.findViewById(R.id.desc);
        }
        public void setDetails(String phone, String address, String category1, String category2, String desc, String status, String time, String timestamp, String whatItem){



          /*  what_txt.setText( whatItem );
            category.setText( category1  );
            date.setText( time );
            addressTxt.setText( address );
            descTxt.setText( desc );*/

         /*   what_txt.setText( "hh");
            category.setText( "hh" );
            date.setText( "hh" );
            addressTxt.setText( "hh");
            descTxt.setText( "hh" );*/


            //Glide.with(ctx).load(userImage).into(user_image);


        }
    }

}
