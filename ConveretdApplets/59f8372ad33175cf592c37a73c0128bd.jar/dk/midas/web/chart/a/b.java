// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.a;

import java.util.Random;
import java.util.StringTokenizer;
import java.io.EOFException;
import java.io.IOException;
import dk.midas.web.chart.applet.bh;
import java.util.Calendar;
import java.util.TimeZone;
import java.io.DataInputStream;
import java.util.Date;

public class b extends Thread implements f, a
{
    public double a6;
    private e aN;
    private c aW;
    private long ba;
    private long aU;
    private String U;
    public String E;
    private int Z;
    private int aR;
    private int B;
    private boolean M;
    private int bh;
    private int aX;
    private boolean aK;
    private boolean a2;
    private boolean az;
    private boolean aM;
    private boolean af;
    private int aA;
    private int a9;
    private int N;
    public double[] aw;
    public double[] aB;
    public double[] bp;
    public double[] R;
    public double[] bt;
    public double[] ay;
    public double[] bo;
    public Date[] aH;
    public long[] ak;
    private double Y;
    private double au;
    private short aq;
    private long bj;
    private long aC;
    private int bm;
    private double S;
    private double J;
    private double ax;
    private double a3;
    private double a0;
    private double aQ;
    private double aY;
    private static final int I = 0;
    private static final int bk = 0;
    private static final int ao = 1;
    private static final short h = 0;
    public static final short K = -128;
    public static final short bq = Short.MIN_VALUE;
    public static final double bb = Double.MIN_NORMAL;
    private byte[] aZ;
    private int am;
    private DataInputStream T;
    private String C;
    private Object L;
    private int ae;
    private String ad;
    private int as;
    private String aa;
    private String bs;
    private String ai;
    private TimeZone aJ;
    private Calendar aP;
    private long P;
    private int aV;
    private int F;
    private double bd;
    private boolean a8;
    private boolean be;
    private long ab;
    private long bn;
    private int ap;
    private int G;
    private int ah;
    private int W;
    private double[] bu;
    private double[] ag;
    private double[] Q;
    private double at;
    private double al;
    private double aG;
    private int aF;
    private double[] av;
    private double[] aj;
    private double[] aI;
    private double[] O;
    private double[] aD;
    public static final int aS = 6;
    public static final int aO = 5;
    public static final int aT = 2;
    private int D;
    private double br;
    private double a7;
    private double a1;
    private short aE;
    private double bg;
    private double H;
    private double aL;
    private boolean bl;
    private boolean an;
    private static final short V = 0;
    private static final short ar = 1;
    private static final short a5 = 2;
    private static final short bi = 3;
    private static final short bc = 4;
    private static final short A = 5;
    private static final int ac = 0;
    private static final int bf = 1;
    private static final int X = 2;
    private static final int a4 = 3;
    
    public b(final e an, final String u, final String bs, final String ai) {
        this.a6 = 0.0;
        this.aW = null;
        this.M = false;
        this.bh = 0;
        this.aK = false;
        this.a2 = true;
        this.az = false;
        this.aM = false;
        this.af = false;
        this.aA = 4;
        this.a9 = 0;
        this.N = 0;
        this.Y = 0.0;
        this.au = 1000.0;
        this.aq = 0;
        this.bj = 0L;
        this.aC = 0L;
        this.bm = 0;
        this.C = "test";
        this.L = new Object();
        this.ae = dk.midas.web.chart.applet.bh.a;
        this.ad = dk.midas.web.chart.applet.bh.if;
        this.as = dk.midas.web.chart.applet.bh.for;
        this.aa = "unknown";
        this.aJ = TimeZone.getDefault();
        this.aP = Calendar.getInstance();
        this.P = Calendar.getInstance().get(15) / 1000;
        this.aV = -1;
        this.F = 0;
        this.bd = 0.0;
        this.a8 = false;
        this.be = false;
        this.G = 0;
        this.ah = 0;
        this.W = 0;
        this.bu = null;
        this.ag = null;
        this.Q = null;
        this.at = 0.0;
        this.al = 0.0;
        this.aG = 0.0;
        this.aF = 0;
        this.av = null;
        this.aj = null;
        this.aI = null;
        this.O = new double[] { 1.0, 10.0, 100.0, 1000.0, 10000.0, 100000.0, 1000000.0 };
        this.aD = new double[] { 1.0, 0.1, 0.01, 0.001, 1.0E-4, 1.0E-5, 1.0E-6, 1.0E-7 };
        this.D = 0;
        this.br = 0.0;
        this.a7 = 0.0;
        this.a1 = 0.0;
        this.aE = 0;
        this.bg = 0.0;
        this.H = 0.0;
        this.aL = 0.0;
        this.bl = false;
        this.an = false;
        String c = null;
        final long n = 3000L;
        this.aU = n;
        this.ba = n;
        this.start();
        this.aN = an;
        this.bs = bs;
        this.ai = ai;
        if (an instanceof dk.midas.web.chart.applet.a) {
            c = ((dk.midas.web.chart.applet.a)an).aX.getParameter("name");
            if (c == null) {
                c = ((dk.midas.web.chart.applet.a)an).aX.getParameter("id");
            }
        }
        if (c != null) {
            this.C = c;
        }
        this.U = u;
        if (u.indexOf("Ext31.dll") > 0) {
            this.a8 = true;
        }
    }
    
