package com.example.monir.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.FileObserver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JsonAsyncTaskLoader extends
        AsyncTaskLoader<List<String>> {
    // You probably have something more complicated
    // than just a String. Roll with me
    private List<String> mData;
    private FileObserver mFileObserver;
    public JsonAsyncTaskLoader(Context context) {
        super(context);
    }
    @Override
    protected void onStartLoading() {
        if (mData != null) {
            // Use cached data
            deliverResult(mData);
        }
        if (mFileObserver == null) {
            String path = new File(
                    getContext().getFilesDir(), "downloaded.json").getPath();
            mFileObserver = new FileObserver(path) {
                @Override
                public void onEvent(int event, String path) {
                    // Notify the loader to reload the data
                    onContentChanged();
                    // If the loader is started, this will kick off
                    // loadInBackground() immediately. Otherwise,
                    // the fact that something changed will be cached
                    // and can be later retrieved via takeContentChanged()
                }
            };
            mFileObserver.startWatching();
        }
        if (takeContentChanged() || mData == null) {
            // Something has changed or we have no data,
            // so kick off loading it
            forceLoad();
        }
    }
    @Override
    public List<String> loadInBackground() {
        // This is on a background thread
        File jsonFile = new File(
                getContext().getFilesDir(), "downloaded.json");
        List<String> data = new ArrayList<>();
        // Parse the JSON using the library of your choice
        return data;
    }
    @Override
    public void deliverResult(List<String> data) {
        // We’ll save the data for later retrieval
        mData = data;
        // We can do any pre-processing we want here
        // Just remember this is on the UI thread so nothing lengthy!
        super.deliverResult(data);
    }
    protected void onReset() {
        // Stop watching for file changes
        if (mFileObserver != null) {
            mFileObserver.stopWatching();
            mFileObserver = null;
        }
    }
}

/*
public  class JsonAsyncTaskLoader extends
        AsyncTaskLoader<List<String>> {
    // You probably have something more complicated
    // than just a String. Roll with me
    private List<String> mData;
    public JsonAsyncTaskLoader(Context context) {
        super(context);
    }
    @Override
    protected void onStartLoading() {
        if (mData != null) {
            // Use cached data
            deliverResult(mData);
        } else {
            // We have no data, so kick off loading it
            forceLoad();
        }
    }
    @Override
    public List<String> loadInBackground() {
        // This is on a background thread
        // Good to know: the Context returned by getContext()
        // is the application context
        File jsonFile = new File(
                getContext().getFilesDir(), "downloaded.json");
        List<String> data = new ArrayList<>();
        // Parse the JSON using the library of your choice
        // Check isLoadInBackgroundCanceled() to cancel out early
        return data;
    }
    @Override
    public void deliverResult(List<String> data) {
        // We’ll save the data for later retrieval
        mData = data;
        // We can do any pre-processing we want here
        // Just remember this is on the UI thread so nothing lengthy!
        super.deliverResult(data);
    }
}*/
