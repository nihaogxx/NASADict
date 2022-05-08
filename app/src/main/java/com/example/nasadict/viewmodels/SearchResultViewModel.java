package com.example.nasadict.viewmodels;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.nasadict.SearchRepository;
import com.example.nasadict.models.SingleItem;
import java.util.List;

public class SearchResultViewModel extends AndroidViewModel {

    private SearchRepository mRepository;
    private MutableLiveData<List<SingleItem>> mList;
    private MutableLiveData<Integer> mPage;
    private MutableLiveData<Boolean> mLoading;
    private MutableLiveData<String> mQuery;
    private static final int PAGE_COUNT_LIMIT = 100;

    public SearchResultViewModel(@NonNull Application application) {
        super(application);
    }

    public void init() {
        mRepository = SearchRepository.getInstance();
        mList = (MutableLiveData<List<SingleItem>>) mRepository.getDataSet();
        mPage = new MutableLiveData<>();
        mPage.setValue(0);
        mLoading = mRepository.getLoading();
        mQuery = new MutableLiveData<>();
    }

    private void getSearchResult(String query, int page, boolean isNewSearch){
        mRepository.getSearchResult(query, page, isNewSearch);
    }

    public void newSearch(String query) {
        resetState();
        incrementPage();
        getSearchResult(query, mPage.getValue(), true);
    }

    public void nextPage() {
        // when it is not the first page && =! page count limit(100)
        if (mPage.getValue() > 0 && mPage.getValue() < PAGE_COUNT_LIMIT){
            mLoading.setValue(true);
            incrementPage();
            mRepository.getSearchResult(mQuery.getValue(), mPage.getValue(), false);
        }
    }

    private void resetState(){
        mLoading.setValue(false);
        mPage.setValue(0);
    }

    private void incrementPage(){
        mPage.setValue(mPage.getValue()+1);
    }

    public LiveData<List<SingleItem>> getList() {
        return mList;
    }

    public MutableLiveData<String> getQuery() {
        return mQuery;
    }

    public MutableLiveData<Boolean> getLoading() {
        return mLoading;
    }

    public MutableLiveData<Integer> getPage() {
        return mPage;
    }
}
