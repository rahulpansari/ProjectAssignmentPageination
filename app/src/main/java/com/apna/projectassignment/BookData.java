package com.apna.projectassignment;

import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class BookData implements  Serializable {
    @SerializedName("records")
    private List<Books> bookdata;

    public List<Books> getBookdata() {
        return bookdata;
    }

    @SerializedName("offset")
    private String offset;

    public String getOffset() {
        return offset;
    }

    public class Books implements Serializable
    {
        @SerializedName("id")
        private String id;

        @Override
        public boolean equals(@Nullable Object obj) {
            return super.equals(obj);
        }

        @SerializedName("fields")
        private BookField bookfield;
        @SerializedName("createdTime")
        private String createdTime;
        public class BookField implements  Serializable
        {
            @SerializedName("Title")
            private String title;
            @SerializedName("Author")
            private String author;
            @SerializedName("Genre")
            private String genre;

            public String getTitle() {
                return title;
            }

            public String getAuthor() {
                return author;
            }

            public String getGenre() {
                return genre;
            }
        }

        public String getId() {
            return id;
        }

        public BookField getBookfield() {
            return bookfield;
        }

        public String getCreatedTime() {
            return createdTime;
        }
    }
}
