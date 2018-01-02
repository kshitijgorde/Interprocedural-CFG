import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Color;
import java.applet.Applet;
import java.awt.MediaTracker;
import java.awt.Image;

// 
// Decompiled by Procyon v0.5.30
// 

public class MoonView extends BufferedCanvas
{
    protected Image fullMoonPic;
    protected Image newMoonPic;
    protected MediaTracker tracker;
    protected Applet itsApplet;
    protected boolean primed;
    protected double phase;
    protected int[] pixels;
    protected Color[] grays;
    
    public void createImageBuffer() {
        this.primed = false;
        super.imageBuffer = this.createImage(this.size().width * 2, this.size().height);
        if (super.imageBuffer != null) {
            this.draw(super.imageBuffer.getGraphics());
        }
    }
    
    public void initImage(final Applet anApplet) {
        this.itsApplet = anApplet;
        this.fullMoonPic = this.itsApplet.getImage(this.itsApplet.getCodeBase(), "full_moon.jpg");
        if (this.fullMoonPic != null) {
            (this.tracker = new MediaTracker(this.itsApplet)).addImage(this.fullMoonPic, 0);
        }
        this.newMoonPic = this.itsApplet.getImage(this.itsApplet.getCodeBase(), "new_moon.jpg");
        if (this.newMoonPic != null && this.tracker != null) {
            this.tracker.addImage(this.newMoonPic, 1);
        }
        this.refresh();
    }
    
    public boolean isReady() {
        return this.tracker != null && this.tracker.checkAll(true);
    }
    
    public double getPhase() {
        return this.phase;
    }
    
    public void setPhase(final double newPhase) {
        this.phase = newPhase;
    }
    
    public void draw(final Graphics g) {
        if (!this.isReady()) {
            return;
        }
        g.drawImage(this.fullMoonPic, 0, 0, this);
        if (Math.abs(this.phase - 180.0) < 0.125) {
            this.descrudge(g);
            return;
        }
        if (!this.primed && this.newMoonPic != null && super.imageBuffer != null) {
            super.imageBuffer.getGraphics().drawImage(this.newMoonPic, 256, 0, this);
            this.primed = true;
            if (System.getProperty("java.vendor").indexOf("Netscape") < 0 || System.getProperty("os.name").indexOf("Mac OS") < 0) {
                this.pixels = new int[131072];
                final PixelGrabber grabber = new PixelGrabber(super.imageBuffer, 0, 0, 512, 256, this.pixels, 0, 512);
                try {
                    if (!grabber.grabPixels()) {
                        this.pixels = null;
                    }
                }
                catch (InterruptedException ex) {
                    this.pixels = null;
                }
                this.grays = new Color[256];
                for (int j = 0; j < 256; ++j) {
                    this.grays[j] = new Color(65793 * j);
                }
            }
        }
        final double coverage = Math.cos(this.phase * 3.141592653589793 / 180.0) + 1.0;
        final int overlap = (this.pixels == null) ? 1 : 0;
        int wgt = 0;
        final Graphics g2 = super.imageBuffer.getGraphics();
        for (int y0 = -109; y0 <= 109; ++y0) {
            final int y2 = y0 + 128;
            final double x0 = Math.sqrt(11881.0 - y0 * y0);
            int x2;
            int x3;
            if (this.phase < 180.0) {
                final double edge1 = 128.0 - x0;
                final double edge2 = edge1 + x0 * coverage;
                x2 = (int)(edge1 - 3.0);
                x3 = (int)(edge2 + overlap);
                wgt = (int)(255.0 * (edge2 - Math.floor(edge2)));
            }
            else {
                final double edge2 = x0 + 128.0;
                final double edge1 = edge2 - x0 * coverage;
                x3 = (int)(edge2 + 3.0);
                x2 = (int)(edge1 - overlap);
                wgt = 255 - (int)(255.0 * (edge1 - Math.floor(edge1)));
            }
            if (this.phase < 0.125 || this.phase > 359.875) {
                --x2;
                ++x3;
            }
            if (this.primed) {
                g2.copyArea(x2 + 256, y2, x3 - x2, 1, -256, 0);
            }
            else {
                g.drawLine(x2, y2, x3, y2);
            }
            if (this.pixels != null) {
                int pixOffset;
                if (this.phase < 180.0) {
                    pixOffset = y2 * 512 + x3;
                }
                else {
                    pixOffset = y2 * 512 + x2 - 1;
                }
                int v0 = this.pixels[pixOffset] >> 8 & 0xFF;
                final int v2 = this.pixels[pixOffset + 256] >> 8 & 0xFF;
                v0 = v2 * wgt / 255 + v0 * (255 - wgt) / 255;
                if (v0 > 255) {
                    v0 = 255;
                }
                g.setColor(this.grays[v0]);
                if (this.phase < 180.0) {
                    g.drawLine(x3, y2, x3, y2);
                }
                else {
                    g.drawLine(x2 - 1, y2, x2 - 1, y2);
                }
                g.setColor(Color.black);
            }
        }
        this.descrudge(g);
    }
    
    protected void descrudge(final Graphics g) {
        g.setColor(Color.black);
        for (int j = 1; j < 10; ++j) {
            g.drawOval(19 - j, 19 - j, 218 + j * 2, 218 + j * 2);
        }
        g.drawLine(0, 19, 255, 19);
        g.drawLine(0, 237, 255, 237);
    }
    
    public MoonView() {
        this.fullMoonPic = null;
        this.newMoonPic = null;
        this.tracker = null;
        this.itsApplet = null;
        this.primed = false;
        this.phase = 0.0;
        this.pixels = null;
    }
}
