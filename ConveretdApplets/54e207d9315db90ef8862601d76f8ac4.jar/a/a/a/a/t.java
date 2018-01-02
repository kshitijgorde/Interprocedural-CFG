// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class t extends b3
{
    a2[] aa;
    int U;
    h W;
    float V;
    float Y;
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
    
    t(final an j, final r i, final boolean r) {
        this.aa = null;
        this.U = 0;
        this.V = 0.017453292f;
        this.Y = 57.29578f;
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
    
    public void if(final aq void1) {
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
        this.if(b3.t);
    }
    
    public boolean do(final long n) {
        boolean long1 = super.long;
        super.long = false;
        if (super.k) {
            if (super.z) {
                this.if(b3.l);
            }
            boolean b = true;
            for (int i = 0; i < this.U; ++i) {
                long1 |= this.aa[i].a(n);
                b &= this.aa[i].else;
            }
            if (!this.Z && b) {
                this.Z = true;
                super.i.byte(this.M);
            }
        }
        else if (super.void != null && super.void.b && !super.k) {
            if (super.void.k != 0) {
                super.void = super.void.try[0];
            }
            if (!super.void.b) {
                return false;
            }
            final bw bw = new bw();
            try {
                aj.a(bw, super.void.l, super.void.do);
                super.d = bw.do;
            }
            catch (Exception ex) {
                this.X = true;
                super.z = true;
                this.W = new h(this, super.j, 0.0f);
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
                        this.V = 1.0f;
                        this.Y = 1.0f;
                    }
                }
                else if (super.d.try[j].toLowerCase().compareTo("quickdisplay") == 0 && super.d.new[j].toLowerCase().indexOf("false") != -1) {
                    this.Q = false;
                }
            }
            this.if(super.d);
            bh bh = super.d.if;
            this.W = new h(this, super.j, this.V);
            this.byte();
            this.a(this.aa[this.U] = this.W);
            ++this.U;
            while (bh != null) {
                if (bh.a.toLowerCase().compareTo("meta") == 0) {
                    for (bh bh2 = bh.if; bh2 != null; bh2 = bh2.for) {
                        if (bh2.a.toLowerCase().compareTo("gps") == 0) {
                            bh b2 = bh2.if;
                            super.b = b2;
                            while (b2 != null) {
                                if (b2.a.toLowerCase().compareTo("gpsimgdirection") == 0) {
                                    for (int k = 0; k < b2.do; ++k) {
                                        if (b2.try[k].toLowerCase().compareTo("gpsimgdirection") == 0) {
                                            this.T = new Float(b2.new[k]) * this.V;
                                            this.N = true;
                                        }
                                    }
                                }
                                b2 = b2.for;
                            }
                        }
                    }
                    this.a(bh);
                }
                else if (bh.a.toLowerCase().compareTo("camera") == 0) {
                    this.W.int(bh);
                }
                bh = bh.for;
            }
            for (bh bh3 = super.d.if; bh3 != null; bh3 = bh3.for) {
                if (bh3.a.toLowerCase().compareTo("script") == 0) {
                    try {
                        final p p = new p();
                        super.x = new bb(this);
                        p.a(bh3.int.do.concat("\u0000").toCharArray());
                        super.c = bh3.int.a;
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
                    this.if(bh3, false);
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
    
    private void if(final bh bh, final boolean b) {
        this.byte();
        try {
            Class<?> clazz = null;
            if (bh.a.toLowerCase().compareTo("panocylinder") == 0) {
                clazz = Class.forName("a.a.a.a.d");
            }
            else if (bh.a.toLowerCase().compareTo("panosphere") == 0) {
                clazz = Class.forName("a.a.a.a.a1");
            }
            else if (bh.a.toLowerCase().compareTo("panocube") == 0) {
                clazz = Class.forName("a.a.a.a.b0");
            }
            else if (bh.a.toLowerCase().compareTo("panoflat") == 0) {
                clazz = Class.forName("a.a.a.a.af");
            }
            else if (bh.a.toLowerCase().compareTo("sfx") == 0) {
                boolean b2 = false;
                String s = "";
                for (int i = 0; i < bh.do; ++i) {
                    if (bh.try[i].toLowerCase().compareTo("name") == 0) {
                        s = bh.new[i];
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
                    (this.aa[this.U] = (a2)Class.forName(s2).getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null)).a(super.j, this.V, super.void, this.W, this);
                    this.aa[this.U].goto = b;
                    this.aa[this.U].int(bh);
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
                (this.aa[this.U] = (a2)clazz.getConstructor((Class<?>[])new Class[0]).newInstance((Object[])null)).a(super.j, this.V, super.void, this.W, this);
                this.aa[this.U].goto = b;
                this.aa[this.U].int(bh);
                this.a(this.aa[this.U]);
                ++this.U;
            }
        }
        catch (Exception ex2) {
            System.out.println("Can't load missing functionnality :" + bh.a);
        }
    }
    
    protected void a(final char[] array, final bi bi) {
        if (a.a.a.a.i.do(array, an.D) == 0) {
            final bw bw = new bw();
            try {
                aj.a(bw, bi.int, a.a.a.a.i.a(bi.int));
                this.if(bw.do, true);
                super.j.if(this.aa, 0, this.U - 1);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            super.long = true;
        }
        else if (a.a.a.a.i.do(array, an.at) == 0) {
            this.U = super.j.a(this.aa, bi.int, this.U);
            super.p = super.j.a(super.else, bi.int, super.p);
            super.long = true;
        }
    }
    
    protected bi a(final char[] array, final char[] array2) {
        if (a.a.a.a.i.do(array2, an.t) == 0) {
            return an.a(this.aa, this.U);
        }
        if (a.a.a.a.i.do(array2, an.else) == 0) {
            final bi bi = new bi();
            bi.char = 4;
            if (this.V == 1.0f) {
                bi.int = an.af;
            }
            else {
                bi.int = an.w;
            }
            return bi;
        }
        if (a.a.a.a.i.do(array2, an.X) == 0) {
            final bi bi2 = new bi();
            bi2.char = 4;
            bi2.int = (String.valueOf(super.void.n) + "\u0000").toCharArray();
            return bi2;
        }
        return an.a(new bi());
    }
    
    private void byte() {
        if (this.aa == null || this.aa.length == this.U) {
            final a2[] aa = new a2[this.U + 10];
            for (int i = 0; i < this.U; ++i) {
                aa[i] = this.aa[i];
            }
            this.aa = aa;
        }
    }
    
    public ap try() {
        if (!super.h) {
            return null;
        }
        final ap case1 = this.W.case(false);
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
    
    void do(final am am) {
        this.W.if(am);
        if (am.goto >= 0 && am.goto < this.W.eM && am.else >= 0 && am.else < this.W.eR) {
            for (int i = this.U - 1; i >= 0; --i) {
                this.aa[i].if(am);
            }
            am.i = true;
        }
        else {
            final boolean j = am.i;
            am.i = true;
            for (int k = this.U - 1; k >= 0; --k) {
                this.aa[k].if(am);
            }
            am.i = j;
        }
    }
    
    public bi if(final char[] array) {
        if (super.x != null) {
            return super.x.a(array);
        }
        return new bi();
    }
}
