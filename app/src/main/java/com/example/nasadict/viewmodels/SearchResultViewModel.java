package com.example.nasadict.viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.nasadict.SearchRepository;
import com.example.nasadict.models.SingleItem;

import java.util.List;

public class SearchResultViewModel extends AndroidViewModel {

    private SearchRepository mRepository;
    private LiveData<List<SingleItem>> mList;

    public SearchResultViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        mRepository = SearchRepository.getInstance();
        mList = mRepository.getDataSet();
    }

    public void getSearchResult(String query){
        mRepository.getSearchResult(query);
    }

    public LiveData<List<SingleItem>> getList() {
        return mList;
    }

}
