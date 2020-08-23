package com.packagename.demo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



class Type implements Serializable {
    public String name;
    public String url;

    @JsonProperty("type")
    private void unpackType(Map<String,Object> type) {
        this.name = (String)type.get("name");
        this.url = (String)type.get("url");
    }
}

class PokemonSerializer implements Serializable {
    private Integer id;
    private String name;
    private String spriteURL;
    private Type[] types;

    @JsonProperty("sprites")
    private void unpackSprites(Map<String,Object> sprite) {
        this.spriteURL = (String)sprite.get("front_default");
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpriteURL() {
        return spriteURL;
    }

    public Type[] getTypes() {
        return types;
    }

}

@Service
public class PokemonService implements Serializable {
    private PokemonRepository pokemonRepository;

    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public PokemonSerializer getPokemon(String name) {
        String pokemonResourceUrl = "https://pokeapi.co/api/v2/pokemon/" + name;
        return new RestTemplate().getForObject(pokemonResourceUrl, PokemonSerializer.class);
    }

    public List<Pokemon> findAll() {
        return pokemonRepository.findAll();
    }

    public void save(Pokemon pokemon) {
        if (pokemon == null) {
            return;
        }
        pokemonRepository.save(pokemon);
    }

    public void delete(Pokemon pokemon) {
        pokemonRepository.delete(pokemon);
    }
}
