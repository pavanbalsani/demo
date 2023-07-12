package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EmployeeRepo;
import com.example.demo.response.EmployeeResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private ModelMapper mapper;

    public EmployeeResponse getEmployeeById(int id) {
        Optional<Employee> employee = employeeRepo.findById(id);
        EmployeeResponse employeeResponse = mapper.map(employee, EmployeeResponse.class);
        return employeeResponse;
    }

    public EmployeeResponse addEmployeeDetails(Employee employee) {
        Employee employeeInserted = employeeRepo.save(employee);
        EmployeeResponse employeeResponse = mapper.map(employeeInserted, EmployeeResponse.class);
        return employeeResponse;
    }

    public EmployeeResponse addOrUpdateEmployeeDetails(Employee employee, int id) {

        Employee updateEmployee = employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));

        updateEmployee.setName(employee.getName());
        updateEmployee.setAge(employee.getAge());
        updateEmployee.setEmail(employee.getEmail());

        employeeRepo.save(updateEmployee);
        EmployeeResponse employeeResponse = mapper.map(updateEmployee, EmployeeResponse.class);
        return employeeResponse;
    }
    public void deleteEmployeeDetailsById(int id) {
        employeeRepo.deleteById(id);
    }

    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> employeeList = employeeRepo.findAll();
        List<EmployeeResponse> employeeResponses = employeeList
                .stream()
                .map(user -> mapper.map(user, EmployeeResponse.class))
                .collect(Collectors.toList());
        return employeeResponses;
    }
}
