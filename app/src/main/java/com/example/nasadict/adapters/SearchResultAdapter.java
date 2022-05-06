package com.example.nasadict.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.nasadict.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nasadict.models.SingleItem;
import com.example.nasadict.models.SingleItemDTO;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<SearchResultAdapter.SearchResultViewHolder> {
    private Context context;
    private List<SingleItem> list;

    public SearchResultAdapter(Context context, List<SingleItem> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public SearchResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_item, parent, false);
        return new SearchResultViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchResultViewHolder holder, int position) {
        SingleItem singleItem = list.get(position);
        holder.mTextView.setText(singleItem.getTitle());
        Picasso.get().load(singleItem.getImage()).into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class SearchResultViewHolder extends RecyclerView.ViewHolder{
        ImageView mImageView;
        TextView mTextView;

        public SearchResultViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image);
            mTextView = itemView.findViewById(R.id.title);
        }
    }

}