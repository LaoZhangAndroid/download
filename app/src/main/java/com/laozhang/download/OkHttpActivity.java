package com.laozhang.download;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.laozhang.download.viewmodel.DoubanModel;

public class OkHttpActivity extends AppCompatActivity {

    private DoubanModel doubanModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);

        doubanModel = ViewModelProviders.of(this).get(DoubanModel.class);
        doubanModel.getMovieData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Toast.makeText(OkHttpActivity.this, s, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void synchronizedGet(View view){
        doubanModel.synchronizedGet();
    }
    public void headerTest(View view){
        doubanModel.headerTest();
    }
    public void asynchronizedGet(View view){
        doubanModel.asynchronizedGet();
    }

    public void cacheGet(View view){
        doubanModel.cacheGet();
    }
}
