package com.example.zhdaily.fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.zhdaily.R;
import com.example.zhdaily.adapter.RecycleViewAdapterByHC;
import com.example.zhdaily.bean.SQLBean;
import com.example.zhdaily.utils.DBUtils;
import com.example.zhdaily.utils.SQLiteHelper;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HistoryFragment extends Fragment {
    RecyclerView rv_history;
    DBUtils dbUtils;
    public HistoryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        initView(view);
        dbUtils = DBUtils.getInstance(getActivity());
        List<SQLBean> list = dbUtils.queryNew();
        RecycleViewAdapterByHC adapterByHC = new RecycleViewAdapterByHC(getActivity(),list);
        rv_history.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv_history.setAdapter(adapterByHC);
        return view;
    }

    private void initView(View view) {
        rv_history = view.findViewById(R.id.rv_history);
    }

}
