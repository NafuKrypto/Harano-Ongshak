package com.example.lostandfound;

import android.app.AlertDialog;
import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import android.widget.SearchView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener  {
    FirebaseAuth mAuth= FirebaseAuth.getInstance();
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    Menu menu;
    TextView textView,losstText,foundText,addLostText,addFoundText,qrcodeText;
    FirebaseUser user1;
   public  TextView name,phoneuser;
   String Phone,User;
    private List<ItemDataREtreive> listData;
   private DataAdapter adapter;
    private List<FeedItemDataRetrieve> listDataFeed;
    private FeedDataAdapter adapterFeed;
    RecyclerView recyclerView;
    Query latestquery;
    public static String lang=null;
    SharedPreferences sharedPreferences2 ;
    SharedPreferences.Editor editor2;
    private String keY="";
    String languageFromSharedPRef;
    boolean shouldChangeMenuLabels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        /**
         * shared preference for user information
         */
        SharedPreferences sharedpreferences = getApplicationContext().getSharedPreferences("MyPref", 0);
        /**
        shared preferences for language
         */
        sharedPreferences2 = getApplicationContext().getSharedPreferences("MyLang", 0);//0 for private mode
        editor2=sharedPreferences2.edit();
        losstText=findViewById( R.id.lostTextview );
        foundText=findViewById( R.id.foundTextview);
        addLostText=findViewById( R.id.addLostTextview);
        addFoundText=findViewById( R.id.addFoundTextview);
        qrcodeText=findViewById( R.id.qrCodeTextview );
        Button refreshBtn=findViewById( R.id.refreshBtn );

        try {
              languageFromSharedPRef = sharedPreferences2.getString( "userLanguageChoice", null );
            Toast.makeText( getApplicationContext(), languageFromSharedPRef,Toast.LENGTH_LONG ).show();
            if (languageFromSharedPRef == null) {
                Toast.makeText( MainActivity.this, "language from sharedpref is null ", Toast.LENGTH_SHORT ).show();
                editor2.putString( "userLanguageChoice", "English" );
                editor2.apply();
            } else {
                languageChangeProg( languageFromSharedPRef );
            }
        }catch (Exception e){
            //Toast.makeText( MainActivity.this,e.getMessage(),Toast.LENGTH_LONG ).show();
            //
            // Toast.makeText( MainActivity.this,e.getMessage(),Toast.LENGTH_LONG ).show();
        }





        // int defaultValue = getResources().getInteger(R.integer.saved_high_score_default_key);
       // int highScore = sharedPref.getInt(getString(R.string.saved_high_score_key), defaultValue);
       // Phone = getIntent().getStringExtra( "phone" );
     //  changeLanguage();
        /**
         * shared preference for user information
         */
       Phone=sharedpreferences.getString( "userPhonenumberSaved",null );
       User=sharedpreferences.getString( "usernameSaved",null );
       // UserCheckLoggedIn();
        /**
         *
         */
        drawerLayout = findViewById( R.id.drawer_layout );
        navigationView = findViewById( R.id.nav_view );
        //textView=findViewById(R.id.textView);
        View v = navigationView.getHeaderView(0);
        name=v.findViewById( R.id.name );
        phoneuser=v.findViewById( R.id.phoneuser );
        toolbar = findViewById( R.id.toolbar );

        /**
         * calling cardview of item_data_retrieve.xml
         *
         */
      /*  View inflatedView=getLayoutInflater().inflate( R.layout.activity_item_data_retreive,null );
        CardView clickableCardView= (CardView) inflatedView.findViewById( R.id.cardviewItemDataREtrieve );

        clickableCardView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        } );*/


        if(Phone!=null){
        try {
            phoneuser.setText( Phone );
        }catch(Exception e){

        }finally {
            phoneuser.setText( Phone );
        }
        }else if(Phone==null){

        }
        if (User!=null){
            name.setText( User );
        }
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        //toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.nav_icon)); // setting a navigation icon in Toolbar
        getSupportActionBar().setDisplayShowTitleEnabled(false); // hide the current title from the Toolbar
        //Menu menu = navigationView.getMenu();
        //menu.findItem( R.id.nav_logout ).setVisible( false )


        navigationView.bringToFront();
        ActionBarDrawerToggle toggle=new
                ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setCheckedItem(R.id.all_item);


        recyclerView=findViewById( R.id.recycler1 );
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


// white background notification bar
        //whiteNotificationBar(recyclerView);

        feedItemAllDisplay();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setReverseLayout( true );
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        DividerItemDecoration dividerItemDecoration=new DividerItemDecoration( this,DividerItemDecoration.VERTICAL );
        recyclerView.addItemDecoration( dividerItemDecoration );
   //fetchData();
        /**
         * Cardaview
         */
        CardView lostItemCard = findViewById( R.id.lostItemCard );
        CardView foundItemCard = findViewById( R.id.foundItemCard );
        CardView addLostItemCard = findViewById( R.id.addLostItemCard);
        CardView addFoundItemCard = findViewById( R.id.addFoundItemCard );
        CardView qrcodeCard=findViewById( R.id.qrCode );

        lostItemCard.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentcard1=new Intent( MainActivity.this,LostItem.class );
                startActivity( intentcard1);
                finish();
            }
        } );

        foundItemCard.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentcard2=new Intent( MainActivity.this,FoundItem.class );
                startActivity( intentcard2 );
                finish();
            }
        } );

        addLostItemCard.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(Phone!=null) {
                    Intent intentcard3 = new Intent( MainActivity.this, AddItem.class );
                    intentcard3.putExtra( "item_type", "Lost" );
                    intentcard3.putExtra( "phone", Phone );
                    startActivity( intentcard3 );
                    finish();
                }else if(Phone==null){
                    Intent intent5 = new Intent( MainActivity.this, Login.class );
                    startActivity( intent5 );
                    finish();
                }
            }
        } );

        addFoundItemCard.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Phone!=null) {
                    Intent intentcard4 = new Intent( MainActivity.this, AddItem.class );
                    intentcard4.putExtra( "item_type", "Found" );
                    intentcard4.putExtra( "phone", Phone );
                    startActivity( intentcard4);
                    finish();
                }else if(Phone==null){
                    Intent intent5 = new Intent( MainActivity.this, Login.class );
                    startActivity( intent5 );
                    finish();
                }
            }
        } );

        qrcodeCard.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent6 = new Intent( MainActivity.this,QRCodeForm.class );
                startActivity( intent6 );
                Toast.makeText( MainActivity.this,"QR Code",Toast.LENGTH_SHORT ).show();
            }
        } );

       // invalidateOptionsMenu();

        refreshBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                feedItemAllDisplay();
            }
        } );
    }

    private  void  changeLanguage() {

        /**
         * Language Change from Alert Dialogue
         */


        //lang=getIntent().getStringExtra( "lang" );
        //lang="Bengal";
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(MainActivity.this);
        alertDialog.setTitle("Set a language");
        String[] items = {"Bengal","English"};
        final int[] checkedItem = {1};
        if(languageFromSharedPRef.equals( "Bengal" )){
            checkedItem[0]=0;
        }


        alertDialog.setSingleChoiceItems(items, checkedItem[0], new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                         lang="Bengal";
                         checkedItem[0] =0;
                       // editor2.putString( "userLanguageChoice",lang );
                       // editor2.apply();
                        break;
                    case 1:
                        lang="English";
                        checkedItem[0]=1;
                       // editor2.putString( "userLanguageChoice",lang );
                       // editor2.apply();
                        break;

                }
            }
        }).setPositiveButton( "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                try {
                    editor2.putString( "userLanguageChoice", lang );
                    editor2.apply();
                    languageChangeProg( sharedPreferences2.getString( "userLanguageChoice", null ) );
                    shouldChangeMenuLabels=true;
                }catch (Exception e){
                 // Toast.makeText( MainActivity.this,e.getMessage(),Toast.LENGTH_LONG ).show();
            }finally {

                }
                }
        } );

        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(false);
        alert.show();

    }

    public void languageChangeProg(String language){
        /**
         * the language var is passed from shared preference
         */
       // Toast.makeText( MainActivity.this,"languageChangeProg  "+language,Toast.LENGTH_SHORT ).show();
        Context context ;
        Resources resources;
        if (language!=null && language.equalsIgnoreCase( "English"  ) ){

            try {
               // Toast.makeText( MainActivity.this,language,Toast.LENGTH_SHORT ).show();
                context=LocaleHelper.setLocale( MainActivity.this,"en" );
                resources=context.getResources();

                losstText.setText( resources.getString( R.string.lostItem ) );
                foundText.setText( resources.getString( R.string.foundItem ) );
                //foundText.setText( resources.getString( R.string.foundItem ) );
                addLostText.setText( resources.getString( R.string.addLostItem) );
                addFoundText.setText( resources.getString( R.string.addFoundItem) );
                qrcodeText.setText( resources.getString( R.string.QRcode ) );
                
                NavigationView navigationView=findViewById( R.id.all_item );



              //  menu.findItem( R.id.all_item ).setTitle( "ana" );

                //i home= findViewById( R.id.all_item );
                //menu.findItem( R.id.all_item ).setTitle( resources.getString( R.string.home ));
                //menu.findItem( R.id.all_item ).setTitle( "lALA");
                //Toast.makeText( MainActivity.this,resources.getString( R.string.home ),Toast.LENGTH_SHORT ).show();

            }catch (Exception e){
                //Toast.makeText( MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT ).show();

            }finally {
                context=LocaleHelper.setLocale( MainActivity.this,"en" );
                resources=context.getResources();
               // Menu home= findViewById( R.id.all_item );
                losstText.setText( resources.getString( R.string.lostItem ) );
                foundText.setText( resources.getString( R.string.foundItem ) );
                //foundText.setText( resources.getString( R.string.foundItem ) );
                addLostText.setText( resources.getString( R.string.addLostItem) );
                addFoundText.setText( resources.getString( R.string.addFoundItem) );
                qrcodeText.setText( resources.getString( R.string.QRcode ) );

                //menu.findItem( R.id.all_item ).setTitle( resources.getString( R.string.home ));
                //menu.findItem( R.id.all_item ).setTitle( "lALA");
               // Toast.makeText( MainActivity.this,resources.getString( R.string.home ),Toast.LENGTH_SHORT ).show();
            }


        }else if(language!=null && language.equals( ("Bengal") )){
           // Toast.makeText( getApplicationContext(),"Yo",Toast.LENGTH_LONG ).show();
            try {
               // Toast.makeText( MainActivity.this,language,Toast.LENGTH_SHORT ).show();
                context=LocaleHelper.setLocale( MainActivity.this,"bn" );
                resources=context.getResources();
                //Menu home= findViewById( R.id.all_item );
                losstText.setText( resources.getString( R.string.lostItem ) );
                foundText.setText( resources.getString( R.string.foundItem ) );
                //foundText.setText( resources.getString( R.string.foundItem ) );
                addLostText.setText( resources.getString( R.string.addLostItem) );
                addFoundText.setText( resources.getString( R.string.addFoundItem) );
                qrcodeText.setText( resources.getString( R.string.QRcode ) );

                //menu.findItem( R.id.all_item ).setTitle( resources.getString( R.string.home ));
               // Toast.makeText( MainActivity.this,resources.getString( R.string.home ),Toast.LENGTH_SHORT ).show();
            }catch (Exception e){
               // Toast.makeText( MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT ).show();
            }
        }



    }

   /* private void fetchData() {
        listData=new ArrayList<>();
        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference("feed");
        latestquery=nm.orderByChild( "timestamp" );
        latestquery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                        FeedItemDataRetrieve l=npsnapshot.getValue(FeedItemDataRetrieve.class);
                        //Toast.makeText(MainActivity.this,l.getCategory1(), Toast.LENGTH_LONG).show();
                        listData.add(l);
                    }

                    adapter=new DataAdapter(listData );
                    // Collections.reverse( Collections.singletonList( adapter ) );
                    Toast.makeText( MainActivity.this,""+adapter,Toast.LENGTH_LONG ).show();
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }*/

    public void onBackPressed(){
        if(drawerLayout.isDrawerOpen( GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {

            super.onBackPressed();
        }




    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        switch (menuItem.getItemId()){
            case R.id.all_item:
                feedItemAllDisplay();
                //Toast.makeText( this,"all",Toast.LENGTH_LONG ).show();

                break;

            case R.id.login:
                Intent intent=new Intent(this,Login.class );
                startActivity( intent );
                finish();
                break;
            case R.id.signup:
                Intent intent1=new Intent(this,SignUp.class );
                startActivity( intent1 );
                finish();

                break;

            case R.id.search_item:
                Intent searchIntent = new Intent(MainActivity.this,Search.class);
                startActivity( searchIntent );
                break;

            case R.id.profile:
                Intent intentProfile=new Intent( MainActivity.this,Profile.class );
                startActivity( intentProfile );
                break;

            case R.id.termsMenu:
               Intent intentTerms=new Intent( MainActivity.this,TermsandConditionClass.class );
               startActivity( intentTerms );

                break;
            case R.id.contact:
                Intent intentContact=new Intent( MainActivity.this,contactus.class );
                startActivity( intentContact );
                break;
            case R.id.language:
                //Intent intentLang=new Intent( MainActivity.this,Language.class );
                //startActivity( intentLang);
                lang="Bengal";
                //invalidateOptionsMenu();
                changeLanguage();
                break;
        }

        drawerLayout.closeDrawer( GravityCompat.START );
        return true;
    }


    protected void onStart() {
        /**
         * Checking if the user is logged in or not
         */
        super.onStart();

        FirebaseUser users = mAuth.getCurrentUser();
       // String user= getIntent().getStringExtra( "user" );
       // user1=mAuth.getCurrentUser();
        if (users != null) {
            // User is signed in
            String name = users.getDisplayName();
            String s =users.getPhoneNumber();
            //Toast.makeText( this,"Phone"+s,Toast.LENGTH_LONG ).show();

        } else {
            // No user is signed in

        }

    }

    protected void UserCheckLoggedIn(){
        super.onStart();
        name=findViewById( R.id.name );
        phoneuser=findViewById( R.id.phoneuser );

        FirebaseUser users = FirebaseAuth.getInstance().getCurrentUser();

        if (users != null) {
            // User is signed in
            String name_user = users.getDisplayName();
            String s =users.getPhoneNumber();
           // Toast.makeText( this,name_user+" "+s,Toast.LENGTH_LONG ).show();
            name.setText( name_user );
            phoneuser.setText( s );
        }

    }

    public void foundItemFeed(){

        listData=new ArrayList<>();
        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference("foundItem");
        latestquery=nm.orderByChild( "timestamp" );
        latestquery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                        ItemDataREtreive l=npsnapshot.getValue(ItemDataREtreive.class);
                        listData.add(l);
                    }
                    adapter=new DataAdapter(listData );
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
    public void lostItemFeed(){

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
                    adapter=new DataAdapter(listData );
                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void feedItemAllDisplay(){

        listDataFeed=new ArrayList<>();
        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference("feed");
        latestquery=nm.orderByChild( "timestamp" );

        latestquery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                           keY=npsnapshot.getKey();
                        FeedItemDataRetrieve l=npsnapshot.getValue(FeedItemDataRetrieve.class);
                        //Toast.makeText(MainActivity.this,l.getCategory1(), Toast.LENGTH_LONG).show();
                        listDataFeed.add(l);
                    }

                    adapterFeed=new FeedDataAdapter( getApplicationContext(),listDataFeed );
                   // Collections.reverse( Collections.singletonList( adapter ) );
                   // Toast.makeText( MainActivity.this,""+adapterFeed,Toast.LENGTH_LONG ).show();
                    recyclerView.setAdapter(adapterFeed);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //Toast.makeText( MainActivity.this,"databse failed "+databaseError ,Toast.LENGTH_LONG ).show();
            }
        });

    }

    public boolean onMenuOpened(int featureid,Menu menu){

          if (shouldChangeMenuLabels){
             // menu.clear();
             // MenuInflater inflater = getMenuInflater();
              menu.clear();
              MenuInflater inflater = getMenuInflater(); // -->onCreateMenu (Menu)
              inflater.inflate(R.menu.locale_menu, menu);
               //menu.findItem( R.id.all_item ).setTitle( "lalal" );
              shouldChangeMenuLabels=false;
          }


        return super.onMenuOpened( featureid,menu );
    }
    public boolean onCreateOptionsMenu(Menu menu){


        getMenuInflater().inflate( R.menu.search_menu,menu );
        SearchManager searchManager = (SearchManager) getSystemService( Context.SEARCH_SERVICE);


        MenuItem item=menu.findItem( R.id.action_search );
        SearchView searchView =(SearchView) item.getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener( new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                //List l = null;


                //processSearch(query);
                //Toast.makeText( Maithis, adapter.getFilter().filter( query ),Toast.LENGTH_LONG ).show();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //adapter.getFilter().filter( newText );
                //recyclerView.setAdapter( adapter );
                //processSearch(newText);
                /*if (newText.equals( null )){
                    feedItemAllDisplay();
                }*/
                try {
                    adapter.getFilter().filter( newText);
                    recyclerView.setAdapter( adapter );
                }catch (Exception e){

                }finally {
                    adapterFeed.getFilter().filter( newText );
                    recyclerView.setAdapter( adapterFeed );
                }
                return false;
            }
        } );

        //searchView.onActionViewCollapsed();
       // MenuItem actionMenuItem = menu.findItem(R.id.myActionItem);

        return   super.onCreateOptionsMenu( menu );
    }


   /* private void processSearch(String s){
        final DatabaseReference nm= FirebaseDatabase.getInstance().getReference("lostItem");
        Query query=nm.child( "category1" ).startAt( s ).endAt( s+"\uf8ff" ).orderByChild( "timestamp" );
        //FirebaseRecyclerOptions<ItemDataREtreive> options = new FirebaseRecyclerOptions.Builder<ItemDataREtreive>()
          //      .setQuery( FirebaseDatabase.getInstance().getReference("lostItem").orderByChild( "category1" ).startAt(s).endAt( s+"\uf8ff" ),ItemDataREtreive.class )
            //    .build();
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
                        ItemDataREtreive l=npsnapshot.getValue(ItemDataREtreive.class);
                        //Toast.makeText(MainActivity.this,l.getCategory1(), Toast.LENGTH_LONG).show();
                        listData.clear();
                        listData.add(l);
                    }

                    adapter=new DataAdapter(listData );
                    // Collections.reverse( Collections.singletonList( adapter ) );

                    recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }*/
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_search) {
            return true;
        }


        return onOptionsItemSelected( item );
    }

    public void onItemClick(int position) {
        // The onClick implementation of the RecyclerView item click
//ur intent code here
        Intent intent =new Intent( MainActivity.this,TermsandConditionClass.class );
        startActivity( intent );
    }

}
