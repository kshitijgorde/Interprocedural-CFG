// 
// Decompiled by Procyon v0.5.30
// 

public class v
{
    t[] a;
    int goto;
    t i;
    float char;
    float byte;
    float g;
    boolean int;
    boolean k;
    boolean try;
    boolean for;
    String d;
    public boolean else;
    private boolean void;
    public boolean long;
    protected aa h;
    protected l j;
    protected boolean case;
    protected boolean f;
    public boolean do;
    protected boolean e;
    protected w[] c;
    protected int l;
    String if;
    String new;
    String m;
    public ak b;
    
    v(final l j, final ak b, final boolean for1) {
        this.a = null;
        this.goto = 0;
        this.char = 0.017453292f;
        this.byte = 57.29578f;
        this.g = 0.0f;
        this.int = false;
        this.k = false;
        this.try = true;
        this.d = "";
        this.else = false;
        this.long = false;
        this.h = null;
        this.j = null;
        this.case = false;
        this.f = false;
        this.do = false;
        this.e = false;
        this.c = null;
        this.l = 0;
        this.if = "";
        this.new = "";
        this.m = "";
        this.j = j;
        this.b = b;
        this.for = for1;
    }
    
    public void a(final aa h) {
        this.h = h;
        this.d = h.l;
    }
    
    boolean if(final boolean b) {
        if (!this.case && this.goto != 0) {
            this.case = true;
            for (int i = 0; i < this.goto; ++i) {
                if (this.a[i].if) {
                    this.case &= this.a[i].if(this.try);
                }
                else {
                    this.a[i].if(this.try);
                }
            }
        }
        return this.case;
    }
    
    void if(final long n) {
        this.f = true;
        for (int i = 0; i < this.goto; ++i) {
            this.a[i].do(n);
        }
    }
    
    public boolean a(final long n) {
        boolean b = false;
        final boolean do1 = this.do;
        this.do = false;
        if (this.e) {
            boolean b2 = true;
            for (int i = 0; i < this.goto; ++i) {
                b |= this.a[i].a(n);
                b2 &= this.a[i].case;
            }
            if (!this.else && b2) {
                this.else = true;
            }
            if (!b && this.void) {
                this.void = b;
                b = true;
            }
            else {
                this.void = b;
            }
        }
        else if (this.h != null && this.h.void && !this.e) {
            if (this.h.j != 0) {
                this.h = this.h.int[0];
            }
            if (!this.h.void) {
                return false;
            }
            final ae ae = new ae();
            n do2;
            try {
                q.a(ae, this.h.k, this.h.if);
                do2 = ae.do;
            }
            catch (Exception ex) {
                this.case = true;
                (this.i = new t()).a(this.j, this.char, this.h, null, this, 4);
                this.e = true;
                System.out.println("XML error in " + this.h.l + " file.");
                return false;
            }
            for (int j = 0; j < do2.do; ++j) {
                if (do2.try[j].toLowerCase().compareTo("xmlns") == 0) {
                    if (do2.new[j].toLowerCase().compareTo("http://www.immervision.com/panorama") != 0) {
                        return false;
                    }
                }
                else if (do2.try[j].toLowerCase().compareTo("angletype") == 0) {
                    if (do2.new[j].toLowerCase().compareTo("radius") == 0) {
                        this.char = 1.0f;
                        this.byte = 1.0f;
                    }
                }
                else if (do2.try[j].toLowerCase().compareTo("quickdisplay") == 0 && do2.new[j].toLowerCase().indexOf("false") != -1) {
                    this.try = false;
                }
            }
            n n2 = do2.if;
            (this.i = new t()).a(this.j, this.char, this.h, null, this, 4);
            this.do();
            this.a(this.a[this.goto] = this.i);
            ++this.goto;
            while (n2 != null) {
                if (n2.a.toLowerCase().compareTo("meta") == 0) {
                    for (n n3 = n2.if; n3 != null; n3 = n3.for) {
                        if (n3.a.toLowerCase().compareTo("gps") == 0) {
                            for (n n4 = n3.if; n4 != null; n4 = n4.for) {
                                if (n4.a.toLowerCase().compareTo("gpsimgdirection") == 0) {
                                    for (int k = 0; k < n4.do; ++k) {
                                        if (n4.try[k].toLowerCase().compareTo("gpsimgdirection") == 0) {
                                            this.g = new Float(n4.new[k]) * this.char;
                                            this.int = true;
                                        }
                                    }
                                }
                            }
                        }
                    }
                    this.a(n2);
                }
                else if (n2.a.toLowerCase().compareTo("camera") == 0) {
                    this.i.if(n2);
                }
                n2 = n2.for;
            }
            for (n n5 = do2.if; n5 != null; n5 = n5.for) {
                this.a(n5, false);
            }
            this.j.if(this.a, 0, this.goto - 1);
            final String s = "20";
            final String string = "<a><move amid=\"immerin\" fov=\"-" + s + "\"/>\n<move amid=\"immerout\" fov=\"" + s + "\"/></a>";
            final ae ae2 = new ae();
            n do3 = null;
            try {
                q.a(ae2, string.toCharArray(), string.length());
                do3 = ae2.do;
            }
            catch (Exception ex2) {}
            for (n n6 = do3.if; n6 != null; n6 = n6.for) {
                this.i.for(n6);
            }
            this.e = true;
        }
        return b | do1 | this.void;
    }
    
