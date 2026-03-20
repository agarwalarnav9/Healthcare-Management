package com.moon.project_two.ServiceHelper;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.moon.project_two.Entity.Insurance;
import com.moon.project_two.Service.InsuranceService;

@SpringBootTest
public class InsuranceServiceTest {

    @Autowired
    private InsuranceService insuranceService; 


    @Test
    public void addInsuranceTest(){

        Insurance insurance = new Insurance();
        insurance.setProvider("SBI");
        insurance.setPolicyNumber("1234");
        insurance.setValidity(LocalDate.of(2028,12,12));

        insuranceService.addInsurance(insurance, 3L);

    }

}
