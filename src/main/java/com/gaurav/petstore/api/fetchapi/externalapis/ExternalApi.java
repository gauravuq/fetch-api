package com.gaurav.petstore.api.fetchapi.externalapis;

import com.gaurav.petstore.api.fetchapi.dtos.Pet;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ExternalApi {
    public ResponseEntity<List<Pet>> getAllThePets(String status);
}
