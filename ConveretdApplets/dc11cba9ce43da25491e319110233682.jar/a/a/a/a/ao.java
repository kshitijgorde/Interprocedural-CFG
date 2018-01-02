// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class ao extends e
{
    protected a0 dX;
    boolean ek;
    protected float ew;
    protected float em;
    protected float ea;
    protected float d5;
    protected float dY;
    protected float ed;
    boolean ez;
    boolean ey;
    boolean eb;
    int ej;
    int eh;
    int eq;
    char[] dW;
    int ex;
    int ei;
    int ep;
    e[] et;
    e[] d3;
    e[] ee;
    float[][] en;
    a5[] d1;
    int d8;
    static final char[] dZ;
    boolean d6;
    aq d0;
    aq dU;
    float ec;
    float d2;
    float eo;
    static final char[] eA;
    static final char[] es;
    static final char[] er;
    static final char[] eg;
    static final char[] d7;
    static final char[] eB;
    static final char[] el;
    static final char[] ev;
    static final char[] d4;
    int eu;
    boolean dV;
    aq[][] d9;
    aq[][] ef;
    
    static {
        dZ = new char[1];
        eA = new char[] { 'm', 'a', 'x', 't', 'i', 'l', 't', '\0' };
        es = new char[] { 'm', 'i', 'n', 't', 'i', 'l', 't', '\0' };
        er = new char[] { 'm', 'a', 'x', 'p', 'a', 'n', '\0' };
        eg = new char[] { 'm', 'i', 'n', 'p', 'a', 'n', '\0' };
        d7 = new char[] { 'm', 'a', 'x', 'f', 'o', 'v', '\0' };
        eB = new char[] { 'm', 'i', 'n', 'f', 'o', 'v', '\0' };
        el = new char[] { 'r', 'm', 't', 'i', 'l', 't', '\0' };
        ev = new char[] { 'r', 'm', 'p', 'a', 'n', '\0' };
        d4 = new char[] { 'l', 'o', 'c', 'k', 'z', 'e', 'n', 'i', 't', 'h', 'n', 'a', 'd', 'i', 'r' };
    }
    
    public void if() {
        super.if();
        if (this.en != null) {
            for (int i = 0; i < this.en.length; ++i) {
                this.en[i] = null;
            }
            this.en = null;
        }
        if (this.d1 != null) {
            for (int j = 0; j < this.d1.length; ++j) {
                this.d1[j] = null;
            }
            this.d1 = null;
        }
        if (this.d9 != null) {
            for (int k = 0; k < this.d9.length; ++k) {
                this.d9[k] = null;
            }
            this.d9 = null;
        }
        if (this.ef != null) {
            for (int l = 0; l < this.ef.length; ++l) {
                this.ef[l] = null;
            }
            this.ef = null;
        }
        if (this.dX != null) {
            this.dX.if();
            this.dX = null;
        }
        for (int n = 0; n < this.ei; ++n) {
            if (this.d3[n] != null) {
                this.d3[n].if();
            }
            this.d3[n] = null;
        }
        this.ei = 0;
        this.d3 = null;
        for (int n2 = 0; n2 < this.ex; ++n2) {
            if (this.et[n2] != null) {
                this.et[n2].if();
            }
            this.et[n2] = null;
        }
        this.ex = 0;
        this.et = null;
        for (int n3 = 0; n3 < this.ep; ++n3) {
            if (this.ee[n3] != null) {
                this.ee[n3].if();
            }
            this.ee[n3] = null;
        }
        this.ep = 0;
        this.ee = null;
    }
    
    public void a(final ac ac, final float n, final ae ae, final f f, final m m) {
    }
    
    ao() {
        this.dX = null;
        this.ek = true;
        this.ew = 0.0f;
        this.em = 0.0f;
        this.ea = 0.0f;
        this.d5 = 0.0f;
        this.dY = 0.01745f;
        this.ed = 2.5f;
        this.ez = true;
        this.ey = true;
        this.eb = false;
        this.eq = -1;
        this.dW = new char[1];
        this.ex = 0;
        this.ei = 0;
        this.ep = 0;
        this.et = null;
        this.d3 = null;
        this.ee = null;
        this.d1 = null;
        this.d8 = 0;
        this.d6 = false;
        this.d0 = null;
        this.dU = null;
        this.ec = 0.0f;
        this.d2 = 0.0f;
        this.eo = 0.0f;
        this.eu = 1;
        this.dV = false;
        this.d9 = null;
        this.ef = null;
        this.d0 = new aq();
        this.dU = new aq();
        super.new = new a3();
    }
    
    public void int(final a2 a2) {
        this.en = super.b.if();
        for (int i = 0; i < a2.do; ++i) {
            if (a2.try[i].toLowerCase().compareTo("id") == 0) {
                super.f = (String.valueOf(a2.new[i]) + "\u0000").toCharArray();
            }
            else if (a2.try[i].toLowerCase().compareTo("layer") == 0) {
                super.d = new Integer(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("pan") == 0) {
                super.cE = new Float(a2.new[i]) * super.cA;
            }
            else if (a2.try[i].toLowerCase().compareTo("tilt") == 0) {
                super.cx = new Float(a2.new[i]) * super.cA;
            }
            else if (a2.try[i].toLowerCase().compareTo("roll") == 0) {
                super.cz = new Float(a2.new[i]) * super.cA;
            }
            else if (a2.try[i].toLowerCase().compareTo("visible") == 0) {
                if (a2.new[i].compareTo("false") == 0) {
                    super.for = false;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("maxpan") == 0) {
                this.ew = new Float(a2.new[i]) * super.cA;
            }
            else if (a2.try[i].toLowerCase().compareTo("minpan") == 0) {
                this.em = new Float(a2.new[i]) * super.cA;
            }
            else if (a2.try[i].toLowerCase().compareTo("maxtilt") == 0) {
                this.ea = new Float(a2.new[i]) * super.cA;
            }
            else if (a2.try[i].toLowerCase().compareTo("mintilt") == 0) {
                this.d5 = new Float(a2.new[i]) * super.cA;
            }
        }
        this.a();
        for (a2 a3 = a2.if; a3 != null; a3 = a3.for) {
            if (a3.a.toLowerCase().compareTo("image") == 0) {
                (this.dX = new a0(super.b, super.long, super.cB.Q, super.goto)).a(a3);
                super.cB.a(this.dX);
            }
            else if (a3.a.toLowerCase().compareTo("autopath") == 0) {
                for (int j = 0; j < a3.do; ++j) {
                    if (a3.try[j].toLowerCase().compareTo("play") == 0) {
                        if (a3.new[j].compareTo("false") == 0) {
                            this.ek = false;
                        }
                    }
                    else if (a3.try[j].toLowerCase().compareTo("first") == 0) {
                        this.dW = (String.valueOf(a3.new[j]) + "\u0000").toCharArray();
                    }
                }
                for (a2 a4 = a3.if; a4 != null; a4 = a4.for) {
                    this.byte(a4);
                }
            }
        }
        for (a2 a5 = a2.if; a5 != null; a5 = a5.for) {
            if (a5.a.toLowerCase().compareTo("image") != 0 && a5.a.toLowerCase().compareTo("autopath") != 0) {
                this.a(a5, super.goto);
            }
        }
    }
    
    private void a(final a2 a2, final boolean goto1) {
        try {
            if (a2.a.toLowerCase().compareTo("hotspotpoint") == 0) {
                this.s();
                (this.et[this.ex] = (e)Class.forName("a.a.a.a.bh").getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null)).a(super.b, super.cA, super.long, super.cC, super.cB);
                super.cB.a(this.et[this.ex]);
                this.et[this.ex].goto = goto1;
                this.et[this.ex].int(a2);
                ++this.ex;
            }
            else if (a2.a.toLowerCase().compareTo("hotspotrectangle") == 0) {
                this.s();
                (this.et[this.ex] = (e)Class.forName("a.a.a.a.bs").getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null)).a(super.b, super.cA, super.long, super.cC, super.cB);
                super.cB.a(this.et[this.ex]);
                this.et[this.ex].goto = goto1;
                this.et[this.ex].int(a2);
                ++this.ex;
            }
            else if (a2.a.toLowerCase().compareTo("hotspotpolygonal") == 0) {
                this.s();
                (this.et[this.ex] = (e)Class.forName("a.a.a.a.n").getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null)).a(super.b, super.cA, super.long, super.cC, super.cB);
                super.cB.a(this.et[this.ex]);
                this.et[this.ex].goto = goto1;
                this.et[this.ex].int(a2);
                ++this.ex;
            }
            else if (a2.a.toLowerCase().compareTo("light") == 0) {
                this.q();
                (this.d3[this.ei] = (e)Class.forName("a.a.a.a.at").getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null)).a(super.b, super.cA, super.long, super.cC, super.cB);
                super.cB.a(this.d3[this.ei]);
                this.d3[this.ei].goto = goto1;
                this.d3[this.ei].int(a2);
                ++this.ei;
            }
            else if (a2.a.toLowerCase().compareTo("sound") == 0) {
                this.p();
                (this.ee[this.ep] = (e)Class.forName("a.a.a.a.as").getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null)).a(super.b, super.cA, super.long, super.cC, super.cB);
                super.cB.a(this.ee[this.ep]);
                this.ee[this.ep].goto = goto1;
                this.ee[this.ep].int(a2);
                ++this.ep;
            }
        }
        catch (Exception ex) {
            System.out.println("Can't load missing functionnality :" + a2.a);
        }
    }
    
    protected void byte(final a2 a2) {
        if (a2.a.toLowerCase().compareTo("moveto") == 0 || a2.a.toLowerCase().compareTo("move") == 0 || a2.a.toLowerCase().compareTo("sleep") == 0 || a2.a.toLowerCase().compareTo("action") == 0) {
            this.t();
            this.d1[this.d8] = new a5();
            if (a2.a.toLowerCase().compareTo("moveto") == 0) {
                this.d1[this.d8].do = 0;
            }
            else if (a2.a.toLowerCase().compareTo("move") == 0) {
                this.d1[this.d8].do = 1;
            }
            else if (a2.a.toLowerCase().compareTo("sleep") == 0) {
                this.d1[this.d8].do = 2;
            }
            else if (a2.a.toLowerCase().compareTo("action") == 0 && a2.case != null) {
                this.d1[this.d8].do = 3;
                this.d1[this.d8].h = (String.valueOf(a2.case.do) + "\u0000").toCharArray();
            }
            for (int i = 0; i < a2.do; ++i) {
                if (a2.try[i].toLowerCase().compareTo("pan") == 0) {
                    this.d1[this.d8].b = new Float(a2.new[i]) * super.cA;
                    this.d1[this.d8].k = true;
                }
                else if (a2.try[i].toLowerCase().compareTo("tilt") == 0) {
                    this.d1[this.d8].c = new Float(a2.new[i]) * super.cA;
                    this.d1[this.d8].f = true;
                }
                else if (a2.try[i].toLowerCase().compareTo("roll") == 0) {
                    this.d1[this.d8].l = new Float(a2.new[i]) * super.cA;
                    this.d1[this.d8].o = true;
                }
                else if (a2.try[i].toLowerCase().compareTo("fov") == 0) {
                    this.d1[this.d8].g = new Float(a2.new[i]) * super.cA;
                    this.d1[this.d8].p = true;
                }
                else if (a2.try[i].toLowerCase().compareTo("time") == 0) {
                    this.d1[this.d8].new = (long)(new Float(a2.new[i]) * 1000.0f);
                    this.d1[this.d8].m = 0;
                }
                else if (a2.try[i].toLowerCase().compareTo("speed") == 0) {
                    this.d1[this.d8].i = new Float(a2.new[i]) * super.cA;
                    this.d1[this.d8].m = 1;
                }
                else if (a2.try[i].toLowerCase().compareTo("amid") == 0 || a2.try[i].toLowerCase().compareTo("apid") == 0) {
                    this.d1[this.d8].long = (String.valueOf(a2.new[i]) + "\u0000").toCharArray();
                }
                else if (a2.try[i].toLowerCase().compareTo("next") == 0) {
                    this.d1[this.d8].void = (String.valueOf(a2.new[i]) + "\u0000").toCharArray();
                }
            }
            ++this.d8;
        }
    }
    
    void a(final aq[][] array, final aq[][] array2, final int n) {
        if (array != null && array2 != null) {
            super.b.a(super.cE, super.cx, super.cz, this.en);
            for (int i = 0; i < this.eu; ++i) {
                for (int j = 0; j < this.ej * this.eh; ++j) {
                    super.b.a(this.en, array[i][j], array2[i][j]);
                }
            }
        }
        this.n();
    }
    
    void n() {
        for (int i = 0; i < this.ei; ++i) {
            this.d3[i].a(this.en);
        }
        for (int j = 0; j < this.ex; ++j) {
            this.et[j].a(this.en);
        }
        for (int k = 0; k < this.ep; ++k) {
            this.ee[k].a(this.en);
        }
    }
    
    boolean byte(final boolean b) {
        super.byte = true;
        for (int i = 0; i < this.ex; ++i) {
            if (!this.et[i].byte) {
                super.byte = false;
            }
        }
        for (int j = 0; j < this.ei; ++j) {
            if (!this.d3[j].byte) {
                super.byte = false;
            }
        }
        return (b && this.dX != null && this.dX.j) || ((this.dX == null || this.dX.byte) && super.byte);
    }
    
    void new(final long n) {
        for (int i = 0; i < this.ex; ++i) {
            this.et[i].new(n);
        }
        for (int j = 0; j < this.ei; ++j) {
            this.d3[j].new(n);
        }
        for (int k = 0; k < this.ep; ++k) {
            this.ee[k].new(n);
        }
        if (this.dX != null) {
            this.dX.if(n);
        }
        super.goto = true;
    }
    
    public boolean a(final long n) {
        super.else = false;
        boolean b = false;
        if (this.dX != null) {
            b |= this.dX.a(n);
            super.byte = this.dX.byte;
        }
        else {
            super.byte = true;
        }
        boolean b2;
        if (super.byte && super.goto) {
            if ((this.dX != null && this.dX.ah) || super.c) {
                if (this.dX != null) {
                    this.dX.ah = false;
                }
                this.r();
                if (super.cE != 0.0f || super.cx != 0.0f || super.cz != 0.0f) {
                    this.a(this.ef, this.d9, this.ej * this.eh);
                }
                super.c = false;
                b = true;
            }
            if (this.ec != super.cE || this.d2 != super.cx || this.eo != super.cz) {
                this.a(this.ef, this.d9, this.ej * this.eh);
                b = true;
            }
            if (this.try(n)) {
                this.a(this.ef, this.d9, this.ej * this.eh);
                b = true;
            }
            this.ec = super.cE;
            this.d2 = super.cx;
            this.eo = super.cz;
            b2 = (b | this.byte(n));
        }
        else {
            b2 = (b | this.byte(n));
        }
        if (this.dX != null) {
            super.else &= this.dX.else;
        }
        final boolean do1 = super.do;
        super.do = false;
        this.l();
        return (b2 & super.for) | do1;
    }
    
    boolean try(final long n) {
        boolean b = false;
        if (this.ek) {
            if ((this.eq == -1 || (this.d1[this.eq].int != 0L && this.d1[this.eq].int < n)) && (this.eq == -1 || ((this.d1[this.eq].do != 0 || this.d1[this.eq].int == 1L) && this.d1[this.eq].do != 3))) {
                this.if(n, false);
                if (this.eq == -1) {
                    return false;
                }
            }
            if (this.d1[this.eq].do == 1) {
                final float n2 = 1000.0f / (n - this.d1[this.eq].q);
                this.d1[this.eq].q = n;
                if (this.d1[this.eq].k) {
                    super.cE += this.d1[this.eq].b / n2;
                }
                if (this.d1[this.eq].f) {
                    super.cx += this.d1[this.eq].c / n2;
                }
                if (this.d1[this.eq].o) {
                    super.cz += this.d1[this.eq].l / n2;
                }
                if (this.d1[this.eq].p) {
                    super.cy += this.d1[this.eq].g / n2;
                }
                b = true;
            }
            else if (this.d1[this.eq].do == 0) {
                float n3;
                if (this.d1[this.eq].new == 0L || this.d1[this.eq].int < n || n - this.d1[this.eq].q >= this.d1[this.eq].int - n) {
                    n3 = 0.0f;
                    this.d1[this.eq].int = 1L;
                }
                else {
                    n3 = (this.d1[this.eq].int - n) / (n - this.d1[this.eq].q);
                }
                this.d1[this.eq].q = n;
                if (this.d1[this.eq].k) {
                    super.cE = super.b.if(super.cE, this.d1[this.eq].b);
                    if (n3 == 0.0f) {
                        super.cE = this.d1[this.eq].b;
                    }
                    else {
                        super.cE += (this.d1[this.eq].b - super.cE) / n3;
                    }
                }
                if (this.d1[this.eq].f) {
                    super.cx = super.b.if(super.cx, this.d1[this.eq].c);
                    if (n3 == 0.0f) {
                        super.cx = this.d1[this.eq].c;
                    }
                    else {
                        super.cx += (this.d1[this.eq].c - super.cx) / n3;
                    }
                }
                if (this.d1[this.eq].o) {
                    super.cz = super.b.if(super.cz, this.d1[this.eq].l);
                    if (n3 == 0.0f) {
                        super.cz = this.d1[this.eq].l;
                    }
                    else {
                        super.cz += (this.d1[this.eq].l - super.cz) / n3;
                    }
                }
                if (this.d1[this.eq].p) {
                    super.cy = super.b.if(super.cy, this.d1[this.eq].g);
                    if (n3 == 0.0f) {
                        super.cy = this.d1[this.eq].g;
                    }
                    else {
                        super.cy += (this.d1[this.eq].g - super.cy) / n3;
                    }
                }
                b = true;
            }
            else if (this.d1[this.eq].do == 3) {
                super.cB.if(this.d1[this.eq].h);
                this.if(n, false);
            }
        }
        return b;
    }
    
    private void if(final long q, final boolean b) {
        if (!b || this.eq == -1 || this.eq >= this.d8) {
            if (this.eq == -1 || this.eq >= this.d8) {
                if (g.a(this.dW) == 0) {
                    this.eq = 0;
                }
                else {
                    for (int i = 0; i < this.d8; ++i) {
                        if (g.if(this.dW, this.d1[i].long) == 0) {
                            this.eq = i;
                        }
                    }
                }
                if (this.eq >= this.d8 || this.eq == -1) {
                    this.ek = false;
                    this.eq = -1;
                    return;
                }
            }
            else {
                if (g.if(this.d1[this.eq].void, ao.dZ) == 0) {
                    this.eq = -1;
                    this.ek = false;
                    return;
                }
                boolean b2 = false;
                for (int j = 0; j < this.d8; ++j) {
                    if (g.if(this.d1[this.eq].void, this.d1[j].long) == 0) {
                        this.eq = j;
                        b2 = true;
                        break;
                    }
                }
                if (!b2) {
                    this.eq = -1;
                    this.ek = false;
                    return;
                }
            }
        }
        this.d1[this.eq].q = q;
        if (this.d1[this.eq].do == 1) {
            if (this.d1[this.eq].new == 0L) {
                this.d1[this.eq].int = 0L;
                this.d1[this.eq].q = q - 40L;
            }
            else {
                this.d1[this.eq].int = q + this.d1[this.eq].new + 40L;
            }
        }
        else if (this.d1[this.eq].do == 0) {
            if (this.d1[this.eq].m == 1) {
                float n = 0.0f;
                if (this.d1[this.eq].k) {
                    n = Math.abs(this.d1[this.eq].b - super.b.if(super.cE, this.d1[this.eq].b));
                }
                if (this.d1[this.eq].f) {
                    n = Math.max(n, Math.abs(this.d1[this.eq].c - super.b.if(super.cx, this.d1[this.eq].c)));
                }
                if (this.d1[this.eq].o) {
                    n = Math.max(n, Math.abs(this.d1[this.eq].l - super.b.if(super.cz, this.d1[this.eq].l)));
                }
                if (this.d1[this.eq].p) {
                    n = Math.max(n, Math.abs(this.d1[this.eq].g - super.b.if(super.cy, this.d1[this.eq].g)));
                }
                this.d1[this.eq].new = (long)(n * 1000.0f / this.d1[this.eq].i);
            }
            if (this.d1[this.eq].new == 0L) {
                this.d1[this.eq].int = 0L;
            }
            else {
                this.d1[this.eq].int = q + this.d1[this.eq].new + 40L;
            }
        }
        else {
            if (this.d1[this.eq].do == 3) {
                this.d1[this.eq].int = 1L;
                return;
            }
            this.d1[this.eq].int = q + this.d1[this.eq].new;
        }
    }
    
    boolean byte(final long n) {
        boolean b = false;
        boolean else1 = true;
        for (int i = 0; i < this.ei; ++i) {
            b |= this.d3[i].a(n);
            else1 &= this.d3[i].byte;
        }
        for (int j = 0; j < this.ex; ++j) {
            b |= this.et[j].a(n);
            else1 &= this.et[j].byte;
        }
        for (int k = 0; k < this.ep; ++k) {
            b |= this.ee[k].a(n);
        }
        super.else = else1;
        return b;
    }
    
    void o() {
        for (int i = 0; i < this.ei; ++i) {
            this.d3[i].new(false);
        }
        for (int j = 0; j < this.ex; ++j) {
            this.et[j].new(false);
        }
        for (int k = 0; k < this.ep; ++k) {
            this.ee[k].new(false);
        }
    }
    
    public void new(final boolean b) {
        if (super.goto && super.byte && super.for) {
            if (this.dX != null && this.dX.for && !super.c) {
                this.dX.for();
                for (int i = 0; i < this.eu; ++i) {
                    super.b.W.a(this.d9[i], this.ej, this.eh, this.dX, super.cC, b);
                }
            }
            this.o();
        }
    }
    
    private void p() {
        if (this.ee == null || this.ee.length == this.ep) {
            final e[] ee = new e[this.ep + 10];
            for (int i = 0; i < this.ep; ++i) {
                ee[i] = this.ee[i];
            }
            this.ee = ee;
        }
    }
    
    private void q() {
        if (this.d3 == null || this.d3.length == this.ei) {
            final e[] d3 = new e[this.ei + 10];
            for (int i = 0; i < this.ei; ++i) {
                d3[i] = this.d3[i];
            }
            this.d3 = d3;
        }
    }
    
    private void s() {
        if (this.et == null || this.et.length == this.ex) {
            final e[] et = new e[this.ex + 10];
            for (int i = 0; i < this.ex; ++i) {
                et[i] = this.et[i];
            }
            this.et = et;
        }
    }
    
    private void t() {
        if (this.d1 == null || this.d1.length == this.d8) {
            final a5[] d1 = new a5[this.d8 + 10];
            for (int i = 0; i < this.d8; ++i) {
                d1[i] = this.d1[i];
            }
            this.d1 = d1;
        }
    }
    
    protected final aq if(final int n, final int n2) {
        final double n3 = super.cC.eI * super.cC.eZ;
        final double n4 = super.cC.eI * super.cC.eV * super.cC.eT;
        final double n5 = super.cC.eI * super.cC.eJ * super.cC.eT;
        final int n6 = n - (super.cC.eM >> 1);
        final int n7 = n2 - (super.cC.eR >> 1);
        this.dU.try = (float)(n4 + n7 * super.cC.eZ * super.cC.eV + n6 * super.cC.eJ);
        this.dU.if = (float)(n5 + n7 * super.cC.eZ * super.cC.eJ - n6 * super.cC.eV);
        this.dU.byte = (float)(n3 - n7 * super.cC.eT);
        final float[][] if1 = super.b.if();
        super.b.a(super.cE, super.cx, super.cz, if1);
        super.b.a(if1, this.dU, this.d0);
        return this.d0;
    }
    
    protected boolean do(final int n, final int n2) {
        return false;
    }
    
    void if(final ab ab) {
        final boolean i = ab.i;
        if (!super.for) {
            ab.i = true;
        }
        for (int j = 0; j < this.ex; ++j) {
            this.et[j].if(ab);
        }
        if (!super.for) {
            ab.i = i;
        }
        if (!ab.i && ab.if == 0 && this.do(ab.goto, ab.else)) {
            ab.i = true;
        }
    }
    
    public void a(final char[] array, final a3 a3) {
        if (g.do(array, ac.M) == 0) {
            super.cE = al.do(a3) * super.cA;
        }
        else if (g.do(array, ac.byte) == 0) {
            super.cx = al.do(a3) * super.cA;
        }
        else if (g.do(array, ac.ax) == 0) {
            super.cz = al.do(a3) * super.cA;
        }
        else if (g.do(array, ac.N) == 0) {
            super.cy = al.do(a3) * super.cA;
        }
        else if (g.do(array, ac.ay) == 0) {
            super.d = al.if(a3);
            super.b.if(super.cB.aa, 0, super.cB.U - 1);
        }
        else if (g.do(array, ac.Q) == 0) {
            if (!(super.for = al.a(a3))) {
                for (int i = 0; i < this.ep; ++i) {
                    this.ee[i].try(super.for);
                }
            }
            super.do = true;
        }
        else if (g.do(array, ac.ae) == 0) {
            this.ek = al.a(a3);
            if (this.ek) {
                this.if(System.currentTimeMillis(), true);
                this.d6 = false;
            }
        }
        else if (g.do(array, ac.n) == 0) {
            if (a3.char != 4) {
                return;
            }
            for (int j = 0; j < this.d8; ++j) {
                if (g.if(a3.int, this.d1[j].long) == 0) {
                    this.eq = j;
                    this.if(System.currentTimeMillis(), true);
                    this.ek = true;
                    this.d6 = false;
                    return;
                }
            }
        }
        else if (g.do(array, ac.C) == 0) {
            int n = 0;
            for (int k = 0; k < this.d8; ++k) {
                if (n != 0) {
                    this.d1[k - 1] = this.d1[k];
                }
                else if (g.if(a3.int, this.d1[k].long) == 0) {
                    n = 1;
                    if (k == this.d8 - 1) {
                        this.d1[k] = null;
                    }
                }
            }
            if (n != 0) {
                --this.d8;
            }
        }
        else if (g.do(array, ac.z) == 0) {
            final bf bf = new bf();
            try {
                y.a(bf, a3.int, g.a(a3.int));
                this.byte(bf.do);
            }
            catch (Exception ex) {}
        }
        else if (g.do(array, ao.eA) == 0) {
            this.ea = al.do(a3) * super.cA;
            this.ey = true;
        }
        else if (g.do(array, ao.es) == 0) {
            this.d5 = al.do(a3) * super.cA;
            this.ey = true;
        }
        else if (g.do(array, ao.er) == 0) {
            this.ew = al.do(a3) * super.cA;
            this.ez = true;
        }
        else if (g.do(array, ao.eg) == 0) {
            this.em = al.do(a3) * super.cA;
            this.ez = true;
        }
        else if (g.do(array, ao.d7) == 0) {
            this.ed = al.do(a3) * super.cA;
            if (this.ed > 2.5) {
                this.ed = 2.5f;
            }
        }
        else if (g.do(array, ao.eB) == 0) {
            this.dY = al.do(a3) * super.cA;
            if (this.dY < 0.01745) {
                this.dY = 0.01745f;
            }
        }
        else if (g.do(array, ao.ev) == 0) {
            this.ez = false;
        }
        else if (g.do(array, ao.el) == 0) {
            this.ey = false;
        }
        else if (g.do(array, ao.d4) == 0) {
            this.dV = al.a(a3);
        }
        else if (g.do(array, ac.D) == 0) {
            final bf bf2 = new bf();
            try {
                y.a(bf2, a3.int, g.a(a3.int));
                this.a(bf2.do, true);
            }
            catch (Exception ex2) {}
        }
        else if (g.do(array, ac.at) == 0) {
            this.ex = super.b.a(this.et, a3.int, this.ex);
            this.ei = super.b.a(this.d3, a3.int, this.ei);
            super.cB.p = super.b.a(super.cB.else, a3.int, super.cB.p);
        }
        super.do = true;
    }
    
    public a3 a(final char[] array) {
        if (g.do(array, ac.M) == 0) {
            super.new.char = 3;
            super.new.else = super.cE * super.cD;
            return super.new;
        }
        if (g.do(array, ac.byte) == 0) {
            super.new.char = 3;
            super.new.else = super.cx * super.cD;
            return super.new;
        }
        if (g.do(array, ac.ax) == 0) {
            super.new.char = 3;
            super.new.else = super.cz * super.cD;
            return super.new;
        }
        if (g.do(array, ac.N) == 0) {
            super.new.char = 3;
            super.new.else = super.cy * super.cD;
            return super.new;
        }
        if (g.do(array, ac.Q) == 0) {
            super.new.char = 1;
            super.new.long = super.for;
            return super.new;
        }
        if (g.do(array, ac.ay) == 0) {
            super.new.char = 2;
            super.new.case = super.d;
            return super.new;
        }
        if (g.do(array, ao.d4) == 0) {
            super.new.char = 1;
            super.new.long = this.dV;
            return super.new;
        }
        if (g.do(array, ac.n) == 0) {
            if (this.d8 == 0) {
                super.new.char = 1;
                super.new.long = false;
                return super.new;
            }
            super.new.char = 4;
            if (this.eq != -1) {
                super.new.int = this.d1[this.eq].long;
            }
            else if (g.if(this.dW, ao.dZ) != 0) {
                super.new.int = this.dW;
            }
            else {
                super.new.int = this.d1[0].long;
            }
            return super.new;
        }
        else {
            if (g.do(array, ac.ae) == 0) {
                super.new.char = 1;
                super.new.long = this.ek;
                return super.new;
            }
            if (g.do(array, ao.eA) == 0) {
                if (!this.ey) {
                    return ac.a(super.new);
                }
                super.new.char = 3;
                super.new.else = this.ea * super.cD;
                return super.new;
            }
            else if (g.do(array, ao.es) == 0) {
                if (!this.ey) {
                    return ac.a(super.new);
                }
                super.new.char = 3;
                super.new.else = this.d5 * super.cD;
                return super.new;
            }
            else if (g.do(array, ao.er) == 0) {
                if (!this.ey) {
                    return ac.a(super.new);
                }
                super.new.char = 3;
                super.new.else = this.ew * super.cD;
                return super.new;
            }
            else if (g.do(array, ao.eg) == 0) {
                if (!this.ey) {
                    return ac.a(super.new);
                }
                super.new.char = 3;
                super.new.else = this.em * super.cD;
                return super.new;
            }
            else if (g.do(array, ao.d7) == 0) {
                if (!this.eb) {
                    return ac.a(super.new);
                }
                super.new.char = 3;
                super.new.else = this.ed * super.cD;
                return super.new;
            }
            else if (g.do(array, ao.eB) == 0) {
                if (!this.eb) {
                    return ac.a(super.new);
                }
                super.new.char = 3;
                super.new.else = this.dY * super.cD;
                return super.new;
            }
            else {
                if (g.do(array, ac.t) == 0) {
                    super.new = new a3();
                    super.new.char = 5;
                    final int goto1 = this.ex + this.ei + this.ep + ((this.dX != null) ? 1 : 0);
                    super.new.goto = goto1;
                    super.new.a = new a3[goto1];
                    for (int i = 0; i < this.ex; ++i) {
                        super.new.a[i] = new a3();
                        super.new.a[i].char = 4;
                        super.new.a[i].int = this.et[i].f;
                    }
                    for (int j = this.ex; j < this.ex + this.ei; ++j) {
                        super.new.a[j] = new a3();
                        super.new.a[j].char = 4;
                        super.new.a[j].int = this.d3[j - this.ex].f;
                    }
                    for (int k = this.ex + this.ei; k < this.ex + this.ei + this.ep; ++k) {
                        super.new.a[k] = new a3();
                        super.new.a[k].char = 4;
                        super.new.a[k].int = this.ee[k - this.ex - this.ei].f;
                    }
                    if (this.dX != null) {
                        super.new.a[goto1 - 1] = new a3();
                        super.new.a[goto1 - 1].char = 4;
                        super.new.a[goto1 - 1].int = this.dX.f;
                    }
                    return super.new;
                }
                return ac.a(super.new);
            }
        }
    }
    
    public void for(final char[] array, final a3 a3) {
        for (int i = 0; i < this.ex; ++i) {
            this.et[i].a(array, a3);
        }
    }
    
    public void if(final char[] array, final a3 a3) {
        for (int i = 0; i < this.ei; ++i) {
            this.d3[i].a(array, a3);
        }
    }
    
    public void do(final char[] array, final a3 a3) {
        for (int i = 0; i < this.ep; ++i) {
            this.ee[i].a(array, a3);
        }
    }
    
    protected void r() {
    }
}