    public b(final e an, final String u, final String e, final int z, final int ar, final int b, final String bs) {
        this.a6 = 0.0;
        this.aW = null;
        this.M = false;
        this.bh = 0;
        this.aK = false;
        this.a2 = true;
        this.az = false;
        this.aM = false;
        this.af = false;
        this.aA = 4;
        this.a9 = 0;
        this.N = 0;
        this.Y = 0.0;
        this.au = 1000.0;
        this.aq = 0;
        this.bj = 0L;
        this.aC = 0L;
        this.bm = 0;
        this.C = "test";
        this.L = new Object();
        this.ae = dk.midas.web.chart.applet.bh.a;
        this.ad = dk.midas.web.chart.applet.bh.if;
        this.as = dk.midas.web.chart.applet.bh.for;
        this.aa = "unknown";
        this.aJ = TimeZone.getDefault();
        this.aP = Calendar.getInstance();
        this.P = Calendar.getInstance().get(15) / 1000;
        this.aV = -1;
        this.F = 0;
        this.bd = 0.0;
        this.a8 = false;
        this.be = false;
        this.G = 0;
        this.ah = 0;
        this.W = 0;
        this.bu = null;
        this.ag = null;
        this.Q = null;
        this.at = 0.0;
        this.al = 0.0;
        this.aG = 0.0;
        this.aF = 0;
        this.av = null;
        this.aj = null;
        this.aI = null;
        this.O = new double[] { 1.0, 10.0, 100.0, 1000.0, 10000.0, 100000.0, 1000000.0 };
        this.aD = new double[] { 1.0, 0.1, 0.01, 0.001, 1.0E-4, 1.0E-5, 1.0E-6, 1.0E-7 };
        this.D = 0;
        this.br = 0.0;
        this.a7 = 0.0;
        this.a1 = 0.0;
        this.aE = 0;
        this.bg = 0.0;
        this.H = 0.0;
        this.aL = 0.0;
        this.bl = false;
        this.an = false;
        String c = null;
        final long n = 3000L;
        this.aU = n;
        this.ba = n;
        this.start();
        this.bs = bs;
        this.aN = an;
        if (an instanceof dk.midas.web.chart.applet.a) {
            c = ((dk.midas.web.chart.applet.a)an).aX.getParameter("name");
            if (c == null) {
                c = ((dk.midas.web.chart.applet.a)an).aX.getParameter("id");
            }
        }
        if (c != null) {
            this.C = c;
        }
        this.U = u;
        this.E = e;
        this.Z = z;
        this.aR = ar;
        this.B = b;
        if (e.endsWith("+A") || e.endsWith("+a")) {
            this.F = 1;
            this.E = e.substring(0, e.length() - 2);
        }
        if (u.indexOf("Ext31.dll") > 0) {
            this.a8 = true;
        }
    }
    
    public void a(final boolean a8) {
        this.a8 = a8;
    }
    
    public void run() {
        while (!this.M) {
            this.f();
            this.case();
            this.f();
            this.byte();
        }
    }
    
    public void a(final String e, final int ar, final int b) {
        this.E = e;
        this.Z = 1;
        this.aR = ar;
        this.B = b;
        if (e.endsWith("+A") || e.endsWith("+a")) {
            this.F = 1;
            this.E = e.substring(0, e.length() - 2);
        }
        this.long();
    }
    
    public void if(final long n) {
        this.ba = n;
        this.aU = n;
    }
    
    public int if() {
        return this.aV;
    }
    
    public void a(final long ab, final long bn, final int ap) {
        this.ab = ab;
        this.bn = bn;
        this.ap = ap;
        this.be = true;
    }
    
    public synchronized void case() {
        if (!this.a2) {
            if (this.aW == null) {
                this.aM = true;
                try {
                    this.aW = new c(this.U, this.E, this.Z, this.aR, this.B, this.C, this.ae, this.ad, this.bs, this.ai, this.as);
                }
                catch (Exception ex) {
                    this.aN.do("No connection to server\n" + ex);
                    this.if(10000);
                }
            }
            else {
                if (this.aW.do()) {
                    return;
                }
                try {
                    if (this.be) {
                        this.aW.a(this.ab, this.bn, this.ap);
                        this.be = false;
                    }
                    else {
                        this.aW.int();
                    }
                }
                catch (Exception ex2) {
                    this.aN.do("Connection to server lost \n" + ex2);
                    this.if(10000);
                }
            }
            if (this.aW != null) {
                this.T = this.aW.if();
                if (this.T == null) {
                    this.if(10000);
                }
                if (this.try() && !this.aK) {
                    this.a(this.bh);
                }
            }
            this.N = 0;
            try {
                if (this.T != null) {
                    this.T.close();
                }
            }
            catch (IOException ex3) {}
        }
        else {
            System.out.println("Data Frozen");
        }
    }
    
    private synchronized void byte() {
        try {
            this.wait(this.aU);
            this.aU = this.ba;
        }
        catch (InterruptedException ex) {
            System.out.println("Thread interrupted " + ex);
            if (this.aW != null) {
                this.aW.a();
            }
            ex.printStackTrace();
        }
    }
    
