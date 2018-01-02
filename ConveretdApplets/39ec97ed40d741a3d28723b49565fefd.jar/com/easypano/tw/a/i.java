// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw.a;

import com.easypano.tw.g;

public class i extends c
{
    public static final int S = 1;
    public static final int T = 2;
    public static final int U = 3;
    protected int V;
    
    public i(final g g) {
        super(g, false);
        this.V = 1;
    }
    
    public void f(final int v) {
        final boolean o = com.easypano.tw.a.a.O;
        int n = v;
        int n2 = v;
        int n5;
        int n4;
        final int n3 = n4 = (n5 = 1);
        Label_0027: {
            if (!o) {
                if (v == n3) {
                    break Label_0027;
                }
                n = v;
                n2 = v;
                n5 = (n4 = 2);
            }
            if (!o) {
                if (n2 == n4) {
                    break Label_0027;
                }
                n = v;
                n5 = 3;
            }
            if (n != n5) {
                return;
            }
        }
        this.V = v;
    }
    
    protected void c() {
        final boolean o = com.easypano.tw.a.a.O;
        super.c();
        i i = this;
        Label_0064: {
            Label_0054: {
                if (!o) {
                    switch (this.V) {
                        case 2: {
                            i = this;
                            break;
                        }
                        case 3: {
                            break Label_0054;
                        }
                        case 1: {
                            break Label_0064;
                        }
                    }
                }
                i.M = 22;
                if (!o) {
                    return;
                }
            }
            super.M = 21;
            if (!o) {
                return;
            }
        }
        i j = this;
        if (!o) {
            if (super.k.e().f()) {
                super.M = 22;
                if (!o) {
                    return;
                }
            }
            j = this;
        }
        j.M = 21;
    }
}
