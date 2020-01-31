package com.example.dekd_intern;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CreatePost extends Fragment implements View.OnClickListener  {

    Callback mCallback;
    TextView url,header,des;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mCallback = (Callback) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement Callback");
        }
    }

    public CreatePost() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_post, container, false);
        view.findViewById(R.id.ok_button).setOnClickListener(this);
        url = view.findViewById(R.id.picture_url_input);
        header = view.findViewById(R.id.header_input);
        des = view.findViewById(R.id.description_input);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ok_button:
                if(header.getText().toString().equals("")||des.getText().toString().equals("")){
                    Toast.makeText((Context) mCallback,"โปรใส่ข้อมูลให้ครบ",Toast.LENGTH_SHORT).show();
                }
                else{
                    JSONObject js = new JSONObject();
                    try {
                        js.put("url",url.getText().toString());
                        js.put("header",header.getText().toString());
                        js.put("description",des.getText().toString());
                        saveJSON(js);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
        }
    }

    private void saveJSON(JSONObject js) throws JSONException {
        MainActivity.js.put(js);
        mCallback.removeEvent();
    }
}
