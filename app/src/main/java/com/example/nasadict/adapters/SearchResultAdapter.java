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
    private OnItemListener onItemListener;

    public SearchResultAdapter(Context context, List<SingleItem> list, OnItemListener onItemListener){
        this.context = context;
        this.list = list;
        this.onItemListener = onItemListener;
    }

    public void setList(List<SingleItem> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public SearchResultViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_item, parent, false);
        return new SearchResultViewHolder(view, onItemListener);
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

    public class SearchResultViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mImageView;
        TextView mTextView;
        OnItemListener mOnItemListener;

        public SearchResultViewHolder(@NonNull View itemView, OnItemListener onItemListener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.image);
            mTextView = itemView.findViewById(R.id.title);
            this.mOnItemListener = onItemListener;
        }

        @Override
        public void onClick(View view) {
            mOnItemListener.onItemClicked(getBindingAdapterPosition());
        }
    }

    public interface OnItemListener{
        void onItemClicked(int position);
    }
}
