package com.edu.sv.ejemplo_g9;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class DogAdapter extends RecyclerView.Adapter<DogViewHolder> {
    private List<String> images;

    public DogAdapter(List<String> images) {
        this.images = images;
    }

    @NonNull
    @Override
    public DogViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_dog, parent, false);
        return new DogViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull DogViewHolder holder, int position) {
        holder.bind(images.get(position));
    }
    @Override
    public int getItemCount() {
        return images != null ? images.size() : 0;
    }
}
