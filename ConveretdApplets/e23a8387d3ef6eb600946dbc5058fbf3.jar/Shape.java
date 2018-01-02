import java.awt.Graphics;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

class Shape implements Runnable
{
    static int threadNum;
    private Color color;
    private Color bg;
    private int naptime;
    private int size;
    private int growthFactor;
    private int ox;
    private int oy;
    private Graphics graphics;
    private int current;
    private int previous;
    private boolean growing;
    public Thread thread;
    public boolean changed;
    ShapesApplet applet;
    
    public Shape(final ShapesApplet applet, final int ox, final int oy, final int current, final int naptime, final Color color, final Color color2) {
        this.bg = Color.black;
        this.naptime = 500;
        this.size = 50;
        this.growthFactor = 1;
        this.growing = false;
        this.changed = true;
        this.applet = applet;
        this.ox = ox;
        this.oy = oy;
        this.size = current;
        this.naptime = naptime;
        this.color = color;
        if (this.color == null) {
            this.color = Color.black;
        }
        this.previous = current;
        this.current = current;
    }
    
    public void run() {
        this.thread.setPriority(2);
        while (this.thread != null) {
            this.changed = true;
            this.previous = this.current;
            if (this.growing) {
                this.current += this.growthFactor;
            }
            else {
                this.current -= this.growthFactor;
            }
            if (this.current == this.size || this.current == 0) {
                this.growing = !this.growing;
            }
            try {
                Thread.sleep(this.naptime);
            }
            catch (InterruptedException ex) {}
            this.applet.repaint();
        }
        this.thread = null;
    }
    
    public void start() {
        if (this.thread == null) {
            (this.thread = new Thread(this, Integer.toString(Shape.threadNum++))).start();
        }
    }
    
    public void stop() {
        this.thread = null;
    }
    
    public void paint(final Graphics graphics) {
        final int n = this.previous / 2;
        final int n2 = this.current / 2;
        graphics.setColor(this.bg);
        graphics.fillRect(this.ox - n, this.oy - n, this.previous, this.previous);
        graphics.setColor(this.color);
        graphics.fillRect(this.ox - n2, this.oy - n2, this.current, this.current);
    }
    
    static {
        Shape.threadNum = 1;
    }
}
