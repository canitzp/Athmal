package de.canitzp.athmal.map;

import com.sun.javafx.UnmodifiableArrayList;
import de.canitzp.athmal.Athmal;
import de.canitzp.athmal.IRender;
import de.canitzp.athmal.tile.Tile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @author canitzp
 */
public class TileMap {

    private Map map;
    private Tile[][] tiles;

    public TileMap(Map map){
        this.map = map;
        this.tiles = new Tile[map.getWidth()][map.getHeight()];
        this.fill(new Tile()); // Air
    }

    public TileMap fill(Tile tile){
        Arrays.stream(this.tiles).forEach(tiles1 -> Arrays.fill(tiles1, tile));
        return this;
    }

    public void fillRandomly(Tile tile){
        Arrays.stream(this.tiles).forEach(tiles1 ->{
            for(int i = 0; i < tiles1.length; i++){
                if(new Random().nextBoolean()){
                    tiles1[i] = tile;
                }
            }
        });
    }

    public void set(int row, int col, Tile tile){
        this.tiles[row][col] = tile;
    }

    public Tile get(int row, int col){
        return this.tiles.clone()[row][col];
    }

    public Tile replace(int row, int col, Tile tile){
        Tile oldTile = this.get(row, col);
        this.set(row, col, tile);
        return oldTile;
    }

    public UnmodifiableArrayList<Tile> getAllTiles(){
        List<Tile> list = new ArrayList<>();
        Arrays.stream(this.tiles).forEach(tiles1 -> list.addAll(Arrays.asList(tiles1)));
        return new UnmodifiableArrayList<>(list.toArray(new Tile[0]), list.size());
    }

    public void render(Athmal game, IRender render, Map map, Layer layer, int mapLeft, int mapTop){
        for(int row = 0; row < this.tiles.length; row++){
            Tile[] cols = this.tiles[row];
            for(int col = 0; col < cols.length; col++){
                cols[col].render(game, render, map, layer, map.getTileSize() * row + mapLeft, map.getTileSize() * col + mapTop);
            }
        }
    }

}
