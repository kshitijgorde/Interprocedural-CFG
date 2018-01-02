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

class t implements CompositeContext
{
    final /* synthetic */ l a;
    
    t(final l a) {
        this.a = a;
    }
    
    public void compose(final Raster raster, final Raster raster2, final WritableRaster writableRaster) {
        final int min = Math.min(raster.getWidth(), raster2.getWidth());
        final int min2 = Math.min(raster.getHeight(), raster2.getHeight());
        final int n = this.a.a.c.width * this.a.a.c.height / 8;
        final short[] array = new short[min];
        for (int i = 0; i < min2; ++i) {
            raster2.getDataElements(0, i, min, 1, array);
            for (int j = 0; j < min; ++j) {
                short n2 = array[j];
                final int n3 = i * this.a.a.c.width + j;
                final boolean b = b(this.a.a.a[n3 / 8], n3 % 8) == 0;
                int n4 = n + n3 * 2;
                final int n5 = 0x0 | (this.a.a.a[n4++] & 0xFF) | (this.a.a.a[n4] << 8 & 0xFF00);
                final int n6 = (byte)(n5 >> 10 & 0x1F) << 11 | (byte)(n5 >> 5 & 0x1F) << 6 | (byte)(n5 & 0x1F);
                if (b) {
                    n2 = 0;
                }
                array[j] = (short)(n6 ^ n2);
            }
            writableRaster.setDataElements(0, i, min, 1, array);
        }
    }
    
    public void dispose() {
    }
}
