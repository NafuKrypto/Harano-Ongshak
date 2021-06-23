package com.example.lostandfound;

public class FeedItemDataRetrieve {
    public String phone,address, category1,category2, desc, status, time, timestamp,type, whatItem;

    public FeedItemDataRetrieve( ) {

    }

    public FeedItemDataRetrieve(String phone, String address, String category1, String category2, String desc, String status, String time, String timestamp, String type, String whatItem) {
        this.phone = phone;
        this.address = address;
        this.category1 = category1;
        this.category2 = category2;
        this.desc = desc;
        this.status = status;
        this.time = time;
        this.timestamp = timestamp;
        this.type = type;
        this.whatItem = whatItem;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCategory1(String category1) {
        this.category1 = category1;
    }

    public void setCategory2(String category2) {
        this.category2 = category2;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setWhatItem(String whatItem) {
        this.whatItem = whatItem;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getCategory1() {
        return category1;
    }

    public String getCategory2() {
        return category2;
    }

    public String getDesc() {
        return desc;
    }

    public String getStatus() {
        return status;
    }

    public String getTime() {
        return time;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getType() {
        return type;
    }

    public String getWhatItem() {
        return whatItem;
    }
}
