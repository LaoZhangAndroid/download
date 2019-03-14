package com.laozhang.download;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;

import com.laozhang.download.adapter.PhotoListAdapter;
import com.laozhang.download.viewmodel.MainViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int CODE_PHOTO_PICK = 0;
    private MainViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView photosView = findViewById(R.id.photosView);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        photosView.setLayoutManager(layoutManager);
        PhotoListAdapter adapter = new PhotoListAdapter();
        model = ViewModelProviders.of(this).get(MainViewModel.class);
        subscribeUi(adapter);
        photosView.setAdapter(adapter);
    }

    private void subscribeUi(final PhotoListAdapter adapter) {
        model.getUris().observe(this, new Observer<List<Uri>>() {
            @Override
            public void onChanged(@Nullable List<Uri> uris) {
                adapter.submitList(uris);
            }
        });
    }

    public void addPhoto(View view){
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, CODE_PHOTO_PICK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CODE_PHOTO_PICK && resultCode==RESULT_OK && data!=null){
            Uri uri = data.getData();
            model.addPhoto(uri);
        }
    }

}
