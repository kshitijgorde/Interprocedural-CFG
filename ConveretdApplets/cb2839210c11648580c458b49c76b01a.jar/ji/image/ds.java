// 
// Decompiled by Procyon v0.5.30
// 

package ji.image;

import java.lang.reflect.Field;
import java.lang.reflect.Array;
import java.lang.reflect.Modifier;
import java.io.ByteArrayInputStream;
import ji.zip.a4;
import ji.util.e;
import ji.res.ay;
import ji.res.s;
import ji.io.h;
import ji.util.d;
import java.awt.Dimension;
import java.awt.Point;
import ji.v1event.af;
import java.awt.Component;
import ji.util.i;
import ji.document.ad;
import ji.adjustment.eh;
import java.awt.Color;
import java.awt.Image;
import ji.io.q;
import ji.io.ac;
import java.util.Vector;

public class ds
{
    private boolean a;
    private boolean b;
    private boolean c;
    private boolean d;
    private boolean e;
    private boolean f;
    private boolean g;
    private boolean h;
    private boolean i;
    public long j;
    public long k;
    public long l;
    public long m;
    public int n;
    public boolean o;
    public int p;
    public long q;
    public long r;
    public long s;
    public int t;
    private long u;
    public int v;
    public int[] w;
    private int x;
    protected Vector y;
    private Vector z;
    private Vector aa;
    private boolean[] ab;
    private boolean ac;
    private boolean ad;
    private int ae;
    private int af;
    private String ag;
    private String ah;
    private int[] ai;
    private byte[] aj;
    private byte[] ak;
    private byte[] al;
    private int am;
    private boolean an;
    private int ao;
    private byte[] ap;
    private long aq;
    private long ar;
    private int[] as;
    private int at;
    private long au;
    private boolean av;
    private boolean aw;
    private boolean ax;
    private boolean ay;
    private int az;
    private boolean a0;
    public ac a1;
    private ac a2;
    private String a3;
    private String a4;
    private ac a5;
    private String a6;
    private q a7;
    private boolean a8;
    private int a9;
    private long ba;
    private int bb;
    private int bc;
    private long bd;
    private int[] be;
    private byte[] bf;
    private byte[] bg;
    private boolean bh;
    private boolean bi;
    private Image bj;
    private int bk;
    private int bl;
    private byte bm;
    private int bn;
    private int bo;
    private int bp;
    private int bq;
    private long br;
    private long bs;
    private byte[] bt;
    private int[] bu;
    private int[] bv;
    private int bw;
    private long bx;
    private long by;
    private byte[] bz;
    private int[] b0;
    private int[] b1;
    private String b2;
    private ac b3;
    public long b4;
    private long b5;
    private byte[] b6;
    private int[] b7;
    private int[] b8;
    private int b9;
    private int ca;
    private int cb;
    private boolean cc;
    public String cd;
    private ds ce;
    private ds cf;
    private ds cg;
    private ds ch;
    private dx ci;
    private Color cj;
    private boolean ck;
    private boolean cl;
    private byte cm;
    private byte cn;
    private byte co;
    private boolean cp;
    private int cq;
    private int cr;
    private int cs;
    private int[] ct;
    private long[] cu;
    private long[] cv;
    private int[] cw;
    private long[] cx;
    private long[] cy;
    private boolean cz;
    private int c0;
    private int c1;
    private byte[] c2;
    private long c3;
    private boolean c4;
    private long c5;
    private int c6;
    private int[][] c7;
    private boolean c8;
    private boolean c9;
    private boolean da;
    private boolean db;
    private double dc;
    private double dd;
    private boolean de;
    private int df;
    private eh dg;
    private boolean dh;
    private int di;
    static final Object dj;
    static /* synthetic */ Class dk;
    
    public final long a() {
        return this.bx;
    }
    
    public final long b() {
        return this.u;
    }
    
    public String c() {
        return this.a3;
    }
    
    public final void a(final int p) {
        this.p = p;
    }
    
    public final void a(final boolean f) {
        this.f = f;
    }
    
    public final void a(final Color cj) {
        this.cj = cj;
    }
    
    public final Color d() {
        return this.cj;
    }
    
    public final void a(final dx ci) {
        this.ci = ci;
    }
    
    public void a(final eh dg) {
        this.dg = dg;
    }
    
    public final void a(final ds ce, final int cb, final ad ad, final boolean b) {
        if (ce != null) {
            this.cb = cb;
        }
        if (ce != null && ji.util.i.c(59)) {
            this.cf = new ds("foregroundDIB:".concat(String.valueOf(String.valueOf(this.ag))), this.cd);
            if (this.dg == null) {
                this.dg = new eh();
            }
            this.dg.a(ad);
        }
        this.a(ad, b);
        this.ce = ce;
        this.f();
    }
    
    public final void b(final boolean g) {
        this.g = g;
    }
    
