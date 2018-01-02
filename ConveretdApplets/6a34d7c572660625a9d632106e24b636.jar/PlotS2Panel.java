import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

// 
// Decompiled by Procyon v0.5.30
// 

class PlotS2Panel extends Panel implements Runnable
{
    Seiche2Plot seiche2plot;
    Image wImage;
    Graphics wGraphics;
    public float d1;
    public float d2;
    public float L1;
    public float L2;
    public float T;
    boolean first;
    boolean stop;
    Thread thread;
    
    public PlotS2Panel() {
        this.Block$();
        this.setBackground(Color.lightGray);
    }
    
    public void set(final boolean stop) {
        this.stop = stop;
    }
    
    public Dimension minimumSize() {
        return new Dimension(300, 150);
    }
    
    public void paint(final Graphics graphics) {
        if (!this.first) {
            this.seiche2plot = new Seiche2Plot(this.bounds().width, this.bounds().height);
            this.wImage = this.createImage(this.bounds().width, this.bounds().height);
            this.wGraphics = this.wImage.getGraphics();
            this.first = true;
        }
        this.wGraphics.clearRect(0, 0, this.bounds().width, this.bounds().height);
        this.seiche2plot.Draw(this.wGraphics);
        graphics.drawImage(this.wImage, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void do_wave(final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        this.seiche2plot.initialize(n, n2, n3, n4, n5, n6);
        this.seiche2plot.do_wave();
        (this.thread = new Thread(this)).start();
    }
    
    public void run() {
        while (!this.stop) {
            this.seiche2plot.do_wave();
            this.repaint();
            try {
                Thread.sleep(150L);
            }
            catch (Exception ex) {}
        }
    }
    
    private void Block$() {
    }
}
