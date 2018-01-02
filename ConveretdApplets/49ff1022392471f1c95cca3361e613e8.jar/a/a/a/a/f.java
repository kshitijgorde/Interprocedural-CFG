// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class f extends ao
{
    public float eC;
    public float eq;
    public float eA;
    public float eG;
    public float ep;
    private float ev;
    private float ek;
    private float ex;
    ad eo;
    ad em;
    ad eQ;
    float eK;
    boolean ew;
    boolean ez;
    int eL;
    int eJ;
    int eP;
    int eO;
    long eN;
    boolean es;
    private char[] el;
    boolean eM;
    ak eI;
    float eE;
    float ej;
    float eH;
    float er;
    float eB;
    float eu;
    boolean eF;
    int et;
    int ey;
    int eD;
    int en;
    
    f(final m ck, final ac void1, final float cj) {
        this.ev = 0.0f;
        this.ek = 0.0f;
        this.ex = -1.0f;
        this.eo = null;
        this.eK = 0.01f;
        this.ew = false;
        this.eN = 0L;
        this.es = false;
        this.el = new char[] { 'c', 'a', 'm', 'e', 'r', 'a', '\0' };
        this.eM = false;
        this.eI = null;
        this.eE = 0.0f;
        this.ej = 0.0f;
        this.eH = 0.0f;
        this.er = 0.0f;
        this.eB = 0.0f;
        this.eu = 0.0f;
        this.eF = false;
        super.char = true;
        super.void = void1;
        super.cj = cj;
        super.cm = 1.0f / super.cj;
        super.ck = ck;
        super.e = this.el;
        this.em = new ad();
        this.eQ = new ad();
        super.eg = false;
        super.ef = false;
        super.byte = this.el;
        super.d1 = false;
    }
    
    void a(final ad ad) {
        this.eQ.p = ad.p;
        this.eQ.s = this.et;
        this.eQ.j = this.ey;
        this.eQ.h = ad.h;
        this.eQ.g = ad.g;
        this.eQ.i = ad.i;
        this.eQ.t = ad.t;
        this.eD = this.et >> 1;
        this.en = this.ey >> 1;
        if (!this.eM) {
            this.em.p = ad.p;
            this.em.s = this.et;
            this.em.j = this.ey;
            this.em.h = ad.h;
            this.em.g = ad.g;
            this.em.i = ad.i;
            this.em.t = ad.t;
            this.eD = this.et >> 1;
            this.en = this.ey >> 1;
        }
    }
    
    public void int(final a2 a2) {
        for (a2 a3 = a2.if; a3 != null; a3 = a3.for) {
            if (a3.a.toLowerCase().compareTo("entrypoint") == 0) {
                for (int i = 0; i < a3.do; ++i) {
                    if (a3.try[i].toLowerCase().compareTo("pan") == 0) {
                        this.ev = new Float(a3.new[i]) * super.cj;
                    }
                    else if (a3.try[i].toLowerCase().compareTo("tilt") == 0) {
                        this.ek = new Float(a3.new[i]) * super.cj;
                    }
                    else if (a3.try[i].toLowerCase().compareTo("fov") == 0) {
                        this.ex = new Float(a3.new[i]) * super.cj;
                    }
                }
            }
            else if (a3.a.toLowerCase().compareTo("limits") == 0) {
                for (int j = 0; j < a3.do; ++j) {
                    if (a3.try[j].toLowerCase().compareTo("maxpan") == 0) {
                        super.ed = new Float(a3.new[j]) * super.cj;
                        super.eg = true;
                    }
                    else if (a3.try[j].toLowerCase().compareTo("minpan") == 0) {
                        super.d3 = new Float(a3.new[j]) * super.cj;
                        super.eg = true;
                    }
                    else if (a3.try[j].toLowerCase().compareTo("maxtilt") == 0) {
                        super.dR = new Float(a3.new[j]) * super.cj;
                        super.ef = true;
                    }
                    else if (a3.try[j].toLowerCase().compareTo("mintilt") == 0) {
                        super.dM = new Float(a3.new[j]) * super.cj;
                        super.ef = true;
                    }
                    else if (a3.try[j].toLowerCase().compareTo("maxfov") == 0) {
                        super.dU = new Float(a3.new[j]) * super.cj;
                    }
                    else if (a3.try[j].toLowerCase().compareTo("minfov") == 0) {
                        super.dF = new Float(a3.new[j]) * super.cj;
                    }
                    else if (a3.try[j].toLowerCase().compareTo("lockzenithnadir") == 0 && a3.new[j].toLowerCase().compareTo("true") == 0) {
                        super.dC = true;
                    }
                }
                if (super.dF > super.dU) {
                    final float df = super.dF;
                    super.dF = super.dU;
                    super.dU = df;
                }
                if (super.dF < 0.01745) {
                    super.dF = 0.01745f;
                }
                if (super.dU > 2.5) {
                    super.dU = 2.5f;
                }
            }
            else if (a3.a.toLowerCase().compareTo("autopath") == 0) {
                super.d1 = true;
                for (int k = 0; k < a3.do; ++k) {
                    if (a3.try[k].toLowerCase().compareTo("play") == 0) {
                        if (a3.new[k].compareTo("false") == 0) {
                            super.d1 = false;
                        }
                    }
                    else if (a3.try[k].toLowerCase().compareTo("first") == 0) {
                        super.dD = (String.valueOf(a3.new[k]) + "\u0000").toCharArray();
                    }
                }
                for (a2 a4 = a3.if; a4 != null; a4 = a4.for) {
                    this.byte(a4);
                }
            }
        }
    }
    
    void if(final ab ab) {
        switch (ab.if) {
            case 0: {
                if (ab.i || ab.goto < 0 || ab.goto >= this.et || ab.else < 0 || ab.else >= this.ey) {
                    break;
                }
                if (ab.g == 5) {
                    this.ez = true;
                }
                else {
                    this.ez = false;
                }
                this.ew = true;
                this.eL = ab.goto;
                this.eJ = ab.else;
                this.eP = ab.goto;
                this.eO = ab.else;
                if (this.ez) {
                    ab.for = 2;
                    break;
                }
                ab.for = 1;
                break;
            }
            case 4: {
                if (!this.ew) {
                    break;
                }
                this.eP = ab.goto;
                this.eO = ab.else;
                if (this.ez) {
                    ab.for = 2;
                    break;
                }
                ab.for = 1;
                break;
            }
            case 1: {
                this.ew = false;
                if (ac.e > 0.0f) {
                    super.dN = true;
                }
                this.er = 0.0f;
                this.eB = 0.0f;
                this.eu = 0.0f;
                break;
            }
        }
    }
    
    boolean do(final int n) {
        return true;
    }
    
    public void new(final long n) {
        super.try = true;
        if (this.eo == null) {
            return;
        }
        if (this.eI != null) {
            this.eM = true;
            try {
                this.em.p = new int[this.et * this.ey];
                this.em.s = this.et;
                this.em.j = this.ey;
                this.em.h = 0;
                this.em.g = 0;
                this.em.i = this.et;
                this.em.t = this.ey;
                this.eD = this.et >> 1;
                this.en = this.ey >> 1;
                if (this.eI != null) {
                    this.eI.a(this.eo, this.em);
                }
            }
            catch (Exception ex) {
                this.eM = false;
            }
            catch (OutOfMemoryError outOfMemoryError) {
                this.eM = false;
            }
        }
    }
    
    public boolean a(final long en) {
        boolean if1 = super.if;
        super.if = false;
        while (super.cn > 3.141592653589793) {
            super.cn -= 6.283185307179586;
        }
        while (super.cn < -3.141592653589793) {
            super.cn += 6.283185307179586;
        }
        while (super.cg > 3.141592653589793) {
            super.cg -= 6.283185307179586;
        }
        while (super.cg < -3.141592653589793) {
            super.cg += 6.283185307179586;
        }
        if (!super.try) {
            return false;
        }
        if (this.es) {
            if (this.ew) {
                super.d1 = false;
                if (this.ez) {
                    final float ch = super.ch;
                    final float eh = (this.eO - this.eJ) * this.eK * (en - this.eN) * 2.0E-4f * super.ck.h.try;
                    this.eH = eh;
                    super.ch = ch + eh;
                    if1 = true;
                }
                else {
                    final float cn = super.cn;
                    final float ee = (this.eP - this.eL) * (en - this.eN) * this.eK * super.ch * super.ck.h.try * 4.0E-4f;
                    this.eE = ee;
                    super.cn = cn - ee;
                    final float cg = super.cg;
                    final float ej = (this.eO - this.eJ) * (en - this.eN) * this.eK * super.ch * super.ck.h.try * 4.0E-4f;
                    this.ej = ej;
                    super.cg = cg - ej;
                    if1 = true;
                }
            }
            else if (super.dN) {
                if (this.er != 0.0f || this.eB == 0.0f) {}
                final float n = ac.e * (40.0f / (en - this.eN));
                if (this.eE != 0.0f) {
                    this.eE *= n;
                }
                if (this.ej != 0.0f) {
                    this.ej *= n;
                }
                if (this.eH != 0.0f) {
                    this.eH *= n;
                }
                if (((this.eE > 0.0f) ? this.eE : (-this.eE)) < 0.001 && ((this.ej > 0.0f) ? this.ej : (-this.ej)) < 0.001 && ((this.eH > 0.0f) ? this.eH : (-this.eH)) < 0.001) {
                    super.dN = false;
                    this.eE = 0.0f;
                    this.ej = 0.0f;
                    this.eH = 0.0f;
                }
                super.cn -= this.eE;
                super.cg -= this.ej;
                super.ch += this.eH;
                if1 = true;
            }
            else {
                if1 |= this.try(en);
            }
        }
        else {
            this.es = true;
            if (!super.ck.H) {
                super.cn = this.ev;
                super.cg = this.ek;
                if (this.ex > 0.0f) {
                    super.ch = this.ex;
                }
            }
        }
        if (super.ch < super.dF) {
            super.ch = super.dF;
        }
        else if (super.ch > super.dU) {
            super.ch = super.dU;
        }
        if (super.ef || super.eg) {
            final float n2 = (float)Math.sqrt(this.en * this.en + this.eD * this.eD);
            float n3 = n2 / (float)Math.tan(super.ch * 0.5f);
            if (super.ef) {
                float n4 = (float)(2.0 * Math.atan2(this.en, n3));
                if (super.dR - super.dM < n4) {
                    n3 = (float)(this.en / Math.tan((super.dR - super.dM) * 0.5));
                    super.ch = (float)(2.0 * Math.atan2(n2, n3));
                    n4 = super.dR - super.dM;
                }
                if (super.cg + n4 * 0.5 > super.dR) {
                    super.cg = (float)(-n4 * 0.5 + super.dR);
                }
                if (super.cg - n4 * 0.5 < super.dM) {
                    super.cg = (float)(n4 * 0.5 + super.dM);
                }
            }
            if (super.eg) {
                float n5 = (float)(2.0 * Math.atan2(this.eD, n3));
                if (super.ed - super.d3 < n5) {
                    super.ch = (float)(2.0 * Math.atan2(n2, (float)(this.eD / Math.tan((super.ed - super.d3) * 0.5))));
                    n5 = super.ed - super.d3;
                }
                if (super.cn + n5 * 0.5 > super.ed) {
                    super.cn = (float)(-n5 * 0.5 + super.ed);
                }
                if (super.cn - n5 * 0.5 < super.d3) {
                    super.cn = (float)(n5 * 0.5 + super.d3);
                }
            }
        }
        if (super.void.for | super.dC) {
            while (super.cg > 3.141592653589793) {
                super.cg -= 6.283185307179586;
            }
            while (super.cg < -3.141592653589793) {
                super.cg += 6.283185307179586;
            }
            if (super.cg > 1.5707963267948966) {
                super.cg = 1.5707964f;
            }
            if (super.cg < -1.5707963267948966) {
                super.cg = -1.5707964f;
            }
        }
        this.eN = en;
        final boolean em = this.eM;
        if (this.eI != null && !(this.eM = this.eI.a(en))) {
            this.em = this.eQ;
        }
        return (if1 & super.do) | em;
    }
    
    public void new(final boolean b) {
        this.eC = (float)Math.cos(super.cn);
        this.eq = (float)Math.sin(super.cn);
        this.eA = (float)Math.cos(super.cg);
        this.eG = (float)Math.sin(super.cg);
        this.ep = (float)(Math.sqrt(this.et * this.et + this.ey * this.ey >> 2) / Math.tan(super.ch * 0.5));
    }
    
    public void u() {
        if (this.eI != null) {
            this.eI.a(super.void.else, this.eQ.h, this.eQ.g, this.eQ.s, this.eQ.j);
        }
    }
    
    public ad case(final boolean b) {
        try {
            this.em.p = new int[this.et * this.ey];
            this.em.s = this.et;
            this.em.j = this.ey;
            this.em.h = 0;
            this.em.g = 0;
            this.em.i = this.et;
            this.em.t = this.ey;
            this.eD = this.et >> 1;
            this.en = this.ey >> 1;
        }
        catch (Exception ex) {
            return null;
        }
        catch (OutOfMemoryError outOfMemoryError) {
            return null;
        }
        return this.em;
    }
    
    public void if(final ad eo) {
        this.eo = eo;
    }
    
    public void a(final ak ei) {
        this.eI = ei;
    }
}
