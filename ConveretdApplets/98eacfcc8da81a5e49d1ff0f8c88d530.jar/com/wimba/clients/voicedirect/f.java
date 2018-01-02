// 
// Decompiled by Procyon v0.5.30
// 

package com.wimba.clients.voicedirect;

import VT_6_1_0_11.cD;
import VT_6_1_0_11.dv;
import java.io.ByteArrayOutputStream;

public final class f
{
    private static byte[] a;
    private ByteArrayOutputStream b;
    private int c;
    private int d;
    private byte[] e;
    private byte[] f;
    private byte[] g;
    private int h;
    private int i;
    private int j;
    private int k;
    private int l;
    private long m;
    
    public f() {
        this.b = new ByteArrayOutputStream(450);
        this.c = 0;
        this.d = 0;
        this.g = new byte[28];
        this.f = new byte[4];
    }
    
    public final boolean a(final byte[] array, int i, final int n) {
        if (array == null || n < 0 || n + 0 > array.length) {
            return false;
        }
        this.b.write(array, 0, n);
        byte[] g;
        byte[] array2;
        for (i = 0; i < n; ++i, ++this.c) {
            if (this.d == 0 && array[i] == com.wimba.clients.voicedirect.f.a[0]) {
                ++this.d;
            }
            else if (this.d == 1 && array[i] == com.wimba.clients.voicedirect.f.a[1] && ((i > 0 && array[i - 1] == com.wimba.clients.voicedirect.f.a[0]) || (i == 0 && this.f[3] == com.wimba.clients.voicedirect.f.a[0]))) {
                ++this.d;
            }
            else if (this.d == 2 && array[i] == com.wimba.clients.voicedirect.f.a[2] && ((i > 0 && array[i - 1] == com.wimba.clients.voicedirect.f.a[1]) || (i == 0 && this.f[3] == com.wimba.clients.voicedirect.f.a[1])) && ((i > 1 && array[i - 2] == com.wimba.clients.voicedirect.f.a[0]) || (i <= 1 && this.f[i + 2] == com.wimba.clients.voicedirect.f.a[0]))) {
                ++this.d;
            }
            else if (this.d == 3 && array[i] == com.wimba.clients.voicedirect.f.a[3] && ((i > 0 && array[i - 1] == com.wimba.clients.voicedirect.f.a[2]) || (i == 0 && this.f[3] == com.wimba.clients.voicedirect.f.a[2])) && ((i > 1 && array[i - 2] == com.wimba.clients.voicedirect.f.a[1]) || (i <= 1 && this.f[i + 2] == com.wimba.clients.voicedirect.f.a[1])) && ((i > 2 && array[i - 3] == com.wimba.clients.voicedirect.f.a[0]) || (i <= 2 && this.f[i + 1] == com.wimba.clients.voicedirect.f.a[0]))) {
                ++this.d;
            }
            else if (this.d >= 4) {
                if (this.d >= 28) {
                    g = this.g;
                    i = 6;
                    array2 = g;
                    this.m = ((g[6] & 0xFF) | (array2[7] & 0xFF) << 8 | (array2[8] & 0xFF) << 16 | (array2[8] & 0xFF) << 24 | (array2[8] & 0xFF) << 32 | (array2[8] & 0xFF) << 40 | (array2[8] & 0xFF) << 48 | array2[9] << 56);
                    this.l = a(this.g, 18);
                    this.k = a(this.g, 14);
                    return true;
                }
                this.g[this.d++] = array[i];
            }
            else {
                this.d = 0;
            }
        }
        if (array.length < 4) {
            System.arraycopy(this.f, array.length, this.f, 0, 4 - array.length);
        }
        if (array.length >= 4) {
            this.f[0] = array[array.length - 4];
        }
        if (array.length >= 3) {
            this.f[1] = array[array.length - 3];
        }
        if (array.length >= 2) {
            this.f[2] = array[array.length - 2];
        }
        if (array.length > 0) {
            this.f[3] = array[array.length - 1];
        }
        return false;
    }
    