    public synchronized void null() {
        this.a2 = true;
        if (this.aW != null) {
            this.aW.a();
        }
        this.aW = null;
        this.aK = true;
    }
    
    public synchronized void g() {
        if (this.aW != null) {
            this.aW.a();
        }
        this.aW = null;
        this.a2 = true;
    }
    
    public synchronized void long() {
        this.a2 = false;
        this.b();
    }
    
    public void a() {
        if (this.aW != null) {
            this.aW.a();
        }
        this.aW = null;
        this.M = true;
    }
    
    public synchronized void void() {
        this.b();
    }
    
    protected final synchronized void f() {
        if (this.aK) {
            System.out.println("Suspend run..." + this.E + " " + this.aR);
            try {
                this.wait();
            }
            catch (InterruptedException ex) {
                System.out.println("InterruptedException threadCheckSuspend");
            }
            System.out.println("Resume run...");
            this.aK = false;
        }
    }
    
    public synchronized void b() {
        if (this.aK) {
            this.notify();
        }
        this.aK = false;
        this.a2 = false;
    }
    
    public synchronized void e() {
        this.aK = true;
        this.aW = null;
    }
    
    private boolean d() {
        boolean b = false;
        final long n = this.ak[0];
        if (this.ak[0] >= this.aC) {
            this.aC += this.bm;
            b = true;
        }
        return b;
    }
    
    private synchronized void a(final int n) {
        if (this.aM) {
            this.aN.a(this, this.aA, this.aA + this.a9, n, this.N);
            if (this.N > 0) {
                this.aM = false;
            }
            this.ax = this.aw[this.N - 1];
            this.a3 = this.aB[this.N - 1];
            this.a0 = this.bp[this.N - 1];
            this.S = this.R[this.N - 1];
            this.J = this.bt[this.N - 1];
            this.aQ = this.ay[this.N - 1];
            this.aY = this.bo[this.N - 1];
        }
        else if (this.N > 1) {
            this.aN.a(this, n, this.N);
            this.ax = this.aw[this.N - 1];
            this.a3 = this.aB[this.N - 1];
            this.a0 = this.bp[this.N - 1];
            this.S = this.R[this.N - 1];
            this.J = this.bt[this.N - 1];
            this.aQ = this.ay[this.N - 1];
            this.aY = this.bo[this.N - 1];
        }
        else {
            this.aN.a(this, n, !this.az);
        }
    }
    
    public synchronized long[] char() {
        final long[] array = new long[this.N];
        System.arraycopy(this.ak, 0, array, 0, this.ak.length);
        return array;
    }
    
    public synchronized double[] for(final int n) {
        this.a2 = true;
        final int n2 = this.N;
        Object o = null;
        final double[] array = new double[n2];
        switch (n) {
            case 1: {
                o = this.aw;
                break;
            }
            case 2: {
                o = this.aB;
                break;
            }
            case 3: {
                o = this.R;
                break;
            }
            case 4: {
                o = this.bt;
                break;
            }
            case 5: {
                o = this.ay;
                break;
            }
            case 6: {
                o = this.bo;
                break;
            }
            case 7: {
                o = this.bp;
                break;
            }
        }
        if (o != null) {
            System.arraycopy(o, 0, array, 0, o.length);
            return array;
        }
        return array;
    }
    
    private synchronized boolean try() {
        boolean b;
        try {
            final short i = this.i();
            if (i > 22 && !this.a8) {
                this.au = this.int();
                this.Y = this.int();
            }
            this.aA = this.i();
            final short j = this.i();
            this.i();
            final short k = this.i();
            final short l = this.i();
            if (i > 28 || this.a8) {
                this.aq = this.i();
            }
            this.aX = l - 1;
            if (!this.af || l > this.aw.length) {
                this.aw = new double[l];
                this.aB = new double[l];
                this.bp = new double[l];
                this.R = new double[l];
                this.bt = new double[l];
                this.ay = new double[l];
                this.bo = new double[l];
            }
            this.aH = new Date[l];
            this.ak = new long[l];
            b = false;
            this.az = false;
            for (short n = 0; n < j; ++n) {
                b |= this.a(this.au, this.Y, k);
            }
        }
        catch (EOFException ex2) {
            this.aN.do("End of data stream prematurely reached");
            this.if(10000);
            b = false;
        }
        catch (IOException ex3) {
            this.aN.if("An I/O problem was encountered ");
            this.if(10000);
            b = false;
        }
        catch (g g) {
            this.aN.if("An internal problem was encountered \n" + g);
            this.if(10000);
            b = false;
        }
        catch (Exception ex) {
            this.aN.if("An unexpected internal problem was encountered\n" + ex);
            ex.printStackTrace();
            this.if(10000);
            b = false;
        }
        return b;
    }
    
    private synchronized double a(final boolean b, final double n, final double n2) throws EOFException, IOException, g {
        short i;
        if (b) {
            final byte c = this.c();
            i = (short)((c == -128) ? -32768 : ((short)c));
        }
        else {
            i = this.i();
        }
        double n3;
        if (i == -32768) {
            n3 = Double.MIN_NORMAL;
        }
        else {
            final double n4 = i / n + n2;
            n3 = ((n4 == 0.0) ? Double.MIN_NORMAL : n4);
        }
        return n3;
    }
    
