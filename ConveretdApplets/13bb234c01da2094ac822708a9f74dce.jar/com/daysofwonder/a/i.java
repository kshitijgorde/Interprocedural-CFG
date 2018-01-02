// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.a;

import com.daysofwonder.tt.p;
import java.util.HashMap;
import java.util.Map;

public abstract class i
{
    protected int a;
    protected int b;
    protected float c;
    protected int d;
    protected int e;
    protected Map f;
    protected String g;
    protected int h;
    protected int i;
    private int k;
    protected boolean j;
    private boolean l;
    private boolean m;
    private a n;
    private boolean o;
    private int p;
    
    public i(final String g) {
        this.f = new HashMap();
        this.k = -1;
        this.g = g;
    }
    
    public i() {
        this.f = new HashMap();
        this.k = -1;
    }
    
    public int u() {
        return this.e;
    }
    
    public void a(final String g) {
        this.g = g;
    }
    
    public void b() {
        this.h = 0;
        this.a = 0;
        this.k = -1;
        this.p = 0;
    }
    
    public int v() {
        return this.a;
    }
    
    public void h(final int a) {
        this.a = a;
    }
    
    public int w() {
        return this.b;
    }
    
    public void i(final int b) {
        this.b = b;
    }
    
    public int x() {
        return this.d;
    }
    
    public void j(final int d) {
        this.d = d;
    }
    
    public float y() {
        return this.c;
    }
    
    public void a(final float c) {
        this.c = c;
    }
    
    public String z() {
        return this.g;
    }
    
    public int A() {
        return this.h;
    }
    
    public void k(final int h) {
        this.h = h;
    }
    
    public synchronized int B() {
        return this.i;
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(this.g);
        sb.append(" [score=");
        sb.append(this.h);
        sb.append(", wid=").append(this.w());
        sb.append(", lid=").append(this.v());
        sb.append(", K=").append(this.B()).append(']');
        return sb.toString();
    }
    
    public void l(final int k) {
        this.k = k;
    }
    
    public int C() {
        return this.k;
    }
    
    public void a(final boolean j) {
        this.j = j;
    }
    
    public void b(final boolean l) {
        this.l = l;
    }
    
    public boolean D() {
        return this.l;
    }
    
    public boolean E() {
        return this.m;
    }
    
    public void F() {
        this.m = true;
    }
    
    public void m(final int e) {
        this.e = e;
    }
    
    public void a(final a n) {
        this.n = n;
    }
    
    public a G() {
        return this.n;
    }
    
    public int b(String b) {
        b = com.daysofwonder.tt.p.b(b);
        if (this.f.containsKey(b)) {
            return this.f.get(b);
        }
        return 0;
    }
    
    public void a(final String s, final int n) {
        this.f.put(com.daysofwonder.tt.p.b(s), n);
    }
    
    public boolean H() {
        return this.o;
    }
    
    public void c(final boolean o) {
        this.o = o;
    }
    
    public void n(final int i) {
        this.i = i;
    }
    
    public void a(final Map f) {
        this.f = f;
    }
    
    public a g(final int n) {
        return this.G();
    }
}
