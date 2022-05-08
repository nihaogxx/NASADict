package com.example.nasadict;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.nasadict.models.ResponseDTO;
import com.example.nasadict.models.SingleItem;
import com.example.nasadict.models.SingleItemDTO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchRepository {
    private static SearchRepository instance;
    private MutableLiveData<List<SingleItem>> dataSet = new MutableLiveData<>();
    private MutableLiveData<Boolean> mLoading = new MutableLiveData<>();
    private static final String URL = "https://images-api.nasa.gov/";
    private static final String TAG = "Repository";
    // API service
    private NASARetrofitService nasaRetrofitService = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(NASARetrofitService.class);


    public static SearchRepository getInstance(){
        if(instance == null){
            instance = new SearchRepository();
        }
        return instance;
    }

    public void getSearchResult(String query, int page, boolean isNewSearch){

        Call<ResponseDTO> call = nasaRetrofitService.search(query, page);
        call.enqueue(new Callback<ResponseDTO>() {
            @Override
            public void onResponse(Call<ResponseDTO> call, retrofit2.Response<ResponseDTO> response) {
                Log.d(TAG, "onResponse: " + response);
                if(response.isSuccessful() && response.body() != null){
                    ArrayList<SingleItemDTO> items = response.body().getCollection().getItems();
                    List<SingleItem> data = new ArrayList<>();
                    for(SingleItemDTO singleItemDTO : items){
                        String title = singleItemDTO.getData().get(0).getTitle();
                        String description = singleItemDTO.getData().get(0).getDescription();
                        String date_created = singleItemDTO.getData().get(0).getDate_created();
                        String image = singleItemDTO.getLinks().get(0).getHref();
                        SingleItem singleItem = new SingleItem(image, title, description, date_created);
                        data.add(singleItem);
                    }
                    if(isNewSearch){
                        dataSet.setValue(data);
                    }
                    else {
                        List<SingleItem> current = dataSet.getValue();
                        if(current != null){
                            current.addAll(data);
                            dataSet.setValue(current);
                        }
                    }
                    mLoading.setValue(false);
                }
                else {
                    Log.d(TAG, "onResponse: " + response);
                }
            }

            @Override
            public void onFailure(Call<ResponseDTO> call, Throwable t) {
                Log.d(TAG, "onFailure: Something went wrong: " + t.getMessage());

            }
        });
    }

    public LiveData<List<SingleItem>> getDataSet() {
        return dataSet;
    }

    public MutableLiveData<Boolean> getLoading() {
        return mLoading;
    }
}
