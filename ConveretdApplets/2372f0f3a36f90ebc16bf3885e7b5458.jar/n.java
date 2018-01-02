// 
// Decompiled by Procyon v0.5.30
// 

public class n implements Runnable
{
    public static int a;
    public static short[] b;
    public double c;
    public int d;
    public int e;
    public int f;
    public short[] g;
    public Thread h;
    public boolean i;
    public volatile boolean j;
    public o k;
    public p l;
    public q m;
    public r n;
    public s o;
    public t p;
    public u q;
    public v r;
    public x s;
    public int t;
    public byte[] u;
    public int v;
    public int w;
    public int x;
    public int y;
    public int z;
    public int aa;
    public int ab;
    public int ac;
    public int ad;
    public int ae;
    public i af;
    
    public n(final boolean t, final i af) {
        this.c = 1.0;
        this.d = 0;
        this.e = 0;
        this.f = 200;
        this.g = null;
        this.h = null;
        this.i = false;
        this.j = false;
        this.t = 0;
        this.u = null;
        this.v = 0;
        this.w = 0;
        this.x = 0;
        this.y = 100;
        this.z = 100;
        this.aa = 0;
        this.ab = 0;
        this.ac = 0;
        this.ad = 0;
        this.ae = 0;
        this.af = null;
        this.k = new o();
        this.l = new p();
        this.m = new q();
        this.n = new r();
        this.o = new s();
        this.p = new t();
        this.q = new u();
        this.r = new v(this.q);
        this.u = null;
        this.v = 0;
        this.k.c();
        this.n.a();
        this.t = (t ? 1 : 0);
        this.s = new x();
        this.af = af;
        this.g = new short[200000];
        (this.h = new Thread(this)).start();
    }
    
    public void run() {
        this.a(Thread.currentThread());
        this.h = null;
    }
    
