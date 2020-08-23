package com.packagename.demo;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * A sample Vaadin view class.
 * <p>
 * To implement a Vaadin view just extend any Vaadin component and
 * use @Route annotation to announce it in a URL as a Spring managed
 * bean.
 * Use the @PWA annotation make the application installable on phones,
 * tablets and some desktop browsers.
 * <p>
 * A new instance of this class is created for every new user and every
 * browser tab/window.
 */
@Route
@PWA(name = "Vaadin Application",
        shortName = "Vaadin App",
        description = "This is an example Vaadin application.",
        enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {

    /**
     * Construct a new Vaadin view.
     * <p>
     * Build the initial UI state for the user accessing the application.
     *
     * @param service The message service. Automatically injected Spring managed bean.
     */
    public MainView(@Autowired PokemonService service) {
        TextField textField = new TextField("Search pokemon");
        textField.addThemeName("bordered");
        Button button = new Button("Search", e -> this.addPokemon(service, textField.getValue()));
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button.addClickShortcut(Key.ENTER);
        addClassName("centered-content");
        add(textField, button);
        for (Pokemon pokemon: service.findAll()) {
            this.addPokemon(service, pokemon.getName());
        }
    }

    private void addPokemon(PokemonService service, String text) {
        PokemonSerializer pokemonSerializer = service.getPokemon(text);
        Label label = new Label(pokemonSerializer.getName());
        Image sprite = new Image(pokemonSerializer.getSpriteURL(), "no image");
        StringBuilder types = new StringBuilder();
        for (Type pokemonType: pokemonSerializer.getTypes()){
            types.append(pokemonType.name).append("|");
        }
        Pokemon pokemon = new Pokemon();
        pokemon.setId(pokemonSerializer.getId());
        pokemon.setName(pokemonSerializer.getName());
        pokemon.setSpriteURL(pokemonSerializer.getSpriteURL());
        pokemon.setType(types.toString());
        service.save(pokemon);
        Icon deleteIcon = new Icon(VaadinIcon.TRASH);
        Button deleteButton = new Button("", deleteIcon, e -> deletePokemon(service, pokemon, sprite.getParent().get()));
        HorizontalLayout row = new HorizontalLayout(label, sprite, new Label(types.toString()), deleteButton);
        row.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        add(row);
    }

    private void deletePokemon(PokemonService service, Pokemon pokemon, Component pokemonComponent) {
        service.delete(pokemon);
        remove(pokemonComponent);
    }
}
