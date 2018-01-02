// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.util.Vector;
import dk.midas.web.chart.applet.a.c;
import dk.midas.web.chart.a.b;
import dk.midas.web.chart.a.a;
import dk.midas.web.chart.a.f;

public class ay implements f, dk.midas.web.chart.a.a
{
    public static final Float bC;
    public static final ay bD;
    public static final ay bz;
    public static final ay bw;
    public static final ay bG;
    public static final ay by;
    public static final ay bF;
    public static final ay bA;
    public static final ay bI;
    public static final ay bx;
    public static final ay bE;
    public static final ay bv;
    private int[] bJ;
    private int bH;
    private int bB;
    
    public static ay int(final int n) {
        switch (n) {
            case 1:
            case 11: {
                return ay.bz;
            }
            case 2:
            case 12: {
                return ay.bw;
            }
            case 3:
            case 13: {
                return ay.bG;
            }
            case 5:
            case 15: {
                return ay.by;
            }
            case 7:
            case 17: {
                return ay.bF;
            }
            case 8:
            case 18: {
                return ay.bz;
            }
            case 9:
            case 19: {
                return ay.bF;
            }
            case 20:
            case 21:
            case 30:
            case 31: {
                return ay.bx;
            }
            case 26:
            case 27:
            case 32:
            case 33: {
                return ay.bE;
            }
            case 22:
            case 23: {
                return ay.bx;
            }
            default: {
                return ay.bD;
            }
        }
    }
    
    private ay(final int[] bj) {
        this.bB = 0;
        this.bJ = bj;
        this.bH = 0;
        for (int i = 0; i < this.bJ.length; ++i) {
            this.bH = Math.max(this.bH, this.bJ[i]);
        }
    }
    
    public a a(final b b) {
        final a a = new a();
        a.if = b.char();
        for (int i = 0; i < this.bJ.length; ++i) {
            final int n = this.bJ[i];
            a.a(n, b.for(n));
        }
        return a;
    }
    
    protected int k() {
        return (this.bH == 0) ? 0 : (this.bH + 1);
    }
    
    static {
        bC = new Float(Float.MIN_VALUE);
        bD = new ay(new int[0]);
        bz = new ay(new int[] { 1, 2, 3, 4, 5 });
        bw = new ay(new int[] { 2, 5 });
        bG = new ay(new int[] { 1, 2, 3, 4 });
        by = new ay(new int[] { 1, 2, 3, 4 });
        bF = new ay(new int[] { 1, 2, 3, 4, 5, 6 });
        bA = new ay(new int[] { 2, 5 });
        bI = new ay(new int[] { 2, 5, 6 });
        bx = new ay(new int[] { 1, 2, 7, 3, 4 });
        bE = new ay(new int[] { 1, 2, 7, 3, 4 });
        bv = new ay(new int[] { 2 });
    }
    
    public class a
    {
        private long[] if;
        private double[][] a;
        
        private a() {
            this.a = null;
            this.a = new double[ay.this.k()][];
        }
        
        protected void a(final int n, final double[] array) {
            this.a[n] = array;
        }
        
        public double[] if(final int n) {
            return this.a[n];
        }
        
        public boolean a(final int n) {
            return n < this.a.length && dk.midas.web.chart.applet.a.c.a(this.a[n]) > 0;
        }
        
        public Vector a(Vector vector) {
            if (vector == null) {
                vector = new Vector<Double>(this.if.length, 128);
            }
            for (int i = 0; i < this.if.length; ++i) {
                vector.addElement(new Double(this.if[i]));
            }
            return vector;
        }
        
        public bk a(bk bk, final int n) {
            if (this.a(n)) {
                final double[] if1 = this.if(n);
                if (bk == null) {
                    bk = new bk(if1.length, 128);
                }
                bk.a(if1);
            }
            return bk;
        }
        
        public Vector a(Vector if1, final int n) {
            final int size = if1.size();
            final int min = Math.min(n, this.if.length);
            int n2 = size - 1;
            boolean b = false;
            int n3 = 0;
            ay.this.bB = 0;
            while (!b && n2 >= 0) {
                final long n4 = (long)(double)if1.elementAt(n2);
                b = (if1.elementAt(n2) < this.if[0]);
                --n2;
                ++n3;
            }
            ay.this.bB = n - (n3 - 1);
            if1 = this.if(if1, ay.this.bB);
            for (int i = 0; i < min; ++i) {
                if1.setElementAt(new Double(this.if[i]), size - min + i);
            }
            return if1;
        }
        
        public bk a(final bk bk, final int n, final int n2) {
            if (this.a(n) && bk != null) {
                bk.a(n2, this.if(n), ay.this.bB);
            }
            return bk;
        }
        
        public Vector if(final Vector vector) {
            vector.setElementAt(new Double(this.if[0]), vector.size() - 1);
            return vector;
        }
        
        public bk if(final bk bk, final int n) {
            if (this.a(n) && bk != null) {
                bk.if(this.if(n));
            }
            return bk;
        }
        
        private Vector if(final Vector vector, final int n) {
            for (int i = 0; i < vector.size() - n; ++i) {
                vector.setElementAt(vector.elementAt(i + n), i);
            }
            return vector;
        }
    }
}
