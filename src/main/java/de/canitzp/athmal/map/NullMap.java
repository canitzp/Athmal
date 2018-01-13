package de.canitzp.athmal.map;

import de.canitzp.athmal.Texture;
import de.canitzp.athmal.tile.TexturedTile;
import de.canitzp.athmal.tile.TileStone;

import java.io.IOException;

/**
 * @author canitzp
 */
public class NullMap extends Map {

    public NullMap() {
        super(2, 2, 53);
        this.clearMapCompletely();
        try {
            this.getTiles(Layer.GROUND).fillRandomly(new TexturedTile(new Texture("test128")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
