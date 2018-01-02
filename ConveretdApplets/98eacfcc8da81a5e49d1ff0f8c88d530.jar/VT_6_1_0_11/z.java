// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

import java.io.EOFException;
import java.util.Random;
import java.util.Vector;
import java.util.List;

public class z
{
    private List a;
    private ck b;
    private int c;
    private int d;
    private int e;
    private int f;
    private int g;
    private boolean h;
    
    public z(final int n, int n2, int n3, final int n4, final boolean b, int length) {
        this.e = 10;
        this.a = new Vector();
        this.f = new Random().nextInt();
        this.h = false;
        final byte[] array2;
        final byte[] array = array2 = new byte[80];
        final int n5 = 0;
        final int n6 = n2;
        final int n7 = n3;
        final int n8 = 0;
        final int n9 = 1;
        final int n10 = n8;
        n2 = n7;
        length = n;
        final int n11 = n6;
        n3 = n5;
        final byte[] array3 = array;
        ck.a(array, 0, "Speex   ");
        ck.a(array3, 8, "speex-1.0");
        System.arraycopy(new byte[11], 0, array3, 17, 11);
        ck.a(array3, 28, 1);
        ck.a(array3, 32, 80);
        ck.a(array3, 36, n11);
        ck.a(array3, 40, length);
        ck.a(array3, 44, 4);
        ck.a(array3, 48, n2);
        ck.a(array3, 52, -1);
        ck.a(array3, 56, 160 << length);
        ck.a(array3, 60, n10);
        ck.a(array3, 64, n9);
        ck.a(array3, 68, 0);
        ck.a(array3, 72, 0);
        ck.a(array3, 76, 0);
        (this.b = new ck(2, 0L, this.f, 0)).a(new aY(array2));
        this.a.add(this.b);
        final byte[] array4 = new byte["Packed by OggSpeexPacker ($Revision: 9548 $)".length() + 8];
        final int n12 = 0;
        final String s = "Packed by OggSpeexPacker ($Revision: 9548 $)";
        n3 = n12;
        final byte[] array5 = array4;
        length = s.length();
        ck.a(array5, 0, length);
        ck.a(array5, 4, s);
        ck.a(array5, length + 0 + 4, 0);
        (this.b = new ck(0, 0L, this.f, 1)).a(new aY(array4));
        this.a.add(this.b);
        this.g = 2;
        this.c();
        this.c = 0;
        this.d = 160 * (1 << n);
    }
    
    private void c() {
        if (!this.h) {
            this.b = new ck(0, this.b.d(), this.f, this.g++);
            this.a.add(this.b);
        }
    }
    
    public final void a() {
        this.h = true;
        this.b.a();
    }
    
    public final synchronized void b() {
        while (this.c > 0) {
            this.a.remove(0);
            --this.c;
        }
    }
    
    private synchronized void a(final aY ay) {
        if (this.h) {
            throw new IllegalStateException("The Packer has been closed.");
        }
        if (ay == null) {
            throw new IllegalArgumentException("Cannot Add a null Packet.");
        }
        if (this.b.e() >= this.e) {
            this.c();
        }
        this.b.a(ay);
    }
    
    public final void a(final byte[] array) {
        this.a(new aY(array, 0, array.length, this.d));
    }
    
    public final synchronized ck a(final boolean b) {
        ck ck = null;
        final int n;
        if ((n = this.a.size() - this.c) > 1 || (this.h && n > 0)) {
            ck = this.a.get(this.c++);
        }
        else if (this.h) {
            throw new EOFException("The Packer has returned all Pages");
        }
        return ck;
    }
    
    public z() {
    }
    
    public static float[] a(int i, int n) {
        final int n2 = n * 7 / 2;
        n = n * 5 / 2;
        final float[] array = new float[i];
        for (i = 0; i < n2; ++i) {
            array[i] = (float)(0.54 - 0.46 * Math.cos(3.141592653589793 * i / n2));
        }
        for (i = 0; i < n; ++i) {
            array[n2 + i] = (float)(0.54 + 0.46 * Math.cos(3.141592653589793 * i / n));
        }
        return array;
    }
    
    public static float[] a(final int n, final float n2) {
        final float[] array = new float[n + 1];
        for (int i = 0; i < n + 1; ++i) {
            array[i] = (float)Math.exp(-0.5 * (6.283185307179586 * n2 * i) * (6.283185307179586 * n2 * i));
        }
        return array;
    }
}
