package com.example.dekd_intern;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ListPost extends Fragment{

    Callback mCallback;
    RecyclerView recyclerView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (Callback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement Callback");
        }
    }

    public ListPost() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list_post, container, false);
        recyclerView = view.findViewById(R.id.recycleview);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager((Context) mCallback);
        recyclerView.setLayoutManager(layoutManager);
        Adapter adapter = new Adapter(MainActivity.js,mCallback);
        recyclerView.setAdapter(adapter);
        mCallback.updateAdapter(adapter);
        return view;
    }



}

