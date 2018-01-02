// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import com.easypano.tw.d.p;

public class n extends f
{
    public static final int l = 100;
    public static final int m = 0;
    private int n;
    
    public n() {
        this.n = 0;
        this.a(new com.easypano.tw.d.n(this));
    }
    
    public void b(final int n) {
        n n2 = this;
        if (!com.easypano.tw.h.q) {
            if (this.n == n) {
                return;
            }
            this.n = n;
            n2 = this;
        }
        n2.f();
    }
    
    public int e() {
        return this.n;
    }
    
    public void f() {
        this.repaint();
    }
}