    private synchronized void a(final short n, final double n2, final double n3, final int n4, final long n5, final short n6) throws EOFException, IOException, g {
        if (n4 < 0) {
            throw new g("Index out of bounds " + n4);
        }
        switch (n) {
            case 1:
            case 11: {
                final boolean b = n == 11;
                this.aw[n4] = this.a(b, n2, n3);
                this.aB[n4] = this.a(b, n2, n3);
                this.R[n4] = this.a(b, n2, n3);
                this.bt[n4] = this.a(b, n2, n3);
                final double int1 = this.int();
                this.ay[n4] = ((int1 >= 0.0) ? int1 : 0.0);
                this.ak[n4] = n5;
                break;
            }
            case 2:
            case 12: {
                this.aB[n4] = this.a(n == 12, n2, n3);
                final double int2 = this.int();
                this.ay[n4] = ((int2 >= 0.0) ? int2 : 0.0);
                this.ak[n4] = n5;
                break;
            }
            case 3:
            case 13: {
                final double a = this.a(n == 13, n2, n3);
                if (this.az) {
                    if (!this.af && a != Double.MIN_NORMAL) {
                        this.af = true;
                        this.aw[n4] = this.ax;
                        this.aB[n4] = a;
                        this.R[n4] = this.S;
                        this.bt[n4] = this.J;
                    }
                    else {
                        this.a(a, n4);
                    }
                    this.ak[n4] = this.a((long)this.j());
                    break;
                }
                this.af = false;
                this.aB[n4] = a;
                this.ak[n4] = this.a((long)this.j());
                break;
            }
            case 5:
            case 15: {
                final boolean b2 = n == 15;
                final double a2 = this.a(b2, n2, n3);
                final double a3 = this.a(b2, n2, n3);
                final double a4 = this.a(b2, n2, n3);
                final double a5 = this.a(b2, n2, n3);
                if (this.az) {
                    if (!this.af && a3 != Double.MIN_NORMAL) {
                        this.af = true;
                        this.aw[n4] = a2;
                        this.aB[n4] = a3;
                        this.R[n4] = a4;
                        this.bt[n4] = a5;
                    }
                    else {
                        this.a(a3, n4);
                    }
                }
                else {
                    this.af = false;
                    this.aw[n4] = a2;
                    this.aB[n4] = a3;
                    this.R[n4] = a4;
                    this.bt[n4] = a5;
                    if (a4 != Double.MIN_NORMAL) {
                        this.S = a4;
                    }
                    if (a5 != Double.MIN_NORMAL) {
                        this.J = a5;
                    }
                    if (a2 != Double.MIN_NORMAL) {
                        this.ax = a2;
                    }
                    if (a3 != Double.MIN_NORMAL) {
                        this.a3 = a3;
                    }
                }
                this.ak[n4] = n5;
                break;
            }
            case 7:
            case 17: {
                final boolean b3 = n == 17;
                this.aw[n4] = this.a(b3, n2, n3);
                this.aB[n4] = this.a(b3, n2, n3);
                this.R[n4] = this.a(b3, n2, n3);
                this.bt[n4] = this.a(b3, n2, n3);
                final double int3 = this.int();
                this.ay[n4] = ((int3 >= 0.0) ? int3 : 0.0);
                this.bo[n4] = this.int();
                this.ak[n4] = n5;
                break;
            }
            case 8:
            case 18: {
                final double a6 = this.a(n == 18, n2, n3);
                final double int4 = this.int();
                this.ay[n4] = ((int4 >= 0.0) ? int4 : 0.0);
                if (this.az) {
                    if (!this.af && a6 != Double.MIN_NORMAL) {
                        this.af = true;
                        this.aw[n4] = this.ax;
                        this.aB[n4] = a6;
                        this.R[n4] = this.S;
                        this.bt[n4] = this.J;
                        this.ay[n4] = this.aQ;
                    }
                    else {
                        this.a(a6, n4);
                    }
                    this.ak[n4] = this.a((long)this.j());
                    break;
                }
                this.af = false;
                this.aB[n4] = a6;
                this.ak[n4] = this.a((long)this.j());
                break;
            }
            case 9:
            case 19: {
                final double a7 = this.a(n == 19, n2, n3);
                final double int5 = this.int();
                this.ay[n4] = ((int5 >= 0.0) ? int5 : 0.0);
                this.bo[n4] = this.int();
                if (this.az) {
                    if (!this.af && a7 != Double.MIN_NORMAL) {
                        this.af = true;
                        this.aw[n4] = this.ax;
                        this.aB[n4] = a7;
                        this.R[n4] = this.S;
                        this.bt[n4] = this.J;
                        this.ay[n4] = this.aQ;
                        this.bo[n4] = this.aY;
                    }
                    else {
                        this.a(a7, n4);
                    }
                    this.ak[n4] = this.a((long)this.j());
                    break;
                }
                this.af = false;
                this.aB[n4] = a7;
                this.ak[n4] = this.a((long)this.j());
                break;
            }
            case 20:
            case 21: {
                final boolean b4 = n == 21;
                final double a8 = this.a(b4, n2, n3);
                final double a9 = this.a(b4, n2, n3);
                final double a10 = this.a(b4, n2, n3);
                final double a11 = this.a(b4, n2, n3);
                this.aw[n4] = this.if(this.F, a8, a10);
                this.aB[n4] = this.if(this.F, a9, a11);
                this.bp[n4] = this.if(1, a9, a11);
                final double a12 = this.a(b4, n2, n3);
                final double a13 = this.a(b4, n2, n3);
                final double a14 = this.a(b4, n2, n3);
                final double a15 = this.a(b4, n2, n3);
                this.R[n4] = this.if(this.F, a12, a13);
                this.bt[n4] = this.if(this.F, a14, a15);
                this.ak[n4] = n5;
                break;
            }
            case 22:
            case 23: {
                final boolean b5 = n == 23;
                final double a16 = this.a(b5, n2, n3);
                final double a17 = this.a(b5, n2, n3);
                final double if1 = this.if(this.F, a16, a17);
                final double if2 = this.if(1, a16, a17);
                if (this.az) {
                    if (!this.af && if1 != Double.MIN_NORMAL) {
                        this.af = true;
                        this.aw[n4] = this.ax;
                        this.aB[n4] = if1;
                        this.bp[n4] = if2;
                        this.R[n4] = this.S;
                        this.bt[n4] = this.J;
                    }
                    else {
                        this.a(if1, if2, n4);
                    }
                    this.ak[n4] = this.a((long)this.j());
                    break;
                }
                this.af = false;
                this.aB[n4] = if1;
                this.bp[n4] = if2;
                this.ak[n4] = this.a((long)this.j());
                break;
            }
            case 26:
            case 27: {
                final boolean b6 = n == 27;
                final double a18 = this.a(b6, n2, n3);
                final double a19 = this.a(b6, n2, n3);
                final double a20 = this.a(b6, n2, n3);
                final double a21 = this.a(b6, n2, n3);
                this.aw[n4] = this.if(this.F, a18, a20);
                this.aB[n4] = this.if(this.F, a19, a21);
                final double a22 = this.a(b6, n2, n3);
                final double a23 = this.a(b6, n2, n3);
                final double a24 = this.a(b6, n2, n3);
                final double a25 = this.a(b6, n2, n3);
                this.R[n4] = this.if(this.F, a22, a23);
                this.bt[n4] = this.if(this.F, a24, a25);
                break;
            }
            case 32:
            case 33: {
                final boolean b7 = n == 33;
                final double a26 = this.a(b7, n2, n3);
                final double a27 = this.a(b7, n2, n3);
                final double a28 = this.a(this.F, a26, a27);
                final double a29 = this.a(1, a26, a27);
                if (this.az) {
                    if (!this.af && a28 != Double.MIN_NORMAL) {
                        this.af = true;
                        this.aw[n4] = this.ax;
                        this.aB[n4] = a28;
                        this.bp[n4] = a29;
                        this.R[n4] = this.S;
                        this.bt[n4] = this.J;
                    }
                    else {
                        this.a(a28, a29, n4);
                    }
                    this.ak[n4] = this.a((long)this.j());
                    break;
                }
                this.af = false;
                this.aB[n4] = a28;
                this.bp[n4] = a29;
                this.ak[n4] = this.a((long)this.j());
                break;
            }
            case 30:
            case 31: {
                final boolean b8 = n == 31;
                final double a30 = this.a(b8, n2, n3);
                final double a31 = this.a(b8, n2, n3);
                final double a32 = this.a(b8, n2, n3);
                final double a33 = this.a(b8, n2, n3);
                this.aw[n4] = this.a(this.F, a30, a32);
                this.aB[n4] = this.a(this.F, a31, a33);
                this.bp[n4] = this.a(1, a31, a33);
                final double a34 = this.a(b8, n2, n3);
                final double a35 = this.a(b8, n2, n3);
                final double a36 = this.a(b8, n2, n3);
                final double a37 = this.a(b8, n2, n3);
                this.R[n4] = this.a(this.F, a34, a36);
                this.bt[n4] = this.a(this.F, a35, a37);
                this.ak[n4] = n5;
                break;
            }
        }
    }
    
