package com.example.zhdaily.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zhdaily.R;
import com.example.zhdaily.adapter.RecycleViewAdapterByHC;
import com.example.zhdaily.bean.SQLBean;
import com.example.zhdaily.utils.DBUtils;

import org.w3c.dom.Text;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CollectionFragment extends Fragment {
    RecyclerView rv_collection;
    TextView tv_collectionTitle;
    DBUtils dbUtils;

    public CollectionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        View view = inflater.inflate(R.layout.fragment_collection, container, false);
        initView(view);
        dbUtils = DBUtils.getInstance(getActivity());
        List<SQLBean> list = dbUtils.queryNewByColl();
        RecycleViewAdapterByHC adapterByHC = new RecycleViewAdapterByHC(getActivity(),list);
        rv_collection.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_collection.setAdapter(adapterByHC);
        return view;
    }
    private void initView(View view) {
        rv_collection = view.findViewById(R.id.rv_collection);
    }
}
