// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class m extends bl
{
    ao[] Y;
    int S;
    f U;
    float T;
    float W;
    float R;
    boolean L;
    boolean N;
    boolean O;
    boolean P;
    String K;
    public boolean X;
    public boolean Q;
    public boolean M;
    protected boolean V;
    
    m(final ac j, final l i, final boolean p3) {
        this.Y = null;
        this.S = 0;
        this.T = 0.017453292f;
        this.W = 57.29578f;
        this.R = 0.0f;
        this.L = false;
        this.N = false;
        this.O = true;
        this.K = "";
        this.X = false;
        this.Q = false;
        this.M = false;
        this.V = false;
        super.j = j;
        super.i = i;
        this.P = p3;
        super.B = "Panorama";
    }
    
    public void if(final ae void1) {
        super.void = void1;
        this.K = void1.n;
    }
    
    boolean if(final boolean b) {
        if (!super.z && this.S != 0) {
            super.z = true;
            for (int i = 0; i < this.S; ++i) {
                if (this.Y[i].for) {
                    super.z &= this.Y[i].byte(this.O);
                }
                else {
                    this.Y[i].byte(this.O);
                }
            }
        }
        return super.z;
    }
    
    void for(final long n) {
        super.h = true;
        for (int i = 0; i < this.S; ++i) {
            this.Y[i].new(n);
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
            for (int i = 0; i < this.S; ++i) {
                long1 |= this.Y[i].a(n);
                b &= this.Y[i].else;
            }
            if (!this.X && b) {
                this.X = true;
                super.i.byte(this.K);
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
                this.V = true;
                super.z = true;
                this.U = new f(this, super.j, 0.0f);
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
                        this.T = 1.0f;
                        this.W = 1.0f;
                    }
                }
                else if (super.d.try[j].toLowerCase().compareTo("quickdisplay") == 0 && super.d.new[j].toLowerCase().indexOf("false") != -1) {
                    this.O = false;
                }
            }
            this.if(super.d);
            a2 a2 = super.d.if;
            this.U = new f(this, super.j, this.T);
            this.try();
            this.a(this.Y[this.S] = this.U);
            ++this.S;
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
                                            this.R = new Float(b2.new[k]) * this.T;
                                            this.L = true;
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
                    this.U.int(a2);
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
            super.j.if(this.Y, 0, this.S - 1);
            this.if();
            super.k = true;
        }
        return this.M = long1;
    }
    
    public void a() {
        super.a();
        final int s = this.S;
        this.S = 0;
        for (int i = 0; i < s; ++i) {
            this.Y[i] = null;
        }
        this.Y = null;
        if (this.U != null) {
            this.U.if();
            this.U = null;
        }
    }
    
    private void if(final a2 a2, final boolean b) {
        this.try();
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
                    (this.Y[this.S] = (ao)Class.forName(s2).getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null)).a(super.j, this.T, super.void, this.U, this);
                    this.Y[this.S].goto = b;
                    this.Y[this.S].int(a2);
                    this.a(this.Y[this.S]);
                    ++this.S;
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
                (this.Y[this.S] = (ao)clazz.getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null)).a(super.j, this.T, super.void, this.U, this);
                this.Y[this.S].goto = b;
                this.Y[this.S].int(a2);
                this.a(this.Y[this.S]);
                ++this.S;
            }
        }
        catch (Exception ex2) {}
    }
    
    protected void a(final char[] array, final a3 a3) {
        if (a.a.a.a.g.do(array, ac.C) == 0) {
            final bf bf = new bf();
            try {
                a.a.a.a.y.a(bf, a3.int, a.a.a.a.g.a(a3.int));
                this.if(bf.do, true);
                super.j.if(this.Y, 0, this.S - 1);
            }
            catch (Exception ex) {}
            super.long = true;
        }
        else if (a.a.a.a.g.do(array, ac.ar) == 0) {
            this.S = super.j.a(this.Y, a3.int, this.S);
            super.p = super.j.a(super.else, a3.int, super.p);
            super.long = true;
        }
    }
    
    protected a3 a(final char[] array, final char[] array2) {
        if (a.a.a.a.g.do(array2, ac.s) == 0) {
            return ac.a(this.Y, this.S);
        }
        if (a.a.a.a.g.do(array2, ac.else) == 0) {
            final a3 a3 = new a3();
            a3.char = 4;
            if (this.T == 1.0f) {
                a3.int = ac.ad;
            }
            else {
                a3.int = ac.v;
            }
            return a3;
        }
        if (a.a.a.a.g.do(array2, ac.W) == 0) {
            final a3 a4 = new a3();
            a4.char = 4;
            a4.int = (String.valueOf(super.void.n) + "\u0000").toCharArray();
            return a4;
        }
        return ac.a(new a3());
    }
    
    private void try() {
        if (this.Y == null || this.Y.length == this.S) {
            final ao[] y = new ao[this.S + 10];
            for (int i = 0; i < this.S; ++i) {
                y[i] = this.Y[i];
            }
            this.Y = y;
        }
    }
    
    public ad new() {
        if (super.h) {
            final ad case1 = this.U.case(false);
            for (int i = 0; i < this.S; ++i) {
                this.Y[i].new(false);
            }
            return case1;
        }
        return null;
    }
    
    void a(final boolean b) {
        if (super.h && this.U != null) {
            this.U.new(false);
            for (int i = 0; i < this.S; ++i) {
                this.Y[i].new(b);
            }
            if (this.U.e4) {
                this.U.w();
            }
        }
    }
    
    void do(final ab ab) {
        this.U.if(ab);
        if (ab.goto >= 0 && ab.goto < this.U.eL && ab.else >= 0 && ab.else < this.U.eQ) {
            for (int i = this.S - 1; i >= 0; --i) {
                this.Y[i].if(ab);
            }
            ab.i = true;
        }
        else {
            final boolean j = ab.i;
            ab.i = true;
            for (int k = this.S - 1; k >= 0; --k) {
                this.Y[k].if(ab);
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
