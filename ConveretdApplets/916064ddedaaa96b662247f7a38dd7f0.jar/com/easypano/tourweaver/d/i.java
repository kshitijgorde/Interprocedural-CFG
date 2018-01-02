// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.d;

import com.easypano.tourweaver.a.e;
import java.io.IOException;
import java.io.InputStream;
import java.util.Hashtable;
import java.io.BufferedInputStream;
import java.util.Vector;

public class i implements d
{
    public static int a;
    public static int b;
    public static int c;
    public static int d;
    public static int e;
    public static int f;
    public static int g;
    public static int h;
    public static int i;
    public static int j;
    Vector k;
    byte[] l;
    BufferedInputStream m;
    a n;
    private byte[] o;
    int p;
    Hashtable q;
    Hashtable r;
    int s;
    int t;
    public static boolean u;
    
    public i(final InputStream inputStream) {
        this.k = new Vector();
        this.o = new byte[4];
        this.p = 0;
        this.q = new Hashtable();
        this.r = new Hashtable();
        this.s = 0;
        this.t = 0;
        this.a(inputStream);
    }
    
    public void a(final InputStream inputStream) {
        this.e();
        this.m = new BufferedInputStream(inputStream);
    }
    
    public void a() throws IOException {
        final BufferedInputStream m = this.m;
        if (!com.easypano.tourweaver.d.i.u) {
            if (m == null) {
                return;
            }
            final BufferedInputStream i = this.m;
        }
        m.close();
    }
    
    public void b() throws IOException {
        this.c();
    }
    
    public synchronized void a(final h h) {
        Vector vector2;
        final Vector vector = vector2 = this.k;
        h h2 = h;
        if (!com.easypano.tourweaver.d.i.u) {
            if (vector.contains(h)) {
                return;
            }
            vector2 = this.k;
            h2 = h;
        }
        vector2.addElement(h2);
    }
    
    public synchronized void b(final h h) {
        this.k.removeElement(h);
    }
    
    private void b(final InputStream inputStream) throws IOException {
        final boolean u = com.easypano.tourweaver.d.i.u;
        while (com.easypano.tourweaver.d.a.a(inputStream, this.o) != 0) {
            final String s = new String(this.o);
            this.a(4);
            final a a = new a(this.o);
            a.a(this);
            a.a(inputStream);
            final boolean equals = s.equals(com.easypano.tourweaver.d.a.p);
            if (!u) {
                if (equals) {
                    this.a(a, com.easypano.tourweaver.d.a.p + new Integer(this.g() + 1).toString());
                }
                s.equals(com.easypano.tourweaver.d.a.y);
            }
            if (equals) {
                this.b(a, com.easypano.tourweaver.d.a.y + new Integer(this.h() + 1).toString());
            }
            if (u) {
                break;
            }
        }
    }
    
    public void c() throws IOException {
        this.e();
        this.b(this.m);
        this.m.close();
    }
    
    synchronized void a(final byte[] array, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10) {
        final boolean u = com.easypano.tourweaver.d.i.u;
        int i = 0;
        while (i < this.k.size()) {
            ((h)this.k.elementAt(i)).a(array, n, n2, n3, n4, n5, n6, n7, n8, n9, n10);
            ++i;
            if (u) {
                break;
            }
        }
    }
    
    public void a(final int n) {
        this.p += n;
    }
    
    public int d() {
        return this.p;
    }
    
    public void e() {
        final boolean u = com.easypano.tourweaver.d.i.u;
        this.p = 0;
        int i = 1;
        while (true) {
            while (i <= this.s) {
                this.r.remove(com.easypano.tourweaver.d.a.p + new Integer(i).toString());
                ++i;
                if (u) {
                    while (i < this.t) {
                        this.q.remove(com.easypano.tourweaver.d.a.y + new Integer(i).toString());
                        ++i;
                        if (u) {
                            return;
                        }
                        if (u) {
                            break;
                        }
                    }
                    this.s = 0;
                    this.t = 0;
                    return;
                }
                if (u) {
                    break;
                }
            }
            i = 1;
            continue;
        }
    }
    
    public void f() {
        i i = this;
        if (!com.easypano.tourweaver.d.i.u) {
            if (this.n != null) {
                this.n.a();
            }
            com.easypano.tourweaver.a.e.a(this.q);
            com.easypano.tourweaver.a.e.a(this.r);
            this.q = null;
            i = this;
        }
        i.r = null;
    }
    
    public void a(final a a, final String s) {
        this.r.put(s, a);
        ++this.s;
    }
    
    public void b(final a a, final String s) {
        this.q.put(s, a);
        ++this.t;
    }
    
    public int g() {
        return this.s;
    }
    
    public int h() {
        return this.t;
    }
    
    static {
        com.easypano.tourweaver.d.i.a = 15;
        com.easypano.tourweaver.d.i.b = 240;
        com.easypano.tourweaver.d.i.c = 3840;
        com.easypano.tourweaver.d.i.d = 61440;
        com.easypano.tourweaver.d.i.e = 983040;
        com.easypano.tourweaver.d.i.f = 1;
        com.easypano.tourweaver.d.i.g = 2;
        com.easypano.tourweaver.d.i.h = 3;
        com.easypano.tourweaver.d.i.i = 1;
        com.easypano.tourweaver.d.i.j = 2;
    }
}
