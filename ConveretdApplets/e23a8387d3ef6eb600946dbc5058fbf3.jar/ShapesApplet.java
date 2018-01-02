import java.awt.Event;
import java.awt.image.ImageObserver;
import java.util.Random;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ShapesApplet extends Applet
{
    public int W;
    public int H;
    public int N;
    public final int minSize = 10;
    public final int maxSize = 50;
    public final int minNaptime = 1;
    public final int maxNaptime = 500;
    public final Color[] colors;
    public final Color bg;
    boolean threadSuspended;
    Image im;
    Graphics offscreen;
    Random rand;
    Shape[] shapes;
    
    public void init() {
        this.resize(this.W, this.H);
        this.rand = new Random(System.currentTimeMillis());
        try {
            this.im = this.createImage(this.W, this.H);
            this.offscreen = this.im.getGraphics();
        }
        catch (Exception ex) {
            this.offscreen = null;
        }
    }
    
    public void start() {
        for (int i = 0; i < this.N; ++i) {
            if (this.shapes[i] == null) {
                (this.shapes[i] = new Shape(this, (int)(this.W * this.rand.nextDouble()), (int)(this.H * this.rand.nextDouble()), 10 + (int)(40.0 * this.rand.nextDouble()), 1 + (int)(499.0 * this.rand.nextDouble()), this.colors[(int)(this.colors.length * this.rand.nextDouble())], this.bg)).start();
            }
        }
        this.repaint();
    }
    
    public void stop() {
        for (int i = 0; i < this.N; ++i) {
            this.shapes[i].stop();
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.offscreen != null) {
            this.paintApplet(this.offscreen);
            graphics.drawImage(this.im, 0, 0, this);
            return;
        }
        this.paintApplet(graphics);
    }
    
    public void paintApplet(final Graphics graphics) {
        graphics.setColor(this.bg);
        graphics.fillRect(0, 0, this.W, this.H);
        for (int i = 0; i < this.N; ++i) {
            this.shapes[i].paint(graphics);
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.threadSuspended) {
            for (int i = 0; i < this.N; ++i) {
                this.shapes[i].thread.resume();
            }
        }
        else {
            for (int j = 0; j < this.N; ++j) {
                this.shapes[j].thread.suspend();
            }
        }
        this.threadSuspended = !this.threadSuspended;
        return true;
    }
    
    public ShapesApplet() {
        this.W = 400;
        this.H = 100;
        this.N = 15;
        this.colors = new Color[] { Color.red, Color.pink, Color.orange, Color.yellow, Color.green, Color.magenta, Color.blue, Color.cyan, Color.white, Color.gray, Color.lightGray, Color.darkGray };
        this.bg = Color.black;
        this.threadSuspended = false;
        this.shapes = new Shape[this.N];
    }
}
