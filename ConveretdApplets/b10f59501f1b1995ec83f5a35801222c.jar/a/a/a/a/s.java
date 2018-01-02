// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.Image;

public class s
{
    public static final int[] a(final Image image, final int n) {
        final int[] data = ((DataBufferInt)((BufferedImage)image).getRaster().getDataBuffer()).getData();
        for (int i = 0; i < n; ++i) {
            data[i] = 0;
        }
        return data;
    }
}
