// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class v extends bl
{
    bm[] K;
    int F;
    float G;
    public aw H;
    o I;
    t D;
    ad J;
    public long L;
    private a2 E;
    
    v(final ac j, final o i, final l k) {
        this.K = null;
        this.F = 0;
        this.G = 0.017453292f;
        this.H = null;
        this.I = null;
        this.D = null;
        this.L = 0L;
        this.E = null;
        super.j = j;
        this.I = i;
        super.i = k;
        this.J = super.j.goto;
        this.D = new t();
        super.B = "GUI";
    }
    
    public void a(final ae void1) {
        super.void = void1;
    }
    
    void if(final long n) {
        this.int();
        super.h = true;
        this.a(bl.t);
        for (int i = 0; i < this.F; ++i) {
            this.K[i].int(n);
        }
    }
    
    private void int() {
        if (this.E == null) {
            return;
        }
        this.do();
        this.K[this.F] = this.I;
        ((o)this.K[this.F]).a(this.E, super.void, this);
        this.a(this.K[this.F]);
        ++this.F;
        super.j.if(this.K, 0, this.F - 1);
        super.long = true;
    }
    
    boolean new() {
        super.z = true;
        for (int i = 0; i < this.F; ++i) {
            if (!this.K[i].byte) {
                super.z = false;
            }
        }
        return super.z;
    }
    
    public boolean a(final long n) {
        if (super.k) {
            if (super.h) {
                this.a(bl.l);
            }
            boolean long1 = super.long;
            super.long = false;
            for (int i = 0; i < this.F; ++i) {
                long1 |= this.K[i].a(n);
            }
            return long1;
        }
        if (super.void != null && super.void.b && !super.k) {
            if (super.void.k != 0) {
                super.void = super.void.try[0];
            }
            if (!super.void.b) {
                return false;
            }
            if (super.void.do == 0) {
                super.void = null;
                System.out.println("Loading default GUI.");
                super.j.P.f = true;
                super.j.P.i = null;
                return false;
            }
            final bf bf = new bf();
            try {
                a.a.a.a.y.a(bf, super.void.l, super.void.do);
                super.d = bf.do;
            }
            catch (Exception ex) {
                System.out.println("XML error in " + super.void.n + " file.");
                super.void = null;
                System.out.println("Loading default GUI.");
                this.a(super.j.B.a(new String(ac.Y), null, false, false, false));
                return false;
            }
            for (int j = 0; j < super.d.do; ++j) {
                if (super.d.try[j].toLowerCase().compareTo("xmlns") == 0) {
                    if (super.d.new[j].toLowerCase().compareTo("http://www.immervision.com/interface") != 0) {
                        return false;
                    }
                }
                else if (super.d.try[j].toLowerCase().compareTo("angletype") == 0) {
                    if (super.d.new[j].toLowerCase().compareTo("radius") == 0) {
                        this.G = 1.0f;
                    }
                }
                else if (super.d.try[j].toLowerCase().compareTo("time") == 0) {
                    this.L = new Integer(super.d.new[j]);
                }
            }
            this.if(super.d);
            for (a2 e = super.d.if; e != null; e = e.for) {
                if (e.a.toLowerCase().compareTo("meta") == 0) {
                    this.a(e);
                }
                else if (e.a.toLowerCase().compareTo("viewer") == 0) {
                    this.do();
                    this.K[this.F] = new aw(super.j);
                    ((aw)this.K[this.F]).if(e, super.void, this);
                    this.H = (aw)this.K[this.F];
                    this.a(this.K[this.F]);
                    ++this.F;
                }
                else if (e.a.toLowerCase().compareTo("progressbar") == 0) {
                    this.E = e;
                }
                else if (e.a.toLowerCase().compareTo("cursors") == 0 && ac.aj >= 2) {
                    this.do();
                    this.K[this.F] = this.D;
                    ((t)this.K[this.F]).a(super.j, e, super.void, this);
                    this.a(this.K[this.F]);
                    ++this.F;
                }
                else if (e.a.toLowerCase().compareTo("script") == 0) {
                    try {
                        final j k = new j();
                        super.x = new ax(this);
                        k.a((String.valueOf(e.int.do) + "\u0000").toCharArray());
                        super.x.a(k);
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
                    this.a(e, false);
                }
            }
            this.if();
            super.k = true;
            super.j.if(this.K, 0, this.F - 1);
        }
        return true;
    }
    
    private void a(final a2 a2, final boolean goto1) {
        try {
            Class<?> clazz = null;
            if (a2.a.toLowerCase().compareTo("image") == 0) {
                clazz = Class.forName("a.a.a.a.aa");
            }
            else if (a2.a.toLowerCase().compareTo("button") == 0) {
                clazz = Class.forName("a.a.a.a.k");
            }
            else if (a2.a.toLowerCase().compareTo("checkbox") == 0) {
                clazz = Class.forName("a.a.a.a.bp");
            }
            else if (a2.a.toLowerCase().compareTo("activearea") == 0) {
                clazz = Class.forName("a.a.a.a.x");
            }
            else if (a2.a.toLowerCase().compareTo("text") == 0) {
                clazz = Class.forName("a.a.a.a.a1");
            }
            else if (a2.a.toLowerCase().compareTo("compass") == 0) {
                clazz = Class.forName("a.a.a.a.ba");
            }
            else if (a2.a.toLowerCase().compareTo("sound") == 0) {
                clazz = Class.forName("a.a.a.a.u");
            }
            else if (a2.a.toLowerCase().compareTo("rectangle") == 0) {
                clazz = Class.forName("a.a.a.a.bn");
            }
            if (clazz != null) {
                this.do();
                this.K[this.F] = (bm)clazz.getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null);
                this.K[this.F].goto = goto1;
                this.K[this.F].a(super.j, a2, super.void, this);
                this.a(this.K[this.F]);
                ++this.F;
            }
        }
        catch (Exception ex) {
            System.out.println("Can't load missing functionnality :" + a2.a);
        }
    }
    
    protected void if(final char[] array, final a3 a3) {
        if (a.a.a.a.g.do(array, ac.D) == 0) {
            final bf bf = new bf();
            try {
                a.a.a.a.y.a(bf, a3.int, a.a.a.a.g.a(a3.int));
                this.a(bf.do, true);
                super.j.if(this.K, 0, this.F - 1);
                super.long = true;
            }
            catch (Exception ex) {}
        }
        else if (a.a.a.a.g.do(array, ac.at) == 0) {
            this.F = super.j.a(this.K, a3.int, this.F);
            super.p = super.j.a(super.else, a3.int, super.p);
            super.long = true;
        }
    }
    
    protected a3 do(final char[] array, final char[] array2) {
        if (a.a.a.a.g.do(array2, ac.t) == 0) {
            return ac.a(this.K, this.F);
        }
        if (a.a.a.a.g.do(array2, ac.X) == 0) {
            final a3 a3 = new a3();
            a3.char = 4;
            a3.int = (String.valueOf(super.void.n) + "\u0000").toCharArray();
            return a3;
        }
        return ac.a(new a3());
    }
    
    public void for() {
        for (int i = 0; i < this.F; ++i) {
            this.K[i].c();
        }
    }
    
    private void do() {
        if (this.K == null || this.K.length == this.F) {
            final bm[] k = new bm[this.F + 10];
            for (int i = 0; i < this.F; ++i) {
                k[i] = this.K[i];
            }
            this.K = k;
        }
    }
    
    void if(final ab ab) {
        for (int i = this.F - 1; i >= 0; --i) {
            this.K[i].a(ab);
        }
        ab.i = false;
    }
    
    void a(final ab ab) {
    }
    
    public a3 a(final char[] array) {
        if (super.x != null) {
            return super.x.a(array);
        }
        return new a3();
    }
    
    public void a() {
        super.a();
        final int f = this.F;
        this.F = 0;
        for (int i = 0; i < f; ++i) {
            this.K[i].if();
            this.K[i] = null;
        }
        this.K = null;
    }
}
