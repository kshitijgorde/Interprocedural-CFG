// 
// Decompiled by Procyon v0.5.30
// 

package b;

import webphone.aw;

public class a
{
    boolean int;
    float[] c;
    int a;
    int[] k;
    float[] try;
    int else;
    af e;
    float[] goto;
    int char;
    af i;
    q n;
    l j;
    ae byte;
    o l;
    k h;
    int[] long;
    int void;
    short[] for;
    byte[] case;
    byte[] g;
    boolean do;
    aw b;
    byte[] if;
    int new;
    short[] d;
    float[] o;
    int m;
    short[] f;
    
    public a(final aw b) {
        this.int = true;
        this.c = new float[90];
        this.k = new int[12];
        this.try = new float[22];
        this.e = new af();
        this.goto = new float[80];
        this.i = new af(new Integer(0));
        this.n = new q();
        this.j = new l();
        this.byte = new ae();
        this.l = new o();
        this.h = new k();
        this.long = new int[11];
        this.void = 0;
        this.for = new short[82];
        this.case = new byte[160];
        this.g = new byte[10];
        this.do = false;
        this.b = null;
        this.if = null;
        this.new = -841;
        this.d = new short[80];
        this.o = null;
        this.m = -489;
        this.f = null;
        this.b = b;
    }
    
    public boolean a(final boolean int1) {
        try {
            this.int = int1;
            if (this.int) {
                this.h.a();
                this.l.a();
            }
            else {
                for (int i = 0; i < 10; ++i) {
                    this.c[i] = 0.0f;
                }
                this.a = 10;
                this.n.a();
                this.j.a();
                this.byte.a();
                this.char = 60;
            }
            return this.do = true;
        }
        catch (Exception ex) {
            this.b.a(3, "g729init", ex);
            return false;
        }
    }
    
    public byte[] if(final byte[] array) {
        this.for = ai.a(array);
        ++this.void;
        ai.a(this.for, 2, this.k, 1);
        this.k[0] = 0;
        for (int i = 2; i < 82; ++i) {
            if (this.for[i] == 0) {
                this.k[0] = 1;
            }
        }
        this.k[4] = ab.a(this.k[3], this.k[4]);
        this.n.a(this.k, 0, this.char, this.c, this.a, this.try, this.e);
        this.char = 0;
        this.else = 0;
        for (int j = 0; j < 80; j += 40) {
            this.j.a(this.e.a, this.c, this.a + j, this.try, this.else, this.goto, j, this.i);
            if (this.i.a != 0) {
                this.char = this.i.a;
            }
            this.else += 11;
        }
        b.j.a(this.c, 80, this.c, 0, 10);
        this.byte.a(this.goto, 80);
        return this.a(this.goto, 80);
    }
    
    public byte[] a(final short[] array) {
        if (array.length != this.new) {
            this.if = new byte[array.length * 2];
            this.new = array.length;
        }
        for (int i = 0; i < array.length; ++i) {
            final byte[] a = b.j.a(array[i]);
            this.if[2 * i] = a[0];
            this.if[2 * i + 1] = a[1];
        }
        return this.if;
    }
    
    public byte[] a(final float[] array, final int n) {
        if (n > 80) {
            throw new RuntimeException("error in fwrite16\n");
        }
        for (int i = 0; i < n; ++i) {
            final float n2 = array[i];
            float n3;
            if (n2 >= 0.0f) {
                n3 = n2 + 0.5f;
            }
            else {
                n3 = n2 - 0.5f;
            }
            if (n3 > 32767.0f) {
                n3 = 32767.0f;
            }
            if (n3 < -32768.0f) {
                n3 = -32768.0f;
            }
            this.d[i] = (short)n3;
        }
        return this.a(this.d);
    }
    
    public void a(final byte[] array, final short[] array2) {
        for (int i = 0; i < array2.length; ++i) {
            array2[i] = b.j.a(array[2 * i], array[2 * i + 1]);
        }
    }
    
    public byte[] a(final byte[] array) {
        ++this.void;
        if (this.m != array.length) {
            this.o = new float[array.length];
            this.f = new short[array.length / 2];
            this.m = array.length;
        }
        this.a(array, this.f);
        for (int i = 0; i < 80; ++i) {
            this.o[i] = this.f[i];
        }
        this.h.a(this.o, 80);
        this.l.a(this.o);
        this.l.a(this.long, 0);
        ai.a(this.long, this.for);
        return ai.a(this.for);
    }
    
    public int if(final byte[] array, final int n, final byte[] array2) {
        try {
            if (!this.do) {
                this.b.a(3, "ERROR,g729 using unitialized codec  on encode");
                return 0;
            }
            if (!this.int) {
                this.b.a(3, "ERROR,g729 using encoder on decoder codec");
                return 0;
            }
            if (n < 160 || n > 9000) {
                this.b.a(3, "ERROR,invalid pcm to g729 packet length " + this.b.c(n));
                return 0;
            }
            int n2 = 0;
            for (int n3 = 0; n3 + 160 <= n; n3 += 160) {
                System.arraycopy(array, n3, this.case, 0, 160);
                final byte[] a = this.a(this.case);
                if (this.b.eK >= 4 && a.length != 10) {
                    this.b.a(4, "ERROR,invalid pcm to g729 packet length inner " + this.b.c(a.length));
                }
                else {
                    System.arraycopy(a, 0, array2, n2, 10);
                    n2 += 10;
                }
            }
            return n2;
        }
        catch (Exception ex) {
            this.b.a(3, "g729encode", ex);
            return 0;
        }
    }
    
    public int a(final byte[] array, final int n, final byte[] array2) {
        try {
            if (!this.do) {
                this.b.a(3, "ERROR,g729 using unitialized codec on decode");
                return 0;
            }
            if (this.int) {
                this.b.a(3, "ERROR,g729 using decoder on encoder codec");
                return 0;
            }
            if (n < 10 || n > 3000) {
                this.b.a(3, "ERROR,invalid g729 to pcm packet length " + this.b.c(n));
                return 0;
            }
            int n2 = 0;
            for (int n3 = 0; n3 + 10 <= n; n3 += 10) {
                System.arraycopy(array, n3, this.g, 0, 10);
                final byte[] if1 = this.if(this.g);
                if (this.b.eK >= 4 && if1.length != 160) {
                    this.b.a(4, "ERROR,invalid g729 to pcm packet length inner " + this.b.c(if1.length));
                }
                else {
                    System.arraycopy(if1, 0, array2, n2, 160);
                    n2 += 160;
                }
            }
            return n2;
        }
        catch (Exception ex) {
            this.b.a(3, "g729decode", ex);
            return 0;
        }
    }
}
