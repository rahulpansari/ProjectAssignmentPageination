package com.apna.projectassignment;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

public class BookRecyclerAdapter extends PagedListAdapter<BookData.Books, BookRecyclerAdapter.BookRecyclerHolder> {

    Context context;

    protected BookRecyclerAdapter(@NonNull DiffUtil.ItemCallback<BookData.Books> diffCallback) {
        super(diffCallback);
    }

    @Override
    public void registerAdapterDataObserver(@NonNull RecyclerView.AdapterDataObserver observer) {
        super.registerAdapterDataObserver(observer);
    }

    public BookRecyclerAdapter(Context c)
    {
        super(DIFF_CALLBACK);
        this.context=c;
    }
    @NonNull
    @Override
    public BookRecyclerHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.recycler_layout,parent,false);
        return new BookRecyclerHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookRecyclerHolder holder, int position) {
        BookData.Books books=getItem(position);
         holder.author.setText(books.getBookfield().getAuthor());
         holder.bookname.setText(books.getBookfield().getTitle());
         holder.genre.setText(books.getBookfield().getGenre());
         holder.cardView.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent i=new Intent(context,MainActivity2.class);
                 i.putExtra("book",books.getBookfield());
                 context.startActivity(i);
             }
         });
    }
    private static DiffUtil.ItemCallback<BookData.Books> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<BookData.Books>() {
                @Override
                public boolean areItemsTheSame(BookData.Books oldBookdata, BookData.Books newBookData) {
                    return oldBookdata.getId()==newBookData.getId();
                }

                @Override
                public boolean areContentsTheSame(BookData.Books oldBookData,
                                                  BookData.Books newBookData) {
                    return oldBookData.equals(newBookData);
                }
            };
    public class BookRecyclerHolder extends RecyclerView.ViewHolder
    {
        TextView bookname,author,genre;
        CardView cardView;
        public BookRecyclerHolder(@NonNull View itemView) {
            super(itemView);
            cardView=itemView.findViewById(R.id.cardView);
            bookname=itemView.findViewById(R.id.bookname);
            author=itemView.findViewById(R.id.bookauthor);
            genre=itemView.findViewById(R.id.bookgenre);
        }
    }
}
