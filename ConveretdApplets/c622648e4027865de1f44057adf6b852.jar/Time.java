import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

public class Time extends Canvas implements Runnable
{
    Cardscreen cv;
    Memoryapplet mem;
    static boolean geof;
    Thread timeThread;
    int antoon;
    boolean running;
    static boolean timeup;
    
    static {
        Time.geof = true;
        Time.timeup = false;
    }
    
    public Time(final Cardscreen cv, final Memoryapplet memoryapplet) {
        this.antoon = 2;
        this.running = true;
        this.cv = cv;
        if (this.timeThread == null) {
            (this.timeThread = new Thread(this)).start();
        }
    }
    
    public void fix() {
    }
    
    public void paint(final Graphics graphics) {
        this.getSize();
        graphics.setFont(new Font("Verdana", 0, 20));
        graphics.setColor(Color.yellow);
        if (Time.geof) {
            graphics.setColor(Color.red);
            graphics.fillRoundRect(30, 10, 40, 300, 40, 40);
            Time.geof = false;
        }
        graphics.setColor(Color.black);
        graphics.fillRect(0, 320, 120, 100);
        graphics.fillRect(30, 10, 40, 300 - this.cv.k);
        graphics.setColor(Color.red);
        graphics.drawString("Level " + this.cv.level, 20, 340);
        graphics.drawString("Score", 23, 365);
        graphics.setColor(Color.yellow);
        graphics.drawString(String.valueOf(this.cv.score), 28, 390);
    }
    
    public void run() {
        while (this.running) {
            final Cardscreen cv = this.cv;
            cv.k -= this.cv.minus;
            if (this.cv.k < 0) {
                Time.timeup = true;
            }
            try {
                Thread.sleep(400L);
            }
            catch (InterruptedException ex) {}
            this.repaint();
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
