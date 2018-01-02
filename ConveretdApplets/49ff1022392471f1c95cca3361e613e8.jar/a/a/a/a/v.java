// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class v extends bl
{
    bm[] B;
    int x;
    float z;
    public aw C;
    o w;
    t A;
    ad y;
    
    v(final ac q, final o w, final l h) {
        this.B = null;
        this.x = 0;
        this.z = 0.017453292f;
        this.C = null;
        this.w = null;
        this.A = null;
        super.q = q;
        this.w = w;
        super.h = h;
        this.y = super.q.else;
        this.A = new t();
    }
    
    public void a(final ae n) {
        super.n = n;
    }
    
    void if(final long n) {
        super.l = true;
        this.a(bl.byte);
        for (int i = 0; i < this.x; ++i) {
            this.B[i].int(n);
        }
    }
    
    boolean int() {
        super.goto = true;
        for (int i = 0; i < this.x; ++i) {
            if (!this.B[i].try) {
                super.goto = false;
            }
        }
        return super.goto;
    }
    
    public boolean a(final long n) {
        if (super.m) {
            if (super.l) {
                this.a(bl.p);
            }
            boolean do1 = super.do;
            super.do = false;
            for (int i = 0; i < this.x; ++i) {
                do1 |= this.B[i].a(n);
            }
            return do1;
        }
        if (super.n != null && super.n.b && !super.m) {
            if (super.n.j != 0) {
                super.n = super.n.new[0];
            }
            if (!super.n.b) {
                return false;
            }
            if (super.n.if == 0) {
                super.n = null;
                System.out.println("Loading default GUI.");
                this.a(super.q.w.a("immergui.xml", null, false, false, false));
                return false;
            }
            final bf bf = new bf();
            try {
                a.a.a.a.y.a(bf, super.n.k);
                super.d = bf.do;
            }
            catch (Exception ex) {
                System.out.println("XML error in " + super.n.m + " file.");
                super.n = null;
                System.out.println("Loading default GUI.");
                this.a(super.q.w.a("immergui.xml", null, false, false, false));
                return false;
            }
            for (int j = 0; j < super.d.do; ++j) {
                if (super.d.try[j].toLowerCase().compareTo("xmlns") == 0) {
                    if (super.d.new[j].toLowerCase().compareTo("http://www.immervision.com/interface") != 0) {
                        return false;
                    }
                }
                else if (super.d.try[j].toLowerCase().compareTo("angletype") == 0 && super.d.new[j].toLowerCase().compareTo("radius") == 0) {
                    this.z = 1.0f;
                }
            }
            for (a2 a2 = super.d.if; a2 != null; a2 = a2.for) {
                if (a2.a.toLowerCase().compareTo("meta") == 0) {
                    this.a(a2);
                }
                else if (a2.a.toLowerCase().compareTo("viewer") == 0) {
                    this.do();
                    this.B[this.x] = new aw(super.q);
                    ((aw)this.B[this.x]).do(a2, super.n, this);
                    this.C = (aw)this.B[this.x];
                    this.a(this.B[this.x]);
                    ++this.x;
                }
                else if (a2.a.toLowerCase().compareTo("progressbar") == 0) {
                    this.do();
                    this.B[this.x] = this.w;
                    ((o)this.B[this.x]).a(a2, super.n, this);
                    this.a(this.B[this.x]);
                    ++this.x;
                }
                else if (a2.a.toLowerCase().compareTo("cursors") == 0 && ac.ab >= 2) {
                    this.do();
                    this.B[this.x] = this.A;
                    ((t)this.B[this.x]).a(super.q, a2, super.n, this);
                    this.a(this.B[this.x]);
                    ++this.x;
                }
                else if (a2.a.toLowerCase().compareTo("script") == 0) {
                    try {
                        final j k = new j();
                        super.case = new ax(this);
                        k.a((String.valueOf(a2.int.if) + "\u0000").toCharArray());
                        super.case.a(k);
                        super.case.a();
                    }
                    catch (ar ar) {
                        ar.a(super.case.for());
                        super.case = null;
                    }
                }
                else {
                    this.a(a2, false);
                }
            }
            this.if();
            super.m = true;
            super.q.if(this.B, 0, this.x - 1);
        }
        return true;
    }
    
    private void a(final a2 a2, final boolean b) {
        if (a2.a.toLowerCase().compareTo("image") == 0) {
            this.do();
            try {
                this.B[this.x] = (bm)Class.forName("a.a.a.a.aa").getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null);
                this.B[this.x].else = b;
                this.B[this.x].a(super.q, a2, super.n, this);
                this.a(this.B[this.x]);
                ++this.x;
            }
            catch (Exception ex) {}
        }
        else if (a2.a.toLowerCase().compareTo("button") == 0) {
            this.do();
            try {
                this.B[this.x] = (bm)Class.forName("a.a.a.a.k").getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null);
                this.B[this.x].else = b;
                this.B[this.x].a(super.q, a2, super.n, this);
                this.a(this.B[this.x]);
                ++this.x;
            }
            catch (Exception ex2) {}
        }
        else if (a2.a.toLowerCase().compareTo("checkbox") == 0) {
            this.do();
            try {
                this.B[this.x] = (bm)Class.forName("a.a.a.a.bp").getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null);
                this.B[this.x].else = b;
                this.B[this.x].a(super.q, a2, super.n, this);
                this.a(this.B[this.x]);
                ++this.x;
            }
            catch (Exception ex3) {}
        }
        else if (a2.a.toLowerCase().compareTo("activearea") == 0) {
            this.do();
            try {
                this.B[this.x] = (bm)Class.forName("a.a.a.a.x").getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null);
                this.B[this.x].else = b;
                this.B[this.x].a(super.q, a2, super.n, this);
                this.a(this.B[this.x]);
                ++this.x;
            }
            catch (Exception ex4) {}
        }
        else if (a2.a.toLowerCase().compareTo("text") == 0) {
            this.do();
            try {
                this.B[this.x] = (bm)Class.forName("a.a.a.a.a1").getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null);
                this.B[this.x].else = b;
                this.B[this.x].a(super.q, a2, super.n, this);
                this.a(this.B[this.x]);
                ++this.x;
            }
            catch (Exception ex5) {}
        }
        else if (a2.a.toLowerCase().compareTo("compass") == 0) {
            this.do();
            try {
                this.B[this.x] = (bm)Class.forName("a.a.a.a.ba").getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null);
                this.B[this.x].else = b;
                this.B[this.x].aG = this.z;
                this.B[this.x].a(super.q, a2, super.n, this);
                this.a(this.B[this.x]);
                ++this.x;
            }
            catch (Exception ex6) {}
        }
        else if (a2.a.toLowerCase().compareTo("sound") == 0) {
            this.do();
            try {
                this.B[this.x] = (bm)Class.forName("a.a.a.a.u").getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null);
                this.B[this.x].else = b;
                this.B[this.x].a(super.q, a2, super.n, this);
                this.a(this.B[this.x]);
                ++this.x;
            }
            catch (Exception ex7) {}
        }
        if (a2.a.toLowerCase().compareTo("rectangle") == 0) {
            this.do();
            try {
                this.B[this.x] = (bm)Class.forName("a.a.a.a.bn").getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null);
                this.B[this.x].else = b;
                ((bn)this.B[this.x]).a(super.q, a2, super.n, this);
                this.a(this.B[this.x]);
                ++this.x;
            }
            catch (Exception ex8) {}
        }
    }
    
    protected void if(final char[] array, final a3 a3) {
        if (a.a.a.a.g.if(array, ac.y) == 0) {
            final bf bf = new bf();
            try {
                a.a.a.a.y.a(bf, a3.int);
                this.a(bf.do, true);
                super.q.if(this.B, 0, this.x - 1);
                super.do = true;
            }
            catch (Exception ex) {}
        }
        else if (a.a.a.a.g.if(array, ac.ak) == 0) {
            this.x = super.q.a(this.B, a3.int, this.x);
            super.r = super.q.a(super.i, a3.int, super.r);
            super.do = true;
        }
    }
    
    protected a3 do(final char[] array, final char[] array2) {
        if (a.a.a.a.g.if(array2, ac.o) == 0) {
            return ac.a(this.B, this.x);
        }
        if (a.a.a.a.g.if(array2, ac.Q) == 0) {
            final a3 a3 = new a3();
            a3.char = 4;
            a3.int = (String.valueOf(super.n.m) + "\u0000").toCharArray();
            return a3;
        }
        return ac.a(new a3());
    }
    
    public void for() {
        for (int i = 0; i < this.x; ++i) {
            this.B[i].e();
        }
    }
    
    private void do() {
        if (this.B == null || this.B.length == this.x) {
            final bm[] b = new bm[this.x + 10];
            for (int i = 0; i < this.x; ++i) {
                b[i] = this.B[i];
            }
            this.B = b;
        }
    }
    
    void if(final ab ab) {
        for (int i = this.x - 1; i >= 0; --i) {
            this.B[i].a(ab);
        }
        ab.i = false;
    }
    
    void a(final ab ab) {
    }
    
    public a3 a(final char[] array) {
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
    
    public void a() {
        super.a();
        final int x = this.x;
        this.x = 0;
        for (int i = 0; i < x; ++i) {
            this.B[i].if();
            this.B[i] = null;
        }
        this.B = null;
    }
}
