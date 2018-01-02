import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Dimension;
import UTIL.SplashScreen;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SplashScreenTest extends Applet implements Runnable
{
    public SplashScreen ss;
    public Dimension dim;
    public int i;
    
    public SplashScreenTest() {
        this.i = 0;
    }
    
    public void init() {
        this.dim = this.getSize();
        this.ss = new SplashScreen(this.dim, "Meonlne");
    }
    
    public void start() {
        new Thread(this).start();
    }
    
    public void paint(final Graphics graphics) {
        if (this.i < 100) {
            ++this.i;
            this.ss.setStatus(this.i);
        }
        graphics.drawImage(this.ss.getImage(), 0, 0, this);
    }
    
    public void run() {
        while (this.i < 100) {
            this.repaint();
            try {
                Thread.sleep(500L);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void destroy() {
        System.gc();
        System.exit(0);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
