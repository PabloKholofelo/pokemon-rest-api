package com.paka.pokemon.service.impl;

import com.paka.pokemon.adaptor.PokemonListAdaptor;
import com.paka.pokemon.model.PokemonListResponse;
import com.paka.pokemon.models.PokemonListQuery;
import com.paka.pokemon.service.PokemonListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PokemonListServiceImpl implements PokemonListService {

    private PokemonListAdaptor pokemonListAdaptor;

    @Autowired
    public void setPokemonListAdaptor(PokemonListAdaptor pokemonListAdaptor) {
        this.pokemonListAdaptor = pokemonListAdaptor;
    }

    @Override
    public ResponseEntity<PokemonListResponse> getPokemonList(Integer limit, Integer offset) {
        PokemonListResponse response;

        PokemonListQuery pokemonListQuery = new PokemonListQuery();
        pokemonListQuery.setLimit(limit);
        pokemonListQuery.setOffset(offset);


        try {
            response = this.pokemonListAdaptor.serviceCall(pokemonListQuery);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
        }

        if (response == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
