package de.canitzp.athmal.map;

import de.canitzp.athmal.Athmal;
import de.canitzp.athmal.IRender;
import de.canitzp.athmal.tile.TileEmpty;

import java.awt.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author canitzp
 */
public class Map {

    private Color colorMain = Color.GREEN, colorBackground = Color.BLACK;
    private int width, height, tileSize;
    private TreeMap<Layer, TileMap> tiles = new TreeMap<>();

    public Map(int width, int height, int tileSize){
        this.width = width * tileSize;
        this.height = height * tileSize;
        this.tileSize = tileSize;
        Arrays.stream(Layer.values()).forEach(layer -> tiles.put(layer, new TileMap(this).fill(layer.getEmptyTile())));
    }

    public void render(Athmal game, IRender render){
        render.renderRect(0, 0, game.getWidth(), game.getHeight(), colorBackground);
        int mapLeft = game.getWidth() / 2 - this.width / 2;
        int mapTop = game.getHeight() / 2 - this.height / 2;
        render.renderRect(mapLeft, mapTop, this.width, this.height, colorMain);
        this.tiles.forEach((layer, tileMap) -> tileMap.render(game, render, this, layer, mapLeft, mapTop));
    }

    public int getWidth() {
        return this.width / this.tileSize;
    }

    public int getHeight() {
        return this.height / this.tileSize;
    }

    public int getTileSize() {
        return tileSize;
    }

    public TileMap getTiles(Layer layer){
        return this.tiles.get(layer);
    }

    public void clearMapCompletely(){
        TileEmpty tile = new TileEmpty();
        this.tiles.forEach((layer, tileMap) -> tileMap.fill(tile));
    }
}
