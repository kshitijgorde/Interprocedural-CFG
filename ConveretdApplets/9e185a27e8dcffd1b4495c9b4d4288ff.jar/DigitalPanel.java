import java.awt.Graphics;
import java.awt.event.MouseListener;
import java.awt.Color;
import java.awt.Image;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class DigitalPanel extends Canvas implements Runnable
{
    private DigitalDisplay digi;
    private boolean running;
    
    public DigitalPanel(final Image magiLabel) {
        this.digi = null;
        this.running = false;
        this.digi = new DigitalDisplay(magiLabel);
        this.setBackground(Color.black);
        this.addMouseListener(this.digi);
    }
    
    public DigitalPanel(final Color displayColour, final Image magiLabel) {
        this.digi = null;
        this.running = false;
        this.digi = new DigitalDisplay(displayColour, magiLabel);
        this.setBackground(Color.black);
        this.addMouseListener(this.digi);
    }
    
    public DigitalPanel(final Color displayColour, final int left, final int top, final int numCells, final Image magiLabel) {
        this.digi = null;
        this.running = false;
        this.digi = new DigitalDisplay(displayColour, left, top, numCells, magiLabel);
        this.setBackground(Color.black);
        this.addMouseListener(this.digi);
    }
    
    public void setDelay(final long delay) {
        this.digi.setDelay(delay);
    }
    
    public void loop(final long loopDelay) {
        this.digi.loop(loopDelay);
    }
    
    public void showMessage(final String message) {
        this.digi.showMessage(this, message);
        this.repaint();
    }
    
    public void showMessage(final String[] messages) {
        this.digi.showMessage(this, messages);
        this.repaint();
    }
    
    public void start() {
        this.digi.animate(true);
        this.running = true;
        new Thread(this).start();
    }
    
    public void stop() {
        this.digi.animate(false);
        this.running = false;
    }
    
    public void run() {
        while (this.running) {
            this.repaint(0L);
            try {
                Thread.currentThread();
                Thread.sleep(50L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void paint(final Graphics gfx) {
        this.digi.draw(gfx, false);
    }
    
    public void update(final Graphics gfx) {
        this.paint(gfx);
    }
}
