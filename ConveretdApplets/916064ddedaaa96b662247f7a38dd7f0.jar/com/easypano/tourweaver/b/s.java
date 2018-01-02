// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.b;

import java.awt.Dimension;

public class s extends f
{
    Dimension v;
    int w;
    int x;
    
    public s() {
        this.v = new Dimension();
        this.w = 0;
        this.x = 0;
    }
    
    public void a(final int w, final int x) {
        this.w = w;
        this.x = x;
    }
    
    public int h() {
        return this.w;
    }
    
    public int l() {
        return this.x;
    }
    
    public void a(final double n, final double n2) {
    }
    
    public void b(final int n, final int n2) {
        this.v.setSize(n, n2);
    }
    
    public Dimension m() {
        return this.v;
    }
}
