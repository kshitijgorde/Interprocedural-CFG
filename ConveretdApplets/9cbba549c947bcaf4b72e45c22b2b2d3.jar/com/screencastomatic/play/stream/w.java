// 
// Decompiled by Procyon v0.5.30
// 

package com.screencastomatic.play.stream;

import com.screencastomatic.play.c.b;
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
import java.awt.Color;
import java.awt.image.WritableRaster;
import java.awt.image.Raster;
import java.awt.CompositeContext;

class w implements CompositeContext
{
    final /* synthetic */ c a;
    
    w(final c a) {
        this.a = a;
    }
    
    public void compose(final Raster raster, final Raster raster2, final WritableRaster writableRaster) {
        final int min = Math.min(raster.getWidth(), raster2.getWidth());
        final int min2 = Math.min(raster.getHeight(), raster2.getHeight());
        final int n = this.a.a.c.width * this.a.a.c.height / 8;
        for (int i = 0; i < min2; ++i) {
            final Object dataElements = raster2.getDataElements(0, i, min, 1, null);
            for (int j = 0; j < min; ++j) {
                int rgb = (dataElements instanceof int[]) ? ((int[])dataElements)[j] : ((short[])dataElements)[j];
                final int n2 = (i * this.a.a.c.width + j) / 8;
                final int n3 = (i * this.a.a.c.width + j) % 8;
                final boolean b = b(this.a.a.a[n2], n3) == 0;
                final int a = b(this.a.a.a[n + n2], n3);
                if (b) {
                    rgb = Color.BLACK.getRGB();
                }
                final int n4 = (a == 0) ? rgb : ((rgb & 0xFF000000) | (~rgb & 0xFFFFFF));
                if (dataElements instanceof int[]) {
                    ((int[])dataElements)[j] = n4;
                }
                else {
                    ((short[])dataElements)[j] = (short)n4;
                }
            }
            writableRaster.setDataElements(0, i, min, 1, dataElements);
        }
    }
    
    public void dispose() {
    }
}
