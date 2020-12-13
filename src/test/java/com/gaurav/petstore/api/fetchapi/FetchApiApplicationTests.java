package com.gaurav.petstore.api.fetchapi;

import com.gaurav.petstore.api.fetchapi.services.PetStoreService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(args = {"available","DESC"})
@RunWith(SpringJUnit4ClassRunner.class)
public class FetchApiApplicationTests {

    @Autowired
    PetStoreService petStoreService;
    private static Logger logger = LoggerFactory.getLogger(FetchApiApplicationTests.class);

    // Integration Test
    @Test
    public void e2eTest(){
        logger.info("First Integration Test");
        petStoreService.fetchThePetsBasedOnTheirStatusAndSortOrder("available","DESC");
    }

    // Error Test 1
    @Test(expected = RuntimeException.class)
    public void e2eTestPetStatusError(){
        logger.info("Second Error Test: Expect Error to be thrown due to wrong Pet Status");
        petStoreService.fetchThePetsBasedOnTheirStatusAndSortOrder("availabl","DESC");
    }

    // Error Test 2
    @Test(expected = RuntimeException.class)
    public void e2eTestPetSortOrderError(){
        logger.info("Third Error Test: Expect Error to be thrown due to wrong Pet Sort Order");
        petStoreService.fetchThePetsBasedOnTheirStatusAndSortOrder("available","DES");
    }

}
