package de.canitzp.athmal.tile;

import de.canitzp.athmal.Athmal;
import de.canitzp.athmal.IRender;
import de.canitzp.athmal.map.Layer;
import de.canitzp.athmal.map.Map;

import java.awt.*;

/**
 * @author canitzp
 */
public class TileEmpty extends Tile {

    @Override
    public void render(Athmal game, IRender render, Map map, Layer layer, int x, int y) {
        if(true)return;
        int size = map.getTileSize() / 2;
        render.renderRect(x, y, size, size, Color.BLACK);
        render.renderRect(x + size, y, size, size, Color.MAGENTA);
        render.renderRect(x, y + size, size, size, Color.MAGENTA);
        render.renderRect(x + size, y + size, size, size, Color.BLACK);
    }
}