    public final byte[] a(final dv dv) {
        final byte[] byteArray;
        this.e = new byte[(byteArray = this.b.toByteArray()).length - this.c + 28 + ((this.l == 0) ? 0 : ((this.l == 1) ? 108 : 217))];
        this.h = 0;
        this.i = 0;
        this.j = 0;
        int g;
        if ((g = dv.g()) < 100) {
            g *= 1000;
        }
        final int n = (g < 12000) ? 0 : ((g < 24000) ? 1 : 2);
        if (this.l > 0) {
            final int n2 = g;
            final int n3 = n;
            final int j = dv.j() ? 1 : 0;
            final int n4 = n3;
            final int n5 = n2;
            this.a(1, 2);
            this.e[this.h + 27] = 80;
            a(this.e, this.i, "Speex   ");
            a(this.e, this.i + 8, "speex-1.0           ");
            b(this.e, this.i + 28, 1);
            b(this.e, this.i + 32, 80);
            b(this.e, this.i + 36, n5);
            b(this.e, this.i + 40, n4);
            b(this.e, this.i + 44, 4);
            b(this.e, this.i + 48, 1);
            b(this.e, this.i + 52, -1);
            b(this.e, this.i + 56, 160 * (1 >>> n4));
            b(this.e, this.i + 60, j);
            b(this.e, this.i + 64, 1);
            b(this.e, this.i + 68, 0);
            b(this.e, this.i + 72, 0);
            b(this.e, this.i + 76, 0);
            this.i += 80;
            this.b();
        }
        if (this.l > 1) {
            String substring = "Header rebuilt mid-stream by OggSpeexResynchronizer for Voice Direct";
            if (substring.length() > 247) {
                substring = substring.substring(0, 247);
            }
            final int length = substring.length();
            this.a(1, 0);
            this.e[this.h + 27] = (byte)(0xFF & length + 8);
            b(this.e, this.i, length);
            a(this.e, this.i + 4, substring);
            b(this.e, this.i + length + 4, 0);
            this.i += length + 8;
            this.b();
        }
        System.arraycopy(byteArray, this.c - 28, this.e, (this.l == 0) ? 0 : ((this.l == 1) ? 108 : 217), byteArray.length - this.c + 28);
        return this.e;
    }
    
    public final long a() {
        return this.m;
    }
    
    private void a(int i, final int n) {
        a(this.e, this.h, "OggS");
        this.e[this.h + 4] = 0;
        this.e[this.h + 5] = (byte)n;
        a(this.e, this.h + 6, this.m);
        b(this.e, this.h + 14, this.k);
        b(this.e, this.h + 18, this.j++);
        b(this.e, this.h + 22, 0);
        this.e[this.h + 26] = 1;
        for (i = 0; i <= 0; ++i) {
            this.e[this.h + 27] = 0;
        }
        this.i = this.h + 27 + 1;
    }
    
    private void b() {
        this.m = this.m;
        a(this.e, this.h + 6, this.m);
        b(this.e, this.h + 22, cD.a(0, this.e, this.h, this.i - this.h));
        this.h = this.i;
    }
    
    private static void b(final byte[] array, final int n, final int n2) {
        array[n] = (byte)(0xFF & n2);
        array[n + 1] = (byte)(0xFF & n2 >>> 8);
        array[n + 2] = (byte)(0xFF & n2 >>> 16);
        array[n + 3] = (byte)(0xFF & n2 >>> 24);
    }
    
    private static void a(final byte[] array, final int n, final long n2) {
        array[n] = (byte)(0xFFL & n2);
        array[n + 1] = (byte)(0xFFL & n2 >>> 8);
        array[n + 2] = (byte)(0xFFL & n2 >>> 16);
        array[n + 3] = (byte)(0xFFL & n2 >>> 24);
        array[n + 4] = (byte)(0xFFL & n2 >>> 32);
        array[n + 5] = (byte)(0xFFL & n2 >>> 40);
        array[n + 6] = (byte)(0xFFL & n2 >>> 48);
        array[n + 7] = (byte)(0xFFL & n2 >>> 56);
    }
    
    private static void a(final byte[] array, final int n, final String s) {
        final byte[] bytes = s.getBytes();
        System.arraycopy(bytes, 0, array, n, bytes.length);
    }
    
    private static int a(final byte[] array, final int n) {
        return (array[n] & 0xFF) | (array[n + 1] & 0xFF) << 8 | (array[n + 2] & 0xFF) << 16 | array[n + 3] << 24;
    }
    
    static {
        f.a = new byte[] { 79, 103, 103, 83 };
    }
}
