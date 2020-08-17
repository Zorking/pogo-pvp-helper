package com.packagename.demo;

import java.io.Serializable;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



class PokemonType {
    public String name;
//    public String url;

    @JsonProperty("type")
    private void unpackType(Map<String,Object> type) {
        this.name = (String)type.get("name");
//        this.url = (String)type.get("url");
    }
}

class Pokemon implements Serializable {
    private long id;
    private String name;
    private String spriteURL;
    private PokemonType[] pokemonTypes;

    @JsonProperty("sprites")
    private void unpackSprites(Map<String,Object> sprite) {
        this.spriteURL = (String)sprite.get("front_default");
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSpriteURL() {
        return spriteURL;
    }

    public PokemonType[] getTypes() {
        return pokemonTypes;
    }

}

@Service
public class PokemonService implements Serializable {
    public Pokemon getPokemon(String name) {
        String pokemonResourceUrl = "https://pokeapi.co/api/v2/pokemon/" + name;
        return new RestTemplate().getForObject(pokemonResourceUrl, Pokemon.class);
    }

}
