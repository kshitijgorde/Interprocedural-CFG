// 
// Decompiled by Procyon v0.5.30
// 

package ru.zhuk.graphics.codec;

import java.io.IOException;
import java.io.OutputStream;
import java.awt.Image;
import java.awt.Component;
import java.io.BufferedOutputStream;

public class e
{
    private BufferedOutputStream j;
    private f c;
    private b e;
    private j k;
    private int b;
    private int f;
    private int a;
    private static int[] g;
    private short i;
    private short d;
    private byte h;
    
    public e(final Component component, final Image image, final int a, final OutputStream outputStream) {
        this.i = 1;
        this.d = 1;
        this.h = 0;
        this.a = a;
        this.c = new f(component, image);
        this.b = this.c.n;
        this.f = this.c.x;
        this.j = new BufferedOutputStream(outputStream);
        this.k = new j(this.a);
        this.e = new b(this.f, this.b);
    }
    
    public void a(final int n, final int n2, final int n3) {
        this.i = (short)n;
        this.d = (short)n2;
        this.h = (byte)n3;
    }
    
    public void a() {
        this.a(this.j);
        this.c(this.j);
        this.b(this.j);
        try {
            this.j.flush();
        }
        catch (IOException ex) {}
    }
    
    private void c(final BufferedOutputStream bufferedOutputStream) {
        final float[][] array = new float[8][8];
        final double[][] array2 = new double[8][8];
        final int[] array3 = new int[64];
        final int[] array4 = new int[this.c.v];
        final int[] array5 = new int[64];
        int min = (this.f % 8 != 0) ? ((int)(Math.floor(this.f / 8.0) + 1) * 8) : this.f;
        int min2 = (this.b % 8 != 0) ? ((int)(Math.floor(this.b / 8.0) + 1) * 8) : this.b;
        for (int i = 0; i < this.c.v; ++i) {
            min = Math.min(min, this.c.o[i]);
            min2 = Math.min(min2, this.c.y[i]);
        }
        for (int j = 0; j < min2; ++j) {
            for (int k = 0; k < min; ++k) {
                final int n = k * 8;
                final int n2 = j * 8;
                for (int l = 0; l < this.c.v; ++l) {
                    final int n3 = this.c.o[l];
                    final int n4 = this.c.y[l];
                    final float[][] array6 = (float[][])this.c.k[l];
                    for (int n5 = 0; n5 < this.c.q[l]; ++n5) {
                        for (int n6 = 0; n6 < this.c.z[l]; ++n6) {
                            final int n7 = n6 * 8;
                            final int n8 = n5 * 8;
                            for (int n9 = 0; n9 < 8; ++n9) {
                                for (int n10 = 0; n10 < 8; ++n10) {
                                    array[n9][n10] = array6[n2 + n8 + n9][n + n7 + n10];
                                }
                            }
                            final int[] a = this.k.a(this.k.a(array), this.c.u[l]);
                            this.e.a(bufferedOutputStream, a, array4[l], this.c.l[l], this.c.t[l]);
                            array4[l] = a[0];
                        }
                    }
                }
            }
        }
        this.e.a(bufferedOutputStream);
    }
    
    private void b(final BufferedOutputStream bufferedOutputStream) {
        this.b(new byte[] { -1, -39 }, bufferedOutputStream);
    }
    