    public final void a(final ad ad, final boolean b) {
        if (this.a1 == null && this.ce != null) {
            if (this.v == 3) {
                if (this.cf != null) {
                    try {
                        this.cf.a((int)this.cf.j, (int)this.cf.k, ad);
                        this.cf.a(this.aj);
                        this.cf.c(ad);
                        this.a(ad, ad, b);
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                else if (this.ce != null) {
                    this.aj = fk.a(this, this.ce, this.aj, this.aj.length, 0, (int)(this.k - 1), 0, this.cb);
                }
            }
            else if (this.v == 2) {
                if (this.cf != null) {
                    try {
                        this.cf.a((int)this.cf.j, (int)this.cf.k, ad);
                        this.cf.a(this.aj);
                        this.cf.c(ad);
                        this.a(ad, ad, b);
                    }
                    catch (Exception ex2) {
                        ex2.printStackTrace();
                    }
                }
                else if (this.ce != null) {
                    this.aj = fk.a(this, this.ce, this.aj, this.aj.length, 0, (int)(this.k - 1), this.cb);
                }
            }
        }
        this.f();
    }
    
    private byte[] b(final byte[] array) {
        int n = (int)this.j;
        final int n2 = n % 2;
        if (n2 > 0) {
            n += 2 - n2;
        }
        final int n3 = n / 2;
        final byte[] array2 = new byte[n * (int)this.k];
        for (int n4 = 0; n4 < this.k; ++n4) {
            final int n5 = n4 * n;
            final int n6 = n4 * n3;
            for (int i = 0; i < n; ++i) {
                array2[n5 + i] = (byte)((byte)((byte)(array[n6 + i / 2] & 0xFF) >> 4 - 4 * (i % 2)) & 0xF);
            }
        }
        this.f();
        return array2;
    }
    
    private byte[] b(final byte[] array, final int n, final int n2) {
        int n3 = n;
        final int n4 = n3 % 2;
        if (n4 > 0) {
            n3 += 2 - n4;
        }
        final int n5 = n3 / 2;
        final byte[] array2 = new byte[n5 * n2];
        for (int i = 0; i < n2; ++i) {
            final int n6 = i * n5;
            final int n7 = i * n;
            for (int j = 0; j < n; ++j) {
                if (j % 2 == 0) {
                    final byte[] array3 = array2;
                    final int n8 = n6 + j / 2;
                    array3[n8] |= (byte)(array[n7 + j] << 4);
                }
                else {
                    final byte[] array4 = array2;
                    final int n9 = n6 + j / 2;
                    array4[n9] |= array[n7 + j];
                }
            }
        }
        this.f();
        return array2;
    }
    
    public void a(final ds ch, final Component component) {
        if (this.ch != null) {
            this.ch.h(component);
            this.ch = null;
        }
        this.ch = ch;
        this.f();
    }
    
    public void b(final ds cg, final Component component) {
        if (this.cg != null) {
            this.cg.h(component);
            this.cg = null;
        }
        this.cg = cg;
        this.f();
    }
    
    public ds a(final Component component, final af af, final String s) throws Exception {
        System.currentTimeMillis();
        this.di = 180;
        ds ds = new ds("rotatedDIB:".concat(String.valueOf(String.valueOf(this.ag))), this.cd);
        try {
            if (this.t == 1) {
                if (this.bi) {
                    ds = this;
                    final int n = (int)this.k;
                    final int[] array = new int[n];
                    final int[] array2 = new int[n];
                    for (int i = 0; i < n; ++i) {
                        array[i] = this.bu[n - i - 1];
                        array2[i] = this.bv[n - i - 1];
                    }
                    this.a(array, array2, this.q, this.ca);
                    this.dh = true;
                }
                else {
                    ds.a(null, (int)this.j, (int)this.k, this.n, this.t, component, this.ci);
                    ds.a(this.v, this.av, this.bi, component);
                    final int size = this.y.size();
                    final Vector vector = new Vector<short[]>(size);
                    for (int j = 0; j < size; ++j) {
                        final short[] array3 = this.y.elementAt(size - j - 1);
                        final int length = array3.length;
                        short[] array4;
                        if (length % 2 > 0) {
                            array4 = new short[length];
                            for (int k = 0; k < length; ++k) {
                                array4[k] = array3[length - k - 1];
                            }
                        }
                        else {
                            array4 = new short[length + 1];
                            array4[0] = 0;
                            for (int l = 1; l < length; ++l) {
                                array4[l] = array3[length - l];
                            }
                        }
                        vector.addElement(array4);
                    }
                    ds.a(vector);
                }
            }
            else if (this.t <= 8) {
                ds = this;
                final byte[] ag = this.ag();
                final byte[] aj = new byte[ag.length];
                int n2 = (int)this.j;
                if (n2 % 2 == 1) {
                    ++n2;
                }
                if (this.t == 8) {
                    final int n3 = n2;
                    final int n4 = (int)this.k * n3;
                    for (int n5 = 0; n5 < this.k; ++n5) {
                        final int n6 = n3 * n5;
                        final int n7 = n4 - n6 - 1;
                        for (int n8 = 0; n8 < n3; ++n8) {
                            aj[n7 - n8] = ag[n6 + n8];
                        }
                    }
                }
                else {
                    final int n9 = n2 / 2;
                    final int n10 = (int)this.k * n9;
                    for (int n11 = 0; n11 < this.k; ++n11) {
                        final int n12 = n9 * n11;
                        final int n13 = n10 - n12 - 1;
                        for (int n14 = 0; n14 < n9; ++n14) {
                            final byte b = ag[n12 + n14];
                            aj[n13 - n14] = (byte)((byte)(b << 4 & 0xF0) | (b >> 4 & 0xF));
                        }
                    }
                }
                this.aj = aj;
            }
            else {
                ds = this;
                this.dh = true;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
        this.f();
        return ds;
    }
    
    private final void a(final int[] bu, final int[] bv, final long q, final int ca) throws Exception {
        this.bu = bu;
        this.bv = bv;
        this.q = q;
        this.ca = ca;
    }
    
    public final void a(final boolean a8, final boolean b, final int n) {
        this.a8 = a8;
        if (b && a8 && n == 3) {
            this.e = false;
        }
    }
    
    public final void a(final Vector vector) {
        final int size = vector.size();
        this.u = 0L;
        this.y.removeAllElements();
        for (int i = 0; i < size; ++i) {
            this.y.addElement(vector.elementAt(i));
            this.u += ((short[])vector.elementAt(i)).length * 2;
        }
        this.f();
    }
    
    public final int e() {
        return this.di;
    }
    
    public ds a(double n, final Component component, final Point point, final boolean b, final int n2, final int n3, final af af, final Dimension dimension, final String s, final boolean b2) throws Exception {
        final boolean b3 = true;
        while (n >= 360.0) {
            n -= 360.0;
        }
        while (n < 0.0) {
            n += 360.0;
        }
        if (n == 180 && b3) {
            return this.a(component, af, s);
        }
        System.currentTimeMillis();
        ds ds = new ds("foregroundDIB:".concat(String.valueOf(String.valueOf(this.ag))), this.cd);
        if (this.a7 == null) {
            this.a7 = ji.io.q.a(component, "jiDIB:RotateDIB");
        }
        ds.a(this.a7.n(), (int)this.j, (int)this.k, this.n, this.t, component, this.ci);
        ds.a(this.v, this.av, this.bi, component);
        ds.a(this.l(), this.ci);
        this.di = (int)n;
        if (this.t == 1) {
            try {
                final dw dw = new dw((int)this.k, (int)this.j, component, ji.util.d.a(0, false, false, this.cd), this.cd, false, this.t);
                if (this.bi) {
                    if (this.aa()) {
                        this.e(component);
                    }
                    final byte[] array = new byte[this.ca];
                    for (int i = 0; i < (int)this.k; ++i) {
                        this.b(i, array);
                        final short[] array2 = new short[this.ca];
                        for (int n4 = this.bu[i], j = 0; j < n4; ++j) {
                            array2[j] = (short)(array[j] & 0xFF);
                        }
                        dw.a(i, ji.util.d.a(array2, (int)this.j, (int)this.j), (int)this.j);
                    }
                }
                else {
                    final int size = this.y.size();
                    final int n5 = size / 20;
                    for (int k = 0; k < size; ++k) {
                        dw.a(k, ji.util.d.a((short[])this.y.elementAt(k), (int)this.j, (int)this.j), (int)this.j);
                    }
                }
                final dw a = fl.a(n, dw, component, this.cd, point, b, n2, n3, (byte)0, af, dimension, "ImageAdjust", s);
                if (a != null) {
                    final int l = a.i();
                    final int m = a.k();
                    if (this.bi) {
                        ds.a(l, m, component);
                    }
                    final byte[] array3 = new byte[l];
                    final Vector vector = new Vector<short[]>(m);
                    int n6 = 0;
                    for (int n7 = 0; n7 < m; ++n7) {
                        final short[] d = ji.util.d.d(a.b(n7));
                        if (this.bi) {
                            ds.b(d, d.length, n7);
                        }
                        else {
                            vector.addElement(d);
                            n6 += d.length;
                        }
                    }
                    if (this.bi) {
                        ds.c(component);
                    }
                    else {
                        ds.a(vector, n6);
                        ds.k = m;
                        ds.j = l;
                    }
                }
                else {
                    ds = null;
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        else if (this.t <= 8) {
            try {
                final byte[] ag = this.ag();
                this.a((int)this.j, (int)this.k, component);
                final dw dw2 = new dw((int)this.k, (int)this.j, component, ji.util.d.a(0, false, false, this.cd), this.cd, false, this.t);
                byte[] b4;
                if (this.t == 4) {
                    b4 = this.b(ag);
                }
                else {
                    b4 = ag;
                }
                dw2.a(0, b4, (int)this.j, (int)this.k);
                final dw a2 = fl.a(n, dw2, component, this.cd, point, false, n2, n3, (byte)(-1), af, dimension, "ImageAdjust", s);
                if (a2 != null) {
                    byte[] array4 = a2.n();
                    a2.i();
                    a2.k();
                    if (this.t == 4) {
                        array4 = this.b(array4, a2.i(), a2.k());
                    }
                    ds.a(array4);
                    ds.a(a2.i(), a2.k());
                    ds.c(component);
                }
                else {
                    ds = null;
                }
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
        }
        else {
            try {
                final du du = new du((int)this.k, (int)this.j, component, ji.util.d.a(0, false, false, this.cd), this.cd, false, this.t, b2);
                du.a(0, 0, 1.0, 1.0, 0, 0);
                final int[] array5 = new int[(int)this.j];
                this.e(component);
                for (int n8 = 0; n8 < this.k; ++n8) {
                    this.c(n8);
                    this.a(array5, (int)this.j);
                    du.a(n8, array5);
                }
                final du a3 = fl.a(n, du, component, this.cd, point, false, n2, n3, af, dimension, "ImageAdjust", s, b2);
                if (a3 != null) {
                    final int f = a3.f();
                    final int h = a3.h();
                    ds.a(f, h, component);
                    for (int n9 = 0; n9 < h; ++n9) {
                        ds.a(a3.a(n9), f, component, n9 + 1, n9 + 2, true);
                    }
                }
                else {
                    ds = null;
                }
            }
            catch (Exception ex3) {
                ex3.printStackTrace();
            }
            finally {
                try {
                    ds.e(component);
                }
                catch (Exception ex4) {
                    if (ji.util.d.cy()) {
                        ex4.printStackTrace();
                    }
                }
            }
        }
        this.f();
        return ds;
    }
    
    public final void f() {
        this.bx = System.currentTimeMillis();
    }
    
    public final void c(final boolean cc) {
        this.cc = cc;
    }
    
    public ds(final String s, final String cd) {
        this.a = false;
        this.b = false;
        this.c = true;
        this.d = false;
        this.e = true;
        this.f = false;
        this.g = false;
        this.h = false;
        this.i = false;
        this.j = 0L;
        this.k = 0L;
        this.l = 0L;
        this.m = 0L;
        this.n = 0;
        this.o = false;
        this.p = 0;
        this.q = 0L;
        this.r = 0L;
        this.s = 0L;
        this.t = 0;
        this.u = 0L;
        this.v = 0;
        this.w = null;
        this.x = 0;
        this.y = null;
        this.z = null;
        this.aa = null;
        this.ab = null;
        this.ac = false;
        this.ad = false;
        this.ae = 0;
        this.af = 0;
        this.ag = null;
        this.ah = null;
        this.ai = null;
        this.aj = null;
        this.ak = null;
        this.al = null;
        this.am = 0;
        this.an = false;
        this.ao = 0;
        this.ap = null;
        this.aq = 0L;
        this.ar = 0L;
        this.as = null;
        this.at = 102400;
        this.au = 0L;
        this.av = true;
        this.aw = false;
        this.ax = true;
        this.ay = true;
        this.az = 2;
        this.a0 = false;
        this.a1 = null;
        this.a2 = null;
        this.a3 = null;
        this.a4 = null;
        this.a5 = null;
        this.a6 = null;
        this.a7 = null;
        this.a8 = false;
        this.a9 = 0;
        this.ba = -1L;
        this.bb = 102400;
        this.bc = this.bb;
        this.bd = 0L;
        this.be = null;
        this.bf = null;
        this.bg = null;
        this.bh = false;
        this.bi = false;
        this.bj = null;
        this.bk = 0;
        this.bl = 0;
        this.bm = -1;
        this.bn = 0;
        this.bo = 0;
        this.bp = 0;
        this.bq = 0;
        this.br = 0L;
        this.bs = 0L;
        this.bt = null;
        this.bu = null;
        this.bv = null;
        this.bw = 0;
        this.bx = 0L;
        this.by = 0L;
        this.bz = null;
        this.b0 = null;
        this.b1 = null;
        this.b2 = null;
        this.b3 = null;
        this.b4 = 0L;
        this.b5 = 0L;
        this.b6 = null;
        this.b7 = null;
        this.b8 = null;
        this.b9 = 204800;
        this.ca = 0;
        this.cb = 0;
        this.cc = false;
        this.cd = null;
        this.ce = null;
        this.cf = null;
        this.cg = null;
        this.ch = null;
        this.ci = null;
        this.cj = null;
        this.ck = false;
        this.cl = false;
        this.cm = 0;
        this.cn = 0;
        this.co = 0;
        this.cp = false;
        this.cq = 0;
        this.cr = 2;
        this.cs = this.cr + 1;
        this.ct = new int[this.cr];
        this.cu = new long[this.cr];
        this.cv = new long[this.cr];
        this.cw = null;
        this.cx = null;
        this.cy = null;
        this.cz = false;
        this.c0 = 0;
        this.c1 = 0;
        this.c2 = null;
        this.c3 = 0L;
        this.c4 = true;
        this.c5 = 0L;
        this.c6 = 0;
        this.c7 = null;
        this.c8 = false;
        this.c9 = false;
        this.da = false;
        this.db = false;
        this.dc = 0.0;
        this.dd = 0.0;
        this.de = false;
        this.df = 0;
        this.dg = null;
        this.dh = false;
        this.di = 0;
        this.cd = cd;
        try {
            if (s != null) {
                this.ag = ji.util.d.h(s, cd);
            }
        }
        catch (Exception ex) {}
        this.f();
    }
    
    public final boolean g() {
        return this.av;
    }
    
    public final void d(final boolean aw) {
        this.f();
        this.aw = aw;
    }
    
    public final boolean h() {
        this.f();
        return this.aw;
    }
    
    public final void a(final boolean a0, final Object o) throws Exception {
        this.a0 = a0;
        this.k(true);
        if (this.bi && this.a1 != null && this.ax) {
            try {
                this.a(this.a1);
                this.e(o);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        this.f();
    }
    
    private void a(final ac ac) throws Exception {
        ac.a(0L);
        this.a(ac, this.bu, (int)this.k, false);
        this.a(ac, this.bv, (int)this.k, false);
        this.f();
    }
    
    private void b(final ac ac) throws Exception {
        ac.a(0L);
        this.a(ac, this.bu, (int)this.k);
        this.a(ac, this.bv, (int)this.k);
        this.f();
    }
    
    public final boolean i() {
        return this.a0;
    }
    
    public void a(final Object o) {
        try {
            this.an();
            this.f(o);
            this.n(o);
            this.o(o);
            this.aw = false;
            this.a0 = false;
            this.ax = true;
            if (this.t <= 1) {
                if (this.y != null) {
                    this.y.removeAllElements();
                }
                this.al();
                this.q = 0L;
            }
            else if (this.t <= 4) {
                if (!this.bi) {
                    this.ak = new byte[(int)(this.k * (1 + this.j / 2))];
                    this.aq = this.j;
                    this.ar = this.k;
                }
            }
            else if (this.t <= 8) {
                this.ak = new byte[(int)(this.k * this.j)];
                this.aq = this.j;
                this.ar = this.k;
            }
            else {
                this.ba = -1L;
                this.bd = -1L;
                this.be = null;
                this.bf = null;
                this.a1 = null;
                this.a3 = null;
            }
        }
        catch (Exception ex) {
            ji.util.d.a(ex);
        }
        this.f();
    }
    
    private void an() {
        this.bh = false;
        this.bj = null;
    }
    
    public final void b(final Object o) {
        if (this.h) {
            this.i = true;
        }
        else {
            this.h(o);
        }
    }
    
    private final void h(final Object o) {
        try {
            this.a0 = false;
            this.an();
            this.f(o);
            this.n(o);
            this.o(o);
            if (this.ac) {
                if (ji.util.i.c(36)) {
                    ji.io.h.d(this.cd, "DIB: close synchronizing");
                }
                synchronized (ds.dj) {
                    if (ji.util.i.c(36)) {
                        ji.io.h.d(this.cd, "DIB: close synchronized");
                    }
                    try {
                        if (this.cf != null) {
                            this.cf.h(o);
                            this.cf = null;
                        }
                        if (this.cg != null) {
                            this.cg.h(o);
                            this.cg = null;
                        }
                        if (this.ch != null) {
                            this.ch.h(o);
                            this.ch = null;
                        }
                        this.ci = null;
                        this.ce = null;
                        this.cf = null;
                        this.ah = null;
                        this.ai = null;
                        this.aj = null;
                        this.ak = null;
                        this.as = null;
                        this.ap = null;
                        this.w = null;
                        this.ac = false;
                        this.bt = null;
                        this.af = 0;
                        this.bu = null;
                        this.bv = null;
                        this.ax = true;
                        this.br = 0L;
                        this.by = 0L;
                        this.bz = null;
                        this.b0 = null;
                        this.b1 = null;
                        this.b2 = null;
                        this.b3 = null;
                        this.b4 = 0L;
                        this.b5 = 0L;
                        this.ca = 0;
                        this.am = 0;
                        this.ao = 0;
                        this.al = null;
                        if (this.y != null) {
                            this.y.removeAllElements();
                            this.y = null;
                        }
                        if (this.z != null) {
                            this.z.removeAllElements();
                            this.z = null;
                        }
                        if (this.aa != null) {
                            this.aa.removeAllElements();
                            this.aa = null;
                        }
                        if (this.ab != null) {
                            this.ab = null;
                        }
                        this.bi = false;
                    }
                    finally {
                        this.ad = false;
                    }
                    if (ji.util.i.c(36)) {
                        ji.io.h.d(this.cd, "DIB: close end synchronized");
                    }
                }
                // monitorexit(ds.dj)
            }
        }
        finally {
            this.i = false;
        }
    }
    
    public final boolean j() {
        return this.bi;
    }
    
    public final void a(final int[] array, final dx dx) {
        if (this.cf != null) {
            this.cf.a(array, dx);
        }
        this.d = false;
        if (array != null) {
            this.as = array.clone();
            if (this.v == 3) {
                if (this.c && ji.util.d.b(this.as)) {
                    for (int i = 0; i < this.as.length; ++i) {
                        final int n = this.as[i];
                        final int n2 = (n & 0xFF0000) >> 16;
                        final int n3 = (n & 0xFF00) >> 8;
                        final int n4 = n & 0xFF;
                        if (n2 == 255 && n3 == 255 && n4 == 255) {
                            this.cm = (byte)i;
                            break;
                        }
                    }
                    this.ck = true;
                    this.cp = false;
                    for (int j = 0; j < this.as.length; ++j) {
                        this.as[j] = -16777216 + (j << 16) + (j << 8) + j;
                    }
                    this.co = -1;
                    this.cn = 0;
                    dx.l = String.valueOf(String.valueOf(dx.l)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer(" (").append(ji.res.s.a(1195, this.cd)).append(")"))))));
                    this.d = true;
                }
                else if (this.c && ji.util.d.a(this.as)) {
                    this.cp = false;
                }
            }
        }
        else {
            this.as = null;
        }
        this.f();
    }
    
    public final boolean k() {
        return this.d;
    }
    
    public final int[] l() {
        return this.as;
    }
    
    public final void c(final Object o) throws Exception {
        if (this.bi) {
            this.a(this.b3);
            this.au = this.by;
            this.q = this.b4;
            if (this.bz != null) {
                this.bt = new byte[this.bz.length];
                for (int i = 0; i < this.bz.length; ++i) {
                    this.bt[i] = this.bz[i];
                }
            }
            if (this.b0 != null) {
                this.bu = new int[this.b0.length];
                for (int j = 0; j < this.b0.length; ++j) {
                    this.bu[j] = this.b0[j];
                }
            }
            if (this.b1 != null) {
                this.bv = new int[this.b1.length];
                for (int k = 0; k < this.b1.length; ++k) {
                    this.bv[k] = this.b1[k];
                }
            }
            this.a3 = this.b2;
            this.a1 = this.b3;
            this.e(o);
            this.br = this.b5;
            this.j = this.aq;
            this.k = this.ar;
        }
        else {
            this.j = this.aq;
            this.k = this.ar;
            this.q = this.ak.length;
            this.aj = new byte[(int)this.q];
            for (int n = 0; n < this.q; ++n) {
                this.aj[n] = this.ak[n];
            }
            this.ak = null;
        }
        this.at();
        this.f();
    }
    
    public final void a(final int n, final int n2, final Object o) throws Exception {
        this.ax = true;
        if (this.t <= 4) {
            if (this.bi) {
                if (this.a2 == null) {
                    this.a2 = this.a1;
                    this.a4 = this.a3;
                    this.l = this.j;
                    this.m = this.k;
                    this.bs = this.br;
                    if (this.bt != null) {
                        this.b6 = new byte[this.bt.length];
                        for (int i = 0; i < this.bt.length; ++i) {
                            this.b6[i] = this.bt[i];
                        }
                    }
                    if (this.bu != null) {
                        this.b7 = new int[this.bu.length];
                        for (int j = 0; j < this.bu.length; ++j) {
                            this.b7[j] = this.bu[j];
                        }
                    }
                    if (this.bv != null) {
                        this.b8 = new int[this.bv.length];
                        for (int k = 0; k < this.bv.length; ++k) {
                            this.b8[k] = this.bv[k];
                        }
                    }
                }
                this.by = 0L;
                this.b4 = 0L;
                this.bz = new byte[this.bw];
                this.b0 = new int[n2];
                this.b1 = new int[n2];
                this.b2 = this.a7.n();
                this.a(this.b3 = new ac(this.b2, true, false, 1048576, false, o, this.cd));
                this.b5 = this.b3.r();
                this.aq = n;
                this.ar = n2;
            }
            else {
                if (this.ap == null) {
                    final int length = this.aj.length;
                    this.ap = new byte[length];
                    for (int l = 0; l < length; ++l) {
                        this.ap[l] = this.aj[l];
                    }
                    this.l = this.j;
                    this.m = this.k;
                }
                this.ak = new byte[n2 * (1 + n / 2)];
                this.aq = n;
                this.ar = n2;
            }
        }
        else if (this.t <= 8) {
            if (this.ap == null) {
                final int length2 = this.aj.length;
                this.ap = new byte[length2];
                for (int n3 = 0; n3 < length2; ++n3) {
                    this.ap[n3] = this.aj[n3];
                }
                this.l = this.j;
                this.m = this.k;
            }
            this.ak = new byte[n2 * n];
            this.aq = n;
            this.ar = n2;
        }
        else {
            if (this.a2 == null) {
                this.a2 = this.a1;
                this.a4 = this.a3;
                this.l = this.j;
                this.m = this.k;
            }
            this.ba = -1L;
            this.bd = -1L;
            this.be = null;
            this.bf = null;
            this.a1 = null;
            this.a3 = null;
            this.c0 = 0;
            this.j = n;
            this.k = n2;
            this.at();
        }
        this.f();
    }
    
    public final void a(final Vector vector, final int n) {
        if (vector != null) {
            final int size = vector.size();
            if (this.y != null) {
                final int size2 = this.y.size();
                if (this.z == null) {
                    this.z = new Vector(size2);
                    for (int i = 0; i < size2; ++i) {
                        this.z.addElement(this.y.elementAt(i));
                    }
                }
                this.y.removeAllElements();
            }
            this.al();
            this.y = null;
            this.y = new Vector(size);
            for (int j = 0; j < size; ++j) {
                this.y.addElement(vector.elementAt(j));
                this.u += ((short[])vector.elementAt(j)).length * 2;
            }
            this.q = n;
        }
        this.f();
    }
    
    public final void d(final Object o) {
        if (this.bi) {
            this.j(o);
        }
        else if (this.t <= 1) {
            this.ap();
        }
        else if (this.t <= 8) {
            this.ao();
        }
        else {
            this.i(o);
        }
        this.f();
    }
    
    public final int m() {
        return (int)this.j;
    }
    
    public final int n() {
        return (int)this.k;
    }
    
    private final void i(final Object o) {
        if (this.a2 != null) {
            this.f(o);
            this.a1 = this.a2;
            this.a3 = this.a4;
            this.j = this.l;
            this.k = this.m;
            this.ba = -1L;
            this.bd = -1L;
            this.be = null;
            this.bf = null;
            this.at();
        }
        this.f();
    }
    
    private final void j(final Object o) {
        if (this.a2 != null) {
            this.f(o);
            this.a1 = this.a2;
            this.a3 = this.a4;
            this.br = this.bs;
            this.j = this.l;
            this.k = this.m;
            this.at();
            this.ba = -1L;
            this.bd = -1L;
            this.be = null;
            this.bf = null;
            if (this.b6 != null) {
                this.bt = new byte[this.b6.length];
                for (int i = 0; i < this.b6.length; ++i) {
                    this.bt[i] = this.b6[i];
                }
            }
            if (this.b7 != null) {
                this.bu = new int[this.b7.length];
                for (int j = 0; j < this.b7.length; ++j) {
                    this.bu[j] = this.b7[j];
                }
            }
            if (this.b8 != null) {
                this.bv = new int[this.b8.length];
                for (int k = 0; k < this.b8.length; ++k) {
                    this.bv[k] = this.b8[k];
                }
            }
        }
        this.f();
    }
    
    private final void ao() {
        if (this.ap != null) {
            this.aj = null;
            final int length = this.ap.length;
            this.aj = new byte[length];
            for (int i = 0; i < length; ++i) {
                this.aj[i] = this.ap[i];
            }
            this.j = this.l;
            this.k = this.m;
            this.at();
        }
        this.f();
    }
    
    private final void ap() {
        if (this.z != null) {
            final int size = this.z.size();
            int n = 0;
            if (this.y != null) {
                this.y.removeAllElements();
            }
            this.y = new Vector(size);
            this.u = 0L;
            for (int i = 0; i < size; ++i) {
                final short[] array = this.z.elementAt(i);
                n += array.length;
                this.y.addElement(array);
                this.u += array.length * 2;
            }
            this.al();
            this.z.removeAllElements();
            this.z = null;
            this.q = n;
        }
        this.f();
    }
    
    public final void b(final int n) throws Exception {
        int i = 0;
        if (this.y != null && n > 0) {
            while (i < n) {
                try {
                    this.y.removeElementAt(this.y.size() - 1);
                }
                catch (Exception ex) {}
                ++i;
            }
        }
        this.f();
    }
    
    public final boolean o() {
        return this.ax;
    }
    
    public final void a(final double dc, final double dd, final boolean de, final int df) {
        this.dc = dc;
        this.dd = dd;
        this.de = de;
        this.df = df;
    }
    
    public final double p() {
        return this.dc;
    }
    
    public final double q() {
        return this.dd;
    }
    
    public final boolean r() {
        return this.de;
    }
    
    public final int s() {
        return this.df;
    }
    
    public final void a(final int n, final boolean b, final Object o) throws Exception {
        if (!this.da) {
            this.a(n, b, false, o);
            this.da = true;
        }
    }
    
    public final void b(final int n, final boolean b, final Object o) throws Exception {
        this.a(n, b, false, o);
    }
    
    private final void aq() {
        try {
            this.cp = true;
            this.cq = 0;
            for (int i = 0; i < this.ct.length; ++i) {
                this.ct[i] = -1;
            }
            for (int j = 0; j < this.cv.length; ++j) {
                this.cv[j] = 33554431L;
            }
        }
        catch (Exception ex) {}
    }
    
    public final int t() {
        return this.t;
    }
    
    public final void a(final int n, final boolean b, final boolean b2, final Object o) throws Exception {
        this.a(n, b, b2, o, false);
    }
    
    public final void a(final int v, final boolean av, final boolean b, final Object o, final boolean b2) throws Exception {
        this.v = v;
        this.av = av;
        this.d = false;
        this.as = null;
        this.ai = null;
        this.aj = null;
        this.ap = null;
        this.ak = null;
        this.bi = false;
        this.af = 0;
        this.ax = true;
        this.br = 0L;
        this.b5 = 0L;
        this.c0 = 0;
        this.c1 = 0;
        if (this.cf != null) {
            this.cf.a(v, av, b, o);
        }
        if (!av) {
            this.l(o);
        }
        if (v == 4) {
            if (av) {
                this.e((int)(this.k * this.j * 4));
                this.ai = new int[(int)(this.k * this.j)];
                this.u = this.ai.length * 4;
            }
            this.q = this.k * this.j;
            this.c5 = 0L;
            this.c8 = false;
            this.c9 = (ji.util.i.c(92) && !ji.util.d.av(this.cd));
            this.t = 24;
            if (this.c9) {
                this.c7 = new int[(int)this.k][0];
            }
            if (this.e) {
                this.aq();
            }
        }
        else if (v == 3) {
            this.q = this.k * this.j;
            if (av) {
                this.aj = new byte[(int)this.q];
                this.u = this.aj.length;
            }
            this.aq();
            this.t = 8;
        }
        else if (v == 2) {
            this.q = (this.k + 1) * (1 + this.j / 2);
            this.aj = new byte[(int)this.q];
            this.u = this.aj.length;
            this.t = 4;
        }
        else if (v == 0) {
            this.ai = new int[(int)(this.k * this.x)];
            this.q = this.k * this.x;
            this.u = this.ai.length * 4;
            this.t = 24;
        }
        else {
            if (this.y != null) {
                this.y.removeAllElements();
                this.y = null;
            }
            if (this.z != null) {
                this.z.removeAllElements();
                this.z = null;
            }
            this.q = 0L;
            final int min = Math.min((int)this.k, 5000);
            this.y = new Vector(min, 100);
            if (ji.util.d.dy()) {
                this.aa = new Vector(min, 100);
                this.ab = new boolean[(int)this.k];
            }
            final long c6 = ji.util.d.c6();
            long j = this.j;
            long k = this.k;
            if (this.ay) {
                j /= this.az;
                k /= this.az;
            }
            if (!b2 && (j > c6 || k > c6 || b)) {
                if (b) {
                    this.l(o);
                    this.ag = ji.util.d.h(this.a7.n(), this.cd);
                }
                this.bi = true;
                this.k(o);
            }
            this.t = 1;
        }
        this.f();
    }
    
    private String d(final int n) {
        return ji.util.d.b(n, this.cd);
    }
    
    private final String ar() {
        return this.a7.n();
    }
    
    private void k(final Object o) throws Exception {
        this.au = 0L;
        this.bw = (int)(1 * (256 * (1 + this.j / 256)));
        this.bt = new byte[this.bw];
        this.bu = new int[(int)this.k];
        this.bv = new int[(int)this.k];
        this.l(o);
        this.a3 = this.ar();
        if (ji.io.ac.d(this.a3, this.cd)) {
            this.b(this.a1 = new ac(this.a3, false, false, 1048576, false, o, this.cd));
            this.ax = false;
        }
        else {
            this.a(this.a1 = new ac(this.a3, true, false, 10 * this.bw, false, o, this.cd));
            this.ao = 0;
            this.am = 1000 * this.bw;
            this.am = Math.min(this.am, 1048576);
            this.al = new byte[this.am];
        }
        this.br = this.a1.r();
    }
    
    public final int u() {
        return this.bw;
    }
    
    private final void e(final int n) throws Exception {
        if (!ji.util.d.dn() && n > ji.util.d.d0()) {
            throw new Exception(String.valueOf(String.valueOf(new StringBuffer(String.valueOf(String.valueOf(this.d(291)))).append(ji.res.ay.a()).append(".").append("\n").append(this.d(292)).append("\n").append(this.d(293)).append("\n").append(this.d(294)).append(ji.res.s.b(this.cd, true)).append("\n"))));
        }
    }
    
    public final void a(final String ah, final int n, final int n2, final int n3, final int t, final Object o, final dx ci) {
        if (this.cf != null) {
            if (this.a7 == null) {
                this.a7 = ji.io.q.a(o, this.cd);
            }
            this.cf.a(this.a7.n(), n, n2, n3, t, o, ci);
        }
        this.h(o);
        this.a9 = 0;
        if (ji.util.i.c(36)) {
            ji.io.h.d(this.cd, "DIB: open write synchronizing");
        }
        synchronized (ds.dj) {
            if (ji.util.i.c(36)) {
                ji.io.h.d(this.cd, "DIB: open write synchronized");
            }
            this.ah = ah;
            this.j = n;
            this.k = n2;
            this.n = n3;
            this.t = t;
            this.q = 0L;
            this.r = 0L;
            this.ci = ci;
            this.ay = ci.c();
            this.az = ci.d();
            this.at();
            if (t <= 1) {
                this.r = 1 + n / 8;
                this.r = this.r + 4 - this.r % 4;
                this.x = (int)(this.r / 4);
            }
            this.w = null;
            this.au();
            this.ac = true;
            this.ad = true;
            if (ji.util.i.c(36)) {
                ji.io.h.d(this.cd, "DIB: open write end synchronized");
            }
        }
        // monitorexit(ds.dj)
        this.f();
    }
    
    private final void l(final Object o) throws Exception {
        if (this.a7 == null) {
            this.a7 = ji.io.q.a(o, this.cd);
        }
    }
    
    public boolean v() {
        return this.bh;
    }
    
    public Image w() {
        return this.bj;
    }
    
    public void a(final ds cf) {
        this.cf = cf;
    }
    
    public final void a(int[] a, int length, final Object o, final int n, final int n2, final boolean b) throws Exception {
        try {
            if (!this.bh) {
                if (this.ck) {
                    this.cl = true;
                    for (int i = 0; i < length; ++i) {
                        if (a[i] == this.cm) {
                            a[i] = this.co;
                        }
                        else {
                            a[i] = this.cm;
                        }
                    }
                }
                if (this.a1 == null) {
                    this.au = 0L;
                    this.l(o);
                    this.a3 = this.a7.n();
                    this.a1 = new ac(this.a3, true, false, 0, false, o, this.cd);
                }
                if (this.ce != null) {
                    if (this.cf != null) {
                        this.cf.a(a, length, o, n, n2, b);
                    }
                    else if (!ji.util.i.c(59)) {
                        a = fk.a(this, this.ce, a, length, n - 1, n2 - 2, this.cb);
                        length = a.length;
                    }
                }
                if (ji.util.d.l() == 1 && !this.c4) {
                    this.b(a, length, o, n, n2, b);
                    if (!this.c8 && !this.as()) {
                        this.j(b);
                    }
                }
                else {
                    this.a(this.a1, a, length, b);
                }
                this.q += length;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        this.f();
    }
    
    private final boolean j(final boolean b) throws Exception {
        if (this.a1 != null && this.a1.b()) {
            if (ji.util.d.dr() && ji.util.e.av()) {
                ji.io.h.d(this.cd, "Free memory ".concat(String.valueOf(String.valueOf(ji.util.d.a(ji.util.d.j(), this.cd)))));
            }
            if (b) {
                this.a1.x();
            }
            final int min = Math.min(this.c7.length, this.c6);
            if (ji.util.d.dr()) {
                ji.io.h.d(this.cd, String.valueOf(String.valueOf(new StringBuffer("Switching to file based color image caching (at ").append(min).append(" lines of ").append(this.k).append(" and ").append(ji.util.d.a(this.c5, this.cd)).append(")"))));
            }
            for (int i = 0; i < min; ++i) {
                final int n = i * 3;
                final int n2 = (int)this.cx[1 + n];
                this.cy[n] = this.a1.r();
                this.a(this.a1, this.c7[i], n2, true);
                this.c7[i] = null;
            }
            this.cz = true;
            this.c5 = 0L;
            this.c8 = true;
            if (!ji.util.d.g() && ji.util.e.av()) {
                if (ji.util.d.dr()) {
                    ji.io.h.d(this.cd, "Forcing garbage collection...");
                }
                System.gc();
            }
            if (ji.util.d.dr() && ji.util.e.av()) {
                ji.io.h.d(this.cd, "Free memory now ".concat(String.valueOf(String.valueOf(ji.util.d.a(ji.util.d.j(), this.cd)))));
            }
            return true;
        }
        this.f();
        return false;
    }
    
    public final void a(final byte[] array, final int n, final Object o, final int n2, final int n3, final boolean b) throws Exception {
        this.a(array, n, o, n2, n3, 4, b);
    }
    
    public final void a(final byte[] array, final int n, final Object o, final int n2, final int n3, final int n4, final boolean b) throws Exception {
        if (!this.bh) {
            if (this.a1 == null) {
                this.au = 0L;
                this.a3 = this.a7.n();
                this.a1 = new ac(this.a3, true, false, 0, false, o, this.cd);
            }
            if (this.ce != null) {
                final int n5 = n / n4;
                final int[] array2 = new int[n5];
                if (this.cf != null) {
                    this.cf.a(array, n, o, n2, n3, n4, b);
                }
                else {
                    int n6 = 0;
                    if (n4 == 4) {
                        for (int i = 0; i < n5; ++i) {
                            array2[i] = ((array[n6++] & 0xFF) << 24 | (array[n6++] & 0xFF) << 16 | (array[n6++] & 0xFF) << 8 | (array[n6++] & 0xFF));
                        }
                    }
                    else if (n4 == 3) {
                        final int n7 = n3 - n2;
                        final int n8 = n5 / n7;
                        for (int j = 0; j < n7; ++j) {
                            final int n9 = j * n8;
                            for (int k = 0; k < n8; ++k) {
                                array2[n9 + k] = (0xFF000000 | (array[n6++] & 0xFF) << 16 | (array[n6++] & 0xFF) << 8 | (array[n6++] & 0xFF));
                            }
                            n6 = (n6 + 3 & 0xFFFFFFFC);
                        }
                    }
                    fk.a(this, this.ce, array2, n, n2 - 1, n3 - 2, this.cb);
                }
                if (ji.util.d.l() == 1 && !this.c4) {
                    this.b(array2, n5, o, n2, n3, b);
                    if (!this.c8 && !this.as()) {
                        this.j(b);
                    }
                }
                else {
                    this.a(this.a1, array2, n5, b);
                }
            }
            else if (ji.util.d.l() == 1 && !this.c4) {
                this.b(array, n, o, n2, n3, n4, b);
                if (!this.c8 && !this.as()) {
                    this.j(b);
                }
            }
            else if (n4 == 4) {
                this.a(this.a1, array, 0, n, b);
            }
            else if (n4 == 3) {
                final int n10 = n3 - n2;
                int n11 = 0;
                final int n12 = n / n10 / 3;
                final int[] array3 = new int[n12];
                for (int l = 0; l < n10; ++l) {
                    for (int n13 = 0; n13 < n12; ++n13) {
                        array3[n13] = (0xFF000000 | (array[n11++] & 0xFF) << 16 | (array[n11++] & 0xFF) << 8 | (array[n11++] & 0xFF));
                    }
                    n11 = (n11 + 3 & 0xFFFFFFFC);
                    this.a(this.a1, array3, array3.length, b);
                }
            }
            this.q += n / 4;
        }
        this.f();
    }
    
    private final boolean as() {
        if (ji.util.e.av()) {
            return this.c5 < ji.util.d.h() && ji.util.d.i() > 0;
        }
        return this.c5 < ji.util.d.h();
    }
    
    private final void b(final int[] array, final int n, final Object o, final int n2, final int n3, final boolean b) throws Exception {
        try {
            if (this.c7 == null && this.c9) {
                this.c7 = new int[(int)this.k][0];
            }
            final int n4 = (int)(n / this.j);
            if (this.cw == null) {
                this.cw = new int[(int)this.j * 2];
                this.cx = new long[(int)this.k * 3];
                this.cy = new long[(int)this.k * 3];
                this.cz = false;
            }
            else if (this.cw.length < this.j * 2) {
                this.cw = new int[(int)this.j * 2];
                this.cx = new long[(int)this.k * 3];
                this.cy = new long[(int)this.k * 3];
                this.cz = false;
            }
            final int length = this.cx.length;
            if (b) {
                this.a1.x();
            }
            for (int i = 0; i < n4; ++i) {
                final int a = this.a(array, (int)(i * this.j), (int)this.j, this.cw);
                final int c0 = this.c0;
                final int n5 = c0 * 3;
                if (n5 < length) {
                    if (a < this.j) {
                        this.cx[2 + n5] = 1L;
                        this.cx[1 + n5] = a;
                        if (!this.c8 && this.c9) {
                            this.c7[c0] = new int[a];
                            System.arraycopy(this.cw, 0, this.c7[c0], 0, a);
                            ++this.c6;
                            this.c5 += a * 4;
                            this.cz = false;
                        }
                        else {
                            this.cy[n5] = this.a1.r();
                            this.cz = true;
                            this.a1.b(this.cw, 0, a);
                        }
                    }
                    else {
                        this.cx[2 + n5] = 0L;
                        this.cx[1 + n5] = this.j;
                        if (!this.c8 && this.c9) {
                            this.c7[c0] = new int[(int)this.j];
                            System.arraycopy(array, (int)(i * this.j), this.c7[c0], 0, (int)this.j);
                            ++this.c6;
                            this.c5 += this.j * 4;
                            this.cz = false;
                        }
                        else {
                            this.cy[n5] = this.a1.r();
                            this.cz = true;
                            this.a1.b(array, (int)(i * this.j), (int)this.j);
                        }
                    }
                }
                ++this.c0;
            }
            this.f();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private final void b(final byte[] array, final int n, final Object o, final int n2, final int n3, final int n4, final boolean b) throws Exception {
        try {
            if (this.c7 == null && this.c9) {
                this.c7 = new int[(int)this.k][0];
            }
            int n5;
            if (n4 == 4) {
                n5 = (int)(n / this.j) / n4;
            }
            else {
                n5 = n3 - n2;
            }
            if (this.cw == null) {
                this.cw = new int[(int)this.j * 2];
                this.cx = new long[(int)this.k * 3];
                this.cy = new long[(int)this.k * 3];
                this.cz = false;
            }
            else if (this.cw.length < this.j * 2) {
                this.cw = new int[(int)this.j * 2];
                this.cx = new long[(int)this.k * 3];
                this.cy = new long[(int)this.k * 3];
                this.cz = false;
            }
            if (this.cx == null) {
                this.cx = new long[(int)this.k * 3];
                this.cy = new long[(int)this.k * 3];
                this.cz = false;
            }
            final int length = this.cx.length;
            long n6 = this.j * n4;
            if (n4 == 3) {
                n6 = (n6 + 3 & 0xFFFFFFFC);
            }
            if (b) {
                this.a1.x();
            }
            for (int i = 0; i < n5; ++i) {
                final int a = this.a(array, (int)(i * n6), (int)n6, this.cw, n4);
                final int c0 = this.c0;
                final int n7 = c0 * 3;
                if (n7 < length) {
                    if (a < this.j) {
                        this.cx[2 + n7] = 1L;
                        this.cx[1 + n7] = a;
                        if (!this.c8 && this.c9) {
                            this.c7[c0] = new int[a];
                            System.arraycopy(this.cw, 0, this.c7[c0], 0, a);
                            ++this.c6;
                            this.c5 += a * n4;
                            this.cz = false;
                        }
                        else {
                            this.cy[n7] = this.a1.r();
                            this.cz = true;
                            this.a1.b(this.cw, 0, a);
                        }
                    }
                    else {
                        this.cx[2 + n7] = 0L;
                        this.cx[1 + n7] = this.j;
                        final int n8 = (int)(i * n6);
                        if (!this.c8 && this.c9) {
                            if (n4 == 4) {
                                for (int n9 = 0; n9 < this.j; ++n9) {
                                    final int n10 = n8 + n9 * 4;
                                    this.cw[n9] = ((array[n10] & 0xFF) << 24 | (array[n10 + 1] & 0xFF) << 16 | (array[n10 + 2] & 0xFF) << 8 | (array[n10 + 3] & 0xFF));
                                }
                            }
                            else {
                                for (int n11 = 0; n11 < this.j; ++n11) {
                                    int n12 = n8 + n11 * 3;
                                    n12 += 3;
                                    this.cw[n11] = (0xFF000000 | (array[n12 + 0] & 0xFF) << 16 | (array[n12 + 1] & 0xFF) << 8 | (array[n12 + 2] & 0xFF));
                                }
                            }
                            this.c7[c0] = new int[(int)this.j];
                            System.arraycopy(this.cw, 0, this.c7[c0], 0, (int)this.j);
                            ++this.c6;
                            this.c5 += n6;
                            this.cz = false;
                        }
                        else {
                            this.cy[n7] = this.a1.r();
                            this.cz = true;
                            this.a(this.a1, array, n8, (int)n6, b);
                        }
                    }
                }
                ++this.c0;
            }
            this.f();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public final void e(final boolean b) {
        this.c4 = !b;
        this.at();
    }
    
    private final int a(final int[] array, final int n, final int n2, final int[] array2) {
        int n3 = array[n];
        int n4 = 1;
        int n5 = 0;
        final boolean b = this.e && this.cp;
        if (b) {
            this.a((long)(n3 & -1));
        }
        for (int i = 1; i < n2; ++i) {
            if (n3 == array[n + i]) {
                ++n4;
            }
            else {
                if (b) {
                    this.a((long)(n3 & -1));
                }
                array2[n5++] = n3;
                array2[n5++] = n4;
                n3 = array[n + i];
                n4 = 1;
            }
            if (n5 > this.c3) {
                n5 = (int)this.j;
                n4 = 0;
                break;
            }
        }
        if (n4 > 0) {
            array2[n5++] = n3;
            array2[n5++] = n4;
        }
        this.f();
        return n5;
    }
    
    private final int a(final byte[] array, final int n, final int n2, final int[] array2, final int n3) {
        int n4;
        if (n3 == 4) {
            n4 = ((array[n] & 0xFF) << 24 | (array[n + 1] & 0xFF) << 16 | (array[n + 2] & 0xFF) << 8 | (array[n + 3] & 0xFF));
        }
        else {
            n4 = (0xFF000000 | (array[n + 0] & 0xFF) << 16 | (array[n + 1] & 0xFF) << 8 | (array[n + 2] & 0xFF));
        }
        int n5 = 1;
        int n6 = 0;
        final boolean b = this.e && this.cp;
        if (b) {
            this.a((long)(n4 & -1));
        }
        for (int i = n3; i < n2; i += n3) {
            final int n7 = n + i;
            int n8;
            if (n3 == 4) {
                n8 = ((array[n7] & 0xFF) << 24 | (array[n7 + 1] & 0xFF) << 16 | (array[n7 + 2] & 0xFF) << 8 | (array[n7 + 3] & 0xFF));
            }
            else {
                n8 = (0xFF000000 | (array[n7 + 0] & 0xFF) << 16 | (array[n7 + 1] & 0xFF) << 8 | (array[n7 + 2] & 0xFF));
            }
            if (n4 == n8) {
                ++n5;
            }
            else {
                if (b) {
                    this.a((long)(n4 & -1));
                }
                array2[n6++] = n4;
                array2[n6++] = n5;
                n4 = n8;
                n5 = 1;
            }
            if (n6 > this.c3) {
                n6 = (int)this.j;
                n5 = 0;
                break;
            }
        }
        if (n5 > 0) {
            array2[n6++] = n4;
            array2[n6++] = n5;
        }
        this.f();
        return n6;
    }
    
    private final void at() {
        this.c3 = 2 * this.j;
    }
    
    private final void c(final byte[] array, final int n, final int n2) {
        try {
            if (this.c && this.cp && this.cq < this.cs) {
                for (int i = 0; i < n2; ++i) {
                    final int n3 = array[i + n] & 0xFF;
                    boolean b = false;
                    for (int j = 0; j < this.cr; ++j) {
                        if (this.ct[j] == n3) {
                            b = true;
                            final long[] cu = this.cu;
                            final int n4 = j;
                            ++cu[n4];
                            break;
                        }
                    }
                    if (!b) {
                        this.ct[this.cq++] = n3;
                        this.cu[this.cq - 1] = 1L;
                        if (this.cq > this.cr) {
                            break;
                        }
                    }
                }
            }
        }
        catch (Exception ex) {}
    }
    
    private final void a(final long n) {
        try {
            if (this.cq < this.cs) {
                boolean b = false;
                for (int i = 0; i < this.cr; ++i) {
                    if (this.cv[i] == n) {
                        b = true;
                        break;
                    }
                }
                if (!b) {
                    this.cv[this.cq++] = n;
                }
            }
        }
        catch (Exception ex) {}
    }
    
    private final void a(final ac ac, final byte[] array, final int n, final int n2, final boolean b) throws Exception {
        if (this.c && this.cp) {
            this.c(array, n, n2);
        }
        if (b) {
            ac.x();
        }
        if (ji.util.d.em()) {
            ac.b(array, n, n2);
        }
        else {
            for (int i = n2, n3 = Math.min(this.at, i), n4 = 0; i > 0; i -= n3, n4 += n3, n3 = Math.min(n3, i)) {
                ac.b(array, n + n4, n3);
            }
        }
        this.f();
    }
    
    public final void b(final byte[] array, final int n, final Object o, final int n2, final int n3, final boolean b) throws Exception {
        this.c4 = true;
        if (!this.bh) {
            if (this.ck) {
                this.cl = true;
                for (int i = 0; i < n; ++i) {
                    if (array[i] == this.cm) {
                        array[i] = this.co;
                    }
                    else {
                        array[i] = this.cm;
                    }
                }
            }
            if (this.a1 == null) {
                this.au = 0L;
                if (this.a7 == null) {
                    this.l(o);
                }
                this.a3 = this.a7.n();
                this.a1 = new ac(this.a3, true, false, 0, false, o, this.cd);
                this.q = 0L;
            }
            if (this.v == 3) {
                if (this.ce != null) {
                    if (this.cf != null) {
                        this.cf.a(this.a1, array, 0, n, b);
                        this.a(this.a1, array, 0, n, b);
                    }
                    else {
                        this.a(this.a1, fk.a(this, this.ce, array, n, n2, n3, n2, this.cb), 0, n, b);
                    }
                }
                else {
                    this.a(this.a1, array, 0, n, b);
                }
            }
            else {
                this.a(this.a1, array, 0, n, b);
            }
            this.q += n;
        }
        this.f();
    }
    
    public final void a(final int n, final byte[] array) throws Exception {
        if (this.a1 != null) {
            this.a1.a(n * this.j + this.au);
            this.a1.a(array, 0, (int)this.j);
        }
        this.f();
    }
    
    public final void b(final int n, final byte[] array) throws Exception {
        if (this.a1 != null) {
            final long ba = this.bv[n] + this.br;
            final int n2 = (int)(ba - this.ba) + this.bu[n];
            if (this.bf == null) {
                this.bf = new byte[this.b9];
            }
            if (ba > this.bd || ba < this.ba || this.bd < 0 || n2 > this.bf.length) {
                if (this.dh) {
                    final long max = Math.max(ba - this.b9, 0L);
                    this.a1.a(max);
                    this.ba = max;
                }
                else {
                    this.a1.a(ba);
                    this.ba = ba;
                }
                this.a1.a(this.bf, 0, this.b9);
                this.bd = this.a1.r();
            }
            int n3 = 0;
            final int n4 = (int)(ba - this.ba);
            final int n5 = n4 + this.bu[n];
            if (this.dh) {
                if (this.bu[n] % 2 > 0) {
                    for (int i = n5 - 1; i >= n4; --i) {
                        array[n3++] = this.bf[i];
                    }
                }
                else {
                    array[n3++] = 0;
                    for (int j = n5 - 1; j >= n4; --j) {
                        array[n3++] = this.bf[j];
                    }
                }
            }
            else {
                for (int k = n4; k < n5; ++k) {
                    array[n3++] = this.bf[k];
                }
            }
        }
        this.f();
    }
    
    public final boolean x() {
        return this.ce != null;
    }
    
    public final void a(final int[] array, final int n, final int n2, final boolean b, final boolean b2) throws Exception {
        if (this.ay) {
            for (int i = 0; i < n; ++i) {
                array[i] *= this.az;
            }
        }
        this.b(array, n, n2, b, b2);
    }
    
    public final void b(int[] array, int n, final int n2, final boolean b, final boolean b2) throws Exception {
        if (this.ce != null && this.cf != null) {
            this.cf.a(array, n, n2, b, b2);
        }
        try {
            if (this.ce != null && b) {
                final int length = array.length;
                final short[] array2 = new short[length];
                for (int i = 0; i < length; ++i) {
                    array2[i] = (short)(array[i] & 0xFFFF);
                }
                final short[] a = fk.a(this, null, array2, n, this.y.size(), this.cb);
                if (a != null) {
                    final int length2 = a.length;
                    array = new int[length2];
                    for (int j = 0; j < length2; ++j) {
                        array[j] = (a[j] & 0xFFFF);
                    }
                    n = length2;
                }
            }
            if (this.bw + this.ao > this.am) {
                this.k(b2);
            }
            this.bo = 0;
            this.bn = 0;
            while (this.bn < n) {
                if (array[this.bn] > 255) {
                    this.bp = array[this.bn];
                    this.bq = this.bm;
                    while (this.bp > 0) {
                        this.al[this.ao++] = (byte)(this.bq & 0xFF);
                        ++this.bo;
                        this.bp -= 255;
                        if (this.bp > 0) {
                            this.al[this.ao++] = 0;
                            ++this.bo;
                        }
                        if (this.bp < 255) {
                            this.bq = (byte)(this.bp & 0xFF);
                        }
                    }
                }
                else {
                    this.al[this.ao++] = (byte)(array[this.bn] & 0xFF);
                    ++this.bo;
                }
                ++this.bn;
            }
            this.bu[n2] = this.bo;
            this.bv[n2] = (int)this.q;
            if (this.bo > this.ca) {
                this.ca = this.bo;
            }
            this.q += this.bo;
        }
        catch (Exception ex) {}
        this.f();
    }
    
    public final short[] a(final short[] array, final int n, final int n2) throws Exception {
        this.f();
        try {
            if (this.ce == null) {
                return array;
            }
            final short[] array2 = new short[n];
            System.arraycopy(array, 0, array2, 0, n);
            final short[] a = fk.a(this, this.ce, array2, n, n2, this.cb);
            if (a != null) {
                return a;
            }
            return array;
        }
        catch (Exception ex) {
            return array;
        }
    }
    
    public final void b(final short[] array, final int n, final int n2) throws Exception {
        this.bo = 0;
        this.bn = 0;
        while (this.bn < n) {
            if (array[this.bn] > 255) {
                this.bp = array[this.bn];
                this.bq = this.bm;
                while (this.bp > 0) {
                    this.bt[this.bo++] = (byte)(this.bq & 0xFF);
                    this.bp -= 255;
                    if (this.bp > 0) {
                        this.bt[this.bo++] = 0;
                    }
                    if (this.bp < 255) {
                        this.bq = (byte)(this.bp & 0xFF);
                    }
                }
            }
            else {
                this.bt[this.bo++] = (byte)(array[this.bn] & 0xFF);
            }
            ++this.bn;
        }
        this.b3.b(this.bt, 0, this.bo);
        this.b0[n2] = this.bo;
        this.b1[n2] = (int)this.b4;
        this.b4 += this.bo;
        this.f();
    }
    
    public final void a(final int[] array, final int n, final int n2) throws Exception {
        this.bo = 0;
        this.bn = 0;
        while (this.bn < n) {
            if (array[this.bn] > 255) {
                this.bp = array[this.bn];
                this.bq = this.bm;
                while (this.bp > 0) {
                    this.bt[this.bo++] = (byte)(this.bq & 0xFF);
                    this.bp -= 255;
                    if (this.bp > 0) {
                        this.bt[this.bo++] = 0;
                    }
                    if (this.bp < 255) {
                        this.bq = (byte)(this.bp & 0xFF);
                    }
                }
            }
            else {
                this.bt[this.bo++] = (byte)(array[this.bn] & 0xFF);
            }
            ++this.bn;
        }
        this.b3.b(this.bt, 0, this.bo);
        this.b0[n2] = this.bo;
        this.b1[n2] = (int)this.b4;
        this.b4 += this.bo;
        this.f();
    }
    
    private final void k(final boolean b) throws Exception {
        if (this.ao > 0 && this.al != null) {
            if (b) {
                this.a1.x();
            }
            this.a1.b(this.al, 0, this.ao);
            this.ao = 0;
        }
        this.f();
    }
    
    public boolean y() {
        return this.an;
    }
    
    public final void f(final boolean db) {
        this.db = db;
    }
    
    public final void g(final boolean e) {
        this.e = e;
    }
    
    public final void a(final Object o, final dx dx, final boolean b) {
        this.a(o, dx, b, 4);
    }
    
    public final void a(final Object o, final dx dx, final boolean b, final int n) {
        try {
            this.cz = false;
            if (this.ce != null) {
                this.a(this.dg.a(), this.dg.a(), b);
            }
            if (this.e && this.v != 3 && this.cp) {
                boolean b2 = true;
                if (this.cq == 2 && ji.util.d.l() == 1 && !this.c4 && this.a1 != null) {
                    for (int i = 0; i < this.cq; ++i) {
                        if ((this.cv[i] & 0xFFFFFF) != 0x0 && (this.cv[i] & 0xFFFFFF) != 0xFFFFFF) {
                            b2 = false;
                        }
                    }
                }
                if (b2 && this.cq == 2 && ji.util.d.l() == 1 && !this.c4 && this.a1 != null) {
                    this.co = -1;
                    this.cn = 0;
                    if (ji.util.i.c(5)) {
                        ji.io.h.d(this.cd, "DIB: Converting colors...");
                    }
                    try {
                        this.e(o);
                        final int n2 = (int)this.j;
                        final int n3 = n2 * n;
                        final byte[] array = new byte[n3];
                        final byte[] array2 = new byte[n2];
                        final byte[] array3 = new byte[n2];
                        this.a(1, false, false, o, true);
                        final long c6 = ji.util.d.c6();
                        long j = this.j;
                        long k = this.k;
                        if (this.ay) {
                            j /= this.az;
                            k /= this.az;
                        }
                        if (j > c6 || k > c6) {
                            this.bi = true;
                        }
                        if (this.bi) {
                            if (ji.util.i.c(5)) {
                                ji.io.h.d(this.cd, "DIB: Converting colors using HighMem...");
                            }
                            this.bw = (int)(1 * (256 * (1 + this.j / 256)));
                            this.ao = 0;
                            this.am = 1000 * this.bw;
                            this.am = Math.min(this.am, 1048576);
                            this.al = new byte[this.am];
                            this.bu = new int[(int)this.k];
                            this.bv = new int[(int)this.k];
                            this.q = 0L;
                            this.u = 0L;
                            this.ca = 0;
                            final String n4 = this.a7.n();
                            final ac a1 = new ac(n4, true, false, 0, false, o, this.cd);
                            this.a(a1);
                            this.br = a1.r();
                            for (int n5 = 0; n5 < this.k; ++n5) {
                                this.c(n5);
                                this.a(array, n2, n);
                                int n6 = 0;
                                for (int l = 0; l < n3; l += n) {
                                    if (((array[l + 1] & 0xFF) | (array[l + 2] & 0xFF) | (array[l + 3] & 0xFF)) != 0xFF) {
                                        array3[n6] = 1;
                                    }
                                    ++n6;
                                }
                                final short[] a2 = ji.util.d.a(array3, this.db);
                                this.bo = 0;
                                this.ao = 0;
                                this.bn = 0;
                                while (this.bn < a2.length) {
                                    if (a2[this.bn] > 255) {
                                        this.bp = a2[this.bn];
                                        this.bq = this.bm;
                                        while (this.bp > 0) {
                                            this.al[this.ao++] = (byte)(this.bq & 0xFF);
                                            ++this.bo;
                                            this.bp -= 255;
                                            if (this.bp > 0) {
                                                this.al[this.ao++] = 0;
                                                ++this.bo;
                                            }
                                            if (this.bp < 255) {
                                                this.bq = (byte)(this.bp & 0xFF);
                                            }
                                        }
                                    }
                                    else {
                                        this.al[this.ao++] = (byte)(a2[this.bn] & 0xFF);
                                        ++this.bo;
                                    }
                                    ++this.bn;
                                }
                                this.bu[n5] = this.bo;
                                this.bv[n5] = (int)this.q;
                                if (this.bo > this.ca) {
                                    this.ca = this.bo;
                                }
                                a1.b(this.al, 0, this.ao);
                                this.q += this.bo;
                                System.arraycopy(array2, 0, array3, 0, n2);
                            }
                            if (this.a1 != null) {
                                try {
                                    this.a1.a(o);
                                    ji.io.ac.c(this.a3, this.cd);
                                }
                                catch (Exception ex4) {}
                            }
                            this.a3 = n4;
                            this.a1 = a1;
                            this.bt = new byte[this.bw];
                            this.al = null;
                        }
                        else {
                            this.q = 0L;
                            this.u = 0L;
                            for (int n7 = 0; n7 < this.k; ++n7) {
                                this.c(n7);
                                this.a(array, n2, n);
                                int n8 = 0;
                                for (int n9 = 0; n9 < n3; n9 += n) {
                                    if (((array[n9 + 1] & 0xFF) | (array[n9 + 2] & 0xFF) | (array[n9 + 3] & 0xFF)) != 0xFF) {
                                        array3[n8] = 1;
                                    }
                                    ++n8;
                                }
                                final short[] a3 = ji.util.d.a(array3, this.db);
                                this.y.addElement(a3);
                                this.u += a3.length * 2;
                                this.q += a3.length;
                                System.arraycopy(array2, 0, array3, 0, n2);
                            }
                        }
                        if (!this.bi) {
                            try {
                                if (this.a1 != null) {
                                    this.a1.a(o);
                                }
                                if (this.a3 != null) {
                                    ji.io.ac.c(this.a3, this.cd);
                                }
                            }
                            catch (Exception ex5) {}
                            this.a1 = null;
                        }
                        this.an = true;
                        this.a = true;
                        if (ji.util.i.c(5)) {
                            ji.io.h.d(this.cd, "DIB: Converted...");
                        }
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
            else if (this.c && this.cp) {
                boolean b3 = false;
                if (this.a1 != null) {
                    final int n10 = (int)this.j;
                    final byte[] array4 = new byte[n10];
                    try {
                        this.e(o);
                        for (int n11 = 0; n11 < this.k; ++n11) {
                            this.a(n11, array4);
                            this.c(array4, 0, n10);
                            if (this.cq >= 2) {
                                break;
                            }
                        }
                        if (this.cq == 2) {
                            b3 = true;
                        }
                    }
                    catch (Exception ex2) {
                        ex2.printStackTrace();
                    }
                    try {
                        this.a1.a(0L);
                    }
                    catch (Exception ex6) {}
                }
                else {
                    this.c(this.aj, 0, this.aj.length);
                    if (this.cq == 2) {
                        b3 = true;
                    }
                }
                if (b3) {
                    if (this.cq == 2) {
                        int n12 = -1;
                        Label_1519: {
                            try {
                                Block_92: {
                                    for (int n13 = 0; n13 < this.cq; ++n13) {
                                        final int n14 = this.as[this.ct[n13]];
                                        final int n15 = (n14 & 0xFF0000) >> 16;
                                        final int n16 = (n14 & 0xFF00) >> 8;
                                        final int n17 = n14 & 0xFF;
                                        if (n15 == 255 && n16 == 255 && n17 == 255) {
                                            break Block_92;
                                        }
                                    }
                                    break Label_1519;
                                }
                                int n13 = 0;
                                n12 = (byte)n13;
                            }
                            catch (Exception ex7) {}
                        }
                        boolean b4 = false;
                        if (n12 < 0) {
                            int n18 = -1;
                            long n19 = 0L;
                            for (int n20 = 0; n20 < this.cq; ++n20) {
                                if (this.cu[n20] > n19) {
                                    n19 = this.cu[n20];
                                    n18 = 0;
                                }
                            }
                            if (n18 >= 0) {
                                if (n18 != 0) {
                                    b4 = true;
                                }
                            }
                            else {
                                for (int n21 = 0; n21 < this.cq; ++n21) {
                                    final int n22 = this.ct[n21];
                                    final int n23 = (n22 & 0xFF0000) >> 16;
                                    final int n24 = (n22 & 0xFF00) >> 8;
                                    final int n25 = n22 & 0xFF;
                                    if (n23 == 0 && n24 == 0 && n25 == 0) {
                                        b4 = true;
                                        break;
                                    }
                                }
                            }
                        }
                        if (this.p != 0) {
                            b4 = true;
                        }
                        if (this.as != null) {
                            for (int n26 = 0; n26 < this.as.length; ++n26) {
                                this.as[n26] = -16777216 + (n26 << 16) + (n26 << 8) + n26;
                            }
                        }
                        else if (ji.util.i.c(85)) {
                            ji.io.h.d(this.cd, "fillDibCompleted: No palette");
                        }
                        this.co = -1;
                        this.cn = 0;
                        if (this.a1 != null) {
                            try {
                                this.a1.a(0L);
                                final String n27 = this.a7.n();
                                final ac ac = new ac(n27, true, false, 0, false, o, this.cd);
                                final int n28 = (int)this.j;
                                final byte[] array5 = new byte[n28];
                                for (int n29 = 0; n29 < this.k; ++n29) {
                                    this.a(n29, array5);
                                    if (this.f) {
                                        for (int n30 = 0; n30 < n28; ++n30) {
                                            if (array5[n30] == this.cm) {
                                                array5[n30] = this.cn;
                                            }
                                            else {
                                                array5[n30] = this.co;
                                            }
                                        }
                                    }
                                    else {
                                        for (int n31 = 0; n31 < n28; ++n31) {
                                            if (array5[n31] == this.cm) {
                                                array5[n31] = this.co;
                                            }
                                            else {
                                                array5[n31] = this.cm;
                                            }
                                        }
                                    }
                                    if (b4) {
                                        try {
                                            for (int n32 = 0; n32 < n28; ++n32) {
                                                array5[n32] = (byte)(255 - (array5[n32] & 0xFF));
                                            }
                                        }
                                        catch (Exception ex8) {}
                                    }
                                    ac.b(array5);
                                }
                                ac.a(o);
                                this.a1.a(o);
                                ji.io.ac.c(this.a3, this.cd);
                                ji.io.ac.a(n27, this.a3, this.cd);
                                this.a1 = new ac(this.a3, false, false, 0, false, o, this.cd);
                                this.m(o);
                            }
                            catch (Exception ex3) {
                                ex3.printStackTrace();
                            }
                            try {
                                this.a1.a(0L);
                            }
                            catch (Exception ex9) {}
                            this.a = true;
                        }
                        else {
                            final int length = this.aj.length;
                            this.co = -1;
                            for (int n33 = 0; n33 < length; ++n33) {
                                if (this.aj[n33] == this.cm) {
                                    this.aj[n33] = this.co;
                                }
                                else {
                                    this.aj[n33] = this.cm;
                                }
                            }
                            if (!b4) {
                                this.a = true;
                                for (int n34 = 0; n34 < length; ++n34) {
                                    this.aj[n34] = (byte)(255 - (this.aj[n34] & 0xFF));
                                }
                            }
                        }
                        this.a = true;
                    }
                    if (dx != null) {
                        this.a = true;
                        dx.l = String.valueOf(String.valueOf(dx.l)).concat(String.valueOf(String.valueOf(String.valueOf(String.valueOf(new StringBuffer(" (").append(ji.res.s.a(1195, this.cd)).append(")"))))));
                    }
                }
            }
            else if (!this.bh && this.ck) {
                if (this.a1 == null && !this.cl) {
                    for (int length2 = this.aj.length, n35 = 0; n35 < length2; ++n35) {
                        if (this.aj[n35] == this.cm) {
                            this.aj[n35] = this.co;
                        }
                        else {
                            this.aj[n35] = this.cm;
                        }
                    }
                }
                this.a = true;
            }
        }
        finally {
            this.bg = null;
            this.a8 = false;
            this.f();
        }
    }
    
    public boolean z() {
        return this.a;
    }
    
    public final void e(final Object o) throws Exception {
        if (!this.bh && this.a1 != null) {
            this.k(true);
            this.m(o);
        }
        this.f();
    }
    
    public final boolean aa() {
        return this.a1 == null || this.a1.b();
    }
    
    private final void m(final Object o) throws Exception {
        this.a(o, this.a3, this.cx, false);
    }
    
    private final void a(final Object o, final String s, final long[] array, final boolean b) throws Exception {
        if (!this.bh) {
            if (this.a1 != null && !b) {
                if (this.c6 > 0 && ji.util.d.dr()) {
                    ji.io.h.d(this.cd, String.valueOf(String.valueOf(new StringBuffer("").append(ji.util.d.a(this.c5, this.cd)).append(" RAM used for color image."))));
                    ji.io.h.d(this.cd, String.valueOf(String.valueOf(new StringBuffer("").append(ji.util.d.a(this.a1.w(), this.cd)).append(" cached to file."))));
                }
                this.a1.a(o);
                this.ba = -1L;
                this.bd = -1L;
                this.be = null;
                this.bf = null;
                this.am = 0;
                this.al = null;
                if (this.ca == 0) {
                    this.b9 = 204800;
                }
                else {
                    this.b9 = this.ca * 500;
                    this.b9 = Math.min(this.b9, 307200);
                }
                if (this.bi) {
                    this.a1 = new ac(s, false, false, 1048576, false, o, this.cd);
                }
                else {
                    this.a1 = new ac(s, false, false, 0, false, o, this.cd);
                }
                if (array != null && !this.c4) {
                    int n = 0;
                    for (int i = 0; i < array.length; i += 3) {
                        array[i] = n;
                        n += (int)(array[i + 1] * 4);
                    }
                }
                this.c0 = 0;
                this.c1 = 0;
            }
            else if (!ji.util.d.by(s) && b) {
                this.ba = -1L;
                this.bd = -1L;
                this.be = null;
                this.bf = null;
                this.am = 0;
                this.al = null;
                if (this.ca == 0) {
                    this.b9 = 204800;
                }
                else {
                    this.b9 = this.ca * 500;
                    this.b9 = Math.min(this.b9, 307200);
                }
                this.a1 = new ac(s, false, false, 0, false, o, this.cd);
            }
        }
        this.f();
    }
    
    public final boolean a(final int[] array, final int n) throws Exception {
        boolean b = false;
        if (ji.util.d.l() == 1 && !this.c4) {
            if (this.cw == null) {
                this.cw = new int[(int)this.j * 2];
            }
            final int n2 = (int)this.cx[1 + this.c1 * 3];
            final int n3 = (int)this.cx[2 + this.c1 * 3];
            if (this.c8 || !this.c9) {
                if (this.cz) {
                    this.a1.a(this.cy[this.c1 * 3]);
                }
                else {
                    this.a1.a(this.cx[this.c1 * 3]);
                }
            }
            if (n3 == 0) {
                if (!this.c8 && this.c9) {
                    if (this.c7[this.c1] != null && this.c7[this.c1].length > 0) {
                        System.arraycopy(this.c7[this.c1], 0, array, 0, (int)this.j);
                        b = true;
                    }
                }
                else if (this.a(this.a1, array, (int)this.j) > 0) {
                    b = true;
                }
            }
            else if (n3 == 1) {
                if (!this.c8 && this.c9) {
                    if (this.c7[this.c1] != null && this.c7[this.c1].length > 0) {
                        System.arraycopy(this.c7[this.c1], 0, this.cw, 0, n2);
                        b = true;
                    }
                }
                else if (this.a(this.a1, this.cw, n2) > 0) {
                    b = true;
                }
                int n4 = 0;
                for (int i = 0; i < n2; i += 2) {
                    final int n5 = this.cw[i];
                    for (int n6 = this.cw[i + 1], j = 0; j < n6; ++j) {
                        array[n4++] = n5;
                    }
                }
            }
            else if (n3 == 2) {
                if (this.c2 == null) {
                    this.c2 = new byte[n2];
                }
                else if (this.c2.length < n2) {
                    this.c2 = new byte[n2];
                }
                if (this.a1.a(this.c2) > 0) {
                    b = true;
                    this.a(ji.zip.a4.a(this.c2, null), array);
                }
            }
            if (this.dh && b) {
                final int[] array2 = array.clone();
                for (int k = 0; k < n; ++k) {
                    array[k] = array2[n - k - 1];
                }
            }
        }
        else if (this.b(array, n) > 0) {}
        this.f();
        return true;
    }
    
    public final void a(final byte[] array, final int n, final int n2) throws Exception {
        if (ji.util.d.l() == 1 && !this.c4) {
            if (this.cw == null) {
                this.cw = new int[(int)this.j * 2];
            }
            final int n3 = (int)this.cx[1 + this.c1 * 3];
            final int n4 = (int)this.cx[2 + this.c1 * 3];
            if (this.c8 || !this.c9) {
                this.a1.a(this.cx[this.c1 * 3]);
            }
            if (n4 == 0) {
                if (!this.c8 && this.c9) {
                    System.arraycopy(this.c7[this.c1], 0, this.cw, 0, (int)this.j);
                    int n5 = 0;
                    if (n2 == 4) {
                        for (int i = 0; i < n3; ++i) {
                            final int n6 = this.cw[i];
                            final byte b = (byte)((n6 & 0xFF0000) >> 16);
                            final byte b2 = (byte)((n6 & 0xFF00) >> 8);
                            final byte b3 = (byte)(n6 & 0xFF);
                            ++n5;
                            array[n5++] = b;
                            array[n5++] = b2;
                            array[n5++] = b3;
                        }
                    }
                    else {
                        for (int j = 0; j < n3; ++j) {
                            final int n7 = this.cw[j];
                            final byte b4 = (byte)((n7 & 0xFF0000) >> 16);
                            final byte b5 = (byte)((n7 & 0xFF00) >> 8);
                            final byte b6 = (byte)(n7 & 0xFF);
                            array[n5++] = b4;
                            array[n5++] = b5;
                            array[n5++] = b6;
                        }
                    }
                }
                else {
                    this.a1.a(array, 0, n * n2);
                }
            }
            else if (n4 == 1) {
                if (!this.c8 && this.c9) {
                    System.arraycopy(this.c7[this.c1], 0, this.cw, 0, n3);
                }
                else {
                    this.a(this.a1, this.cw, n3);
                }
                int n8 = 0;
                if (n2 == 4) {
                    for (int k = 0; k < n3; k += 2) {
                        final int n9 = this.cw[k];
                        final int n10 = this.cw[k + 1];
                        final byte b7 = (byte)((n9 & 0xFF0000) >> 16);
                        final byte b8 = (byte)((n9 & 0xFF00) >> 8);
                        final byte b9 = (byte)(n9 & 0xFF);
                        for (int l = 0; l < n10; ++l) {
                            ++n8;
                            array[n8++] = b7;
                            array[n8++] = b8;
                            array[n8++] = b9;
                        }
                    }
                }
                else {
                    for (int n11 = 0; n11 < n3; n11 += 2) {
                        final int n12 = this.cw[n11];
                        final int n13 = this.cw[n11 + 1];
                        final byte b10 = (byte)((n12 & 0xFF0000) >> 16);
                        final byte b11 = (byte)((n12 & 0xFF00) >> 8);
                        final byte b12 = (byte)(n12 & 0xFF);
                        for (int n14 = 0; n14 < n13; ++n14) {
                            array[n8++] = b10;
                            array[n8++] = b11;
                            array[n8++] = b12;
                        }
                    }
                }
            }
            else if (n4 == 2) {
                ji.io.h.d(this.cd, "Block read as bytes not supported for zipped 24-bit DIB");
            }
            if (this.dh) {
                if (n2 == 4) {
                    final byte[] array2 = array.clone();
                    for (int n15 = n * 4, n16 = 0; n16 < n15; n16 += 4) {
                        array[n16] = array2[n15 - n16 - 4];
                        array[n16 + 1] = array2[n15 - n16 - 3];
                        array[n16 + 2] = array2[n15 - n16 - 2];
                        array[n16 + 3] = array2[n15 - n16 - 1];
                    }
                }
                else {
                    final byte[] array3 = array.clone();
                    for (int n17 = n * 3, n18 = 0; n18 < n17; n18 += 3) {
                        array[n18 + 1] = array3[n17 - n18 - 3];
                        array[n18 + 2] = array3[n17 - n18 - 2];
                        array[n18 + 3] = array3[n17 - n18 - 1];
                    }
                }
            }
        }
        else {
            ji.io.h.d(this.cd, "Block read as bytes not supported for uncompressed 24-bit DIB");
        }
        this.f();
    }
    
    private final int b(final int[] array, final int n) throws Exception {
        int n2 = 0;
        if (this.a1 != null) {
            this.bc = Math.min(this.bb, (int)this.a1.w());
            if (this.be == null) {
                this.be = new int[this.bc];
            }
            if (this.ba + n > this.bd + this.bc || this.ba < this.bd || this.bd < 0) {
                if (this.dh) {
                    this.a1.a(Math.max(this.ba * 4 - this.au, 0L));
                    n2 = this.a(this.a1, this.be, this.bc);
                    this.bd = this.ba;
                }
                else {
                    try {
                        this.a1.a(this.au + this.ba * 4);
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                    n2 = this.a(this.a1, this.be, this.bc);
                    this.bd = this.ba;
                }
            }
            else {
                n2 = n;
            }
            int n3 = 0;
            final int n4 = (int)(this.ba - this.bd);
            final int n5 = n4 + n;
            if (this.dh) {
                for (int i = n5 - 1; i >= n4; --i) {
                    array[n3++] = this.be[i];
                }
            }
            else {
                for (int j = n4; j < n5; ++j) {
                    array[n3++] = this.be[j];
                }
            }
        }
        this.f();
        return n2;
    }
    
    public final void c(int c1) throws Exception {
        if (this.dh) {
            c1 = (int)this.k - c1 - 1;
        }
        this.c1 = c1;
        this.ba = c1 * this.j;
        this.f();
    }
    
    private final void n(final Object o) {
        try {
            if (this.a2 != null) {
                this.a1 = this.a2;
                this.a3 = this.a4;
                this.f(o);
            }
        }
        catch (Exception ex) {}
    }
    
    private final void o(final Object o) {
        try {
            if (this.b3 != null) {
                this.a1 = this.b3;
                this.a3 = this.b2;
                this.f(o);
            }
        }
        catch (Exception ex) {}
    }
    
    public final void ab() {
        try {
            this.k(true);
        }
        catch (Exception ex) {}
        this.ba = -1L;
        this.bd = -1L;
    }
    
    public final void f(final Object o) {
        if (this.c7 != null) {
            for (int i = 0; i < this.c7.length; ++i) {
                this.c7[i] = null;
            }
            this.c7 = null;
        }
        try {
            this.ba = -1L;
            this.bd = -1L;
            this.be = null;
            this.bf = null;
            if (this.a1 != null) {
                this.a1.a(o);
                if (this.au == 0) {
                    this.a7.d(this.a3);
                }
            }
        }
        catch (Exception ex) {}
        finally {
            this.a1 = null;
            this.a3 = null;
        }
        try {
            if (this.a5 != null) {
                this.a5.a(o);
                if (this.au == 0) {
                    this.a7.d(this.a6);
                }
            }
        }
        catch (Exception ex2) {}
        finally {
            this.a5 = null;
            this.a6 = null;
        }
    }
    
    public final boolean a(final Component component, final af af, final boolean b) {
        this.f();
        return this.cf != null && this.ce != null && this.dg != null && fk.a(component, this, this.cf, this.ce, this.cg, this.ch, this.dg, this.cb, af, b);
    }
    
    public final void a(final short[] array, final int n) throws Exception {
        if (this.ay) {
            for (int i = 0; i < n; ++i) {
                array[i] *= (short)this.az;
            }
        }
        this.b(array, n);
    }
    
    public final void b(final short[] array, int length) throws Exception {
        if (this.y != null) {
            short[] array2 = new short[length];
            System.arraycopy(array, 0, array2, 0, length);
            if (this.ce != null) {
                if (this.cf != null) {
                    this.cf.a(array, length);
                }
                else {
                    final short[] a = fk.a(this, this.ce, array2, length, this.y.size(), this.cb);
                    if (a != null) {
                        array2 = a;
                        length = array2.length;
                    }
                }
            }
            this.y.addElement(array2);
            this.u += array2.length * 2;
            if (this.ce != null) {}
            this.q += length;
        }
        this.f();
    }
    
    public final byte[] a(final int n, final boolean b) {
        this.f();
        try {
            if (this.y == null) {
                return null;
            }
            byte[] array3;
            if (this.bi) {
                final int u = this.u();
                final byte[] array = new byte[u];
                this.b(n, array);
                final short[] array2 = new short[u];
                for (int i = 0; i < u; ++i) {
                    array2[i] = array[i];
                }
                array3 = this.c(array2, (int)this.j);
            }
            else {
                if (n >= this.y.size()) {
                    return null;
                }
                final short[] array4 = this.y.elementAt(n);
                if (array4 == null) {
                    return null;
                }
                array3 = this.c(array4, (int)this.j);
            }
            if (!b) {
                return array3;
            }
            int length = array3.length;
            final int n2 = length % 4;
            if (n2 > 0) {
                length += 4 - n2;
            }
            if (length != array3.length) {
                final byte[] array5 = new byte[length];
                System.arraycopy(array3, 0, array5, 0, array3.length);
                return array5;
            }
            return array3;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    private final byte[] c(final short[] array, int n) {
        n = (n + 7 & 0xFFFFFFF8);
        final byte[] array2 = new byte[n / 8];
        int n2 = 1;
        for (int n3 = array[0] & 0xFFFF, n4 = 1, length = array.length; n3 < n && n4 < length; ++n4) {
            if (n2 == 1) {
                final int n5 = n3 + (array[n4] & 0xFFFF);
                for (int i = n3; i < n5; ++i) {
                    final byte[] array3 = array2;
                    final int n6 = i / 8;
                    array3[n6] |= (byte)(1 << 7 - i % 8);
                }
                n3 = n5;
            }
            else {
                n3 += (array[n4] & 0xFFFF);
            }
            n2 = 1 - n2;
        }
        this.f();
        return array2;
    }
    
    public final boolean ac() {
        return this.h;
    }
    
    public final void h(final boolean h) {
        this.h = h;
    }
    
    public final boolean ad() {
        return this.i;
    }
    
    public final void i(final boolean i) {
        this.i = i;
    }
    
    public final boolean g(final Object o) throws Exception {
        this.ae();
        return this.a(o, this.ah);
    }
    
    public final boolean a(final Object o, final String s) throws Exception {
        boolean b = false;
        if (!this.bh && !this.bi) {
            try {
                if (ji.util.i.c(36)) {
                    ji.io.h.d(this.cd, "DIB: save synchronizing");
                }
                Label_1191: {
                    synchronized (ds.dj) {
                        if (ji.util.i.c(36)) {
                            ji.io.h.d(this.cd, "DIB: save synchronized");
                        }
                        if (this.ad && this.ac && s != null) {
                            boolean b2 = false;
                            if (ji.util.d.l() == 1 && !this.c4) {
                                if (!this.c8) {
                                    if (this.j(true)) {
                                        b2 = true;
                                    }
                                }
                                else {
                                    b2 = true;
                                }
                            }
                            System.currentTimeMillis();
                            ac ac = null;
                            Label_1163: {
                                try {
                                    ac = new ac(s, true, false, 0, false, o, this.cd);
                                    if (this.av) {
                                        ac.b(1);
                                    }
                                    else {
                                        ac.b(0);
                                    }
                                    if (this.c4) {
                                        ac.b(1);
                                    }
                                    else {
                                        ac.b(0);
                                    }
                                    boolean b3 = false;
                                    if (this.cx != null && !this.c4) {
                                        ac.b(1);
                                        long n = 0L;
                                        if (!this.cz && !b2 && this.c7 != null) {
                                            for (int i = 0; i < this.c7.length; ++i) {
                                                this.cy[i * 3] = n;
                                                if (this.c7[i] != null) {
                                                    n += this.c7[i].length * 4;
                                                }
                                            }
                                            b3 = true;
                                        }
                                        ac.b(this.cx.length);
                                        ac.b(this.cx);
                                        ac.b(this.cy);
                                    }
                                    else {
                                        ac.b(0);
                                    }
                                    ac.b((int)this.j);
                                    ac.b((int)this.k);
                                    ac.b(this.n);
                                    ac.b((int)this.r);
                                    ac.b((int)this.s);
                                    ac.b((int)this.dc);
                                    ac.b((int)this.dd);
                                    ac.b(this.df);
                                    if (this.de) {
                                        ac.b(1);
                                    }
                                    else {
                                        ac.b(0);
                                    }
                                    if (this.an) {
                                        ac.b(1);
                                    }
                                    else {
                                        ac.b(this.t);
                                    }
                                    ac.b(this.v);
                                    ac.b(this.x);
                                    if (this.as != null) {
                                        ac.b(this.as.length);
                                        this.a(ac, this.as, this.as.length, true);
                                    }
                                    else {
                                        ac.b(0);
                                    }
                                    Label_1093: {
                                        if (this.v == 1) {
                                            int n2 = 0;
                                            int n3 = 0;
                                            final int min = Math.min(this.y.size(), this.w.length);
                                            for (int j = 0; j < min; ++j) {
                                                this.w[j] = n3;
                                                final short[] array = this.y.elementAt(n2++);
                                                final int length = array.length;
                                                n3 += array.length;
                                            }
                                            if (this.w != null) {
                                                ac.b(min);
                                                this.a(ac, this.w, min, true);
                                            }
                                            else {
                                                ac.b(0);
                                            }
                                            ac.b((int)this.q);
                                            ac.r();
                                            int n4 = 0;
                                            for (int k = 0; k < min; ++k) {
                                                final short[] array2 = this.y.elementAt(n4++);
                                                this.a(ac, array2, array2.length);
                                            }
                                        }
                                        else {
                                            if (this.w != null) {
                                                ac.b(this.w.length);
                                                this.a(ac, this.w, this.w.length, true);
                                            }
                                            else {
                                                ac.b(0);
                                            }
                                            if (b3) {
                                                ac.b(1);
                                                for (int l = 0; l < this.c7.length; ++l) {
                                                    this.a(ac, this.c7[l], this.c7[l].length, true);
                                                }
                                            }
                                            else if (this.ai != null || this.aj != null || !this.av) {
                                                if (!this.av) {
                                                    ac.b(1);
                                                    final ac ac2 = new ac(this.a3, false, false, 0, false, o, this.cd);
                                                    try {
                                                        int n5 = 102400;
                                                        int n6 = (int)ac2.w();
                                                        if (n5 > n6) {
                                                            n5 = n6;
                                                        }
                                                        final byte[] array3 = new byte[n5];
                                                        while (n6 > 0) {
                                                            final int a = ac2.a(array3, 0, n5);
                                                            ac.b(array3, 0, a);
                                                            n6 -= a;
                                                            if (n5 > n6) {
                                                                n5 = n6;
                                                            }
                                                        }
                                                        break Label_1093;
                                                    }
                                                    finally {
                                                        try {
                                                            ac2.a(o);
                                                        }
                                                        catch (Exception ex2) {}
                                                    }
                                                }
                                                ac.b(0);
                                                if (this.ai != null) {
                                                    ac.b(this.ai.length);
                                                    this.a(ac, this.ai, this.ai.length, true);
                                                }
                                                else {
                                                    ac.b(this.aj.length);
                                                    ac.b(this.aj, 0, this.aj.length);
                                                }
                                            }
                                            else {
                                                ac.b(0);
                                            }
                                        }
                                    }
                                    b = true;
                                    break Label_1163;
                                }
                                finally {
                                    if (ac != null) {
                                        ac.a(o);
                                    }
                                }
                                throw new Exception(this.d(392));
                            }
                            if (ji.util.i.c(36)) {
                                ji.io.h.d(this.cd, "DIB: save end synchronized");
                            }
                            // monitorexit(ds.dj)
                            break Label_1191;
                        }
                        throw new Exception(this.d(392));
                    }
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            finally {
                this.h = false;
            }
        }
        this.f();
        return b;
    }
    
    public final void ae() {
        if (this.h || this.i) {
            while (this.h || this.i) {
                ji.util.d.b(100, 10221, this.cd);
            }
        }
    }
    
    public final void a(final String s, final Object o) throws Exception {
        boolean b = false;
        this.av = true;
        this.db = false;
        this.ay = false;
        this.ae();
        if (s != null) {
            if (this.ah == null) {
                b = true;
            }
            else if (!this.ah.equals(s)) {
                b = true;
            }
            if (b) {
                this.f(o);
                this.n(o);
                System.currentTimeMillis();
                this.h(o);
                this.ah = s;
                ac ac = null;
                this.ai = null;
                try {
                    ac = new ac(s, false, false, 0, false, o, this.cd);
                    if (ac.p() > 0) {
                        this.av = true;
                    }
                    else {
                        this.av = false;
                    }
                    if (ac.p() > 0) {
                        this.c4 = true;
                    }
                    else {
                        this.c4 = false;
                    }
                    if (ac.p() > 0) {
                        final int p2 = ac.p();
                        this.cx = new long[p2];
                        this.cy = new long[p2];
                        this.cz = false;
                        ac.a(this.cx);
                        ac.a(this.cy);
                        this.cz = true;
                    }
                    this.j = ac.p();
                    this.k = ac.p();
                    this.n = ac.p();
                    this.r = ac.p();
                    this.s = ac.p();
                    this.dc = ac.p();
                    this.dd = ac.p();
                    this.df = ac.p();
                    this.de = (ac.p() == 1);
                    this.t = ac.p();
                    this.v = ac.p();
                    this.x = ac.p();
                    this.at();
                    this.ae = ac.p();
                    if (this.ae > 0) {
                        this.a(ac, this.as = new int[this.ae], this.ae);
                    }
                    this.ae = ac.p();
                    if (this.ae > 0) {
                        this.a(ac, this.w = new int[this.ae + 1], this.ae);
                    }
                    if (this.v == 0) {
                        ac.p();
                        this.ae = ac.p();
                        if (this.ae > 0) {
                            this.a(ac, this.ai = new int[this.ae], this.ae);
                        }
                    }
                    else if (this.v == 4) {
                        ac.p();
                        if (this.av) {
                            this.ae = ac.p();
                            if (this.ae > 0) {
                                this.a(ac, this.ai = new int[this.ae], this.ae);
                            }
                        }
                        else {
                            this.au = ac.r();
                            this.a3 = s;
                            this.a1 = new ac(this.a3, true, false, 0, false, o, this.cd);
                            if (this.cy != null) {
                                for (int i = 0; i < this.cy.length; ++i) {
                                    final long[] cy = this.cy;
                                    final int n = i;
                                    cy[n] += this.au;
                                }
                                this.au = 0L;
                            }
                        }
                    }
                    else if (this.v == 3) {
                        if (ac.p() == 0) {
                            this.ae = ac.p();
                            if (this.ae > 0) {
                                ac.a(this.aj = new byte[this.ae], 0, this.ae);
                            }
                        }
                        else {
                            this.au = ac.r();
                            this.a3 = s;
                            this.a1 = new ac(this.a3, true, false, 0, false, o, this.cd);
                        }
                    }
                    else if (this.v == 2) {
                        if (ac.p() == 0) {
                            this.ae = ac.p();
                            if (this.ae > 0) {
                                ac.a(this.aj = new byte[this.ae], 0, this.ae);
                            }
                        }
                        else {
                            this.au = ac.r();
                            this.a3 = s;
                            this.a1 = new ac(this.a3, true, false, 0, false, o, this.cd);
                        }
                    }
                    else {
                        this.q = ac.p();
                        if (this.y != null) {
                            this.y.removeAllElements();
                            this.y = null;
                        }
                        if (this.aa != null) {
                            this.aa.removeAllElements();
                            this.aa = null;
                        }
                        if (this.ab != null) {
                            this.ab = null;
                        }
                        final byte[] array = new byte[(int)(ac.w() - ac.r())];
                        ac.a(array);
                        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(array);
                        this.y = new Vector((int)this.k, 100);
                        if (ji.util.d.dy()) {
                            this.aa = new Vector((int)this.k, 100);
                            this.ab = new boolean[(int)this.k];
                        }
                        long n2 = -1L;
                        short[] array2 = null;
                        this.u = 0L;
                        for (int ae = this.ae, j = 0; j < ae; ++j) {
                            long n3;
                            if (j < this.k - 1) {
                                n3 = this.w[j + 1] - this.w[j];
                            }
                            else {
                                n3 = this.q - this.w[j];
                            }
                            if (n3 > 0) {
                                if (n3 > n2) {
                                    array2 = new short[(int)n3];
                                    n2 = n3;
                                }
                                this.a(byteArrayInputStream, array2, (int)n3);
                                final short[] array3 = new short[(int)n3];
                                System.arraycopy(array2, 0, array3, 0, (int)n3);
                                this.y.addElement(array3);
                                this.u += array3.length * 2;
                            }
                        }
                        byteArrayInputStream.close();
                    }
                    this.ac = true;
                    this.a0 = true;
                }
                finally {
                    if (ac != null) {
                        ac.a(o);
                    }
                    ji.util.d.d(true, this.cd);
                }
            }
        }
        this.f();
    }
    
    public boolean af() {
        return this.ac;
    }
    
    private final void a(final ac ac, final short[] array, final int n) throws Exception {
        if (array != null) {
            int i = n * 2;
            int n2 = Math.min(this.at, i);
            int n3 = 0;
            final byte[] array2 = new byte[n2];
            while (i > 0) {
                for (int j = 0; j < n2; j += 2) {
                    final int n4 = (j + n3) / 2;
                    array2[j] = (byte)(array[n4] >> 8 & 0xFF);
                    array2[j + 1] = (byte)(array[n4] & 0xFF);
                }
                ac.b(array2, 0, n2);
                i -= n2;
                n3 += n2;
                n2 = Math.min(this.at, i);
            }
        }
    }
    
    private final void a(final ac ac, final int[] array, final int n, final boolean b) throws Exception {
        if (array != null) {
            if (b) {
                ac.x();
            }
            if (ac.z()) {
                ac.b(array, 0, n);
                return;
            }
            int i = n * 4;
            int n2 = Math.min(this.at, i);
            int n3 = 0;
            if (this.bg == null) {
                this.bg = new byte[n2];
            }
            else if (this.bg.length != n2) {
                this.bg = new byte[n2];
            }
            while (i > 0) {
                ji.util.d.a(array, n3 / 4, n2 / 4, this.bg, 0, n2);
                ac.b(this.bg, 0, n2);
                i -= n2;
                n3 += n2;
                n2 = Math.min(this.at / 4, i);
            }
        }
    }
    
    private final void a(final ByteArrayInputStream byteArrayInputStream, final short[] array, final int n) throws Exception {
        if (array != null) {
            int i = n * 2;
            int n2 = Math.min(this.at, i);
            int n3 = 0;
            final byte[] array2 = new byte[n2];
            while (i > 0) {
                byteArrayInputStream.read(array2, 0, n2);
                for (int j = 0; j < n2; j += 2) {
                    final int n4 = (j + n3) / 2;
                    array[n4] = (short)((array2[j] & 0xFF) << 8);
                    final int n5 = n4;
                    array[n5] |= (short)(array2[j + 1] & 0xFF);
                }
                i -= n2;
                n3 += n2;
                n2 = Math.min(this.at, i);
            }
        }
    }
    
    private final int a(final ac ac, final int[] array, final int n) throws Exception {
        int n2 = 0;
        if (array != null) {
            if (ac.z()) {
                ac.a(array, 0, n);
                return n2 + n * 4;
            }
            int i = n * 4;
            int n3 = Math.min(this.at, i);
            int n4 = 0;
            final byte[] array2 = new byte[n3];
            while (i > 0) {
                final int a = ac.a(array2, 0, n3);
                if (a <= 0) {
                    break;
                }
                n2 += a;
                for (int j = 0; j < a; j += 4) {
                    final int n5 = (j + n4) / 4;
                    array[n5] = (array2[j] & 0xFF) << 24;
                    final int n6 = n5;
                    array[n6] |= (array2[j + 1] & 0xFF) << 16;
                    final int n7 = n5;
                    array[n7] |= (array2[j + 2] & 0xFF) << 8;
                    final int n8 = n5;
                    array[n8] |= (array2[j + 3] & 0xFF);
                }
                i -= a;
                n4 += a;
                n3 = Math.min(this.at, i);
            }
        }
        return n2;
    }
    
    private final void a(final byte[] array, final int[] array2) {
        try {
            for (int i = 0; i < array.length; i += 4) {
                final int n = i / 4;
                array2[n] = (array[i] & 0xFF) << 24;
                final int n2 = n;
                array2[n2] |= (array[i + 1] & 0xFF) << 16;
                final int n3 = n;
                array2[n3] |= (array[i + 2] & 0xFF) << 8;
                final int n4 = n;
                array2[n4] |= (array[i + 3] & 0xFF);
            }
        }
        catch (Exception ex) {}
    }
    
    public final byte[] ag() {
        this.f();
        return this.aj;
    }
    
    public final byte[] ah() {
        this.f();
        return this.ak;
    }
    
    public final void a(final byte[] ak) {
        this.f();
        this.ak = ak;
    }
    
    public final void a(final int n, final int n2) {
        this.aq = n;
        this.ar = n2;
    }
    
    public final Vector ai() {
        return this.y;
    }
    
    public final Vector aj() {
        return this.aa;
    }
    
    public final boolean[] ak() {
        return this.ab;
    }
    
    public final void al() {
        try {
            if (this.aa != null) {
                this.aa.removeAllElements();
            }
            this.ab = new boolean[(int)this.k];
            this.aa = null;
        }
        catch (Exception ex) {}
        this.f();
    }
    
    public final int am() {
        return this.x;
    }
    
    private final void au() {
        this.s = 14 + this.k + 4 * this.k + 2;
        this.av();
    }
    
    private final void av() {
        if (this.w == null) {
            this.w = new int[(int)this.k + 1];
        }
        else if (this.w.length != this.k + 1) {
            this.w = null;
            this.w = new int[(int)this.k + 1];
        }
    }
    
    public void finalize() {
        if (this.y != null) {
            this.y.removeAllElements();
            this.y = null;
        }
        if (this.c7 != null) {
            for (int i = 0; i < this.c7.length; ++i) {
                this.c7[i] = null;
            }
            this.c7 = null;
        }
        if (this.aa != null) {
            this.aa.removeAllElements();
            this.aa = null;
        }
    }
    
    public final void b(final ds ds) throws Exception {
        final Field[] declaredFields = this.getClass().getDeclaredFields();
        for (int i = 0; i < declaredFields.length; ++i) {
            if (!Modifier.isStatic(declaredFields[i].getModifiers())) {
                final Class<?> type = declaredFields[i].getType();
                if (type == Boolean.TYPE) {
                    declaredFields[i].setBoolean(this, declaredFields[i].getBoolean(ds));
                }
                else if (type == Long.TYPE) {
                    declaredFields[i].setLong(this, declaredFields[i].getLong(ds));
                }
                else if (type == Integer.TYPE) {
                    declaredFields[i].setInt(this, declaredFields[i].getInt(ds));
                }
                else if (type == Short.TYPE) {
                    declaredFields[i].setShort(this, declaredFields[i].getShort(ds));
                }
                else if (type == Byte.TYPE) {
                    declaredFields[i].setByte(this, declaredFields[i].getByte(ds));
                }
                else if (type == Double.TYPE) {
                    declaredFields[i].setDouble(this, declaredFields[i].getDouble(ds));
                }
                else if (type == Float.TYPE) {
                    declaredFields[i].setFloat(this, declaredFields[i].getFloat(ds));
                }
                else if (type == Character.TYPE) {
                    declaredFields[i].setChar(this, declaredFields[i].getChar(ds));
                }
                else if (type.isArray()) {
                    final Object value = declaredFields[i].get(ds);
                    Object instance = null;
                    if (value != null) {
                        final int length = Array.getLength(value);
                        final Class<?> componentType = (Class<?>)type.getComponentType();
                        if (componentType.isArray()) {
                            throw new Exception("Cannot deal with multi-dimensional arrays yet....");
                        }
                        instance = Array.newInstance(componentType, length);
                        for (int j = 0; j < length; ++j) {
                            if (componentType == Boolean.TYPE) {
                                Array.setBoolean(instance, i, Array.getBoolean(value, j));
                            }
                            else if (componentType == Long.TYPE) {
                                Array.setLong(instance, i, Array.getLong(value, j));
                            }
                            else if (componentType == Integer.TYPE) {
                                Array.setInt(instance, i, Array.getInt(value, j));
                            }
                            else if (componentType == Short.TYPE) {
                                Array.setShort(instance, i, Array.getShort(value, j));
                            }
                            else if (componentType == Byte.TYPE) {
                                Array.setByte(instance, i, Array.getByte(value, j));
                            }
                            else if (componentType == Double.TYPE) {
                                Array.setDouble(instance, i, Array.getDouble(value, j));
                            }
                            else if (componentType == Float.TYPE) {
                                Array.setFloat(instance, i, Array.getFloat(value, j));
                            }
                            else if (componentType == Character.TYPE) {
                                Array.setChar(instance, i, Array.getChar(value, j));
                            }
                            else {
                                Array.set(instance, j, Array.get(value, j));
                            }
                        }
                    }
                    declaredFields[i].set(this, instance);
                }
                else if (type == ((ds.dk == null) ? (ds.dk = class$("java.util.Vector")) : ds.dk)) {
                    Vector<Object> vector = null;
                    final Vector vector2 = (Vector)declaredFields[i].get(ds);
                    if (vector2 != null) {
                        vector = new Vector<Object>(vector2.size());
                        for (int k = 0; k < vector2.size(); ++k) {
                            vector.addElement(vector2.get(k));
                        }
                    }
                    declaredFields[i].set(this, vector);
                }
                else {
                    declaredFields[i].set(this, declaredFields[i].get(ds));
                }
            }
        }
    }
    
    static Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        dj = new Object();
    }
}
