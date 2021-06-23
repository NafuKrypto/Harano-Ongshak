package com.example.lostandfound;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;

public class DrawerItemCustomerAdapter extends ArrayAdapter<DataModel> {
    Context context;
    int resource;
    DataModel data[] = null;
    public DrawerItemCustomerAdapter(@NonNull Context context, int resource, DataModel[] data) {
        super(context, resource,data);
        this.resource = resource;
        this.context = context;
        this.data = data;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View listItem = convertView;

        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        listItem = inflater.inflate(resource, parent, false);

        ImageView imageViewIcon = (ImageView) listItem.findViewById(R.id.imageViewIcon);
        //TextView textViewName = (TextView) listItem.findViewById(R.id.textViewName);

        DataModel folder = data[position];


        imageViewIcon.setImageResource(folder.icon);
        //textViewName.setText(folder.name);

        return listItem;
    }
}

