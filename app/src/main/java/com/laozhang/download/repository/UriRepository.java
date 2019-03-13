package com.laozhang.download.repository;
import android.net.Uri;

import java.util.ArrayList;
import java.util.List;

public class UriRepository {

    private List<Uri> uris;

    public void addUri(Uri uri){
        if(uris==null) uris = new ArrayList<>();
        uris.add(uri);
    }

    public List<Uri> getUris(){
        if(uris==null) uris = new ArrayList<>();
        return uris;
    }
}
