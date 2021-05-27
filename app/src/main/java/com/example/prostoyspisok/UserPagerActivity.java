package com.example.prostoyspisok;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

public class UserPagerActivity extends AppCompatActivity{
    private ViewPager userViewPager;
    private List<User> users;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_pager);

        userViewPager = findViewById(R.id.userViewPager);
        users = Users.get(this).getUserList();
        int position = getIntent().getIntExtra("position", 1);
        FragmentManager fragmentManager = getSupportFragmentManager();

        userViewPager.setAdapter(new FragmentPagerAdapter(fragmentManager){
            @Override
            public Fragment getItem(int position){ //фрагмент с текущим пользователем
                User user = users.get(position);
                Bundle args = new Bundle();
                args.putInt("position", position);
                UserFragment fragment = new UserFragment();
                fragment.setArguments(args);
                return fragment;
            }

            @Override
            public int getCount(){ //возвращает кол-во элементов списка
                return users.size();
            }
        });
        userViewPager.setCurrentItem(position);
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