    private synchronized double do(final double n, final double n2) {
        return (n > n2) ? n : n2;
    }
    
    private synchronized double if(final double n, final double n2) {
        double do1;
        if (n == Double.MIN_NORMAL || n2 == Double.MIN_NORMAL) {
            do1 = this.do(n, n2);
        }
        else {
            do1 = ((n < n2) ? n : n2);
        }
        return do1;
    }
    
    private synchronized void a(final double n, final int n2) {
        if (n == 0.0) {
            return;
        }
        this.aB[n2] = n;
        this.R[n2] = this.do(this.aw[n2], this.R[n2]);
        this.R[n2] = this.do(n, this.R[n2]);
        this.bt[n2] = this.if(this.aw[n2], this.bt[n2]);
        this.bt[n2] = this.if(n, this.bt[n2]);
    }
    
    private synchronized void a(final double n, final double n2, final int n3) {
        if (n == 0.0) {
            return;
        }
        this.aB[n3] = n;
        this.bp[n3] = n2;
        this.R[n3] = this.do(this.aw[n3], this.R[n3]);
        this.R[n3] = this.do(n, this.R[n3]);
        this.bt[n3] = this.if(this.aw[n3], this.bt[n3]);
        this.bt[n3] = this.if(n, this.bt[n3]);
    }
    
