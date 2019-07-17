package com.tw.apistackbase;

import com.tw.apistackbase.beans.Case;
import com.tw.apistackbase.beans.CaseDetail;
import com.tw.apistackbase.beans.Procuratorate;
import com.tw.apistackbase.repository.CaseRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;
import java.util.*;

@RunWith(SpringRunner.class)
@DataJpaTest
public  class CaseRepositoryTest {
    public Long returnSeconds(int year, int month, int date) {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.clear();
        calendar.set(year,month, date);
        long secondsSinceEpoch = calendar.getTimeInMillis() / 1000L;
        return secondsSinceEpoch;
    }

    @Autowired
    CaseRepository caseRepository;

    @Before
    private void setUp() {
        List<Case> cases =new ArrayList<>();
        Case caseOne=new Case("caseA",returnSeconds(2018,9,2));
        Case caseTwo=new Case("caseB",returnSeconds(2008,9,2));
        Case caseThree=new Case("caseC",returnSeconds(2015,9,2));
        cases.add(caseOne);
        cases.add(caseTwo);
        cases.add(caseThree);
        caseRepository.saveAll(cases);
    }
   @Test
    public ResponseEntity<List<Case>> should_return_CaseList_when_add_Case()
   {
       List<Case> allCases = caseRepository.findAll();
       return ResponseEntity.ok(allCases);
   }

    @Test
    public ResponseEntity<Optional> should_return_case_by_id()
    {
        Optional fetchedCase = caseRepository.findById(1);
        return ResponseEntity.ok(fetchedCase);
    }

    @Test
    public ResponseEntity<List<Case>> should_return_case_by_caseName()
    {
        List<Case> fetchedCases = caseRepository.findByCaseName("caseA");
        return ResponseEntity.ok(fetchedCases);
    }

    @Test
    public ResponseEntity<List<Case>> should_return_all_cases_order_by_caseTime()
    {
        List<Case> fetchedCases = caseRepository.findByCaseName("caseA");
        return ResponseEntity.ok(fetchedCases);
    }

    @Test
    @Transactional
    public ResponseEntity<List<Case>> should_return_all_cases_when_delete_by_id()
    {
        caseRepository.deleteById(1);
        List<Case> fetchedCases = caseRepository.findAll();
        return ResponseEntity.ok(fetchedCases);
    }

    @Test
    public void should_return_newCases_when_add_caseDetail(){
        Case newCase = new Case("caseD",111111111);
        CaseDetail caseDetail = new CaseDetail("subjectiveDetailD","objectiveDetailD");
        newCase.setCaseDetail(caseDetail);
       Case caseSaved = (Case) caseRepository.save(newCase);
        Assert.assertEquals(caseSaved.getCaseDetail().getObjectiveDetail(),"subjectiveDetailD");
    }

    @Test
    @Transactional
    public int should_return_update_count_by_caseName(){
       return caseRepository.updateCaseCaseDetailByCaseName("caseA");
    }

    @Test
    public void should_return_newCases_when_add_procuratorate_with_caseDetail(){
        Case newCase = new Case("caseD",111111111);
        Procuratorate procuratorate = new Procuratorate("ProcuratorateD");
        newCase.setProcuratorate(procuratorate);
        Case caseSaved = (Case) caseRepository.save(newCase);
        Assert.assertEquals(caseSaved.getProcuratorate().getProcuratorateName(),"ProcuratorateD");
    }

    @Test
    @Transactional
    public int should_return_update_count_by_caseName_with_procuratorate_info(){
        return caseRepository.updateCaseProcuratorateInfoByCaseName("caseA");
    }















}
