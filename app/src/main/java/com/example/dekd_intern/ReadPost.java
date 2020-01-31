package com.example.dekd_intern;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

public class ReadPost extends Fragment{

    private Callback mCallback;
    private ImageView picture;
    private TextView title;
    private TextView description;
    private String url,header,des;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (Callback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement Callback");
        }
    }

    public ReadPost(String url,String header,String des) {
        this.url = url;
        this.header = header;
        this.des = des;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_detail_post, container, false);
        picture = view.findViewById(R.id.picture2);
        title = view.findViewById(R.id.text_title2);
        description = view.findViewById(R.id.text_description2);

        Glide.with((Context) mCallback).load(url).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .error(R.mipmap.ic_launcher)
                .into(picture);

        title.setText(header);
        description.setText(des);
        return view;
    }

}