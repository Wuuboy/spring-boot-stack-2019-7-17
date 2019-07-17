package com.tw.apistackbase;

import com.tw.apistackbase.beans.Case;
import com.tw.apistackbase.beans.CaseDetail;
import com.tw.apistackbase.repository.CaseDetailRepository;
import com.tw.apistackbase.repository.CaseRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CaseDetailRepositoryTest {

    @Autowired
    private CaseDetailRepository caseDetailRepository;

    @Before
    public void setUp(){
        List<CaseDetail> caseDetails = new ArrayList<CaseDetail>() {{
            add(new CaseDetail("subjectiveDetailA", "objectiveDetailA"));
            add(new CaseDetail("subjectiveDetailB", "objectiveDetailB"));
            add(new CaseDetail("subjectiveDetailAC", "objectiveDetailC"));
        }};
        caseDetailRepository.saveAll(caseDetails);
    }

    @Autowired
    CaseRepository caseRepository;

    @Test
    public void should_return_caseDetail_by_id()
    {
        Optional caseDetail = caseRepository.findById(1);
        Assert.assertNotEquals(null,caseDetail.get());
    }


}
