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


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookmarkFragment extends Fragment implements BookmarkAdaptor.onItemSelected {
    private BookmarkAdaptor mAdaptor;
    @BindView(R.id.recycler_view_bookmark)
    RecyclerView recyclerView;

    private String TAG = "BookmarkFragment";

    public BookmarkFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */
    // TODO: Rename and change types and number of parameters
    public static BookmarkFragment newInstance() {
        return new BookmarkFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bookmark, container, false);
        ButterKnife.bind(this, view);

        mAdaptor = new BookmarkAdaptor(getActivity(), this);

        return view;
    }

    @Override
    public void onStart() {
        Log.e(TAG, "onStart()");
        super.onStart();
        BookmarkViewModel viewModel = ViewModelProviders.of(getActivity()).get(BookmarkViewModel.class);
        if (viewModel.getBookmark() != null) {
            viewModel.getBookmark().observe(getActivity(), bookmarks -> {
                Log.e(TAG, String.valueOf(bookmarks));
                mAdaptor.setData(bookmarks);
                recyclerView.setAdapter(mAdaptor);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

            });
        }
    }

    @Override
    public void onClick(int position, String webUrl) {

    }
}
