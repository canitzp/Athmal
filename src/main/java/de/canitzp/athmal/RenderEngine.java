package de.canitzp.athmal;

import de.canitzp.athmal.map.MapManager;

import java.awt.*;

/**
 * @author canitzp
 */
public class RenderEngine {

    public static void render(Athmal game, IRender render){
        MapManager.renderMap(game, render);

        renderDebugInformation(game, render);
    }

    private static void renderDebugInformation(Athmal game, IRender render){
        render.clear(0, 0, 110, 15);
        render.renderText(null, String.format("FPS: %d   TPS: %d", game.getFps(), game.getTps()), 0, 0, Color.BLACK);
    }

}
