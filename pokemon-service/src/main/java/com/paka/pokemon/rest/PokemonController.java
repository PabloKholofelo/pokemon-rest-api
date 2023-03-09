package com.paka.pokemon.rest;

import com.paka.pokemon.controller.ApiUtil;
import com.paka.pokemon.controller.PokemonApi;
import com.paka.pokemon.controller.PokemonApiDelegate;
import com.paka.pokemon.model.PokemonListResponse;
import com.paka.pokemon.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class PokemonController implements PokemonApiDelegate{

    private PokemonService pokemonService;

    @Autowired
    public void setPokemonService(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }
    /**
     * @see PokemonApi#getPokemonList
     */
    public ResponseEntity<PokemonListResponse> getPokemonList(String  limit,
                                                               String  offset) {
        Integer l;
        Integer o;
        try {
            l = Integer.valueOf(limit);
            o = Integer.valueOf(offset);
        } catch (Exception ex) {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return this.pokemonService.getPokemonList(l, o);

    }
}
