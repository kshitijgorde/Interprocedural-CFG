// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.c;

public class d
{
    private int[] a;
    private int b;
    
    d(final int[] a) {
        this.a = null;
        this.b = -1;
        this.a = a;
    }
    
    void a(final int b) {
        this.b = b;
    }
    
    int[] a(final c c) {
        return c.a(this.a);
    }
    
    int a() {
        return this.b;
    }
}
