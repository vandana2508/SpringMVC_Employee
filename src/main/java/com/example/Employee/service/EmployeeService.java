package com.example.Employee.service;

import com.example.Employee.entity.Employee;
import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    public List<Employee> findAll();

    public Employee findById(int employeeId);

    public void add(Employee employee);

    public Employee update(int employeeId,Employee employee);

    public void deleteById(int employeeId);



}
