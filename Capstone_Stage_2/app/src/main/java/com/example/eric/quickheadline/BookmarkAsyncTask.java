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

/**
 * Created by eric on 16/02/2018.
 */

import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.eric.quickheadline.model.Bookmark;
import com.example.eric.quickheadline.viewmodel.BookmarkViewModelContract;

import java.util.List;

/**
 * TODO: Comment on this class
 * An async Class that
 */
public class BookmarkAsyncTask extends AsyncTask<Void, Void, LiveData<List<Bookmark>>> {

    private final BookmarkDb db;
    private BookmarkViewModelContract mViewModel;


    public BookmarkAsyncTask(BookmarkDb db, BookmarkViewModelContract mViewModel) {
        this.db = db;
        this.mViewModel = mViewModel;
    }

    @Override
    protected LiveData<List<Bookmark>> doInBackground(Void... params) {
        return new BookmarkRepository(mViewModel).performQuery(db);

    }


}
