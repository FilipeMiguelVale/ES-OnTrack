package com.example.cucumber;

import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

//@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = { ParkingLotsServiceApplication.class, H2TestProfileJPAConfig.class})
@CucumberContextConfiguration
@ContextConfiguration
@DirtiesContext

class CucumberApplicationTests {


}
