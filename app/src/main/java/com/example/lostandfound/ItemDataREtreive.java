package com.example.lostandfound;

public class ItemDataREtreive  {

    public String phone,address, category1,category2, desc, status, time, timestamp, whatItem;

    public ItemDataREtreive() {
    }

    public ItemDataREtreive(String phone, String address, String category1, String category2, String desc, String status, String time, String timestamp, String whatItem) {
        this.phone = phone;
        this.address = address;
        this.category1 = category1;
        this.category2 = category2;
        this.desc = desc;
        this.status = status;
        this.time = time;
        this.timestamp = timestamp;
        this.whatItem = whatItem;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCategory1() {
        return category1;
    }

    public void setCategory1(String category1) {
        this.category1 = category1;
    }

    public String getCategory2() {
        return category2;
    }

    public void setCategory2(String category2) {
        this.category2 = category2;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getWhatItem() {
        return whatItem;
    }

    public void setWhatItem(String whatItem) {
        this.whatItem = whatItem;
    }


    /* String phone,primarykey,category1,category2,what,address,time,timestamp,desc,status;

    public ItemDataREtreive( ) {

    }

    public ItemDataREtreive(String phone , String category1, String category2, String what, String address, String time, String timestamp, String desc,String status) {
        this.phone = phone;
        //this.primarykey = primarykey;
        this.category1 = category1;
        this.category2 = category2;
        this.what = what;
        this.address = address;
        this.time = time;
        this.timestamp = timestamp;
        this.desc = desc;
        this.status=status;
    }

    public String getPhone() {
        return phone;
    }

  /*  public String getPrimarykey() {
        return primarykey;
    }*/

   /* public String getCategory1() {
        return category1;
    }

    public String getCategory2() {
        return category2;
    }

    public String getWhat() {
        return what;
    }

    public String getAddress() {
        return address;
    }

    public String getTime() {
        return time;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getDesc() {
        return desc;
    }
    public  String getStatus(){return status;}*/
}
