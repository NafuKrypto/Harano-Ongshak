package com.example.lostandfound;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class QRCodeForm extends AppCompatActivity {
SharedPreferences sharedPreferencesQr;
Context context ;
Resources resources;
Button submitButton;
int selectItem=0;
TextView textView;
String s="None";
String nameS,phonenumS,addressS,emailS,area,selectCat,modelS,email2S,address2S,ownername2S,phonenum2S,name3S;
String email3S,office3S,phone3S,address3S,networkName,NetworkType,networkPass,Url;
//RadioButton camera,phone,laptop,studentId,officeId,atmCard,nid,moneyBag,passport,bag,chequeBook,houseDoor;
//RadioButton keyRing,book,dairy,documentFile,facebook,youtube,wifi,other;
int selectedId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_qrcode_form );
        RadioGroup radioGroup=findViewById( R.id.radioGroup );
        submitButton=findViewById( R.id.btn );
        textView=findViewById( R.id.msg );
       /* camera=findViewById( R.id.camera );
        phone=findViewById( R.id.phone);
        laptop=findViewById( R.id.laptop );
        studentId=findViewById( R.id.student_id );
        officeId=findViewById( R.id.officeId );
        atmCard =findViewById( R.id.atm_card );
        nid=findViewById( R.id.nid );
        moneyBag=findViewById( R.id.money_bag );
        passport=findViewById( R.id.passport);
        bag=findViewById( R.id.bag );
        chequeBook=findViewById( R.id.cheque_book );
        houseDoor=findViewById( R.id.house_door );
        keyRing=findViewById( R.id.key_ring);
        book=findViewById( R.id.book );
        dairy=findViewById( R.id.dairy );
        documentFile=findViewById( R.id.document_file );
        facebook=findViewById( R.id.facebook );
        youtube=findViewById( R.id.youtube);
        wifi=findViewById( R.id.wifi);
        other=findViewById( R.id.othersId );*/

        //selectedId=radioGroup.getCheckedRadioButtonId();
        //Create an ArrayAdapter using the string array and a default spinner layout
        sharedPreferencesQr=getApplicationContext().getSharedPreferences( "MyLang",0 );
        String languageFromSharedPRef = sharedPreferencesQr.getString( "userLanguageChoice", null );

        if (languageFromSharedPRef!=null && languageFromSharedPRef.equalsIgnoreCase( "English"  ) ){
            context=LocaleHelper.setLocale( QRCodeForm.this,"en" );
            resources=context.getResources();
            // stringArr[]=getResources().getStringArray( R.array.Select_area );
        }


     submitButton.setOnClickListener( new View.OnClickListener() {
        @Override
        public void onClick(View view) {
              if (area==null){
                  textView.setText( "You must select the area" );
                  textView.setTextColor( Color.RED );
                  textView.setVisibility( View.VISIBLE );
              }else if(selectItem==0){
                  textView.setText( "You must select the category" );
                  textView.setTextColor( Color.RED );
                  textView.setVisibility( View.VISIBLE );
              }
            if (area !=null && selectItem!=0){
              switch (selectItem) {
                  case 1:
                      createAlertDialougeFuncRealPhone();

                      //qrCodeDatabaseClass database=new qrCodeDatabaseClass( s,s,s,s,area,selectCat,modelS,email2S,address2S,ownername2S,phonenum2S,
                        //      s,s,s,s,s,s,s,s,s);
                     // database.writeToDatabase();
                      break;
                  case 2:
                      createAlertDialougeFuncRealPhone();

                      break;

                  case 3:
                      createAlertDialougeFuncRealPhone();

                      break;
                  case 4:
                      createAlertDialougeFuncPhone();

                      break;
                  case 5:
                      createAlertDialougeFuncPhone();

                      break;
                  case 6:
                      createAlertDialougeFuncStudId();
                      qrCodeDatabaseClass database6=new qrCodeDatabaseClass(s,s,s,s,area,selectCat,
                              s,s,s,s,s,name3S,email3S,office3S,phone3S,address3S,s,s,s,s);
                      database6.writeToDatabase();

                      break;
                  case 7:
                      createAlertDialougeFuncStudId();

                      break;
                  case 8:
                      createAlertDialougeFuncPhone();

                      break;
                  case 9:
                      createAlertDialougeFuncPhone();

                      break;
                  case 10:
                      createAlertDialougeFuncPhone();
                      break;
                  case 11:
                      createAlertDialougeFuncPhone();

                      break;
                  case 12:
                      createAlertDialougeFuncPhone();

                      break;
                  case 13:
                      createAlertDialougeFuncPhone();

                      break;
                  case 14:
                      createAlertDialougeFuncPhone();

                      break;
                  case 15:
                      createAlertDialougeFuncPhone();

                      break;
                  case 16:
                      createAlertDialougeFuncPhone();

                      break;
                  case 17:
                      createAlertDialougeFuncUrl();

                      break;
                  case 18:
                      createAlertDialougeFuncUrl();

                      break;
                  case 19:
                      createAlertDialougeFuncWifi();

                      break;
                  case 20:
                      createAlertDialougeFuncPhone();

                      break;

              }



              }


        }
    } );


    }

    private void createAlertDialougeFuncPhone() {
        /**
         * for Atm and bla bla bla
         */
        Toast.makeText( getApplicationContext(),"phone alertDialogue",Toast.LENGTH_LONG ).show();
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(QRCodeForm.this);
        alertDialog.setTitle(R.string.fillUpAllTheInfo);

        final EditText name = new EditText(QRCodeForm.this);
         final EditText phoneNumber = new EditText(QRCodeForm.this);
         final EditText address = new EditText(QRCodeForm.this);
          final EditText email = new EditText(QRCodeForm.this);
        name.setHint( R.string.formName );
        name.setGravity( Gravity.CENTER );
        name.setBackgroundResource( R.drawable.editbox);

        phoneNumber.setHint( R.string.phone);
        phoneNumber.setGravity( Gravity.CENTER );
        phoneNumber.setBackgroundResource( R.drawable.editbox);

        address.setHint( R.string.address2 );
        address.setGravity( Gravity.CENTER );
        address.setBackgroundResource( R.drawable.editbox);

        email.setHint( R.string.email );
        email.setGravity( Gravity.CENTER );
        email.setBackgroundResource( R.drawable.editbox);
        /*LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);*/
        LinearLayout lp=new LinearLayout( getApplicationContext() );
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins( 20,20,20,20);
        lp.setOrientation( LinearLayout.VERTICAL );
        lp.addView( name,layoutParams );
        lp.addView( phoneNumber,layoutParams );
        lp.addView( address ,layoutParams);
        lp.addView( email ,layoutParams);
        alertDialog.setView( lp );

        alertDialog.setPositiveButton( "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                nameS=name.getText().toString().trim();
                phonenumS=phoneNumber.getText().toString().trim();
                addressS=address.getText().toString().trim();
                emailS=email.getText().toString().trim();
                qrCodeDatabaseClass database4=new qrCodeDatabaseClass( nameS,phonenumS,addressS,emailS,
                        area,selectCat,s,s,s,s,s,s,s,s,s,s,s,s,s,s);
                database4.writeToDatabase();
               // Toast.makeText( getApplicationContext(),nameS+" "+phonenumS+" "+addressS+" "+emailS,Toast.LENGTH_LONG  ).show();

            }
        } );
        alertDialog.setNegativeButton( "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        } );

        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(false);
        alert.show();

    }

    private void createAlertDialougeFuncRealPhone() {
        /**
         * for phone,camera and laptop
         */
        Toast.makeText( getApplicationContext(),"phone alertDialogue",Toast.LENGTH_LONG ).show();
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(QRCodeForm.this);
        alertDialog.setTitle(R.string.fillUpAllTheInfo);

        final EditText model = new EditText(QRCodeForm.this);
        final EditText name = new EditText(QRCodeForm.this);
        final EditText phoneNumber = new EditText(QRCodeForm.this);
        final EditText address = new EditText(QRCodeForm.this);
        final EditText email = new EditText(QRCodeForm.this);
        model.setHint( R.string.model );
        model.setGravity( Gravity.CENTER );
        model.setBackgroundResource( R.drawable.editbox);

        name.setHint( R.string.formName );
        name.setGravity( Gravity.CENTER );
        name.setBackgroundResource( R.drawable.editbox);

        phoneNumber.setHint( R.string.phone);
        phoneNumber.setGravity( Gravity.CENTER );
        phoneNumber.setBackgroundResource( R.drawable.editbox);

        address.setHint( R.string.address2 );
        address.setGravity( Gravity.CENTER );
        address.setBackgroundResource( R.drawable.editbox);

        email.setHint( R.string.email );
        email.setGravity( Gravity.CENTER );
        email.setBackgroundResource( R.drawable.editbox);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins( 20,20,20,20);

        LinearLayout lp=new LinearLayout( getApplicationContext() );
        lp.setOrientation( LinearLayout.VERTICAL );
        lp.addView( model,layoutParams );
        lp.addView( name ,layoutParams);
        lp.addView( phoneNumber,layoutParams );
        lp.addView( address,layoutParams );
        lp.addView( email ,layoutParams);
        alertDialog.setView( lp );

        alertDialog.setPositiveButton( "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                modelS=model.getText().toString().trim();
                ownername2S=name.getText().toString().trim();
                phonenum2S=phoneNumber.getText().toString().trim();
                address2S=address.getText().toString().trim();
                email2S=email.getText().toString().trim();
                qrCodeDatabaseClass database=new qrCodeDatabaseClass( s,s,s,s,area,selectCat,modelS,email2S,address2S,ownername2S,phonenum2S,
                        s,s,s,s,s,s,s,s,s);
                database.writeToDatabase();
                // Toast.makeText( getApplicationContext(),nameS+" "+phonenumS+" "+addressS+" "+emailS,Toast.LENGTH_LONG  ).show();

            }
        } );
        alertDialog.setNegativeButton( "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        } );

        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(false);
        alert.show();

    }

    private void createAlertDialougeFuncStudId() {
        /**
         * student id ,office id
         */
        Toast.makeText( getApplicationContext(),"phone alertDialogue",Toast.LENGTH_LONG ).show();
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(QRCodeForm.this);
        alertDialog.setTitle(R.string.fillUpAllTheInfo);

        final EditText office= new EditText(QRCodeForm.this);
        final EditText name = new EditText(QRCodeForm.this);
        final EditText phoneNumber = new EditText(QRCodeForm.this);
        final EditText address = new EditText(QRCodeForm.this);
        final EditText email = new EditText(QRCodeForm.this);
        office.setHint( R.string.office );
        office.setGravity( Gravity.CENTER );
        office.setBackgroundResource( R.drawable.editbox);

        name.setHint( R.string.formName );
        name.setGravity( Gravity.CENTER );
        name.setBackgroundResource( R.drawable.editbox);

        phoneNumber.setHint( R.string.phone);
        phoneNumber.setGravity( Gravity.CENTER );
        phoneNumber.setBackgroundResource( R.drawable.editbox);

        address.setHint( R.string.address2 );
        address.setGravity( Gravity.CENTER );
        address.setBackgroundResource( R.drawable.editbox);

        email.setHint( R.string.email );
        email.setGravity( Gravity.CENTER );
        email.setBackgroundResource( R.drawable.editbox);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins( 20,20,20,20);

        LinearLayout lp=new LinearLayout( getApplicationContext() );
        lp.setOrientation( LinearLayout.VERTICAL );
        lp.addView( office,layoutParams);
        lp.addView( name ,layoutParams);
        lp.addView( phoneNumber,layoutParams );
        lp.addView( address ,layoutParams);
        lp.addView( email,layoutParams );
        alertDialog.setView( lp );

        alertDialog.setPositiveButton( "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                office3S=office.getText().toString().trim();
                name3S=name.getText().toString().trim();
                phone3S=phoneNumber.getText().toString().trim();
                address3S=address.getText().toString().trim();
                email3S=email.getText().toString().trim();

                qrCodeDatabaseClass database7=new qrCodeDatabaseClass(s,s,s,s,area,selectCat,
                        s,s,s,s,s,name3S,email3S,office3S,phone3S,address3S,s,s,s,s);
                database7.writeToDatabase();
                // Toast.makeText( getApplicationContext(),nameS+" "+phonenumS+" "+addressS+" "+emailS,Toast.LENGTH_LONG  ).show();

            }
        } );
        alertDialog.setNegativeButton( "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        } );

        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(false);
        alert.show();

    }
    private void createAlertDialougeFuncWifi() {
        /**
         * wifi
         */
        Toast.makeText( getApplicationContext(),"phone alertDialogue",Toast.LENGTH_LONG ).show();
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(QRCodeForm.this);
        alertDialog.setTitle(R.string.fillUpAllTheInfo);

        final EditText network_name= new EditText(QRCodeForm.this);
        final EditText password = new EditText(QRCodeForm.this);
        final EditText networkType = new EditText(QRCodeForm.this);

        network_name.setHint( R.string.networkName );
        network_name.setGravity( Gravity.CENTER );
        network_name.setBackgroundResource( R.drawable.editbox);

        networkType.setHint( R.string.networkType );
        networkType.setGravity( Gravity.CENTER );
        networkType.setBackgroundResource( R.drawable.editbox);

        password.setHint( R.string.passwordNet);
        password.setGravity( Gravity.CENTER );
        password.setBackgroundResource( R.drawable.editbox);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins( 20,20,20,20);

        LinearLayout lp=new LinearLayout( getApplicationContext() );
        lp.setOrientation( LinearLayout.VERTICAL );
        lp.addView( network_name,layoutParams);
        lp.addView( networkType ,layoutParams);
        lp.addView( password,layoutParams );

        alertDialog.setView( lp );

        alertDialog.setPositiveButton( "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
               networkName=network_name.getText().toString().trim();
                NetworkType=networkType.getText().toString().trim();
                networkPass=password.getText().toString().trim();
                qrCodeDatabaseClass database19=new qrCodeDatabaseClass(s,s,s,s,area,selectCat,
                        s,s,s,s,s,s,s,s,s,s,networkName,NetworkType,networkPass, s );
                database19.writeToDatabase();
                // Toast.makeText( getApplicationContext(),nameS+" "+phonenumS+" "+addressS+" "+emailS,Toast.LENGTH_LONG  ).show();

            }
        } );
        alertDialog.setNegativeButton( "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        } );

        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(false);
        alert.show();

    }
    private void createAlertDialougeFuncUrl() {
        /**
         * facebook and url
         */
        Toast.makeText( getApplicationContext(),"phone alertDialogue",Toast.LENGTH_LONG ).show();
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(QRCodeForm.this);
        alertDialog.setTitle(R.string.fillUpAllTheInfo);

        final EditText url= new EditText(QRCodeForm.this);


        url.setHint( R.string.url );
        url.setGravity( Gravity.CENTER );
        url.setBackgroundResource( R.drawable.editbox);

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins( 20,20,20,20);

        LinearLayout lp=new LinearLayout( getApplicationContext() );
        lp.setOrientation( LinearLayout.VERTICAL );
        lp.addView( url,layoutParams);
        alertDialog.setView( lp );

        alertDialog.setPositiveButton( "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                Url=url.getText().toString().trim();

                qrCodeDatabaseClass database17=new qrCodeDatabaseClass(s,s,s,s,area,selectCat,
                        s,s,s,s,s,s,s,s,s,s,s,s,s, Url );
                database17.writeToDatabase();
                // Toast.makeText( getApplicationContext(),nameS+" "+phonenumS+" "+addressS+" "+emailS,Toast.LENGTH_LONG  ).show();

            }
        } );
        alertDialog.setNegativeButton( "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        } );

        AlertDialog alert = alertDialog.create();
        alert.setCanceledOnTouchOutside(false);
        alert.show();

    }
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?

        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.phone:
                if (checked){
                    selectItem=1;
                    selectCat="phone";
                    //createAlertDialougeFuncPhone();
                   // Toast.makeText( getApplicationContext(),"phone is checked",Toast.LENGTH_LONG ).show();


                }

                    break;
            case R.id.camera:
                if (checked){
                    selectItem=2;
                    selectCat="camera";
                }
                    // Ninjas rule
                    break;
            case R.id.laptop:
                if (checked){
                    selectItem=3;
                    selectCat="laptop";
                }
                    // Ninjas rule
                    break;
            case R.id.atm_card:
                if (checked){
                    selectItem=4;
                    selectCat="atmcard";
                }
                    // Ninjas rule
                    break;
            case R.id.key_ring:
                if (checked){
                    selectItem=5;
                    selectCat="keyring";
                }
                    // Ninjas rule
                    break;
            case R.id.student_id:
                if (checked){
                    selectItem=6;
                    selectCat="studentid";
                }
                    // Ninjas rule
                    break;
            case R.id.officeId:
                if (checked){
                    selectItem=7;
                    selectCat="officeid";
                }
                    // Ninjas rule
                    break;
            case R.id.nid:
                if (checked){
                    selectItem=8;
                    selectCat="nid";
                }
                    // Ninjas rule
                    break;

            case R.id.money_bag:
            if (checked){
                selectItem=9;
                selectCat="moneybag";
            }
                // Ninjas rule
                break;
            case R.id.bag:
                if (checked){
                    selectItem=10;
                    selectCat="bag";
                }
                    // Ninjas rule
                    break;
            case R.id.passport:
                if (checked){
                    selectItem=11;
                    selectCat="passport";
                }
                    // Ninjas rule
                    break;
            case R.id.cheque_book:
                if (checked){
                    selectItem=12;
                    selectCat="chequebook";
                }
                    // Ninjas rule
                    break;
            case R.id.house_door:
                if (checked){
                    selectItem=13;
                    selectCat="housedoor";
                }
                    // Ninjas rule
                    break;
            case R.id.book:
                if (checked){
                    selectItem=14;
                    selectCat="book";
                }
                    // Ninjas rule
                    break;
            case R.id.dairy:
                if (checked){
                    selectItem=15;
                    selectCat="dairy";
                }
                    // Ninjas rule
                    break;
            case R.id.document_file:
                if (checked){
                    selectItem=16;
                    selectCat="documentfile";
                }
                    // Ninjas rule
                    break;
            case R.id.youtube:
                if (checked){
                    selectItem=17;
                    selectCat="youtube";
                }
                    // Ninjas rule
                    break;
            case R.id.facebook:
                if (checked){
                    selectItem=18;
                    selectCat="facebook";
                }
                    // Ninjas rule
                    break;
            case R.id.wifi:
                if (checked){
                    selectItem=19;
                    selectCat="wifi";
                }
                    // Ninjas rule
                    break;
            case R.id.othersId:
                if (checked){
                    selectItem=20;
                    selectCat="othersid";
                }
                    // Ninjas rule
                    break;
            default:
                break;


        }
    }


    public void onItemClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()){
            case R.id.dhaka:
                if (checked){
                    area="Dhaka";
                }
                break;
            case R.id.ctg:
                if (checked){
                    area="Chittagong";
                }
                break;
            case R.id.feni:
                if (checked){
                    area="Feni";
                }
                break;
        }
    }
}
