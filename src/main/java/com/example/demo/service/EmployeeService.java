package com.example.demo.service;

import com.example.demo.entity.Employee;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.EmployeeRepo;
import com.example.demo.response.EmployeeResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    RedisCacheManager redisCacheManager;

    @Autowired
    private ModelMapper mapper;

    @Cacheable(value = "getEmployeeByIdCache")
    public EmployeeResponse getEmployeeById(int id) {
        Optional<Employee> employee = employeeRepo.findById(id);
        return mapper.map(employee, EmployeeResponse.class);
    }

    public EmployeeResponse addEmployeeDetails(Employee employee) {
        Employee employeeInserted = employeeRepo.save(employee);
        return mapper.map(employeeInserted, EmployeeResponse.class);
    }

    public EmployeeResponse addOrUpdateEmployeeDetails(Employee employee, int id) {

        Employee updateEmployee = employeeRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not exist with id: " + id));

        updateEmployee.setName(employee.getName());
        updateEmployee.setAge(employee.getAge());
        updateEmployee.setEmail(employee.getEmail());

        employeeRepo.save(updateEmployee);
        return mapper.map(updateEmployee, EmployeeResponse.class);
    }
    public void deleteEmployeeDetailsById(int id) {
        employeeRepo.deleteById(id);
    }

    @Cacheable(value = "getAllEmployeesCache")
    public List<EmployeeResponse> getAllEmployees() {
        List<Employee> employeeList = employeeRepo.findAll();
        return employeeList
                .stream()
                .map(user -> mapper.map(user, EmployeeResponse.class))
                .collect(Collectors.toList());
    }
}
