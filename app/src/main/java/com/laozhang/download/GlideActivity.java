package com.laozhang.download;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.net.URL;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;

public class GlideActivity extends AppCompatActivity {

    private ImageView imageView;

    private static final String IMAGE_URL = "http://img.mp.itc.cn/upload/20161102/93a9cfc021ed44b28af7a143be6ba017_th.jpeg";
    private static final String GIF_URL = "http://hiphotos.baidu.com/feed/pic/item/0eb30f2442a7d933b0b7b483a14bd11373f00126.jpg";
    private static final String IMAGE_URL_1 = "http://img2.imgtn.bdimg.com/it/u=142110436,2913459142&fm=26&gp=0.jpg";
    private static final String IMAGE_URL_BROKE = "http://img2.imgtn.bdimg.com/it/u=1421104dd36,2913459142&fm=26&gp=0.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);

        imageView = findViewById(R.id.image1);
    }

    public void loadImage(View view){
//        fun1();
//        fun2();
//        fun3();
//        fun4();
//        fun5();
//        fun6();
//        fun7();
//        fun8();
        fun9();
    }

    private void fun1(){
        Glide.with(this).load(IMAGE_URL).into(imageView);
    }

    private void fun2(){
        Glide.with(this).load(IMAGE_URL_1).placeholder(R.drawable.ic_image_black).into(imageView);
    }

    private void fun3(){
        Glide.with(this).load(IMAGE_URL_BROKE).diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.drawable.ic_image_black)
                .error(R.drawable.ic_broken_image_black)
                .into(imageView);
    }

    private void fun4(){
        Glide.with(this)
                .asBitmap()
                .load(GIF_URL)
                .placeholder(R.drawable.ic_image_black)
                .error(R.drawable.ic_broken_image_black)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);
    }

    private void fun5(){
        Glide.with(this)
                .asGif()
                .load(GIF_URL)
                .placeholder(R.drawable.ic_image_black)
                .error(R.drawable.ic_broken_image_black)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);
    }

    private void fun6(){
        Glide.with(this)
                .load(IMAGE_URL)
                .placeholder(R.drawable.ic_image_black)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .override(300, 300)
                .into(imageView);
    }

    private void fun7(){
        Glide.with(this)
                .load(IMAGE_URL)
                .placeholder(R.drawable.ic_image_black)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .into(imageView);
    }

    private void fun8(){
        Glide.with(this)
                .load(IMAGE_URL)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        Log.i("TAG", "加载失败");
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        Log.i("TAG", "加载成功，");
                        return false;
                    }
                })
                .into(imageView);

    }

    private void fun9(){
        Glide.with(this)
                .load(IMAGE_URL)
                .transform(new GrayscaleTransformation())
                .into(imageView);
    }


}
