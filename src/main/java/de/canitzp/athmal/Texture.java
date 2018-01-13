package de.canitzp.athmal;

import javafx.util.Pair;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author canitzp
 */
public class Texture {

    private Map<Float, Map<Float, Texture>> textures = new HashMap<>();

    private int width, height;
    private BufferedImage image;

    public Texture(BufferedImage image){
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
    }

    public Texture(InputStream stream) throws IOException {
        this(ImageIO.read(stream));
    }

    public Texture(String path) throws IOException {
        this(Texture.class.getResourceAsStream("/assets/textures/" + path + ".png"));
    }

    private void addScalesTexture(float width, float height, Texture texture){
        HashMap<Float, Texture> map = new HashMap<>();
        map.put(height, texture);
        this.textures.put(width, map);
    }

    private Texture scale(float width, float height){
        BufferedImage after = new BufferedImage(Math.round(width), Math.round(height), BufferedImage.TYPE_INT_ARGB);
        AffineTransform at = new AffineTransform();
        at.scale(width / this.width, height / this.height);
        AffineTransformOp scaleOp = new AffineTransformOp(at, null);
        return new Texture(scaleOp.filter(image, after));
    }

    public Texture getScaledImage(float scaleFactor){
        return this.getScaledImage(scaleFactor, scaleFactor);
    }

    public Texture getScaledImage(float width, float height){
        Texture texture = this.textures.getOrDefault(width, new HashMap<>()).getOrDefault(height, null);
        if(texture != null){
            return texture;
        }
        this.addScalesTexture(width, height, this.scale(width, height));
        return this.getScaledImage(width, height);
    }

    public void draw(Graphics g, int x, int y, float width, float height){
        if(width == 1.0F && height == 1.0F){
            g.drawImage(this.image, x, y, null);
        } else {
            this.getScaledImage(width, height).draw(g, x, y, 1.0F, 1.0F);
        }
    }

    public void draw(Graphics g, int x, int y, float scaleFactor){
        this.draw(g, x, y, scaleFactor, scaleFactor);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
