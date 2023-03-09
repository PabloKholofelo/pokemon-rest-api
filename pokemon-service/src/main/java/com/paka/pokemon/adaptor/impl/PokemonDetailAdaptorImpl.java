package com.paka.pokemon.adaptor.impl;

import com.paka.pokemon.adaptor.PokemonDetailAdaptor;
import com.paka.pokemon.model.PokemonDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Component
public class PokemonDetailAdaptorImpl implements PokemonDetailAdaptor {

    private RestTemplate restTemplate;

    @Value("${host.pokeApi}")
    private String host;

    @Autowired
    @Qualifier("pokemonRestTemplate")
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public PokemonDetail serviceCall(String name) {
        String uri = host + "/pokemon/{name}";

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(uri)
                .build();
        Map<String, String> params = new HashMap<String, String>();
        params.put("name", name);

        HttpHeaders headers = new HttpHeaders();
        headers.add("user-agent", "Application");
        headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, requestEntity,
                PokemonDetail.class, params).getBody();

    }
}
