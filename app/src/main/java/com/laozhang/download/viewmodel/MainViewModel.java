package com.laozhang.download.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.net.Uri;

import com.laozhang.download.repository.UriRepository;

import java.util.List;

public class MainViewModel extends ViewModel {

    private UriRepository uriRepository;
    private MutableLiveData<List<Uri>> mUris;

    public MainViewModel(UriRepository uriRepository){
        this.uriRepository = uriRepository;
        mUris = new MutableLiveData<>();
    }

    public void addPhoto(Uri uri){
        uriRepository.addUri(uri);
        mUris.setValue(uriRepository.getUris());
    }

    public LiveData<List<Uri>> getUris(){
        return mUris;
    }

}
