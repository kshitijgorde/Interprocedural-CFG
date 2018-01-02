// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class bm extends a8
{
    protected int bh;
    protected int bg;
    protected int a5;
    protected int a4;
    protected int aI;
    protected int aK;
    protected float bf;
    protected float be;
    protected float a2;
    protected float bl;
    protected float aZ;
    protected boolean bd;
    protected boolean bb;
    protected boolean a9;
    a1 ba;
    protected int bi;
    protected int aV;
    protected int aR;
    protected char[] a6;
    protected char[] bq;
    protected char[] aP;
    protected char[] a8;
    protected char[] bn;
    protected char[] a3;
    protected char[] bp;
    protected char[] aU;
    protected char[] bm;
    protected char[] aW;
    protected char[] bt;
    protected char[] bc;
    protected char[] aM;
    protected char[] aL;
    protected char[] aX;
    protected boolean bo;
    protected boolean a0;
    protected boolean bj;
    protected boolean a1;
    protected ad aO;
    protected ae aT;
    protected int br;
    protected static final int aN = 5;
    protected float aQ;
    protected boolean a7;
    a5[] aS;
    int aY;
    int bk;
    char[] aJ;
    v bs;
    
    protected void void() {
        if (super.byte && !super.a) {
            super.b.a(this);
            super.a = true;
        }
    }
    
    public boolean a(final long n) {
        if (this.aT.k != 0) {
            this.aT = this.aT.try[0];
        }
        return false;
    }
    
    public void if() {
        super.if();
        if (this.aO != null) {
            this.aO.if();
        }
        this.bs = null;
        if (this.aT != null) {
            this.aT.a();
        }
        if (this.aS != null) {
            for (int i = 0; i < this.aS.length; ++i) {
                if (this.aS[i] != null) {
                    this.aS[i].a();
                }
                this.aS[i] = null;
            }
            this.aS = null;
        }
        this.aJ = null;
        this.a6 = null;
        this.bq = null;
        this.aP = null;
        this.a8 = null;
        this.bn = null;
        this.a3 = null;
        this.bp = null;
        this.aU = null;
        this.bm = null;
        this.aW = null;
        this.bt = null;
        this.bc = null;
        this.aM = null;
        this.aL = null;
        this.aX = null;
    }
    
    bm() {
        this.bh = 0;
        this.bg = 0;
        this.a5 = 0;
        this.a4 = 0;
        this.aI = -1;
        this.aK = -1;
        this.aZ = 0.0f;
        this.bd = false;
        this.bb = true;
        this.ba = null;
        this.bi = -1;
        this.aV = -1711276288;
        this.aR = -16711681;
        this.a6 = new char[1];
        this.bq = new char[1];
        this.aP = new char[1];
        this.a8 = new char[1];
        this.bn = new char[1];
        this.a3 = new char[1];
        this.bp = new char[1];
        this.aU = new char[1];
        this.bm = new char[1];
        this.aW = new char[1];
        this.bt = new char[1];
        this.bc = new char[1];
        this.aM = new char[] { 'x', '\0' };
        this.aL = new char[] { 'y', '\0' };
        this.aX = new char[1];
        this.bo = true;
        this.a0 = false;
        this.bj = false;
        this.a1 = false;
        this.aO = null;
        this.aT = null;
        this.br = 0;
        this.a7 = true;
        this.aS = null;
        this.aY = 0;
        this.bk = -1;
        this.aJ = new char[1];
        super.new = new a3();
    }
    
    void int(final long n) {
        super.goto = true;
        if (this.aO != null) {
            this.aO.if(n);
        }
    }
    
    void a(final ab ab) {
    }
    
    public void a(final ac ac, final a2 a2, final ae ae, final v v) {
    }
    
    public void c() {
    }
    
    public void a(final a2 a2, final ae ae) {
        for (int i = 0; i < a2.do; ++i) {
            if (a2.try[i].toLowerCase().compareTo("id") == 0) {
                super.f = (String.valueOf(a2.new[i]) + "\u0000").toCharArray();
            }
            else if (a2.try[i].toLowerCase().compareTo("posx") == 0) {
                this.bh = Integer.valueOf(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("posy") == 0) {
                this.bg = Integer.valueOf(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("x") == 0) {
                this.a5 = Integer.valueOf(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("y") == 0) {
                this.a4 = Integer.valueOf(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("width") == 0) {
                this.aI = Integer.valueOf(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("height") == 0) {
                this.aK = Integer.valueOf(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("layer") == 0) {
                super.d = Integer.valueOf(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("visible") == 0) {
                super.for = Boolean.valueOf(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("checked") == 0) {
                this.a9 = Boolean.valueOf(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("blockmouse") == 0) {
                this.bo = Boolean.valueOf(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("north") == 0 || a2.try[i].toLowerCase().compareTo("horrizon") == 0) {
                this.aZ = Float.valueOf(a2.new[i]) * this.bs.G;
            }
            else if (a2.try[i].toLowerCase().compareTo("managetransparency") == 0) {
                this.a7 = Boolean.valueOf(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("color") == 0) {
                this.bi = ac.a(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("surfacecolor") == 0) {
                this.aV = ac.a(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("bordercolor") == 0) {
                this.aR = ac.a(a2.new[i]);
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
                String s2 = null;
                boolean t = true;
                for (int j = 0; j < a3.do; ++j) {
                    if (a3.try[j].compareTo("file") == 0) {
                        s = a3.new[j];
                    }
                    else if (a3.try[j].compareTo("id") == 0) {
                        s2 = a3.new[j];
                    }
                    else if (a3.try[j].toLowerCase().compareTo("play") == 0 && a3.new[j].toLowerCase().compareTo("false") == 0) {
                        t = false;
                    }
                }
                if (s != null) {
                    this.aO = new ad();
                    if (s2 != null) {
                        this.aO.f = s2.toCharArray();
                    }
                    this.aT = super.b.B.a(s, ae, false, true, false);
                    this.aO.t = t;
                    this.bs.a(this.aO);
                }
            }
            else if (do1 != null && do1.trim().length() != 0) {
                if ((String.valueOf(lowerCase) + "\u0000").toLowerCase().compareTo(new String(ac.d)) == 0) {
                    this.bq = do1.toCharArray();
                }
                else if ((String.valueOf(lowerCase) + "\u0000").toLowerCase().compareTo(new String(ac.y)) == 0) {
                    this.a6 = do1.toCharArray();
                }
                else if ((String.valueOf(lowerCase) + "\u0000").toLowerCase().compareTo(new String(ac.long)) == 0) {
                    this.aP = do1.toCharArray();
                }
                else if ((String.valueOf(lowerCase) + "\u0000").toLowerCase().compareTo(new String(ac.J)) == 0) {
                    this.a8 = do1.toCharArray();
                }
                else if ((String.valueOf(lowerCase) + "\u0000").toLowerCase().compareTo(new String(ac.E)) == 0) {
                    this.bn = do1.toCharArray();
                }
                else if ((String.valueOf(lowerCase) + "\u0000").toLowerCase().compareTo(new String(ac.an)) == 0) {
                    this.a3 = do1.toCharArray();
                }
                else if ((String.valueOf(lowerCase) + "\u0000").toLowerCase().compareTo(new String(ac.al)) == 0) {
                    this.bp = do1.toCharArray();
                }
                else if ((String.valueOf(lowerCase) + "\u0000").toLowerCase().compareTo(new String(ac.R)) == 0) {
                    this.aU = do1.toCharArray();
                }
                else if ((String.valueOf(lowerCase) + "\u0000").toLowerCase().compareTo(new String(ac.F)) == 0) {
                    this.bm = do1.toCharArray();
                }
                else if ((String.valueOf(lowerCase) + "\u0000").toLowerCase().compareTo(new String(ac.ab)) == 0) {
                    this.aW = do1.toCharArray();
                }
                else if ((String.valueOf(lowerCase) + "\u0000").toLowerCase().compareTo(new String(ac.Z)) == 0) {
                    this.bt = do1.toCharArray();
                }
                else if ((String.valueOf(lowerCase) + "\u0000").toLowerCase().compareTo(new String(ac.o)) == 0) {
                    this.aX = (String.valueOf(do1) + "\u0000").toCharArray();
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
                            this.bb = false;
                        }
                    }
                    else if (a3.try[i].toLowerCase().compareTo("first") == 0) {
                        this.aJ = (String.valueOf(a3.new[i]) + "\u0000").toCharArray();
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
            this.b();
            this.aS[this.aY] = new a5();
            if (a2.a.toLowerCase().compareTo("moveto") == 0) {
                this.aS[this.aY].do = 0;
            }
            else if (a2.a.toLowerCase().compareTo("move") == 0) {
                this.aS[this.aY].do = 1;
            }
            else if (a2.a.toLowerCase().compareTo("sleep") == 0) {
                this.aS[this.aY].do = 2;
            }
            else if (a2.a.toLowerCase().compareTo("action") == 0 && a2.case != null) {
                this.aS[this.aY].do = 3;
                this.aS[this.aY].h = (String.valueOf(a2.case.do) + "\u0000").toCharArray();
            }
            for (int i = 0; i < a2.do; ++i) {
                if (a2.try[i].toLowerCase().compareTo("posx") == 0) {
                    this.aS[this.aY].case = new Integer(a2.new[i]);
                    this.aS[this.aY].else = true;
                }
                else if (a2.try[i].toLowerCase().compareTo("posy") == 0) {
                    this.aS[this.aY].try = new Integer(a2.new[i]);
                    this.aS[this.aY].char = true;
                }
                else if (a2.try[i].toLowerCase().compareTo("width") == 0) {
                    this.aS[this.aY].d = new Integer(a2.new[i]);
                    this.aS[this.aY].j = true;
                }
                else if (a2.try[i].toLowerCase().compareTo("height") == 0) {
                    this.aS[this.aY].goto = new Integer(a2.new[i]);
                    this.aS[this.aY].if = true;
                }
                else if (a2.try[i].toLowerCase().compareTo("speed") == 0) {
                    this.aS[this.aY].i = new Float(a2.new[i]);
                    this.aS[this.aY].m = 1;
                }
                else if (a2.try[i].toLowerCase().compareTo("time") == 0) {
                    this.aS[this.aY].new = (long)(new Float(a2.new[i]) * 1000.0f);
                    this.aS[this.aY].m = 0;
                }
                else if (a2.try[i].toLowerCase().compareTo("amid") == 0 || a2.try[i].toLowerCase().compareTo("apid") == 0) {
                    this.aS[this.aY].long = (String.valueOf(a2.new[i]) + "\u0000").toCharArray();
                }
                else if (a2.try[i].toLowerCase().compareTo("next") == 0) {
                    this.aS[this.aY].void = (String.valueOf(a2.new[i]) + "\u0000").toCharArray();
                }
            }
            ++this.aY;
        }
    }
    
    boolean for(final long n) {
        if (this.bb) {
            if ((this.bk == -1 || (this.aS[this.bk].int != 0L && this.aS[this.bk].int < n)) && (this.bk == -1 || ((this.aS[this.bk].do != 0 || this.aS[this.bk].int == 1L) && this.aS[this.bk].do != 3))) {
                this.a(n, false);
                if (this.bk == -1) {
                    return false;
                }
            }
            if (this.aS[this.bk].do == 1) {
                final float n2 = 1000.0f / (n - this.aS[this.bk].q);
                this.aS[this.bk].q = n;
                if (this.aS[this.bk].else) {
                    this.bf += this.aS[this.bk].case / n2;
                    this.bh = (int)this.bf;
                }
                if (this.aS[this.bk].char) {
                    this.be += this.aS[this.bk].try / n2;
                    this.bg = (int)this.be;
                }
                if (this.aS[this.bk].j) {
                    this.a2 += this.aS[this.bk].d / n2;
                    this.aI = (int)this.a2;
                }
                if (this.aS[this.bk].if) {
                    this.bl += this.aS[this.bk].goto / n2;
                }
            }
            else if (this.aS[this.bk].do == 0) {
                float n3;
                if (this.aS[this.bk].new == 0L || this.aS[this.bk].int < n || n - this.aS[this.bk].q >= this.aS[this.bk].int - n) {
                    n3 = 0.0f;
                    this.aS[this.bk].int = 1L;
                }
                else {
                    n3 = (this.aS[this.bk].int - n) / (n - this.aS[this.bk].q);
                }
                this.aS[this.bk].q = n;
                if (this.aS[this.bk].else) {
                    if (n3 == 0.0f) {
                        this.bf = this.aS[this.bk].case;
                    }
                    else {
                        this.bf += (this.aS[this.bk].case - this.bf) / n3;
                    }
                    this.bh = (int)this.bf;
                }
                if (this.aS[this.bk].char) {
                    if (n3 == 0.0f) {
                        this.be = this.aS[this.bk].try;
                    }
                    else {
                        this.be += (this.aS[this.bk].try - this.be) / n3;
                    }
                    this.bg = (int)this.be;
                }
                if (this.aS[this.bk].j) {
                    if (n3 == 0.0f) {
                        this.a2 = this.aS[this.bk].d;
                    }
                    else {
                        this.a2 += (this.aS[this.bk].d - this.a2) / n3;
                    }
                    this.aI = (int)this.a2;
                }
                if (this.aS[this.bk].if) {
                    if (n3 == 0.0f) {
                        this.bl = this.aS[this.bk].goto;
                    }
                    else {
                        this.bl += (this.aS[this.bk].goto - this.bl) / n3;
                    }
                    this.aK = (int)this.bl;
                }
            }
            else if (this.aS[this.bk].do == 3) {
                this.bs.a(this.aS[this.bk].h);
                this.a(n, false);
            }
            return true;
        }
        return false;
    }
    
    private void a(final long q, final boolean b) {
        if (!b || this.bk == -1) {
            if (this.bk == -1) {
                if (g.a(this.aJ) == 0) {
                    this.bk = 0;
                }
                else {
                    for (int i = 0; i < this.aY; ++i) {
                        if (g.if(this.aJ, this.aS[i].long) == 0) {
                            this.bk = i;
                        }
                    }
                }
                if (this.bk >= this.aY || this.bk == -1) {
                    this.bb = false;
                    this.bk = -1;
                    return;
                }
            }
            else {
                if (g.if(this.aS[this.bk].void, ac.x) == 0) {
                    this.bk = -1;
                    this.bb = false;
                    return;
                }
                boolean b2 = false;
                for (int j = 0; j < this.aY; ++j) {
                    if (g.if(this.aS[this.bk].void, this.aS[j].long) == 0) {
                        this.bk = j;
                        b2 = true;
                        break;
                    }
                }
                if (!b2) {
                    this.bk = -1;
                    this.bb = false;
                    return;
                }
            }
        }
        this.aS[this.bk].q = q;
        if (this.aS[this.bk].do == 1) {
            if (this.aS[this.bk].new == 0L) {
                this.aS[this.bk].int = 0L;
                this.aS[this.bk].q = q - 40L;
            }
            else {
                this.aS[this.bk].int = q + this.aS[this.bk].new + 40L;
            }
        }
        else if (this.aS[this.bk].do == 0) {
            if (this.aS[this.bk].m == 1) {
                float n = 0.0f;
                if (this.aS[this.bk].else) {
                    n = Math.abs(this.bh - this.aS[this.bk].case);
                }
                if (this.aS[this.bk].char) {
                    n = Math.max(n, Math.abs(this.bg - this.aS[this.bk].try));
                }
                if (this.aS[this.bk].j) {
                    n = Math.max(n, Math.abs(this.aI - this.aS[this.bk].d));
                }
                if (this.aS[this.bk].if) {
                    n = Math.max(n, Math.abs(this.aK - this.aS[this.bk].goto));
                }
                this.aS[this.bk].new = (long)(n * 1000.0f / this.aS[this.bk].i);
            }
            if (this.aS[this.bk].new == 0L) {
                this.aS[this.bk].int = 0L;
            }
            else {
                this.aS[this.bk].int = q + this.aS[this.bk].new + 40L;
            }
        }
        else {
            if (this.aS[this.bk].do == 3) {
                this.aS[this.bk].int = 1L;
                return;
            }
            this.aS[this.bk].int = q + this.aS[this.bk].new;
            return;
        }
        this.bf = this.bh;
        this.be = this.bg;
        this.a2 = this.aI;
        this.bl = this.aK;
    }
    
    private void b() {
        if (this.aS == null || this.aS.length == this.aY) {
            final a5[] as = new a5[this.aY + 10];
            for (int i = 0; i < this.aY; ++i) {
                as[i] = this.aS[i];
            }
            this.aS = as;
        }
    }
    
    public void a(final char[] array, final a3 a3) {
        if (g.do(array, ac.ad) == 0) {
            this.bh = al.if(a3);
        }
        else if (g.do(array, ac.ac) == 0) {
            this.bg = al.if(a3);
        }
        else if (g.do(array, ac.u) == 0) {
            this.aI = al.if(a3);
        }
        else if (g.do(array, ac.as) == 0) {
            this.aK = al.if(a3);
        }
        else if (g.do(array, this.aM) == 0) {
            this.a5 = al.if(a3);
        }
        else if (g.do(array, this.aL) == 0) {
            this.a4 = al.if(a3);
        }
        else if (g.do(array, ac.ay) == 0) {
            super.d = al.if(a3);
            super.b.if(this.bs.K, 0, this.bs.F - 1);
        }
        else if (g.do(array, ac.Q) == 0) {
            super.for = al.a(a3);
        }
        else if (g.do(array, ac.av) == 0) {
            this.a7 = al.a(a3);
        }
        else if (g.do(array, ac.h) == 0) {
            this.bo = al.a(a3);
        }
        else if (g.do(array, ac.char) == 0) {
            this.a9 = al.a(a3);
            if (this.a9) {
                this.br = this.aO.s / 5;
            }
            else {
                this.br = 0;
            }
        }
        else if (g.do(array, ac.ae) == 0) {
            this.bb = al.a(a3);
            if (this.bb) {
                this.a(System.currentTimeMillis(), true);
            }
        }
        else if (g.do(array, ac.n) == 0) {
            if (a3.char != 4) {
                return;
            }
            for (int i = 0; i < this.aY; ++i) {
                if (g.if(a3.int, this.aS[i].long) == 0) {
                    this.bk = i;
                    this.a(System.currentTimeMillis(), true);
                    this.bb = true;
                    return;
                }
            }
        }
        else if (g.do(array, ac.C) == 0) {
            int n = 0;
            for (int j = 0; j < this.aY; ++j) {
                if (n != 0) {
                    this.aS[j - 1] = this.aS[j];
                }
                else if (g.if(a3.int, this.aS[j].long) == 0) {
                    n = 1;
                }
            }
            if (n != 0) {
                --this.aY;
            }
        }
        else if (g.do(array, ac.z) == 0) {
            final bf bf = new bf();
            try {
                y.a(bf, a3.int, g.a(a3.int));
                this.do(bf.do);
            }
            catch (Exception ex) {}
        }
        else if (g.do(array, ac.y) == 0) {
            if (a3.char == 4) {
                this.a6 = a3.int;
            }
        }
        else if (g.do(array, ac.d) == 0) {
            if (a3.char == 4) {
                this.bq = a3.int;
            }
        }
        else if (g.do(array, ac.long) == 0) {
            if (a3.char == 4) {
                this.aP = a3.int;
            }
        }
        else if (g.do(array, ac.J) == 0) {
            if (a3.char == 4) {
                this.a8 = a3.int;
            }
        }
        else if (g.do(array, ac.E) == 0) {
            if (a3.char == 4) {
                this.bn = a3.int;
            }
        }
        else if (g.do(array, ac.an) == 0) {
            if (a3.char == 4) {
                this.a3 = a3.int;
            }
        }
        else if (g.do(array, ac.al) == 0) {
            if (a3.char == 4) {
                this.bp = a3.int;
            }
        }
        else if (g.do(array, ac.R) == 0) {
            if (a3.char == 4) {
                this.aU = a3.int;
            }
        }
        else if (g.do(array, ac.F) == 0) {
            if (a3.char == 4) {
                this.bm = a3.int;
            }
        }
        else if (g.do(array, ac.ab) == 0) {
            if (a3.char == 4) {
                this.aW = a3.int;
            }
        }
        else if (g.do(array, ac.ak) == 0) {
            if (a3.char == 2) {
                this.bi = (int)a3.case;
            }
        }
        else if (g.do(array, ac.S) == 0) {
            if (a3.char == 2) {
                this.aV = (int)a3.case;
            }
        }
        else if (g.do(array, ac.l) == 0) {
            if (a3.char == 2) {
                this.aR = (int)a3.case;
            }
        }
        else if (g.do(array, ac.o) == 0) {
            if (a3.char == 4) {
                this.aX = a3.int;
            }
        }
        else if (g.do(array, ac.ag) == 0) {
            if (a3.char == 2) {
                this.aZ = a3.case * this.bs.G;
            }
            else if (a3.char == 3) {
                this.aZ = (float)(a3.else * this.bs.G);
            }
        }
        super.do = true;
    }
    
    public a3 a(final char[] array) {
        if (g.do(array, ac.ad) == 0) {
            super.new.char = 2;
            super.new.case = this.bh;
            return super.new;
        }
        if (g.do(array, ac.ac) == 0) {
            super.new.char = 2;
            super.new.case = this.bg;
            return super.new;
        }
        if (g.do(array, ac.u) == 0) {
            super.new.char = 2;
            super.new.case = this.aI;
            return super.new;
        }
        if (g.do(array, ac.as) == 0) {
            super.new.char = 2;
            super.new.case = this.aK;
            return super.new;
        }
        if (g.do(array, this.aM) == 0) {
            super.new.char = 2;
            super.new.case = this.a5;
            return super.new;
        }
        if (g.do(array, this.aL) == 0) {
            super.new.char = 2;
            super.new.case = this.a5;
            return super.new;
        }
        if (g.do(array, ac.ay) == 0) {
            super.new.char = 2;
            super.new.case = super.d;
            return super.new;
        }
        if (g.do(array, ac.Q) == 0) {
            super.new.char = 1;
            super.new.long = super.for;
            return super.new;
        }
        if (g.do(array, ac.av) == 0) {
            super.new.char = 1;
            super.new.long = this.a7;
            return super.new;
        }
        if (g.do(array, ac.h) == 0) {
            super.new.char = 1;
            super.new.long = this.bo;
            return super.new;
        }
        if (g.do(array, ac.ae) == 0) {
            super.new.char = 1;
            super.new.long = this.bb;
            return super.new;
        }
        if (g.do(array, ac.char) == 0) {
            super.new.char = 1;
            super.new.long = this.a9;
            return super.new;
        }
        if (g.do(array, ac.n) == 0) {
            if (this.aY == 0) {
                super.new.char = 1;
                super.new.long = false;
                return super.new;
            }
            super.new.char = 4;
            if (this.bk != -1) {
                super.new.int = this.aS[this.bk].long;
            }
            else if (g.if(this.aJ, ac.x) != 0) {
                super.new.int = this.aJ;
            }
            else {
                super.new.int = this.aS[0].long;
            }
            return super.new;
        }
        else {
            if (g.do(array, ac.y) == 0) {
                super.new.char = 4;
                super.new.int = this.a6;
                return super.new;
            }
            if (g.do(array, ac.d) == 0) {
                super.new.char = 4;
                super.new.int = this.bq;
                return super.new;
            }
            if (g.do(array, ac.long) == 0) {
                super.new.char = 4;
                super.new.int = this.aP;
                return super.new;
            }
            if (g.do(array, ac.J) == 0) {
                super.new.char = 4;
                super.new.int = this.a8;
                return super.new;
            }
            if (g.do(array, ac.E) == 0) {
                super.new.char = 4;
                super.new.int = this.bn;
                return super.new;
            }
            if (g.do(array, ac.an) == 0) {
                super.new.char = 4;
                super.new.int = this.a3;
                return super.new;
            }
            if (g.do(array, ac.al) == 0) {
                super.new.char = 4;
                super.new.int = this.bp;
                return super.new;
            }
            if (g.do(array, ac.R) == 0) {
                super.new.char = 4;
                super.new.int = this.aU;
                return super.new;
            }
            if (g.do(array, ac.F) == 0) {
                super.new.char = 4;
                super.new.int = this.bm;
                return super.new;
            }
            if (g.do(array, ac.ab) == 0) {
                super.new.char = 4;
                super.new.int = this.aW;
                return super.new;
            }
            if (g.do(array, ac.Z) == 0) {
                super.new.char = 4;
                super.new.int = this.bt;
                return super.new;
            }
            if (g.do(array, ac.do) == 0) {
                super.new.char = 4;
                super.new.int = super.case;
                return super.new;
            }
            if (g.do(array, ac.t) == 0) {
                super.new.char = 5;
                if (this.aO != null) {
                    super.new.goto = 1;
                    (super.new.a = new a3[1])[0] = new a3();
                    super.new.a[0].char = 4;
                    super.new.a[0].int = this.aO.f;
                }
                else {
                    super.new.goto = 0;
                }
                return super.new;
            }
            if (g.do(array, ac.ak) == 0) {
                super.new.char = 2;
                super.new.case = this.bi;
                return super.new;
            }
            if (g.do(array, ac.S) == 0) {
                super.new.char = 2;
                super.new.case = this.aV;
                return super.new;
            }
            if (g.do(array, ac.l) == 0) {
                super.new.char = 2;
                super.new.case = this.aR;
                return super.new;
            }
            if (g.do(array, ac.o) == 0) {
                super.new.char = 4;
                super.new.int = this.aX;
                return super.new;
            }
            if (g.do(array, ac.ag) == 0) {
                super.new.char = 3;
                super.new.else = this.aZ / this.bs.G;
                return super.new;
            }
            return ac.a(super.new);
        }
    }
}
