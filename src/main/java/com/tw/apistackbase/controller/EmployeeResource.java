package com.tw.apistackbase.controller;

import com.tw.apistackbase.model.Employee;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

import static java.lang.Integer.parseInt;
import static java.lang.Math.random;
import static java.lang.String.valueOf;

/**
 * Created by jxzhong on 18/08/2017.
 */
@RestController
@RequestMapping("/employees")
public class EmployeeResource {

    private final Logger log = Logger.getLogger(this.getClass().getName());
    private List<Employee> employeeList = new ArrayList();

    @GetMapping(path = "")
    public List<Employee> getAll() {
        return employeeList;
    }

    @PostMapping(path = "")
    public Employee addEmployee(@RequestBody Employee employee) {
        int employeeID = parseInt(valueOf(new Random().nextInt()));

        employee.setId(employeeID);

        employeeList.add(employee);
        return employee;
    }

    @DeleteMapping(path = "/{id}")
    public Employee deleteEmployee(@PathVariable int id){
        Employee deleteEmployee = employeeList.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .orElse(null);
        employeeList.remove(deleteEmployee);
        return deleteEmployee;
    }

    @PutMapping(path = "/{id}")
    public Employee updateEmployee(@PathVariable int id, @RequestBody Employee employee){
        Employee updateEmployee = employeeList.stream()
                .filter(emp -> emp.getId() == id)
                .findFirst()
                .orElse(null);

        employee.setId(id);

        employeeList.remove(updateEmployee);

        employeeList.add(employee);
        return updateEmployee;
    }
}
