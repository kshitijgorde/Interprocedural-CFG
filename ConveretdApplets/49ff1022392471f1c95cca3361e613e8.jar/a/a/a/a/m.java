// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class m extends bl
{
    ao[] O;
    int J;
    f L;
    float K;
    float M;
    float I;
    boolean E;
    boolean F;
    boolean G;
    boolean H;
    String D;
    private boolean N;
    
    m(final ac q, final l h, final boolean h2) {
        this.O = null;
        this.J = 0;
        this.K = 0.017453292f;
        this.M = 57.29578f;
        this.I = 0.0f;
        this.E = false;
        this.F = false;
        this.G = true;
        this.D = "";
        this.N = false;
        super.q = q;
        super.h = h;
        this.H = h2;
    }
    
    public void if(final ae n) {
        super.n = n;
        this.D = n.m;
    }
    
    boolean if(final boolean b) {
        if (!super.goto && this.J != 0) {
            super.goto = true;
            if (this.G) {
                for (int i = 0; i < this.J; ++i) {
                    if (this.O[i].byte(this.G)) {
                        super.goto = true;
                        break;
                    }
                }
            }
            else {
                for (int j = 0; j < this.J; ++j) {
                    if (!this.O[j].byte(this.G)) {
                        super.goto = false;
                    }
                }
            }
        }
        if (b && super.goto) {
            super.h.if(this.D);
        }
        return super.goto;
    }
    
    void for(final long n) {
        super.l = true;
        for (int i = 0; i < this.J; ++i) {
            this.O[i].new(n);
        }
        this.if(bl.byte);
    }
    
    public boolean do(final long n) {
        boolean do1 = super.do;
        super.do = false;
        if (super.m) {
            if (super.goto) {
                this.if(bl.p);
            }
            boolean b = true;
            for (int i = 0; i < this.J; ++i) {
                do1 |= this.O[i].a(n);
                b &= this.O[i].char;
            }
            if (!this.N && b) {
                this.N = true;
                super.h.case(this.D);
            }
        }
        else if (super.n != null && super.n.b && !super.m) {
            if (super.n.j != 0) {
                super.n = super.n.new[0];
            }
            if (!super.n.b) {
                return false;
            }
            final bf bf = new bf();
            try {
                y.a(bf, super.n.k);
                super.d = bf.do;
            }
            catch (Exception ex) {
                super.goto = true;
                this.L = new f(this, super.q, 0.0f);
                super.m = true;
                System.out.println("XML error in " + super.n.m + " file.");
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
                        this.K = 1.0f;
                        this.M = 1.0f;
                    }
                }
                else if (super.d.try[j].toLowerCase().compareTo("quickdisplay") == 0 && super.d.new[j].toLowerCase().indexOf("false") != -1) {
                    this.G = false;
                }
            }
            a2 a2 = super.d.if;
            this.L = new f(this, super.q, this.K);
            this.try();
            this.a(this.O[this.J] = this.L);
            ++this.J;
            while (a2 != null) {
                if (a2.a.toLowerCase().compareTo("meta") == 0) {
                    for (a2 a3 = a2.if; a3 != null; a3 = a3.for) {
                        if (a3.a.toLowerCase().compareTo("gps") == 0) {
                            a2 f = a3.if;
                            super.f = f;
                            while (f != null) {
                                if (f.a.toLowerCase().compareTo("gpsimgdirection") == 0) {
                                    for (int k = 0; k < f.do; ++k) {
                                        if (f.try[k].toLowerCase().compareTo("gpsimgdirection") == 0) {
                                            this.I = new Float(f.new[k]) * this.K;
                                            this.E = true;
                                        }
                                    }
                                }
                                f = f.for;
                            }
                        }
                    }
                    this.a(a2);
                }
                else if (a2.a.toLowerCase().compareTo("camera") == 0) {
                    this.L.int(a2);
                }
                a2 = a2.for;
            }
            for (a2 a4 = super.d.if; a4 != null; a4 = a4.for) {
                if (a4.a.toLowerCase().compareTo("script") == 0) {
                    try {
                        final j l = new j();
                        super.case = new ax(this);
                        l.a(a4.int.if.concat("\u0000").toCharArray());
                        super.case.a(l);
                        super.case.a();
                    }
                    catch (ar ar) {
                        ar.a(super.case.for());
                        super.case = null;
                    }
                }
                else {
                    this.if(a4, false);
                }
            }
            super.q.if(this.O, 0, this.J - 1);
            this.if();
            super.m = true;
        }
        return do1;
    }
    
    public void a() {
        super.a();
        final int j = this.J;
        this.J = 0;
        for (int i = 0; i < j; ++i) {
            this.O[i].if();
            this.O[i] = null;
        }
        this.O = null;
        if (this.L != null) {
            this.L.if();
            this.L = null;
        }
    }
    
    private void if(final a2 a2, final boolean else1) {
        this.try();
        try {
            if (a2.a.toLowerCase().compareTo("panocylinder") == 0) {
                (this.O[this.J] = (ao)Class.forName("a.a.a.a.c").getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null)).a(super.q, this.K, super.n, this.L, this);
                this.O[this.J].else = else1;
                this.O[this.J].int(a2);
                this.a(this.O[this.J]);
                ++this.J;
            }
            else if (a2.a.toLowerCase().compareTo("panosphere") == 0) {
                (this.O[this.J] = (ao)Class.forName("a.a.a.a.an").getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null)).a(super.q, this.K, super.n, this.L, this);
                this.O[this.J].else = else1;
                this.O[this.J].int(a2);
                this.a(this.O[this.J]);
                ++this.J;
            }
            else if (a2.a.toLowerCase().compareTo("panocube") == 0) {
                (this.O[this.J] = (ao)Class.forName("a.a.a.a.bi").getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null)).a(super.q, this.K, super.n, this.L, this);
                this.O[this.J].else = else1;
                this.O[this.J].int(a2);
                this.a(this.O[this.J]);
                ++this.J;
            }
            else if (a2.a.toLowerCase().compareTo("panoflat") == 0) {
                (this.O[this.J] = (ao)Class.forName("a.a.a.a.w").getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null)).a(super.q, this.K, super.n, this.L, this);
                this.O[this.J].else = else1;
                this.O[this.J].int(a2);
                this.a(this.O[this.J]);
                ++this.J;
            }
            else if (a2.a.toLowerCase().compareTo("sfx") == 0) {
                boolean b = false;
                String s = "";
                for (int i = 0; i < a2.do; ++i) {
                    if (a2.try[i].toLowerCase().compareTo("name") == 0) {
                        s = a2.new[i];
                    }
                }
                try {
                    String s2 = null;
                    if (super.q.c != null && s.substring(3).compareTo("Sfx") == 0) {
                        s2 = super.q.c.getProperty(s);
                    }
                    if (s2 == null) {
                        s2 = "com.immervision.pure.player." + s;
                    }
                    (this.O[this.J] = (ao)Class.forName(s2).getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null)).a(super.q, this.K, super.n, this.L, this);
                    this.O[this.J].else = else1;
                    this.O[this.J].int(a2);
                    this.a(this.O[this.J]);
                    ++this.J;
                }
                catch (Exception ex) {
                    b = true;
                }
                if (b) {
                    System.out.println("Can't load sfx: " + s);
                }
            }
        }
        catch (Exception ex2) {}
    }
    
    protected void a(final char[] array, final a3 a3) {
        if (a.a.a.a.g.if(array, ac.y) == 0) {
            final bf bf = new bf();
            try {
                y.a(bf, a3.int);
                this.if(bf.do, true);
                super.q.if(this.O, 0, this.J - 1);
            }
            catch (Exception ex) {}
            super.do = true;
        }
        else if (a.a.a.a.g.if(array, ac.ak) == 0) {
            this.J = super.q.a(this.O, a3.int, this.J);
            super.r = super.q.a(super.i, a3.int, super.r);
            super.do = true;
        }
    }
    
    protected a3 a(final char[] array, final char[] array2) {
        if (a.a.a.a.g.if(array2, ac.o) == 0) {
            return ac.a(this.O, this.J);
        }
        if (a.a.a.a.g.if(array2, ac.char) == 0) {
            final a3 a3 = new a3();
            a3.char = 4;
            if (this.K == 1.0f) {
                a3.int = ac.X;
            }
            else {
                a3.int = ac.r;
            }
            return a3;
        }
        if (a.a.a.a.g.if(array2, ac.Q) == 0) {
            final a3 a4 = new a3();
            a4.char = 4;
            a4.int = (String.valueOf(super.n.m) + "\u0000").toCharArray();
            return a4;
        }
        return ac.a(new a3());
    }
    
    private void try() {
        if (this.O == null || this.O.length == this.J) {
            final ao[] o = new ao[this.J + 10];
            for (int i = 0; i < this.J; ++i) {
                o[i] = this.O[i];
            }
            this.O = o;
        }
    }
    
    public ad new() {
        if (super.l) {
            final ad case1 = this.L.case(false);
            for (int i = 0; i < this.J; ++i) {
                this.O[i].new(false);
            }
            return case1;
        }
        return null;
    }
    
    void a(final boolean b) {
        if (super.l && this.L != null) {
            this.L.new(false);
            for (int i = 0; i < this.J; ++i) {
                this.O[i].new(b);
            }
            if (this.L.eM) {
                this.L.u();
            }
        }
    }
    
    void do(final ab ab) {
        this.L.if(ab);
        if (ab.goto >= 0 && ab.goto < this.L.et && ab.else >= 0 && ab.else < this.L.ey) {
            for (int i = 0; i < this.J; ++i) {
                this.O[i].if(ab);
            }
            ab.i = true;
        }
        else {
            final boolean j = ab.i;
            ab.i = true;
            for (int k = 0; k < this.J; ++k) {
                this.O[k].if(ab);
            }
            ab.i = j;
        }
    }
    
    public a3 if(final char[] array) {
        try {
            if (super.case != null) {
                return super.case.a(array);
            }
        }
        catch (ar ar) {
            ar.a(super.case.for());
        }
        return new a3();
    }
}
