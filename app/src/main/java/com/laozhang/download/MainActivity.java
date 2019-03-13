package com.laozhang.download;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;

import com.laozhang.download.adapter.PhotoListAdapter;

public class MainActivity extends AppCompatActivity {

    private static final int CODE_PHOTO_PICK = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView photosView = findViewById(R.id.photosView);
        photosView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        PhotoListAdapter adapter = new PhotoListAdapter();
        subscribeUi();
        photosView.setAdapter(adapter);
    }

    private void subscribeUi() {
//        MainViewModel model = ViewModelProvider
    }

    public void addPhoto(View view){
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, CODE_PHOTO_PICK);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CODE_PHOTO_PICK && resultCode==RESULT_OK && data!=null){
            Uri uri = data.getData();
        }
    }
}
