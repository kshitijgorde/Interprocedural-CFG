import java.net.URL;
import java.awt.Color;
import java.io.IOException;
import java.awt.image.ImageProducer;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.LayoutManager;
import java.awt.GridLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class DiceGIF extends Applet
{
    ControlPanelGIF felt2;
    DiceCanvasGIF felt1;
    drawHistogram felt3;
    int ndice;
    int xbins;
    int[] xcount;
    double[] theoreticalCount;
    int[] die;
    int total_nrolls;
    DiceGIF applet;
    int[][] probs;
    Image[] dicegif;
    int imageWidth;
    int imageHeight;
    Graphics offscreenGraphics;
    Image offscreenImage;
    
    public DiceGIF() {
        this.ndice = 1;
        this.xbins = this.ndice * 5 + 1;
        this.xcount = new int[this.xbins];
        this.theoreticalCount = new double[this.xbins];
        this.die = new int[this.ndice];
        this.total_nrolls = 0;
        this.applet = this;
        this.probs = new int[][] { { 1, 1, 1, 1, 1, 1 }, { 1, 2, 3, 4, 5, 6, 5, 4, 3, 2, 1 }, { 1, 3, 6, 10, 15, 21, 25, 27, 27, 25, 21, 15, 10, 6, 3, 1 }, { 1, 4, 10, 20, 35, 56, 80, 104, 125, 140, 146, 140, 125, 104, 80, 10, 56, 35, 20, 4, 1 }, { 1, 5, 15, 35, 70, 126, 205, 305, 420, 540, 651, 735, 780, 780, 735, 651, 540, 420, 305, 205, 126, 70, 35, 15, 5, 1 }, { 1, 6, 21, 56, 126, 252, 456, 756, 1161, 1666, 2247, 2856, 3431, 3906, 4221, 4332, 4221, 3906, 3431, 2856, 2247, 1666, 1161, 756, 456, 252, 126, 56, 21, 6, 1 }, { 1, 7, 28, 84, 210, 462, 917, 1667, 2807, 4417, 6538, 9142, 12117, 15267, 18327, 20993, 22967, 24017, 24017, 22967, 20993, 18327, 15267, 12117, 9142, 6538, 4417, 2807, 1667, 917, 462, 210, 84, 28, 7, 1 }, { 1, 8, 36, 120, 330, 792, 1708, 3368, 6147, 10480, 16808, 25488, 36688, 50288, 65808, 82384, 98813, 113688, 125588, 133288, 135954, 133288, 125588, 113688, 98813, 82384, 65808, 50288, 36688, 25488, 16808, 10480, 6147, 3368, 1708, 792, 330, 120, 36, 8, 1 }, { 1, 9, 45, 165, 495, 1287, 2994, 6354, 12465, 22825, 39303, 63999, 98979, 145899, 205560, 277464, 359469, 447669, 536569, 619569, 689715, 740619, 767394, 767394, 740619, 689715, 619569, 536569, 447669, 359469, 277464, 205560, 145899, 98979, 63999, 39303, 22825, 12465, 6354, 2994, 1287, 495, 165, 45, 9, 1 } };
        this.dicegif = new Image[6];
    }
    
    @Override
    public void init() {
        this.setLayout(new GridLayout(1, 3));
        this.felt1 = new DiceCanvasGIF(this.die, this.ndice, this.applet, this.dicegif);
        final MediaTracker mediaTracker = new MediaTracker(this);
        for (int i = 0; i < 6; ++i) {
            final URL resource = this.getClass().getResource("die" + (i + 1) + ".gif");
            try {
                this.dicegif[i] = this.createImage((ImageProducer)resource.getContent());
            }
            catch (IOException ex) {
                System.err.println("couldn't find dice[" + i + ".gif");
            }
            mediaTracker.addImage(this.dicegif[i], i);
        }
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex2) {}
        if (mediaTracker.isErrorAny()) {
            this.showStatus("error loading images");
        }
        this.felt2 = new ControlPanelGIF(this.applet);
        this.felt3 = new drawHistogram(this.xcount, this.xbins, this.theoreticalCount, this.applet);
        this.felt1.setBackground(new Color(0, 140, 0));
        this.felt3.setBackground(new Color(0, 0, 140));
        this.add(this.felt1);
        this.add(this.felt2);
        this.add(this.felt3);
    }
    
    @Override
    public void paint(final Graphics graphics) {
        this.felt1.paint(graphics);
    }
    
    public void rollem(final int n) {
        for (int i = 0; i < n; ++i) {
            int n2 = 0;
            for (int j = 0; j < this.ndice; ++j) {
                this.die[j] = (int)Math.floor(1.0 + Math.random() * 6.0);
                n2 += this.die[j];
            }
            this.felt1.update(this.die, n2);
            final int[] xcount = this.xcount;
            final int n3 = n2 - this.ndice;
            ++xcount[n3];
            this.felt3.update(this.xcount);
            this.felt2.update();
        }
        this.total_nrolls += n;
        for (int k = 0; k < this.xbins; ++k) {
            this.theoreticalCount[k] = this.total_nrolls * this.probs[this.ndice - 1][k] * Math.pow(6.0, -1 * this.ndice);
        }
    }
    
    public void clear() {
        this.ndice = Integer.valueOf(this.felt2.ndice_cbg.getCurrent().getLabel());
        this.xbins = this.ndice * 5 + 1;
        this.xcount = new int[this.xbins];
        this.theoreticalCount = new double[this.xbins];
        this.total_nrolls = 0;
        this.die = new int[this.ndice];
        this.felt1.clearCanvas(this.ndice);
        this.felt3.clearHistogram(this.xcount, this.xbins, this.theoreticalCount);
    }
}
