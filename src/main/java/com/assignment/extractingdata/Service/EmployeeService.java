package com.assignment.extractingdata.Service;

import com.assignment.extractingdata.Entity.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {
    public void  saveData();
    public List<Employee> readData();

}