    private void a(final Thread thread) {
        int n = 0;
        this.i = false;
    Label_1698:
        while (!this.i) {
            int n2 = 0;
            final int a = this.k.a(4096);
            this.u = this.k.a;
            try {
                this.v = this.af.a(this.u, a, 4096);
            }
            catch (Exception ex3) {
                return;
            }
            this.k.b(this.v);
            if (n != 0) {
                n = 0;
            }
            else if (this.k.b(this.m) != 1) {
                if (this.v < 4096) {
                    break;
                }
                System.err.println(zkmToString("i\u001cxIwI\u0016.eKe*.JrT\u0001zZ~A\u001f "));
                return;
            }
            this.l.a(this.m.f());
            this.l.b();
            this.o.a();
            this.p.a();
            if (this.l.a(this.m) < 0) {
                System.err.println(zkmToString("e\u0000|Gi\u0000\u0000kI\u007fI\u001ci\bVp7V\byI\u0006}\\iE\u0013c\bkA\u0015k\u0006"));
                return;
            }
            if (this.l.a(this.n) != 1) {
                System.err.println(zkmToString("e\u0000|Gi\u0000\u0000kI\u007fI\u001ci\bsE\u0013jMi\u000e"));
                break;
            }
            if (this.o.a(this.p, this.n) < 0) {
                System.err.println(zkmToString("t\u001ag[;m\"Kp;B\u001bz[oR\u0017oE;D\u001dk[;N\u001dz\bxO\u001czIrNRo]\u007fI\u001d "));
                return;
            }
            int i = 0;
            while (i < 2) {
                while (i < 2) {
                    final int b = this.k.b(this.m);
                    if (b == 0) {
                        break;
                    }
                    if (b != 1) {
                        continue;
                    }
                    this.l.a(this.m);
                    while (i < 2) {
                        final int a2 = this.l.a(this.n);
                        if (a2 == 0) {
                            break;
                        }
                        if (a2 == -1) {
                            System.err.println(zkmToString("i\u001cxIwI\u0016.@~A\u0016kZ5"));
                            break Label_1698;
                        }
                        this.o.a(this.p, this.n);
                        ++i;
                    }
                }
                final int a3 = this.k.a(4096);
                this.u = this.k.a;
                try {
                    this.v = this.af.a(this.u, a3, 4096);
                }
                catch (Exception ex) {
                    System.err.println(ex);
                    return;
                }
                if (this.v == 0 && i < 2) {
                    System.err.println(zkmToString("e=H"));
                    return;
                }
                this.k.b(this.v);
            }
            final byte[][] b2 = this.p.b;
            for (int n3 = 0; n3 < b2.length && b2[n3] != null; ++n3) {}
            n.a = 4096 / this.o.c;
            this.q.a(this.o);
            this.r.a(this.q);
            final double[][][] array = { null };
            final float[][][] array2 = { null };
            final int[] array3 = new int[this.o.c];
            if (this.af.g) {
                this.af.h = true;
                this.k.b();
            }
            while (!this.i && n2 == 0) {
                while (!this.i && n2 == 0) {
                    if (this.h != thread) {
                        return;
                    }
                    final int b3 = this.k.b(this.m);
                    if (b3 == 0) {
                        break;
                    }
                    if (b3 == -1) {
                        continue;
                    }
                    this.l.a(this.m);
                    if (this.m.e() == 0L) {
                        n = 1;
                        n2 = 1;
                        break;
                    }
                    while (!this.i) {
                        final int a4 = this.l.a(this.n);
                        if (a4 == 0) {
                            break;
                        }
                        if (a4 == -1) {
                            continue;
                        }
                        if (this.r.a(this.n) == 0) {
                            this.q.a(this.r);
                        }
                        int a5;
                        while ((a5 = this.q.a(array2, array3)) > 0) {
                            final double[][] array4 = array[0];
                            final float[][] array5 = array2[0];
                            final int n4 = (a5 < n.a) ? a5 : n.a;
                            final int[][] array6 = new int[this.o.c][n4];
                            final int[] array7 = new int[n4];
                            for (int j = 0; j < this.o.c; ++j) {
                                int n5 = j;
                                final int n6 = array3[j];
                                for (int k = 0; k < n4; ++k) {
                                    int n7 = (int)(array5[j][n6 + k] * this.c * 32767.0);
                                    if (n7 > 32767) {
                                        n7 = 32767;
                                    }
                                    if (n7 < -32768) {
                                        n7 = -32768;
                                    }
                                    if (n7 < 0) {
                                        n7 |= 0x8000;
                                    }
                                    if (this.t == 1) {
                                        array6[j][k] = n7;
                                    }
                                    n.b[n5] = (short)n7;
                                    n5 += this.o.c;
                                }
                            }
                            final double n8 = this.o.d / 8000.0;
                            final int[] array8 = new int[n4];
                            final int[] array9 = new int[n4];
                            int n9 = 0;
                            if (this.t == 1) {
                                if (this.o.c > 1) {
                                    for (int l = 0; l < n4; ++l) {
                                        array7[l] = 0;
                                        for (int n10 = 0; n10 < this.o.c; ++n10) {
                                            final int[] array10 = array7;
                                            final int n11 = l;
                                            array10[n11] += array6[n10][l];
                                        }
                                        array7[l] /= this.o.c;
                                    }
                                }
                                else {
                                    for (int n12 = 0; n12 < n4; ++n12) {
                                        array7[n12] = array6[0][n12];
                                    }
                                }
                                for (int n13 = 0; n13 < n4; ++n13) {
                                    array8[n13] = array7[n13];
                                }
                                this.s.a(array7, n4, 16, 1, this.o.d);
                                for (int n14 = 0; n14 < n4; ++n14) {
                                    if (array7[n14] == 0) {
                                        final int[] array11 = array9;
                                        final int n15 = 0;
                                        ++array11[n15];
                                    }
                                    if (array7[n14] != array8[n14]) {
                                        final int[] array12 = array9;
                                        final int n16 = 1;
                                        ++array12[n16];
                                    }
                                }
                                double n17 = 0.0;
                                for (int n18 = 0; n18 < n4; ++n18) {
                                    array7[n18] = array7[(int)n17];
                                    n17 += n8;
                                    ++n9;
                                    if ((int)n17 >= n4) {
                                        break;
                                    }
                                }
                                int n19 = 0;
                                for (int n20 = 0; n20 < n9; ++n20) {
                                    n.b[n19] = (short)array7[n20];
                                    ++n19;
                                }
                            }
                            if (this.o.c == 1) {
                                this.aa += n4 / 2;
                            }
                            else {
                                this.aa += n4;
                            }
                            this.ac = n9;
                            int n21 = 0;
                            while (n21 == 0) {
                                synchronized (this.g) {
                                    if (!this.j) {
                                        if (this.t == 1) {
                                            this.e = n9;
                                        }
                                        else {
                                            this.e = this.o.c * n4;
                                        }
                                        System.arraycopy(n.b, 0, this.g, this.d, this.e);
                                        this.d += this.e;
                                        if (this.d > 8000) {
                                            this.j = true;
                                        }
                                        n21 = 1;
                                    }
                                }
                                if (n21 == 0) {
                                    try {
                                        Thread.currentThread();
                                        Thread.sleep(10L);
                                    }
                                    catch (Exception ex2) {
                                        System.err.println(ex2);
                                    }
                                }
                            }
                            this.q.a(n4);
                        }
                    }
                    if (this.m.d() == 0) {
                        continue;
                    }
                    n2 = 1;
                }
                if (n2 == 0) {
                    final int a6 = this.k.a(4096);
                    this.u = this.k.a;
                    try {
                        this.v = this.af.a(this.u, a6, 4096);
                    }
                    catch (Exception ex4) {
                        break;
                    }
                    if (this.v == -1) {
                        System.err.println(zkmToString("B\u000bzMh\u001dO#\u0019"));
                        break;
                    }
                    this.k.b(this.v);
                    if (this.v != 0) {
                        continue;
                    }
                    n2 = 1;
                }
            }
            if (!this.i) {}
            this.l.a();
            this.r.a();
            this.q.a();
            this.o.b();
        }
        this.k.a();
        this.j = true;
    }
    
    public int a(final short[] array) {
        int d = 0;
        try {
            if (this.h == null && !this.j) {
                return 0;
            }
            int i = 0;
            final i af = this.af;
            af.x += this.d;
            while (i == 0) {
                synchronized (this.g) {
                    if (this.j) {
                        d = this.d;
                        System.arraycopy(this.g, 0, array, 0, this.d);
                        this.d = 0;
                        this.j = false;
                        i = 1;
                    }
                }
                try {
                    if (i != 0) {
                        continue;
                    }
                    Thread.currentThread();
                    Thread.sleep(10L);
                }
                catch (Exception ex2) {}
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return d;
    }
    
    static {
        n.a = 8192;
        n.b = new short[n.a];
    }
    
    private static String zkmToString(final String s) {
        final char[] charArray = s.toCharArray();
        for (int length = charArray.length, i = 0; i < length; ++i) {
            final char[] array = charArray;
            final int n = i;
            final char c = array[n];
            char c2 = '\0';
            switch (i % 5) {
                case 0: {
                    c2 = ' ';
                    break;
                }
                case 1: {
                    c2 = 'r';
                    break;
                }
                case 2: {
                    c2 = '\u000e';
                    break;
                }
                case 3: {
                    c2 = '(';
                    break;
                }
                default: {
                    c2 = '\u001b';
                    break;
                }
            }
            array[n] = (char)(c ^ c2);
        }
        return new String(charArray);
    }
}
