package com.example.dekd_intern;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.View;
import org.json.JSONArray;


public class MainActivity extends AppCompatActivity implements Callback{
    FloatingActionButton fab;
    public static JSONArray js;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        js = new JSONArray();

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.Frame, new ListPost())
                    .commit();
        }

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                someEvent(new CreatePost());
    }
});
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        fab.show();
    }

    @Override
    public void someEvent(Fragment fragment) {
        replaceFragment(fragment);
        fab.hide();

    }

    @Override
    public void removeEvent() {
        getSupportFragmentManager().popBackStack();
        fab.show();
    }

    @Override
    public void updateAdapter(Adapter adapter) {
        adapter.notifyDataSetChanged();
    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.Frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }



}
