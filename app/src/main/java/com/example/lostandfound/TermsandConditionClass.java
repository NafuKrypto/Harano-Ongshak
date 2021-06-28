package com.example.lostandfound;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class TermsandConditionClass extends AppCompatActivity {
TextView t1,t2,t3,t4,t5,t6,t7,t8,t9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_termsand_condition_class );
        t1=findViewById( R.id.terms1 );
        t2=findViewById( R.id.terms2 );
        t3=findViewById( R.id.terms3 );
        t4=findViewById( R.id.terms4);
        t5=findViewById( R.id.terms5 );
        t6=findViewById( R.id.terms6 );
        t7=findViewById( R.id.terms7 );
        t8=findViewById( R.id.terms8 );
        t9=findViewById( R.id.terms9 );
        Button button =findViewById( R.id.homeBtn );
        SharedPreferences sharedPreferences2 = getApplicationContext().getSharedPreferences( "MyLang", 0 );
        String languageFromSharedPRef = sharedPreferences2.getString( "userLanguageChoice", null );
        languageChangeProg( languageFromSharedPRef );
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( TermsandConditionClass.this,MainActivity.class );
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
                context=LocaleHelper.setLocale( TermsandConditionClass.this,"en" );
                resources=context.getResources();
                 t1.setText( resources.getString( R.string.termsandcond ) );
                 t2.setText( resources.getString( R.string.terms1stpara) );
                 t3.setText( resources.getString( R.string.terms2ndpara ) );
                 t4.setText( resources.getString( R.string.terms3rdpara) );
                t5.setText( resources.getString( R.string.terms4thpara) );
                t6.setText( resources.getString( R.string.terms5thpara ) );
                t7.setText( resources.getString( R.string.terms6thpara) );
                t8.setText( resources.getString( R.string.terms7thpara) );
                t9.setText( resources.getString( R.string.terms8thpara ) );
                //Menu home= findViewById( R.id.all_item );
                //menu.findItem( R.id.all_item ).setTitle( resources.getString( R.string.home ));
                //menu.findItem( R.id.all_item ).setTitle( "lALA");
                //Toast.makeText( MainActivity.this,resources.getString( R.string.home ),Toast.LENGTH_SHORT ).show();

            }catch (Exception e){
                // Toast.makeText( MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT ).show();

            }finally {
                context=LocaleHelper.setLocale( TermsandConditionClass.this,"en" );
                resources=context.getResources();
                // Menu home= findViewById( R.id.all_item );
                t1.setText( resources.getString( R.string.termsandcond ) );
                t2.setText( resources.getString( R.string.terms1stpara) );
                t3.setText( resources.getString( R.string.terms2ndpara ) );
                t4.setText( resources.getString( R.string.terms3rdpara) );
                t5.setText( resources.getString( R.string.terms4thpara) );
                t6.setText( resources.getString( R.string.terms5thpara ) );
                t7.setText( resources.getString( R.string.terms6thpara) );
                t8.setText( resources.getString( R.string.terms7thpara) );
                t9.setText( resources.getString( R.string.terms8thpara ) );
                //menu.findItem( R.id.all_item ).setTitle( resources.getString( R.string.home ));
                //menu.findItem( R.id.all_item ).setTitle( "lALA");
                // Toast.makeText( MainActivity.this,resources.getString( R.string.home ),Toast.LENGTH_SHORT ).show();
            }


        }else if(language!=null && language.equals( ("Bengal") )){

            try {
                //Toast.makeText( MainActivity.this,language,Toast.LENGTH_SHORT ).show();
                context=LocaleHelper.setLocale( TermsandConditionClass.this,"bn" );
                resources=context.getResources();
                //Menu home= findViewById( R.id.all_item );
                //Toast.makeText( getApplicationContext(),"Yo and Yo",Toast.LENGTH_LONG ).show();

                t1.setText( resources.getString( R.string.termsandcond ) );
                t2.setText( resources.getString( R.string.terms1stpara) );
                t3.setText( resources.getString( R.string.terms2ndpara ) );
                t4.setText( resources.getString( R.string.terms3rdpara) );
                t5.setText( resources.getString( R.string.terms4thpara) );
                t6.setText( resources.getString( R.string.terms5thpara ) );
                t7.setText( resources.getString( R.string.terms6thpara) );
                t8.setText( resources.getString( R.string.terms7thpara) );
                t9.setText( resources.getString( R.string.terms8thpara ) );
                //menu.findItem( R.id.all_item ).setTitle( resources.getString( R.string.home ));
                //Toast.makeText( TermsandConditionClass.this,resources.getString( R.string.whatisit ),Toast.LENGTH_SHORT ).show();
            }catch (Exception e){
                //Toast.makeText( MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT ).show();
            }
        }

    }

}
