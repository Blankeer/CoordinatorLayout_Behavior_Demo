package com.blanke.coordinatorlayout_behavior_demo;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private CoordinatorLayout mainCoordinatorlayout;
    private Toolbar toolbar;
    private RecyclerView mainRecyclerview;
    private BottomNavigationView mainBottomBar;
    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainCoordinatorlayout = (CoordinatorLayout) findViewById(R.id.main_coordinatorlayout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        mainRecyclerview = (RecyclerView) findViewById(R.id.main_recyclerview);
        mainBottomBar = (BottomNavigationView) findViewById(R.id.main_bottomBar);
        setSupportActionBar(toolbar);
        List<String> contents = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            contents.add("item " + i);
        }
        mAdapter = new MyAdapter(contents);
        mainRecyclerview.setAdapter(mAdapter);
        mainRecyclerview.setLayoutManager(new LinearLayoutManager(this));

    }

    static class MyAdapter extends RecyclerView.Adapter<MyVh> {
        private List<String> contents;

        public MyAdapter(List<String> contents) {
            this.contents = contents;
        }

        @Override
        public MyVh onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyVh(View.inflate(parent.getContext(),
                    android.R.layout.simple_list_item_1, null));
        }

        @Override
        public int getItemCount() {
            return contents == null ? 0 : contents.size();
        }

        @Override
        public void onBindViewHolder(MyVh holder, int position) {
            holder.textView.setText(contents.get(position));
        }
    }

    static class MyVh extends RecyclerView.ViewHolder {
        TextView textView;

        public MyVh(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(android.R.id.text1);
        }
    }

}
