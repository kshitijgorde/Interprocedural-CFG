// 
// Decompiled by Procyon v0.5.30
// 

package com.impatica.v402;

import java.io.IOException;

public class ImSound
{
    int I;
    int Z;
    int C;
    int B;
    int D;
    boolean F;
    int J;
    byte[] S;
    int A;
    int E;
    byte[] G;
    ImVi arraycopy;
    int H;
    int K;
    boolean L;
    boolean M;
    int N;
    private static final int[] out;
    private static final int[] println;
    private int[] sleep;
    private int[] start;
    private int[] stop;
    private int[] O;
    private int P;
    private int[] Q;
    private int[] R;
    private int[][] T;
    private int[] U;
    private int V;
    private int[] W;
    private int X;
    private int[] Y;
    int i;
    private int[] v;
    private int[] z;
    private int[] c;
    private int[] b;
    private int[] d;
    
    public final int I() {
        int n = this.A - this.E;
        if (n <= 0) {
            n += 64000;
        }
        return (n >> 1) - 1;
    }
    
    final void Z() {
        this.S();
    }
    
    final boolean C() {
        return this.A == this.E;
    }
    
    final void I(final ImIstream imIstream, int i) {
        while (i > 0) {
            int j;
            int n;
            if (i >= 320) {
                j = 65;
                n = 320;
                i -= 320;
            }
            else {
                j = 33;
                n = 160;
                i -= 160;
            }
            int n2 = 0;
            while (j > 0) {
                final int read = imIstream.read(this.G, n2, j);
                if (read <= 0) {
                    throw new IOException("eof");
                }
                n2 += read;
                j -= read;
            }
            if (!this.L && !this.M) {
                this.K = 0;
                this.i = 8;
                this.C(n);
            }
        }
    }
    
    public final void I(final byte[] array, int i) {
        int e = this.E;
        int n = 0;
        while (i > 0) {
            final int n2 = array[n++] | array[n++] << 8;
            int j = e + 2;
            if (j == 64000) {
                j = 0;
            }
            while (j == this.A) {
                this.E = e;
                this.A();
                this.println(100);
            }
            this.S[e] = (byte)(n2 >> 8);
            this.S[e + 1] = (byte)n2;
            e = j;
            i -= 2;
        }
        this.E = e;
        this.A();
    }
    
    public final void Z(final ImIstream imIstream, int i) {
        while (i > 0) {
            int n = 640;
            if (i < n) {
                n = i;
            }
            final int read = imIstream.read(this.G, 0, n);
            if (read == -1) {
                throw new IOException("eof");
            }
            if (!this.L && !this.M) {
                this.I(this.G, read);
            }
            i -= read;
        }
    }
    
    final void Z(final byte[] array, int i) {
        int e = this.E;
        int n = 0;
        while (i > 0) {
            final byte b = (byte)~array[n++];
            final int n2 = ((b & 0xF) << 3) + 132 << ((b & 0x70) >>> 4);
            final int n3 = ((b & 0x80) != 0x0) ? (132 - n2) : (n2 - 132);
            int j = e + 2;
            if (j == 64000) {
                j = 0;
            }
            while (j == this.A) {
                this.E = e;
                this.A();
                this.println(100);
            }
            this.S[e] = (byte)(n3 >> 8);
            this.S[e + 1] = (byte)n3;
            e = j;
            --i;
        }
        this.E = e;
        this.A();
    }
    
    public final void C(final ImIstream imIstream, int i) {
        while (i > 0) {
            int n = 640;
            if (i < 640) {
                n = i;
            }
            final int read = imIstream.read(this.G, 0, n);
            if (read == -1) {
                throw new IOException("eof");
            }
            if (!this.L && !this.M) {
                this.Z(this.G, read);
            }
            i -= read;
        }
    }
    
    private final void arraycopy() {
        this.arraycopy.stop();
        this.A = 0;
        this.E = 0;
        this.arraycopy.start();
    }
    
    final int B() {
        return this.F() / 8;
    }
    
    final void D() {
        this.arraycopy.stop();
    }
    
    final int F() {
        if (this.arraycopy == null) {
            return 0;
        }
        return this.arraycopy.I();
    }
    
    private final void out(final int h) {
        this.H = h;
        if (this.arraycopy != null) {
            this.arraycopy.I(h);
        }
    }
    
