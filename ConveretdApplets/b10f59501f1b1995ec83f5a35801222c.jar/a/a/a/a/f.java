// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class f extends ao
{
    public float eU;
    public float eI;
    public float eS;
    public float eY;
    public float eH;
    private float eN;
    private float eC;
    private float eP;
    ad eG;
    ad eE;
    ad e8;
    float e2;
    boolean eO;
    boolean eR;
    int e3;
    int e1;
    int e7;
    int e6;
    long e5;
    boolean eK;
    private char[] eD;
    boolean e4;
    ak e0;
    float eW;
    float eB;
    float eZ;
    float eJ;
    float eT;
    float eM;
    boolean eX;
    int eL;
    int eQ;
    int eV;
    int eF;
    
    f(final m ca, final ac b, final float cz) {
        this.eN = 0.0f;
        this.eC = 0.0f;
        this.eP = -1.0f;
        this.eG = null;
        this.eE = null;
        this.e8 = null;
        this.e2 = 0.01f;
        this.eO = false;
        this.e5 = 0L;
        this.eK = false;
        this.eD = new char[] { 'c', 'a', 'm', 'e', 'r', 'a', '\0' };
        this.e4 = false;
        this.e0 = null;
        this.eW = 0.0f;
        this.eB = 0.0f;
        this.eZ = 0.0f;
        this.eJ = 0.0f;
        this.eT = 0.0f;
        this.eM = 0.0f;
        this.eX = false;
        super.else = true;
        super.b = b;
        super.cz = cz;
        super.cC = 1.0f / super.cz;
        super.cA = ca;
        super.f = this.eD;
        this.eE = new ad();
        this.e8 = new ad();
        super.ey = false;
        super.ex = false;
        super.case = this.eD;
        super.ej = false;
    }
    
    public void if() {
        if (this.eG != null) {
            this.eG.if();
        }
        this.eG = null;
        if (this.eE != null) {
            this.eE.if();
        }
        this.eE = null;
        if (this.e8 != null) {
            this.e8.if();
        }
        this.e8 = null;
    }
    
    void a(final ad ad) {
        this.e8.q = ad.q;
        this.e8.t = this.eL;
        this.e8.k = this.eQ;
        this.e8.i = ad.i;
        this.e8.h = ad.h;
        this.e8.j = ad.j;
        this.e8.u = ad.u;
        this.eV = this.eL >> 1;
        this.eF = this.eQ >> 1;
        if (!this.e4) {
            this.eE.q = ad.q;
            this.eE.t = this.eL;
            this.eE.k = this.eQ;
            this.eE.i = ad.i;
            this.eE.h = ad.h;
            this.eE.j = ad.j;
            this.eE.u = ad.u;
            this.eV = this.eL >> 1;
            this.eF = this.eQ >> 1;
        }
    }
    
    public void int(final a2 a2) {
        for (a2 a3 = a2.if; a3 != null; a3 = a3.for) {
            if (a3.a.toLowerCase().compareTo("entrypoint") == 0) {
                for (int i = 0; i < a3.do; ++i) {
                    if (a3.try[i].toLowerCase().compareTo("pan") == 0) {
                        this.eN = new Float(a3.new[i]) * super.cz;
                    }
                    else if (a3.try[i].toLowerCase().compareTo("tilt") == 0) {
                        this.eC = new Float(a3.new[i]) * super.cz;
                    }
                    else if (a3.try[i].toLowerCase().compareTo("fov") == 0) {
                        this.eP = new Float(a3.new[i]) * super.cz;
                    }
                }
            }
            else if (a3.a.toLowerCase().compareTo("limits") == 0) {
                for (int j = 0; j < a3.do; ++j) {
                    if (a3.try[j].toLowerCase().compareTo("maxpan") == 0) {
                        super.ev = new Float(a3.new[j]) * super.cz;
                        super.ey = true;
                    }
                    else if (a3.try[j].toLowerCase().compareTo("minpan") == 0) {
                        super.el = new Float(a3.new[j]) * super.cz;
                        super.ey = true;
                    }
                    else if (a3.try[j].toLowerCase().compareTo("maxtilt") == 0) {
                        super.d9 = new Float(a3.new[j]) * super.cz;
                        super.ex = true;
                    }
                    else if (a3.try[j].toLowerCase().compareTo("mintilt") == 0) {
                        super.d4 = new Float(a3.new[j]) * super.cz;
                        super.ex = true;
                    }
                    else if (a3.try[j].toLowerCase().compareTo("maxfov") == 0) {
                        super.ec = new Float(a3.new[j]) * super.cz;
                    }
                    else if (a3.try[j].toLowerCase().compareTo("minfov") == 0) {
                        super.dX = new Float(a3.new[j]) * super.cz;
                    }
                    else if (a3.try[j].toLowerCase().compareTo("lockzenithnadir") == 0 && a3.new[j].toLowerCase().compareTo("true") == 0) {
                        super.dU = true;
                    }
                }
                if (super.dX > super.ec) {
                    final float dx = super.dX;
                    super.dX = super.ec;
                    super.ec = dx;
                }
                if (super.dX < 0.01745) {
                    super.dX = 0.01745f;
                }
                if (super.ec > 2.5) {
                    super.ec = 2.5f;
                }
            }
            else if (a3.a.toLowerCase().compareTo("autopath") == 0) {
                super.ej = true;
                for (int k = 0; k < a3.do; ++k) {
                    if (a3.try[k].toLowerCase().compareTo("play") == 0) {
                        if (a3.new[k].compareTo("false") == 0) {
                            super.ej = false;
                        }
                    }
                    else if (a3.try[k].toLowerCase().compareTo("first") == 0) {
                        super.dV = (String.valueOf(a3.new[k]) + "\u0000").toCharArray();
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
                if (ab.i || ab.goto < 0 || ab.goto >= this.eL || ab.else < 0 || ab.else >= this.eQ) {
                    break;
                }
                if (ab.g == 5) {
                    this.eR = true;
                }
                else {
                    this.eR = false;
                }
                this.eO = true;
                this.e3 = ab.goto;
                this.e1 = ab.else;
                this.e7 = ab.goto;
                this.e6 = ab.else;
                if (this.eR) {
                    ab.for = 2;
                    break;
                }
                ab.for = 1;
                break;
            }
            case 4: {
                if (!this.eO) {
                    break;
                }
                this.e7 = ab.goto;
                this.e6 = ab.else;
                if (this.eR) {
                    ab.for = 2;
                    break;
                }
                ab.for = 1;
                break;
            }
            case 1: {
                this.eO = false;
                if (ac.g > 0.0f) {
                    super.d5 = true;
                }
                this.eJ = 0.0f;
                this.eT = 0.0f;
                this.eM = 0.0f;
                break;
            }
        }
    }
    
    boolean do(final int n) {
        return true;
    }
    
    public void new(final long n) {
        super.byte = true;
        if (this.eG == null) {
            return;
        }
        if (this.e0 != null) {
            this.e4 = true;
            try {
                this.eE.q = new int[this.eL * this.eQ];
                this.eE.t = this.eL;
                this.eE.k = this.eQ;
                this.eE.i = 0;
                this.eE.h = 0;
                this.eE.j = this.eL;
                this.eE.u = this.eQ;
                this.eV = this.eL >> 1;
                this.eF = this.eQ >> 1;
                if (this.e0 != null) {
                    this.e0.a(this.eG, this.eE);
                }
            }
            catch (Exception ex) {
                this.e4 = false;
            }
            catch (OutOfMemoryError outOfMemoryError) {
                this.e4 = false;
            }
        }
    }
    
    public boolean a(final long e5) {
        boolean do1 = super.do;
        super.do = false;
        while (super.cD > 6.283185307179586) {
            super.cD -= 6.283185307179586;
        }
        while (super.cD < -6.283185307179586) {
            super.cD += 6.283185307179586;
        }
        while (super.cw > 3.141592653589793) {
            super.cw -= 6.283185307179586;
        }
        while (super.cw < -3.141592653589793) {
            super.cw += 6.283185307179586;
        }
        if (!super.byte) {
            return false;
        }
        if (this.eK) {
            if (this.eO) {
                super.ej = false;
                if (this.eR) {
                    final float cx = super.cx;
                    final float ez = (this.e6 - this.e1) * this.e2 * (e5 - this.e5) * 2.0E-4f * super.cA.i.byte;
                    this.eZ = ez;
                    super.cx = cx + ez;
                    do1 = true;
                }
                else {
                    final float cd = super.cD;
                    final float ew = (this.e7 - this.e3) * (e5 - this.e5) * this.e2 * super.cx * super.cA.i.byte * 4.0E-4f;
                    this.eW = ew;
                    super.cD = cd - ew;
                    final float cw = super.cw;
                    final float eb = (this.e6 - this.e1) * (e5 - this.e5) * this.e2 * super.cx * super.cA.i.byte * 4.0E-4f;
                    this.eB = eb;
                    super.cw = cw - eb;
                    do1 = true;
                }
            }
            else if (super.d5) {
                float g = ac.g * (40.0f / (e5 - this.e5));
                if (g > 1.0f) {
                    g = ac.g;
                }
                if (this.eW != 0.0f) {
                    this.eW *= g;
                }
                if (this.eB != 0.0f) {
                    this.eB *= g;
                }
                if (this.eZ != 0.0f) {
                    this.eZ *= g;
                }
                if (((this.eW > 0.0f) ? this.eW : (-this.eW)) < 0.001 && ((this.eB > 0.0f) ? this.eB : (-this.eB)) < 0.001 && ((this.eZ > 0.0f) ? this.eZ : (-this.eZ)) < 0.001) {
                    super.d5 = false;
                    this.eW = 0.0f;
                    this.eB = 0.0f;
                    this.eZ = 0.0f;
                }
                super.cD -= this.eW;
                super.cw -= this.eB;
                super.cx += this.eZ;
                do1 = true;
            }
            else {
                do1 |= this.try(e5);
            }
        }
        else {
            this.eK = true;
            if (!super.cA.P) {
                super.cD = this.eN;
                super.cw = this.eC;
                if (this.eP > 0.0f) {
                    super.cx = this.eP;
                }
            }
        }
        if (super.cx < super.dX) {
            super.cx = super.dX;
        }
        else if (super.cx > super.ec) {
            super.cx = super.ec;
        }
        if (super.ex || super.ey) {
            final float n = (float)Math.sqrt(this.eF * this.eF + this.eV * this.eV);
            float n2 = n / (float)Math.tan(super.cx * 0.5f);
            if (super.ex) {
                float n3 = (float)(2.0 * Math.atan2(this.eF, n2));
                if (super.d9 - super.d4 < n3) {
                    n2 = (float)(this.eF / Math.tan((super.d9 - super.d4) * 0.5));
                    super.cx = (float)(2.0 * Math.atan2(n, n2));
                    n3 = super.d9 - super.d4;
                }
                if (super.cw + n3 * 0.5 > super.d9) {
                    super.cw = (float)(-n3 * 0.5 + super.d9);
                }
                if (super.cw - n3 * 0.5 < super.d4) {
                    super.cw = (float)(n3 * 0.5 + super.d4);
                }
            }
            if (super.ey) {
                float n4 = (float)(2.0 * Math.atan2(this.eV, n2));
                if (super.ev - super.el < n4) {
                    super.cx = (float)(2.0 * Math.atan2(n, (float)(this.eV / Math.tan((super.ev - super.el) * 0.5))));
                    n4 = super.ev - super.el;
                }
                if (super.cD + n4 * 0.5 > super.ev) {
                    super.cD = (float)(-n4 * 0.5 + super.ev);
                }
                if (super.cD - n4 * 0.5 < super.el) {
                    super.cD = (float)(n4 * 0.5 + super.el);
                }
            }
        }
        if (super.b.for | super.dU) {
            while (super.cw > 3.141592653589793) {
                super.cw -= 6.283185307179586;
            }
            while (super.cw < -3.141592653589793) {
                super.cw += 6.283185307179586;
            }
            if (super.cw > 1.5707963267948966) {
                super.cw = 1.5707964f;
            }
            if (super.cw < -1.5707963267948966) {
                super.cw = -1.5707964f;
            }
        }
        this.e5 = e5;
        final boolean e6 = this.e4;
        if (this.e0 != null && !(this.e4 = this.e0.a(e5))) {
            this.eE = this.e8;
        }
        return (do1 & super.for) | e6;
    }
    
    public void new(final boolean b) {
        this.eU = (float)Math.cos(super.cD);
        this.eI = (float)Math.sin(super.cD);
        this.eS = (float)Math.cos(super.cw);
        this.eY = (float)Math.sin(super.cw);
        this.eH = (float)(Math.sqrt(this.eL * this.eL + this.eQ * this.eQ >> 2) / Math.tan(super.cx * 0.5));
    }
    
    public void w() {
        if (this.e0 != null) {
            this.e0.a(super.b.goto, this.e8.i, this.e8.h, this.e8.t, this.e8.k);
        }
    }
    
    public ad case(final boolean b) {
        try {
            this.eE.q = new int[this.eL * this.eQ];
            this.eE.t = this.eL;
            this.eE.k = this.eQ;
            this.eE.i = 0;
            this.eE.h = 0;
            this.eE.j = this.eL;
            this.eE.u = this.eQ;
            this.eV = this.eL >> 1;
            this.eF = this.eQ >> 1;
        }
        catch (Exception ex) {
            return null;
        }
        catch (OutOfMemoryError outOfMemoryError) {
            return null;
        }
        return this.eE;
    }
    
    public void if(final ad eg) {
        this.eG = eg;
    }
    
    public void a(final ak e0) {
        this.e0 = e0;
    }
}
