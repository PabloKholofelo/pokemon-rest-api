package com.paka.pokemon.service;

import com.paka.pokemon.model.PokemonDetail;
import org.springframework.http.ResponseEntity;

public interface PokemonDetailsService {
    ResponseEntity<PokemonDetail> findByName(String name);
}
