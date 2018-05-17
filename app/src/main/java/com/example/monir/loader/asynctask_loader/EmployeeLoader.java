package com.example.monir.loader.asynctask_loader;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import android.util.Log;

public class EmployeeLoader  extends AsyncTaskLoaderEx<List<Employee>> {
    private List<Employee> mData;


    public EmployeeLoader(Context context) {
        super(context);
    }

/*    @Override
    protected void onStartLoading() {


        if (mData != null) {
        // Use cached data
        deliverResult(mData);
        }else{
            forceLoad();
        }
        if(takeContentChanged() && mData == null){
            // Something has changed or we have no data,
            // so kick off loading it
             Log.e("content", "content changed");
           forceLoad();
           }

    }*/

    @Override
    public List<Employee> loadInBackground() {
        Log.e("visited", "first time");
        List<Employee> list = new ArrayList<Employee>();
        list.add(new Employee("emp1", "Anjan"));
        list.add(new Employee("emp2", "Shameem"));
        list.add(new Employee("emp3", "Sudipto"));
        list.add(new Employee("emp4", "Tariqul"));
        list.add(new Employee("emp3", "Monir"));
        return list;
    }

/*    @Override
    public void deliverResult(List<Employee> data) {
        Log.e("called", "every time");
        // Here save the data for later retrieval
        mData = data;
        // We can do any pre-processing we want here
        // Just remember this is on the UI thread so nothing lengthy!
        super.deliverResult(data);
     }*/
   }
