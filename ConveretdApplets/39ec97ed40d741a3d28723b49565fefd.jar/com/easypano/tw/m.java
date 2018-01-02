// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import com.easypano.tw.a.p;
import com.easypano.tw.a.n;

public class m extends e
{
    public static final int l = 100;
    public static final int m = 0;
    private int n;
    
    public m() {
        this.n = 0;
        this.a(new n(this));
    }
    
    public void b(final int n) {
        m m = this;
        if (!com.easypano.tw.g.q) {
            if (this.n == n) {
                return;
            }
            this.n = n;
            m = this;
        }
        m.f();
    }
    
    public int e() {
        return this.n;
    }
    
    public void f() {
        this.repaint();
    }
}
