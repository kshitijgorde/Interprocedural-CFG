// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.applet;

import java.awt.Color;
import com.daysofwonder.b.a;
import java.awt.FontMetrics;

public class H implements P
{
    private int a;
    private String b;
    private Object c;
    private boolean d;
    
    public H(final String b, final int a, final Object c) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = true;
    }
    
    public H(final String b) {
        this.b = b;
        this.d = true;
    }
    
    public Object a() {
        return this.c;
    }
    
    public int b() {
        return this.a;
    }
    
    public String c() {
        return this.b;
    }
    
    public boolean d() {
        return this.d;
    }
    
    public void a(final boolean d) {
        this.d = d;
    }
    
    public void a(final int n, final int n2, final int n3, final int n4, final int n5, final FontMetrics fontMetrics, final a a, final Y y) {
        if (y != null && y.b() == n) {
            a.a(Color.gray);
            a.d(n2, n3, n4 - 4, n5);
        }
        if (this.d()) {
            a.a(Color.black);
        }
        else {
            a.a(Color.gray);
        }
        a.a(this.c(), n2, n3 + fontMetrics.getMaxAscent());
    }
}
