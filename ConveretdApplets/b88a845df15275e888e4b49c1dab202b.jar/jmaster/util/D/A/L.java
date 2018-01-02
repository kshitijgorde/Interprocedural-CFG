// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.D.A;

import java.awt.image.BufferedImage;

public class L implements Q
{
    protected int[] A;
    
    public L(final BufferedImage bufferedImage) {
        this.A = new int[256];
        for (int i = 0; i < 256; ++i) {
            this.A[i] = bufferedImage.getRGB(i, 0);
        }
    }
    
    public int A(final float n) {
        return this.A[(int)(n * 255.0)];
    }
}
