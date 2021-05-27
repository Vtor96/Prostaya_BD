package com.example.prostoyspisok;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class UserFragment extends Fragment{
    int position;
    User user;
    Users users;
    TextView userInfo;
    Button upgBtn, delBtn;
    EditText editName;
    EditText editLastName;
    EditText editPhone;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        position = getArguments().getInt("position");
        user = Users.get(getActivity()).getUserList().get(position);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_user, viewGroup, false);

        users =Users.get(getActivity());
        editName = view.findViewById(R.id.editName);
        editLastName = view.findViewById(R.id.editLastName);
        editPhone = view.findViewById(R.id.editPhone);

        upgBtn = view.findViewById(R.id.upgBtn);
        delBtn = view.findViewById(R.id.delBtn);

        userInfo = view.findViewById(R.id.userInfo);
        userInfo.setText(user.getUserName() + " " + user.getUserLastName() + "\n" + user.getPhone());

        upgBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                String newUserName = editName.getText().toString();
                String newUserLastname = editLastName.getText().toString();
                String newUserPhone = editPhone.getText().toString();
                user.setUserName(newUserName);
                user.setUserLastName(newUserLastname);
                user.setPhone(newUserPhone);
                users.upgUser(user);
                Toast.makeText(getActivity(),"Данные изменены",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Users.get(getContext()).deleteUser(user.getUuid().toString());
                Intent intent = new Intent(getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
