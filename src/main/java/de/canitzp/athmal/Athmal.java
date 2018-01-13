package de.canitzp.athmal;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferStrategy;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @author canitzp
 */
public class Athmal {

    public static final int TPS_CAP = 32, FPS_CAP = 60, WIDTH = 1080, HEIGHT = 720;
    public static Athmal INSTANCE;

    private JFrame window;
    private Canvas gamePanel;
    private int fps, tps;
    private boolean isRunning = true, doesGamePause;
    private IRender render;

    public static void main(String[] args){
        INSTANCE = new Athmal();
        INSTANCE.init();
        INSTANCE.loop();
    }

    private void init(){
        this.window = new JFrame("Athmal");
        this.gamePanel = new Canvas();
        this.gamePanel.setSize(new Dimension(WIDTH, HEIGHT));
        this.window.add(this.gamePanel);
        this.window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.window.pack();
        this.window.setLocationRelativeTo(null);
        this.window.setVisible(true);

        this.gamePanel.createBufferStrategy(2);
        this.gamePanel.requestFocus();

        this.gamePanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                e.getComponent().repaint();
                super.componentResized(e);
            }
        });

        this.render = new BufferStrategyRender(this.gamePanel.getBufferStrategy());
    }

    private void loop(){
        int calcTPS = 0, calcFPS = 0;
        double fpsTimer = System.currentTimeMillis();
        double secondsPerTick = 1D / TPS_CAP;
        double nsPerTick = secondsPerTick * 1000000000D;
        double then = System.nanoTime();
        double now;
        double unprocessed = 0;
        while (isRunning) {
            now = System.nanoTime();
            unprocessed += (now - then) / nsPerTick;
            then = now;
            while (unprocessed >= 1) {
                update();
                calcTPS++;
                unprocessed--;
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            render();
            calcFPS++;
            if (System.currentTimeMillis() - fpsTimer >= 1000) {
                this.fps = calcFPS;
                this.tps = calcTPS;
                calcFPS = 0;
                calcTPS = 0;
                fpsTimer += 1000;
            }
        }

        window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
    }

    private void update(){

    }

    private void render(){
        if(!doesGamePause){
            try {
                RenderEngine.render(this, this.render);
                this.render.show();
                //this.gamePanel.getBufferStrategy().getDrawGraphics().clearRect(0, 0, this.gamePanel.getWidth(), this.gamePanel.getHeight());
            } catch (Exception e){
                trowAlert("Exception while game rendering", e);
            }
        }
    }

    public void stopGame(){
        this.isRunning = false;
    }

    public static void trowAlert(String alert, Exception e){
        INSTANCE.doesGamePause = true;
        JFrame frame = new JFrame("Error");
        frame.setSize(new Dimension(640, 480));
        JTextArea text = new JTextArea();
        text.append(alert + "\r\n");
        if(e != null){
            StringWriter writer = new StringWriter();
            e.printStackTrace(new PrintWriter(writer));
            text.append(writer.toString());
        } else {
            text.append("No Exception Provided!");
        }
        frame.add(text);
        frame.addWindowListener(new WindowAdapter() {
            /**
             * Invoked when a window is in the process of being closed.
             * The close operation can be overridden at this point.
             *
             * @param e
             */
            @Override
            public void windowClosing(WindowEvent e) {
                INSTANCE.doesGamePause = false;
                //guiHandler.setActiveGui("GuiMainMenu");
            }
        });
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public int getFps() {
        return fps;
    }

    public int getTps() {
        return tps;
    }

    public int getWidth(){
        return this.gamePanel.getWidth();
    }

    public int getHeight(){
        return this.gamePanel.getHeight();
    }
}
