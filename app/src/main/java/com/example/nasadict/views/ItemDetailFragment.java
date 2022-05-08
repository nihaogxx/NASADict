package com.example.nasadict.views;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nasadict.R;
import com.example.nasadict.models.SingleItem;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ItemDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ItemDetailFragment extends Fragment {

    private static final String ARG_ITEM = "item";
    private String title;
    private String description;
    private String date_created;
    private String image;

    public ItemDetailFragment() {
        // Required empty public constructor
    }

    public static ItemDetailFragment newInstance(SingleItem item) {
        ItemDetailFragment fragment = new ItemDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_ITEM, item);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            SingleItem singleItem = getArguments().getParcelable("item");
            title = singleItem.getTitle();
            description = singleItem.getDescription();
            date_created = singleItem.getDate_created();
            image = singleItem.getImage();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_item_detail, container, false);

        //UI
        TextView titleView = view.findViewById(R.id.title_view);
        titleView.setText(title);
        TextView descriptionView = view.findViewById(R.id.description_view);
        descriptionView.setText(description);
        TextView dateView = view.findViewById(R.id.date_view);
        dateView.setText(date_created);
        ImageView imageView = view.findViewById(R.id.image_view);
        Picasso.get().load(image).into(imageView);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().hide();
    }
    @Override
    public void onStop() {
        super.onStop();
        ((AppCompatActivity)getActivity()).getSupportActionBar().show();
    }
}