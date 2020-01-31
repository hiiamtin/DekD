package com.example.dekd_intern;


import androidx.fragment.app.Fragment;

public interface Callback {

    void someEvent(Fragment fragment);
    void removeEvent();
    void updateList();

}
