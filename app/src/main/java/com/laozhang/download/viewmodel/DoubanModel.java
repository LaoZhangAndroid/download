package com.laozhang.download.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.util.Log;

import com.laozhang.download.repository.DoubanRepository;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DoubanModel extends ViewModel {

    private DoubanRepository repository;
    private MutableLiveData<String> movieData;

    public DoubanModel(){
            repository = new DoubanRepository();
            movieData = new MutableLiveData<>();
    }

    public LiveData<String> getMovieData(){
        return movieData;
    }

    public void synchronizedGet(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String movies = repository.synchronizedGet();
                    movieData.postValue(movies);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void asynchronizedGet(){
        repository.AsynchronizedGet(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                movieData.postValue(e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.i("TAG", "thread: "+Thread.currentThread().getName());
                if(response.isSuccessful()){
                    movieData.postValue(response.body().string());
                }else {
                    movieData.postValue(response.message());
                }
            }
        });
    }

    public void headerTest(){
        repository.headerTest();
    }

    public void cacheGet(){
       new Thread(new Runnable() {
           @Override
           public void run() {
               try {
                   repository.cacheGet();
               } catch (IOException e) {
                   e.printStackTrace();
               }
           }
       }).start();
    }

}
