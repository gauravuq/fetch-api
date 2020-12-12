package com.gaurav.petstore.api.fetchapi.externalapis;

import com.gaurav.petstore.api.fetchapi.dtos.Pet;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@Scope(proxyMode = ScopedProxyMode.INTERFACES, value = "prototype")
public class ExternalApiImpl implements ExternalApi {
    @Override
    public ResponseEntity<List<Pet>> getAllThePets(String status) {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<Pet>> response = restTemplate.exchange(
                "https://petstore.swagger.io/v2/pet/findByStatus?status="+ status.toLowerCase(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Pet>>(){});
        return response;
    }
}
