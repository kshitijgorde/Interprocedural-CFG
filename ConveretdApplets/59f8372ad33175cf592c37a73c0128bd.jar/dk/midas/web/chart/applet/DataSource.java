// 
// Decompiled by Procyon v0.5.30
// 

package dk.midas.web.chart.applet;

import java.util.Stack;
import dk.midas.web.chart.a.b;
import java.util.Vector;
import dk.midas.web.chart.a.a;

public class DataSource implements Cloneable, dk.midas.web.chart.a.a
{
    static final int bN = 1200;
    static final int b1 = 1200;
    private dk.midas.web.chart.applet.a b3;
    private int bP;
    private int bM;
    private dk.midas.web.chart.applet.c.a bV;
    private dk.midas.web.chart.applet.b.a bT;
    private long bR;
    private long bZ;
    public Vector b6;
    public bk b5;
    public bk bL;
    public bk bX;
    public bk bW;
    public bk bY;
    public bk b4;
    public bk bS;
    private int bQ;
    private int b7;
    private b bU;
    i bO;
    boolean b2;
    private a9 b0;
    private a7 bK;
    
    public DataSource(final dk.midas.web.chart.applet.a b3, final dk.midas.web.chart.applet.c.a bv, final dk.midas.web.chart.applet.b.a bt, final long n) {
        this.b3 = null;
        this.bP = -1;
        this.bM = -1;
        this.bZ = 300L;
        this.b4 = null;
        this.bS = null;
        this.bQ = 3;
        this.b7 = 3;
        this.bU = new b();
        this.bO = new i();
        this.b2 = true;
        this.bK = new a7("DataSourceLock");
        this.b3 = b3;
        this.bV = bv;
        this.bT = bt;
        this.bZ = n;
        this.bR = n;
        this.b6 = new Vector(1200, 1200);
        if (this.bT.do()) {
            this.bW = new bk(1200, 1200);
        }
        else {
            this.b5 = new bk(1200, 128);
            this.bL = new bk(1200, 128);
            this.bX = new bk(1200, 128);
            this.bW = new bk(1200, 128);
        }
    }
    
    public void if(final aq aq) {
        this.t().a(aq);
    }
    
    public void a(final aq aq) {
        this.t().if(aq);
    }
    
    public void a(final dk.midas.web.chart.applet.c.a bv) {
        final dk.midas.web.chart.applet.c.a bv2 = this.bV;
        this.bV = bv;
        if (!bv2.equals(bv)) {
            this.s();
            this.t().a("instrument", bv2, bv);
        }
    }
    
    public dk.midas.web.chart.applet.c.a r() {
        return this.bV;
    }
    
    public void a(final dk.midas.web.chart.applet.b.a bt) {
        final dk.midas.web.chart.applet.b.a bt2 = this.bT;
        this.bT = bt;
        if (!bt2.equals(bt)) {
            this.s();
            this.t().a("timeScale", bt2, bt);
        }
    }
    
    public dk.midas.web.chart.applet.b.a C() {
        return this.bT;
    }
    
    public void for(final long n) {
        final long br = this.bR;
        this.bR = ((n <= 1L) ? this.w() : n);
        if (br != this.bR) {
            this.q();
            this.t().a("totalPointsNumber", new Long(br), new Long(n));
        }
    }
    
    public long B() {
        return this.bR;
    }
    
    public void do(final long bz) {
        this.bZ = bz;
    }
    
    public long w() {
        return this.bZ;
    }
    
    public int D() {
        return (this.b6 == null) ? 0 : this.b6.size();
    }
    
    public void byte(final int bm) {
        this.bM = bm;
    }
    
    public int m() {
        return this.bM;
    }
    
    public void a(final int bq, final int b7) {
        this.bQ = bq;
        this.b7 = b7;
    }
    
    public int A() {
        return this.b7;
    }
    
    public int p() {
        return this.bQ;
    }
    
