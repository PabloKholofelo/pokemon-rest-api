package com.paka.pokemon.adaptor;

import com.paka.pokemon.model.PokemonDetail;
import com.paka.pokemon.model.PokemonListResponse;
import com.paka.pokemon.models.PokemonListQuery;
import org.springframework.stereotype.Component;

@Component
public interface PokemonDetailAdaptor {
    PokemonDetail serviceCall(String name);
}
