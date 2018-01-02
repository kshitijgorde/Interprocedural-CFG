// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.c;

public class e
{
    private boolean a;
    private int b;
    private int[] c;
    
    e() {
        this.a = false;
        this.b = -1;
        this.c = null;
    }
    
    void a(final int[] c) {
        this.c = c;
    }
    
    int[] a() {
        return this.c;
    }
    
    int a(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        return this.c[((n2 / n4 & n5) << n) + (n3 & n6)];
    }
    
    int a(final int n, final int n2, final int n3, final int n4, final int n5, final boolean b) {
        return this.c[((n2 & n4) << n) + (n3 & n5)];
    }
    
    int a(final int n, final int n2, final int n3, final int n4, final int n5) {
        return this.c[(n / n3 << n4) + (n2 & n5)];
    }
}