    private void a(final BufferedOutputStream bufferedOutputStream) {
        this.b(new byte[] { -1, -40 }, bufferedOutputStream);
        this.a(new byte[] { -1, -32, 0, 16, 74, 70, 73, 70, 0, 1, 0, this.h, (byte)(this.i >> 8 & 0xFF), (byte)(this.i & 0xFF), (byte)(this.d >> 8 & 0xFF), (byte)(this.d & 0xFF), 0, 0 }, bufferedOutputStream);
        final String b = this.c.b();
        final int length = b.length();
        if (length > 0) {
            final byte[] array = new byte[length + 4];
            array[0] = -1;
            array[1] = -2;
            array[2] = (byte)(length >> 8 & 0xFF);
            array[3] = (byte)(length & 0xFF);
            System.arraycopy(b.getBytes(), 0, array, 4, b.length());
            this.a(array, bufferedOutputStream);
        }
        final byte[] array2 = new byte[134];
        array2[0] = -1;
        array2[1] = -37;
        array2[2] = 0;
        array2[3] = -124;
        int n = 4;
        for (int i = 0; i < 2; ++i) {
            array2[n++] = (byte)(0 + i);
            final int[] array3 = (int[])this.k.b[i];
            for (int j = 0; j < 64; ++j) {
                array2[n++] = (byte)array3[ru.zhuk.graphics.codec.e.g[j]];
            }
        }
        this.a(array2, bufferedOutputStream);
        final byte[] array4 = { -1, -64, 0, 17, (byte)this.c.f, (byte)(this.c.n >> 8 & 0xFF), (byte)(this.c.n & 0xFF), (byte)(this.c.x >> 8 & 0xFF), (byte)(this.c.x & 0xFF), (byte)this.c.v, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        int n2 = 10;
        for (byte b2 = 0; b2 < array4[9]; ++b2) {
            array4[n2++] = (byte)this.c.m[b2];
            array4[n2++] = (byte)((this.c.z[b2] << 4) + this.c.q[b2]);
            array4[n2++] = (byte)this.c.u[b2];
        }
        this.a(array4, bufferedOutputStream);
        int n3 = 4;
        int n4 = 4;
        final byte[] array5 = new byte[17];
        byte[] array6 = { -1, -60, 0, 0 };
        for (int k = 0; k < 4; ++k) {
            int n5 = 0;
            array5[n3++ - n4] = (byte)((int[])this.e.b.elementAt(k))[0];
            for (int l = 1; l < 17; ++l) {
                final int n6 = ((int[])this.e.b.elementAt(k))[l];
                array5[n3++ - n4] = (byte)n6;
                n5 += n6;
            }
            final int n7 = n3;
            final byte[] array7 = new byte[n5];
            for (int n8 = 0; n8 < n5; ++n8) {
                array7[n3++ - n7] = (byte)((int[])this.e.a.elementAt(k))[n8];
            }
            final byte[] array8 = new byte[n3];
            System.arraycopy(array6, 0, array8, 0, n4);
            System.arraycopy(array5, 0, array8, n4, 17);
            System.arraycopy(array7, 0, array8, n4 + 17, n5);
            array6 = array8;
            n4 = n3;
        }
        array6[2] = (byte)(n3 - 2 >> 8 & 0xFF);
        array6[3] = (byte)(n3 - 2 & 0xFF);
        this.a(array6, bufferedOutputStream);
        final byte[] array9 = new byte[14];
        array9[0] = -1;
        array9[1] = -38;
        array9[2] = 0;
        array9[3] = 12;
        array9[4] = (byte)this.c.v;
        int n9 = 5;
        for (byte b3 = 0; b3 < array9[4]; ++b3) {
            array9[n9++] = (byte)this.c.m[b3];
            array9[n9++] = (byte)((this.c.l[b3] << 4) + this.c.t[b3]);
        }
        array9[n9++] = (byte)this.c.p;
        array9[n9++] = (byte)this.c.g;
        array9[n9++] = (byte)((this.c.r << 4) + this.c.b);
        this.a(array9, bufferedOutputStream);
    }
    
    private void b(final byte[] array, final BufferedOutputStream bufferedOutputStream) {
        try {
            bufferedOutputStream.write(array, 0, 2);
        }
        catch (IOException ex) {}
    }
    
    private void a(final byte[] array, final BufferedOutputStream bufferedOutputStream) {
        try {
            bufferedOutputStream.write(array, 0, ((array[2] & 0xFF) << 8) + (array[3] & 0xFF) + 2);
        }
        catch (IOException ex) {}
    }
    
    static {
        e.g = new int[] { 0, 1, 8, 16, 9, 2, 3, 10, 17, 24, 32, 25, 18, 11, 4, 5, 12, 19, 26, 33, 40, 48, 41, 34, 27, 20, 13, 6, 7, 14, 21, 28, 35, 42, 49, 56, 57, 50, 43, 36, 29, 22, 15, 23, 30, 37, 44, 51, 58, 59, 52, 45, 38, 31, 39, 46, 53, 60, 61, 54, 47, 55, 62, 63 };
    }
}
