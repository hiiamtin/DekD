package com.example.dekd_intern;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.View;

import org.json.JSONObject;


public class MainActivity extends AppCompatActivity implements Callback{
    FloatingActionButton fab;
    public static JSONObject js;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        js = new JSONObject();

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
    public void updateList() {

    }

    public void replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.Frame, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }



}
