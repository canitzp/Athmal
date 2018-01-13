package de.canitzp.athmal;


import java.awt.*;

/**
 * @author canitzp
 */
public interface IRender {

    void show();

    void renderText(Font font, String text, int x, int y, Color color);

    void renderImage(Texture texture, int x, int y, float scale);

    void renderImage(Texture texture, int x, int y, float width, float height);

    void renderRect(int x, int y, int width, int height, Color color);

    void renderCage(int x, int y, int width, int height, Color color);

    void clear(int x, int y, int width, int height);

}
