package com.example.nasadict.viewmodels;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.nasadict.SearchRepository;
import com.example.nasadict.models.SingleItem;
import com.example.nasadict.models.SingleItemDTO;

import java.util.List;

public class SearchResultViewModel extends ViewModel {

    private SearchRepository mRepository = SearchRepository.getInstance();
    private MutableLiveData<List<SingleItem>> mList = (MutableLiveData<List<SingleItem>>) mRepository.getDataSet();


    public void getSearchResult(String query){
        mList.postValue(mRepository.getSearchResult(query).getValue());
        Log.d("ViewMode", mList.getValue().size() + "");
    }

//    public void observeRepository(){
//
//        mRepository.getDataSet().observe((LifecycleOwner) this, new Observer<List<SingleItem>>() {
//            @Override
//            public void onChanged(List<SingleItem> singleItems) {
//                mList.postValue(singleItems);
//                Log.d("ViewModel", mList.getValue().size() + "");
//            }
//        });
//    }

    public LiveData<List<SingleItem>> getList() {
        return mList;
    }
}
