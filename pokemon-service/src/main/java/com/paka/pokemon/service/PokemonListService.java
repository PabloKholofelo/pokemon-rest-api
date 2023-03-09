package com.paka.pokemon.service;

import com.paka.pokemon.model.PokemonListResponse;
import org.springframework.http.ResponseEntity;

public interface PokemonListService {
    ResponseEntity<PokemonListResponse> getPokemonList(Integer  limit,
                                                       Integer  offset);
}
