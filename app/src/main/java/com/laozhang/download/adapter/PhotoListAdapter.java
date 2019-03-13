package com.laozhang.download.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.laozhang.download.R;

import java.util.ArrayList;
import java.util.List;

public class PhotoListAdapter extends RecyclerView.Adapter<PhotoListAdapter.ViewHolder> {

    private List<Uri> uris;

    public void addPhoto(Uri uri){
        if(uris == null) uris = new ArrayList<>();
        uris.add(uri);
        notifyItemInserted(uris.size()-1);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_photo, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Glide.with(viewHolder.itemView.getContext()).load(uris.get(i)).into(viewHolder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        return uris==null ? 0 : uris.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgPhoto;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.photo);
        }
    }

}


