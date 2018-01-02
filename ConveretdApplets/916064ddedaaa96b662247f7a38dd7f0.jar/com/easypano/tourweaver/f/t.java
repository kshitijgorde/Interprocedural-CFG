// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.f;

import java.awt.image.ImageProducer;
import java.awt.Toolkit;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.Dimension;
import java.awt.image.ImageObserver;
import com.easypano.tourweaver.a.e;
import java.awt.Image;
import com.easypano.tourweaver.a.d;

public class t extends s
{
    int[] A;
    int[] B;
    int[] C;
    d D;
    int E;
    int F;
    int G;
    
    public t() {
        this.A = null;
        this.B = null;
        this.C = null;
        this.D = null;
        this.F = 0;
        this.G = 0;
    }
    
    public boolean a(final h h) {
        return false;
    }
    
    public void c() {
        this.A = null;
        this.B = null;
        this.C = null;
        this.D = null;
        super.p = null;
        super.q = null;
        super.o = null;
    }
    
    public void a(final int n) {
        final boolean i = com.easypano.tourweaver.f.r.i;
        int[] array2;
        final int[] array = array2 = this.A;
        if (!i) {
            if (array == null) {
                return;
            }
            final int[] b;
            array2 = (b = this.B);
        }
        if (!i) {
            if (array == null) {
                return;
            }
            array2 = this.C;
        }
        if (array2 != null) {
            final int n2 = n * 256 / 100;
            int j = 0;
            while (j < this.E) {
                final int n3 = this.B[j];
                final int n4 = n3 >> 16 & 0xFF;
                final int n5 = n3 >> 8 & 0xFF;
                final int n6 = n3 & 0xFF;
                final int n7 = this.C[j];
                this.A[j] = (n4 - ((n4 - (n7 >> 16 & 0xFF)) * n2 >> 8) << 16 | n5 - ((n5 - (n7 >> 8 & 0xFF)) * n2 >> 8) << 8 | n6 - ((n6 - (n7 & 0xFF)) * n2 >> 8) | 0xFF000000);
                ++j;
                if (i) {
                    return;
                }
                if (i) {
                    break;
                }
            }
            final d d = this.D;
            if (!i) {
                if (d == null) {
                    return;
                }
                final d d2 = this.D;
            }
            d.c();
        }
    }
    
    public void a(final Image image, final Image image2, final Image image3) {
        Image image4 = image;
        if (!com.easypano.tourweaver.f.r.i) {
            if (image == null) {
                return;
            }
            image4 = image2;
        }
        if (image4 != null) {
            this.B = com.easypano.tourweaver.a.e.b(image);
            this.C = com.easypano.tourweaver.a.e.b(image2);
            this.F = image.getWidth(com.easypano.tourweaver.a.e.f);
            this.G = image.getHeight(com.easypano.tourweaver.a.e.f);
            this.E = this.F * this.G;
            this.A = new int[this.F * this.G];
            this.D = new d(new Dimension(this.F, this.G), new DirectColorModel(32, 16711680, 65280, 255, 0), this.A);
            super.o = Toolkit.getDefaultToolkit().createImage(this.D);
        }
    }
}
