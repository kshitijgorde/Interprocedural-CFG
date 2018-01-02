// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Point;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.util.Vector;

public class dd
{
    k a;
    Vector b;
    int c;
    int d;
    int e;
    
    public dd(final k a) {
        this.a = null;
        this.b = new Vector();
        this.c = -1;
        this.d = -1;
        this.e = -1;
        (this.a = a).addMouseListener(new z(this));
        this.a.addMouseMotionListener(new bj(this));
        this.a.addKeyListener(new s(this));
    }
    
    private int a(final Point point) {
        return point.y / this.a.f();
    }
    
    private int a(int n) {
        final boolean q = g.q;
        int n3;
        final int n2 = n3 = n;
        if (!q) {
            if (n2 < 0) {
                n = 0;
            }
            final int n4;
            n3 = (n4 = n);
        }
        if (!q) {
            if (n2 >= this.b.size()) {
                n = this.b.size() - 1;
            }
            n3 = n;
        }
        return n3;
    }
    
    public void b(final int c) {
        if (c != this.c) {
            this.c = c;
            this.d = -1;
            this.d();
        }
    }
    
    public void c(final int n) {
        if (n != this.d) {
            this.c = n;
            this.d = n;
            this.a.b(n);
            this.d();
        }
    }
    
    public void d(final int e) {
        final boolean q = g.q;
        int n = e;
        final int size;
        Label_0040: {
            if (!q) {
                if (e >= 0) {
                    n = e;
                    size = this.b.size();
                    if (q) {
                        break Label_0040;
                    }
                    if (e < size) {
                        this.a.b(e);
                    }
                }
                n = e;
            }
            final int e2 = this.e;
        }
        if (n != size) {
            this.e = e;
            this.e();
        }
    }
    
    public void e(final int e) {
        final boolean q = g.q;
        int n = e;
        final int size;
        Label_0040: {
            if (!q) {
                if (e >= 0) {
                    n = e;
                    size = this.b.size();
                    if (q) {
                        break Label_0040;
                    }
                    if (e < size) {
                        this.a.b(e);
                    }
                }
                n = e;
            }
            final int e2 = this.e;
        }
        if (n != size) {
            this.e = e;
            this.a.g();
        }
    }
    
    public int a() {
        return this.c;
    }
    
    public int b() {
        return this.d;
    }
    
    public int c() {
        return this.e;
    }
    
    private void d() {
        final k a = this.a;
        if (!g.q) {
            if (a == null) {
                return;
            }
            final k a2 = this.a;
        }
        a.g();
    }
    
    private void e() {
        final k a = this.a;
        if (!g.q) {
            if (a == null) {
                return;
            }
            this.a.g();
            final k a2 = this.a;
        }
        a.h();
    }
    
    public void a(final Object o) {
        this.b.addElement(o);
    }
    
    public Vector f() {
        return this.b;
    }
    
    static int a(final dd dd, final Point point) {
        return dd.a(point);
    }
    
    static int a(final dd dd, final int n) {
        return dd.a(n);
    }
}
