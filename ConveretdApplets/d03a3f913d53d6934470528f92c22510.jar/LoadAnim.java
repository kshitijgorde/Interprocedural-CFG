import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Canvas;

// 
// Decompiled by Procyon v0.5.30
// 

class LoadAnim extends Canvas implements Runnable
{
    private Thread a;
    private boolean run;
    private Dimension dim;
    private int k;
    private Invaders inv;
    
    public LoadAnim(final Invaders inv) {
        this.k = 0;
        this.inv = inv;
        this.a = new Thread(this);
        this.setBackground(Color.black);
    }
    
    public void paint(final Graphics graphics) {
        this.dim = this.getSize();
        if (this.k % 2 == 0) {
            graphics.setColor(Color.red);
            graphics.fillRect(3, 3, this.dim.width - 6, this.dim.height - 6);
        }
        else {
            graphics.setColor(Color.green);
            graphics.fillRect(5, 5, this.dim.width - 10, this.dim.height - 10);
        }
    }
    
    public void run() {
        while (this.run) {
            try {
                Thread.sleep(500L);
            }
            catch (Exception ex) {}
            ++this.k;
            this.repaint();
            this.inv.repaint();
        }
    }
    
    public void startUp() {
        this.run = true;
        this.a.start();
    }
    
    public void stop() {
        this.run = false;
        this.a = null;
    }
}
