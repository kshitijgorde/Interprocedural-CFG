// 
// Decompiled by Procyon v0.5.30
// 

package ru.zhuk.graphics.codec;

import java.awt.AWTException;
import ru.zhuk.graphics.d;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Image;

class f
{
    private String d;
    public Image s;
    public int n;
    public int x;
    public int[] o;
    public int[] y;
    public int f;
    public int v;
    public Object[] k;
    public int[] m;
    public int[] z;
    public int[] q;
    public int[] u;
    public int[] l;
    public int[] t;
    public boolean[] w;
    public boolean[] c;
    public int p;
    public int g;
    public int r;
    public int b;
    public int[] h;
    public int[] j;
    public int a;
    public int i;
    private Component e;
    
    public f(final Component e, final Image s) {
        this.f = 8;
        this.v = 3;
        this.m = new int[] { 1, 2, 3 };
        this.z = new int[] { 1, 1, 1 };
        this.q = new int[] { 1, 1, 1 };
        this.u = new int[] { 0, 1, 1 };
        this.l = new int[] { 0, 1, 1 };
        this.t = new int[] { 0, 1, 1 };
        this.w = new boolean[] { false, false, false };
        this.c = new boolean[] { false, false, false };
        this.p = 0;
        this.g = 63;
        this.r = 0;
        this.b = 0;
        this.e = e;
        this.k = new Object[this.v];
        this.h = new int[this.v];
        this.j = new int[this.v];
        this.o = new int[this.v];
        this.y = new int[this.v];
        this.s = s;
        this.x = s.getWidth(null);
        this.n = s.getHeight(null);
        this.d = "";
        this.a();
    }
    
    public String b() {
        return this.d;
    }
    
    private void a() {
        final int[] array = new int[this.x * this.n];
        final d d = new d(this.e, this.s, 0, 0, this.x, this.n, array, 0, this.x);
        this.a = 1;
        this.i = 1;
        for (int i = 0; i < this.v; ++i) {
            this.a = Math.max(this.a, this.z[i]);
            this.i = Math.max(this.i, this.q[i]);
        }
        for (int j = 0; j < this.v; ++j) {
            this.h[j] = ((this.x % 8 != 0) ? ((int)Math.ceil(this.x / 8.0) * 8) : this.x) / this.a * this.z[j];
            if (this.h[j] != this.x / this.a * this.z[j]) {
                this.w[j] = true;
            }
            this.o[j] = (int)Math.ceil(this.h[j] / 8.0);
            this.j[j] = ((this.n % 8 != 0) ? ((int)Math.ceil(this.n / 8.0) * 8) : this.n) / this.i * this.q[j];
            if (this.j[j] != this.n / this.i * this.q[j]) {
                this.c[j] = true;
            }
            this.y[j] = (int)Math.ceil(this.j[j] / 8.0);
        }
        try {
            if (!d.grabPixels()) {
                try {
                    throw new AWTException("Grabber returned false: ");
                }
                catch (Exception ex) {}
            }
        }
        catch (InterruptedException ex2) {}
        final float[][] array2 = new float[this.j[0]][this.h[0]];
        final float[][] array3 = new float[this.j[0]][this.h[0]];
        final float[][] array4 = new float[this.j[0]][this.h[0]];
        final float[][] array5 = new float[this.j[1]][this.h[1]];
        final float[][] array6 = new float[this.j[2]][this.h[2]];
        int n = 0;
        for (int k = 0; k < this.n; ++k) {
            for (int l = 0; l < this.x; ++l) {
                final int n2 = array[n] >> 16 & 0xFF;
                final int n3 = array[n] >> 8 & 0xFF;
                final int n4 = array[n] & 0xFF;
                array2[k][l] = (float)(0.299 * n2 + 0.587 * n3 + 0.114 * n4);
                array4[k][l] = 128 + (float)(-0.16874 * n2 - 0.33126 * n3 + 0.5 * n4);
                array3[k][l] = 128 + (float)(0.5 * n2 - 0.41869 * n3 - 0.08131 * n4);
                ++n;
            }
        }
        this.k[0] = array2;
        this.k[1] = array4;
        this.k[2] = array3;
    }
}
