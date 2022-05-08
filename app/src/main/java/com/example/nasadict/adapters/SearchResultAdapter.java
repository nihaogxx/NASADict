package com.example.nasadict.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.nasadict.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nasadict.models.SingleItem;
import com.example.nasadict.models.SingleItemDTO;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchResultAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<SingleItem> list;
    private OnItemListener onItemListener;

    private static final int ITEM = 0;
    private static final int LOADING = 1;
    private boolean isLoadingAdded = false;

    public SearchResultAdapter(Context context, OnItemListener onItemListener){
        this.context = context;
        this.onItemListener = onItemListener;
        this.list = new ArrayList<>();
    }

    public void setList(List<SingleItem> list){
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        if (viewType == ITEM){
            View view = LayoutInflater.from(context).inflate(R.layout.single_item, parent, false);
            return new SearchResultViewHolder(view, onItemListener);
        }

        View view = LayoutInflater.from(context).inflate(R.layout.item_loading, parent, false);
        return new LoadingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (getItemViewType(position) == ITEM) {
            SearchResultViewHolder searchResultViewHolder = (SearchResultViewHolder) holder;
            SingleItem singleItem = list.get(position);
            searchResultViewHolder.mTextView.setText(singleItem.getTitle());
            Picasso.get().load(singleItem.getImage()).into(((SearchResultViewHolder) holder).mImageView);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return (position == list.size() - 1 && isLoadingAdded) ? LOADING : ITEM;
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
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            Log.d("adapter", "view holder clicked");
            mOnItemListener.onItemClicked(getBindingAdapterPosition());
        }
    }

    //Helpers
    public void addLoadingFooter() {
        isLoadingAdded = true;
        SingleItem footer = new SingleItem();
        list.add(footer);
        notifyItemInserted(list.size()-1);
    }

    public void removeLoadingFooter() {
        isLoadingAdded = false;
        if(list.size() > 0){
            int position = list.size() - 1;
            SingleItem item = list.get(position);

            if (item != null) {
                list.remove(position);
                notifyItemRemoved(position);
            }
        }

    }



    //ViewHolders
    public class LoadingViewHolder extends RecyclerView.ViewHolder {
        private ProgressBar mProgressBar;

        public LoadingViewHolder(View itemView) {
            super(itemView);
            mProgressBar = itemView.findViewById(R.id.loading_progress);
        }
    }

    public interface OnItemListener{
        void onItemClicked(int position);
    }
}
