// 
// Decompiled by Procyon v0.5.30
// 

package com.daysofwonder.tt;

import com.daysofwonder.req.k;
import com.daysofwonder.util.t;
import java.util.StringTokenizer;
import java.awt.Rectangle;
import com.daysofwonder.util.G;

public class o
{
    private e b;
    private String c;
    private String d;
    private c e;
    private c f;
    private o g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    public static final String[] a;
    private G m;
    private Rectangle[] n;
    
    public o() {
        this.m = new G();
        this.n = new Rectangle[2];
    }
    
    public o(final e b) {
        this.m = new G();
        this.n = new Rectangle[2];
        this.b = b;
    }
    
    public boolean a(final String s) {
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(s, ",");
            this.c = stringTokenizer.nextToken();
            this.d = stringTokenizer.nextToken();
            this.i = Integer.parseInt(stringTokenizer.nextToken());
            final String nextToken = stringTokenizer.nextToken();
            for (int i = 0; i < o.a.length; ++i) {
                if (nextToken.equalsIgnoreCase(o.a[i])) {
                    this.h = i + 1;
                    break;
                }
            }
            int n = 0;
            final int[] array = new int[4];
            final String nextToken2 = stringTokenizer.nextToken();
            if (nextToken2.length() != 0) {
                try {
                    array[n++] = Integer.parseInt(nextToken2);
                }
                catch (NumberFormatException ex2) {
                    if (nextToken2.equals("t")) {
                        this.j = 1;
                    }
                    else if (nextToken2.equals("f1")) {
                        this.j = 2;
                        this.k = 1;
                    }
                    else if (nextToken2.equals("f2")) {
                        this.j = 2;
                        this.k = 2;
                    }
                }
            }
            while (stringTokenizer.hasMoreTokens()) {
                array[n++] = Integer.parseInt(stringTokenizer.nextToken());
                if (n == 3) {
                    this.m.c(new a(array, this));
                    n = 0;
                }
            }
        }
        catch (Exception ex) {
            t.a(ex);
            return false;
        }
        if (this.h == 0) {
            return false;
        }
        this.e = this.b.a(this.c);
        this.f = this.b.a(this.d);
        this.e.a(this);
        this.f.a(this);
        return true;
    }
    
    public void a(final o g) {
        this.l();
        this.g = g;
    }
    
    public o a() {
        this.l();
        return this.g;
    }
    
    public boolean b() {
        this.l();
        return this.g != null;
    }
    
    public int c() {
        this.l();
        return this.h;
    }
    
    public int d() {
        this.l();
        return this.i;
    }
    
    public c e() {
        this.l();
        return this.e;
    }
    
    public c f() {
        this.l();
        return this.f;
    }
    
    public int g() {
        return this.l;
    }
    
    public void a(final int l) {
        this.l = l;
    }
    
    public boolean h() {
        return this.j == 2;
    }
    
    public int i() {
        return this.k;
    }
    
    public boolean j() {
        return this.j == 1;
    }
    
    public boolean a(final c c) {
        this.l();
        return this.e == c || this.f == c;
    }
    
    public void a(final k k) {
        k.writeByte(this.l);
    }
    
    public void b(final k k) {
        this.l = k.readByte();
    }
    
    public int a(final Object o) {
        this.l();
        final o o2 = (o)o;
        if (o2.a(this.e) && o2.a(this.f) && !this.e.b() && !this.f.b()) {
            return 0;
        }
        return 1;
    }
    
    public G k() {
        return this.m;
    }
    
    private void l() {
        if (this.b == null) {
            t.e("Map not initialized");
            throw new RuntimeException("Map not initialized");
        }
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append(this.c).append('-').append(this.d).append('[').append(o.a[this.h - 1]).append(',').append(this.i).append(',').append(this.l).append(']');
        return sb.toString();
    }
    
    public Rectangle b(final int n) {
        return this.n[n];
    }
    
    public void a(final int n, final Rectangle rectangle) {
        this.n[n] = rectangle;
    }
    
    static {
        a = new String[] { "purple", "white", "blue", "yellow", "brown", "black", "red", "green", "wild" };
    }
}
