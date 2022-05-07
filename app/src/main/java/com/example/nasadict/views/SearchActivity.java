package com.example.nasadict.views;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nasadict.R;
import com.example.nasadict.adapters.SearchResultAdapter;
import com.example.nasadict.models.SingleItem;
import com.example.nasadict.viewmodels.SearchResultViewModel;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchResultAdapter.OnItemListener {
    private String mQuery;
    private RecyclerView mRecyclerView;
    private SearchResultAdapter mSearchResultAdapter;
    private SearchResultViewModel mResultViewModel;
    private SearchView mSearchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //UI
        mRecyclerView = findViewById(R.id.recyclerview);

        //ViewModel
        mResultViewModel = new ViewModelProvider(this).get(SearchResultViewModel.class);
        mResultViewModel.init();
        mResultViewModel.getList().observe(this, singleItems -> mSearchResultAdapter.setList(singleItems));
        initRecyclerView();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        // initialize searchView
        mSearchView = (SearchView) menu.findItem(R.id.search).getActionView();
        mSearchView.setQueryHint("Search here...");
        mSearchView.setIconified(false);
        mSearchView.setOnQueryTextListener(mOnQueryTextListener);

        return true;
    }

    SearchView.OnQueryTextListener mOnQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            mQuery = query;
            mSearchView.clearFocus();
            // do search
            mResultViewModel.getSearchResult(query);
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    private void initRecyclerView() {
        mSearchResultAdapter = new SearchResultAdapter(this, new ArrayList<>(), this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mSearchResultAdapter);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("query", mQuery);
    }

    @Override
    public void onItemClicked(int position) {
        ItemDetailFragment itemDetailFragment = new ItemDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable("item", mResultViewModel.getList().getValue().get(position));
        itemDetailFragment.setArguments(bundle);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.recyclerview, itemDetailFragment, "SearchActivity").addToBackStack(null).commit();
    }
}