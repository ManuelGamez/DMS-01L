package com.edu.sv.ejemplo_g9;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;

import com.edu.sv.ejemplo_g9.databinding.ItemDogBinding;

public class DogViewHolder extends RecyclerView.ViewHolder {

private ItemDogBinding itemDogBinding;
public DogViewHolder(View view) {
        super(view);
        itemDogBinding = ItemDogBinding.bind(view);
        }
public void bind(String imageUrl) {
        Picasso.get().load(imageUrl).into(itemDogBinding.ivDog);
        }
}
