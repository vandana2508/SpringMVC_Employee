package com.example.Employee.service;

import com.example.Employee.dao.EmployeeRepository;
import com.example.Employee.entity.Employee;
import com.example.Employee.exception.EmployeeNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImplementation implements EmployeeService{

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImplementation(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }
    @Override
    public List<Employee> findAll() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    @Override
    public Employee findById(int employeeId) {
        Optional<Employee> result = employeeRepository.findById(employeeId);
        if(result.isEmpty()) {
            throw new EmployeeNotFoundException("Employee Not Found");
        }
        Employee employee = result.get();
        return employee;
    }

    @Override
    public void add(Employee employee) {

        employeeRepository.save(employee);

    }

    @Override
    public Employee update(int employeeId, Employee employee) {

        Optional<Employee> result = employeeRepository.findById(employeeId);
        if(result.isEmpty()){
            throw new EmployeeNotFoundException("Employee Not Found");
        }
        Employee theEmployee = result.get();
        theEmployee.setFirstName(employee.getFirstName());
        theEmployee.setLastName(employee.getLastName());
        theEmployee.setEmail(employee.getEmail());
        employeeRepository.save(theEmployee);
        return theEmployee;
    }

    @Override
    public void deleteById(int employeeId) {
        employeeRepository.deleteById(employeeId);
    }
}
