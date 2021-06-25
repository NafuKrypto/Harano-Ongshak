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
public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> implements Filterable {
    private List<ItemDataREtreive> listData;
    private List<ItemDataREtreive> listDataFiltered;
    private DataAdapterListener listener;
    private List<FeedItemDataRetrieve> feedlistData;
    ItemDataREtreive ld;
    Context context;
    /*public DataAdapter(List<ItemDataREtreive> listData ) {
        this.listData = listData;

    }*/
    public DataAdapter(List<ItemDataREtreive> listData  ) {
        this.listData = listData;

    }

    public DataAdapter(Context context,List<ItemDataREtreive> listData  ) {
        this.context=context;
        this.listData = listData;

    }






    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_item_data_retreive,parent,false);
        return new ViewHolder(view);
    }
    //Context context;

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        ld=listData.get(position);
        holder.what_txt.setText(ld.getWhatItem());
        holder.address.setText(ld.getAddress());
        holder.desc.setText(ld.getDesc());
        holder.time.setText(ld.getTime());
        holder.cat.setText( ld.getCategory1());
        holder.primaryKey.setText( ld.getPrimaryKey() );
        holder.cat2.setText( ld.getCategory2() );
       // holder.what_txt.setText(ld.getPhone());
        holder.parentLayout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent( context,DescriptiveView.class );
                intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
                intent.putExtra( "primaryKey", holder.primaryKey.getText().toString());
                intent.putExtra( "what",holder.what_txt.getText().toString() );
                intent.putExtra( "address",holder.address.getText().toString() );
                intent.putExtra( "desc",holder.desc.getText().toString() );
                intent.putExtra( "time",holder.time.getText().toString());
                intent.putExtra( "timestamp",ld.getTimestamp() );
                intent.putExtra( "category1",holder.cat.getText().toString() );
                intent.putExtra( "category2",holder.cat2.getText().toString());
                context.startActivity( intent );
            }
        } );
    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView what_txt,address,time,desc,cat,primaryKey,cat2;
        private CardView parentLayout;
        public ViewHolder(View itemView) {
            super(itemView);
            what_txt=(TextView)itemView.findViewById(R.id.what_id);
            //what_txt.setVisibility( View.GONE );
            address=(TextView)itemView.findViewById(R.id.address);
            time=(TextView)itemView.findViewById(R.id.time);
            desc=(TextView)itemView.findViewById(R.id.desc);
            cat=(TextView)itemView.findViewById(R.id.category);
            primaryKey=(TextView)itemView.findViewById( R.id.primarykey );
            cat2=(TextView)itemView.findViewById( R.id.category2);
            parentLayout=(CardView)itemView.findViewById( R.id.cardviewItemDataREtrieve );

        }
    }

    public Filter getFilter(){
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()){
                    listDataFiltered=listData;
                }else {
                    List<ItemDataREtreive> filteredList =new ArrayList<>(  );
                    for (ItemDataREtreive row: listData){
                        // name match condition. this might differ depending on your requirement
                        // here we are looking for name or phone number match
                        if (row.getWhatItem().toLowerCase().contains( charString.toLowerCase() )
                                ||row.getAddress().toLowerCase().contains( charString.toLowerCase() )
                                ||row.getTime().toLowerCase().contains( charString.toLowerCase() )
                                ||row.getDesc().toLowerCase().contains( charString.toLowerCase() )
                                ||row.getCategory1().toLowerCase().contains( charString.toLowerCase() )
                                ||row.getCategory2().contains( charString.toLowerCase() )){
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
              listData.addAll( (ArrayList<ItemDataREtreive>) filterResults.values);
              listDataFiltered=(ArrayList<ItemDataREtreive>)filterResults.values;
              notifyDataSetChanged();
            }
        };
    }
    public interface DataAdapterListener{
        void onItemDataREtreiveSelected(ItemDataREtreive itemDataREtreive);
    }
}