    private void n() {
        for (int i = 0; i < this.bO.a.size(); ++i) {
            final aj if1 = this.bO.if(i);
            if (this.b6.size() > 0 && if1.long()) {
                if ((int)if1.char() > 0 && (int)if1.int() > 0 && (int)if1.char() < this.b6.size() && (int)if1.int() < this.b6.size()) {
                    if1.if((double)this.b6.elementAt((int)if1.char()));
                    if1.a((double)this.b6.elementAt((int)if1.int()));
                }
                else {
                    this.bO.a(i);
                }
            }
        }
    }
    
    public void if(final boolean b) {
        this.bK.do();
        try {
            if (b) {
                this.n();
            }
            else {
                this.bO.a();
            }
            this.b6.removeAllElements();
            if (this.bT.do()) {
                this.bW.new();
            }
            else {
                this.b5.new();
                this.bL.new();
                this.bX.new();
                this.bW.new();
            }
            if (this.bY != null) {
                this.bY.new();
                this.bY = null;
            }
            if (this.b4 != null) {
                this.b4.new();
                this.b4 = null;
            }
            if (this.bS != null) {
                this.bS.new();
                this.bS = null;
            }
            this.y().int();
        }
        finally {
            this.bK.a();
            this.t().a(1);
        }
    }
    
    public void q() {
        this.b3.a(this);
    }
    
    public void v() {
        this.b3.for(this.bP);
        this.bP = -1;
    }
    
    public void s() {
        this.v();
        this.if(false);
        this.q();
    }
    
    public void z() {
        this.b3.do();
    }
    
    public float a(final bk bk) {
        return bk.a(this.u(), this.l());
    }
    
    public float if(final bk bk) {
        return bk.if(this.u(), this.l());
    }
    
    public float a(final double n) {
        int l;
        double n2;
        for (l = this.l(), n2 = this.b6.elementAt(l); n < n2 && l > 0; --l, n2 = this.b6.elementAt(l)) {}
        return (n < n2) ? -1.0f : l;
    }
    
    public void a(final dk.midas.web.chart.a.b b, final int n, final int n2, final int n3, final int n4) {
        this.bK.do();
        try {
            this.a(n, n2);
            this.if(true);
            final ay.a a = ay.int(n3).a(b);
            this.b6 = a.a(this.b6);
            this.bX = a.a(this.bX, 1);
            this.bW = a.a(this.bW, 2);
            this.bL = a.a(this.bL, 3);
            this.b5 = a.a(this.b5, 4);
            this.b4 = a.a(this.b4, 5);
            this.bS = a.a(this.bS, 6);
            this.bY = a.a(this.bY, 7);
            this.y().if(n4 - (int)this.bR, n4 - 1);
            b.long();
            this.n();
            this.byte(b.if());
        }
        finally {
            this.bK.a();
            this.t().a(2, true, true);
        }
    }
    
    public void a(final dk.midas.web.chart.a.b b, final int n, final int n2) {
        this.bK.do();
        try {
            final ay.a a = ay.int(n).a(b);
            this.b6 = a.a(this.b6, n2);
            this.bX = a.a(this.bX, 1, n2);
            this.bW = a.a(this.bW, 2, n2);
            this.bL = a.a(this.bL, 3, n2);
            this.b5 = a.a(this.b5, 4, n2);
            this.b4 = a.a(this.b4, 5, n2);
            this.bS = a.a(this.bS, 6, n2);
            this.bY = a.a(this.bY, 7, n2);
            b.long();
            this.byte(b.if());
        }
        finally {
            this.bK.a();
            this.t().a(3, true, false);
        }
    }
    
