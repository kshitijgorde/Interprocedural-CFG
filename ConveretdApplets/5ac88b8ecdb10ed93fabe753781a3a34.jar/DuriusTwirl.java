import java.awt.Graphics;
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

public class DuriusTwirl extends WrApp implements Runnable
{
    int z1;
    double z11;
    double z5;
    double z0;
    double z2;
    int z3;
    int z4;
    int z12;
    Screen32 z6;
    Screen32 z7;
    int z23;
    int z24;
    Light z8;
    double z9;
    double z10;
    
    public void z0() {
        super.z1 = 17565;
        super.z26 = this.getParameter("image");
        final Image image = this.getImage(this.getDocumentBase(), super.z26);
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(image, 0);
        try {
            mediaTracker.waitForAll();
        }
        catch (InterruptedException ex) {}
        this.z24 = image.getWidth(this);
        this.z23 = image.getHeight(this);
        this.z5 = 0.0;
        this.z11 = 0.0;
        this.z12 = 0;
        this.z4 = 1;
        super.z26 = this.getParameter("noise");
        if (super.z26 != null) {
            this.z12 = Integer.parseInt(super.z26);
        }
        super.z26 = this.getParameter("mouse");
        if (super.z26 != null) {
            this.z4 = Integer.parseInt(super.z26);
        }
        super.z26 = this.getParameter("angle");
        if (super.z26 != null) {
            this.z1 = Integer.parseInt(super.z26);
        }
        int int1 = 70;
        int int2 = 70;
        super.z26 = this.getParameter("lightsizex");
        if (super.z26 != null) {
            int1 = Integer.parseInt(super.z26);
        }
        super.z26 = this.getParameter("lightsizey");
        if (super.z26 != null) {
            int2 = Integer.parseInt(super.z26);
        }
        super.z26 = this.getParameter("light");
        if (super.z26 != null) {
            this.z3 = Integer.parseInt(super.z26);
        }
        (this.z8 = new Light(int1, int2, 0, 0)).z0(this.z3, this.z3, this.z3, this.z3);
        this.z6 = new Screen32(this.z24, this.z23);
        this.z7 = new Screen32(this.z24, this.z23);
        super.z6 = new DirectColorModel(32, 16711680, 65280, 255);
        super.z19 = new MemoryImageSource(this.z6.z2(), this.z6.z1(), super.z6, this.z6.z0(), 0, this.z6.z2());
        super.z16 = this.createImage(super.z19);
        this.z7.z0(image);
    }
    
    public DuriusTwirl() {
        this.z3 = 128;
        this.z1 = 50;
    }
    
    private void z2() {
        double n = super.z3 / 2;
        if (n > super.z2 / 2) {
            n = super.z2 / 2;
        }
        final int n2 = super.z3 / 2 + (int)this.z9;
        final int n3 = super.z2 / 2 + (int)this.z10;
        for (int i = 0; i < super.z2; ++i) {
            for (int j = 0; j < super.z3; ++j) {
                final double n4 = j - n2;
                final double n5 = i - n3;
                final double n6 = (this.z11 - (n4 + n5) * this.z11 / n) * 3.141592654 / 180.0;
                final double sin = Math.sin(n6);
                final double cos = Math.cos(n6);
                final int n7 = (int)(n4 * cos - n5 * sin + n2);
                final int n8 = (int)(n4 * sin + n5 * cos + n3);
                if (n7 > 0 && n7 < super.z3 - 1 && n8 > 0 && n8 < super.z2 - 1) {
                    final double n9 = n7 - n7;
                    final double n10 = n8 - n8;
                    this.z6.z0[this.z6.z5[i] + j] = this.z7.z0[this.z7.z5[n8] + n7];
                }
                else {
                    this.z6.z0[this.z6.z5[i] + j] = super.z4;
                }
            }
        }
    }
    
    public void update(final Graphics graphics) {
        if (super.z17 != null) {
            super.z27 = System.currentTimeMillis();
            this.z2();
            if (this.z3 != 0) {
                this.z8.z0((int)(Math.sin(this.z8.z0) * 70.0 + (this.z6.z4 >> 1)), (int)(Math.sin(this.z8.z4) * 90.0) + (this.z6.z1 >> 1));
                final Light z8 = this.z8;
                z8.z0 += 0.052;
                final Light z9 = this.z8;
                z9.z4 += 0.07;
                this.z8.z0(this.z6, this.z6);
            }
            this.z11 = Math.sin(this.z5) * this.z1;
            this.z9 = Math.sin(this.z0) * (super.z3 >> 1);
            this.z10 = Math.cos(this.z2) * (super.z2 >> 1);
            this.z5 -= 0.034;
            this.z0 += 0.028;
            this.z2 += 0.041;
            super.z16.flush();
            graphics.drawImage(super.z16, 0, 0, this);
            super.z13 = System.currentTimeMillis();
            final long n = super.z14 - (super.z13 - super.z27);
            if (n > 0L) {
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
}
