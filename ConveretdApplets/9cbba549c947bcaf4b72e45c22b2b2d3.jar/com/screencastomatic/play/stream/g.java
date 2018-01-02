// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play.stream;

import com.screencastomatic.play.c.b;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.Composite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.io.ObjectInputStream;
import java.io.InputStream;
import java.io.DataInputStream;
import java.awt.Image;
import java.awt.Point;
import java.awt.Dimension;
import java.awt.image.WritableRaster;
import java.awt.image.Raster;
import java.awt.CompositeContext;

class g implements CompositeContext
{
    final /* synthetic */ f a;
    
    g(final f a) {
        this.a = a;
    }
    
    public void compose(final Raster raster, final Raster raster2, final WritableRaster writableRaster) {
        final int min = Math.min(raster.getWidth(), raster2.getWidth());
        final int min2 = Math.min(raster.getHeight(), raster2.getHeight());
        final int n = this.a.a.c.width * this.a.a.c.height / 8;
        final boolean a = this.a();
        final int[] array = new int[min];
        for (int i = 0; i < min2; ++i) {
            raster2.getDataElements(0, i, min, 1, array);
            for (int j = 0; j < min; ++j) {
                int n2 = array[j];
                final int n3 = i * this.a.a.c.width + j;
                final boolean b = b(this.a.a.a[n3 / 8], n3 % 8) == 0;
                int n4 = n + n3 * 4;
                final byte b2 = this.a.a.a[n4++];
                final byte b3 = this.a.a.a[n4++];
                final byte b4 = this.a.a.a[n4++];
                final byte b5 = this.a.a.a[n4];
                int n5 = 0x0 | (b2 & 0xFF) | (b3 << 8 & 0xFF00) | (b4 << 16 & 0xFF0000);
                if (b5 != 0) {
                    final int n6 = n2 & 0xFF;
                    final int n7 = n2 >> 8 & 0xFF;
                    final int n8 = n2 >> 16 & 0xFF;
                    final int n9 = b5 & 0xFF;
                    n5 = (n5 | (this.a(b2, n6, n9) & 0xFF) | (this.a(b3, n7, n9) << 8 & 0xFF00) | (this.a(b4, n8, n9) << 16 & 0xFF0000));
                }
                else if (a) {
                    n5 = n2;
                }
                if (b && !a) {
                    n2 = 0;
                }
                array[j] = ((a ? n5 : (n5 ^ n2)) | 0xFF000000);
            }
            writableRaster.setDataElements(0, i, min, 1, array);
        }
    }
    
    public void dispose() {
    }
    
    private int a(final int n, final int n2, final int n3) {
        return Math.min(255, (int)(n + (1.0 - n3 / 255.0f) * n2));
    }
    
    private boolean a() {
        for (int n = this.a.a.c.width * this.a.a.c.height / 8, i = 0; i < n; ++i) {
            if (this.a.a.a[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
