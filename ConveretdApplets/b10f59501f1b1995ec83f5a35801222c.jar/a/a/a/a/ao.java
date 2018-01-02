// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class ao extends e
{
    protected a0 dW;
    boolean ej;
    protected float ev;
    protected float el;
    protected float d9;
    protected float d4;
    protected float dX;
    protected float ec;
    boolean ey;
    boolean ex;
    boolean ea;
    int ei;
    int eg;
    int ep;
    char[] dV;
    int ew;
    int eh;
    int eo;
    e[] es;
    e[] d2;
    e[] ed;
    float[][] em;
    a5[] d0;
    int d7;
    static final char[] dY;
    boolean d5;
    aq dZ;
    aq dT;
    float eb;
    float d1;
    float en;
    static final char[] ez;
    static final char[] er;
    static final char[] eq;
    static final char[] ef;
    static final char[] d6;
    static final char[] eA;
    static final char[] ek;
    static final char[] eu;
    static final char[] d3;
    int et;
    boolean dU;
    aq[][] d8;
    aq[][] ee;
    
    static {
        dY = new char[1];
        ez = new char[] { 'm', 'a', 'x', 't', 'i', 'l', 't', '\0' };
        er = new char[] { 'm', 'i', 'n', 't', 'i', 'l', 't', '\0' };
        eq = new char[] { 'm', 'a', 'x', 'p', 'a', 'n', '\0' };
        ef = new char[] { 'm', 'i', 'n', 'p', 'a', 'n', '\0' };
        d6 = new char[] { 'm', 'a', 'x', 'f', 'o', 'v', '\0' };
        eA = new char[] { 'm', 'i', 'n', 'f', 'o', 'v', '\0' };
        ek = new char[] { 'r', 'm', 't', 'i', 'l', 't', '\0' };
        eu = new char[] { 'r', 'm', 'p', 'a', 'n', '\0' };
        d3 = new char[] { 'l', 'o', 'c', 'k', 'z', 'e', 'n', 'i', 't', 'h', 'n', 'a', 'd', 'i', 'r' };
    }
    
    public void if() {
        super.if();
        if (this.em != null) {
            for (int i = 0; i < this.em.length; ++i) {
                this.em[i] = null;
            }
            this.em = null;
        }
        if (this.d0 != null) {
            for (int j = 0; j < this.d0.length; ++j) {
                this.d0[j] = null;
            }
            this.d0 = null;
        }
        if (this.d8 != null) {
            for (int k = 0; k < this.d8.length; ++k) {
                this.d8[k] = null;
            }
            this.d8 = null;
        }
        if (this.ee != null) {
            for (int l = 0; l < this.ee.length; ++l) {
                this.ee[l] = null;
            }
            this.ee = null;
        }
        if (this.dW != null) {
            this.dW.if();
            this.dW = null;
        }
        for (int n = 0; n < this.eh; ++n) {
            if (this.d2[n] != null) {
                this.d2[n].if();
            }
            this.d2[n] = null;
        }
        this.eh = 0;
        this.d2 = null;
        for (int n2 = 0; n2 < this.ew; ++n2) {
            if (this.es[n2] != null) {
                this.es[n2].if();
            }
            this.es[n2] = null;
        }
        this.ew = 0;
        this.es = null;
        for (int n3 = 0; n3 < this.eo; ++n3) {
            if (this.ed[n3] != null) {
                this.ed[n3].if();
            }
            this.ed[n3] = null;
        }
        this.eo = 0;
        this.ed = null;
    }
    
    public void a(final ac ac, final float n, final ae ae, final f f, final m m) {
    }
    
    ao() {
        this.dW = null;
        this.ej = true;
        this.ev = 0.0f;
        this.el = 0.0f;
        this.d9 = 0.0f;
        this.d4 = 0.0f;
        this.dX = 0.01745f;
        this.ec = 2.5f;
        this.ey = true;
        this.ex = true;
        this.ea = false;
        this.ep = -1;
        this.dV = new char[1];
        this.ew = 0;
        this.eh = 0;
        this.eo = 0;
        this.es = null;
        this.d2 = null;
        this.ed = null;
        this.d0 = null;
        this.d7 = 0;
        this.d5 = false;
        this.dZ = null;
        this.dT = null;
        this.eb = 0.0f;
        this.d1 = 0.0f;
        this.en = 0.0f;
        this.et = 1;
        this.dU = false;
        this.d8 = null;
        this.ee = null;
        this.dZ = new aq();
        this.dT = new aq();
        super.new = new a3();
    }
    
    public void int(final a2 a2) {
        this.em = super.b.if();
        for (int i = 0; i < a2.do; ++i) {
            if (a2.try[i].toLowerCase().compareTo("id") == 0) {
                super.f = (String.valueOf(a2.new[i]) + "\u0000").toCharArray();
            }
            else if (a2.try[i].toLowerCase().compareTo("layer") == 0) {
                super.d = new Integer(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("pan") == 0) {
                super.cD = new Float(a2.new[i]) * super.cz;
            }
            else if (a2.try[i].toLowerCase().compareTo("tilt") == 0) {
                super.cw = new Float(a2.new[i]) * super.cz;
            }
            else if (a2.try[i].toLowerCase().compareTo("roll") == 0) {
                super.cy = new Float(a2.new[i]) * super.cz;
            }
            else if (a2.try[i].toLowerCase().compareTo("visible") == 0) {
                if (a2.new[i].compareTo("false") == 0) {
                    super.for = false;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("maxpan") == 0) {
                this.ev = new Float(a2.new[i]) * super.cz;
            }
            else if (a2.try[i].toLowerCase().compareTo("minpan") == 0) {
                this.el = new Float(a2.new[i]) * super.cz;
            }
            else if (a2.try[i].toLowerCase().compareTo("maxtilt") == 0) {
                this.d9 = new Float(a2.new[i]) * super.cz;
            }
            else if (a2.try[i].toLowerCase().compareTo("mintilt") == 0) {
                this.d4 = new Float(a2.new[i]) * super.cz;
            }
        }
        this.a();
        for (a2 a3 = a2.if; a3 != null; a3 = a3.for) {
            if (a3.a.toLowerCase().compareTo("image") == 0) {
                (this.dW = new a0(super.b, super.long, super.cA.O, super.goto)).a(a3);
                super.cA.a(this.dW);
            }
            else if (a3.a.toLowerCase().compareTo("autopath") == 0) {
                for (int j = 0; j < a3.do; ++j) {
                    if (a3.try[j].toLowerCase().compareTo("play") == 0) {
                        if (a3.new[j].compareTo("false") == 0) {
                            this.ej = false;
                        }
                    }
                    else if (a3.try[j].toLowerCase().compareTo("first") == 0) {
                        this.dV = (String.valueOf(a3.new[j]) + "\u0000").toCharArray();
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
                this.u();
                (this.es[this.ew] = (e)Class.forName("a.a.a.a.bh").getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null)).a(super.b, super.cz, super.long, super.cB, super.cA);
                super.cA.a(this.es[this.ew]);
                this.es[this.ew].goto = goto1;
                this.es[this.ew].int(a2);
                ++this.ew;
            }
            else if (a2.a.toLowerCase().compareTo("hotspotrectangle") == 0) {
                this.u();
                (this.es[this.ew] = (e)Class.forName("a.a.a.a.bs").getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null)).a(super.b, super.cz, super.long, super.cB, super.cA);
                super.cA.a(this.es[this.ew]);
                this.es[this.ew].goto = goto1;
                this.es[this.ew].int(a2);
                ++this.ew;
            }
            else if (a2.a.toLowerCase().compareTo("hotspotpolygonal") == 0) {
                this.u();
                (this.es[this.ew] = (e)Class.forName("a.a.a.a.n").getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null)).a(super.b, super.cz, super.long, super.cB, super.cA);
                super.cA.a(this.es[this.ew]);
                this.es[this.ew].goto = goto1;
                this.es[this.ew].int(a2);
                ++this.ew;
            }
            else if (a2.a.toLowerCase().compareTo("light") == 0) {
                this.s();
                (this.d2[this.eh] = (e)Class.forName("a.a.a.a.at").getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null)).a(super.b, super.cz, super.long, super.cB, super.cA);
                super.cA.a(this.d2[this.eh]);
                this.d2[this.eh].goto = goto1;
                this.d2[this.eh].int(a2);
                ++this.eh;
            }
            else if (a2.a.toLowerCase().compareTo("sound") == 0) {
                this.r();
                (this.ed[this.eo] = (e)Class.forName("a.a.a.a.as").getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null)).a(super.b, super.cz, super.long, super.cB, super.cA);
                super.cA.a(this.ed[this.eo]);
                this.ed[this.eo].goto = goto1;
                this.ed[this.eo].int(a2);
                ++this.eo;
            }
        }
        catch (Exception ex) {}
    }
    
    protected void byte(final a2 a2) {
        if (a2.a.toLowerCase().compareTo("moveto") == 0 || a2.a.toLowerCase().compareTo("move") == 0 || a2.a.toLowerCase().compareTo("sleep") == 0 || a2.a.toLowerCase().compareTo("action") == 0) {
            this.v();
            this.d0[this.d7] = new a5();
            if (a2.a.toLowerCase().compareTo("moveto") == 0) {
                this.d0[this.d7].do = 0;
            }
            else if (a2.a.toLowerCase().compareTo("move") == 0) {
                this.d0[this.d7].do = 1;
            }
            else if (a2.a.toLowerCase().compareTo("sleep") == 0) {
                this.d0[this.d7].do = 2;
            }
            else if (a2.a.toLowerCase().compareTo("action") == 0) {
                this.d0[this.d7].do = 3;
                this.d0[this.d7].h = (String.valueOf(a2.case.do) + "\u0000").toCharArray();
            }
            for (int i = 0; i < a2.do; ++i) {
                if (a2.try[i].toLowerCase().compareTo("pan") == 0) {
                    this.d0[this.d7].b = new Float(a2.new[i]) * super.cz;
                    this.d0[this.d7].k = true;
                }
                else if (a2.try[i].toLowerCase().compareTo("tilt") == 0) {
                    this.d0[this.d7].c = new Float(a2.new[i]) * super.cz;
                    this.d0[this.d7].f = true;
                }
                else if (a2.try[i].toLowerCase().compareTo("roll") == 0) {
                    this.d0[this.d7].l = new Float(a2.new[i]) * super.cz;
                    this.d0[this.d7].o = true;
                }
                else if (a2.try[i].toLowerCase().compareTo("fov") == 0) {
                    this.d0[this.d7].g = new Float(a2.new[i]) * super.cz;
                    this.d0[this.d7].p = true;
                }
                else if (a2.try[i].toLowerCase().compareTo("time") == 0) {
                    this.d0[this.d7].new = (long)(new Float(a2.new[i]) * 1000.0f);
                    this.d0[this.d7].m = 0;
                }
                else if (a2.try[i].toLowerCase().compareTo("speed") == 0) {
                    this.d0[this.d7].i = new Float(a2.new[i]) * super.cz;
                    this.d0[this.d7].m = 1;
                }
                else if (a2.try[i].toLowerCase().compareTo("amid") == 0 || a2.try[i].toLowerCase().compareTo("apid") == 0) {
                    this.d0[this.d7].long = (String.valueOf(a2.new[i]) + "\u0000").toCharArray();
                }
                else if (a2.try[i].toLowerCase().compareTo("next") == 0) {
                    this.d0[this.d7].void = (String.valueOf(a2.new[i]) + "\u0000").toCharArray();
                }
            }
            ++this.d7;
        }
    }
    
    void a(final aq[][] array, final aq[][] array2, final int n) {
        if (array != null && array2 != null) {
            super.b.a(super.cD, super.cw, super.cy, this.em);
            for (int i = 0; i < this.et; ++i) {
                for (int j = 0; j < this.ei * this.eg; ++j) {
                    super.b.a(this.em, array[i][j], array2[i][j]);
                }
            }
        }
        this.p();
    }
    
    void p() {
        for (int i = 0; i < this.eh; ++i) {
            this.d2[i].a(this.em);
        }
        for (int j = 0; j < this.ew; ++j) {
            this.es[j].a(this.em);
        }
        for (int k = 0; k < this.eo; ++k) {
            this.ed[k].a(this.em);
        }
    }
    
    boolean byte(final boolean b) {
        super.byte = true;
        for (int i = 0; i < this.ew; ++i) {
            if (!this.es[i].byte) {
                super.byte = false;
            }
        }
        for (int j = 0; j < this.eh; ++j) {
            if (!this.d2[j].byte) {
                super.byte = false;
            }
        }
        return (b && this.dW != null && this.dW.n) || ((this.dW == null || this.dW.byte) && super.byte);
    }
    
    void new(final long n) {
        for (int i = 0; i < this.ew; ++i) {
            this.es[i].new(n);
        }
        for (int j = 0; j < this.eh; ++j) {
            this.d2[j].new(n);
        }
        for (int k = 0; k < this.eo; ++k) {
            this.ed[k].new(n);
        }
        if (this.dW != null) {
            this.dW.if(n);
        }
        super.goto = true;
    }
    
    public boolean a(final long n) {
        super.else = false;
        boolean b = false;
        if (this.dW != null) {
            b |= this.dW.a(n);
            super.byte = this.dW.byte;
        }
        else {
            super.byte = true;
        }
        boolean b2;
        if (super.byte && super.goto) {
            if ((this.dW != null && this.dW.ag) || super.c) {
                if (this.dW != null) {
                    this.dW.ag = false;
                }
                this.t();
                if (super.cD != 0.0f || super.cw != 0.0f || super.cy != 0.0f) {
                    this.a(this.ee, this.d8, this.ei * this.eg);
                }
                super.c = false;
                b = true;
            }
            if (this.eb != super.cD || this.d1 != super.cw || this.en != super.cy) {
                this.a(this.ee, this.d8, this.ei * this.eg);
                b = true;
            }
            if (this.try(n)) {
                this.a(this.ee, this.d8, this.ei * this.eg);
                b = true;
            }
            this.eb = super.cD;
            this.d1 = super.cw;
            this.en = super.cy;
            b2 = (b | this.byte(n));
        }
        else {
            b2 = (b | this.byte(n));
        }
        if (this.dW != null) {
            super.else &= this.dW.else;
        }
        final boolean do1 = super.do;
        super.do = false;
        this.n();
        return (b2 & super.for) | do1;
    }
    
    boolean try(final long n) {
        boolean b = false;
        if (this.ej) {
            if ((this.ep == -1 || (this.d0[this.ep].int != 0L && this.d0[this.ep].int < n)) && (this.ep == -1 || ((this.d0[this.ep].do != 0 || this.d0[this.ep].int == 1L) && this.d0[this.ep].do != 3))) {
                this.if(n, false);
                if (this.ep == -1) {
                    return false;
                }
            }
            if (this.d0[this.ep].do == 1) {
                final float n2 = 1000.0f / (n - this.d0[this.ep].q);
                this.d0[this.ep].q = n;
                if (this.d0[this.ep].k) {
                    super.cD += this.d0[this.ep].b / n2;
                }
                if (this.d0[this.ep].f) {
                    super.cw += this.d0[this.ep].c / n2;
                }
                if (this.d0[this.ep].o) {
                    super.cy += this.d0[this.ep].l / n2;
                }
                if (this.d0[this.ep].p) {
                    super.cx += this.d0[this.ep].g / n2;
                }
                b = true;
            }
            else if (this.d0[this.ep].do == 0) {
                float n3;
                if (this.d0[this.ep].new == 0L || this.d0[this.ep].int < n || n - this.d0[this.ep].q >= this.d0[this.ep].int - n) {
                    n3 = 0.0f;
                    this.d0[this.ep].int = 1L;
                }
                else {
                    n3 = (this.d0[this.ep].int - n) / (n - this.d0[this.ep].q);
                }
                this.d0[this.ep].q = n;
                if (this.d0[this.ep].k) {
                    super.cD = super.b.if(super.cD, this.d0[this.ep].b);
                    if (n3 == 0.0f) {
                        super.cD = this.d0[this.ep].b;
                    }
                    else {
                        super.cD += (this.d0[this.ep].b - super.cD) / n3;
                    }
                }
                if (this.d0[this.ep].f) {
                    super.cw = super.b.if(super.cw, this.d0[this.ep].c);
                    if (n3 == 0.0f) {
                        super.cw = this.d0[this.ep].c;
                    }
                    else {
                        super.cw += (this.d0[this.ep].c - super.cw) / n3;
                    }
                }
                if (this.d0[this.ep].o) {
                    super.cy = super.b.if(super.cy, this.d0[this.ep].l);
                    if (n3 == 0.0f) {
                        super.cy = this.d0[this.ep].l;
                    }
                    else {
                        super.cy += (this.d0[this.ep].l - super.cy) / n3;
                    }
                }
                if (this.d0[this.ep].p) {
                    super.cx = super.b.if(super.cx, this.d0[this.ep].g);
                    if (n3 == 0.0f) {
                        super.cx = this.d0[this.ep].g;
                    }
                    else {
                        super.cx += (this.d0[this.ep].g - super.cx) / n3;
                    }
                }
                b = true;
            }
            else if (this.d0[this.ep].do == 3) {
                super.cA.if(this.d0[this.ep].h);
                this.if(n, false);
            }
        }
        return b;
    }
    
    private void if(final long q, final boolean b) {
        if (!b || this.ep == -1) {
            if (this.ep == -1) {
                if (g.a(this.dV) == 0) {
                    this.ep = 0;
                }
                else {
                    for (int i = 0; i < this.d7; ++i) {
                        if (g.if(this.dV, this.d0[i].long) == 0) {
                            this.ep = i;
                        }
                    }
                }
                if (this.ep >= this.d7 || this.ep == -1) {
                    this.ej = false;
                    this.ep = -1;
                    return;
                }
            }
            else {
                if (g.if(this.d0[this.ep].void, ao.dY) == 0) {
                    this.ep = -1;
                    this.ej = false;
                    return;
                }
                boolean b2 = false;
                for (int j = 0; j < this.d7; ++j) {
                    if (g.if(this.d0[this.ep].void, this.d0[j].long) == 0) {
                        this.ep = j;
                        b2 = true;
                        break;
                    }
                }
                if (!b2) {
                    this.ep = -1;
                    this.ej = false;
                    return;
                }
            }
        }
        this.d0[this.ep].q = q;
        if (this.d0[this.ep].do == 1) {
            if (this.d0[this.ep].new == 0L) {
                this.d0[this.ep].int = 0L;
                this.d0[this.ep].q = q - 40L;
            }
            else {
                this.d0[this.ep].int = q + this.d0[this.ep].new + 40L;
            }
        }
        else if (this.d0[this.ep].do == 0) {
            if (this.d0[this.ep].m == 1) {
                float n = 0.0f;
                if (this.d0[this.ep].k) {
                    n = Math.abs(this.d0[this.ep].b - super.b.if(super.cD, this.d0[this.ep].b));
                }
                if (this.d0[this.ep].f) {
                    n = Math.max(n, Math.abs(this.d0[this.ep].c - super.b.if(super.cw, this.d0[this.ep].c)));
                }
                if (this.d0[this.ep].o) {
                    n = Math.max(n, Math.abs(this.d0[this.ep].l - super.b.if(super.cy, this.d0[this.ep].l)));
                }
                if (this.d0[this.ep].p) {
                    n = Math.max(n, Math.abs(this.d0[this.ep].g - super.b.if(super.cx, this.d0[this.ep].g)));
                }
                this.d0[this.ep].new = (long)(n * 1000.0f / this.d0[this.ep].i);
            }
            if (this.d0[this.ep].new == 0L) {
                this.d0[this.ep].int = 0L;
            }
            else {
                this.d0[this.ep].int = q + this.d0[this.ep].new + 40L;
            }
        }
        else {
            if (this.d0[this.ep].do == 3) {
                this.d0[this.ep].int = 1L;
                return;
            }
            this.d0[this.ep].int = q + this.d0[this.ep].new;
        }
    }
    
    boolean byte(final long n) {
        boolean b = false;
        boolean else1 = true;
        for (int i = 0; i < this.eh; ++i) {
            b |= this.d2[i].a(n);
            else1 &= this.d2[i].byte;
        }
        for (int j = 0; j < this.ew; ++j) {
            b |= this.es[j].a(n);
            else1 &= this.es[j].byte;
        }
        for (int k = 0; k < this.eo; ++k) {
            b |= this.ed[k].a(n);
        }
        super.else = else1;
        return b;
    }
    
    void q() {
        for (int i = 0; i < this.eh; ++i) {
            this.d2[i].new(false);
        }
        for (int j = 0; j < this.ew; ++j) {
            this.es[j].new(false);
        }
        for (int k = 0; k < this.eo; ++k) {
            this.ed[k].new(false);
        }
    }
    
    public void new(final boolean b) {
        if (super.goto && super.byte && super.for) {
            if (this.dW != null && this.dW.for && !super.c) {
                this.dW.for();
                for (int i = 0; i < this.et; ++i) {
                    super.b.V.a(this.d8[i], this.ei, this.eg, this.dW, super.cB, b);
                }
            }
            this.q();
        }
    }
    
    private void r() {
        if (this.ed == null || this.ed.length == this.eo) {
            final e[] ed = new e[this.eo + 10];
            for (int i = 0; i < this.eo; ++i) {
                ed[i] = this.ed[i];
            }
            this.ed = ed;
        }
    }
    
    private void s() {
        if (this.d2 == null || this.d2.length == this.eh) {
            final e[] d2 = new e[this.eh + 10];
            for (int i = 0; i < this.eh; ++i) {
                d2[i] = this.d2[i];
            }
            this.d2 = d2;
        }
    }
    
    private void u() {
        if (this.es == null || this.es.length == this.ew) {
            final e[] es = new e[this.ew + 10];
            for (int i = 0; i < this.ew; ++i) {
                es[i] = this.es[i];
            }
            this.es = es;
        }
    }
    
    private void v() {
        if (this.d0 == null || this.d0.length == this.d7) {
            final a5[] d0 = new a5[this.d7 + 10];
            for (int i = 0; i < this.d7; ++i) {
                d0[i] = this.d0[i];
            }
            this.d0 = d0;
        }
    }
    
    protected final aq if(final int n, final int n2) {
        final double n3 = super.cB.eH * super.cB.eY;
        final double n4 = super.cB.eH * super.cB.eU * super.cB.eS;
        final double n5 = super.cB.eH * super.cB.eI * super.cB.eS;
        final int n6 = n - (super.cB.eL >> 1);
        final int n7 = n2 - (super.cB.eQ >> 1);
        this.dT.try = (float)(n4 + n7 * super.cB.eY * super.cB.eU + n6 * super.cB.eI);
        this.dT.if = (float)(n5 + n7 * super.cB.eY * super.cB.eI - n6 * super.cB.eU);
        this.dT.byte = (float)(n3 - n7 * super.cB.eS);
        final float[][] if1 = super.b.if();
        super.b.a(super.cD, super.cw, super.cy, if1);
        super.b.a(if1, this.dT, this.dZ);
        return this.dZ;
    }
    
    protected boolean do(final int n, final int n2) {
        return false;
    }
    
    void if(final ab ab) {
        final boolean i = ab.i;
        if (!super.for) {
            ab.i = true;
        }
        for (int j = 0; j < this.ew; ++j) {
            this.es[j].if(ab);
        }
        if (!super.for) {
            ab.i = i;
        }
        if (!ab.i && ab.if == 0 && this.do(ab.goto, ab.else)) {
            ab.i = true;
        }
    }
    
    public void a(final char[] array, final a3 a3) {
        if (g.do(array, ac.L) == 0) {
            super.cD = al.do(a3) * super.cz;
        }
        else if (g.do(array, ac.byte) == 0) {
            super.cw = al.do(a3) * super.cz;
        }
        else if (g.do(array, ac.av) == 0) {
            super.cy = al.do(a3) * super.cz;
        }
        else if (g.do(array, ac.M) == 0) {
            super.cx = al.do(a3) * super.cz;
        }
        else if (g.do(array, ac.aw) == 0) {
            super.d = al.if(a3);
            super.b.if(super.cA.Y, 0, super.cA.S - 1);
        }
        else if (g.do(array, ac.P) == 0) {
            if (!(super.for = al.a(a3))) {
                for (int i = 0; i < this.eo; ++i) {
                    this.ed[i].try(super.for);
                }
            }
            super.do = true;
        }
        else if (g.do(array, ac.ac) == 0) {
            this.ej = al.a(a3);
            if (this.ej) {
                this.if(System.currentTimeMillis(), true);
                this.d5 = false;
            }
        }
        else if (g.do(array, ac.n) == 0) {
            if (a3.char != 4) {
                return;
            }
            for (int j = 0; j < this.d7; ++j) {
                if (g.if(a3.int, this.d0[j].long) == 0) {
                    this.ep = j;
                    this.if(System.currentTimeMillis(), true);
                    this.ej = true;
                    this.d5 = false;
                    return;
                }
            }
        }
        else if (g.do(array, ac.B) == 0) {
            int n = 0;
            for (int k = 0; k < this.d7; ++k) {
                if (n != 0) {
                    this.d0[k - 1] = this.d0[k];
                }
                else if (g.if(a3.int, this.d0[k].long) == 0) {
                    n = 1;
                }
            }
            if (n != 0) {
                --this.d7;
            }
        }
        else if (g.do(array, ac.y) == 0) {
            final bf bf = new bf();
            try {
                y.a(bf, a3.int, g.a(a3.int));
                this.byte(bf.do);
            }
            catch (Exception ex) {}
        }
        else if (g.do(array, ao.ez) == 0) {
            this.d9 = al.do(a3) * super.cz;
            this.ex = true;
        }
        else if (g.do(array, ao.er) == 0) {
            this.d4 = al.do(a3) * super.cz;
            this.ex = true;
        }
        else if (g.do(array, ao.eq) == 0) {
            this.ev = al.do(a3) * super.cz;
            this.ey = true;
        }
        else if (g.do(array, ao.ef) == 0) {
            this.el = al.do(a3) * super.cz;
            this.ey = true;
        }
        else if (g.do(array, ao.d6) == 0) {
            this.ec = al.do(a3) * super.cz;
            if (this.ec > 2.5) {
                this.ec = 2.5f;
            }
        }
        else if (g.do(array, ao.eA) == 0) {
            this.dX = al.do(a3) * super.cz;
            if (this.dX < 0.01745) {
                this.dX = 0.01745f;
            }
        }
        else if (g.do(array, ao.eu) == 0) {
            this.ey = false;
        }
        else if (g.do(array, ao.ek) == 0) {
            this.ex = false;
        }
        else if (g.do(array, ao.d3) == 0) {
            this.dU = al.a(a3);
        }
        else if (g.do(array, ac.C) == 0) {
            final bf bf2 = new bf();
            try {
                y.a(bf2, a3.int, g.a(a3.int));
                this.a(bf2.do, true);
            }
            catch (Exception ex2) {}
        }
        else if (g.do(array, ac.ar) == 0) {
            this.ew = super.b.a(this.es, a3.int, this.ew);
            this.eh = super.b.a(this.d2, a3.int, this.eh);
            super.cA.p = super.b.a(super.cA.else, a3.int, super.cA.p);
        }
        super.do = true;
    }
    
    public a3 a(final char[] array) {
        if (g.do(array, ac.L) == 0) {
            super.new.char = 3;
            super.new.else = super.cD * super.cC;
            return super.new;
        }
        if (g.do(array, ac.byte) == 0) {
            super.new.char = 3;
            super.new.else = super.cw * super.cC;
            return super.new;
        }
        if (g.do(array, ac.av) == 0) {
            super.new.char = 3;
            super.new.else = super.cy * super.cC;
            return super.new;
        }
        if (g.do(array, ac.M) == 0) {
            super.new.char = 3;
            super.new.else = super.cx * super.cC;
            return super.new;
        }
        if (g.do(array, ac.P) == 0) {
            super.new.char = 1;
            super.new.long = super.for;
            return super.new;
        }
        if (g.do(array, ac.aw) == 0) {
            super.new.char = 2;
            super.new.case = super.d;
            return super.new;
        }
        if (g.do(array, ac.ac) == 0) {
            super.new.char = 1;
            super.new.long = this.ej;
            return super.new;
        }
        if (g.do(array, ao.d3) == 0) {
            super.new.char = 1;
            super.new.long = this.dU;
            return super.new;
        }
        if (g.do(array, ac.n) == 0) {
            if (this.d7 == 0) {
                super.new.char = 1;
                super.new.long = false;
                return super.new;
            }
            super.new.char = 4;
            if (this.ep != -1) {
                super.new.int = this.d0[this.ep].long;
            }
            else if (g.if(this.dV, ao.dY) != 0) {
                super.new.int = this.dV;
            }
            else {
                super.new.int = this.d0[0].long;
            }
            return super.new;
        }
        else {
            if (g.do(array, ac.ac) == 0) {
                super.new.char = 1;
                super.new.long = this.ej;
                return super.new;
            }
            if (g.do(array, ao.ez) == 0) {
                if (!this.ex) {
                    return ac.a(super.new);
                }
                super.new.char = 3;
                super.new.else = this.d9 * super.cC;
                return super.new;
            }
            else if (g.do(array, ao.er) == 0) {
                if (!this.ex) {
                    return ac.a(super.new);
                }
                super.new.char = 3;
                super.new.else = this.d4 * super.cC;
                return super.new;
            }
            else if (g.do(array, ao.eq) == 0) {
                if (!this.ex) {
                    return ac.a(super.new);
                }
                super.new.char = 3;
                super.new.else = this.ev * super.cC;
                return super.new;
            }
            else if (g.do(array, ao.ef) == 0) {
                if (!this.ex) {
                    return ac.a(super.new);
                }
                super.new.char = 3;
                super.new.else = this.el * super.cC;
                return super.new;
            }
            else if (g.do(array, ao.d6) == 0) {
                if (!this.ea) {
                    return ac.a(super.new);
                }
                super.new.char = 3;
                super.new.else = this.ec * super.cC;
                return super.new;
            }
            else if (g.do(array, ao.eA) == 0) {
                if (!this.ea) {
                    return ac.a(super.new);
                }
                super.new.char = 3;
                super.new.else = this.dX * super.cC;
                return super.new;
            }
            else {
                if (g.do(array, ac.s) == 0) {
                    super.new = new a3();
                    super.new.char = 5;
                    final int goto1 = this.ew + this.eh + this.eo + ((this.dW != null) ? 1 : 0);
                    super.new.goto = goto1;
                    super.new.a = new a3[goto1];
                    for (int i = 0; i < this.ew; ++i) {
                        super.new.a[i] = new a3();
                        super.new.a[i].char = 4;
                        super.new.a[i].int = this.es[i].f;
                    }
                    for (int j = this.ew; j < this.ew + this.eh; ++j) {
                        super.new.a[j] = new a3();
                        super.new.a[j].char = 4;
                        super.new.a[j].int = this.d2[j - this.ew].f;
                    }
                    for (int k = this.ew + this.eh; k < this.ew + this.eh + this.eo; ++k) {
                        super.new.a[k] = new a3();
                        super.new.a[k].char = 4;
                        super.new.a[k].int = this.ed[k - this.ew - this.eh].f;
                    }
                    if (this.dW != null) {
                        super.new.a[goto1 - 1] = new a3();
                        super.new.a[goto1 - 1].char = 4;
                        super.new.a[goto1 - 1].int = this.dW.f;
                    }
                    return super.new;
                }
                return ac.a(super.new);
            }
        }
    }
    
    public void for(final char[] array, final a3 a3) {
        for (int i = 0; i < this.ew; ++i) {
            this.es[i].a(array, a3);
        }
    }
    
    public void if(final char[] array, final a3 a3) {
        for (int i = 0; i < this.eh; ++i) {
            this.d2[i].a(array, a3);
        }
    }
    
    public void do(final char[] array, final a3 a3) {
        for (int i = 0; i < this.eo; ++i) {
            this.ed[i].a(array, a3);
        }
    }
    
    protected void t() {
    }
}
