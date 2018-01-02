// 
// Decompiled by Procyon v0.5.30
// 

package jmaster.util.D.A;

import java.io.Serializable;
import java.awt.image.RGBImageFilter;

public class C extends RGBImageFilter implements Serializable
{
    static final long B = 4823747720743333615L;
    private Q A;
    
    public C() {
        this.A = new G();
        this.canFilterIndexColorModel = true;
    }
    
    public void A(final Q a) {
        this.A = a;
    }
    
    public Q A() {
        return this.A;
    }
    
    public int filterRGB(final int n, final int n2, int n3) {
        n3 = ((n3 >> 16 & 0xFF) + (n3 >> 8 & 0xFF) + (n3 & 0xFF)) / 3;
        return this.A.A(n3 / 255.0f);
    }
    
    public String toString() {
        return "Colors/Lookup...";
    }
}
