package com.tw.apistackbase.controller;

//import com.tw.apistackbase.beans.Company;
//import com.tw.apistackbase.beans.Employee;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class EmployeeController {
    private List<Employee>employeeList = new ArrayList<>();

    @PostMapping("/employees")
    public ResponseEntity addEmployee(@RequestBody Employee employee){
        employee.setId((int) (Math.random()*100));
        employeeList.add(employee);
        return ResponseEntity.ok(employeeList);
    }

    @GetMapping("/employees")
    public ResponseEntity getEmplyees(){
        return ResponseEntity.ok(employeeList);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity getEmployee(@PathVariable Long id){
        Employee employee = employeeList.stream().filter(c -> c.getId()==id)
                .findFirst()
                .orElse(null);
        if (employee != null) {
            return ResponseEntity.ok(employee);
        }
        return ResponseEntity.ok(null);
    }

    @GetMapping("/employees?page={page}&pageSize={pageSize}")
    public ResponseEntity getEmployeesWithPage(@PathVariable Integer page,@PathVariable Integer pageSize){
        List<Employee>employeeListResult = new ArrayList<>();
        for (int i=0;i<pageSize;i++){
            employeeListResult.add(employeeList.get((page-1)*pageSize+i));
        }
        return ResponseEntity.ok(employeeListResult);
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity deleteCompanyById(@PathVariable Long id){
        Employee employee = employeeList.stream().filter(c -> c.getId()==id)
                .findFirst()
                .orElse(null);
        if (employee != null) {
            employeeList.remove(employee);
            return ResponseEntity.ok(employeeList);
        }
        return ResponseEntity.ok(employeeList);
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity updateCompany(@PathVariable Long id,@RequestBody Employee employee){
        Employee findedEmployee = employeeList.stream().filter(c -> c.getId()==id)
                .findFirst()
                .orElse(null);
        if (findedEmployee != null) {
            BeanUtils.copyProperties(employee,findedEmployee);
            return ResponseEntity.ok(findedEmployee);
        }
        return ResponseEntity.ok(null);
    }

}