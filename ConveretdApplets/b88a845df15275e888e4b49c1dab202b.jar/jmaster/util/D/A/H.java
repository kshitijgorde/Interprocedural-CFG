// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.D.A;

import java.io.Serializable;
import java.awt.image.RGBImageFilter;

public abstract class H extends RGBImageFilter implements Serializable
{
    protected int[] D;
    protected int[] C;
    protected int[] A;
    protected boolean B;
    
    public H() {
        this.B = false;
        this.canFilterIndexColorModel = true;
    }
    
    public int filterRGB(final int n, final int n2, final int n3) {
        if (!this.B) {
            this.B();
            this.B = true;
        }
        return (n3 & 0xFF000000) | this.D[n3 >> 16 & 0xFF] << 16 | this.C[n3 >> 8 & 0xFF] << 8 | this.A[n3 & 0xFF];
    }
    
    public void setDimensions(final int n, final int n2) {
        this.B = false;
        super.setDimensions(n, n2);
    }
    
    protected void B() {
        final int[] a = this.A();
        this.A = a;
        this.C = a;
        this.D = a;
    }
    
    protected int[] A() {
        final int[] array = new int[256];
        for (int i = 0; i < 256; ++i) {
            array[i] = this.A(i);
        }
        return array;
    }
    
    protected int A(final int n) {
        return 0;
    }
}
