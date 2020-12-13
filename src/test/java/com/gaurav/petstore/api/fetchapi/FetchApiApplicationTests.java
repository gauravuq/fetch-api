package com.gaurav.petstore.api.fetchapi;

import com.gaurav.petstore.api.fetchapi.services.PetStoreService;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest(args = {"available","DESC"})
public class FetchApiApplicationTests {

    @Autowired
    PetStoreService petStoreService;
    private static Logger logger = LoggerFactory.getLogger(FetchApiApplicationTests.class);

    // Integration Test
    @Test
    public void e2eTest(){
        logger.info("Integration Test");
        assertEquals("Success",petStoreService.fetchThePetsBasedOnTheirStatusAndSortOrder("available","DESC"));
    }

    // Error Test 1
    @Test
    public void e2eTestPetStatusError(){
        logger.info("Error Test: Expect Error to be thrown due to wrong Pet Status");
        assertThrows(RuntimeException.class,()->{
            petStoreService.fetchThePetsBasedOnTheirStatusAndSortOrder("availabl","DES");
        });
    }

    // Error Test 2
    @Test
    public void e2eTestPetSortOrderError(){
        logger.info("Error Test: Expect Error to be thrown due to wrong Pet Sort Order");
        assertThrows(RuntimeException.class,()->{
            petStoreService.fetchThePetsBasedOnTheirStatusAndSortOrder("available","DES");
        });

    }

}