    public void a(final dk.midas.web.chart.a.b b, final int n, final boolean b2) {
        int n2 = 1;
        this.bK.do();
        try {
            if (this.D() == 0) {
                return;
            }
            final ay.a a = ay.int(n).a(b);
            b.long();
            if (b2) {
                n2 = 1;
                this.b6 = a.a(this.b6, 1);
                this.bX = a.a(this.bX, 1, 1);
                this.bW = a.a(this.bW, 2, 1);
                this.bL = a.a(this.bL, 3, 1);
                this.b5 = a.a(this.b5, 4, 1);
                this.b4 = a.a(this.b4, 5, 1);
                this.bS = a.a(this.bS, 6, 1);
                this.bY = a.a(this.bY, 7, 1);
            }
            else {
                final float if1 = this.bW.if(this.D() - 1);
                this.b6 = a.if(this.b6);
                this.bX = a.if(this.bX, 1);
                this.bW = a.if(this.bW, 2);
                this.bL = a.if(this.bL, 3);
                this.b5 = a.if(this.b5, 4);
                this.b4 = a.if(this.b4, 5);
                this.bS = a.if(this.bS, 6);
                this.bY = a.if(this.bY, 7);
                n2 = ((this.bW.if(this.D() - 1) != if1) ? 1 : 0);
            }
        }
        finally {
            this.bK.a();
            if (n2 != 0) {
                this.t().a(3, false, b2);
            }
        }
    }
    
    public void a(final dk.midas.web.chart.a.b b, final int n) {
    }
    
    public String toString() {
        return "[" + this.r().if() + "]";
    }
    
    public int u() {
        return this.y().if();
    }
    
    public int l() {
        return this.y().for();
    }
    
    public double new(final int n) {
        return this.b6.elementAt(n);
    }
    
    public float case(final int n) {
        return this.bW.if(n);
    }
    
    public float char(final int n) {
        return this.bL.if(n);
    }
    
    public float try(final int n) {
        return this.b5.if(n);
    }
    
    public DataSource x() {
        this.bK.do();
        try {
            return (DataSource)this.clone();
        }
        catch (CloneNotSupportedException ex) {
            return this;
        }
        finally {
            this.bK.a();
        }
    }
    
    public a7 o() {
        return this.bK;
    }
    
    public b y() {
        return this.bU;
    }
    
    private synchronized a9 t() {
        if (this.b0 == null) {
            this.b0 = new a9(this);
        }
        return this.b0;
    }
    
    public class b
    {
        private Stack a;
        
        public b() {
            this.a = new Stack();
            this.int();
        }
        
        public synchronized void int() {
            this.a.removeAllElements();
            this.a.push(new a());
            DataSource.this.t().a(5);
        }
        
        public synchronized void if(final int n, final int n2) {
            this.a.removeAllElements();
            this.a.push(new a(n, n2));
            DataSource.this.t().a(5);
        }
        
        public synchronized int if() {
            return this.a.peek().a();
        }
        
        public synchronized int for() {
            return this.a.peek().if();
        }
        
        public synchronized int new() {
            return this.a.peek().do();
        }
        
        public synchronized void a(final int n, final int n2) {
            this.a(new a(n, n2));
        }
        
        protected synchronized void a(final a a) {
            this.a.push(a);
            DataSource.this.t().a(5);
        }
        
        public synchronized void try() {
            if (this.do()) {
                this.a.pop();
                DataSource.this.t().a(5);
            }
        }
        
        public synchronized boolean do() {
            return this.a.size() > 1;
        }
        
        public synchronized boolean a() {
            return DataSource.this.l() == DataSource.this.D() - 1;
        }
    }
    
    private class a
    {
        private int if;
        private int a;
        
        public a() {
            this.if = 0;
            this.a = DataSource.this.D() - 1;
        }
        
        public a(final int n, final int n2) {
            this.if = Math.max(0, Math.min(n, n2));
            this.a = Math.min(DataSource.this.D(), Math.max(n, n2));
        }
        
        public int a() {
            return this.if;
        }
        
        public int if() {
            return this.a;
        }
        
        public int do() {
            final int n = this.a - this.if + 1;
            return (n < 0) ? 0 : n;
        }
    }
}
