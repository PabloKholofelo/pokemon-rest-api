package com.paka.pokemon;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.BufferingClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
public class PokemonConfig {
    private Integer timeout;

    @Bean(name = "pokemonRestTemplate")
    public RestTemplate restTemplate() {
        final RestTemplate restTemplate = new RestTemplate(setRequestConfig());

        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        //Add the Jackson Message converter
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();

        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        restTemplate.setMessageConverters(messageConverters);


        return restTemplate;
    }

    private ClientHttpRequestFactory setRequestConfig() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        try {
            timeout = 25000;
            requestFactory.setReadTimeout(timeout);
        } catch (NumberFormatException nfe) {
            requestFactory.setReadTimeout(25000);
        }
        return new BufferingClientHttpRequestFactory(requestFactory);
    }


}
