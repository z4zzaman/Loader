package com.example.monir.loader.asynctask_loader;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.monir.loader.R;

import java.util.ArrayList;
import java.util.List;

public class EmployeeAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<Employee> employees = new ArrayList<Employee>();
    public EmployeeAdapter(Context context, List<Employee> employees) {
        this.employees = employees;
        inflater = LayoutInflater.from(context);
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        Employee emp = (Employee) getItem(position);
        if (view == null) {
            view = inflater.inflate(R.layout.employeedata, null);
        }
        TextView empid = (TextView) view.findViewById(R.id.empid);
        empid.setText(emp.empid);
        TextView empname = (TextView) view.findViewById(R.id.empname);
        empname.setText(emp.name);
        return view;
    }
    @Override
    public Object getItem(int position) {
        return employees.get(position);
    }
    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public int getCount() {
        return employees.size();
    }
    public void setEmployees(List<Employee> data) {
        employees.addAll(data);
        notifyDataSetChanged();
    }
}
