package com.edu.sv.ejerciciog9;

import com.edu.sv.ejerciciog9.GithubAPI;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class Adapter extends ArrayAdapter<GithubRepo> {

    public Adapter(@NonNull Context context,  @NonNull List<GithubRepo> objects) {
        super(context, 0, objects);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        GithubRepo user = getItem(position);


        if (convertView == null) {

            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_user,parent,false);
        }

        TextView tvName =  convertView.findViewById(R.id.tvRepo);


        tvName.setText(user.getName());
        return convertView;



    }

}
