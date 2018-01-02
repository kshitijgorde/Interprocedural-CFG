// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

import java.awt.image.DataBufferUShort;
import java.awt.image.DataBufferInt;
import java.awt.image.ImageObserver;
import java.awt.image.BufferedImage;
import java.awt.Component;

public class bj extends i
{
    int D;
    short[] E;
    
    public bj() {
        this.D = 0;
        this.E = null;
    }
    
    public void a(final Component w, final boolean x, final l z) throws Exception {
        final int pixelSize = ((BufferedImage)w.createImage(1, 1)).getColorModel().getPixelSize();
        if (pixelSize != 32 && pixelSize != 24 && pixelSize != 16) {
            ac.Y = false;
            throw new Exception();
        }
        if (pixelSize == 16) {
            ac.Y = false;
        }
        super.x = x;
        super.w = w;
        super.z = z;
        if (z.v == null) {
            System.out.println("Render mode 1.");
        }
    }
    
    public void char() {
    }
    
    public void for() {
        try {
            if (!ac.Y) {
                try {
                    int n = 0;
                    while (true) {
                        final int n2;
                        this.E[n] = (short)(((n2 = super.p[n++]) >> 3 & 0x1F) | (n2 >> 5 & 0x7E0) | (n2 >> 8 & 0xF800));
                    }
                }
                catch (Exception ex) {}
            }
            this.else();
            super.w.getGraphics().drawImage(super.y, 0, 0, super.w);
        }
        catch (Exception ex2) {}
    }
    
    protected void else() {
        if (super.z.v != null && !super.z.v.goto) {
            if (!super.z.a && super.z.D != null) {
                final int width = super.z.D.getWidth(null);
                final int height = super.z.D.getHeight(null);
                int new1 = super.i - width >> 1;
                int do1 = super.t - height >> 1;
                if (super.z.new != -1000 && super.z.do != -1000) {
                    new1 = super.z.new;
                    do1 = super.z.do;
                }
                super.y.getGraphics().drawImage(super.z.D, new1, do1, super.w);
            }
            else if (super.z.a) {
                ap.a(super.v, this, super.i - super.v.i >> 1, super.t - super.v.t >> 1, super.v.i, super.v.t);
            }
        }
    }
    
    public void try() {
        if (super.i == super.z.width && super.t == super.z.height) {
            return;
        }
        super.i = super.z.width;
        super.t = super.z.height;
        super.s = super.i;
        super.j = super.t;
        super.y = null;
        System.gc();
        if (ac.Y) {
            super.y = super.w.createImage(super.i, super.t);
            super.p = ((DataBufferInt)((BufferedImage)super.y).getRaster().getDataBuffer()).getData();
            for (int i = 0; i < super.i * super.t; ++i) {
                super.p[i] = super.z.t;
            }
        }
        else {
            super.p = new int[super.i * super.t];
            super.y = super.w.createImage(super.i, super.t);
            this.E = ((DataBufferUShort)((BufferedImage)super.y).getRaster().getDataBuffer()).getData();
            final int t = super.z.t;
            final short n = (short)((t >> 3 & 0x1F) | (t >> 5 & 0x7E0) | (t >> 8 & 0xF800 & 0xFFFF));
            for (int j = 0; j < super.i * super.t; ++j) {
                super.p[j] = super.z.t;
                this.E[j] = n;
            }
        }
        if (super.z.v != null && !super.z.v.goto) {
            this.a(super.y.getGraphics(), super.i, super.t);
        }
    }
}
