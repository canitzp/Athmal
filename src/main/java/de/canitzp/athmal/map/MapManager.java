package de.canitzp.athmal.map;

import de.canitzp.athmal.Athmal;
import de.canitzp.athmal.IRender;

import java.util.HashMap;

/**
 * @author canitzp
 */
public class MapManager {

    private static Map currentLoaded = new NullMap();
    private static HashMap<String, Map> allMaps = new HashMap<>();

    public static void showMap(String name){
        forceShow(allMaps.get(name));
    }

    public static void forceShow(Map map){
        currentLoaded = map;
        if(currentLoaded == null){
            currentLoaded = new NullMap();
        }
    }

    public static void renderMap(Athmal game, IRender renderer){
        currentLoaded.render(game, renderer);
    }

}
