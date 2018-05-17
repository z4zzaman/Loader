package com.example.monir.loader.asynctask_loader;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class AsyncTaskLoaderEx<T> extends AsyncTaskLoader<T> {
    private static final AtomicInteger sCurrentUniqueId = new AtomicInteger(0);
    private T mData;
    public boolean hasResult = false;

    public static int getNewUniqueLoaderId() {
        return sCurrentUniqueId.getAndIncrement();
    }

    public AsyncTaskLoaderEx(final Context context) {
        super(context);
        onContentChanged();
    }

    @Override
    protected void onStartLoading() {
        //this part should be removed from support library 27.1.0 :
         if (hasResult && mData !=null){
             deliverResult(mData);
         }
          else if (takeContentChanged()){
              forceLoad();
             }

    }

    @Override
    public void deliverResult(final T data) {
        mData = data;
        hasResult = true;
        super.deliverResult(data);
    }

    @Override
    protected void onReset() {
        super.onReset();
        onStopLoading();
        if (hasResult) {
            onReleaseResources(mData);
            mData = null;
            hasResult = false;
        }
    }

    protected void onReleaseResources(T data) {
        //nothing to do.
    }

    public T getResult() {
        return mData;
    }
}
