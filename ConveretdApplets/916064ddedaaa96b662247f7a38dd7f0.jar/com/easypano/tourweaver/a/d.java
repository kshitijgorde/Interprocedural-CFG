// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.a;

import java.awt.image.PixelGrabber;
import java.awt.image.DirectColorModel;
import java.awt.Dimension;
import java.awt.image.ImageConsumer;
import java.awt.image.ColorModel;
import java.awt.image.ImageProducer;

public class d implements ImageProducer
{
    ColorModel a;
    ImageConsumer b;
    Dimension c;
    int[] d;
    
    public d(final Dimension c, final ColorModel a, final int[] d) {
        this.c = c;
        this.a = a;
        this.d = d;
    }
    
    public d(final Dimension dimension, final int[] array) {
        this(dimension, new DirectColorModel(24, 16711680, 65280, 255), array);
    }
    
    public void addConsumer(final ImageConsumer b) {
        final int l = e.l;
        ImageConsumer b2 = b;
        if (l == 0) {
            if (b instanceof PixelGrabber) {
                b.setColorModel(this.a);
                b.setDimensions(this.c.width, this.c.height);
                b.setHints(30);
                try {
                    b.setPixels(0, 0, this.c.width, this.c.height, this.a, this.d, 0, this.c.width);
                    b.imageComplete(2);
                    return;
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    if (l == 0) {
                        return;
                    }
                }
            }
            (this.b = b).setColorModel(this.a);
            this.b.setDimensions(this.c.width, this.c.height);
            b2 = this.b;
        }
        b2.setHints(30);
        this.c();
    }
    
    public ImageConsumer a() {
        return this.b;
    }
    
    public boolean isConsumer(final ImageConsumer imageConsumer) {
        return true;
    }
    
    public void removeConsumer(final ImageConsumer imageConsumer) {
    }
    
    public void requestTopDownLeftRightResend(final ImageConsumer imageConsumer) {
    }
    
    public void a(final ColorModel a) {
        this.a = a;
    }
    
    public void a(final int[] d) {
        try {
            this.d = d;
            this.b.setPixels(0, 0, this.c.width, this.c.height, this.a, d, 0, this.c.width);
            this.b.imageComplete(2);
        }
        catch (Exception ex) {}
    }
    
    public void startProduction(final ImageConsumer imageConsumer) {
        this.addConsumer(imageConsumer);
    }
    
    public int[] b() {
        return this.d;
    }
    
    public void c() {
        final ImageConsumer b = this.b;
        Label_0058: {
            if (e.l != 0) {
                break Label_0058;
            }
            if (b == null) {
                return;
            }
            try {
                this.b.setPixels(0, 0, this.c.width, this.c.height, this.a, this.d, 0, this.c.width);
                final ImageConsumer b2 = this.b;
                b.imageComplete(2);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
