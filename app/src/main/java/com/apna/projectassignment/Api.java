package com.apna.projectassignment;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {
    @GET("Imported%20table")
    Call<BookData> getBookData(@Query("api_key")String apikey,@Query("pageSize")String records,@Query("offset")String offset);
    @GET("Imported%20table")
    Call<BookData> getBookDataInitial(@Query("api_key")String apikey,@Query("pageSize")String records);

}
