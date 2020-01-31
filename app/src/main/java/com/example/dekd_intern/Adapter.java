package com.example.dekd_intern;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import org.json.JSONException;
import org.json.JSONObject;


public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {
    private JSONObject mDataSet;
    private Context context;
    private Callback callback;

    public Adapter(JSONObject dataSet,Callback mCallback) {
        mDataSet = dataSet;
        callback = mCallback;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        final Holder holder = new Holder(view);
        this.context = parent.getContext();

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback.someEvent(new ReadPost(holder.url,holder.textTitle.getText().toString(),holder.textDescription.getText().toString()));
            }});
        view.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(true);
                builder.setMessage("คุณต้องการลบข้อมูลนี้?");
                builder.setPositiveButton("ลบ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        int x = holder.getAdapterPosition();
                        mDataSet.remove(String.valueOf(x));
                        MainActivity.js.remove(String.valueOf(x));
                        notifyItemRemoved(x);
                    }
                });
                builder.setNegativeButton("ยกเลิก", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Do something
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                return false;
            }
        });

        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        try {
            holder.setItem(position);
        }  catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return mDataSet.length();
    }

    class Holder extends RecyclerView.ViewHolder {
        TextView textTitle;
        TextView textDescription;
        ImageView picture;
        String url;
        View view;

        public Holder(View itemView) {
            super(itemView);
            textTitle = itemView.findViewById(R.id.text_title);
            textDescription = itemView.findViewById(R.id.text_description);
            picture = itemView.findViewById(R.id.picture);
            view = itemView;
        }

        public void setItem(int position) throws JSONException {
            JSONObject js = mDataSet.getJSONObject(String.valueOf(position));
            url = js.getString("url");
            textTitle.setText(js.getString("header"));
            textDescription.setText(js.getString("description"));
            Glide.with(context).load(url)
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .error(R.mipmap.ic_launcher)
                    .into(picture);

        }
    }


}