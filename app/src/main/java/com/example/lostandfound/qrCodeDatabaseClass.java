package com.example.lostandfound;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class qrCodeDatabaseClass {
    String nameS,phonenumS,addressS,emailS,area,selectCat,modelS,email2S,address2S,ownername2S,phonenum2S,name3S;
    String email3S,office3S,phone3S,address3S,networkName,networkType,networkPass,url;

    public qrCodeDatabaseClass() {
    }

    public qrCodeDatabaseClass(String nameS, String phonenumS, String addressS, String emailS, String area, String selectCat, String modelS, String email2S, String address2S, String ownername2S, String phonenum2S, String name3S, String email3S, String office3S, String phone3S, String address3S, String networkName, String networkType, String networkPass, String url) {
        this.nameS = nameS;
        this.phonenumS = phonenumS;
        this.addressS = addressS;
        this.emailS = emailS;
        this.area = area;
        this.selectCat = selectCat;
        this.modelS = modelS;
        this.email2S = email2S;
        this.address2S = address2S;
        this.ownername2S = ownername2S;
        this.phonenum2S = phonenum2S;
        this.name3S = name3S;
        this.email3S = email3S;
        this.office3S = office3S;
        this.phone3S = phone3S;
        this.address3S = address3S;
        this.networkName = networkName;
        this.networkType = networkType;
        this.networkPass = networkPass;
        this.url = url;
    }

    public String getNameS() {
        return nameS;
    }

    public void setNameS(String nameS) {
        this.nameS = nameS;
    }

    public String getPhonenumS() {
        return phonenumS;
    }

    public void setPhonenumS(String phonenumS) {
        this.phonenumS = phonenumS;
    }

    public String getAddressS() {
        return addressS;
    }

    public void setAddressS(String addressS) {
        this.addressS = addressS;
    }

    public String getEmailS() {
        return emailS;
    }

    public void setEmailS(String emailS) {
        this.emailS = emailS;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getSelectCat() {
        return selectCat;
    }

    public void setSelectCat(String selectCat) {
        this.selectCat = selectCat;
    }

    public String getModelS() {
        return modelS;
    }

    public void setModelS(String modelS) {
        this.modelS = modelS;
    }

    public String getEmail2S() {
        return email2S;
    }

    public void setEmail2S(String email2S) {
        this.email2S = email2S;
    }

    public String getAddress2S() {
        return address2S;
    }

    public void setAddress2S(String address2S) {
        this.address2S = address2S;
    }

    public String getOwnername2S() {
        return ownername2S;
    }

    public void setOwnername2S(String ownername2S) {
        this.ownername2S = ownername2S;
    }

    public String getPhonenum2S() {
        return phonenum2S;
    }

    public void setPhonenum2S(String phonenum2S) {
        this.phonenum2S = phonenum2S;
    }

    public String getName3S() {
        return name3S;
    }

    public void setName3S(String name3S) {
        this.name3S = name3S;
    }

    public String getEmail3S() {
        return email3S;
    }

    public void setEmail3S(String email3S) {
        this.email3S = email3S;
    }

    public String getOffice3S() {
        return office3S;
    }

    public void setOffice3S(String office3S) {
        this.office3S = office3S;
    }

    public String getPhone3S() {
        return phone3S;
    }

    public void setPhone3S(String phone3S) {
        this.phone3S = phone3S;
    }

    public String getAddress3S() {
        return address3S;
    }

    public void setAddress3S(String address3S) {
        this.address3S = address3S;
    }

    public String getNetworkName() {
        return networkName;
    }

    public void setNetworkName(String networkName) {
        this.networkName = networkName;
    }

    public String getNetworkType() {
        return networkType;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }

    public String getNetworkPass() {
        return networkPass;
    }

    public void setNetworkPass(String networkPass) {
        this.networkPass = networkPass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void writeToDatabase(){
        DatabaseReference mDatabase;
        Calendar calendar = Calendar.getInstance();
        //Returns current time in millis
        long timeMilli2 = calendar.getTimeInMillis();
        String key= String.valueOf( timeMilli2 );
        qrCodeDatabaseClass m =new qrCodeDatabaseClass( nameS,  phonenumS,  addressS,  emailS,  area,  selectCat,  modelS,  email2S,  address2S,  ownername2S,  phonenum2S,  name3S,  email3S,  office3S,  phone3S,  address3S,  networkName,  networkType,  networkPass,  url);
        mDatabase= FirebaseDatabase.getInstance().getReference("qrCodeForm").child( key);

        mDatabase.setValue( m );




    }
}
