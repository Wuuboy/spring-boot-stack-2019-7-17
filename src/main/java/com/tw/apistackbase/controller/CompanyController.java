package com.tw.apistackbase.controller;


import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CompanyController {
   private List<Company> companies=new ArrayList<Company>();
   private List<Employee> employees=new ArrayList<>();

    @Autowired
    private databaseInterface databaseInterface;



    private String companyName;
    private Company company1=new Company("huawei");
    private Company company2=new Company("vivo");



    private Employee employee1=new Employee(1,"zhangsan",20,"male","10000");
    private Employee employee2=new Employee(2,"lisi",25,"female","9000");


//    @PostMapping("/companies")
//    public ResponseEntity addCompany(@RequestBody Company company)
//    {
//          companies.add(company);
//          return ResponseEntity.ok(companies);
//    }



    @GetMapping("/companies")
    public ResponseEntity getCompanyList()
    {
        return ResponseEntity.ok(companies);
    }


    @GetMapping("/companies/{companyName}")
    public ResponseEntity getEmployeesOfSpecialCompany(@PathVariable String companyName)
    {
        Company company1 = companies.stream().filter(c -> c.getCompanyName() == companyName)
                .findFirst()
                .orElse(null);
        if (company1 != null) {
            return ResponseEntity.ok(company1);
        }
        return ResponseEntity.ok(null);
    }



    @GetMapping("/companies?page={page}&pageSize={pageSize}")
    public ResponseEntity getCompaniesWithPage(@PathVariable Integer page,@PathVariable Integer pageSize){
        List<Company>companyListResult = new ArrayList<>();
        for (int i=0;i<pageSize;i++){
            companyListResult.add(companies.get((page-1)*pageSize+i));
        }
        return ResponseEntity.ok(companyListResult);
    }

    @DeleteMapping("/companies/{companyName}")
    public ResponseEntity deleteCompanyById(@PathVariable String companyName){
        Company company3 = companies.stream().filter(c -> c.getCompanyName()==companyName)
                .findFirst()
                .orElse(null);
        if (company3 != null) {
            companies.remove(company3);
            return ResponseEntity.ok(null);
        }

        return ResponseEntity.ok(companies);
    }



    @PutMapping("/companies/{companyName}")
    public ResponseEntity updateCompany(@PathVariable String companyName,@RequestBody Company company){
        Company company4 = companies.stream().filter(c -> c.getCompanyName()==companyName)
                .findFirst()
                .orElse(null);
        if (company4 != null) {
//            company4.setCompanyName("NewNamedCompany");

            BeanUtils.copyProperties(company,company4);
            return ResponseEntity.ok(company4);
        }
        return ResponseEntity.ok(null);
    }







    @PostMapping("/companies")
    public ResponseEntity<Company> saveCompaniesToH2(@RequestBody Company company)
    {
         List<Company> companies=new ArrayList();
         Company company1=new Company("01","20","huaweiNew");
         Company company2=new Company("02","30","meizuNew");
         companies.add(company1);
         companies.add(company2);
         databaseInterface.saveAll(companies);
         return (ResponseEntity<Company>) companies;
    }



//    @GetMapping("/companies/{companyId}")
//    public ResponseEntity<Company> findByCompaniesByID(@PathVariable  String companyId)
//    {
//        Company companyfined = databaseInterface.findAllById()
//    }


}
