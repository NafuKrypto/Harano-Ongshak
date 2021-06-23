package com.example.lostandfound;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

public class AddItem extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
private TextView warning,tittle,selectCategorytxt;
LinearLayout id;
CheckBox men,women,kid,old;
EditText what,address,time,desc;
String what_str,address_str,time_str,desc_str, user_phone,timestamp,primaryKey;
String checkboxitem="None",Category;
final Calendar myCalendar = Calendar.getInstance();
DatePickerDialog.OnDateSetListener date;
FirebaseAuth mAuth;
SharedPreferences sharedPreferences2 ;

Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_add_item );


       // onStart();
        tittle=findViewById( R.id.tittle );
        warning=findViewById(R.id.warning);
        id=findViewById( R.id.Checkbox );
        what=findViewById( R.id.what );
        address=findViewById( R.id.address );
        time=findViewById( R.id.time );
        desc=findViewById( R.id.desc );
        btn=findViewById( R.id.submitbtn );
        selectCategorytxt=findViewById( R.id.selectone );
        Spinner spinner = (Spinner) findViewById(R.id.category);
        /**
         * sharedpreference for language
         */
        sharedPreferences2 = getApplicationContext().getSharedPreferences("MyLang", 0);
        String languageFromSharedPRef = sharedPreferences2.getString( "userLanguageChoice", null );

        Toast.makeText( getApplicationContext(), languageFromSharedPRef,Toast.LENGTH_LONG ).show();
        languageChangeProg( languageFromSharedPRef );
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.category_list, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        /**
         * Datepicker
         */
             date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        time.setOnClickListener( new  View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(AddItem.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        } );


     btn.setOnClickListener( new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             what_str= what.getText().toString().trim();
             address_str=address.getText().toString().trim();
             time_str=time.getText().toString().trim();
             desc_str=desc.getText().toString().trim();
             String itemtype=getIntent().getStringExtra( "item_type" );
             Toast.makeText( AddItem.this,"Lost : "+(itemtype=="Lost"),Toast.LENGTH_SHORT ).show();
        /*Toast.makeText( AddItem.this,"what :"+what_str,Toast.LENGTH_SHORT ).show();
        Toast.makeText( AddItem.this,"addr :"+address_str,Toast.LENGTH_SHORT ).show();
        Toast.makeText( AddItem.this,"time :"+time_str,Toast.LENGTH_SHORT ).show();
        Toast.makeText( AddItem.this,"desc:"+desc_str,Toast.LENGTH_SHORT ).show();
        Toast.makeText( AddItem.this,"category1 :"+Category,Toast.LENGTH_SHORT ).show();
        Toast.makeText( AddItem.this,"catgory2 :"+checkboxitem,Toast.LENGTH_SHORT ).show();*/

             DatabaseReference mDatabase;
             mDatabase= FirebaseDatabase.getInstance().getReference();
             //FirebaseUser user=mAuth.getCurrentUser();
             user_phone = getIntent().getStringExtra( "phone" );

             timestamp=timestamp();

             primaryKey=user_phone+" "+timestamp;
             //Toast.makeText( AddItem.this,"user :"+user,Toast.LENGTH_SHORT ).show();
             //Toast.makeText( AddItem.this,"primary key :"+primaryKey,Toast.LENGTH_SHORT ).show();
             //primaryKey=primaryKey.replaceAll( "\\+","");
            // primaryKey=primaryKey.replaceAll( ".","");
             //primaryKey=primaryKey.replaceAll( " ","");
             Toast.makeText( AddItem.this,"primary key :"+primaryKey,Toast.LENGTH_SHORT ).show();
             if(itemtype.equals("Lost")) {
/**
 * Entering Data into lostItem and into Feed child with status 0
 */
                 Toast.makeText( AddItem.this,"Lost :"+itemtype,Toast.LENGTH_SHORT ).show();
                 DatabaseAddItem dataAdd = new DatabaseAddItem( user_phone,primaryKey,
                         Category,checkboxitem,what_str,address_str,time_str,timestamp,desc_str,"0");//status 0 if admin takes no action
                                                                                                           //status 1 if admin takes action
                 dataAdd.writeNewUser( user_phone,primaryKey,
                         Category,checkboxitem,what_str,address_str,time_str,timestamp,desc_str ,"0");
                 //For Feed in MainActivity
                 FeedDatabaseClass feedDatabaseClass = new FeedDatabaseClass( user_phone,primaryKey,
                         Category,checkboxitem,what_str,address_str,time_str,timestamp,desc_str ,"0","lost" );
                 feedDatabaseClass.userFeed();



             }else if(itemtype.equals( "Found" )){
                 /**
                  * Entering Data into foundItem and into Feed child with status 0
                  */
                 Toast.makeText( AddItem.this,"Lost :"+itemtype,Toast.LENGTH_SHORT ).show();
                 DatabaseAddItem dataAdd = new DatabaseAddItem( user_phone,primaryKey,
                         Category,checkboxitem,what_str,address_str,time_str,timestamp,desc_str,"0");
                 dataAdd.writeNewUserFound( user_phone,primaryKey,
                         Category,checkboxitem,what_str,address_str,time_str,timestamp,desc_str ,"0");
                 //For Feed in MainActivity

                 FeedDatabaseClass feedDatabaseClass = new FeedDatabaseClass( user_phone,primaryKey,
                         Category,checkboxitem,what_str,address_str,time_str,timestamp,desc_str ,"0","found" );
                 feedDatabaseClass.userFeed();


             }
             Intent intent = new Intent( AddItem.this, MainActivity.class );
             intent.putExtra( "phone",user_phone );
             startActivity( intent );
             finish();
         }
     } );
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
                context=LocaleHelper.setLocale( AddItem.this,"en" );
                resources=context.getResources();
                tittle.setText( resources.getString( R.string.formTitle ) );
                what.setHint( resources.getString( R.string.whatisit ) );
                address.setHint( resources.getString( R.string.address ) );
                time.setHint( resources.getString( R.string.dateString ) );
                desc.setHint( resources.getString( R.string.descString ) );
                btn.setText( resources.getString( R.string.addSubmitBtn ) );
                selectCategorytxt.setText( resources.getString( R.string.selectacat ) );
                //Menu home= findViewById( R.id.all_item );
                //menu.findItem( R.id.all_item ).setTitle( resources.getString( R.string.home ));
                //menu.findItem( R.id.all_item ).setTitle( "lALA");
                //Toast.makeText( MainActivity.this,resources.getString( R.string.home ),Toast.LENGTH_SHORT ).show();

            }catch (Exception e){
               // Toast.makeText( MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT ).show();

            }finally {
                context=LocaleHelper.setLocale( AddItem.this,"en" );
                resources=context.getResources();
                // Menu home= findViewById( R.id.all_item );
                tittle.setText( resources.getString( R.string.formTitle ) );
                what.setHint( resources.getString( R.string.whatisit ) );
                address.setHint( resources.getString( R.string.address ) );
                time.setHint( resources.getString( R.string.dateString ) );
                desc.setHint( resources.getString( R.string.descString ) );
                btn.setText( resources.getString( R.string.addSubmitBtn ) );
                selectCategorytxt.setText( resources.getString( R.string.selectacat ) );
                //menu.findItem( R.id.all_item ).setTitle( resources.getString( R.string.home ));
                //menu.findItem( R.id.all_item ).setTitle( "lALA");
                // Toast.makeText( MainActivity.this,resources.getString( R.string.home ),Toast.LENGTH_SHORT ).show();
            }


        }else if(language!=null && language.equals( ("Bengal") )){

            try {
                //Toast.makeText( MainActivity.this,language,Toast.LENGTH_SHORT ).show();
                context=LocaleHelper.setLocale( AddItem.this,"bn" );
                resources=context.getResources();
                //Menu home= findViewById( R.id.all_item );
                //Toast.makeText( getApplicationContext(),"Yo and Yo",Toast.LENGTH_LONG ).show();

                tittle.setText( resources.getString( R.string.formTitle ) );
                what.setHint( resources.getString( R.string.whatisit ) );
                address.setHint( resources.getString( R.string.address ) );
                time.setHint( resources.getString( R.string.dateString ) );
                desc.setHint( resources.getString( R.string.descString ) );
                btn.setText( resources.getString( R.string.addSubmitBtn ) );
                selectCategorytxt.setText( resources.getString( R.string.selectacat ) );
                //menu.findItem( R.id.all_item ).setTitle( resources.getString( R.string.home ));
                Toast.makeText( AddItem.this,resources.getString( R.string.whatisit ),Toast.LENGTH_SHORT ).show();
            }catch (Exception e){
                //Toast.makeText( MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT ).show();
            }
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        Category= adapterView.getItemAtPosition(i).toString();

        if (i==1){
            id.setVisibility(View.VISIBLE);
        }
        if(i!=1){
            id.setVisibility(View.GONE);
        }
        Toast.makeText(AddItem.this,Category,Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        warning.setVisibility(View.VISIBLE);
        warning.setText("You must select one category");
    }

    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.men:
                if (checked){
                    checkboxitem="men";
                }

            else checkboxitem="None";
                //
                break;
            case R.id.women:
                if (checked){
                    checkboxitem="women";
                }
                //
            else checkboxitem="None";
                //
                break;
            case R.id.kid:
                if (checked){
                    checkboxitem="kid";
                }

            else checkboxitem="None";

                break;
            case R.id.old_men_or_women:
                if (checked){
                    checkboxitem="old";
                }

            else checkboxitem="None";

                break;
            // TODO: Checked Items of People
        }

    }

    private void updateLabel() {
        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        time.setText(sdf.format(myCalendar.getTime()));
    }





    public String timestamp(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss "); //addz for gmt version
        Date date=new Date();
        String currentDateandTime = dateFormat.format(date);

        try {
            date=dateFormat.parse( time_str );
        } catch (ParseException e) {
            e.printStackTrace();
        }
        System.out.println("Today is " + date.getTime());
        Toast.makeText( AddItem.this,"timestamp milli:"+ date.getTime(),Toast.LENGTH_LONG).show();
        return String.valueOf( date.getTime() );
    }
}
