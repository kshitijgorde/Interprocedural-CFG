// 
// Decompiled by Procyon v0.5.30
// 

package ru.zhuk.graphics.codec;

import java.io.IOException;
import ru.zhuk.graphics.d;
import java.io.OutputStream;
import java.util.zip.DeflaterOutputStream;
import java.io.ByteArrayOutputStream;
import java.util.zip.Deflater;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.util.zip.CRC32;
import java.awt.Image;

public class g
{
    private int a;
    private int m;
    private byte[] i;
    private byte[] j;
    private byte[] n;
    private Image f;
    private int c;
    private int k;
    private CRC32 d;
    private boolean b;
    private int g;
    private int l;
    private int h;
    private Component e;
    
    public g(final Component e, final Image f) {
        this.d = new CRC32();
        this.b = false;
        this.g = 0;
        this.h = 9;
        this.e = e;
        this.f = f;
    }
    
    public byte[] d() {
        if (this.f == null) {
            return null;
        }
        this.c = this.f.getWidth(null);
        this.k = this.f.getHeight(null);
        this.i = new byte[(this.c + 1) * this.k * 3 + 200];
        this.m = 0;
        this.a = this.a(new byte[] { -119, 80, 78, 71, 13, 10, 26, 10 }, 0);
        this.a();
        if (this.b()) {
            this.c();
            return this.i = this.b(this.i, this.m);
        }
        return null;
    }
    
    private byte[] b(final byte[] array, final int n) {
        final byte[] array2 = new byte[n];
        System.arraycopy(array, 0, array2, 0, Math.min(array.length, n));
        return array2;
    }
    
    private int a(final byte[] array, final int n) {
        this.m = Math.max(this.m, n + array.length);
        if (array.length + n > this.i.length) {
            this.i = this.b(this.i, this.i.length + Math.max(1000, array.length));
        }
        System.arraycopy(array, 0, this.i, n, array.length);
        return n + array.length;
    }
    
    private int a(final byte[] array, final int n, final int n2) {
        this.m = Math.max(this.m, n2 + n);
        if (n + n2 > this.i.length) {
            this.i = this.b(this.i, this.i.length + Math.max(1000, n));
        }
        System.arraycopy(array, 0, this.i, n2, n);
        return n2 + n;
    }
    
    private int b(final int n, final int n2) {
        return this.a(new byte[] { (byte)(n >> 24 & 0xFF), (byte)(n >> 16 & 0xFF), (byte)(n >> 8 & 0xFF), (byte)(n & 0xFF) }, n2);
    }
    
    private int a(final int n, final int n2) {
        return this.a(new byte[] { (byte)n }, n2);
    }
    
    private int a(final String s, final int n) {
        return this.a(s.getBytes(), n);
    }
    
    private void a() {
        final int b = this.b(13, this.a);
        this.a = b;
        final int n = b;
        this.a = this.a("IHDR", this.a);
        this.a = this.b(this.c, this.a);
        this.a = this.b(this.k, this.a);
        this.a = this.a(8, this.a);
        this.a = this.a(this.b ? 6 : 2, this.a);
        this.a = this.a(0, this.a);
        this.a = this.a(0, this.a);
        this.a = this.a(0, this.a);
        this.d.reset();
        this.d.update(this.i, n, this.a - n);
        this.a = this.b((int)this.d.getValue(), this.a);
    }
    
    private boolean b() {
        int i = this.k;
        int n = 0;
        this.l = (this.b ? 4 : 3);
        final Deflater deflater = new Deflater(this.h);
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(1024);
        final DeflaterOutputStream deflaterOutputStream = new DeflaterOutputStream(byteArrayOutputStream, deflater);
        try {
            while (i > 0) {
                final int min = Math.min(32767 / (this.c * (this.l + 1)), i);
                final int n2 = this.c * min;
                final int[] array = new int[n2];
                final d d = new d(this.e, this.f, 0, n, this.c, min, array, 0, this.c);
                try {
                    d.grabPixels();
                }
                catch (Exception ex) {
                    return false;
                }
                if ((d.getStatus() & 0x80) != 0x0) {
                    return false;
                }
                final byte[] array2 = new byte[n2 * this.l + min];
                if (this.g == 1) {
                    this.n = new byte[16];
                }
                if (this.g == 2) {
                    this.j = new byte[this.c * this.l];
                }
                int n3 = 0;
                for (int j = 0; j < n2; ++j) {
                    if (j % this.c == 0) {
                        array2[n3++] = (byte)this.g;
                    }
                    array2[n3++] = (byte)(array[j] >> 16 & 0xFF);
                    array2[n3++] = (byte)(array[j] >> 8 & 0xFF);
                    array2[n3++] = (byte)(array[j] & 0xFF);
                }
                deflaterOutputStream.write(array2, 0, n3);
                n += min;
                i -= min;
            }
            deflaterOutputStream.close();
            final byte[] byteArray = byteArrayOutputStream.toByteArray();
            final int length = byteArray.length;
            this.d.reset();
            this.a = this.b(length, this.a);
            this.a = this.a("IDAT", this.a);
            this.d.update("IDAT".getBytes());
            this.a = this.a(byteArray, length, this.a);
            this.d.update(byteArray, 0, length);
            this.a = this.b((int)this.d.getValue(), this.a);
            deflater.finish();
        }
        catch (IOException ex2) {
            return false;
        }
        return true;
    }
    
    private void c() {
        this.a = this.b(0, this.a);
        this.a = this.a("IEND", this.a);
        this.d.reset();
        this.d.update("IEND".getBytes());
        this.a = this.b((int)this.d.getValue(), this.a);
    }
}
