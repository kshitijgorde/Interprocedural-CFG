// 
// Decompiled by Procyon v0.5.30
// 

package com.impatica.v402;

public class ImRect extends ImBase
{
    public int L;
    public int M;
    public int N;
    public int O;
    
    final void C(final int l, final int n, final int m, final int o) {
        if (this.L < l) {
            this.L = l;
        }
        if (this.M > m) {
            this.M = m;
        }
        if (this.N < n) {
            this.N = n;
        }
        if (this.O > o) {
            this.O = o;
        }
    }
    
    final boolean B(final int n, final int n2) {
        return n >= this.L && n < this.M && n2 >= this.N && n2 < this.O;
    }
    
    final boolean C() {
        return this.L >= this.M || this.N >= this.O;
    }
    
    final boolean I(final ImRect imRect) {
        return this.L == imRect.L && this.N == imRect.N && this.M == imRect.M && this.O == imRect.O;
    }
    
    final boolean I(final ImRect imRect, final ImRect imRect2) {
        return this.I(imRect, imRect2.L, imRect2.N, imRect2.M, imRect2.O);
    }
    
    final boolean I(final ImRect imRect, final int n, final int n2, final int n3, final int n4) {
        this.L = ((imRect.L > n) ? imRect.L : n);
        this.N = ((imRect.N > n2) ? imRect.N : n2);
        this.M = ((imRect.M < n3) ? imRect.M : n3);
        this.O = ((imRect.O < n4) ? imRect.O : n4);
        return !this.C();
    }
    
    final void Z(final ImRect imRect) {
        this.B(imRect.L, imRect.N, imRect.M, imRect.O);
    }
    
    final void B(final int l, final int n, final int m, final int o) {
        this.L = l;
        this.N = n;
        this.M = m;
        this.O = o;
    }
    
    final void D(int n, int n2, int n3, int n4) {
        if (n > n3) {
            final int n5 = n;
            n = n3;
            n3 = n5;
        }
        else if (n == n3) {
            ++n3;
        }
        if (n2 > n4) {
            final int n6 = n2;
            n2 = n4;
            n4 = n6;
        }
        else if (n2 == n4) {
            ++n4;
        }
        this.B(n, n2, n3, n4);
    }
    
    final void C(final ImRect imRect) {
        this.J(imRect.L, imRect.N, imRect.M, imRect.O);
    }
    
    final void F(int n, int n2, int n3, int n4) {
        if (n > n3) {
            final int n5 = n;
            n = n3;
            n3 = n5;
        }
        else if (n == n3) {
            ++n3;
        }
        if (n2 > n4) {
            final int n6 = n2;
            n2 = n4;
            n4 = n6;
        }
        else if (n2 == n4) {
            ++n4;
        }
        this.J(n, n2, n3, n4);
    }
    
    private final void J(final int l, final int n, final int m, final int o) {
        if (this.C()) {
            this.B(l, n, m, o);
        }
        else if (l < m && n < o) {
            if (l < this.L) {
                this.L = l;
            }
            if (m > this.M) {
                this.M = m;
            }
            if (n < this.N) {
                this.N = n;
            }
            if (o > this.O) {
                this.O = o;
            }
        }
    }
}
