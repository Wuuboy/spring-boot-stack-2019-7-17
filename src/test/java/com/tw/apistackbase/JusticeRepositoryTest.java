package com.tw.apistackbase;

import com.tw.apistackbase.beans.Justice;
import com.tw.apistackbase.beans.Procuratorate;
import com.tw.apistackbase.repository.JusticeRepository;
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
public class JusticeRepositoryTest {
    @Autowired
    private JusticeRepository justiceRepository;

    @Before
    public void setUp() {
        List<Justice> Inspectors = new ArrayList<Justice>() {{
            add(new Justice("JusticeA"));
            add(new Justice("JusticeB"));
            add(new Justice("JusticeC"));
        }};
        justiceRepository.saveAll(Inspectors);
    }

    @Test
    public ResponseEntity<Optional> should_return_justice_by_id()
    {
        Optional fetchedJustice = justiceRepository.findById(1);
        return ResponseEntity.ok(fetchedJustice);
    }





}
