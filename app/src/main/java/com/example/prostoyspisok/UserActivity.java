package com.example.prostoyspisok;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import androidx.fragment.app.Fragment;

public class UserActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment(){
        Fragment fragment = new UserFragment();
        return fragment;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_items, menu);
        return true;
    }

    public void onExitMenuClick (MenuItem item){
        finish ();
    }

    public void onClearMenuClick(MenuItem item){}
}
