package com.laozhang.download.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.util.Log;
import android.view.View;

import com.laozhang.download.beans.User;

/**
 * Created by zzc on 2019/3/27.
 */
public class DataBindingViewModel extends ViewModel {

    public void buttonClick(User user){
        Log.i("TAG", "buttonClick: "+user.getName());
    }

    public void toast(View view){
        Log.i("TAG",  "hello world");
    }
}
