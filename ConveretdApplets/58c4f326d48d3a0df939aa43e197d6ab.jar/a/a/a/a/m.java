// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class m extends bl
{
    ao[] aa;
    int U;
    f W;
    static float V;
    static float Y;
    float T;
    boolean N;
    boolean P;
    boolean Q;
    boolean R;
    String M;
    public boolean Z;
    public boolean S;
    public boolean O;
    protected boolean X;
    
    static {
        m.V = 0.017453292f;
        m.Y = 57.29578f;
    }
    
    m(final ac j, final l i, final boolean r) {
        this.aa = null;
        this.U = 0;
        this.T = 0.0f;
        this.N = false;
        this.P = false;
        this.Q = true;
        this.M = "";
        this.Z = false;
        this.S = false;
        this.O = false;
        this.X = false;
        super.j = j;
        super.i = i;
        this.R = r;
        super.B = "Panorama";
    }
    
    public void if(final ae void1) {
        super.void = void1;
        this.M = void1.n;
    }
    
    boolean if(final boolean b) {
        if (!super.z && this.U != 0) {
            super.z = true;
            for (int i = 0; i < this.U; ++i) {
                if (this.aa[i].for) {
                    super.z &= this.aa[i].byte(this.Q);
                }
                else {
                    this.aa[i].byte(this.Q);
                }
            }
        }
        return super.z;
    }
    
    void for(final long n) {
        super.h = true;
        for (int i = 0; i < this.U; ++i) {
            this.aa[i].new(n);
        }
        this.if(bl.t);
    }
    
    public boolean do(final long n) {
        boolean long1 = super.long;
        super.long = false;
        if (super.k) {
            if (super.z) {
                this.if(bl.l);
            }
            boolean b = true;
            for (int i = 0; i < this.U; ++i) {
                long1 |= this.aa[i].a(n);
                b &= this.aa[i].else;
            }
            if (!this.Z && b) {
                this.Z = true;
                super.i.char(this.M);
            }
        }
        else if (super.void != null && super.void.b && !super.k) {
            if (super.void.k != 0) {
                super.void = super.void.try[0];
            }
            if (!super.void.b) {
                return false;
            }
            final bf bf = new bf();
            try {
                a.a.a.a.y.a(bf, super.void.l, super.void.do);
                super.d = bf.do;
            }
            catch (Exception ex) {
                this.X = true;
                super.z = true;
                this.W = new f(this, super.j, 0.0f);
                super.k = true;
                System.out.println("XML error in " + super.void.n + " file.");
                return false;
            }
            for (int j = 0; j < super.d.do; ++j) {
                if (super.d.try[j].toLowerCase().compareTo("xmlns") == 0) {
                    if (super.d.new[j].toLowerCase().compareTo("http://www.immervision.com/panorama") != 0) {
                        return false;
                    }
                }
                else if (super.d.try[j].toLowerCase().compareTo("angletype") == 0) {
                    if (super.d.new[j].toLowerCase().compareTo("radius") == 0) {
                        m.V = 1.0f;
                        m.Y = 1.0f;
                    }
                }
                else if (super.d.try[j].toLowerCase().compareTo("quickdisplay") == 0 && super.d.new[j].toLowerCase().indexOf("false") != -1) {
                    this.Q = false;
                }
            }
            this.if(super.d);
            a2 a2 = super.d.if;
            this.W = new f(this, super.j, m.V);
            this.byte();
            this.a(this.aa[this.U] = this.W);
            ++this.U;
            while (a2 != null) {
                if (a2.a.toLowerCase().compareTo("meta") == 0) {
                    for (a2 a3 = a2.if; a3 != null; a3 = a3.for) {
                        if (a3.a.toLowerCase().compareTo("gps") == 0) {
                            a2 b2 = a3.if;
                            super.b = b2;
                            while (b2 != null) {
                                if (b2.a.toLowerCase().compareTo("gpsimgdirection") == 0) {
                                    for (int k = 0; k < b2.do; ++k) {
                                        if (b2.try[k].toLowerCase().compareTo("gpsimgdirection") == 0) {
                                            this.T = new Float(b2.new[k]) * m.V;
                                            this.N = true;
                                        }
                                    }
                                }
                                b2 = b2.for;
                            }
                        }
                    }
                    this.a(a2);
                }
                else if (a2.a.toLowerCase().compareTo("camera") == 0) {
                    this.W.int(a2);
                }
                a2 = a2.for;
            }
            for (a2 a4 = super.d.if; a4 != null; a4 = a4.for) {
                if (a4.a.toLowerCase().compareTo("script") == 0) {
                    try {
                        final j l = new j();
                        super.x = new ax(this);
                        l.a(a4.int.do.concat("\u0000").toCharArray());
                        super.c = a4.int.a;
                        super.x.a(l);
                        super.x.a();
                    }
                    catch (ar ar) {
                        ar.a();
                        System.out.print(super.c + super.x.do().try());
                        ar.a(super.x.do());
                        super.x = null;
                    }
                }
                else {
                    this.if(a4, false);
                }
            }
            super.j.if(this.aa, 0, this.U - 1);
            this.if();
            super.k = true;
        }
        return this.O = long1;
    }
    
    public void a() {
        super.a();
        final int u = this.U;
        this.U = 0;
        for (int i = 0; i < u; ++i) {
            this.aa[i].if();
            this.aa[i] = null;
        }
        this.aa = null;
        if (this.W != null) {
            this.W.if();
            this.W = null;
        }
    }
    
    private void if(final a2 a2, final boolean b) {
        this.byte();
        try {
            Class<?> clazz = null;
            if (a2.a.toLowerCase().compareTo("panocylinder") == 0) {
                clazz = Class.forName("a.a.a.a.c");
            }
            else if (a2.a.toLowerCase().compareTo("panosphere") == 0) {
                clazz = Class.forName("a.a.a.a.an");
            }
            else if (a2.a.toLowerCase().compareTo("panocube") == 0) {
                clazz = Class.forName("a.a.a.a.bi");
            }
            else if (a2.a.toLowerCase().compareTo("panoflat") == 0) {
                clazz = Class.forName("a.a.a.a.w");
            }
            else if (a2.a.toLowerCase().compareTo("sfx") == 0) {
                boolean b2 = false;
                String s = "";
                for (int i = 0; i < a2.do; ++i) {
                    if (a2.try[i].toLowerCase().compareTo("name") == 0) {
                        s = a2.new[i];
                    }
                }
                try {
                    String s2 = null;
                    if (super.j.e != null && s.toLowerCase().startsWith("sfx")) {
                        s2 = super.j.e.getProperty(s.toLowerCase());
                    }
                    if (s2 == null) {
                        s2 = "com.immervision.pure.player." + s;
                    }
                    (this.aa[this.U] = (ao)Class.forName(s2).getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null)).a(super.j, m.V, super.void, this.W, this);
                    this.aa[this.U].goto = b;
                    this.aa[this.U].int(a2);
                    this.a(this.aa[this.U]);
                    ++this.U;
                }
                catch (Exception ex) {
                    b2 = true;
                }
                if (b2) {
                    System.out.println("Can't load sfx: " + s);
                }
                return;
            }
            if (clazz != null) {
                (this.aa[this.U] = (ao)clazz.getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null)).a(super.j, m.V, super.void, this.W, this);
                this.aa[this.U].goto = b;
                this.aa[this.U].int(a2);
                this.a(this.aa[this.U]);
                ++this.U;
            }
        }
        catch (Exception ex2) {
            System.out.println("Can't load missing functionnality :" + a2.a);
        }
    }
    
    protected void a(final char[] array, final a3 a3) {
        if (a.a.a.a.g.do(array, ac.D) == 0) {
            final bf bf = new bf();
            try {
                a.a.a.a.y.a(bf, a3.int, a.a.a.a.g.a(a3.int));
                this.if(bf.do, true);
                super.j.if(this.aa, 0, this.U - 1);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            super.long = true;
        }
        else if (a.a.a.a.g.do(array, ac.at) == 0) {
            this.U = super.j.a(this.aa, a3.int, this.U);
            super.p = super.j.a(super.else, a3.int, super.p);
            super.long = true;
        }
    }
    
    protected a3 a(final char[] array, final char[] array2) {
        if (a.a.a.a.g.do(array2, ac.t) == 0) {
            return ac.a(this.aa, this.U);
        }
        if (a.a.a.a.g.do(array2, ac.else) == 0) {
            final a3 a3 = new a3();
            a3.char = 4;
            if (m.V == 1.0f) {
                a3.int = ac.af;
            }
            else {
                a3.int = ac.w;
            }
            return a3;
        }
        if (a.a.a.a.g.do(array2, ac.X) == 0) {
            final a3 a4 = new a3();
            a4.char = 4;
            a4.int = (String.valueOf(super.void.n) + "\u0000").toCharArray();
            return a4;
        }
        return ac.a(new a3());
    }
    
    private void byte() {
        if (this.aa == null || this.aa.length == this.U) {
            final ao[] aa = new ao[this.U + 10];
            for (int i = 0; i < this.U; ++i) {
                aa[i] = this.aa[i];
            }
            this.aa = aa;
        }
    }
    
    public ad try() {
        if (!super.h) {
            return null;
        }
        final ad case1 = this.W.case(false);
        if (case1 == null) {
            return this.W.eF = null;
        }
        for (int i = 0; i < this.U; ++i) {
            this.aa[i].new(false);
        }
        this.W.eF = null;
        this.W.e9 = null;
        return case1;
    }
    
    void a(final boolean b) {
        if (super.h && this.W != null) {
            this.W.new(false);
            for (int i = 0; i < this.U; ++i) {
                this.aa[i].new(b);
            }
            if (this.W.e5) {
                this.W.u();
            }
        }
    }
    
    void do(final ab ab) {
        this.W.if(ab);
        if (ab.goto >= 0 && ab.goto < this.W.eM && ab.else >= 0 && ab.else < this.W.eR) {
            for (int i = this.U - 1; i >= 0; --i) {
                this.aa[i].if(ab);
            }
            ab.i = true;
        }
        else {
            final boolean j = ab.i;
            ab.i = true;
            for (int k = this.U - 1; k >= 0; --k) {
                this.aa[k].if(ab);
            }
            ab.i = j;
        }
    }
    
    public a3 if(final char[] array) {
        if (super.x != null) {
            return super.x.a(array);
        }
        return new a3();
    }
}
