package com.example.lostandfound;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class FeedDataAdapter extends RecyclerView.Adapter<FeedDataAdapter.ViewHolder> implements Filterable {
    private List<FeedItemDataRetrieve> listData;
    private List<FeedItemDataRetrieve> listDataFiltered;
    private String primaryKey;
    Context context;
    FeedItemDataRetrieve ld;
    //private OnItemClicked onClick;

    public FeedDataAdapter(List<FeedItemDataRetrieve> listData) {
        this.listData = listData;
    }

    public FeedDataAdapter(Context context ,List<FeedItemDataRetrieve> listData,String primaryKey) {
        this.listData = listData;
        this.context=context;
        this.primaryKey=primaryKey;
    }

    //make interface like this
   /* public interface OnItemClicked {
        void onItemClick(int position);
    }*/

    public FeedDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_data_retreive,parent,false);

        return new FeedDataAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        ld=listData.get(position);
        holder.what_txt.setText(ld.getWhatItem());
        //Log.d( "My app","Get Data"+ld.getWhatItem() );
        //System.out.println(  );
        holder.address.setText(ld.getAddress());
        holder.desc.setText(ld.getDesc());
        holder.time.setText(ld.getTime());
        holder.cat.setText( ld.getCategory1());
        // holder.what_txt.setText(ld.getPhone());
        holder.parentLayout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent( context,DescriptiveView.class );
                intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                intent.putExtra( "primaryKey", primaryKey);
                intent.putExtra( "what",holder.what_txt.getText().toString() );
                intent.putExtra( "address",holder.address.getText().toString() );
                intent.putExtra( "desc",holder.desc.getText().toString() );
                intent.putExtra( "time",holder.time.getText().toString());
                intent.putExtra( "timestamp",ld.getTimestamp() );
                intent.putExtra( "category1",holder.cat.getText().toString() );
                intent.putExtra( "category2",ld.getCategory2() );
                context.startActivity( intent );
            }
        } );

    }




    @Override
    public int getItemCount() {
        return listData.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()){
                    listDataFiltered=listData;
                }else {
                    List<FeedItemDataRetrieve> filteredList =new ArrayList<>(  );
                    for (FeedItemDataRetrieve row: listData){
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getWhatItem().toLowerCase().contains( charString.toLowerCase() )
                                ||row.getAddress().toLowerCase().contains( charString.toLowerCase() )
                                ||row.getTime().toLowerCase().contains( charString.toLowerCase() )
                                ||row.getDesc().toLowerCase().contains( charString.toLowerCase() )
                                ||row.getCategory1().toLowerCase().contains( charString.toLowerCase() )
                                ||row.getCategory2().contains( charString.toLowerCase() )
                                ||row.getType().contains( charString.toLowerCase() )){
                            filteredList.add( row );
                        }
                    }
                    listDataFiltered=filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = listDataFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                listData.clear();
                listData.addAll( (ArrayList<FeedItemDataRetrieve>) filterResults.values);
                listDataFiltered=(ArrayList<FeedItemDataRetrieve>)filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView what_txt,address,time,desc,cat;
        private CardView parentLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            what_txt=(TextView)itemView.findViewById(R.id.what_id);
            //what_txt.setVisibility( View.GONE );
            address=(TextView)itemView.findViewById(R.id.address);
            time=(TextView)itemView.findViewById(R.id.time);
            desc=(TextView)itemView.findViewById(R.id.desc);
            cat=(TextView)itemView.findViewById(R.id.category);
            parentLayout=(CardView)itemView.findViewById( R.id.cardviewItemDataREtrieve );


        }
    }
}
