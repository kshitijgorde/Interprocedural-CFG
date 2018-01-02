// 
// Decompiled by Procyon v0.5.30
// 

public class j extends an
{
    an m;
    an l;
    aa g;
    aa j;
    boolean n;
    String i;
    boolean k;
    int h;
    
    j(final l int1, final aa byte1, final boolean a, final boolean try1) {
        this.m = null;
        this.l = null;
        this.g = null;
        this.j = null;
        this.n = false;
        this.i = null;
        this.k = false;
        this.h = 0;
        super.int = int1;
        super.byte = byte1;
        super.a = a;
        super.try = try1;
    }
    
    public void a(final n n) {
        String s = null;
        String s2 = null;
        super.e = -1;
        super.long = -1;
        for (int i = 0; i < n.do; ++i) {
            if (n.try[i].toLowerCase().compareTo("file") == 0) {
                s2 = n.new[i];
            }
            else if (n.try[i].toLowerCase().compareTo("preview") == 0) {
                s = n.new[i];
            }
            else if (n.try[i].toLowerCase().compareTo("visible") == 0 && n.new[i].compareTo("false") == 0) {
                super.if = false;
            }
        }
        if (super.long > 0 && super.e > 0) {
            super.for = true;
        }
        if (s != null) {
            this.m = new an();
            this.g = super.int.n.a(s, super.byte, false, true, true, null);
        }
        if (s2 != null) {
            this.l = new an();
            this.j = super.int.n.a(s2, super.byte, false, true, true, this.g);
            if (this.g != null) {
                this.j.else = this.g;
            }
            this.h = 2;
        }
    }
    
    void if(final long n) {
        if (this.h == 0 && this.m != null && this.m.for) {
            this.m.if(n);
        }
        else if (this.h > 0 && this.l != null && this.l.for) {
            this.l.if(n);
        }
        super.try = true;
    }
    
    public boolean a(final long n) {
        boolean a = false;
        final boolean do1 = super.do;
        super.do = false;
        if (this.j.j != 0) {
            this.j = this.j.int[0];
        }
        if (!super.for && ((this.j.for && super.a) || this.j.byte)) {
            this.l = this.j.a(this.l);
            this.l.try = super.try;
            super.long = this.l.long;
            super.e = this.l.e;
            super.c = this.l.c;
            super.b = true;
            super.for = true;
            this.n = true;
            super.do = true;
            super.f = this.l.f;
        }
        if (!super.try) {
            return false;
        }
        if (super.try && super.for) {
            a = this.l.a(n);
        }
        if (this.l != null) {
            if (!this.l.for) {
                this.l.for = this.j.byte;
                return true;
            }
            super.case = true;
        }
        return (a & super.if) | do1;
    }
    
    public void a() {
        if (this.m != null) {
            this.m.a();
            this.m = null;
        }
        if (this.l != null) {
            this.l.a();
            this.l = null;
        }
    }
}
