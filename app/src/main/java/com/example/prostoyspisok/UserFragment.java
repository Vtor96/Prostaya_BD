package com.example.prostoyspisok;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.prostoyspisok.database.UserDBSchema;

import java.util.UUID;

import androidx.fragment.app.Fragment;

public class UserFragment extends Fragment{
    User user;
    TextView userInfo;
    Button upgBtn, delBtn;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        int position = getArguments().getInt("position");
        user = Users.get(getActivity()).getUserList().get(position);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_user, viewGroup, false);

        upgBtn = view.findViewById(R.id.upgBtn);
        delBtn = view.findViewById(R.id.delBtn);

        userInfo = view.findViewById(R.id.userInfo);
        userInfo.setText(user.getUserName() + " " + user.getUserLastName() + "\n" + user.getPhone());
        return view;
    }
}
