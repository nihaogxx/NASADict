package com.example.nasadict;

import com.example.nasadict.models.ResponseDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface NASARetrofitService {

    @Headers("Content-Type: application/vnd.collection+json")
    @GET("search?media_type=image")
    Call<ResponseDTO> search(@Query("q") String query);

}
