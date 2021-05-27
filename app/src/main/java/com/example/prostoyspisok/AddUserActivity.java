package com.example.prostoyspisok;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddUserActivity extends AppCompatActivity{

    Button insertUserBtn;
    EditText editTextName;
    EditText editTextLastName;
    EditText editTextPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d("SYSTEM INFO: ", "Метод onCreate() запущен");
        setContentView(R.layout.activity_add_user);

        insertUserBtn = findViewById(R.id.insertUserBtn);
        editTextName = findViewById(R.id.editTextName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextPhone = findViewById(R.id.editTextPhone);

        insertUserBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d("SYSTEM INFO: ", "Метод onClick() запущен");
                Users users = Users.get(AddUserActivity.this);
                User user = new User();
                user.setUserName(editTextName.getText().toString());
                user.setUserLastName(editTextLastName.getText().toString());
                user.setPhone(editTextPhone.getText().toString());
                users.addUser(user);
                onBackPressed();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu_items, menu);
        return true;
    }

    public void onExitMenuClick (MenuItem item){
        finish ();
    }

    public void onClearMenuClick(MenuItem item){
        editTextName.setText(" ");
        editTextLastName.setText(" ");
        editTextPhone.setText(" ");
    }
}