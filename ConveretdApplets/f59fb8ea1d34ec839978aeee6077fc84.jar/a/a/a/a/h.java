// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class h extends a2
{
    public float eV;
    public float eJ;
    public float eT;
    public float eZ;
    public float eI;
    private float eO;
    private float eD;
    private float eQ;
    ap eH;
    ap eF;
    ap e9;
    float e3;
    boolean eP;
    boolean eS;
    int e4;
    int e2;
    int e8;
    int e7;
    long e6;
    boolean eL;
    private char[] eE;
    boolean e5;
    ay e1;
    float eX;
    float eC;
    float e0;
    float eK;
    float eU;
    float eN;
    boolean eY;
    int eM;
    int eR;
    int eW;
    int eG;
    
    h(final t cb, final an b, final float ca) {
        this.eO = 0.0f;
        this.eD = 0.0f;
        this.eQ = -1.0f;
        this.eH = null;
        this.eF = null;
        this.e9 = null;
        this.e3 = 0.01f;
        this.eP = false;
        this.e6 = 0L;
        this.eL = false;
        this.eE = new char[] { 'c', 'a', 'm', 'e', 'r', 'a', '\0' };
        this.e5 = false;
        this.e1 = null;
        this.eX = 0.0f;
        this.eC = 0.0f;
        this.e0 = 0.0f;
        this.eK = 0.0f;
        this.eU = 0.0f;
        this.eN = 0.0f;
        this.eY = false;
        super.else = true;
        super.b = b;
        super.cA = ca;
        super.cD = 1.0f / super.cA;
        super.cB = cb;
        super.f = this.eE;
        this.eF = new ap();
        this.e9 = new ap();
        super.ez = false;
        super.ey = false;
        super.case = this.eE;
        super.ek = false;
    }
    
    public void if() {
        if (this.eH != null) {
            this.eH.if();
        }
        this.eH = null;
        if (this.eF != null) {
            this.eF.if();
        }
        this.eF = null;
        if (this.e9 != null) {
            this.e9.if();
        }
        this.e9 = null;
    }
    
    void a(final ap ap) {
        this.e9.x = ap.x;
        this.e9.l = this.eM;
        this.e9.p = this.eR;
        this.e9.n = ap.n;
        this.e9.m = ap.m;
        this.e9.s = ap.s;
        this.e9.r = ap.r;
        this.eW = this.eM >> 1;
        this.eG = this.eR >> 1;
        if (!this.e5) {
            this.eF.x = ap.x;
            this.eF.l = this.eM;
            this.eF.p = this.eR;
            this.eF.n = ap.n;
            this.eF.m = ap.m;
            this.eF.s = ap.s;
            this.eF.r = ap.r;
            this.eW = this.eM >> 1;
            this.eG = this.eR >> 1;
        }
    }
    
    public void int(final bh bh) {
        for (bh bh2 = bh.if; bh2 != null; bh2 = bh2.for) {
            if (bh2.a.toLowerCase().compareTo("entrypoint") == 0) {
                for (int i = 0; i < bh2.do; ++i) {
                    if (bh2.try[i].toLowerCase().compareTo("pan") == 0) {
                        this.eO = new Float(bh2.new[i]) * super.cA;
                    }
                    else if (bh2.try[i].toLowerCase().compareTo("tilt") == 0) {
                        this.eD = new Float(bh2.new[i]) * super.cA;
                    }
                    else if (bh2.try[i].toLowerCase().compareTo("fov") == 0) {
                        this.eQ = new Float(bh2.new[i]) * super.cA;
                    }
                }
            }
            else if (bh2.a.toLowerCase().compareTo("limits") == 0) {
                for (int j = 0; j < bh2.do; ++j) {
                    if (bh2.try[j].toLowerCase().compareTo("maxpan") == 0) {
                        super.ew = new Float(bh2.new[j]) * super.cA;
                        super.ez = true;
                    }
                    else if (bh2.try[j].toLowerCase().compareTo("minpan") == 0) {
                        super.em = new Float(bh2.new[j]) * super.cA;
                        super.ez = true;
                    }
                    else if (bh2.try[j].toLowerCase().compareTo("maxtilt") == 0) {
                        super.ea = new Float(bh2.new[j]) * super.cA;
                        super.ey = true;
                    }
                    else if (bh2.try[j].toLowerCase().compareTo("mintilt") == 0) {
                        super.d5 = new Float(bh2.new[j]) * super.cA;
                        super.ey = true;
                    }
                    else if (bh2.try[j].toLowerCase().compareTo("maxfov") == 0) {
                        super.ed = new Float(bh2.new[j]) * super.cA;
                    }
                    else if (bh2.try[j].toLowerCase().compareTo("minfov") == 0) {
                        super.dY = new Float(bh2.new[j]) * super.cA;
                    }
                    else if (bh2.try[j].toLowerCase().compareTo("lockzenithnadir") == 0 && bh2.new[j].toLowerCase().compareTo("true") == 0) {
                        super.dV = true;
                    }
                }
                if (super.dY > super.ed) {
                    final float dy = super.dY;
                    super.dY = super.ed;
                    super.ed = dy;
                }
                if (super.dY < 0.01745) {
                    super.dY = 0.01745f;
                }
                if (super.ed > 2.5) {
                    super.ed = 2.5f;
                }
            }
            else if (bh2.a.toLowerCase().compareTo("autopath") == 0) {
                super.ek = true;
                for (int k = 0; k < bh2.do; ++k) {
                    if (bh2.try[k].toLowerCase().compareTo("play") == 0) {
                        if (bh2.new[k].compareTo("false") == 0) {
                            super.ek = false;
                        }
                    }
                    else if (bh2.try[k].toLowerCase().compareTo("first") == 0) {
                        super.dW = (String.valueOf(bh2.new[k]) + "\u0000").toCharArray();
                    }
                }
                for (bh bh3 = bh2.if; bh3 != null; bh3 = bh3.for) {
                    this.byte(bh3);
                }
            }
        }
    }
    
    void if(final am am) {
        switch (am.if) {
            case 0: {
                if (am.i || am.goto < 0 || am.goto >= this.eM || am.else < 0 || am.else >= this.eR) {
                    break;
                }
                if (am.g == 5) {
                    this.eS = true;
                }
                else {
                    this.eS = false;
                }
                this.eP = true;
                this.e4 = am.goto;
                this.e2 = am.else;
                this.e8 = am.goto;
                this.e7 = am.else;
                if (this.eS) {
                    am.for = 2;
                    break;
                }
                am.for = 1;
                break;
            }
            case 4: {
                if (!this.eP) {
                    break;
                }
                this.e8 = am.goto;
                this.e7 = am.else;
                if (this.eS) {
                    am.for = 2;
                    break;
                }
                am.for = 1;
                break;
            }
            case 1: {
                this.eP = false;
                if (an.g > 0.0f) {
                    super.d6 = true;
                }
                this.eK = 0.0f;
                this.eU = 0.0f;
                this.eN = 0.0f;
                break;
            }
        }
    }
    
    boolean do(final int n) {
        return true;
    }
    
    public void new(final long n) {
        super.byte = true;
        if (this.eH == null) {
            return;
        }
        if (this.e1 != null) {
            this.e5 = true;
            try {
                this.eF.x = new int[this.eM * this.eR];
                this.eF.l = this.eM;
                this.eF.p = this.eR;
                this.eF.n = 0;
                this.eF.m = 0;
                this.eF.s = this.eM;
                this.eF.r = this.eR;
                this.eW = this.eM >> 1;
                this.eG = this.eR >> 1;
                if (this.e1 != null) {
                    this.e1.a(this.eH, this.eF);
                }
            }
            catch (Exception ex) {
                this.e5 = false;
            }
            catch (OutOfMemoryError outOfMemoryError) {
                System.out.println("Transition disabled. Not enough memory. 2");
                this.e5 = false;
                this.e1 = null;
            }
        }
    }
    
    public boolean a(final long e6) {
        boolean do1 = super.do;
        super.do = false;
        while (super.cE > 6.283185307179586) {
            super.cE -= 6.283185307179586;
        }
        while (super.cE < -6.283185307179586) {
            super.cE += 6.283185307179586;
        }
        while (super.cx > 3.141592653589793) {
            super.cx -= 6.283185307179586;
        }
        while (super.cx < -3.141592653589793) {
            super.cx += 6.283185307179586;
        }
        if (!super.byte) {
            return false;
        }
        if (this.eL) {
            if (this.eP) {
                super.ek = false;
                if (this.eS) {
                    final float cy = super.cy;
                    final float e7 = (this.e7 - this.e2) * this.e3 * (e6 - this.e6) * 2.0E-4f * super.cB.i.for;
                    this.e0 = e7;
                    super.cy = cy + e7;
                    do1 = true;
                }
                else {
                    final float ce = super.cE;
                    final float ex = (this.e8 - this.e4) * (e6 - this.e6) * this.e3 * super.cy * super.cB.i.for * 4.0E-4f;
                    this.eX = ex;
                    super.cE = ce - ex;
                    final float cx = super.cx;
                    final float ec = (this.e7 - this.e2) * (e6 - this.e6) * this.e3 * super.cy * super.cB.i.for * 4.0E-4f;
                    this.eC = ec;
                    super.cx = cx - ec;
                    do1 = true;
                }
            }
            else if (super.d6) {
                float g = an.g * (40.0f / (e6 - this.e6));
                if (g > 1.0f) {
                    g = an.g;
                }
                if (this.eX != 0.0f) {
                    this.eX *= g;
                }
                if (this.eC != 0.0f) {
                    this.eC *= g;
                }
                if (this.e0 != 0.0f) {
                    this.e0 *= g;
                }
                if (((this.eX > 0.0f) ? this.eX : (-this.eX)) < 0.001 && ((this.eC > 0.0f) ? this.eC : (-this.eC)) < 0.001 && ((this.e0 > 0.0f) ? this.e0 : (-this.e0)) < 0.001) {
                    super.d6 = false;
                    this.eX = 0.0f;
                    this.eC = 0.0f;
                    this.e0 = 0.0f;
                }
                super.cE -= this.eX;
                super.cx -= this.eC;
                super.cy += this.e0;
                do1 = true;
            }
            else {
                do1 |= this.try(e6);
            }
        }
        else {
            this.eL = true;
            if (!super.cB.R) {
                super.cE = this.eO;
                super.cx = this.eD;
                if (this.eQ > 0.0f) {
                    super.cy = this.eQ;
                }
            }
        }
        if (super.cy < super.dY) {
            super.cy = super.dY;
        }
        else if (super.cy > super.ed) {
            super.cy = super.ed;
        }
        if (super.ey || super.ez) {
            final float n = (float)Math.sqrt(this.eG * this.eG + this.eW * this.eW);
            float n2 = n / (float)Math.tan(super.cy * 0.5f);
            if (super.ey) {
                float n3 = (float)(2.0 * Math.atan2(this.eG, n2));
                if (super.ea - super.d5 < n3) {
                    n2 = (float)(this.eG / Math.tan((super.ea - super.d5) * 0.5));
                    super.cy = (float)(2.0 * Math.atan2(n, n2));
                    n3 = super.ea - super.d5;
                }
                if (super.cx + n3 * 0.5 > super.ea) {
                    super.cx = (float)(-n3 * 0.5 + super.ea);
                }
                if (super.cx - n3 * 0.5 < super.d5) {
                    super.cx = (float)(n3 * 0.5 + super.d5);
                }
            }
            if (super.ez) {
                float n4 = (float)(2.0 * Math.atan2(this.eW, n2));
                if (super.ew - super.em < n4) {
                    super.cy = (float)(2.0 * Math.atan2(n, (float)(this.eW / Math.tan((super.ew - super.em) * 0.5))));
                    n4 = super.ew - super.em;
                }
                if (super.cE + n4 * 0.5 > super.ew) {
                    super.cE = (float)(-n4 * 0.5 + super.ew);
                }
                if (super.cE - n4 * 0.5 < super.em) {
                    super.cE = (float)(n4 * 0.5 + super.em);
                }
            }
        }
        if (super.b.for | super.dV) {
            while (super.cx > 3.141592653589793) {
                super.cx -= 6.283185307179586;
            }
            while (super.cx < -3.141592653589793) {
                super.cx += 6.283185307179586;
            }
            if (super.cx > 1.5707963267948966) {
                super.cx = 1.5707964f;
            }
            if (super.cx < -1.5707963267948966) {
                super.cx = -1.5707964f;
            }
        }
        this.e6 = e6;
        final boolean e8 = this.e5;
        if (this.e1 != null && !(this.e5 = this.e1.a(e6))) {
            this.eF = this.e9;
        }
        return (do1 & super.for) | e8;
    }
    
    public void new(final boolean b) {
        this.eV = (float)Math.cos(super.cE);
        this.eJ = (float)Math.sin(super.cE);
        this.eT = (float)Math.cos(super.cx);
        this.eZ = (float)Math.sin(super.cx);
        this.eI = (float)(Math.sqrt(this.eM * this.eM + this.eR * this.eR >> 2) / Math.tan(super.cy * 0.5));
    }
    
    public void u() {
        if (this.e1 != null) {
            this.e1.a(this.e9, this.e9.n, this.e9.m, this.e9.l, this.e9.p);
        }
    }
    
    public ap case(final boolean b) {
        try {
            this.eF.x = new int[this.eM * this.eR];
            this.eF.l = this.eM;
            this.eF.p = this.eR;
            this.eF.n = 0;
            this.eF.m = 0;
            this.eF.s = this.eM;
            this.eF.r = this.eR;
            this.eW = this.eM >> 1;
            this.eG = this.eR >> 1;
        }
        catch (Exception ex) {
            return null;
        }
        catch (OutOfMemoryError outOfMemoryError) {
            System.out.println("Transition disabled. Not enough memory. 3");
            this.eF = null;
            this.e5 = false;
            return null;
        }
        return this.eF;
    }
    
    public void if(final ap ap) {
        this.eH = new ap();
        this.eH.x = ap.x;
        this.eH.l = ap.l;
        this.eH.p = ap.p;
        this.eH.n = 0;
        this.eH.m = 0;
        this.eH.s = ap.l;
        this.eH.r = ap.p;
    }
    
    public void a(final ay e1) {
        this.e1 = e1;
    }
}
