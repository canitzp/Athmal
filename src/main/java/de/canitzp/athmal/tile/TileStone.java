package de.canitzp.athmal.tile;

import de.canitzp.athmal.Athmal;
import de.canitzp.athmal.IRender;
import de.canitzp.athmal.map.Layer;
import de.canitzp.athmal.map.Map;

import java.awt.*;

/**
 * @author canitzp
 */
public class TileStone extends Tile {

    @Override
    public void render(Athmal game, IRender render, Map map, Layer layer, int x, int y) {
        if(layer == Layer.UNDERGROUND || layer == Layer.GROUND){
            render.renderRect(x, y, map.getTileSize(), map.getTileSize(), Color.GRAY);
        }
    }
}
