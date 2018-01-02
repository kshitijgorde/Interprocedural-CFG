// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.req;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.InputStream;
import java.util.zip.InflaterInputStream;
import java.io.ByteArrayInputStream;
import java.util.Enumeration;
import java.util.Vector;
import com.daysofwonder.a.n;

public class b
{
    public int a;
    public boolean b;
    public String c;
    public String[] d;
    public int[] e;
    public int[] f;
    public int[] g;
    public boolean[] h;
    public int[] i;
    public int j;
    public boolean k;
    public boolean l;
    public boolean m;
    public boolean n;
    public int o;
    public int p;
    public n q;
    
    private b(final int a, final String c, final boolean b, final Vector vector, final Vector vector2, final Vector vector3, final Vector vector4, final Vector vector5, final Vector vector6, final int j, final boolean k, final boolean l, final boolean m, final boolean n, final int o, final int p17, final n q) {
        this.a = a;
        this.c = c;
        this.b = b;
        this.j = j;
        this.k = k;
        this.l = l;
        this.m = m;
        this.n = n;
        this.o = o;
        this.p = p17;
        this.q = q;
        this.d = new String[vector.size()];
        this.e = new int[vector6.size()];
        this.f = new int[vector2.size()];
        this.g = new int[vector3.size()];
        this.h = new boolean[vector4.size()];
        this.i = new int[vector5.size()];
        int n2 = 0;
        final Enumeration<String> elements = vector.elements();
        while (elements.hasMoreElements()) {
            this.d[n2++] = elements.nextElement();
        }
        int n3 = 0;
        final Enumeration<Integer> elements2 = vector6.elements();
        while (elements2.hasMoreElements()) {
            this.e[n3++] = elements2.nextElement();
        }
        int n4 = 0;
        final Enumeration<Integer> elements3 = vector2.elements();
        while (elements3.hasMoreElements()) {
            this.f[n4++] = elements3.nextElement();
        }
        int n5 = 0;
        final Enumeration<Integer> elements4 = vector3.elements();
        while (elements4.hasMoreElements()) {
            this.g[n5++] = elements4.nextElement();
        }
        int n6 = 0;
        final Enumeration<Boolean> elements5 = vector4.elements();
        while (elements5.hasMoreElements()) {
            this.h[n6++] = b(elements5.nextElement());
        }
        int n7 = 0;
        final Enumeration<Integer> elements6 = vector5.elements();
        while (elements6.hasMoreElements()) {
            this.i[n7++] = elements6.nextElement();
        }
    }
}
