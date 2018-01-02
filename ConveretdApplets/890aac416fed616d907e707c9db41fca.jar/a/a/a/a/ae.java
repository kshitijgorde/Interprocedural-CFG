// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class ae extends b3
{
    b5[] K;
    int F;
    float G;
    public ba H;
    v I;
    ab D;
    ap J;
    public long L;
    private bh E;
    
    ae(final an j, final v i, final r k) {
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
        this.D = new ab();
        super.B = "GUI";
    }
    
    public void a(final aq void1) {
        super.void = void1;
    }
    
    void if(final long n) {
        this.int();
        super.h = true;
        this.a(b3.t);
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
        ((v)this.K[this.F]).a(this.E, super.void, this);
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
                this.a(b3.l);
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
            final bw bw = new bw();
            try {
                aj.a(bw, super.void.l, super.void.do);
                super.d = bw.do;
            }
            catch (Exception ex) {
                System.out.println("XML error in " + super.void.n + " file.");
                super.void = null;
                System.out.println("Loading default GUI.");
                this.a(super.j.B.a(new String(an.Y), null, false, false, false));
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
            for (bh e = super.d.if; e != null; e = e.for) {
                if (e.a.toLowerCase().compareTo("meta") == 0) {
                    this.a(e);
                }
                else if (e.a.toLowerCase().compareTo("viewer") == 0) {
                    this.do();
                    this.K[this.F] = new ba(super.j);
                    ((ba)this.K[this.F]).if(e, super.void, this);
                    this.H = (ba)this.K[this.F];
                    this.a(this.K[this.F]);
                    ++this.F;
                }
                else if (e.a.toLowerCase().compareTo("progressbar") == 0) {
                    this.E = e;
                }
                else if (e.a.toLowerCase().compareTo("cursors") == 0 && an.aj >= 2) {
                    this.do();
                    this.K[this.F] = this.D;
                    ((ab)this.K[this.F]).a(super.j, e, super.void, this);
                    this.a(this.K[this.F]);
                    ++this.F;
                }
                else if (e.a.toLowerCase().compareTo("script") == 0) {
                    try {
                        final p p = new p();
                        super.x = new bb(this);
                        p.a((String.valueOf(e.int.do) + "\u0000").toCharArray());
                        super.x.a(p);
                        super.x.a();
                    }
                    catch (a5 a5) {
                        a5.a();
                        System.out.print(super.c + super.x.do().try());
                        a5.a(super.x.do());
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
    
    private void a(final bh bh, final boolean goto1) {
        try {
            Class<?> clazz = null;
            if (bh.a.toLowerCase().compareTo("image") == 0) {
                clazz = Class.forName("a.a.a.a.al");
            }
            else if (bh.a.toLowerCase().compareTo("button") == 0) {
                clazz = Class.forName("a.a.a.a.q");
            }
            else if (bh.a.toLowerCase().compareTo("checkbox") == 0) {
                clazz = Class.forName("a.a.a.a.b8");
            }
            else if (bh.a.toLowerCase().compareTo("activearea") == 0) {
                clazz = Class.forName("a.a.a.a.ai");
            }
            else if (bh.a.toLowerCase().compareTo("text") == 0) {
                clazz = Class.forName("a.a.a.a.bg");
            }
            else if (bh.a.toLowerCase().compareTo("compass") == 0) {
                clazz = Class.forName("a.a.a.a.bq");
            }
            else if (bh.a.toLowerCase().compareTo("sound") == 0) {
                clazz = Class.forName("a.a.a.a.ac");
            }
            else if (bh.a.toLowerCase().compareTo("rectangle") == 0) {
                clazz = Class.forName("a.a.a.a.b6");
            }
            if (clazz != null) {
                this.do();
                this.K[this.F] = (b5)clazz.getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null);
                this.K[this.F].goto = goto1;
                this.K[this.F].a(super.j, bh, super.void, this);
                this.a(this.K[this.F]);
                ++this.F;
            }
        }
        catch (Exception ex) {
            System.out.println("Can't load missing functionnality :" + bh.a);
        }
    }
    
    protected void if(final char[] array, final bi bi) {
        if (a.a.a.a.i.do(array, an.D) == 0) {
            final bw bw = new bw();
            try {
                aj.a(bw, bi.int, a.a.a.a.i.a(bi.int));
                this.a(bw.do, true);
                super.j.if(this.K, 0, this.F - 1);
                super.long = true;
            }
            catch (Exception ex) {}
        }
        else if (a.a.a.a.i.do(array, an.at) == 0) {
            this.F = super.j.a(this.K, bi.int, this.F);
            super.p = super.j.a(super.else, bi.int, super.p);
            super.long = true;
        }
    }
    
    protected bi do(final char[] array, final char[] array2) {
        if (a.a.a.a.i.do(array2, an.t) == 0) {
            return an.a(this.K, this.F);
        }
        if (a.a.a.a.i.do(array2, an.X) == 0) {
            final bi bi = new bi();
            bi.char = 4;
            bi.int = (String.valueOf(super.void.n) + "\u0000").toCharArray();
            return bi;
        }
        return an.a(new bi());
    }
    
    public void for() {
        for (int i = 0; i < this.F; ++i) {
            this.K[i].c();
        }
    }
    
    private void do() {
        if (this.K == null || this.K.length == this.F) {
            final b5[] k = new b5[this.F + 10];
            for (int i = 0; i < this.F; ++i) {
                k[i] = this.K[i];
            }
            this.K = k;
        }
    }
    
    void if(final am am) {
        for (int i = this.F - 1; i >= 0; --i) {
            this.K[i].a(am);
        }
        am.i = false;
    }
    
    void a(final am am) {
    }
    
    public bi a(final char[] array) {
        if (super.x != null) {
            return super.x.a(array);
        }
        return new bi();
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
