/*
 * Copyright (C) 2018 Eric Afenyo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */

package com.example.eric.quickheadline;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import android.util.Log;


import com.example.eric.quickheadline.model.Bookmark;
import com.example.eric.quickheadline.viewmodel.BookmarkViewModelContract;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by eric on 15/02/2018.
 */

public class BookmarkRepository {

    private BookmarkViewModelContract mViewModel;

    public BookmarkRepository(BookmarkViewModelContract mViewModel) {
        this.mViewModel = mViewModel;
    }


    public static int performDelete(@NonNull final BookmarkDb db, Bookmark bookmark) {
        return db.bookmarkDao().delete(bookmark);
    }

    public void executeSyncTask(@NonNull final BookmarkDb db) {
        BookmarkAsyncTask bookmarkSync = new BookmarkAsyncTask(db, mViewModel);

        try {
            while (bookmarkSync.execute().get() == null){
                bookmarkSync.wait(500);
            }

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }


    }


    public static long[] performInsert(@NonNull BookmarkDb db, Bookmark bookmark) {
        return db.bookmarkDao().insert(bookmark);
    }

    public LiveData<List<Bookmark>>  performQuery(@NonNull BookmarkDb db) {
        LiveData<List<Bookmark>> bookmarks = db.bookmarkDao().queryAll();
        mViewModel.setBookmark(bookmarks);
        if (bookmarks == null) {
            loadEmptyState();
            Log.e("Empty state", "bookmark is null or empty");
        }
        return bookmarks;
    }

    public static void loadEmptyState() {

    }


}


