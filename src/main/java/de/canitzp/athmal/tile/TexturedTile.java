package de.canitzp.athmal.tile;

import de.canitzp.athmal.Athmal;
import de.canitzp.athmal.IRender;
import de.canitzp.athmal.Texture;
import de.canitzp.athmal.map.Layer;
import de.canitzp.athmal.map.Map;

/**
 * @author canitzp
 */
public class TexturedTile extends Tile {

    private Texture texture;

    public TexturedTile(Texture texture){
        this.texture = texture;
    }

    @Override
    public void render(Athmal game, IRender render, Map map, Layer layer, int x, int y) {
        if(this.texture != null){
            render.renderImage(this.texture, x, y, map.getTileSize(), map.getTileSize());
        }
    }
}
