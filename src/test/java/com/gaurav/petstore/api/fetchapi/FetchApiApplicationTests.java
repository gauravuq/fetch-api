package com.gaurav.petstore.api.fetchapi;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
class FetchApiApplicationTests {

    private static Logger logger = LoggerFactory.getLogger(FetchApiApplicationTests.class);

    @Test
    public void firstSampleTest(){

        logger.info("First Sample Test");
    }

}
