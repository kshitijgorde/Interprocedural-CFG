// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class a2 extends f
{
    protected bf dX;
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
    f[] et;
    f[] d3;
    f[] ee;
    float[][] en;
    bl[] d1;
    int d8;
    static final char[] dZ;
    boolean d6;
    a4 d0;
    a4 dU;
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
    a4[][] d9;
    a4[][] ef;
    
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
    
    public void a(final an an, final float n, final aq aq, final h h, final t t) {
    }
    
    a2() {
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
        this.d0 = new a4();
        this.dU = new a4();
        super.new = new bi();
    }
    
    public void int(final bh bh) {
        this.en = super.b.if();
        for (int i = 0; i < bh.do; ++i) {
            if (bh.try[i].toLowerCase().compareTo("id") == 0) {
                super.f = (String.valueOf(bh.new[i]) + "\u0000").toCharArray();
            }
            else if (bh.try[i].toLowerCase().compareTo("layer") == 0) {
                super.d = new Integer(bh.new[i]);
            }
            else if (bh.try[i].toLowerCase().compareTo("pan") == 0) {
                super.cE = new Float(bh.new[i]) * super.cA;
            }
            else if (bh.try[i].toLowerCase().compareTo("tilt") == 0) {
                super.cx = new Float(bh.new[i]) * super.cA;
            }
            else if (bh.try[i].toLowerCase().compareTo("roll") == 0) {
                super.cz = new Float(bh.new[i]) * super.cA;
            }
            else if (bh.try[i].toLowerCase().compareTo("visible") == 0) {
                if (bh.new[i].compareTo("false") == 0) {
                    super.for = false;
                }
            }
            else if (bh.try[i].toLowerCase().compareTo("maxpan") == 0) {
                this.ew = new Float(bh.new[i]) * super.cA;
            }
            else if (bh.try[i].toLowerCase().compareTo("minpan") == 0) {
                this.em = new Float(bh.new[i]) * super.cA;
            }
            else if (bh.try[i].toLowerCase().compareTo("maxtilt") == 0) {
                this.ea = new Float(bh.new[i]) * super.cA;
            }
            else if (bh.try[i].toLowerCase().compareTo("mintilt") == 0) {
                this.d5 = new Float(bh.new[i]) * super.cA;
            }
        }
        this.a();
        for (bh bh2 = bh.if; bh2 != null; bh2 = bh2.for) {
            if (bh2.a.toLowerCase().compareTo("image") == 0) {
                (this.dX = new bf(super.b, super.long, super.cB.Q, super.goto)).a(bh2);
                super.cB.a(this.dX);
            }
            else if (bh2.a.toLowerCase().compareTo("autopath") == 0) {
                for (int j = 0; j < bh2.do; ++j) {
                    if (bh2.try[j].toLowerCase().compareTo("play") == 0) {
                        if (bh2.new[j].compareTo("false") == 0) {
                            this.ek = false;
                        }
                    }
                    else if (bh2.try[j].toLowerCase().compareTo("first") == 0) {
                        this.dW = (String.valueOf(bh2.new[j]) + "\u0000").toCharArray();
                    }
                }
                for (bh bh3 = bh2.if; bh3 != null; bh3 = bh3.for) {
                    this.byte(bh3);
                }
            }
        }
        for (bh bh4 = bh.if; bh4 != null; bh4 = bh4.for) {
            if (bh4.a.toLowerCase().compareTo("image") != 0 && bh4.a.toLowerCase().compareTo("autopath") != 0) {
                this.a(bh4, super.goto);
            }
        }
    }
    
    private void a(final bh bh, final boolean goto1) {
        try {
            if (bh.a.toLowerCase().compareTo("hotspotpoint") == 0) {
                this.s();
                (this.et[this.ex] = (f)Class.forName("a.a.a.a.by").getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null)).a(super.b, super.cA, super.long, super.cC, super.cB);
                super.cB.a(this.et[this.ex]);
                this.et[this.ex].goto = goto1;
                this.et[this.ex].int(bh);
                ++this.ex;
            }
            else if (bh.a.toLowerCase().compareTo("hotspotrectangle") == 0) {
                this.s();
                (this.et[this.ex] = (f)Class.forName("a.a.a.a.cc").getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null)).a(super.b, super.cA, super.long, super.cC, super.cB);
                super.cB.a(this.et[this.ex]);
                this.et[this.ex].goto = goto1;
                this.et[this.ex].int(bh);
                ++this.ex;
            }
            else if (bh.a.toLowerCase().compareTo("hotspotpolygonal") == 0) {
                this.s();
                (this.et[this.ex] = (f)Class.forName("a.a.a.a.u").getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null)).a(super.b, super.cA, super.long, super.cC, super.cB);
                super.cB.a(this.et[this.ex]);
                this.et[this.ex].goto = goto1;
                this.et[this.ex].int(bh);
                ++this.ex;
            }
            else if (bh.a.toLowerCase().compareTo("light") == 0) {
                this.q();
                (this.d3[this.ei] = (f)Class.forName("a.a.a.a.a7").getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null)).a(super.b, super.cA, super.long, super.cC, super.cB);
                super.cB.a(this.d3[this.ei]);
                this.d3[this.ei].goto = goto1;
                this.d3[this.ei].int(bh);
                ++this.ei;
            }
            else if (bh.a.toLowerCase().compareTo("sound") == 0) {
                this.p();
                (this.ee[this.ep] = (f)Class.forName("a.a.a.a.a6").getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null)).a(super.b, super.cA, super.long, super.cC, super.cB);
                super.cB.a(this.ee[this.ep]);
                this.ee[this.ep].goto = goto1;
                this.ee[this.ep].int(bh);
                ++this.ep;
            }
        }
        catch (Exception ex) {
            System.out.println("Can't load missing functionnality :" + bh.a);
        }
    }
    
    protected void byte(final bh bh) {
        if (bh.a.toLowerCase().compareTo("moveto") == 0 || bh.a.toLowerCase().compareTo("move") == 0 || bh.a.toLowerCase().compareTo("sleep") == 0 || bh.a.toLowerCase().compareTo("action") == 0) {
            this.t();
            this.d1[this.d8] = new bl();
            if (bh.a.toLowerCase().compareTo("moveto") == 0) {
                this.d1[this.d8].do = 0;
            }
            else if (bh.a.toLowerCase().compareTo("move") == 0) {
                this.d1[this.d8].do = 1;
            }
            else if (bh.a.toLowerCase().compareTo("sleep") == 0) {
                this.d1[this.d8].do = 2;
            }
            else if (bh.a.toLowerCase().compareTo("action") == 0 && bh.case != null) {
                this.d1[this.d8].do = 3;
                this.d1[this.d8].h = (String.valueOf(bh.case.do) + "\u0000").toCharArray();
            }
            for (int i = 0; i < bh.do; ++i) {
                if (bh.try[i].toLowerCase().compareTo("pan") == 0) {
                    this.d1[this.d8].b = new Float(bh.new[i]) * super.cA;
                    this.d1[this.d8].k = true;
                }
                else if (bh.try[i].toLowerCase().compareTo("tilt") == 0) {
                    this.d1[this.d8].c = new Float(bh.new[i]) * super.cA;
                    this.d1[this.d8].f = true;
                }
                else if (bh.try[i].toLowerCase().compareTo("roll") == 0) {
                    this.d1[this.d8].l = new Float(bh.new[i]) * super.cA;
                    this.d1[this.d8].o = true;
                }
                else if (bh.try[i].toLowerCase().compareTo("fov") == 0) {
                    this.d1[this.d8].g = new Float(bh.new[i]) * super.cA;
                    this.d1[this.d8].p = true;
                }
                else if (bh.try[i].toLowerCase().compareTo("time") == 0) {
                    this.d1[this.d8].new = (long)(new Float(bh.new[i]) * 1000.0f);
                    this.d1[this.d8].m = 0;
                }
                else if (bh.try[i].toLowerCase().compareTo("speed") == 0) {
                    this.d1[this.d8].i = new Float(bh.new[i]) * super.cA;
                    this.d1[this.d8].m = 1;
                }
                else if (bh.try[i].toLowerCase().compareTo("amid") == 0 || bh.try[i].toLowerCase().compareTo("apid") == 0) {
                    this.d1[this.d8].long = (String.valueOf(bh.new[i]) + "\u0000").toCharArray();
                }
                else if (bh.try[i].toLowerCase().compareTo("next") == 0) {
                    this.d1[this.d8].void = (String.valueOf(bh.new[i]) + "\u0000").toCharArray();
                }
            }
            ++this.d8;
        }
    }
    
    void a(final a4[][] array, final a4[][] array2, final int n) {
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
        if (!b || this.eq == -1) {
            if (this.eq == -1) {
                if (i.a(this.dW) == 0) {
                    this.eq = 0;
                }
                else {
                    for (int i = 0; i < this.d8; ++i) {
                        if (i.if(this.dW, this.d1[i].long) == 0) {
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
                if (i.if(this.d1[this.eq].void, a2.dZ) == 0) {
                    this.eq = -1;
                    this.ek = false;
                    return;
                }
                boolean b2 = false;
                for (int j = 0; j < this.d8; ++j) {
                    if (i.if(this.d1[this.eq].void, this.d1[j].long) == 0) {
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
            final f[] ee = new f[this.ep + 10];
            for (int i = 0; i < this.ep; ++i) {
                ee[i] = this.ee[i];
            }
            this.ee = ee;
        }
    }
    
    private void q() {
        if (this.d3 == null || this.d3.length == this.ei) {
            final f[] d3 = new f[this.ei + 10];
            for (int i = 0; i < this.ei; ++i) {
                d3[i] = this.d3[i];
            }
            this.d3 = d3;
        }
    }
    
    private void s() {
        if (this.et == null || this.et.length == this.ex) {
            final f[] et = new f[this.ex + 10];
            for (int i = 0; i < this.ex; ++i) {
                et[i] = this.et[i];
            }
            this.et = et;
        }
    }
    
    private void t() {
        if (this.d1 == null || this.d1.length == this.d8) {
            final bl[] d1 = new bl[this.d8 + 10];
            for (int i = 0; i < this.d8; ++i) {
                d1[i] = this.d1[i];
            }
            this.d1 = d1;
        }
    }
    
    protected final a4 if(final int n, final int n2) {
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
    
    void if(final am am) {
        final boolean i = am.i;
        if (!super.for) {
            am.i = true;
        }
        for (int j = 0; j < this.ex; ++j) {
            this.et[j].if(am);
        }
        if (!super.for) {
            am.i = i;
        }
        if (!am.i && am.if == 0 && this.do(am.goto, am.else)) {
            am.i = true;
        }
    }
    
    public void a(final char[] array, final bi bi) {
        if (i.do(array, an.M) == 0) {
            super.cE = az.do(bi) * super.cA;
        }
        else if (i.do(array, an.byte) == 0) {
            super.cx = az.do(bi) * super.cA;
        }
        else if (i.do(array, an.ax) == 0) {
            super.cz = az.do(bi) * super.cA;
        }
        else if (i.do(array, an.N) == 0) {
            super.cy = az.do(bi) * super.cA;
        }
        else if (i.do(array, an.ay) == 0) {
            super.d = az.if(bi);
            super.b.if(super.cB.aa, 0, super.cB.U - 1);
        }
        else if (i.do(array, an.Q) == 0) {
            if (!(super.for = az.a(bi))) {
                for (int i = 0; i < this.ep; ++i) {
                    this.ee[i].try(super.for);
                }
            }
            super.do = true;
        }
        else if (i.do(array, an.ae) == 0) {
            this.ek = az.a(bi);
            if (this.ek) {
                this.if(System.currentTimeMillis(), true);
                this.d6 = false;
            }
        }
        else if (i.do(array, an.n) == 0) {
            if (bi.char != 4) {
                return;
            }
            for (int j = 0; j < this.d8; ++j) {
                if (i.if(bi.int, this.d1[j].long) == 0) {
                    this.eq = j;
                    this.if(System.currentTimeMillis(), true);
                    this.ek = true;
                    this.d6 = false;
                    return;
                }
            }
        }
        else if (i.do(array, an.C) == 0) {
            int n = 0;
            for (int k = 0; k < this.d8; ++k) {
                if (n != 0) {
                    this.d1[k - 1] = this.d1[k];
                }
                else if (i.if(bi.int, this.d1[k].long) == 0) {
                    n = 1;
                }
            }
            if (n != 0) {
                --this.d8;
            }
        }
        else if (i.do(array, an.z) == 0) {
            final bw bw = new bw();
            try {
                aj.a(bw, bi.int, i.a(bi.int));
                this.byte(bw.do);
            }
            catch (Exception ex) {}
        }
        else if (i.do(array, a2.eA) == 0) {
            this.ea = az.do(bi) * super.cA;
            this.ey = true;
        }
        else if (i.do(array, a2.es) == 0) {
            this.d5 = az.do(bi) * super.cA;
            this.ey = true;
        }
        else if (i.do(array, a2.er) == 0) {
            this.ew = az.do(bi) * super.cA;
            this.ez = true;
        }
        else if (i.do(array, a2.eg) == 0) {
            this.em = az.do(bi) * super.cA;
            this.ez = true;
        }
        else if (i.do(array, a2.d7) == 0) {
            this.ed = az.do(bi) * super.cA;
            if (this.ed > 2.5) {
                this.ed = 2.5f;
            }
        }
        else if (i.do(array, a2.eB) == 0) {
            this.dY = az.do(bi) * super.cA;
            if (this.dY < 0.01745) {
                this.dY = 0.01745f;
            }
        }
        else if (i.do(array, a2.ev) == 0) {
            this.ez = false;
        }
        else if (i.do(array, a2.el) == 0) {
            this.ey = false;
        }
        else if (i.do(array, a2.d4) == 0) {
            this.dV = az.a(bi);
        }
        else if (i.do(array, an.D) == 0) {
            final bw bw2 = new bw();
            try {
                aj.a(bw2, bi.int, i.a(bi.int));
                this.a(bw2.do, true);
            }
            catch (Exception ex2) {}
        }
        else if (i.do(array, an.at) == 0) {
            this.ex = super.b.a(this.et, bi.int, this.ex);
            this.ei = super.b.a(this.d3, bi.int, this.ei);
            super.cB.p = super.b.a(super.cB.else, bi.int, super.cB.p);
        }
        super.do = true;
    }
    
    public bi a(final char[] array) {
        if (i.do(array, an.M) == 0) {
            super.new.char = 3;
            super.new.else = super.cE * super.cD;
            return super.new;
        }
        if (i.do(array, an.byte) == 0) {
            super.new.char = 3;
            super.new.else = super.cx * super.cD;
            return super.new;
        }
        if (i.do(array, an.ax) == 0) {
            super.new.char = 3;
            super.new.else = super.cz * super.cD;
            return super.new;
        }
        if (i.do(array, an.N) == 0) {
            super.new.char = 3;
            super.new.else = super.cy * super.cD;
            return super.new;
        }
        if (i.do(array, an.Q) == 0) {
            super.new.char = 1;
            super.new.long = super.for;
            return super.new;
        }
        if (i.do(array, an.ay) == 0) {
            super.new.char = 2;
            super.new.case = super.d;
            return super.new;
        }
        if (i.do(array, a2.d4) == 0) {
            super.new.char = 1;
            super.new.long = this.dV;
            return super.new;
        }
        if (i.do(array, an.n) == 0) {
            if (this.d8 == 0) {
                super.new.char = 1;
                super.new.long = false;
                return super.new;
            }
            super.new.char = 4;
            if (this.eq != -1) {
                super.new.int = this.d1[this.eq].long;
            }
            else if (i.if(this.dW, a2.dZ) != 0) {
                super.new.int = this.dW;
            }
            else {
                super.new.int = this.d1[0].long;
            }
            return super.new;
        }
        else {
            if (i.do(array, an.ae) == 0) {
                super.new.char = 1;
                super.new.long = this.ek;
                return super.new;
            }
            if (i.do(array, a2.eA) == 0) {
                if (!this.ey) {
                    return an.a(super.new);
                }
                super.new.char = 3;
                super.new.else = this.ea * super.cD;
                return super.new;
            }
            else if (i.do(array, a2.es) == 0) {
                if (!this.ey) {
                    return an.a(super.new);
                }
                super.new.char = 3;
                super.new.else = this.d5 * super.cD;
                return super.new;
            }
            else if (i.do(array, a2.er) == 0) {
                if (!this.ey) {
                    return an.a(super.new);
                }
                super.new.char = 3;
                super.new.else = this.ew * super.cD;
                return super.new;
            }
            else if (i.do(array, a2.eg) == 0) {
                if (!this.ey) {
                    return an.a(super.new);
                }
                super.new.char = 3;
                super.new.else = this.em * super.cD;
                return super.new;
            }
            else if (i.do(array, a2.d7) == 0) {
                if (!this.eb) {
                    return an.a(super.new);
                }
                super.new.char = 3;
                super.new.else = this.ed * super.cD;
                return super.new;
            }
            else if (i.do(array, a2.eB) == 0) {
                if (!this.eb) {
                    return an.a(super.new);
                }
                super.new.char = 3;
                super.new.else = this.dY * super.cD;
                return super.new;
            }
            else {
                if (i.do(array, an.t) == 0) {
                    super.new = new bi();
                    super.new.char = 5;
                    final int goto1 = this.ex + this.ei + this.ep + ((this.dX != null) ? 1 : 0);
                    super.new.goto = goto1;
                    super.new.a = new bi[goto1];
                    for (int i = 0; i < this.ex; ++i) {
                        super.new.a[i] = new bi();
                        super.new.a[i].char = 4;
                        super.new.a[i].int = this.et[i].f;
                    }
                    for (int j = this.ex; j < this.ex + this.ei; ++j) {
                        super.new.a[j] = new bi();
                        super.new.a[j].char = 4;
                        super.new.a[j].int = this.d3[j - this.ex].f;
                    }
                    for (int k = this.ex + this.ei; k < this.ex + this.ei + this.ep; ++k) {
                        super.new.a[k] = new bi();
                        super.new.a[k].char = 4;
                        super.new.a[k].int = this.ee[k - this.ex - this.ei].f;
                    }
                    if (this.dX != null) {
                        super.new.a[goto1 - 1] = new bi();
                        super.new.a[goto1 - 1].char = 4;
                        super.new.a[goto1 - 1].int = this.dX.f;
                    }
                    return super.new;
                }
                return an.a(super.new);
            }
        }
    }
    
    public void for(final char[] array, final bi bi) {
        for (int i = 0; i < this.ex; ++i) {
            this.et[i].a(array, bi);
        }
    }
    
    public void if(final char[] array, final bi bi) {
        for (int i = 0; i < this.ei; ++i) {
            this.d3[i].a(array, bi);
        }
    }
    
    public void do(final char[] array, final bi bi) {
        for (int i = 0; i < this.ep; ++i) {
            this.ee[i].a(array, bi);
        }
    }
    
    protected void r() {
    }
}
