package com.homerunsb.navigationdrawertest;

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

public class MaterialListFragment extends Fragment {

    private static final String TAG = "MaterialListFragment";
    private static final String KEY_LAYOUT_MANAGER = "layoutManager";
    private static final int SPAN_COUNT = 2;
//    private static final int DATASET_COUNT = 60;

    private enum LayoutManagerType {
        GRID_LAYOUT_MANAGER,
        LINEAR_LAYOUT_MANAGER
    }

    protected LayoutManagerType mCurrentLayoutManagerType;
    protected RecyclerView mRecyclerView;
    protected MaterialListAdapter mMaterialAdapter;
    protected RecyclerView.LayoutManager mLayoutManager;
    protected List<MaterialDataItem> mMaterialItems;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, "onCreate()");
        initDataset();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_item_layout, container, false);
        rootView.setTag(TAG);

        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_itemList);

        // LinearLayoutManager is used here, this will layout the elements in a similar fashion
        // to the way ListView would layout elements. The RecyclerView.LayoutManager defines how
        // elements are laid out.
        mLayoutManager = new LinearLayoutManager(getActivity());

        mCurrentLayoutManagerType = LayoutManagerType.LINEAR_LAYOUT_MANAGER;

        if (savedInstanceState != null) {
            // Restore saved layout manager type.
            mCurrentLayoutManagerType = (LayoutManagerType) savedInstanceState
                    .getSerializable(KEY_LAYOUT_MANAGER);
        }
        setRecyclerViewLayoutManager(mCurrentLayoutManagerType);

        Log.i(TAG, "mMaterialItems size () " + mMaterialItems.size());

        mMaterialAdapter = new MaterialListAdapter(mMaterialItems);
        // Set CustomAdapter as the adapter for RecyclerView.
        mRecyclerView.setAdapter(mMaterialAdapter);
        mRecyclerView.setNestedScrollingEnabled(false);

        setRecyclerViewLayoutManager(LayoutManagerType.LINEAR_LAYOUT_MANAGER);

        return rootView;
    }

    public void setRecyclerViewLayoutManager(LayoutManagerType layoutManagerType) {
        int scrollPosition = 0;
        Log.i(TAG, "레이아웃 매니저 : "  + layoutManagerType.toString());

        // If a layout manager has already been set, get current scroll position.
        if (mRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        Log.i(TAG, "액티비티 : " + this.getActivity().toString());

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
        // Save currently selected layout manager.
        savedInstanceState.putSerializable(KEY_LAYOUT_MANAGER, mCurrentLayoutManagerType);
        super.onSaveInstanceState(savedInstanceState);
    }


    private void initDataset() {
        mMaterialItems = new ArrayList<MaterialDataItem>();
        mMaterialItems.add(new MaterialDataItem("Indigo", "#3F51B5", "멀티 스트라이프", 4500));
        mMaterialItems.add(new MaterialDataItem("Pink", "#E91E63", "스트라이프 코튼", 2300));
        mMaterialItems.add(new MaterialDataItem("Orange", "#FF5722", "떨이 조각 천", 5500));
        mMaterialItems.add(new MaterialDataItem("Green", "#4CAF50", "싱글 스트라이프", 6100));
        mMaterialItems.add(new MaterialDataItem("Grey", "#607D8B", "조각", 1200));
        mMaterialItems.add(new MaterialDataItem("Cyan", "#00BCD4", "쓰레기", 1500));
        mMaterialItems.add(new MaterialDataItem("Amber", "#FFC107", "비싼 쓰레기", 3100));
        mMaterialItems.add(new MaterialDataItem("Brown", "#795548", "원자재 이름임", 2000));
        mMaterialItems.add(new MaterialDataItem("Blue", "#03A9F4", "노가다", 300));
        mMaterialItems.add(new MaterialDataItem("Red", "#F44336", "원자재", 1500));

    }
}