// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class bm extends a8
{
    protected int a7;
    protected int a6;
    protected int aV;
    protected int aU;
    protected int ay;
    protected int aA;
    protected float a5;
    protected float a4;
    protected float aS;
    protected float bb;
    protected float aP;
    protected boolean a3;
    protected boolean a1;
    protected boolean aZ;
    a1 a0;
    protected int a8;
    protected int aL;
    protected int aH;
    protected char[] aW;
    protected char[] bf;
    protected char[] aF;
    protected char[] aY;
    protected char[] bd;
    protected char[] aT;
    protected char[] be;
    protected char[] aK;
    protected char[] bc;
    protected char[] aM;
    protected char[] bi;
    protected char[] a2;
    protected char[] aC;
    protected char[] aB;
    protected char[] aN;
    protected boolean aQ;
    protected boolean a9;
    protected boolean aR;
    protected ad aE;
    protected ae aJ;
    protected int bg;
    protected static final int aD = 5;
    protected float aG;
    protected boolean aX;
    a5[] aI;
    int aO;
    int ba;
    char[] az;
    v bh;
    
    bm() {
        this.a7 = 0;
        this.a6 = 0;
        this.aV = 0;
        this.aU = 0;
        this.ay = -1;
        this.aA = -1;
        this.aP = 0.0f;
        this.a3 = false;
        this.a1 = true;
        this.a0 = null;
        this.a8 = -1;
        this.aL = -1711276288;
        this.aH = -16711681;
        this.aW = new char[1];
        this.bf = new char[1];
        this.aF = new char[1];
        this.aY = new char[1];
        this.bd = new char[1];
        this.aT = new char[1];
        this.be = new char[1];
        this.aK = new char[1];
        this.bc = new char[1];
        this.aM = new char[1];
        this.bi = new char[1];
        this.a2 = new char[1];
        this.aC = new char[] { 'x', '\0' };
        this.aB = new char[] { 'y', '\0' };
        this.aN = new char[1];
        this.aQ = false;
        this.a9 = false;
        this.aR = false;
        this.aE = null;
        this.aJ = null;
        this.bg = 0;
        this.aX = true;
        this.aI = null;
        this.aO = 0;
        this.ba = -1;
        this.az = new char[1];
        super.int = new a3();
    }
    
    void int(final long n) {
        super.else = true;
        if (this.aE != null) {
            this.aE.if(n);
        }
    }
    
    void a(final ab ab) {
    }
    
    public void a(final ac ac, final a2 a2, final ae ae, final v v) {
    }
    
    public void e() {
    }
    
    public void a(final a2 a2, final ae ae) {
        for (int i = 0; i < a2.do; ++i) {
            if (a2.try[i].toLowerCase().compareTo("id") == 0) {
                super.e = (String.valueOf(a2.new[i]) + "\u0000").toCharArray();
            }
            else if (a2.try[i].toLowerCase().compareTo("posx") == 0) {
                this.a7 = Integer.valueOf(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("posy") == 0) {
                this.a6 = Integer.valueOf(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("x") == 0) {
                this.aV = Integer.valueOf(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("y") == 0) {
                this.aU = Integer.valueOf(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("width") == 0) {
                this.ay = Integer.valueOf(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("height") == 0) {
                this.aA = Integer.valueOf(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("layer") == 0) {
                super.c = Integer.valueOf(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("visible") == 0) {
                super.do = Boolean.valueOf(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("checked") == 0) {
                this.aZ = Boolean.valueOf(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("north") == 0 || a2.try[i].toLowerCase().compareTo("horrizon") == 0) {
                this.aP = Float.valueOf(a2.new[i]) * this.aG;
            }
            else if (a2.try[i].toLowerCase().compareTo("managetransparency") == 0) {
                this.aX = Boolean.valueOf(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("color") == 0) {
                this.a8 = ac.a(a2.new[i]);
                if ((this.a8 & 0xFF000000) == 0x0) {
                    this.a8 |= 0xFF000000;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("surfacecolor") == 0) {
                this.aL = ac.a(a2.new[i]);
                if ((this.aL & 0xFF000000) == 0x0) {
                    this.aL |= 0xFF000000;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("bordercolor") == 0) {
                this.aH = ac.a(a2.new[i]);
                if ((this.aH & 0xFF000000) == 0x0) {
                    this.aH |= 0xFF000000;
                }
            }
        }
        this.a();
        for (a2 a3 = a2.if; a3 != null; a3 = a3.for) {
            final String lowerCase = a3.a.toLowerCase();
            String if1;
            if (a3.case == null) {
                if1 = "";
            }
            else {
                if1 = a3.case.if;
            }
            if (lowerCase.compareTo("image") == 0) {
                String s = null;
                boolean boolean1 = true;
                for (int j = 0; j < a3.do; ++j) {
                    if (a3.try[j].compareTo("file") == 0) {
                        s = a3.new[j];
                    }
                    else if (a3.try[j].compareTo("id") == 0) {
                        super.e = a3.new[j].toCharArray();
                    }
                    else if (a3.try[j].compareTo("play") == 0) {
                        boolean1 = Boolean.getBoolean(a3.new[j]);
                    }
                }
                if (s != null) {
                    this.aE = new ad();
                    this.aJ = super.void.w.a(s, ae, false, true, false);
                    this.aE.k = boolean1;
                    this.bh.a(this.aE);
                }
            }
            else if (if1 != null && if1.trim().length() != 0) {
                if ((String.valueOf(lowerCase) + "\u0000").toLowerCase().compareTo(new String(ac.void)) == 0) {
                    this.bf = if1.toCharArray();
                }
                else if ((String.valueOf(lowerCase) + "\u0000").toLowerCase().compareTo(new String(ac.t)) == 0) {
                    this.aW = if1.toCharArray();
                }
                else if ((String.valueOf(lowerCase) + "\u0000").toLowerCase().compareTo(new String(ac.goto)) == 0) {
                    this.aF = if1.toCharArray();
                }
                else if ((String.valueOf(lowerCase) + "\u0000").toLowerCase().compareTo(new String(ac.D)) == 0) {
                    this.aY = if1.toCharArray();
                }
                else if ((String.valueOf(lowerCase) + "\u0000").toLowerCase().compareTo(new String(ac.z)) == 0) {
                    this.bd = if1.toCharArray();
                }
                else if ((String.valueOf(lowerCase) + "\u0000").toLowerCase().compareTo(new String(ac.ae)) == 0) {
                    this.aT = if1.toCharArray();
                }
                else if ((String.valueOf(lowerCase) + "\u0000").toLowerCase().compareTo(new String(ac.ac)) == 0) {
                    this.be = if1.toCharArray();
                }
                else if ((String.valueOf(lowerCase) + "\u0000").toLowerCase().compareTo(new String(ac.L)) == 0) {
                    this.aK = if1.toCharArray();
                }
                else if ((String.valueOf(lowerCase) + "\u0000").toLowerCase().compareTo(new String(ac.A)) == 0) {
                    this.bc = if1.toCharArray();
                }
                else if ((String.valueOf(lowerCase) + "\u0000").toLowerCase().compareTo(new String(ac.T)) == 0) {
                    this.aM = if1.toCharArray();
                }
                else if ((String.valueOf(lowerCase) + "\u0000").toLowerCase().compareTo(new String(ac.R)) == 0) {
                    this.bi = if1.toCharArray();
                }
                else if ((String.valueOf(lowerCase) + "\u0000").toLowerCase().compareTo(new String(ac.k)) == 0) {
                    this.aN = (String.valueOf(if1) + "\u0000").toCharArray();
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
                            this.a1 = false;
                        }
                    }
                    else if (a3.try[i].toLowerCase().compareTo("first") == 0) {
                        this.az = (String.valueOf(a3.new[i]) + "\u0000").toCharArray();
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
            this.d();
            this.aI[this.aO] = new a5();
            if (a2.a.toLowerCase().compareTo("moveto") == 0) {
                this.aI[this.aO].do = 0;
            }
            else if (a2.a.toLowerCase().compareTo("move") == 0) {
                this.aI[this.aO].do = 1;
            }
            else if (a2.a.toLowerCase().compareTo("sleep") == 0) {
                this.aI[this.aO].do = 2;
            }
            else if (a2.a.toLowerCase().compareTo("action") == 0) {
                this.aI[this.aO].do = 3;
                this.aI[this.aO].h = (String.valueOf(a2.case.if) + "\u0000").toCharArray();
            }
            for (int i = 0; i < a2.do; ++i) {
                if (a2.try[i].toLowerCase().compareTo("posx") == 0) {
                    this.aI[this.aO].case = new Integer(a2.new[i]);
                    this.aI[this.aO].else = true;
                }
                else if (a2.try[i].toLowerCase().compareTo("posy") == 0) {
                    this.aI[this.aO].try = new Integer(a2.new[i]);
                    this.aI[this.aO].char = true;
                }
                else if (a2.try[i].toLowerCase().compareTo("width") == 0) {
                    this.aI[this.aO].d = new Integer(a2.new[i]);
                    this.aI[this.aO].j = true;
                }
                else if (a2.try[i].toLowerCase().compareTo("height") == 0) {
                    this.aI[this.aO].goto = new Integer(a2.new[i]);
                    this.aI[this.aO].if = true;
                }
                else if (a2.try[i].toLowerCase().compareTo("speed") == 0) {
                    this.aI[this.aO].i = new Float(a2.new[i]);
                    this.aI[this.aO].m = 1;
                }
                else if (a2.try[i].toLowerCase().compareTo("time") == 0) {
                    this.aI[this.aO].new = (long)(new Float(a2.new[i]) * 1000.0f);
                    this.aI[this.aO].m = 0;
                }
                else if (a2.try[i].toLowerCase().compareTo("amid") == 0 || a2.try[i].toLowerCase().compareTo("apid") == 0) {
                    this.aI[this.aO].long = (String.valueOf(a2.new[i]) + "\u0000").toCharArray();
                }
                else if (a2.try[i].toLowerCase().compareTo("next") == 0) {
                    this.aI[this.aO].void = (String.valueOf(a2.new[i]) + "\u0000").toCharArray();
                }
            }
            ++this.aO;
        }
    }
    
    boolean for(final long n) {
        if (this.a1) {
            if ((this.ba == -1 || (this.aI[this.ba].int != 0L && this.aI[this.ba].int < n)) && (this.ba == -1 || this.aI[this.ba].do != 0 || this.aI[this.ba].int == 1L)) {
                this.a(n, false);
                if (this.ba == -1) {
                    return false;
                }
            }
            if (this.aI[this.ba].do == 1) {
                final float n2 = 1000.0f / (n - this.aI[this.ba].q);
                this.aI[this.ba].q = n;
                if (this.aI[this.ba].else) {
                    this.a5 += this.aI[this.ba].case / n2;
                    this.a7 = (int)this.a5;
                }
                if (this.aI[this.ba].char) {
                    this.a4 += this.aI[this.ba].try / n2;
                    this.a6 = (int)this.a4;
                }
                if (this.aI[this.ba].j) {
                    this.aS += this.aI[this.ba].d / n2;
                    this.ay = (int)this.aS;
                }
                if (this.aI[this.ba].if) {
                    this.bb += this.aI[this.ba].goto / n2;
                }
            }
            else if (this.aI[this.ba].do == 0) {
                float n3;
                if (this.aI[this.ba].new == 0L || this.aI[this.ba].int < n || n - this.aI[this.ba].q >= this.aI[this.ba].int - n) {
                    n3 = 0.0f;
                    this.aI[this.ba].int = 1L;
                }
                else {
                    n3 = (this.aI[this.ba].int - n) / (n - this.aI[this.ba].q);
                }
                this.aI[this.ba].q = n;
                if (this.aI[this.ba].else) {
                    if (n3 == 0.0f) {
                        this.a5 = this.aI[this.ba].case;
                    }
                    else {
                        this.a5 += (this.aI[this.ba].case - this.a5) / n3;
                    }
                    this.a7 = (int)this.a5;
                }
                if (this.aI[this.ba].char) {
                    if (n3 == 0.0f) {
                        this.a4 = this.aI[this.ba].try;
                    }
                    else {
                        this.a4 += (this.aI[this.ba].try - this.a4) / n3;
                    }
                    this.a6 = (int)this.a4;
                }
                if (this.aI[this.ba].j) {
                    if (n3 == 0.0f) {
                        this.aS = this.aI[this.ba].d;
                    }
                    else {
                        this.aS += (this.aI[this.ba].d - this.aS) / n3;
                    }
                    this.ay = (int)this.aS;
                }
                if (this.aI[this.ba].if) {
                    if (n3 == 0.0f) {
                        this.bb = this.aI[this.ba].goto;
                    }
                    else {
                        this.bb += (this.aI[this.ba].goto - this.bb) / n3;
                    }
                    this.aA = (int)this.bb;
                }
            }
            return true;
        }
        return false;
    }
    
    private void a(final long q, final boolean b) {
        if (!b || this.ba == -1) {
            if (this.ba == -1) {
                if (g.a(this.az) == 0) {
                    this.ba = 0;
                }
                else {
                    for (int i = 0; i < this.aO; ++i) {
                        if (g.a(this.az, this.aI[i].long) == 0) {
                            this.ba = i;
                        }
                    }
                }
                if (this.ba >= this.aO || this.ba == -1) {
                    this.a1 = false;
                    this.ba = -1;
                    return;
                }
            }
            else {
                if (g.a(this.aI[this.ba].void, ac.s) == 0) {
                    this.ba = -1;
                    this.a1 = false;
                    return;
                }
                boolean b2 = false;
                for (int j = 0; j < this.aO; ++j) {
                    if (g.a(this.aI[this.ba].void, this.aI[j].long) == 0) {
                        this.ba = j;
                        b2 = true;
                        break;
                    }
                }
                if (!b2) {
                    this.ba = -1;
                    this.a1 = false;
                    return;
                }
            }
        }
        this.aI[this.ba].q = q;
        if (this.aI[this.ba].do == 1) {
            if (this.aI[this.ba].new == 0L) {
                this.aI[this.ba].int = 0L;
                this.aI[this.ba].q = q - 40L;
            }
            else {
                this.aI[this.ba].int = q + this.aI[this.ba].new + 40L;
            }
        }
        else if (this.aI[this.ba].do == 0) {
            if (this.aI[this.ba].m == 1) {
                float n = 0.0f;
                if (this.aI[this.ba].else) {
                    n = Math.abs(this.a7 - this.aI[this.ba].case);
                }
                if (this.aI[this.ba].char) {
                    n = Math.max(n, Math.abs(this.a6 - this.aI[this.ba].try));
                }
                if (this.aI[this.ba].j) {
                    n = Math.max(n, Math.abs(this.ay - this.aI[this.ba].d));
                }
                if (this.aI[this.ba].if) {
                    n = Math.max(n, Math.abs(this.aA - this.aI[this.ba].goto));
                }
                this.aI[this.ba].new = (long)(n * 1000.0f / this.aI[this.ba].i);
            }
            if (this.aI[this.ba].new == 0L) {
                this.aI[this.ba].int = 0L;
            }
            else {
                this.aI[this.ba].int = q + this.aI[this.ba].new + 40L;
            }
        }
        else {
            if (this.aI[this.ba].do == 3) {
                this.bh.a(this.aI[this.ba].h);
                this.aI[this.ba].int = 1L;
                return;
            }
            this.aI[this.ba].int = q + this.aI[this.ba].new;
            return;
        }
        this.a5 = this.a7;
        this.a4 = this.a6;
        this.aS = this.ay;
        this.bb = this.aA;
    }
    
    private void d() {
        if (this.aI == null || this.aI.length == this.aO) {
            final a5[] ai = new a5[this.aO + 10];
            for (int i = 0; i < this.aO; ++i) {
                ai[i] = this.aI[i];
            }
            this.aI = ai;
        }
    }
    
    public void a(final char[] array, final a3 a3) {
        if (g.if(array, ac.V) == 0) {
            this.a7 = al.if(a3);
            super.if = true;
        }
        else if (g.if(array, ac.U) == 0) {
            this.a6 = al.if(a3);
            super.if = true;
        }
        else if (g.if(array, ac.p) == 0) {
            this.ay = al.if(a3);
            super.if = true;
        }
        else if (g.if(array, ac.aj) == 0) {
            this.aA = al.if(a3);
            super.if = true;
        }
        else if (g.if(array, this.aC) == 0) {
            this.aV = al.if(a3);
            super.if = true;
        }
        else if (g.if(array, this.aB) == 0) {
            this.aU = al.if(a3);
            super.if = true;
        }
        else if (g.if(array, ac.ap) == 0) {
            super.c = al.if(a3);
            super.void.if(this.bh.B, 0, this.bh.x - 1);
            super.if = true;
        }
        else if (g.if(array, ac.K) == 0) {
            super.do = al.a(a3);
            super.if = true;
        }
        else if (g.if(array, ac.am) == 0) {
            this.aX = al.a(a3);
        }
        else if (g.if(array, ac.case) == 0) {
            this.aZ = al.a(a3);
            if (this.aZ) {
                this.bg = this.aE.i / 5;
            }
            else {
                this.bg = 0;
            }
            super.if = true;
        }
        else if (g.if(array, ac.W) == 0) {
            this.a1 = al.a(a3);
            if (this.a1) {
                this.a(System.currentTimeMillis(), true);
            }
        }
        else if (g.if(array, ac.j) == 0) {
            if (a3.char != 4) {
                return;
            }
            for (int i = 0; i < this.aO; ++i) {
                if (g.a(a3.int, this.aI[i].long) == 0) {
                    this.ba = i;
                    this.a(System.currentTimeMillis(), true);
                    this.a1 = true;
                    return;
                }
            }
        }
        else if (g.if(array, ac.x) == 0) {
            int n = 0;
            for (int j = 0; j < this.aO; ++j) {
                if (n != 0) {
                    this.aI[j - 1] = this.aI[j];
                }
                else if (g.a(a3.int, this.aI[j].long) == 0) {
                    n = 1;
                }
            }
            if (n != 0) {
                --this.aO;
            }
        }
        else if (g.if(array, ac.u) == 0) {
            final bf bf = new bf();
            try {
                y.a(bf, a3.int);
                this.do(bf.do);
            }
            catch (Exception ex) {}
        }
        else if (g.if(array, ac.t) == 0) {
            if (a3.char == 4) {
                this.aW = a3.int;
            }
        }
        else if (g.if(array, ac.void) == 0) {
            if (a3.char == 4) {
                this.bf = a3.int;
            }
        }
        else if (g.if(array, ac.goto) == 0) {
            if (a3.char == 4) {
                this.aF = a3.int;
            }
        }
        else if (g.if(array, ac.D) == 0) {
            if (a3.char == 4) {
                this.aY = a3.int;
            }
        }
        else if (g.if(array, ac.z) == 0) {
            if (a3.char == 4) {
                this.bd = a3.int;
            }
        }
        else if (g.if(array, ac.ae) == 0) {
            if (a3.char == 4) {
                this.aT = a3.int;
            }
        }
        else if (g.if(array, ac.ac) == 0) {
            if (a3.char == 4) {
                this.be = a3.int;
            }
        }
        else if (g.if(array, ac.L) == 0) {
            if (a3.char == 4) {
                this.aK = a3.int;
            }
        }
        else if (g.if(array, ac.A) == 0) {
            if (a3.char == 4) {
                this.bc = a3.int;
            }
        }
        else if (g.if(array, ac.T) == 0) {
            if (a3.char == 4) {
                this.aM = a3.int;
            }
        }
        else if (g.if(array, ac.aa) == 0) {
            if (a3.char == 2) {
                this.a8 = ((int)a3.case | 0xFF000000);
            }
        }
        else if (g.if(array, ac.k) == 0 && a3.char == 4) {
            this.aN = a3.int;
        }
    }
    
    public a3 a(final char[] array) {
        if (g.if(array, ac.V) == 0) {
            super.int.char = 2;
            super.int.case = this.a7;
            return super.int;
        }
        if (g.if(array, ac.U) == 0) {
            super.int.char = 2;
            super.int.case = this.a6;
            return super.int;
        }
        if (g.if(array, ac.p) == 0) {
            super.int.char = 2;
            super.int.case = this.ay;
            return super.int;
        }
        if (g.if(array, ac.aj) == 0) {
            super.int.char = 2;
            super.int.case = this.aA;
            return super.int;
        }
        if (g.if(array, ac.ap) == 0) {
            super.int.char = 2;
            super.int.case = super.c;
            return super.int;
        }
        if (g.if(array, ac.K) == 0) {
            super.int.char = 1;
            super.int.long = super.do;
            return super.int;
        }
        if (g.if(array, ac.am) == 0) {
            super.int.char = 1;
            super.int.long = this.aX;
            return super.int;
        }
        if (g.if(array, ac.W) == 0) {
            super.int.char = 1;
            super.int.long = this.a1;
            return super.int;
        }
        if (g.if(array, ac.case) == 0) {
            super.int.char = 1;
            super.int.long = this.aZ;
            return super.int;
        }
        if (g.if(array, ac.j) == 0) {
            if (this.aO == 0) {
                super.int.char = 1;
                super.int.long = false;
                return super.int;
            }
            super.int.char = 4;
            if (this.ba != -1) {
                super.int.int = this.aI[this.ba].long;
            }
            else if (g.a(this.az, ac.s) != 0) {
                super.int.int = this.az;
            }
            else {
                super.int.int = this.aI[0].long;
            }
            return super.int;
        }
        else {
            if (g.if(array, ac.t) == 0) {
                super.int.char = 4;
                super.int.int = this.aW;
                return super.int;
            }
            if (g.if(array, ac.void) == 0) {
                super.int.char = 4;
                super.int.int = this.bf;
                return super.int;
            }
            if (g.if(array, ac.goto) == 0) {
                super.int.char = 4;
                super.int.int = this.aF;
                return super.int;
            }
            if (g.if(array, ac.D) == 0) {
                super.int.char = 4;
                super.int.int = this.aY;
                return super.int;
            }
            if (g.if(array, ac.z) == 0) {
                super.int.char = 4;
                super.int.int = this.bd;
                return super.int;
            }
            if (g.if(array, ac.ae) == 0) {
                super.int.char = 4;
                super.int.int = this.aT;
                return super.int;
            }
            if (g.if(array, ac.ac) == 0) {
                super.int.char = 4;
                super.int.int = this.be;
                return super.int;
            }
            if (g.if(array, ac.L) == 0) {
                super.int.char = 4;
                super.int.int = this.aK;
                return super.int;
            }
            if (g.if(array, ac.A) == 0) {
                super.int.char = 4;
                super.int.int = this.bc;
                return super.int;
            }
            if (g.if(array, ac.T) == 0) {
                super.int.char = 4;
                super.int.int = this.aM;
                return super.int;
            }
            if (g.if(array, ac.R) == 0) {
                super.int.char = 4;
                super.int.int = this.bi;
                return super.int;
            }
            if (g.if(array, ac.do) == 0) {
                super.int.char = 4;
                super.int.int = super.byte;
                return super.int;
            }
            if (g.if(array, ac.o) == 0) {
                super.int.char = 5;
                if (this.aE != null) {
                    super.int.goto = 1;
                    (super.int.a = new a3[1])[0] = new a3();
                    super.int.a[0].char = 4;
                    super.int.a[0].int = this.aE.e;
                }
                else {
                    super.int.goto = 0;
                }
                return super.int;
            }
            if (g.if(array, ac.aa) == 0) {
                super.int.char = 2;
                super.int.case = this.a8;
                return super.int;
            }
            if (g.if(array, ac.k) == 0) {
                super.int.char = 4;
                super.int.int = this.aN;
                return super.int;
            }
            return ac.a(super.int);
        }
    }
    
    public void if() {
        if (this.aE != null) {
            this.aE.if();
        }
    }
}
