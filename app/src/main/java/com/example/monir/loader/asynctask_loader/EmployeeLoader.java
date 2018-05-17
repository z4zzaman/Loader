package com.example.monir.loader.asynctask_loader;

import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class EmployeeLoader  extends AsyncTaskLoader<List<Employee>> {
    public EmployeeLoader(Context context) {
        super(context);
    }
    @Override
    public List<Employee> loadInBackground() {
        List<Employee> list = new ArrayList<Employee>();
        list.add(new Employee("emp1", "Brahma"));
        list.add(new Employee("emp2", "Vishnu"));
        list.add(new Employee("emp3", "Mahesh"));
        return list;
    }
}
