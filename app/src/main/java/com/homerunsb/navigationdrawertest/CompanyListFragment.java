package com.homerunsb.navigationdrawertest;

/*
* Copyright (C) 2014 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*      http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Demonstrates the use of {@link RecyclerView} with a {@link LinearLayoutManager} and a
 * {@link GridLayoutManager}.
 */
public class CompanyListFragment extends Fragment {

    private static final String TAG = "CompanyListFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;
//    private static final int DATASET_COUNT = 60;

//    public CompanyListFragment(){
//        Log.i(TAG, "****************************************");
//    }

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;

//    protected RadioButton mLinearLayoutRadioButton;
//    protected RadioButton mGridLayoutRadioButton;

    protected RecyclerView mRecyclerView;
    protected CompanyListAdapter mCompanyAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected List<CompanyDataItem> mCompanyDataItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate()");

        // Initialize dataset, this data would usually come from a local content provider or
        // remote server.
        initDataset();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView()");

        View rootView = inflater.inflate(R.layout.fragment_item_layout, container, false);
        rootView.setTag(TAG);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_itemList);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        if (savedInstanceState != null) {
            // Restore saved layout manager type.
            mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState
                    .getSerializable(KEY_LAYOUT_MANAGER);
        }
        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);

        Log.i(TAG, "mCompanyDataItems size () " + mCompanyDataItems.size());
        mCompanyAdapter = new CompanyListAdapter(mCompanyDataItems);
        // Set CustomAdapter as the adapter for RecyclerView.
        mRecyclerView.setAdapter(mCompanyAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);

        setRecyclerViewLayoutManager(LayoutManagerType.GRID_LAYOUT_MANAGER);


        return rootView;
    }

    /**
     * Set RecyclerView's LayoutManager to the one given.
     *
     * @param layoutManagerType Type of layout manager to switch to.
     */
    public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
        Log.i(TAG, "setRecyclerViewLayoutManager()");
        Log.i(TAG, "레이아웃 매니저 : "  + layoutManagerType.toString());

        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }


        switch (layoutManagerType) {
            case GRID_LAYOUT_MANAGER:
                mLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT);
                mCurrentLayoutManagerType = LayoutManagerType.GRID_LAYOUT_MANAGER;
                break;
            case LINEAR_LAYOUT_MANAGER:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
                break;
            default:
                mLayoutManager = new LinearLayoutManager(getActivity());
                mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;
        }

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.scrollToPosition(scrollPosition);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        Log.i(TAG, "onSaveInstanceState()");

        // Save currently selected layout manager.
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }

    /**
     * Generates Strings for RecyclerView's adapter. This data would usually come
     * from a local content provider or remote server.
     */
    private void initDataset() {
        Log.i(TAG, "initDataset()");

        mCompanyDataItems = new ArrayList<CompanyDataItem>();
        mCompanyDataItems.add(new CompanyDataItem("Indigo", "#3F51B5"));
        mCompanyDataItems.add(new CompanyDataItem("Pink", "#E91E63"));
        mCompanyDataItems.add(new CompanyDataItem("Orange", "#FF5722"));
        mCompanyDataItems.add(new CompanyDataItem("Green", "#4CAF50"));
        mCompanyDataItems.add(new CompanyDataItem("Grey", "#607D8B"));
        mCompanyDataItems.add(new CompanyDataItem("Cyan", "#00BCD4"));
        mCompanyDataItems.add(new CompanyDataItem("Amber", "#FFC107"));
        mCompanyDataItems.add(new CompanyDataItem("Brown", "#795548"));
        mCompanyDataItems.add(new CompanyDataItem("Blue", "#03A9F4"));
        mCompanyDataItems.add(new CompanyDataItem("Red", "#F44336"));



    }
}