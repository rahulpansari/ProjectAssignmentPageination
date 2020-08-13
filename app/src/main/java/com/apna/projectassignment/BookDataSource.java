package com.apna.projectassignment;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.PageKeyedDataSource;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDataSource extends PageKeyedDataSource<String, BookData.Books> {
    public static String PAGE_SIZE="10";
    private String API_KEY="keygTJgCK6ERJsX8P";
    private String OFFSET;
    public MutableLiveData<Boolean> initialValue;
    public  MutableLiveData<Boolean> loadLiveData;

    public BookDataSource()
    {
        initialValue=new MutableLiveData<>();
        loadLiveData=new MutableLiveData<>();

    }

    public MutableLiveData getInitialValue()
    {
        return initialValue;
    }

    public MutableLiveData getLoadValue()
    {
        return loadLiveData;
    }


    @Override
    public void loadInitial(@NonNull LoadInitialParams<String> params, @NonNull final LoadInitialCallback<String, BookData.Books> callback) {
        Call<BookData> bookDataCall=RetrofitClient.getInstance().getApi().getBookDataInitial(API_KEY,PAGE_SIZE);
        initialValue.postValue(true);
        bookDataCall.enqueue(new Callback<BookData>() {
            @Override
            public void onResponse(Call<BookData> call, Response<BookData> response) {
                initialValue.postValue(false);
                if (response.body() != null) {
                    OFFSET = response.body().getOffset();
                    callback.onResult(response.body().getBookdata(), null, OFFSET);
                }
            }

            @Override
            public void onFailure(Call<BookData> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<String> params, @NonNull LoadCallback<String, BookData.Books> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<String> params, @NonNull final LoadCallback<String, BookData.Books> callback) {
        loadLiveData.postValue(true);
        if(OFFSET!=null) {
                Call<BookData> bookDataCall = RetrofitClient.getInstance().getApi().getBookData(API_KEY, PAGE_SIZE, OFFSET);
                bookDataCall.enqueue(new Callback<BookData>() {
                    @Override
                    public void onResponse(Call<BookData> call, Response<BookData> response) {
                        if (response.body() != null) {
                            loadLiveData.postValue(false);
                            OFFSET = response.body().getOffset();
                            callback.onResult(response.body().getBookdata(),  OFFSET);
                        }
                    }

                    @Override
                    public void onFailure(Call<BookData> call, Throwable t) {

                    }
                });
            }
    }

}
