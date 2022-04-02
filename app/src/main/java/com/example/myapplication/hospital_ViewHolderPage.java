package com.example.myapplication;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class hospital_ViewHolderPage extends RecyclerView.ViewHolder {
    private TextView tv_title;
    private RelativeLayout rl_layout;


    hospital_ViewHolderPage(View itemView) {
        super(itemView);
        tv_title = itemView.findViewById(R.id.tv_title);
        rl_layout = itemView.findViewById(R.id.rl_layout);
    }
}

