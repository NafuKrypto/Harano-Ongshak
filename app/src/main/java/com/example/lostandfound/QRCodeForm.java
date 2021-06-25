package com.example.lostandfound;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
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
        phoneNumber.setHint( R.string.phone);
        address.setHint( R.string.address2 );
        email.setHint( R.string.email );
        /*LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);*/
        LinearLayout lp=new LinearLayout( getApplicationContext() );
        lp.setOrientation( LinearLayout.VERTICAL );
        lp.addView( name );
        lp.addView( phoneNumber );
        lp.addView( address );
        lp.addView( email );
        alertDialog.setView( lp );

        alertDialog.setPositiveButton( "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                nameS=name.getText().toString().trim();
                phonenumS=phoneNumber.getText().toString().trim();
                addressS=address.getText().toString().trim();
                emailS=email.getText().toString().trim();
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
        name.setHint( R.string.formName );
        phoneNumber.setHint( R.string.phone);
        address.setHint( R.string.address2 );
        email.setHint( R.string.email );
        /*LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);*/
        LinearLayout lp=new LinearLayout( getApplicationContext() );
        lp.setOrientation( LinearLayout.VERTICAL );
        lp.addView( model );
        lp.addView( name );
        lp.addView( phoneNumber );
        lp.addView( address );
        lp.addView( email );
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
        name.setHint( R.string.formName );
        phoneNumber.setHint( R.string.phone);
        address.setHint( R.string.address2 );
        email.setHint( R.string.email );
        /*LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);*/
        LinearLayout lp=new LinearLayout( getApplicationContext() );
        lp.setOrientation( LinearLayout.VERTICAL );
        lp.addView( office);
        lp.addView( name );
        lp.addView( phoneNumber );
        lp.addView( address );
        lp.addView( email );
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
        networkType.setHint( R.string.networkType );
        password.setHint( R.string.passwordNet);

        /*LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);*/
        LinearLayout lp=new LinearLayout( getApplicationContext() );
        lp.setOrientation( LinearLayout.VERTICAL );
        lp.addView( network_name);
        lp.addView( networkType );
        lp.addView( password );

        alertDialog.setView( lp );

        alertDialog.setPositiveButton( "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
               networkName=network_name.getText().toString().trim();
                NetworkType=networkType.getText().toString().trim();
                networkPass=password.getText().toString().trim();

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


        /*LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);*/
        LinearLayout lp=new LinearLayout( getApplicationContext() );
        lp.setOrientation( LinearLayout.VERTICAL );
        lp.addView( url);
        alertDialog.setView( lp );

        alertDialog.setPositiveButton( "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
                Url=url.getText().toString().trim();


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
                    //createAlertDialougeFuncPhone();
                    Toast.makeText( getApplicationContext(),"phone is checked",Toast.LENGTH_LONG ).show();


                }

                    break;
            case R.id.camera:
                if (checked){
                    selectItem=2;
                }
                    // Ninjas rule
                    break;
            case R.id.laptop:
                if (checked){
                    selectItem=3;
                }
                    // Ninjas rule
                    break;
            case R.id.atm_card:
                if (checked){
                    selectItem=4;
                }
                    // Ninjas rule
                    break;
            case R.id.key_ring:
                if (checked){
                    selectItem=5;
                }
                    // Ninjas rule
                    break;
            case R.id.student_id:
                if (checked){
                    selectItem=6;
                }
                    // Ninjas rule
                    break;
            case R.id.officeId:
                if (checked){
                    selectItem=7;
                }
                    // Ninjas rule
                    break;
            case R.id.nid:
                if (checked){
                    selectItem=8;
                }
                    // Ninjas rule
                    break;

            case R.id.money_bag:
            if (checked){
                selectItem=9;
            }
                // Ninjas rule
                break;
            case R.id.bag:
                if (checked){
                    selectItem=10;
                }
                    // Ninjas rule
                    break;
            case R.id.passport:
                if (checked){
                    selectItem=11;
                }
                    // Ninjas rule
                    break;
            case R.id.cheque_book:
                if (checked){
                    selectItem=12;
                }
                    // Ninjas rule
                    break;
            case R.id.house_door:
                if (checked){
                    selectItem=13;
                }
                    // Ninjas rule
                    break;
            case R.id.book:
                if (checked){
                    selectItem=14;
                }
                    // Ninjas rule
                    break;
            case R.id.dairy:
                if (checked){
                    selectItem=15;
                }
                    // Ninjas rule
                    break;
            case R.id.document_file:
                if (checked){
                    selectItem=16;
                }
                    // Ninjas rule
                    break;
            case R.id.youtube:
                if (checked){
                    selectItem=17;
                }
                    // Ninjas rule
                    break;
            case R.id.facebook:
                if (checked){
                    selectItem=18;
                }
                    // Ninjas rule
                    break;
            case R.id.wifi:
                if (checked){
                    selectItem=19;
                }
                    // Ninjas rule
                    break;
            case R.id.othersId:
                if (checked){
                    selectItem=20;
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
