package com.gaurav.petstore.api.fetchapi.services;

import com.gaurav.petstore.api.fetchapi.dtos.Pet;
import com.gaurav.petstore.api.fetchapi.externalapis.ExternalApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS, value = "prototype")
public class PetStoreFetchApiService {

    @Autowired
    ExternalApi externalApi;
    private static Logger logger = LoggerFactory.getLogger(PetStoreFetchApiService.class);
    private List<Pet> pets;
    private Map<Long, List<Pet>> groupedPets;


    public PetStoreFetchApiService() {
    }

    public void fetchThePetsBasedOnTheProvidedStatusAndSortOrder(String status, String order) {
        validateParameters(status, order);
        fetchThePets(status);
        groupThePetsBasedOnTheirCategory();
        sortThePetsInEachCategoryBasedOnTheGivenSortOrder(order);
        printTheGroupedPets();
    }

    private void validateParameters(String status, String order) {
        if (!(status.equalsIgnoreCase("sold") || status.equalsIgnoreCase("pending") || status.equalsIgnoreCase("available"))) {
            throw new RuntimeException("Wrong Pet Status Type Given. Valid Keywords are SOLD or PENDING or AVAILABLE ");
        }
        if (!(order.equalsIgnoreCase("asc") || order.equalsIgnoreCase("desc"))) {
            throw new RuntimeException("Wrong Pet Sort Order Given. Valid Keywords are ASC or DESC");
        }
    }

    private void fetchThePets(String status) {
        pets = externalApi.getAllThePets(status).getBody();
    }

    private void groupThePetsBasedOnTheirCategory() {
        groupedPets = pets.stream()
                .filter(pet -> pet.getCategory() != null) // check for Pets whose category is not defined
                .collect(Collectors.groupingBy(pet -> pet.getCategory().getId(), TreeMap::new, Collectors.toCollection(ArrayList::new)));
        List<Pet> petsWithNoCategory = pets.stream().filter(pet -> pet.getCategory() == null).collect(Collectors.toList());
        groupedPets.put(Long.MIN_VALUE, petsWithNoCategory);
    }

    private void sortThePetsInEachCategoryBasedOnTheGivenSortOrder(String order) {
        if (order.equalsIgnoreCase("ASC")) {
            for (Map.Entry<Long, List<Pet>> petCategory : groupedPets.entrySet()) {
                groupedPets.put(petCategory.getKey(), petCategory.getValue().stream().sorted(Comparator.comparing(Pet::getName)).collect(Collectors.toList()));
            }
        } else if (order.equalsIgnoreCase("DESC")) {
            for (Map.Entry<Long, List<Pet>> petCategory : groupedPets.entrySet()) {
                groupedPets.put(petCategory.getKey(), petCategory.getValue().stream().sorted(Comparator.comparing(Pet::getName).reversed()).collect(Collectors.toList()));
            }
        }
    }

    private void printTheGroupedPets() {
        groupedPets.forEach((x, y) -> {
            logger.info("\n");
            logger.info("{}::{}", x, y);
        });
    }

}