    private synchronized void a(final short n) throws EOFException, IOException {
        final String[] a = this.a(this.do((n == 4) ? 40 : this.c()));
        if (a[0].equalsIgnoreCase("ID") && !a[1].equals("")) {
            this.aW.if(a[1]);
        }
        if (a[0].equalsIgnoreCase("SEQ") && !a[1].equals("")) {
            this.aW.a(a[1]);
        }
        if (a[0].equalsIgnoreCase("PROVIDER") && !a[1].equals("")) {
            dk.midas.web.chart.applet.bh.int = a[1];
            this.aa = a[1];
        }
        if (a[0].equalsIgnoreCase("ACTION")) {
            if (a[1].equalsIgnoreCase("RELOAD")) {
                final int h = this.h();
                System.out.println("RELOAD : sleep " + h + " seconds");
                this.aN.do(h);
            }
            else if (a[1].equalsIgnoreCase("RESET")) {
                final int h2 = this.h();
                System.out.println("RESET in " + h2 + " seconds.");
                this.if(h2 * 1000);
            }
            else if (a[1].toUpperCase().startsWith("WAIT")) {
                final StringTokenizer stringTokenizer = new StringTokenizer(a[1], "|");
                stringTokenizer.nextToken();
                final int intValue = Integer.valueOf(stringTokenizer.nextToken());
                final String nextToken = stringTokenizer.nextToken();
                this.aN.do(nextToken);
                if (nextToken.startsWith("Unknown")) {
                    this.null();
                }
                else {
                    this.aU = ((intValue > 100) ? intValue : (intValue * 1000));
                }
            }
            else {
                System.out.println("Unknown ACTION = " + a[1]);
            }
        }
        if (a[0].equalsIgnoreCase("ERROR")) {
            this.aN.do(a[1]);
            this.null();
        }
        if (a[0].equalsIgnoreCase("DELAY")) {
            this.aV = Integer.parseInt(a[1]);
        }
    }
    
    private String do(final int n) throws EOFException, IOException {
        final byte[] array = new byte[n];
        this.T.readFully(array);
        final String s = new String(array, 0, n);
        return s.substring(0, s.indexOf(0));
    }
    
    private String[] a(final String s) {
        final String[] array = { "", "" };
        final int index = s.indexOf("=");
        if (index > 0) {
            array[0] = s.substring(0, index);
            array[1] = s.substring(index + 1);
        }
        return array;
    }
    
    private synchronized boolean a(final double n, final double n2, final short n3) throws EOFException, IOException, g {
        final short i = this.i();
        boolean af = this.af;
        if (i < 0) {
            this.az = true;
        }
        else {
            af = false;
        }
        final short bh = (short)Math.abs(i);
        this.bh = bh;
        final short j = this.i();
        Calendar.getInstance();
        long a = this.a((long)this.j());
        this.bm = ((n3 < 0) ? (-n3 * 1440 * 60) : (n3 * 60));
        if (this.aM) {
            final long n4 = a + this.bm;
            this.aC = ((n4 > this.aC) ? n4 : this.aC);
        }
        boolean b = false;
        for (short n5 = 0; n5 < j; ++n5) {
            switch (bh) {
                case 4: {
                    this.a(bh);
                    break;
                }
                case 6: {
                    this.a(bh);
                    break;
                }
                case 24: {
                    this.goto();
                    break;
                }
                case 34: {
                    this.else();
                    break;
                }
                case 35: {
                    this.au = this.int();
                    this.Y = this.int();
                    break;
                }
                case 36: {
                    for (byte c = this.c(), b2 = 0; b2 < c; ++b2) {
                        this.c();
                    }
                    break;
                }
                default: {
                    this.af = af;
                    this.a(bh, n, n2, this.aX - this.N, a, n3);
                    a -= this.bm;
                    ++this.N;
                    b = true;
                    break;
                }
            }
        }
        return b;
    }
    
    private double a(final int n, double n2, double n3) {
        if (n3 == Double.MIN_NORMAL && n2 == Double.MIN_NORMAL) {
            return Double.MIN_NORMAL;
        }
        if (n == 1) {
            if (n3 == Double.MIN_NORMAL) {
                return Double.MIN_NORMAL;
            }
            if (n2 == Double.MIN_NORMAL) {
                n2 = n3;
            }
        }
        else {
            if (n2 == Double.MIN_NORMAL) {
                return Double.MIN_NORMAL;
            }
            if (n3 == Double.MIN_NORMAL) {
                n3 = n2;
            }
        }
        final int ah = this.ah;
        final double n4 = 100000.0;
        this.aL = 0.0;
        this.bl = ((this.aE & 0x2) > 0);
        this.an = ((this.aE & 0x4) > 0);
        if (this.bl) {}
        if ((this.aE & 0x4) > 0) {}
        if ((this.aE & 0x1) > 0) {
            this.aL = this.bg;
        }
        else {
            final double a = this.a(n3 - n2, ah, this.bl, this.an);
            this.aL = this.a(a + this.a1, ah, this.bl, this.an);
            if (this.aL < this.bg) {
                this.aL = this.bg;
            }
            else if (this.aL > this.H && n4 <= this.a7) {
                if (a > this.H) {
                    this.aL = this.a(a, ah, this.bl, this.an);
                }
                else {
                    this.aL = this.H;
                }
            }
        }
        if (this.br != 0.0) {
            switch (this.D) {
                case 1: {
                    this.br = n2 * this.br / 100.0;
                    break;
                }
            }
            this.aL = this.a(this.aL + 2.0 * this.br, ah, this.bl, this.an);
        }
        final double a2 = this.a((n2 + n3 - this.aL) * 0.5, ah, this.bl, this.an);
        final double a3 = this.a(a2 + this.aL, ah, this.bl, this.an);
        return (n == 0) ? a2 : a3;
    }
    
