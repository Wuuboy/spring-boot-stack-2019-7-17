package com.tw.apistackbase.repository;

import com.tw.apistackbase.beans.Case;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public interface CaseRepository extends JpaRepository {
    List<Case> findByCaseName(String caseA);
    List<Case> findAllOrderByCaseTimeDesc();

    @Query(" update case c set caseDetail = ? where caseName = ? ")
    int updateCaseCaseDetailByCaseName(String caseName);

    @Query(" update case c set procuratorate = ? where caseName = ? ")
    int updateCaseProcuratorateInfoByCaseName(String caseName);
}
