package com.example.demo.controller;

import com.example.demo.entity.Employee;
import com.example.demo.response.EmployeeResponse;
import com.example.demo.service.EmployeeService;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import io.swagger.v3.oas.annotations.*;

@Tag(name = "EmployeeController", description = "Employee Management Service Controller")
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    ConcurrentHashMap<String,Employee> employees = new ConcurrentHashMap<>();

    @Operation(summary = "Get Employee Details with their ID")
    @GetMapping("/employees/{id}")
    private ResponseEntity<EmployeeResponse> getEmployeeDetails(@PathVariable("id") int id) {
        EmployeeResponse employee = employeeService.getEmployeeById(id);
        return ResponseEntity.status(HttpStatus.OK).body(employee);
    }

    @Operation(summary = "Get list of all employees with their details")
    @GetMapping("/employees/list")
    public List<EmployeeResponse> getAllEmployeesDetails(){
        return employeeService.getAllEmployees();
    }

    @Operation(summary = "Add new employee and their details")
    @PostMapping("/employees/addEmployeeDetails")
    public ResponseEntity<EmployeeResponse> addEmployeeDetails(@RequestBody Employee employee) {
        EmployeeResponse employeeResponse = employeeService.addEmployeeDetails(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(employeeResponse);
    }

    @Operation(summary = "Update employee details with their ID")
    @PutMapping("/employees/updateEmployeeDetails/{id}")
    ResponseEntity<EmployeeResponse> updateEmployee(@RequestBody Employee newEmployee, @PathVariable int id) {
        EmployeeResponse employeeResponse = employeeService.addOrUpdateEmployeeDetails(newEmployee, id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(employeeResponse);
    }

    @Operation(summary = "Delete employee details with their ID")
    @DeleteMapping("/employees/{id}")
    void deleteEmployee(@PathVariable int id) {
        employeeService.deleteEmployeeDetailsById(id);
    }
}