    private double if(final int n, double n2, double n3) {
        int n4 = 0;
        if (n3 == Double.MIN_NORMAL || n2 == Double.MIN_NORMAL) {
            return Double.MIN_NORMAL;
        }
        final double n5 = n3 - n2;
        if (this.aG < 0.0) {
            this.aG = (n3 + n2) * 2.5E-4;
        }
        if (n5 < this.aG) {
            n2 = (n3 + n2 - this.aG) * 0.5;
            n3 = n2 + this.aG;
            n4 = ((n4 < 2) ? 2 : n4);
        }
        int n6;
        for (n6 = 0; n6 < this.W && n2 >= this.bu[n6]; ++n6) {}
        final int n7 = (n6 < this.W) ? n6 : (this.W - 1);
        final double n8 = this.ag[n7];
        final double n9 = this.Q[n7];
        final double n10 = this.aI[n7];
        double for1;
        double n12;
        if (n8 != 0.0) {
            final double n11 = n2 * n8 / 100.0;
            for1 = n3 + n11;
            n12 = n2 - n11;
            n4 = 3;
        }
        else {
            for1 = n3;
            n12 = n2;
        }
        if (n9 != 0.0) {
            if (for1 - n12 < n9) {
                final double n13 = (for1 + n12 - n9) * 0.5;
                if (n8 == 0.0 && this.G == 6) {
                    n12 = this.a(n13, 0.25);
                }
                else {
                    n12 = this.for(n13, this.ah);
                }
                for1 = n12 + n9;
                n4 = 0;
            }
            else if (n8 != 0.0 || this.G == 6) {}
        }
        if (n10 != 0.0) {
            n12 -= n10;
            for1 += n10;
            n4 = 3;
        }
        int n14;
        for (n14 = 0; n14 < this.aF && n12 > this.av[n14]; ++n14) {}
        final double n15 = this.aj[(n14 < this.aF) ? n14 : (this.aF - 1)];
        switch (n4) {
            case 1:
            case 3: {
                for1 = this.for(for1, n15);
                n12 = this.a(n12, n15);
                break;
            }
            case 2: {
                n12 = this.a(n12, n15);
                for1 = n12 + this.for(n9, n15);
                break;
            }
        }
        return (n == 0) ? n12 : for1;
    }
    
    private double do(final double n, int n2) {
        if (n2 < 0) {
            n2 = 0;
        }
        if (n2 > 6) {
            n2 = 6;
        }
        return (long)(n * this.O[n2] + 0.5) * this.aD[n2];
    }
    
    private double int(final double n, final double n2) {
        return (long)(n / n2 + 0.5) * n2;
    }
    
    private double a(final double n, final int n2, final boolean b, final boolean b2) {
        if (b2) {
            return n;
        }
        if (b) {
            return this.for(n, this.aD[n2] * 0.5);
        }
        return this.if(n, n2);
    }
    
    private double a(final double n, final int n2, final boolean b) {
        if (b) {
            return this.for(n, this.aD[n2] * 0.5);
        }
        return this.if(n, n2);
    }
    
    private double if(final double n, int n2) {
        if (n2 < 0) {
            n2 = 0;
        }
        if (n2 > 8) {
            n2 = 8;
        }
        return (long)(n * this.O[n2] + 0.5) * this.aD[n2];
    }
    
    private double for(final double n, int n2) {
        if (n2 < 0) {
            n2 = 0;
        }
        if (n2 > 6) {
            n2 = 6;
        }
        return (long)(n * this.O[n2] + 1.0E-9) * this.aD[n2];
    }
    
    private double for(final double n, final double n2) {
        return (long)(n / n2 + 0.5) * n2;
    }
    
    private double a(final double n, final double n2) {
        return (long)(n / n2 + 1.0E-9) * n2;
    }
    
    private void do() {
        System.out.println("Parse FX Ext header !!!!!");
        System.out.println("UserId = " + this.ad);
        System.out.println("mark up = " + this.br);
        System.out.println("numDec = " + this.ah);
        System.out.println("limitSoftMax = " + this.a7);
        System.out.println("spreadType = " + this.aE);
        System.out.println("spreadAddition = " + this.a1);
        System.out.println("minSpread = " + this.bg);
        System.out.println("softMaxSpread = " + this.H);
        if (this.aq > 0) {
            System.out.println("MarkUp Type = " + this.D);
        }
    }
    
