package com.paka.pokemon.adaptor.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paka.pokemon.adaptor.PokemonListAdaptor;
import com.paka.pokemon.model.PokemonListResponse;
import com.paka.pokemon.models.ApiException;
import com.paka.pokemon.models.PokemonListQuery;
import com.sun.jndi.toolkit.url.Uri;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Component
public class PokemonListAdaptorImpl implements PokemonListAdaptor {
    private RestTemplate restTemplate;

    @Autowired
    @Qualifier("pokemonRestTemplate")
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public PokemonListResponse serviceCall(PokemonListQuery request) {
        String uri = "https://pokeapi.co/api/v2/pokemon";


        UriComponents builder = UriComponentsBuilder.fromHttpUrl(uri)
                .queryParam("offset",request.getOffset())
                .queryParam("limit",request.getLimit())
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        headers.add("Content-Type", "application/json");
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        PokemonListResponse strResponse = restTemplate.exchange(builder.toUriString(),HttpMethod.GET, requestEntity,
                PokemonListResponse.class).getBody();

        return strResponse;

    }

}
