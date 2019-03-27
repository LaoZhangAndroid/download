package com.laozhang.download;

import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.laozhang.download.beans.User;
import com.laozhang.download.databinding.ActivityDataBindingBinding;
import com.laozhang.download.viewmodel.DataBindingViewModel;

public class DataBindingActivity extends AppCompatActivity {

    private ActivityDataBindingBinding dataBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);

        DataBindingViewModel model = ViewModelProviders.of(this).get(DataBindingViewModel.class);
        User user = new User();
        user.setName("hanmeimei");
        user.setAge(11);
        dataBinding.setUser(user);
        dataBinding.setModel1(model);
    }

    public void toastHello(View view){
        Toast.makeText(this, "hello world", Toast.LENGTH_SHORT).show();
    }
}