    private void for() {
        System.out.println("Parse CFD header !!!!!");
        System.out.println("UserID : " + this.ad);
        System.out.println("ExchId = " + this.G);
        System.out.println("NumDec = " + this.ah);
        System.out.println("NumPriceSegs = " + this.W);
        for (int i = 0; i < 5; ++i) {
            if (i < this.W) {
                System.out.println("priceSeg[" + i + "] = " + this.bu[i]);
                System.out.println("spreadFactors[" + i + "] = " + this.ag[i]);
                System.out.println("minSpreads[" + i + "] = " + this.Q[i]);
                System.out.println("markups[" + i + "] = " + this.aI[i]);
            }
        }
        System.out.println("ticketFeeLimit = " + this.at);
        System.out.println("ticketFee = " + this.al);
        System.out.println("minSrcSpread = " + this.aG);
        System.out.println("NumRoundSegs = " + this.aF);
        for (int j = 0; j < 2; ++j) {
            if (j < this.aF) {
                System.out.println("roundSeg[" + j + "] = " + this.av[j]);
                System.out.println("pipSizes[" + j + "] = " + this.aj[j]);
            }
        }
    }
    
    private void goto() {
        try {
            this.G = this.j();
            this.ah = this.j();
            this.W = this.i();
            this.bu = new double[this.W];
            this.ag = new double[this.W];
            this.Q = new double[this.W];
            this.aI = new double[this.W];
            this.at = this.int();
            this.al = this.int();
            this.aG = this.int();
            this.aF = this.i();
            for (int i = 0; i < this.W; ++i) {
                this.bu[i] = this.int();
            }
            for (int j = 0; j < this.W; ++j) {
                this.ag[j] = this.int();
            }
            for (int k = 0; k < this.W; ++k) {
                this.Q[k] = this.int();
            }
            if (this.aq > 0) {
                for (int l = 0; l < this.W; ++l) {
                    this.aI[l] = this.int();
                }
            }
            this.av = new double[this.aF];
            this.aj = new double[this.aF];
            for (int n = 0; n < this.aF; ++n) {
                this.av[n] = this.int();
            }
            for (int n2 = 0; n2 < this.aF; ++n2) {
                this.aj[n2] = this.int();
            }
        }
        catch (Exception ex) {
            System.out.println("parseCFDHeaader exception " + ex);
        }
    }
    
    private void else() {
        try {
            this.br = this.int();
            this.ah = this.j();
            this.a7 = this.int();
            this.aE = this.i();
            this.a1 = this.int();
            this.bg = this.int();
            this.H = this.int();
            this.a9 = 0;
            if ((this.aE & 0x2) > 0 || (this.aE & 0x3) > 0 || (this.aE & 0x4) > 0 || (this.aE & 0x5) > 0) {
                this.a9 = 1;
            }
            this.D = 0;
            if (this.aq > 0) {
                this.D = this.i();
            }
        }
        catch (Exception ex) {
            System.out.println("parseFXExtHeaader exception " + ex);
        }
    }
    
    private void if(final int n) {
        this.aW = null;
        this.aU = n;
    }
    
    private int h() {
        return Math.abs(new Random(System.currentTimeMillis()).nextInt()) % 15;
    }
    
    public String new() {
        return this.aa;
    }
    
    private long a(final long n) {
        this.aP.setTime(new Date(n * 1000L));
        final TimeZone aj = this.aJ;
        final Calendar ap = this.aP;
        final Calendar ap2 = this.aP;
        final int value = ap.get(0);
        final Calendar ap3 = this.aP;
        final Calendar ap4 = this.aP;
        final int value2 = ap3.get(1);
        final Calendar ap5 = this.aP;
        final Calendar ap6 = this.aP;
        final int value3 = ap5.get(2);
        final Calendar ap7 = this.aP;
        final Calendar ap8 = this.aP;
        final int value4 = ap7.get(5);
        final Calendar ap9 = this.aP;
        final Calendar ap10 = this.aP;
        final int value5 = ap9.get(7);
        final Calendar ap11 = this.aP;
        final Calendar ap12 = this.aP;
        this.P = aj.getOffset(value, value2, value3, value4, value5, ap11.get(14));
        return n - this.P / 1000L;
    }
    
    private byte c() throws IOException {
        return this.T.readByte();
    }
    
    private short i() throws IOException, g {
        if (!this.a8) {
            return this.T.readShort();
        }
        final byte[] array = new byte[2];
        if (this.T.read(array) != 2) {
            throw new g("Error in readShort");
        }
        return this.if(array);
    }
    
    private int j() throws IOException, g {
        if (!this.a8) {
            return this.T.readInt();
        }
        final byte[] array = new byte[4];
        if (this.T.read(array) != 4) {
            throw new g("Error in readInt");
        }
        return this.a(array);
    }
    
    private double int() throws IOException, g {
        if (!this.a8) {
            return this.T.readDouble();
        }
        final byte[] array = new byte[8];
        if (this.T.read(array) != 8) {
            throw new g("Error in readDouble");
        }
        return this.do(array);
    }
    
    private short if(final byte[] array) {
        return (short)((array[1] & 0xFF) << 8 | (array[0] & 0xFF));
    }
    
    private int a(final byte[] array) {
        return (array[3] & 0xFF) << 24 | (array[2] & 0xFF) << 16 | (array[1] & 0xFF) << 8 | (array[0] & 0xFF);
    }
    
    private double do(final byte[] array) {
        long n = 0L;
        for (int i = 7; i > -1; --i) {
            n = (n << 8 | (array[i] & 0xFF));
        }
        return Double.longBitsToDouble(n);
    }
}
