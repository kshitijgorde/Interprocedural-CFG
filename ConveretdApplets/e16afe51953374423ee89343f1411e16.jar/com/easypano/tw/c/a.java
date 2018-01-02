// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw.c;

import java.awt.image.PixelGrabber;
import com.easypano.tw.f;
import java.awt.image.ColorModel;
import java.awt.image.ImageConsumer;
import java.awt.image.ImageProducer;

public class a implements ImageProducer
{
    private ImageConsumer a;
    private ColorModel b;
    private int c;
    private int d;
    private int[] e;
    public static int f;
    
    public a(final ColorModel b, final int c, final int d, final int[] e) {
        final int f = com.easypano.tw.c.a.f;
        this.a = null;
        this.b = null;
        this.e = null;
        this.b = b;
        this.c = c;
        this.d = d;
        this.e = e;
        if (f != 0) {
            int k = com.easypano.tw.f.k;
            com.easypano.tw.f.k = ++k;
        }
    }
    
    public void addConsumer(final ImageConsumer a) {
        final int f = a.f;
        ImageConsumer a2 = a;
        if (f == 0) {
            if (a instanceof PixelGrabber) {
                a.setColorModel(this.b);
                a.setDimensions(this.c, this.d);
                a.setHints(30);
                try {
                    a.setPixels(0, 0, this.c, this.d, this.b, this.e, 0, this.c);
                    a.imageComplete(2);
                    return;
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                    if (f == 0) {
                        return;
                    }
                }
            }
            (this.a = a).setColorModel(this.b);
            this.a.setDimensions(this.c, this.d);
            a2 = this.a;
        }
        a2.setHints(30);
        this.a();
    }
    
    public void removeConsumer(final ImageConsumer imageConsumer) {
    }
    
    public void requestTopDownLeftRightResend(final ImageConsumer imageConsumer) {
    }
    
    public void startProduction(final ImageConsumer imageConsumer) {
        this.addConsumer(imageConsumer);
    }
    
    public boolean isConsumer(final ImageConsumer imageConsumer) {
        return false;
    }
    
    public void a() {
        final ImageConsumer a = this.a;
        Label_0049: {
            if (com.easypano.tw.c.a.f != 0) {
                break Label_0049;
            }
            if (a == null) {
                return;
            }
            try {
                this.a.setPixels(0, 0, this.c, this.d, this.b, this.e, 0, this.c);
                final ImageConsumer a2 = this.a;
                a.imageComplete(2);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
