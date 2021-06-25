package com.example.lostandfound;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

public class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    String  [] imageUri ;
    //private String[] imageUrls;
    private List<DescriptiveView.ImageClass> imageUrls;
    ViewPagerAdapter(Context context, String [] imageUri) {
        this.context = context;
        this.imageUri = imageUri;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container,final int position) {
        ImageView imageView = new ImageView(context);



        Picasso.get()
                .load( imageUri[position] )
                .fit()
                .centerCrop()
                .into(imageView);
        container.addView(imageView);
        return imageView;
    }
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
    @Override
    public int getCount() {
        return imageUri.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }
}
