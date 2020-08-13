package com.apna.projectassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import androidx.appcompat.widget.SearchView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
RecyclerView recyclerView;
BookDataViewModel viewModel;
boolean loaddata;
Toolbar toolbar;
    BookRecyclerAdapter adapter;
    PagedList<BookData.Books> orgbooks;
    PagedList<BookData.Books> searchbooks;
ProgressBar progressBarinitial,progressBarloading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recyclerview);
        progressBarinitial=findViewById(R.id.progressBarinitial);
        progressBarloading=findViewById(R.id.progressBarload);
        toolbar=findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Books");

        adapter=new BookRecyclerAdapter(this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        viewModel = new ViewModelProvider(this).get(BookDataViewModel.class);
        viewModel.bookPageList.observe(this, new Observer<PagedList<BookData.Books>>() {
            @Override
            public void onChanged(PagedList<BookData.Books> books) {
                orgbooks=books;
                searchbooks=books;
                adapter.submitList(books);
            }
        });
        viewModel.initialProgress.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if(aBoolean==true)
                    progressBarinitial.setVisibility(View.VISIBLE);
                else
                    progressBarinitial.setVisibility(View.GONE);
            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (loaddata
                        && !recyclerView.canScrollVertically(1)) {
                  progressBarloading.setVisibility(View.VISIBLE);

                }
                else
                {
                    progressBarloading.setVisibility(View.GONE);
                }
            }

        });
        viewModel.loadProgress.observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Log.e("load",aBoolean+"");
                if(aBoolean==true)
                    loaddata=true;
                else {
                    progressBarloading.setVisibility(View.GONE);
                    loaddata = false;
                }
            }
        });
            recyclerView.setAdapter(adapter);
    }

   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem=menu.findItem(R.id.search_icon);
        SearchView searchView=(SearchView) menuItem.getActionView();
        searchView.setQueryHint("Search Books");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
PagedList<BookData.Books> books=new PagedList.Builder<BookData.Books>()
                for(int i=0;i<orgbooks.size();i++)
                {
                    if(orgbooks.get(i).getBookfield().getAuthor().toLowerCase().contains(query.toLowerCase()))
                    {
                        //searchbooks.add(orgbooks.get(i));
                    }
                    else if(orgbooks.get(i).getBookfield().getGenre().toLowerCase().contains(query.toLowerCase()))
                    {
                        //searchbooks.add(orgbooks.get(i));
                    }
                    else if(orgbooks.get(i).getBookfield().getTitle().toLowerCase().contains(query.toLowerCase()))
                    {
                        //searchbooks.add(orgbooks.get(i));
                    }
                    else
                    {
                        searchbooks.remove(i);
                    }
                }
                adapter.submitList( searchbooks);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }*/
}