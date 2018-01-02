import java.awt.Component;
import java.awt.Color;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class bikeCAD2000 extends Applet implements Runnable
{
    Thread animate;
    Santeria region;
    DinosaurJr controls;
    
    public void init() {
        this.setLayout(new GridLayout(2, 1));
        this.setBackground(Color.white);
        this.setForeground(Color.blue);
        this.region = new Santeria();
        this.controls = new DinosaurJr(this.region, this);
        this.add(this.region);
        this.add(this.controls);
    }
    
    public void start() {
        if (this.animate == null) {
            (this.animate = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.animate != null) {
            this.animate.stop();
            this.animate = null;
        }
    }
    
    public void run() {
        while (true) {
            final Santeria region = this.region;
            ++region.k;
            if (this.region.k == 5) {
                this.region.k = -5;
            }
            this.region.repaint();
            try {
                Thread.sleep(200L);
            }
            catch (InterruptedException ex) {}
        }
    }
}
