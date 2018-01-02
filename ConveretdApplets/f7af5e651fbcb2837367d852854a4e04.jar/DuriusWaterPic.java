import java.awt.Graphics;
import java.awt.Event;
import java.awt.Image;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.MemoryImageSource;
import java.awt.image.DirectColorModel;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;

// 
// Decompiled by Procyon v0.5.30
// 

public class DuriusWaterPic extends WrApp implements Runnable
{
    int z1;
    int z2;
    Screen32 z0;
    Screen32 z3;
    int z4;
    int z5;
    Waterpic z6;
    
    public void z0() {
        super.z1 = 15637;
        super.z26 = this.getParameter("image");
        final Image image = this.getImage(this.getDocumentBase(), super.z26);
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex) {}
        this.z5 = image.getWidth(this);
        this.z4 = image.getHeight(this);
        this.z2 = 0;
        this.z1 = 1;
        super.z26 = this.getParameter("noise");
        if (super.z26 != null) {
            this.z2 = Integer.parseInt(super.z26);
        }
        super.z26 = this.getParameter("mouse");
        if (super.z26 != null) {
            this.z1 = Integer.parseInt(super.z26);
        }
        this.z0 = new Screen32(this.z5, this.z4);
        this.z3 = new Screen32(this.z5, this.z4);
        this.z6 = new Waterpic(this.z5, this.z4);
        this.z3.z0(image);
        super.z26 = this.getParameter("dim");
        if (super.z26 != null) {
            this.z6.z1 = Integer.parseInt(super.z26);
        }
        super.z26 = this.getParameter("dotsize");
        if (super.z26 != null) {
            this.z6.z2 = Integer.parseInt(super.z26);
        }
        super.z26 = this.getParameter("ndotsize");
        if (super.z26 != null) {
            this.z6.z6 = Integer.parseInt(super.z26);
        }
        super.z6 = new DirectColorModel(32, 16711680, 65280, 255);
        super.z19 = new MemoryImageSource(this.z0.z2(), this.z0.z1(), super.z6, this.z0.z0(), 0, this.z0.z2());
        super.z16 = this.createImage(super.z19);
    }
    
    public void z3(final Event event, int n, int n2) {
        if (this.z1 == 1) {
            if (n > super.z3 - this.z6.z2) {
                n = super.z3 - this.z6.z2;
            }
            if (n2 > super.z2 - this.z6.z2) {
                n2 = super.z2 - this.z6.z2;
            }
            if (n < 0) {
                n = 0;
            }
            if (n2 < 0) {
                n2 = 0;
            }
            this.z6.z0(n, n2, 450, this.z6.z2);
        }
    }
    
    public void update(final Graphics graphics) {
        super.z27 = System.currentTimeMillis();
        if (super.z17 != null) {
            if (super.z28 == 0) {
                this.z6.z0();
                if (this.z2 == 1) {
                    this.z6.z0((int)(Math.random() * (this.z6.z7 >> 1) + (this.z6.z7 >> 2) - (this.z6.z6 >> 1)), (int)(Math.random() * (this.z6.z3 >> 1) + (this.z6.z3 >> 2) - (this.z6.z6 >> 1)), 200, this.z6.z6);
                }
                this.z6.z0(this.z0, this.z3);
            }
            else {
                this.showStatus("http://www.durius.com/");
            }
            super.z16.flush();
            graphics.drawImage(super.z16, 0, 0, this);
        }
        super.z13 = System.currentTimeMillis();
        final long n = super.z14 - (super.z13 - super.z27);
        if (n > 1L) {
            try {
                Thread.sleep(n);
                return;
            }
            catch (Exception ex) {
                return;
            }
        }
        try {
            Thread.sleep(1L);
        }
        catch (Exception ex2) {}
    }
}
