package com.example.prostoyspisok;

import android.view.Menu;
import android.view.MenuItem;

import androidx.fragment.app.Fragment;

public class MainActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment(){
        return new UserListFragment();
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