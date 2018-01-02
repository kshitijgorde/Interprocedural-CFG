// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw.b;

import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import com.easypano.tw.ds;
import java.awt.Image;

public class a implements c
{
    int[] a;
    int[] b;
    int[] c;
    int d;
    
    public a() {
        this.a = null;
        this.b = null;
        this.c = null;
    }
    
    public void a(final Image image, final Image image2) {
        if (image != null && image2 != null) {
            final int width = image.getWidth(ds.d);
            final int height = image.getHeight(ds.d);
            this.a = new int[width * height];
            final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, width, height, this.a, 0, width);
            try {
                pixelGrabber.grabPixels();
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            this.b = new int[width * height];
            final PixelGrabber pixelGrabber2 = new PixelGrabber(image2, 0, 0, width, height, this.b, 0, width);
            try {
                pixelGrabber2.grabPixels();
            }
            catch (InterruptedException ex2) {
                ex2.printStackTrace();
            }
        }
    }
    
    public void a(final int[] c) {
        this.c = c;
        this.d = c.length;
    }
    
    public void a(final Image image) {
    }
    
    public void a(final int n) {
        if (this.c != null && this.a != null && this.b != null) {
            final int n2 = n * 256 / 100;
            for (int i = 0; i < this.d; ++i) {
                final int n3 = this.a[i];
                final int n4 = n3 >> 16 & 0xFF;
                final int n5 = n3 >> 8 & 0xFF;
                final int n6 = n3 & 0xFF;
                final int n7 = this.b[i];
                this.c[i] = (n4 - ((n4 - (n7 >> 16 & 0xFF)) * n2 >> 8) << 16 | n5 - ((n5 - (n7 >> 8 & 0xFF)) * n2 >> 8) << 8 | n6 - ((n6 - (n7 & 0xFF)) * n2 >> 8) | 0xFF000000);
            }
        }
    }
    
    public int a() {
        return 2;
    }
    
    public void b() {
        this.a = null;
        this.b = null;
        this.c = null;
    }
}
