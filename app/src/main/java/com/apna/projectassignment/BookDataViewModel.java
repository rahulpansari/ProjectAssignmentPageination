package com.apna.projectassignment;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

public class BookDataViewModel extends ViewModel {
    public LiveData<PagedList<BookData.Books>> bookPageList;
    public LiveData<BookDataSource> liveDataSource;
    public MutableLiveData<Boolean> initialProgress;
    public MutableLiveData<Boolean> loadProgress;


    public BookDataViewModel()
    {


        BookDataSourceFactory factory=new BookDataSourceFactory();
        liveDataSource=factory.getBooklivedatasource();

        PagedList.Config config=(new PagedList.Config.Builder()).setEnablePlaceholders(false).setPageSize(10).build();


    bookPageList=new LivePagedListBuilder(factory,config).build();

    initialProgress= (MutableLiveData<Boolean>) Transformations.switchMap(liveDataSource, srcs->srcs.getInitialValue());
    loadProgress= (MutableLiveData<Boolean>) Transformations.switchMap(liveDataSource, srcs->srcs.getLoadValue());
    }
}
