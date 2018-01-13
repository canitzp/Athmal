package de.canitzp.athmal;


import java.awt.*;
import java.awt.image.BufferStrategy;
import java.util.function.Consumer;

/**
 * @author canitzp
 */
public class BufferStrategyRender implements IRender {

    private BufferStrategy buffer;

    public BufferStrategyRender(BufferStrategy buffer){
        this.buffer = buffer;
    }

    private void g(Consumer<Graphics> g){
        g.accept(this.buffer.getDrawGraphics());
    }

    public Font getFont(){
        return this.buffer.getDrawGraphics().getFont();
    }

    protected Graphics getGrafics(){
        return this.buffer.getDrawGraphics();
    }

    @Override
    public void show() {
        this.buffer.show();
    }

    @Override
    public void renderText(Font font, String text, int x, int y, Color color) {
        this.g(graphics -> {
            if(font != null){
                graphics.setFont(font);
            }
            graphics.setColor(color);
            graphics.drawString(text, x, y + this.getFont().getSize());
        });
    }

    @Override
    public void renderImage(Texture texture, int x, int y, float scale){
        texture.draw(this.getGrafics(), x, y, scale);
    }

    @Override
    public void renderImage(Texture texture, int x, int y, float width, float height) {
        texture.draw(this.getGrafics(), x, y, width, height);
    }

    @Override
    public void renderRect(int x, int y, int width, int height, Color color) {
        this.g(graphics -> {
            graphics.setColor(color);
            graphics.fillRect(x, y, width, height);
        });
    }

    @Override
    public void renderCage(int x, int y, int width, int height, Color color) {
        this.g(graphics -> {
            graphics.setColor(color);
            graphics.drawRect(x, y, width, height);
        });
    }

    @Override
    public void clear(int x, int y, int width, int height) {
        this.g(graphics -> graphics.clearRect(x, y, width, height));
    }
}
