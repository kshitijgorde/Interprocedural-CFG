// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class v extends bl
{
    bm[] I;
    int E;
    float G;
    public aw J;
    o D;
    t H;
    ad F;
    
    v(final ac j, final o d, final l i) {
        this.I = null;
        this.E = 0;
        this.G = 0.017453292f;
        this.J = null;
        this.D = null;
        this.H = null;
        super.j = j;
        this.D = d;
        super.i = i;
        this.F = super.j.goto;
        this.H = new t();
        super.B = "GUI";
    }
    
    public void a(final ae void1) {
        super.void = void1;
    }
    
    void if(final long n) {
        super.h = true;
        this.a(bl.t);
        for (int i = 0; i < this.E; ++i) {
            this.I[i].int(n);
        }
    }
    
    boolean int() {
        super.z = true;
        for (int i = 0; i < this.E; ++i) {
            if (!this.I[i].byte) {
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
            for (int i = 0; i < this.E; ++i) {
                long1 |= this.I[i].a(n);
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
                this.a(super.j.A.a("immergui.gif", null, false, false, false));
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
                this.a(super.j.A.a("immergui.xml", null, false, false, false));
                return false;
            }
            for (int j = 0; j < super.d.do; ++j) {
                if (super.d.try[j].toLowerCase().compareTo("xmlns") == 0) {
                    if (super.d.new[j].toLowerCase().compareTo("http://www.immervision.com/interface") != 0) {
                        return false;
                    }
                }
                else if (super.d.try[j].toLowerCase().compareTo("angletype") == 0 && super.d.new[j].toLowerCase().compareTo("radius") == 0) {
                    this.G = 1.0f;
                }
            }
            this.if(super.d);
            for (a2 a2 = super.d.if; a2 != null; a2 = a2.for) {
                if (a2.a.toLowerCase().compareTo("meta") == 0) {
                    this.a(a2);
                }
                else if (a2.a.toLowerCase().compareTo("viewer") == 0) {
                    this.do();
                    this.I[this.E] = new aw(super.j);
                    ((aw)this.I[this.E]).do(a2, super.void, this);
                    this.J = (aw)this.I[this.E];
                    this.a(this.I[this.E]);
                    ++this.E;
                }
                else if (a2.a.toLowerCase().compareTo("progressbar") == 0) {
                    this.do();
                    this.I[this.E] = this.D;
                    ((o)this.I[this.E]).a(a2, super.void, this);
                    this.a(this.I[this.E]);
                    ++this.E;
                }
                else if (a2.a.toLowerCase().compareTo("cursors") == 0 && ac.ah >= 2) {
                    this.do();
                    this.I[this.E] = this.H;
                    ((t)this.I[this.E]).a(super.j, a2, super.void, this);
                    this.a(this.I[this.E]);
                    ++this.E;
                }
                else if (a2.a.toLowerCase().compareTo("script") == 0) {
                    try {
                        final j k = new j();
                        super.x = new ax(this);
                        k.a((String.valueOf(a2.int.do) + "\u0000").toCharArray());
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
                    this.a(a2, false);
                }
            }
            this.if();
            super.k = true;
            super.j.if(this.I, 0, this.E - 1);
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
                this.I[this.E] = (bm)clazz.getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null);
                this.I[this.E].goto = goto1;
                this.I[this.E].a(super.j, a2, super.void, this);
                this.a(this.I[this.E]);
                ++this.E;
            }
        }
        catch (Exception ex) {}
    }
    
    protected void if(final char[] array, final a3 a3) {
        if (a.a.a.a.g.do(array, ac.C) == 0) {
            final bf bf = new bf();
            try {
                a.a.a.a.y.a(bf, a3.int, a.a.a.a.g.a(a3.int));
                this.a(bf.do, true);
                super.j.if(this.I, 0, this.E - 1);
                super.long = true;
            }
            catch (Exception ex) {}
        }
        else if (a.a.a.a.g.do(array, ac.ar) == 0) {
            this.E = super.j.a(this.I, a3.int, this.E);
            super.p = super.j.a(super.else, a3.int, super.p);
            super.long = true;
        }
    }
    
    protected a3 do(final char[] array, final char[] array2) {
        if (a.a.a.a.g.do(array2, ac.s) == 0) {
            return ac.a(this.I, this.E);
        }
        if (a.a.a.a.g.do(array2, ac.W) == 0) {
            final a3 a3 = new a3();
            a3.char = 4;
            a3.int = (String.valueOf(super.void.n) + "\u0000").toCharArray();
            return a3;
        }
        return ac.a(new a3());
    }
    
    public void for() {
        for (int i = 0; i < this.E; ++i) {
            this.I[i].f();
        }
    }
    
    private void do() {
        if (this.I == null || this.I.length == this.E) {
            final bm[] i = new bm[this.E + 10];
            for (int j = 0; j < this.E; ++j) {
                i[j] = this.I[j];
            }
            this.I = i;
        }
    }
    
    void if(final ab ab) {
        for (int i = this.E - 1; i >= 0; --i) {
            this.I[i].a(ab);
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
        final int e = this.E;
        this.E = 0;
        for (int i = 0; i < e; ++i) {
            this.I[i].if();
            this.I[i] = null;
        }
        this.I = null;
    }
}
