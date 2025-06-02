package com.example.PayslipGenerator.Service;

import com.example.PayslipGenerator.DTO.EmployeeRequest;
import com.example.PayslipGenerator.Model.Employee;
import com.example.PayslipGenerator.Repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(EmployeeRequest request) {
        Employee employee = new Employee();
        employee.setFullName(request.getFullName());
        employee.setEmail(request.getEmail());
        employee.setPhoneNumber(request.getPhoneNumber());
        employee.setAddress(request.getAddress());
        employee.setDesignation(request.getDesignation());
        employee.setJoiningDate(request.getJoiningDate());
        employee.setBasicSalary(request.getBasicSalary());
        employee.setHra(request.getHra());
        employee.setSpecialAllowance(request.getSpecialAllowance());
        employee.setPf(request.getPf());
        employee.setPt(request.getPt());
        employee.setTds(request.getTds());
        employee.setPANNumber(request.getPANNumber());
        employee.setBankAccountNumber(request.getBankAccountNumber());
        employee.setIfscCode(request.getIfscCode());
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }

    public void deleteEmployeeById(Long id){
        employeeRepository.deleteById(id);
    }

    public Employee updateEmployee(Long id, EmployeeRequest request) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found with ID: " + id));

        employee.setFullName(request.getFullName());
        employee.setEmail(request.getEmail());
        employee.setPhoneNumber(request.getPhoneNumber());
        employee.setAddress(request.getAddress());
        employee.setDesignation(request.getDesignation());
        employee.setJoiningDate(request.getJoiningDate());
        employee.setBasicSalary(request.getBasicSalary());
        employee.setHra(request.getHra());
        employee.setSpecialAllowance(request.getSpecialAllowance());
        employee.setPf(request.getPf());
        employee.setPt(request.getPt());
        employee.setTds(request.getTds());
        employee.setPANNumber(request.getPANNumber());
        employee.setBankAccountNumber(request.getBankAccountNumber());
        employee.setIfscCode(request.getIfscCode());

        return employeeRepository.save(employee);
    }
}

