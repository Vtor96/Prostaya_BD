package com.example.prostoyspisok;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class UserListFragment extends Fragment{
    Button addUser;
    RecyclerView recyclerView;
    UserAdapter userAdapter;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d("SYSTEM INFO: ", "Метод onCreate() запущен");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup viewGroup, Bundle savedInstanceState){
        Log.d("SYSTEM INFO: ", "Метод onCreateView() запущен");
        View view = inflater.inflate(R.layout.fragment_user_list, viewGroup, false);
        addUser = view.findViewById(R.id.addUser);
        recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        addUser.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Log.d("SYSTEM INFO: ", "Метод onClick() запущен");
                Intent intent = new Intent(getActivity(), AddUserActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }

    private void recyclerViewInit(){
        Log.d("SYSTEM INFO: ", "Метод recyclerViewInit() запущен");
        Users users = Users.get(getActivity());
        List<User> userList = users.getUserList();
        List<String> userNameList = new ArrayList<>();
        for (User user:userList) {
            userNameList.add(user.getUserName());
        }
        userAdapter = new UserAdapter(userNameList);
        recyclerView.setAdapter(userAdapter);
    }

    @Override
    public void onResume(){
        super.onResume();
        recyclerViewInit();
        Log.d("SYSTEM INFO: ", "Метод onResume() запущен");
    }

    // UserHolder отвечает за каждый элемент списко по отдельности
    // Именно здесь мы будем наполнчть нашу activity_item контентом
    private class UserHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView itemTextView;
        String userName;
        int position;

        public UserHolder(LayoutInflater inflater, ViewGroup viewGroup){
            super(inflater.inflate(R.layout.activity_item,viewGroup,false));
            Log.d("SYSTEM INFO: ", "Метод UserHolder() запущен");
            //itemView - текущий layout activity_item
            itemTextView = itemView.findViewById(R.id.itemTextView);
            itemView.setOnClickListener(this);
        }

        public void bind(String userName, int position){
            Log.d("SYSTEM INFO: ", "Метод bind() запущен");
            this.userName = userName;
            this.position = position;
            itemTextView.setText(userName);
        }

        @Override
        public void onClick(View view){
            Log.d("SYSTEM INFO: ", "Метод onClick() запущен");
            //Toast.makeText(getActivity(),userName+" - клик!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getActivity(), UserPagerActivity.class);
            intent.putExtra("position", position);
            startActivity(intent);
        }
    }

    // UserAdapter нужен для того, чтобы разместить элементы на RecyclerView
    private class UserAdapter extends RecyclerView.Adapter<UserHolder>{
        List<String> userList;
        public UserAdapter(List<String> userList){
            this.userList = userList;
        }

        @Override
        public UserHolder onCreateViewHolder(ViewGroup viewGroup, int i){
            Log.d("SYSTEM INFO: ", "Метод onCreateViewHolder() запущен");
            // inflater - наполнитель
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            return new UserHolder(inflater,viewGroup);
        }

        @Override
        public void onBindViewHolder(UserHolder userHolder, int position){
            Log.d("SYSTEM INFO: ", "Метод onBindViewHolder() запущен");
            String userName = userList.get(position);
            userHolder.bind(userName, position);
        }

        @Override
        public int getItemCount(){
            Log.d("SYSTEM INFO: ", "Метод getItemCount() запущен");
            return userList.size();
        }
    }
}
