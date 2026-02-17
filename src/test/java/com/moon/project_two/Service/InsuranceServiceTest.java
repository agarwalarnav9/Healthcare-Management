package com.moon.project_two.Service;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.moon.project_two.Entity.Insurance;

@SpringBootTest
public class InsuranceServiceTest {

    @Autowired
    private InsuranceService insuranceService; 


    @Test
    public void addInsuranceTest(){

        Insurance insurance = new Insurance(null,"SBI", "1234",LocalDate.of(2028,12,12), null,null);

        insuranceService.addInsurance(insurance, 3L);

    }

}
