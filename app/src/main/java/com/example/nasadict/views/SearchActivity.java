package com.example.nasadict.views;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nasadict.R;
import com.example.nasadict.adapters.SearchResultAdapter;
import com.example.nasadict.viewmodels.SearchResultViewModel;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {
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
        mResultViewModel.getList().observe(this, singleItems -> mSearchResultAdapter.notifyDataSetChanged());
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
        // enable assisted search
     /*   SearchManager searchManager = (SearchManager)getSystemService(Context.SEARCH_SERVICE) ;
        mSearchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));*/

        mSearchView.setIconified(false);
        mSearchView.setOnQueryTextListener(mOnQueryTextListener);

        return true;
    }

    SearchView.OnQueryTextListener mOnQueryTextListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            mQuery = query;
            mSearchView.clearFocus();
            Log.d("QUERY", query);
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
        mSearchResultAdapter = new SearchResultAdapter(this, new ArrayList<>());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mSearchResultAdapter);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("query", mQuery);
    }
}