    public void a() {
        final int goto1 = this.goto;
        this.goto = 0;
        for (int i = 0; i < goto1; ++i) {
            this.a[i] = null;
        }
        this.a = null;
        if (this.i != null) {
            this.i.a();
            this.i = null;
        }
        this.h = null;
        this.j = null;
        for (int j = 0; j < this.l; ++j) {
            if (this.c[j] != null) {
                this.c[j].a();
            }
            this.c[j] = null;
        }
        this.l = 0;
        this.c = null;
        this.b = null;
        this.if = null;
        this.new = null;
        this.m = null;
    }
    
    private void a(final n n, final boolean try1) {
        this.do();
        int n2 = -1;
        if (n.a.toLowerCase().compareTo("panocylinder") == 0) {
            n2 = 2;
        }
        else if (n.a.toLowerCase().compareTo("panosphere") == 0) {
            n2 = 1;
        }
        else if (n.a.toLowerCase().compareTo("panocube") == 0) {
            n2 = 0;
        }
        else if (n.a.toLowerCase().compareTo("panoflat") == 0) {
            n2 = 3;
        }
        if (n2 != -1) {
            (this.a[this.goto] = new t()).a(this.j, this.char, this.h, this.i, this, n2);
            this.a[this.goto].try = try1;
            this.a[this.goto].if(n);
            this.a(this.a[this.goto]);
            ++this.goto;
        }
    }
    
    private void do() {
        if (this.a == null || this.a.length == this.goto) {
            final t[] a = new t[this.goto + 10];
            for (int i = 0; i < this.goto; ++i) {
                a[i] = this.a[i];
            }
            this.a = a;
        }
    }
    
    void for() {
        if (this.void) {
            this.long = false;
            if (this.b.g == 2) {
                this.a(true);
            }
            else {
                this.a(false);
            }
        }
        else if (!this.long) {
            this.long = true;
            if (this.b.g == 0) {
                this.a(false);
            }
            else {
                this.a(true);
            }
        }
        else if (this.b.g == 0) {
            this.a(false);
        }
        else {
            this.a(true);
        }
    }
    
    void a(final boolean b) {
        if (this.f && this.i != null) {
            this.i.a(false);
            for (int i = 0; i < this.goto; ++i) {
                this.a[i].a(b);
            }
        }
    }
    
    void a(final ah ah) {
        this.i.a(ah);
        if (ah.goto >= 0 && ah.goto < this.i.a1 && ah.else >= 0 && ah.else < this.i.aS) {
            for (int i = this.goto - 1; i >= 0; --i) {
                this.a[i].a(ah);
            }
            ah.h = true;
        }
        else {
            final boolean h = ah.h;
            ah.h = true;
            for (int j = this.goto - 1; j >= 0; --j) {
                this.a[j].a(ah);
            }
            ah.h = h;
        }
    }
    
    protected void a(final n n) {
        for (n n2 = n.if; n2 != null; n2 = n2.for) {
            if (n2.a.toLowerCase().compareTo("copyright") == 0 && n2.case != null) {
                this.if = n2.case.do;
            }
            else if (n2.a.toLowerCase().compareTo("author") == 0 && n2.case != null) {
                this.new = n2.case.do;
            }
            else if ((n2.a.toLowerCase().compareTo("licence") == 0 || n2.a.toLowerCase().compareTo("license") == 0) && n2.case != null) {
                this.m = n2.case.do;
            }
        }
    }
    
    void a(final w w) {
        if (this.c == null || this.c.length == this.l) {
            final w[] c = new w[this.l + 10];
            for (int i = 0; i < this.l; ++i) {
                c[i] = this.c[i];
            }
            this.c = c;
        }
        this.c[this.l] = w;
        ++this.l;
    }
    
    public void if() {
        for (int i = 0; i < this.goto; ++i) {
            for (int j = 0; j < this.a[i].aP; ++j) {
                ((aj)this.a[i].aH[j]).bP = !((aj)this.a[i].aH[j]).bP;
            }
        }
        this.do = true;
    }
    
    public void int() {
        for (int i = 0; i < this.goto; ++i) {
            for (int j = 0; j < this.a[i].bD; ++j) {
                this.a[i].ax[j].if = !this.a[i].ax[j].if;
            }
        }
        this.do = true;
    }
}
