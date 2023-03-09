package com.paka.pokemon.service.impl;

import com.paka.pokemon.adaptor.PokemonDetailAdaptor;
import com.paka.pokemon.service.PokemonDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.paka.pokemon.model.PokemonDetail;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PokemonDetailsServiceImpl implements PokemonDetailsService {

    private PokemonDetailAdaptor pokemonDetailAdaptor;

    @Autowired
    public void setPokemonDetailAdaptor(PokemonDetailAdaptor pokemonDetailAdaptor) {
        this.pokemonDetailAdaptor = pokemonDetailAdaptor;
    }

    @Override
    public ResponseEntity<PokemonDetail> findByName(String name) {

        PokemonDetail response;
        try {
            response = this.pokemonDetailAdaptor.serviceCall(name);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

        if (response == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
