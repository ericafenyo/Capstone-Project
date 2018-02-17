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

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.eric.quickheadline.model.Bookmark;
import com.example.eric.quickheadline.viewmodel.BookmarkViewModelContract;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by eric on 16/02/2018.
 */

public class BookmarkViewModel extends AndroidViewModel implements BookmarkViewModelContract {

    private BookmarkRepository mRepository;

    private LiveData<List<Bookmark>> mBookmark;

    public void setLiveDataBookmark(LiveData<List<Bookmark>> mBookmark) {
        Log.e("ModelClass",String.valueOf("setLiveDataBookmark()"));
        this.mBookmark = mBookmark;
    }

    public LiveData<List<Bookmark>> getBookmark() {
        if (mBookmark == null){
            mBookmark = new MutableLiveData<>();
            loadData();
        }

        return mBookmark;

    }

    public BookmarkViewModel(@NonNull Application application) {
        super(application);
        mRepository = new BookmarkRepository(this);
    }

    private void loadData(){
        mRepository.executeSyncTask(BookmarkDb.getINSTANCE(this.getApplication()));
    }

    @Override
    public void setBookmark(LiveData<List<Bookmark>> bookmarks){
        setLiveDataBookmark(bookmarks);
    }

    @Override
    public void setText(String text) {
        Log.e("ModelClass",String.valueOf("setText()"));

    }

}
