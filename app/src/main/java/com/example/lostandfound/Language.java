package com.example.lostandfound;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.appcompat.app.AppCompatActivity;

public class Language extends AppCompatActivity {
    boolean lang_selected;
    Context context;
    Resources resources;
    public static String language="English";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_language );

        Button submit=findViewById( R.id.submitBtn );
       /*try {
           submit.setOnClickListener( new View.OnClickListener() {

               @Override
               public void onClick(View view) {
                   Toast.makeText( Language.this,"Hi",Toast.LENGTH_SHORT ).show();
                    Intent intent = new Intent( Language.this,MainActivity.class );
                    intent.putExtra( "lang",language );
                    startActivity( intent );
                    finish();
               }
           } );
       }catch (Exception e){
           Toast.makeText( Language.this,e.getMessage(),Toast.LENGTH_LONG).show();
       }finally {
           submit.setOnClickListener( new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   Toast.makeText( Language.this,"Hi",Toast.LENGTH_SHORT ).show();
                   Intent intent = new Intent( Language.this,MainActivity.class );
                   intent.putExtra( "lang",language );
                   startActivity( intent );
                   finish();
               }
           } );
       }*/


    }

    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.engLang:
                if (checked)

                   // context= LocaleHelper.setLocale( Language.this,"en" );
                   // resources = context.getResources();
                  // language="English";

                  // context=LocaleHelper.setLocale( MainActivity.this,"en" );
                    break;
            case R.id.bangLang:
                if (checked)
                   // language="Bengal";

                break;
        }
    }

}
