package com.example.nasadict.viewmodels;

import android.app.Application;
import android.util.Log;

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
    private static final String TAG = "ViewModel";


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

    private void getSearchResult(String query, int page, boolean isNewResult){
        mRepository.getSearchResult(query, page, isNewResult);
    }

    public void nextPage() {
        // when it is not the first page && =! page count limit(100)
        if (mPage.getValue() > 0 && mPage.getValue() < PAGE_COUNT_LIMIT){
            mLoading.setValue(true);
            Log.d(TAG, "next page: " + mLoading.getValue());
            incrementPage();
            Log.d(TAG, "page value for next search:" + mPage.getValue());
            mRepository.getSearchResult(mQuery.getValue(), mPage.getValue(), false);
        }
    }

    public void newSearch(String query) {
        resetState();
        incrementPage();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "page value for new search:" + mPage.getValue());
        getSearchResult(query, mPage.getValue(), true);
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
