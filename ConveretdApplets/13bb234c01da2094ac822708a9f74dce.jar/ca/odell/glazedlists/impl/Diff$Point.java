// 
// Decompiled by Procyon v0.5.30
// 

package ca.odell.glazedlists.impl;

import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

class Diff$Point
{
    private int a;
    private int b;
    private Diff$Point c;
    
    public Diff$Point(final int a, final int b) {
        this.a = 0;
        this.b = 0;
        this.c = null;
        this.a = a;
        this.b = b;
    }
    
    public Diff$Point a(final int n, final int n2) {
        final Diff$Point diff$Point = new Diff$Point(this.a + n, this.b + n2);
        diff$Point.c = this;
        return diff$Point;
    }
    
    public Diff$Point a() {
        final Diff$Point a = this.a(1, 1);
        if (this.c != null && a.a - this.c.a == a.b - this.c.b) {
            a.c = this.c;
        }
        return a;
    }
    
    public int b() {
        return this.a;
    }
    
    public int c() {
        return this.b;
    }
    
    public boolean a(final Diff$Point diff$Point) {
        return this.a < diff$Point.a && this.b < diff$Point.b;
    }
    
    public boolean b(final Diff$Point diff$Point) {
        return this.a >= diff$Point.a && this.b >= diff$Point.b;
    }
    
    public String toString() {
        return "(" + this.a + "," + this.b + ")";
    }
    
    public List d() {
        final ArrayList<Diff$Point> list = new ArrayList<Diff$Point>();
        for (Diff$Point c = this; c != null; c = c.c) {
            list.add(c);
        }
        Collections.reverse(list);
        return list;
    }
}
