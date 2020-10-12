package com.packagename.demo;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Array;

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
    public MainView(@Autowired PokemonService service) {
        HorizontalLayout navbar= new HorizontalLayout();
//        menuBar.addItem("Login with Google")
        boolean isAuthentificated = false;
        Anchor logout = new Anchor("/logout", "Logout");
        Anchor googleLoginButton = new Anchor("/oauth2/authorization/google", "Login with Google");
        logout.getElement().setAttribute("router-ignore", true);
        logout.setVisible(isAuthentificated);
        googleLoginButton.setVisible(!isAuthentificated);
        navbar.add(googleLoginButton, logout);
        VerticalLayout toolPanel = new VerticalLayout();
        HorizontalLayout buttonPanel = new HorizontalLayout();
        HorizontalLayout pokemonsPanel = new HorizontalLayout();
        VerticalLayout yourPokemonsPanel = new VerticalLayout();
        VerticalLayout rivalPokemonsPanel = new VerticalLayout();
        toolPanel.getStyle().set("padding-right", "13%");
        yourPokemonsPanel.getStyle().set("margin", "0px 0px 0px 25%").set("display", "table").set("width", "50%");
        rivalPokemonsPanel.getStyle().set("margin", "0px 25% 0px 0px").set("display", "table").set("width", "50%");
        pokemonsPanel.setWidth("100%");
        ComboBox<String> searchPokemon = new ComboBox<>();
        searchPokemon.setLabel("Search Pokemon");
        searchPokemon.setItems("Bulbasaur", "Chikorita", "Treecko", "Turtwig", "Victini", "Chespin", "Rowlet", "Grookey", "Ivysaur", "Bayleef", "Grovyle", "Grotle", "Snivy", "Quilladin", "Dartrix", "Thwackey", "Venusaur", "Meganium", "Sceptile", "Torterra", "Servine", "Chesnaught", "Decidueye", "Rillaboom", "Charmander", "Cyndaquil", "Torchic", "Chimchar", "Serperior", "Fennekin", "Litten", "Scorbunny", "Charmeleon", "Quilava", "Combusken", "Monferno", "Tepig", "Braixen", "Torracat", "Raboot", "Charizard", "Typhlosion", "Blaziken", "Infernape", "Pignite", "Delphox", "Incineroar", "Cinderace", "Squirtle", "Totodile", "Mudkip", "Piplup", "Emboar", "Froakie", "Popplio", "Sobble", "Wartortle", "Croconaw", "Marshtomp", "Prinplup", "Oshawott", "Frogadier", "Brionne", "Drizzile", "Blastoise", "Feraligatr", "Swampert", "Empoleon", "Dewott", "Greninja", "Primarina", "Inteleon", "Caterpie", "Sentret", "Poochyena", "Starly", "Samurott", "Bunnelby", "Pikipek", "Skwovet", "Metapod", "Furret", "Mightyena", "Staravia", "Patrat", "Diggersby", "Trumbeak", "Greedent", "Butterfree", "Hoothoot", "Zigzagoon", "Staraptor", "Watchog", "Fletchling", "Toucannon", "Rookidee", "Weedle", "Noctowl", "Linoone", "Bidoof", "Lillipup", "Fletchinder", "Yungoos", "Corvisquire", "Kakuna", "Ledyba", "Wurmple", "Bibarel", "Herdier", "Talonflame", "Gumshoos", "Corviknight", "Beedrill", "Ledian", "Silcoon", "Kricketot", "Stoutland", "Scatterbug", "Grubbin", "Blipbug", "Pidgey", "Spinarak", "Beautifly", "Kricketune", "Purrloin", "Spewpa", "Charjabug", "Dottler", "Pidgeotto", "Ariados", "Cascoon", "Shinx", "Liepard", "Vivillon", "Vikavolt", "Orbeetle", "Pidgeot", "Crobat", "Dustox", "Luxio", "Pansage", "Litleo", "Crabrawler", "Nickit", "Rattata", "Chinchou", "Lotad", "Luxray", "Simisage", "Pyroar", "Crabominable", "Thievul", "Raticate", "Lanturn", "Lombre", "Budew", "Pansear", "Flabébé", "Oricorio", "Gossifleur", "Spearow", "Pichu", "Ludicolo", "Roserade", "Simisear", "Floette", "Cutiefly", "Eldegoss", "Fearow", "Cleffa", "Seedot", "Cranidos", "Panpour", "Florges", "Ribombee", "Wooloo", "Ekans", "Igglybuff", "Nuzleaf", "Rampardos", "Simipour", "Skiddo", "Rockruff", "Dubwool", "Arbok", "Togepi", "Shiftry", "Shieldon", "Munna", "Gogoat", "Lycanroc", "Chewtle", "Pikachu", "Togetic", "Taillow", "Bastiodon", "Musharna", "Pancham", "Wishiwashi", "Drednaw", "Raichu", "Natu", "Swellow", "Burmy", "Pidove", "Pangoro", "Mareanie", "Yamper", "Sandshrew", "Xatu", "Wingull", "Wormadam", "Tranquill", "Furfrou", "Toxapex", "Boltund", "Sandslash", "Mareep", "Pelipper", "Mothim", "Unfezant", "Espurr", "Mudbray", "Rolycoly", "Nidoran♀", "Flaaffy", "Ralts", "Combee", "Blitzle", "Meowstic", "Mudsdale", "Carkol", "Nidorina", "Ampharos", "Kirlia", "Vespiquen", "Zebstrika", "Honedge", "Dewpider", "Coalossal", "Nidoqueen", "Bellossom", "Gardevoir", "Pachirisu", "Roggenrola", "Doublade", "Araquanid", "Applin", "Nidoran♂", "Marill", "Surskit", "Buizel", "Boldore", "Aegislash", "Fomantis", "Flapple", "Nidorino", "Azumarill", "Masquerain", "Floatzel", "Gigalith", "Spritzee", "Lurantis", "Appletun", "Nidoking", "Sudowoodo", "Shroomish", "Cherubi", "Woobat", "Aromatisse", "Morelull", "Silicobra", "Clefairy", "Politoed", "Breloom", "Cherrim", "Swoobat", "Swirlix", "Shiinotic", "Sandaconda", "Clefable", "Hoppip", "Slakoth", "Shellos", "Drilbur", "Slurpuff", "Salandit", "Cramorant", "Vulpix", "Skiploom", "Vigoroth", "Gastrodon", "Excadrill", "Inkay", "Salazzle", "Arrokuda", "Ninetales", "Jumpluff", "Slaking", "Ambipom", "Audino", "Malamar", "Stufful", "Barraskewda", "Jigglypuff", "Aipom", "Nincada", "Drifloon", "Timburr", "Binacle", "Bewear", "Toxel", "Wigglytuff", "Sunkern", "Ninjask", "Drifblim", "Gurdurr", "Barbaracle", "Bounsweet", "Toxtricity", "Zubat", "Sunflora", "Shedinja", "Buneary", "Conkeldurr", "Skrelp", "Steenee", "Sizzlipede", "Golbat", "Yanma", "Whismur", "Lopunny", "Tympole", "Dragalge", "Tsareena", "Centiskorch", "Oddish", "Wooper", "Loudred", "Mismagius", "Palpitoad", "Clauncher", "Comfey", "Clobbopus", "Gloom", "Quagsire", "Exploud", "Honchkrow", "Seismitoad", "Clawitzer", "Oranguru", "Grapploct", "Vileplume", "Espeon", "Makuhita", "Glameow", "Throh", "Helioptile", "Passimian", "Sinistea", "Paras", "Umbreon", "Hariyama", "Purugly", "Sawk", "Heliolisk", "Wimpod", "Polteageist", "Parasect", "Murkrow", "Azurill", "Chingling", "Sewaddle", "Tyrunt", "Golisopod", "Hatenna", "Venonat", "Slowking", "Nosepass", "Stunky", "Swadloon", "Tyrantrum", "Sandygast", "Hattrem", "Venomoth", "Misdreavus", "Skitty", "Skuntank", "Leavanny", "Amaura", "Palossand", "Hatterene", "Diglett", "Unown", "Delcatty", "Bronzor", "Venipede", "Aurorus", "Pyukumuku", "Impidimp", "Dugtrio", "Wobbuffet", "Sableye", "Bronzong", "Whirlipede", "Sylveon", "Type: Null", "Morgrem", "Meowth", "Girafarig", "Mawile", "Bonsly", "Scolipede", "Hawlucha", "Silvally", "Grimmsnarl", "Persian", "Pineco", "Aron", "Mime Jr.", "Cottonee", "Dedenne", "Minior", "Obstagoon", "Psyduck", "Forretress", "Lairon", "Happiny", "Whimsicott", "Carbink", "Komala", "Perrserker", "Golduck", "Dunsparce", "Aggron", "Chatot", "Petilil", "Goomy", "Turtonator", "Cursola", "Mankey", "Gligar", "Meditite", "Spiritomb", "Lilligant", "Sliggoo", "Togedemaru", "Sirfetch'd", "Primeape", "Steelix", "Medicham", "Gible", "Basculin", "Goodra", "Mimikyu", "Mr. Rime", "Growlithe", "Snubbull", "Electrike", "Gabite", "Sandile", "Klefki", "Bruxish", "Runerigus", "Arcanine", "Granbull", "Manectric", "Garchomp", "Krokorok", "Phantump", "Drampa", "Milcery", "Poliwag", "Qwilfish", "Plusle", "Munchlax", "Krookodile", "Trevenant", "Dhelmise", "Alcremie", "Poliwhirl", "Scizor", "Minun", "Riolu", "Darumaka", "Pumpkaboo", "Jangmo-o", "Falinks", "Poliwrath", "Shuckle", "Volbeat", "Lucario", "Darmanitan", "Gourgeist", "Hakamo-o", "Pincurchin", "Abra", "Heracross", "Illumise", "Hippopotas", "Maractus", "Bergmite", "Kommo-o", "Snom", "Kadabra", "Sneasel", "Roselia", "Hippowdon", "Dwebble", "Avalugg", "Tapu Koko", "Frosmoth", "Alakazam", "Teddiursa", "Gulpin", "Skorupi", "Crustle", "Noibat", "Tapu Lele", "Stonjourner", "Machop", "Ursaring", "Swalot", "Drapion", "Scraggy", "Noivern", "Tapu Bulu", "Eiscue", "Machoke", "Slugma", "Carvanha", "Croagunk", "Scrafty", "Xerneas", "Tapu Fini", "Indeedee", "Machamp", "Magcargo", "Sharpedo", "Toxicroak", "Sigilyph", "Yveltal", "Cosmog", "Morpeko", "Bellsprout", "Swinub", "Wailmer", "Carnivine", "Yamask", "Zygarde", "Cosmoem", "Cufant", "Weepinbell", "Piloswine", "Wailord", "Finneon", "Cofagrigus", "Diancie", "Solgaleo", "Copperajah", "Victreebel", "Corsola", "Numel", "Lumineon", "Tirtouga", "Hoopa", "Lunala", "Dracozolt", "Tentacool", "Remoraid", "Camerupt", "Mantyke", "Carracosta", "Volcanion", "Nihilego", "Arctozolt", "Tentacruel", "Octillery", "Torkoal", "Snover", "Archen", "Buzzwole", "Dracovish", "Geodude", "Delibird", "Spoink", "Abomasnow", "Archeops", "Pheromosa", "Arctovish", "Graveler", "Mantine", "Grumpig", "Weavile", "Trubbish", "Xurkitree", "Duraludon", "Golem", "Skarmory", "Spinda", "Magnezone", "Garbodor", "Celesteela", "Dreepy", "Ponyta", "Houndour", "Trapinch", "Lickilicky", "Zorua", "Kartana", "Drakloak", "Rapidash", "Houndoom", "Vibrava", "Rhyperior", "Zoroark", "Guzzlord", "Dragapult", "Slowpoke", "Kingdra", "Flygon", "Tangrowth", "Minccino", "Necrozma", "Zacian", "Slowbro", "Phanpy", "Cacnea", "Electivire", "Cinccino", "Magearna", "Zamazenta", "Magnemite", "Donphan", "Cacturne", "Magmortar", "Gothita", "Marshadow", "Eternatus", "Magneton", "Porygon2", "Swablu", "Togekiss", "Gothorita", "Poipole", "Kubfu", "Farfetch'd", "Stantler", "Altaria", "Yanmega", "Gothitelle", "Naganadel", "Urshifu", "Doduo", "Smeargle", "Zangoose", "Leafeon", "Solosis", "Stakataka", "Zarude", "Dodrio", "Tyrogue", "Seviper", "Glaceon", "Duosion", "Blacephalon", null, "Seel", "Hitmontop", "Lunatone", "Gliscor", "Reuniclus", "Zeraora", null, "Dewgong", "Smoochum", "Solrock", "Mamoswine", "Ducklett", "Meltan", "Calyrex", "Grimer", "Elekid", "Barboach", "Porygon-Z", "Swanna", "Melmetal", "Muk", "Magby", "Whiscash", "Gallade", "Vanillite", "Shellder", "Miltank", "Corphish", "Probopass", "Vanillish", "Cloyster", "Blissey", "Crawdaunt", "Dusknoir", "Vanilluxe", "Gastly", "Raikou", "Baltoy", "Froslass", "Deerling", "Haunter", "Entei", "Claydol", "Rotom", "Sawsbuck", "Gengar", "Suicune", "Lileep", "Uxie", "Emolga", "Onix", "Larvitar", "Cradily", "Mesprit", "Karrablast", "Drowzee", "Pupitar", "Anorith", "Azelf", "Escavalier", "Hypno", "Tyranitar", "Armaldo", "Dialga", "Foongus", "Krabby", "Lugia", "Feebas", "Palkia", "Amoonguss", "Kingler", "Ho-oh", "Milotic", "Heatran", "Frillish", "Voltorb", "Celebi", "Castform", "Regigigas", "Jellicent", "Electrode", "Kecleon", "Giratina", "Alomomola", "Exeggcute", "Shuppet", "Cresselia", "Joltik", "Exeggutor", "Banette", "Phione", "Galvantula", "Cubone", "Duskull", "Manaphy", "Ferroseed", "Marowak", "Dusclops", "Darkrai", "Ferrothorn", "Hitmonlee", "Tropius", "Shaymin", "Klink", "Hitmonchan", "Chimecho", "Arceus", "Klang", "Lickitung", "Absol", "Klinklang", "Koffing", "Wynaut", "Tynamo", "Weezing", "Snorunt", "Eelektrik", "Rhyhorn", "Glalie", "Eelektross", "Rhydon", "Spheal", "Elgyem", "Chansey", "Sealeo", "Beheeyem", "Tangela", "Walrein", "Litwick", "Kangaskhan", "Clamperl", "Lampent", "Horsea", "Huntail", "Chandelure", "Seadra", "Gorebyss", "Axew", "Goldeen", "Relicanth", "Fraxure", "Seaking", "Luvdisc", "Haxorus", "Staryu", "Bagon", "Cubchoo", "Starmie", "Shelgon", "Beartic", "Mr. Mime", "Salamence", "Cryogonal", "Scyther", "Beldum", "Shelmet", "Jynx", "Metang", "Accelgor", "Electabuzz", "Metagross", "Stunfisk", "Magmar", "Regirock", "Mienfoo", "Pinsir", "Regice", "Mienshao", "Tauros", "Registeel", "Druddigon", "Magikarp", "Latias", "Golett", "Gyarados", "Latios", "Golurk", "Lapras", "Kyogre", "Pawniard", "Ditto", "Groudon", "Bisharp", "Eevee", "Rayquaza", "Bouffalant", "Vaporeon", "Jirachi", "Rufflet", "Jolteon", "Deoxys", "Braviary", "Flareon", "Vullaby", "Porygon", "Mandibuzz", "Omanyte", "Heatmor", "Omastar", "Durant", "Kabuto", "Deino", "Kabutops", "Zweilous", "Aerodactyl", "Hydreigon", "Snorlax", "Larvesta", "Articuno", "Volcarona", "Zapdos", "Cobalion", "Moltres", "Terrakion", "Dratini", "Virizion", "Dragonair", "Tornadus", "Dragonite", "Thundurus", "Mewtwo", "Reshiram", "Mew", "Zekrom", "Landorus", "Kyurem", "Keldeo", "Meloetta", "Genesect");
        Button addYourPokemonButton = new Button("Add Your Pokemon", e -> this.addPokemon(service, searchPokemon.getValue(), true, yourPokemonsPanel));
        Button addRivalPokemonButton = new Button("Add Rival Pokemon", e -> this.addPokemon(service, searchPokemon.getValue(), false, rivalPokemonsPanel));
        addYourPokemonButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        addYourPokemonButton.addClickShortcut(Key.ENTER);
        buttonPanel.add(addYourPokemonButton, addRivalPokemonButton);
        toolPanel.setAlignItems(Alignment.CENTER);
        pokemonsPanel.setAlignItems(Alignment.CENTER);
        toolPanel.add(searchPokemon, buttonPanel);
        pokemonsPanel.add(yourPokemonsPanel, rivalPokemonsPanel);
        HorizontalLayout headers = new HorizontalLayout();
        headers.setWidth("100%");
        H3 yourPokemonHeader = new H3("Your Pokemon");
        H3 rivalsPokemonHeader = new H3("Rivals Pokemon");
        yourPokemonHeader.getStyle().set("margin", "0px 7% 0px 30%").set("display", "table").set("Width", "50%");
        rivalsPokemonHeader.getStyle().set("margin", "0px 25% 0px 7%").set("display", "table").set("Width", "50%");
        headers.add(yourPokemonHeader, rivalsPokemonHeader);
        add(navbar, toolPanel, headers, pokemonsPanel);
//        for (Pokemon pokemon: service.findAll()) {
//            this.addPokemon(service, pokemon.getName(), true);
//        }
    }

    private void addPokemon(PokemonService service, String text, boolean isYourPokemon, VerticalLayout layout) {
        PokemonSerializer pokemonSerializer = service.getPokemon(text);
        Label label = new Label(pokemonSerializer.getName());
        Image sprite = new Image(pokemonSerializer.getSpriteURL(), "no image");
        StringBuilder types = new StringBuilder();
        Type[] allTypes = pokemonSerializer.getTypes();
        for (int i = 0; i < allTypes.length; i++) {
            Type pokemonType = allTypes[i];
            if (isYourPokemon) {
                types.append(pokemonType.name);
                if (allTypes.length - 1 != i) types.append(", ");
            } else {
                types.append(pokemonType.name).append("<br>");
                TypeDetailSerializer weaknesses = service.getTypeWeakness(pokemonType.url);
                ReflectionUtils.doWithFields(weaknesses.damage_relations.getClass(), field -> {
                    String fieldName = field.getName();
                    Type[] fieldValue = (Type[]) field.get(weaknesses.damage_relations);
                    if (fieldValue.length != 0) {
                        types.append("<b>").append(fieldName).append(": ").append("</b>");
                        for (int j = 0; j < fieldValue.length; j++) {
                            types.append(fieldValue[j].name);
                            if (fieldValue.length - 1 != j) types.append(", ");
                        }
                        types.append("<br>");
                    }
                });
                types.append("<br>");
            }
        }
        Pokemon pokemon = new Pokemon();
//        if (isYourPokemon) {
        pokemon.setId(pokemonSerializer.getId());
        pokemon.setName(pokemonSerializer.getName());
        pokemon.setSpriteURL(pokemonSerializer.getSpriteURL());
//        pokemon.setType(types.toString());
        service.save(pokemon);
//        }
        Icon deleteIcon = new Icon(VaadinIcon.TRASH);
        Button deleteButton = new Button("", deleteIcon, e -> deletePokemon(service, pokemon, sprite.getParent().get(), layout));
        Div typesPokemon = new Div();
        typesPokemon.getElement().setProperty("innerHTML", types.toString());
        HorizontalLayout row = new HorizontalLayout(label, sprite, typesPokemon, deleteButton);
//        if (!isYourPokemon) {
//            row.getStyle().set("background-color", "coral");
//        }
        row.setDefaultVerticalComponentAlignment(Alignment.CENTER);
        layout.add(row);
    }

    private void deletePokemon(PokemonService service, Pokemon pokemon, Component pokemonComponent, VerticalLayout layout) {
//        if (pokemon != null) {
//            service.delete(pokemon);
//        }
        layout.remove(pokemonComponent);
    }
}
