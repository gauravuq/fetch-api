package com.gaurav.petstore.api.fetchapi;

import com.gaurav.petstore.api.fetchapi.services.PetStoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
* Instructions
     Browse Swagger documentation at https://petstore.swagger.io/
     Develop a console application to execute the required sample API(s) to return a list of available Pets from the Pet Store
     Print a list of available Pets to the console sorted in Categories and displayed in reverse order by Name
     Write at least one unit test for your code and deliver your code (with instructions in the form a Readme file in markdown) for review
* */

/*
 * Command line Run command : java -jar fetch-api-0.0.1-SNAPSHOT.jar Available Ascending
 * */
@SpringBootApplication
public class FetchApiApplication implements CommandLineRunner {

    private static Logger logger = LoggerFactory.getLogger(FetchApiApplication.class);

    @Autowired
    PetStoreService petStoreService;

    public static void main(String[] args) {
        SpringApplication.run(FetchApiApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        try {
            if (args.length < 2)
                throw new RuntimeException("Please provide both Pet Status Type and Pet Sort Order parameters for this console application to work");
            logger.info("Pet Status Type being used::{}", args[0]);
            logger.info("Pet Sort Order for each category of pet::{}", args[1]);
            petStoreService.fetchThePetsBasedOnTheProvidedStatusAndSortOrder(args[0], args[1]);
        } catch (Exception exception) {
            logger.info("Error===>{}", exception.getMessage());
        }
    }
}
