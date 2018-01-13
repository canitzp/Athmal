package de.canitzp.athmal.tile;

import de.canitzp.athmal.Athmal;
import de.canitzp.athmal.IRender;
import de.canitzp.athmal.map.Layer;
import de.canitzp.athmal.map.Map;

import java.awt.*;

/**
 * @author canitzp
 */
public class Tile {

    public void render(Athmal game, IRender render, Map map, Layer layer, int x, int y){
        render.renderCage(x, y, map.getTileSize(), map.getTileSize(), Color.MAGENTA);
    }

}
