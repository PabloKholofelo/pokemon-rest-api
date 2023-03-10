package com.paka.pokemon.adaptor.impl;

import com.paka.pokemon.adaptor.PokemonListAdaptor;
import com.paka.pokemon.model.PokemonListResponse;
import com.paka.pokemon.models.PokemonListQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;


@Component
public class PokemonListAdaptorImpl implements PokemonListAdaptor {
    private RestTemplate restTemplate;

    @Value("${host.pokeApi}")
    private String host;
    @Autowired
    @Qualifier("pokemonRestTemplate")
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }
    @Override
    public PokemonListResponse serviceCall(PokemonListQuery request) {

        String uri = host + "/pokemon";

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(uri)
                .queryParam("offset",request.getOffset())
                .queryParam("limit",request.getLimit())
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        return restTemplate.exchange(builder.toUriString(),HttpMethod.GET, requestEntity,
                PokemonListResponse.class).getBody();
    }

}
