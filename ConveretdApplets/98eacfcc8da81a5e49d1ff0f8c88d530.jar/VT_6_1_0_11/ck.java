// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.IOException;
import java.io.EOFException;
import java.util.Iterator;
import java.io.InputStream;
import java.util.Vector;
import java.util.List;

public final class ck
{
    private List a;
    private byte[] b;
    private int c;
    private int d;
    private long e;
    private int f;
    private int g;
    
    public ck(final int d, final long e, final int f, final int g) {
        this.d = d;
        this.e = e;
        this.f = f;
        this.g = g;
        this.a = new Vector();
        this.b = null;
        this.c = 0;
    }
    
    public ck(final InputStream inputStream) {
        this.a = new Vector();
        this.b = null;
        this.c = 0;
        this.a(inputStream, false);
    }
    
    public final void a(final aY ay) {
        if (this.e() >= 255) {
            throw new IndexOutOfBoundsException("Ogg Page can't exceed 255 packets");
        }
        if (ay != null) {
            this.a.add(ay);
            if (ay.e() != -1) {
                this.e += ay.e();
            }
            this.b = null;
        }
    }
    
    public final void a() {
        this.d = 4;
    }
    
    public final boolean b() {
        return this.d == 4;
    }
    
    public final long c() {
        return this.d;
    }
    
    public final long d() {
        return this.e;
    }
    
    public final int e() {
        return this.a.size();
    }
    
    private int g() {
        int n = 0;
        final Iterator<aY> iterator = (Iterator<aY>)this.a.iterator();
        while (iterator.hasNext()) {
            n += iterator.next().c();
        }
        int n2 = n + this.e();
        n2 += 27;
        return n2;
    }
    
    public final aY a(final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Requested Packet with negative index");
        }
        if (n >= this.e()) {
            return null;
        }
        final aY value;
        if ((value = this.a.get(n)) != null) {
            return value;
        }
        return null;
    }
    
    public final byte[] f() {
        if (this.b == null) {
            this.b = new byte[this.g()];
            final byte[] b = this.b;
            int n = 27 + this.e();
            final byte[] array2;
            final byte[] array = array2 = new byte[this.g()];
            final int d = this.d;
            final long e = this.e;
            final int f = this.f;
            final int g = this.g;
            final int n2 = f;
            final long n3 = e;
            final int n4 = d;
            final byte[] array3 = array2;
            a(array3, 0, "OggS");
            array3[4] = 0;
            array3[5] = (byte)n4;
            final byte[] array4 = array3;
            final int n5 = 6;
            final long n6 = n3;
            final int n7 = n5;
            final byte[] array5 = array4;
            array4[n7] = (byte)(0xFFL & n6);
            array5[n7 + 1] = (byte)(0xFFL & n6 >>> 8);
            array5[n7 + 2] = (byte)(0xFFL & n6 >>> 16);
            array5[n7 + 3] = (byte)(0xFFL & n6 >>> 24);
            array5[n7 + 4] = (byte)(0xFFL & n6 >>> 32);
            array5[n7 + 5] = (byte)(0xFFL & n6 >>> 40);
            array5[n7 + 6] = (byte)(0xFFL & n6 >>> 48);
            array5[n7 + 7] = (byte)(0xFFL & n6 >>> 56);
            a(array3, 14, n2);
            a(array3, 18, g);
            a(array3, 22, 0);
            array3[26] = (byte)this.e();
            final Iterator iterator = this.a.iterator();
            int n8 = 27;
            while (iterator.hasNext()) {
                array3[n8++] = (byte)iterator.next().c();
            }
            final int n9 = this.e() + 27;
            int n10 = cD.a(0, array, 0, n);
            for (final aY ay : this.a) {
                n10 = cD.a(n10, ay.a(), ay.d(), ay.c());
            }
            a(array, 22, n10);
            System.arraycopy(array, 0, b, 0, array.length);
            int n11 = n + 0;
            final Iterator iterator3 = this.a.iterator();
            while (iterator3.hasNext()) {
                final aY ay2;
                System.arraycopy((ay2 = iterator3.next()).a(), ay2.d(), b, n11, ay2.c());
                n11 += ay2.c();
                n += ay2.c();
            }
        }
        return this.b;
    }
    
    private int a(final InputStream inputStream, final boolean b) {
        final byte[] array = new byte[27];
        int read2;
        for (int read = read2 = inputStream.read(array, 0, 27); read2 < 27 && read > 0; read = inputStream.read(array, read2, 27 - read2), read2 += read) {}
        if (read2 < 27) {
            throw new EOFException("InputStream ended before the page header was fully read");
        }
        if (array[0] != 79 || array[1] != 103 || array[2] != 103 || array[3] != 83) {
            throw new IOException("Ogg Page doesn't begin with 'OggS'");
        }
        this.d = (array[5] & 0xFF);
        final byte[] array2;
        this.e = (((array2 = array)[6] & 0xFF) | (array2[7] & 0xFF) << 8 | (array2[8] & 0xFF) << 16 | (array2[9] & 0xFF) << 24 | (array2[10] & 0xFF) << 32 | (array2[11] & 0xFF) << 40 | (array2[12] & 0xFF) << 48 | array2[13] << 56);
        this.f = a(array, 14);
        this.g = a(array, 18);
        final int n;
        final byte[] array3 = new byte[n = (array[26] & 0xFF)];
        int read4;
        for (int read3 = read4 = inputStream.read(array3, 0, n); read4 < n && read3 > 0; read3 = inputStream.read(array3, read4, n - read4), read4 += read3) {}
        int n2 = n + 27;
        for (int i = 0; i < n; ++i) {
            final int n3 = array3[i] & 0xFF;
            this.a.add(new aY(inputStream, n3, -1));
            n2 += n3;
        }
        return n2;
    }
    
    public static final void a(final byte[] array, final int n, final int n2) {
        array[n] = (byte)(0xFF & n2);
        array[n + 1] = (byte)(0xFF & n2 >>> 8);
        array[n + 2] = (byte)(0xFF & n2 >>> 16);
        array[n + 3] = (byte)(0xFF & n2 >>> 24);
    }
    
    public static final void a(final byte[] array, final int n, final String s) {
        final byte[] bytes = s.getBytes();
        System.arraycopy(bytes, 0, array, n, bytes.length);
    }
    
    public static final int a(final byte[] array, final int n) {
        return (array[n] & 0xFF) | (array[n + 1] & 0xFF) << 8 | (array[n + 2] & 0xFF) << 16 | array[n + 3] << 24;
    }
}
