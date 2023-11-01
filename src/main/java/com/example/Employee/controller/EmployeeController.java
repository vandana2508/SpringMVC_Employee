package com.example.Employee.controller;

import com.example.Employee.entity.Employee;
import com.example.Employee.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/employees")
@Controller
public class EmployeeController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping("/list")
    public String listEmployees(Model model){
        model.addAttribute("employees",employeeService.findAll());
        return "list-employees";
    }

    @GetMapping("/addEmployee")
    public String showAddForm(Model model){

        Employee employee = new Employee();
        model.addAttribute("employee",employee);
        return "add-employee";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee){
        employeeService.add(employee);
        return "redirect:/employees/list";
    }

    @GetMapping("/updateEmployee")
    public String showUpdateForm(@RequestParam("employeeId") int employeeId, Model model){

        Employee employee=employeeService.findById(employeeId);
        model.addAttribute("employee",employee);
        return "update-employee";
    }

    @PostMapping("/update")
    public String updateEmployee(@ModelAttribute("employee") Employee employee){
        System.out.println("Employee:"+employee.toString());
        employeeService.update(employee.getId(), employee);
        return "redirect:/employees/list";
    }

    @GetMapping("/deleteEmployee")
    public String deleteEmployee(@RequestParam("employeeId") int employeeId){
        employeeService.deleteById(employeeId);
        return "redirect:/employees/list";
    }

}
