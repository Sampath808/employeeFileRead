package com.assignment.extractingdata.Controller;

import com.assignment.extractingdata.Entity.Employee;
import com.assignment.extractingdata.Service.EmployeeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }
    @GetMapping("/saveData")
   public void saveData(){
        employeeService.saveData();
   }
}
