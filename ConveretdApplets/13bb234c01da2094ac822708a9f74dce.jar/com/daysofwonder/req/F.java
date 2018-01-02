// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.req;

import java.io.DataInput;
import java.io.DataOutput;
import java.util.Enumeration;
import java.util.Vector;
import com.daysofwonder.a.n;

public class F
{
    public int a;
    public boolean b;
    public String c;
    public String[] d;
    public int[] e;
    public int[] f;
    public boolean[] g;
    public int[] h;
    public int i;
    public boolean j;
    public boolean k;
    public boolean l;
    public int m;
    public int n;
    public n o;
    
    private F(final int a, final String c, final boolean b, final Vector vector, final Vector vector2, final Vector vector3, final Vector vector4, final Vector vector5, final int i, final boolean j, final boolean k, final boolean l, final int m, final int n, final n o) {
        this.a = a;
        this.c = c;
        this.b = b;
        this.i = i;
        this.j = j;
        this.k = k;
        this.l = l;
        this.m = m;
        this.n = n;
        this.o = o;
        this.d = new String[vector.size()];
        this.e = new int[vector5.size()];
        this.f = new int[vector2.size()];
        this.g = new boolean[vector3.size()];
        this.h = new int[vector4.size()];
        int n2 = 0;
        final Enumeration<String> elements = vector.elements();
        while (elements.hasMoreElements()) {
            this.d[n2++] = elements.nextElement();
        }
        int n3 = 0;
        final Enumeration<Integer> elements2 = vector5.elements();
        while (elements2.hasMoreElements()) {
            this.e[n3++] = elements2.nextElement();
        }
        int n4 = 0;
        final Enumeration<Integer> elements3 = vector2.elements();
        while (elements3.hasMoreElements()) {
            this.f[n4++] = elements3.nextElement();
        }
        int n5 = 0;
        final Enumeration<Boolean> elements4 = vector3.elements();
        while (elements4.hasMoreElements()) {
            this.g[n5++] = b(elements4.nextElement());
        }
        int n6 = 0;
        final Enumeration<Integer> elements5 = vector4.elements();
        while (elements5.hasMoreElements()) {
            this.h[n6++] = elements5.nextElement();
        }
    }
}
