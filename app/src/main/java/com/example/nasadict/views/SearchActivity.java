package com.example.nasadict.views;

import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.nasadict.R;
import com.example.nasadict.adapters.SearchResultAdapter;
import com.example.nasadict.utils.PaginationScrollListener;
import com.example.nasadict.viewmodels.SearchResultViewModel;


public class SearchActivity extends AppCompatActivity implements SearchResultAdapter.OnItemListener {
    private RecyclerView mRecyclerView;
    private SearchResultAdapter mSearchResultAdapter;
    private SearchResultViewModel mResultViewModel;
    private SearchView mSearchView;
    private static final String TAG = "SearchActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //UI
        mRecyclerView = findViewById(R.id.recyclerview);

        //ViewModel
        mResultViewModel = new ViewModelProvider(this).get(SearchResultViewModel.class);
        mResultViewModel.init();

        // observe search result list
        mResultViewModel.getList().observe(this, singleItems -> {
            mSearchResultAdapter.setList(singleItems);

            if (mResultViewModel.getPage().getValue() == 1){ // new search
                mRecyclerView.scrollToPosition(0);
            }
            if(singleItems.size() == 0){
                Toast.makeText(this, "No result found", Toast.LENGTH_SHORT).show();
            }
        });

        // observe query
        mResultViewModel.getQuery().observe(this, query -> mResultViewModel.newSearch(query));

        // observe loading
        mResultViewModel.getLoading().observe(this, isLoading -> {
                if (isLoading) {
                    mSearchResultAdapter.addLoadingFooter();
                }
                else mSearchResultAdapter.removeLoadingFooter();
        });
        // init recyclerView
        initRecyclerView();
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
            mSearchView.clearFocus();
            // reset query and do search
            mResultViewModel.getQuery().setValue(query);
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            return false;
        }
    };

    private void initRecyclerView() {
        mSearchResultAdapter = new SearchResultAdapter(this, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mSearchResultAdapter);
        // set up scroll behavior for pagination
        mRecyclerView.addOnScrollListener(new PaginationScrollListener(linearLayoutManager) {
            @Override
            protected void loadMoreItems() {
                mResultViewModel.nextPage();
            }
            @Override
            public boolean isLoading() {
                return mResultViewModel.getLoading().getValue();
            }
        });

    }

    @Override
    public void onItemClicked(int position) {
        ItemDetailFragment itemDetailFragment = ItemDetailFragment.newInstance(mResultViewModel.getList().getValue().get(position));
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.recyclerview_container, itemDetailFragment, TAG).addToBackStack(null).commit();
    }
}