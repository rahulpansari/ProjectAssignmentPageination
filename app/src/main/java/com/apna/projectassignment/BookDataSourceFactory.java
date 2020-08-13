package com.apna.projectassignment;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

public class BookDataSourceFactory extends DataSource.Factory {
    private MutableLiveData<BookDataSource> booklivedatasource=new MutableLiveData<>();
    public BookDataSource bookDataSource;
    @NonNull
    @Override
    public DataSource create() {
      bookDataSource=new BookDataSource();
        booklivedatasource.postValue(bookDataSource);
        return bookDataSource;
    }

    public MutableLiveData<BookDataSource> getBooklivedatasource()
    {
        return booklivedatasource;
    }


}
