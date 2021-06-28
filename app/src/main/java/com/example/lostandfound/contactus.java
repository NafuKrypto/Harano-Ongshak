package com.example.lostandfound;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class contactus extends AppCompatActivity {
    TextView t1,t2,t3,t4,t5,t6,t7,t8,t9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_contactus );
        t1=findViewById( R.id.contact1);
        t2=findViewById( R.id.contact2);
        t3=findViewById( R.id.contact3);

        SharedPreferences sharedPreferences2 = getApplicationContext().getSharedPreferences( "MyLang", 0 );
        String languageFromSharedPRef = sharedPreferences2.getString( "userLanguageChoice", null );
        languageChangeProg( languageFromSharedPRef );

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
                context=LocaleHelper.setLocale( contactus.this,"en" );
                resources=context.getResources();
                t1.setText( resources.getString( R.string.getintouch ) );
                t2.setText( resources.getString( R.string.contactAdd) );
                t3.setText( resources.getString( R.string.contactPhone ) );

                //Menu home= findViewById( R.id.all_item );
                //menu.findItem( R.id.all_item ).setTitle( resources.getString( R.string.home ));
                //menu.findItem( R.id.all_item ).setTitle( "lALA");
                //Toast.makeText( MainActivity.this,resources.getString( R.string.home ),Toast.LENGTH_SHORT ).show();

            }catch (Exception e){
                // Toast.makeText( MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT ).show();

            }finally {
                context=LocaleHelper.setLocale( contactus.this,"en" );
                resources=context.getResources();
                // Menu home= findViewById( R.id.all_item );
                t1.setText( resources.getString( R.string.getintouch ) );
                t2.setText( resources.getString( R.string.contactAdd) );
                t3.setText( resources.getString( R.string.contactPhone ) );
                //menu.findItem( R.id.all_item ).setTitle( resources.getString( R.string.home ));
                //menu.findItem( R.id.all_item ).setTitle( "lALA");
                // Toast.makeText( MainActivity.this,resources.getString( R.string.home ),Toast.LENGTH_SHORT ).show();
            }


        }else if(language!=null && language.equals( ("Bengal") )){

            try {
                //Toast.makeText( MainActivity.this,language,Toast.LENGTH_SHORT ).show();
                context=LocaleHelper.setLocale( contactus.this,"bn" );
                resources=context.getResources();
                //Menu home= findViewById( R.id.all_item );
                //Toast.makeText( getApplicationContext(),"Yo and Yo",Toast.LENGTH_LONG ).show();

                t1.setText( resources.getString( R.string.getintouch ) );
                t2.setText( resources.getString( R.string.contactAdd) );
                t3.setText( resources.getString( R.string.contactPhone ) );
                //menu.findItem( R.id.all_item ).setTitle( resources.getString( R.string.home ));
                //Toast.makeText( TermsandConditionClass.this,resources.getString( R.string.whatisit ),Toast.LENGTH_SHORT ).show();
            }catch (Exception e){
                //Toast.makeText( MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT ).show();
            }
        }

    }
}
