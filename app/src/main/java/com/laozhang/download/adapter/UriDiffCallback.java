package com.laozhang.download.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.util.DiffUtil;

/**
 * Created by zzc on 2019/3/14.
 */
class UriDiffCallback extends DiffUtil.ItemCallback<Uri>{

    @Override
    public boolean areItemsTheSame(@NonNull Uri uri, @NonNull Uri t1) {
        return false;
    }

    @Override
    public boolean areContentsTheSame(@NonNull Uri uri, @NonNull Uri t1) {
        return false;
    }
}

