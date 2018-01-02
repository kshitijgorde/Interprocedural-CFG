// 
// Decompiled by Procyon v0.5.30
// 

package de.ts.b.a;

import java.awt.Image;

public class b extends j
{
    String ad;
    Image ab;
    int ac;
    
    public b(final String ad) {
        this.ad = null;
        this.ab = null;
        this.ac = 0;
        this.ad = ad;
    }
    
    public b(final String ad, final int n) {
        super(n);
        this.ad = null;
        this.ab = null;
        this.ac = 0;
        this.ad = ad;
    }
    
    public void a(final int ac) {
        this.ac = ac;
    }
    
    public String void() {
        return this.ad;
    }
    
    public int null() {
        return this.ac;
    }
    
    public boolean b() {
        return super.Q == 9;
    }
}
