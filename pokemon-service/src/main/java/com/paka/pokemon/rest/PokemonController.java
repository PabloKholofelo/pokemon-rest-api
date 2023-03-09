package com.paka.pokemon.rest;

import com.paka.pokemon.controller.ApiUtil;
import com.paka.pokemon.controller.PokemonApi;
import com.paka.pokemon.controller.PokemonApiDelegate;
import com.paka.pokemon.model.PokemonDetail;
import com.paka.pokemon.model.PokemonListResponse;
import com.paka.pokemon.service.PokemonDetailsService;
import com.paka.pokemon.service.PokemonListService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class PokemonController implements PokemonApiDelegate {

    private PokemonListService pokemonListService;
    private PokemonDetailsService pokemonDetailsService;

    @Autowired
    public void setPokemonService(PokemonListService pokemonListService) {
        this.pokemonListService = pokemonListService;
    }

    @Autowired
    public void setPokemonDetailsService(PokemonDetailsService pokemonDetailsService) {
        this.pokemonDetailsService = pokemonDetailsService;
    }
    /**
     * @see PokemonApi#getPokemonList
     */
    public ResponseEntity<PokemonListResponse> getPokemonList(String  limit,
                                                               String  offset) {
        try {
            return this.pokemonListService.getPokemonList(Integer.valueOf(limit), Integer.valueOf(offset));
        } catch (Exception ex) {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }

    /**
     * @see PokemonApi#findByName(String)
     */
    public ResponseEntity<PokemonDetail> findByName(String name) {

        try {
            return this.pokemonDetailsService.findByName(name);
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }
}