    final void I(final int n) {
        final int n2 = n * 8;
        final int n3 = n2 - this.H << 1;
        final int a = this.A;
        final int e = this.E;
        this.arraycopy();
        if (n3 > 0) {
            int n4 = e - a;
            if (n4 < 0) {
                n4 += 64000;
            }
            if (n4 > n3) {
                n4 = n3;
            }
            if (n4 > 0) {
                int a2 = a + n4;
                if (a2 >= 64000) {
                    a2 -= 64000;
                }
                this.A = a2;
                this.E = e;
            }
        }
        this.out(n2);
    }
    
    public final void Z(final int n) {
        this.N = n;
        this.arraycopy.Z(n);
    }
    
    public final void J() {
        this.arraycopy.start();
    }
    
    public final void S() {
        this.arraycopy.stop();
        this.A = 0;
        this.E = 0;
    }
    
    final void println(final int n) {
        if (n > 0) {
            try {
                Thread.sleep(n);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public final void A() {
        final int a = this.A;
        int n = this.E - a;
        if (n < 0) {
            n += 64000;
        }
        if (n <= 0) {
            return;
        }
        final int n2 = 64000 - a;
        if (n > n2) {
            n = n2;
        }
        final int i = this.arraycopy.I(this.S, a, n);
        this.H += i >> 1;
        int a2 = a + i;
        if (a2 == 64000) {
            a2 = 0;
        }
        this.A = a2;
    }
    
    private static final int sleep(final int n, final int n2) {
        int n3 = n + n2;
        if (n3 < -32768) {
            n3 = -32768;
        }
        else if (n3 > 32767) {
            n3 = 32767;
        }
        return n3;
    }
    
    private final void start(final int[] array, int n, final int n2, final int n3, final int[] array2) {
        final int n4 = ImSound.out[n3];
        final int start = start(6, n2);
        final int stop = stop(1, start(start, 1));
        int n5 = 0;
        int n6 = 13;
        while (n6-- > 0) {
            array2[n5++] = arraycopy(stop(println(n4, (array[n++] << 1) - 7 << 12) + stop), start);
        }
    }
    
    private static final int stop(final int n, final int n2) {
        if (n2 >= 16) {
            return 0;
        }
        if (n2 <= -16) {
            return (n < 0) ? -1 : 0;
        }
        if (n2 < 0) {
            return arraycopy(n, -n2);
        }
        return n << n2;
    }
    
    private static final int arraycopy(final int n, final int n2) {
        if (n2 >= 16) {
            return (n < 0) ? -1 : 0;
        }
        if (n2 <= -16) {
            return 0;
        }
        if (n2 < 0) {
            return n << -n2;
        }
        return n >> n2;
    }
    
    private static final void out(final int[] array, final int[] array2, final int[] array3) {
        for (int i = 0; i < 8; ++i) {
            array3[i] = sleep(array[i] >> 2, array2[i] >> 2);
            array3[i] = sleep(array3[i], array[i] >> 1);
        }
    }
    
    private static final void println(final int[] array, final int[] array2, final int[] array3) {
        for (int i = 0; i < 8; ++i) {
            array3[i] = sleep(array[i] >> 1, array2[i] >> 1);
        }
    }
    
    private static final void sleep(final int[] array, final int[] array2, final int[] array3) {
        for (int i = 0; i < 8; ++i) {
            array3[i] = sleep(array[i] >> 2, array2[i] >> 2);
            array3[i] = sleep(array3[i], array2[i] >> 1);
        }
    }
    
    private static final void start(final int[] array, final int[] array2) {
        for (int i = 0; i < 8; ++i) {
            array2[i] = array[i];
        }
    }
    
    private static final void stop(final int[] array, final int[] array2) {
        final int println = println(13107, sleep(array[0], -32) << 10);
        array2[0] = sleep(println, println);
        final int println2 = println(13107, sleep(array[1], -32) << 10);
        array2[1] = sleep(println2, println2);
        final int println3 = println(13107, start(sleep(array[2], -16) << 10, 4096));
        array2[2] = sleep(println3, println3);
        final int println4 = println(13107, start(sleep(array[3], -16) << 10, -5120));
        array2[3] = sleep(println4, println4);
        final int println5 = println(19223, start(sleep(array[4], -8) << 10, 188));
        array2[4] = sleep(println5, println5);
        final int println6 = println(17476, start(sleep(array[5], -8) << 10, -3584));
        array2[5] = sleep(println6, println6);
        final int println7 = println(31454, start(sleep(array[6], -4) << 10, -682));
        array2[6] = sleep(println7, println7);
        final int println8 = println(29708, start(sleep(array[7], -4) << 10, -2288));
        array2[7] = sleep(println8, println8);
    }
    
    final void C(int i) {
        while (i >= 160) {
            this.stop(this.Q, 0, 2, 6);
            this.stop(this.Q, 2, 4, 5);
            this.stop(this.Q, 4, 6, 4);
            this.stop(this.Q, 6, 8, 3);
            this.stop(this.W, 0, 4, 7);
            this.stop(this.sleep, 0, 4, 2);
            this.stop(this.U, 0, 4, 2);
            this.stop(this.c, 0, 4, 6);
            this.stop(this.b, 0, 52, 3);
            int n = 0;
            for (int j = 0; j < 4; ++j) {
                this.start(this.c[j], this.U[j], this.b, j * 13, this.stop);
                this.out(this.W[j], this.sleep[j], this.stop, this.start);
                for (int k = 0; k < 40; ++k) {
                    this.z[n + k] = this.start[120 + k];
                }
                n += 40;
            }
            this.arraycopy(this.Q, this.z);
            int e = this.E;
            int n2 = 100;
            while (true) {
                int n3 = this.A - e;
                if (n3 <= 0) {
                    n3 += 64000;
                }
                if (n3 > 320) {
                    for (int l = 0; l < 160; ++l) {
                        this.V = stop(this.Y[l] + println(this.V, 28180));
                        int n4 = this.V + this.V & 0xFFFFFFF8;
                        if (n4 < -32768) {
                            n4 = -32768;
                        }
                        else if (n4 > 32767) {
                            n4 = 32767;
                        }
                        this.S[e++] = (byte)(n4 >> 8);
                        this.S[e++] = (byte)n4;
                    }
                    if (e == 64000) {
                        e = 0;
                    }
                    this.E = e;
                    this.A();
                    i -= 160;
                    break;
                }
                if (this.F() == 0 && --n2 == 0) {
                    System.out.println("Audio not working");
                    this.M = true;
                    return;
                }
                this.println(100);
                this.A();
            }
        }
    }
    
    public ImSound(final ImIsys imIsys) {
        this.S = new byte[64000];
        this.G = new byte[640];
        this.sleep = new int[4];
        this.start = new int[280];
        this.stop = new int[40];
        this.O = new int[2];
        this.Q = new int[8];
        this.R = new int[8];
        this.T = new int[2][8];
        this.U = new int[4];
        this.W = new int[4];
        this.Y = new int[160];
        this.v = new int[9];
        this.z = new int[160];
        this.c = new int[4];
        this.b = new int[52];
        this.d = new int[13];
        this.E();
        this.N = 255;
        this.arraycopy = imIsys.I(this);
    }
    
    final void E() {
        this.P = 0;
        this.X = 40;
        this.out(0);
    }
    
    private static final void arraycopy(final int[] array) {
        for (int i = 0; i < 8; ++i) {
            if (array[i] < 0) {
                final int n = (array[i] == -32768) ? 32767 : (-array[i]);
                array[i] = -((n < 11059) ? (n << 1) : ((n < 20070) ? (n + 11059) : sleep(n >> 2, 26112)));
            }
            else {
                final int n2 = array[i];
                array[i] = ((n2 < 11059) ? (n2 << 1) : ((n2 < 20070) ? (n2 + 11059) : sleep(n2 >> 2, 26112)));
            }
        }
    }
    
    private final void out(final int n, final int n2, final int[] array, final int[] array2) {
        final int x = (n < 40 || n > 120) ? this.X : n;
        this.X = x;
        final int n3 = ImSound.println[n2];
        for (int i = 0; i <= 39; ++i) {
            int n4 = n3 * array2[120 + (i - x)] + 16384 >> 15;
            if (n4 < -32768) {
                n4 = -32768;
            }
            else if (n4 > 32767) {
                n4 = 32767;
            }
            int n5 = array[i] + n4;
            if (n5 < -32768) {
                n5 = -32768;
            }
            else if (n5 > 32767) {
                n5 = 32767;
            }
            array2[120 + i] = n5;
        }
        System.arraycopy(array2, 40, array2, 0, 120);
    }
    
    private static final int println(final int n, final int n2) {
        int n3 = n * n2 + 16384 >> 15;
        if (n3 < -32768) {
            n3 = -32768;
        }
        else if (n3 > 32767) {
            n3 = 32767;
        }
        return n3;
    }
    
    private static final void sleep(int n, final int[] array, final int[] array2) {
        int n2 = 13;
        int n3 = 0;
        int n4 = 0;
        switch (n) {
            case 3: {
                array2[n3++] = 0;
            }
            case 2: {
                array2[n3++] = 0;
            }
            case 1: {
                array2[n3++] = 0;
            }
            case 0: {
                array2[n3++] = array[n4++];
                --n2;
                break;
            }
        }
        do {
            array2[n3++] = 0;
            array2[n3++] = 0;
            array2[n3++] = array[n4++];
        } while (--n2 > 0);
        while (++n < 4) {
            array2[n3++] = 0;
        }
    }
    
    private final void start(final int n, final int n2, final int[] array, final int n3, final int[] array2) {
        this.arraycopy(this.O, n);
        this.start(array, n3, this.O[0], this.O[1], this.d);
        sleep(n2, this.d, this.stop);
    }
    
    private static final int stop(final int n) {
        return (n < -32768) ? -32768 : ((n > 32767) ? 32767 : n);
    }
    
    private final void arraycopy(final int[] array, final int[] array2) {
        final int[] array3 = this.T[this.P];
        final int[][] t = this.T;
        final int p2 = this.P ^ 0x1;
        this.P = p2;
        final int[] array4 = t[p2];
        stop(array, array3);
        out(array4, array3, this.R);
        arraycopy(this.R);
        this.out(this.R, 13, array2, this.Y, 0);
        println(array4, array3, this.R);
        arraycopy(this.R);
        this.out(this.R, 14, array2, this.Y, 13);
        sleep(array4, array3, this.R);
        arraycopy(this.R);
        this.out(this.R, 13, array2, this.Y, 27);
        start(array3, this.R);
        arraycopy(this.R);
        this.out(this.R, 120, array2, this.Y, 40);
    }
    
    private final void out(final int[] array, int n, final int[] array2, final int[] array3, final int n2) {
        int n3 = n2;
        int n4 = n2;
        while (n-- > 0) {
            int n5 = array2[n3++];
            for (int i = 7; i >= 0; --i) {
                final int n6 = array[i];
                int n7 = n6 * this.v[i] + 16384 >> 15;
                if (n7 < -32768) {
                    n7 = -32768;
                }
                else if (n7 > 32767) {
                    n7 = 32767;
                }
                n5 -= n7;
                if (n5 < -32768) {
                    n5 = -32768;
                }
                else if (n5 > 32767) {
                    n5 = 32767;
                }
                int n8 = n6 * n5 + 16384 >> 15;
                if (n8 < -32768) {
                    n8 = -32768;
                }
                else if (n8 > 32767) {
                    n8 = 32767;
                }
                int n9 = n8 + this.v[i];
                if (n9 < -32768) {
                    n9 = -32768;
                }
                else if (n9 > 32767) {
                    n9 = 32767;
                }
                this.v[i + 1] = n9;
            }
            array3[n4++] = (this.v[0] = n5);
        }
    }
    
    private static final int start(final int n, final int n2) {
        return stop(n - n2);
    }
    
    private final void stop(final int[] array, final int n, final int n2, final int n3) {
        final int n4 = 255 >> 8 - n3;
        int n5 = this.G[this.K];
        int i = this.i;
        for (int j = n; j < n2; ++j) {
            i -= n3;
            if (i < 0) {
                n5 = (n5 << 8 | (this.G[++this.K] & 0xFF));
                i += 8;
            }
            array[j] = (n5 >> i & n4);
        }
        this.i = i;
    }
    
    private final void arraycopy(final int[] array, final int n) {
        int n2 = 0;
        if (n > 15) {
            n2 = (n >> 3) - 1;
        }
        int i = n - (n2 << 3);
        if (i == 0) {
            n2 = -4;
            i = 7;
        }
        else {
            while (i <= 7) {
                i = (i << 1 | 0x1);
                --n2;
            }
            i -= 8;
        }
        array[0] = n2;
        array[1] = i;
    }
    
    static {
        out = new int[] { 18431, 20479, 22527, 24575, 26623, 28671, 30719, 32767 };
        println = new int[] { 3277, 11469, 21299, 32767 };
    }
}
