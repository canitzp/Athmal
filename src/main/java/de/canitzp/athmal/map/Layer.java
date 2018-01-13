package de.canitzp.athmal.map;

import de.canitzp.athmal.tile.Tile;
import de.canitzp.athmal.tile.TileEmpty;
import de.canitzp.athmal.tile.TileStone;

/**
 * @author canitzp
 */
public enum Layer {

    UNDERGROUND(new TileStone()),
    GROUND,
    PATHS,
    PLAYER;

    private Tile emptyTile = new TileEmpty();

    Layer(){}

    Layer(Tile emptyTile){
        this.emptyTile = emptyTile;
    }

    public Tile getEmptyTile() {
        return emptyTile;
    }

    public boolean canThereBeTiles(){
        return this != PLAYER;
    }
}
