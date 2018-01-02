// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class bm extends a8
{
    protected int bg;
    protected int bf;
    protected int a4;
    protected int a3;
    protected int aH;
    protected int aJ;
    protected float be;
    protected float bd;
    protected float a1;
    protected float bk;
    protected float aY;
    protected boolean bc;
    protected boolean ba;
    protected boolean a8;
    a1 a9;
    protected int bh;
    protected int aU;
    protected int aQ;
    protected char[] a5;
    protected char[] bp;
    protected char[] aO;
    protected char[] a7;
    protected char[] bm;
    protected char[] a2;
    protected char[] bo;
    protected char[] aT;
    protected char[] bl;
    protected char[] aV;
    protected char[] bs;
    protected char[] bb;
    protected char[] aL;
    protected char[] aK;
    protected char[] aW;
    protected boolean bn;
    protected boolean aZ;
    protected boolean bi;
    protected boolean a0;
    protected ad aN;
    protected ae aS;
    protected int bq;
    protected static final int aM = 5;
    protected float aP;
    protected boolean a6;
    a5[] aR;
    int aX;
    int bj;
    char[] aI;
    v br;
    
    protected void d() {
        if (super.byte && !super.a) {
            super.b.a(this);
            super.a = true;
        }
    }
    
    public boolean a(final long n) {
        if (this.aS.k != 0) {
            this.aS = this.aS.try[0];
        }
        return false;
    }
    
    public void if() {
        super.if();
        if (this.aN != null) {
            this.aN.if();
        }
        this.br = null;
        if (this.aS != null) {
            this.aS.a();
        }
        if (this.aR != null) {
            for (int i = 0; i < this.aR.length; ++i) {
                if (this.aR[i] != null) {
                    this.aR[i].a();
                }
                this.aR[i] = null;
            }
            this.aR = null;
        }
        this.aI = null;
        this.a5 = null;
        this.bp = null;
        this.aO = null;
        this.a7 = null;
        this.bm = null;
        this.a2 = null;
        this.bo = null;
        this.aT = null;
        this.bl = null;
        this.aV = null;
        this.bs = null;
        this.bb = null;
        this.aL = null;
        this.aK = null;
        this.aW = null;
    }
    
    bm() {
        this.bg = 0;
        this.bf = 0;
        this.a4 = 0;
        this.a3 = 0;
        this.aH = -1;
        this.aJ = -1;
        this.aY = 0.0f;
        this.bc = false;
        this.ba = true;
        this.a9 = null;
        this.bh = -1;
        this.aU = -1711276288;
        this.aQ = -16711681;
        this.a5 = new char[1];
        this.bp = new char[1];
        this.aO = new char[1];
        this.a7 = new char[1];
        this.bm = new char[1];
        this.a2 = new char[1];
        this.bo = new char[1];
        this.aT = new char[1];
        this.bl = new char[1];
        this.aV = new char[1];
        this.bs = new char[1];
        this.bb = new char[1];
        this.aL = new char[] { 'x', '\0' };
        this.aK = new char[] { 'y', '\0' };
        this.aW = new char[1];
        this.bn = true;
        this.aZ = false;
        this.bi = false;
        this.a0 = false;
        this.aN = null;
        this.aS = null;
        this.bq = 0;
        this.a6 = true;
        this.aR = null;
        this.aX = 0;
        this.bj = -1;
        this.aI = new char[1];
        super.new = new a3();
    }
    
    void int(final long n) {
        super.goto = true;
        if (this.aN != null) {
            this.aN.if(n);
        }
    }
    
    void a(final ab ab) {
    }
    
    public void a(final ac ac, final a2 a2, final ae ae, final v v) {
    }
    
    public void f() {
    }
    
    public void a(final a2 a2, final ae ae) {
        for (int i = 0; i < a2.do; ++i) {
            if (a2.try[i].toLowerCase().compareTo("id") == 0) {
                super.f = (String.valueOf(a2.new[i]) + "\u0000").toCharArray();
            }
            else if (a2.try[i].toLowerCase().compareTo("posx") == 0) {
                this.bg = Integer.valueOf(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("posy") == 0) {
                this.bf = Integer.valueOf(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("x") == 0) {
                this.a4 = Integer.valueOf(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("y") == 0) {
                this.a3 = Integer.valueOf(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("width") == 0) {
                this.aH = Integer.valueOf(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("height") == 0) {
                this.aJ = Integer.valueOf(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("layer") == 0) {
                super.d = Integer.valueOf(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("visible") == 0) {
                super.for = Boolean.valueOf(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("checked") == 0) {
                this.a8 = Boolean.valueOf(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("blockmouse") == 0) {
                this.bn = Boolean.valueOf(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("north") == 0 || a2.try[i].toLowerCase().compareTo("horrizon") == 0) {
                this.aY = Float.valueOf(a2.new[i]) * this.br.G;
            }
            else if (a2.try[i].toLowerCase().compareTo("managetransparency") == 0) {
                this.a6 = Boolean.valueOf(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("color") == 0) {
                this.bh = ac.a(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("surfacecolor") == 0) {
                this.aU = ac.a(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("bordercolor") == 0) {
                this.aQ = ac.a(a2.new[i]);
            }
        }
        this.a();
        for (a2 a3 = a2.if; a3 != null; a3 = a3.for) {
            final String lowerCase = a3.a.toLowerCase();
            String do1;
            if (a3.case == null) {
                do1 = "";
            }
            else {
                do1 = a3.case.do;
            }
            if (lowerCase.compareTo("image") == 0) {
                String s = null;
                boolean l = true;
                for (int j = 0; j < a3.do; ++j) {
                    if (a3.try[j].compareTo("file") == 0) {
                        s = a3.new[j];
                    }
                    else if (a3.try[j].compareTo("id") == 0) {
                        super.f = a3.new[j].toCharArray();
                    }
                    else if (a3.try[j].compareTo("play") == 0 && a3.new[j].compareTo("false") == 0) {
                        l = false;
                    }
                }
                if (s != null) {
                    this.aN = new ad();
                    this.aS = super.b.A.a(s, ae, false, true, false);
                    this.aN.l = l;
                    this.br.a(this.aN);
                }
            }
            else if (do1 != null && do1.trim().length() != 0) {
                if ((String.valueOf(lowerCase) + "\u0000").toLowerCase().compareTo(new String(ac.c)) == 0) {
                    this.bp = do1.toCharArray();
                }
                else if ((String.valueOf(lowerCase) + "\u0000").toLowerCase().compareTo(new String(ac.x)) == 0) {
                    this.a5 = do1.toCharArray();
                }
                else if ((String.valueOf(lowerCase) + "\u0000").toLowerCase().compareTo(new String(ac.long)) == 0) {
                    this.aO = do1.toCharArray();
                }
                else if ((String.valueOf(lowerCase) + "\u0000").toLowerCase().compareTo(new String(ac.I)) == 0) {
                    this.a7 = do1.toCharArray();
                }
                else if ((String.valueOf(lowerCase) + "\u0000").toLowerCase().compareTo(new String(ac.D)) == 0) {
                    this.bm = do1.toCharArray();
                }
                else if ((String.valueOf(lowerCase) + "\u0000").toLowerCase().compareTo(new String(ac.al)) == 0) {
                    this.a2 = do1.toCharArray();
                }
                else if ((String.valueOf(lowerCase) + "\u0000").toLowerCase().compareTo(new String(ac.aj)) == 0) {
                    this.bo = do1.toCharArray();
                }
                else if ((String.valueOf(lowerCase) + "\u0000").toLowerCase().compareTo(new String(ac.Q)) == 0) {
                    this.aT = do1.toCharArray();
                }
                else if ((String.valueOf(lowerCase) + "\u0000").toLowerCase().compareTo(new String(ac.E)) == 0) {
                    this.bl = do1.toCharArray();
                }
                else if ((String.valueOf(lowerCase) + "\u0000").toLowerCase().compareTo(new String(ac.Z)) == 0) {
                    this.aV = do1.toCharArray();
                }
                else if ((String.valueOf(lowerCase) + "\u0000").toLowerCase().compareTo(new String(ac.X)) == 0) {
                    this.bs = do1.toCharArray();
                }
                else if ((String.valueOf(lowerCase) + "\u0000").toLowerCase().compareTo(new String(ac.o)) == 0) {
                    this.aW = (String.valueOf(do1) + "\u0000").toCharArray();
                }
            }
        }
    }
    
    public void if(final a2 a2) {
        for (a2 a3 = a2.if; a3 != null; a3 = a3.for) {
            if (a3.a.toLowerCase().compareTo("autopath") == 0) {
                for (int i = 0; i < a3.do; ++i) {
                    if (a3.try[i].toLowerCase().compareTo("play") == 0) {
                        if (a3.new[i].compareTo("false") == 0) {
                            this.ba = false;
                        }
                    }
                    else if (a3.try[i].toLowerCase().compareTo("first") == 0) {
                        this.aI = (String.valueOf(a3.new[i]) + "\u0000").toCharArray();
                    }
                }
                for (a2 a4 = a3.if; a4 != null; a4 = a4.for) {
                    this.do(a4);
                }
            }
        }
    }
    
    public void do(final a2 a2) {
        if (a2.a.toLowerCase().compareTo("moveto") == 0 || a2.a.toLowerCase().compareTo("move") == 0 || a2.a.toLowerCase().compareTo("sleep") == 0 || a2.a.toLowerCase().compareTo("action") == 0) {
            this.e();
            this.aR[this.aX] = new a5();
            if (a2.a.toLowerCase().compareTo("moveto") == 0) {
                this.aR[this.aX].do = 0;
            }
            else if (a2.a.toLowerCase().compareTo("move") == 0) {
                this.aR[this.aX].do = 1;
            }
            else if (a2.a.toLowerCase().compareTo("sleep") == 0) {
                this.aR[this.aX].do = 2;
            }
            else if (a2.a.toLowerCase().compareTo("action") == 0) {
                this.aR[this.aX].do = 3;
                this.aR[this.aX].h = (String.valueOf(a2.case.do) + "\u0000").toCharArray();
            }
            for (int i = 0; i < a2.do; ++i) {
                if (a2.try[i].toLowerCase().compareTo("posx") == 0) {
                    this.aR[this.aX].case = new Integer(a2.new[i]);
                    this.aR[this.aX].else = true;
                }
                else if (a2.try[i].toLowerCase().compareTo("posy") == 0) {
                    this.aR[this.aX].try = new Integer(a2.new[i]);
                    this.aR[this.aX].char = true;
                }
                else if (a2.try[i].toLowerCase().compareTo("width") == 0) {
                    this.aR[this.aX].d = new Integer(a2.new[i]);
                    this.aR[this.aX].j = true;
                }
                else if (a2.try[i].toLowerCase().compareTo("height") == 0) {
                    this.aR[this.aX].goto = new Integer(a2.new[i]);
                    this.aR[this.aX].if = true;
                }
                else if (a2.try[i].toLowerCase().compareTo("speed") == 0) {
                    this.aR[this.aX].i = new Float(a2.new[i]);
                    this.aR[this.aX].m = 1;
                }
                else if (a2.try[i].toLowerCase().compareTo("time") == 0) {
                    this.aR[this.aX].new = (long)(new Float(a2.new[i]) * 1000.0f);
                    this.aR[this.aX].m = 0;
                }
                else if (a2.try[i].toLowerCase().compareTo("amid") == 0 || a2.try[i].toLowerCase().compareTo("apid") == 0) {
                    this.aR[this.aX].long = (String.valueOf(a2.new[i]) + "\u0000").toCharArray();
                }
                else if (a2.try[i].toLowerCase().compareTo("next") == 0) {
                    this.aR[this.aX].void = (String.valueOf(a2.new[i]) + "\u0000").toCharArray();
                }
            }
            ++this.aX;
        }
    }
    
    boolean for(final long n) {
        if (this.ba) {
            if ((this.bj == -1 || (this.aR[this.bj].int != 0L && this.aR[this.bj].int < n)) && (this.bj == -1 || ((this.aR[this.bj].do != 0 || this.aR[this.bj].int == 1L) && this.aR[this.bj].do != 3))) {
                this.a(n, false);
                if (this.bj == -1) {
                    return false;
                }
            }
            if (this.aR[this.bj].do == 1) {
                final float n2 = 1000.0f / (n - this.aR[this.bj].q);
                this.aR[this.bj].q = n;
                if (this.aR[this.bj].else) {
                    this.be += this.aR[this.bj].case / n2;
                    this.bg = (int)this.be;
                }
                if (this.aR[this.bj].char) {
                    this.bd += this.aR[this.bj].try / n2;
                    this.bf = (int)this.bd;
                }
                if (this.aR[this.bj].j) {
                    this.a1 += this.aR[this.bj].d / n2;
                    this.aH = (int)this.a1;
                }
                if (this.aR[this.bj].if) {
                    this.bk += this.aR[this.bj].goto / n2;
                }
            }
            else if (this.aR[this.bj].do == 0) {
                float n3;
                if (this.aR[this.bj].new == 0L || this.aR[this.bj].int < n || n - this.aR[this.bj].q >= this.aR[this.bj].int - n) {
                    n3 = 0.0f;
                    this.aR[this.bj].int = 1L;
                }
                else {
                    n3 = (this.aR[this.bj].int - n) / (n - this.aR[this.bj].q);
                }
                this.aR[this.bj].q = n;
                if (this.aR[this.bj].else) {
                    if (n3 == 0.0f) {
                        this.be = this.aR[this.bj].case;
                    }
                    else {
                        this.be += (this.aR[this.bj].case - this.be) / n3;
                    }
                    this.bg = (int)this.be;
                }
                if (this.aR[this.bj].char) {
                    if (n3 == 0.0f) {
                        this.bd = this.aR[this.bj].try;
                    }
                    else {
                        this.bd += (this.aR[this.bj].try - this.bd) / n3;
                    }
                    this.bf = (int)this.bd;
                }
                if (this.aR[this.bj].j) {
                    if (n3 == 0.0f) {
                        this.a1 = this.aR[this.bj].d;
                    }
                    else {
                        this.a1 += (this.aR[this.bj].d - this.a1) / n3;
                    }
                    this.aH = (int)this.a1;
                }
                if (this.aR[this.bj].if) {
                    if (n3 == 0.0f) {
                        this.bk = this.aR[this.bj].goto;
                    }
                    else {
                        this.bk += (this.aR[this.bj].goto - this.bk) / n3;
                    }
                    this.aJ = (int)this.bk;
                }
            }
            else if (this.aR[this.bj].do == 3) {
                this.br.a(this.aR[this.bj].h);
                this.a(n, false);
            }
            return true;
        }
        return false;
    }
    
    private void a(final long q, final boolean b) {
        if (!b || this.bj == -1) {
            if (this.bj == -1) {
                if (g.a(this.aI) == 0) {
                    this.bj = 0;
                }
                else {
                    for (int i = 0; i < this.aX; ++i) {
                        if (g.if(this.aI, this.aR[i].long) == 0) {
                            this.bj = i;
                        }
                    }
                }
                if (this.bj >= this.aX || this.bj == -1) {
                    this.ba = false;
                    this.bj = -1;
                    return;
                }
            }
            else {
                if (g.if(this.aR[this.bj].void, ac.w) == 0) {
                    this.bj = -1;
                    this.ba = false;
                    return;
                }
                boolean b2 = false;
                for (int j = 0; j < this.aX; ++j) {
                    if (g.if(this.aR[this.bj].void, this.aR[j].long) == 0) {
                        this.bj = j;
                        b2 = true;
                        break;
                    }
                }
                if (!b2) {
                    this.bj = -1;
                    this.ba = false;
                    return;
                }
            }
        }
        this.aR[this.bj].q = q;
        if (this.aR[this.bj].do == 1) {
            if (this.aR[this.bj].new == 0L) {
                this.aR[this.bj].int = 0L;
                this.aR[this.bj].q = q - 40L;
            }
            else {
                this.aR[this.bj].int = q + this.aR[this.bj].new + 40L;
            }
        }
        else if (this.aR[this.bj].do == 0) {
            if (this.aR[this.bj].m == 1) {
                float n = 0.0f;
                if (this.aR[this.bj].else) {
                    n = Math.abs(this.bg - this.aR[this.bj].case);
                }
                if (this.aR[this.bj].char) {
                    n = Math.max(n, Math.abs(this.bf - this.aR[this.bj].try));
                }
                if (this.aR[this.bj].j) {
                    n = Math.max(n, Math.abs(this.aH - this.aR[this.bj].d));
                }
                if (this.aR[this.bj].if) {
                    n = Math.max(n, Math.abs(this.aJ - this.aR[this.bj].goto));
                }
                this.aR[this.bj].new = (long)(n * 1000.0f / this.aR[this.bj].i);
            }
            if (this.aR[this.bj].new == 0L) {
                this.aR[this.bj].int = 0L;
            }
            else {
                this.aR[this.bj].int = q + this.aR[this.bj].new + 40L;
            }
        }
        else {
            if (this.aR[this.bj].do == 3) {
                this.aR[this.bj].int = 1L;
                return;
            }
            this.aR[this.bj].int = q + this.aR[this.bj].new;
            return;
        }
        this.be = this.bg;
        this.bd = this.bf;
        this.a1 = this.aH;
        this.bk = this.aJ;
    }
    
    private void e() {
        if (this.aR == null || this.aR.length == this.aX) {
            final a5[] ar = new a5[this.aX + 10];
            for (int i = 0; i < this.aX; ++i) {
                ar[i] = this.aR[i];
            }
            this.aR = ar;
        }
    }
    
    public void a(final char[] array, final a3 a3) {
        if (g.do(array, ac.ab) == 0) {
            this.bg = al.if(a3);
        }
        else if (g.do(array, ac.aa) == 0) {
            this.bf = al.if(a3);
        }
        else if (g.do(array, ac.t) == 0) {
            this.aH = al.if(a3);
        }
        else if (g.do(array, ac.aq) == 0) {
            this.aJ = al.if(a3);
        }
        else if (g.do(array, this.aL) == 0) {
            this.a4 = al.if(a3);
        }
        else if (g.do(array, this.aK) == 0) {
            this.a3 = al.if(a3);
        }
        else if (g.do(array, ac.aw) == 0) {
            super.d = al.if(a3);
            super.b.if(this.br.I, 0, this.br.E - 1);
        }
        else if (g.do(array, ac.P) == 0) {
            super.for = al.a(a3);
        }
        else if (g.do(array, ac.at) == 0) {
            this.a6 = al.a(a3);
        }
        else if (g.do(array, ac.h) == 0) {
            this.bn = al.a(a3);
        }
        else if (g.do(array, ac.char) == 0) {
            this.a8 = al.a(a3);
            if (this.a8) {
                this.bq = this.aN.j / 5;
            }
            else {
                this.bq = 0;
            }
        }
        else if (g.do(array, ac.ac) == 0) {
            this.ba = al.a(a3);
            if (this.ba) {
                this.a(System.currentTimeMillis(), true);
            }
        }
        else if (g.do(array, ac.n) == 0) {
            if (a3.char != 4) {
                return;
            }
            for (int i = 0; i < this.aX; ++i) {
                if (g.if(a3.int, this.aR[i].long) == 0) {
                    this.bj = i;
                    this.a(System.currentTimeMillis(), true);
                    this.ba = true;
                    return;
                }
            }
        }
        else if (g.do(array, ac.B) == 0) {
            int n = 0;
            for (int j = 0; j < this.aX; ++j) {
                if (n != 0) {
                    this.aR[j - 1] = this.aR[j];
                }
                else if (g.if(a3.int, this.aR[j].long) == 0) {
                    n = 1;
                }
            }
            if (n != 0) {
                --this.aX;
            }
        }
        else if (g.do(array, ac.y) == 0) {
            final bf bf = new bf();
            try {
                y.a(bf, a3.int, g.a(a3.int));
                this.do(bf.do);
            }
            catch (Exception ex) {}
        }
        else if (g.do(array, ac.x) == 0) {
            if (a3.char == 4) {
                this.a5 = a3.int;
            }
        }
        else if (g.do(array, ac.c) == 0) {
            if (a3.char == 4) {
                this.bp = a3.int;
            }
        }
        else if (g.do(array, ac.long) == 0) {
            if (a3.char == 4) {
                this.aO = a3.int;
            }
        }
        else if (g.do(array, ac.I) == 0) {
            if (a3.char == 4) {
                this.a7 = a3.int;
            }
        }
        else if (g.do(array, ac.D) == 0) {
            if (a3.char == 4) {
                this.bm = a3.int;
            }
        }
        else if (g.do(array, ac.al) == 0) {
            if (a3.char == 4) {
                this.a2 = a3.int;
            }
        }
        else if (g.do(array, ac.aj) == 0) {
            if (a3.char == 4) {
                this.bo = a3.int;
            }
        }
        else if (g.do(array, ac.Q) == 0) {
            if (a3.char == 4) {
                this.aT = a3.int;
            }
        }
        else if (g.do(array, ac.E) == 0) {
            if (a3.char == 4) {
                this.bl = a3.int;
            }
        }
        else if (g.do(array, ac.Z) == 0) {
            if (a3.char == 4) {
                this.aV = a3.int;
            }
        }
        else if (g.do(array, ac.ai) == 0) {
            if (a3.char == 2) {
                this.bh = (int)a3.case;
            }
        }
        else if (g.do(array, ac.R) == 0) {
            if (a3.char == 2) {
                this.aU = (int)a3.case;
            }
        }
        else if (g.do(array, ac.l) == 0) {
            if (a3.char == 2) {
                this.aQ = (int)a3.case;
            }
        }
        else if (g.do(array, ac.o) == 0) {
            if (a3.char == 4) {
                this.aW = a3.int;
            }
        }
        else if (g.do(array, ac.ae) == 0) {
            if (a3.char == 2) {
                this.aY = a3.case * this.br.G;
            }
            else if (a3.char == 3) {
                this.aY = (float)(a3.else * this.br.G);
            }
        }
        super.do = true;
    }
    
    public a3 a(final char[] array) {
        if (g.do(array, ac.ab) == 0) {
            super.new.char = 2;
            super.new.case = this.bg;
            return super.new;
        }
        if (g.do(array, ac.aa) == 0) {
            super.new.char = 2;
            super.new.case = this.bf;
            return super.new;
        }
        if (g.do(array, ac.t) == 0) {
            super.new.char = 2;
            super.new.case = this.aH;
            return super.new;
        }
        if (g.do(array, ac.aq) == 0) {
            super.new.char = 2;
            super.new.case = this.aJ;
            return super.new;
        }
        if (g.do(array, this.aL) == 0) {
            super.new.char = 2;
            super.new.case = this.a4;
            return super.new;
        }
        if (g.do(array, this.aK) == 0) {
            super.new.char = 2;
            super.new.case = this.a4;
            return super.new;
        }
        if (g.do(array, ac.aw) == 0) {
            super.new.char = 2;
            super.new.case = super.d;
            return super.new;
        }
        if (g.do(array, ac.P) == 0) {
            super.new.char = 1;
            super.new.long = super.for;
            return super.new;
        }
        if (g.do(array, ac.at) == 0) {
            super.new.char = 1;
            super.new.long = this.a6;
            return super.new;
        }
        if (g.do(array, ac.h) == 0) {
            super.new.char = 1;
            super.new.long = this.bn;
            return super.new;
        }
        if (g.do(array, ac.ac) == 0) {
            super.new.char = 1;
            super.new.long = this.ba;
            return super.new;
        }
        if (g.do(array, ac.char) == 0) {
            super.new.char = 1;
            super.new.long = this.a8;
            return super.new;
        }
        if (g.do(array, ac.n) == 0) {
            if (this.aX == 0) {
                super.new.char = 1;
                super.new.long = false;
                return super.new;
            }
            super.new.char = 4;
            if (this.bj != -1) {
                super.new.int = this.aR[this.bj].long;
            }
            else if (g.if(this.aI, ac.w) != 0) {
                super.new.int = this.aI;
            }
            else {
                super.new.int = this.aR[0].long;
            }
            return super.new;
        }
        else {
            if (g.do(array, ac.x) == 0) {
                super.new.char = 4;
                super.new.int = this.a5;
                return super.new;
            }
            if (g.do(array, ac.c) == 0) {
                super.new.char = 4;
                super.new.int = this.bp;
                return super.new;
            }
            if (g.do(array, ac.long) == 0) {
                super.new.char = 4;
                super.new.int = this.aO;
                return super.new;
            }
            if (g.do(array, ac.I) == 0) {
                super.new.char = 4;
                super.new.int = this.a7;
                return super.new;
            }
            if (g.do(array, ac.D) == 0) {
                super.new.char = 4;
                super.new.int = this.bm;
                return super.new;
            }
            if (g.do(array, ac.al) == 0) {
                super.new.char = 4;
                super.new.int = this.a2;
                return super.new;
            }
            if (g.do(array, ac.aj) == 0) {
                super.new.char = 4;
                super.new.int = this.bo;
                return super.new;
            }
            if (g.do(array, ac.Q) == 0) {
                super.new.char = 4;
                super.new.int = this.aT;
                return super.new;
            }
            if (g.do(array, ac.E) == 0) {
                super.new.char = 4;
                super.new.int = this.bl;
                return super.new;
            }
            if (g.do(array, ac.Z) == 0) {
                super.new.char = 4;
                super.new.int = this.aV;
                return super.new;
            }
            if (g.do(array, ac.X) == 0) {
                super.new.char = 4;
                super.new.int = this.bs;
                return super.new;
            }
            if (g.do(array, ac.do) == 0) {
                super.new.char = 4;
                super.new.int = super.case;
                return super.new;
            }
            if (g.do(array, ac.s) == 0) {
                super.new.char = 5;
                if (this.aN != null) {
                    super.new.goto = 1;
                    (super.new.a = new a3[1])[0] = new a3();
                    super.new.a[0].char = 4;
                    super.new.a[0].int = this.aN.f;
                }
                else {
                    super.new.goto = 0;
                }
                return super.new;
            }
            if (g.do(array, ac.ai) == 0) {
                super.new.char = 2;
                super.new.case = this.bh;
                return super.new;
            }
            if (g.do(array, ac.R) == 0) {
                super.new.char = 2;
                super.new.case = this.aU;
                return super.new;
            }
            if (g.do(array, ac.l) == 0) {
                super.new.char = 2;
                super.new.case = this.aQ;
                return super.new;
            }
            if (g.do(array, ac.o) == 0) {
                super.new.char = 4;
                super.new.int = this.aW;
                return super.new;
            }
            if (g.do(array, ac.ae) == 0) {
                super.new.char = 3;
                super.new.else = this.aY / this.br.G;
                return super.new;
            }
            return ac.a(super.new);
        }
    }
}
