// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class p extends e
{
    boolean cw;
    protected char[] cx;
    protected char[] cy;
    protected char[] cq;
    protected char[] cs;
    protected char[] cu;
    protected char[] cz;
    protected boolean ct;
    protected boolean co;
    protected boolean cv;
    ad cp;
    int cr;
    
    public p() {
        this.cw = false;
        this.cx = new char[1];
        this.cy = new char[1];
        this.cq = new char[1];
        this.cs = new char[1];
        this.cu = new char[1];
        this.cz = new char[1];
        this.ct = false;
        this.co = false;
        this.cv = false;
        this.cp = null;
        this.cr = -1;
        super.try = true;
    }
    
    void try(final a2 a2) {
        for (a2 a3 = a2.if; a3 != null; a3 = a3.for) {
            final String lowerCase = a3.a.toLowerCase();
            if (a3.case.if.trim().length() != 0) {
                final String if1 = a3.case.if;
                if (lowerCase.compareTo("mouseclick") == 0) {
                    this.cy = if1.toCharArray();
                }
                else if (lowerCase.compareTo("mousepress") == 0) {
                    this.cx = if1.toCharArray();
                }
                else if (lowerCase.compareTo("mouseenter") == 0) {
                    this.cq = if1.toCharArray();
                }
                else if (lowerCase.compareTo("mouseleave") == 0) {
                    this.cs = if1.toCharArray();
                }
                else if (lowerCase.compareTo("mouserelease") == 0) {
                    this.cu = if1.toCharArray();
                }
                else if (lowerCase.compareTo("text") == 0) {
                    this.cz = if1.toCharArray();
                }
            }
        }
    }
    
    void a(final ab ab, final boolean co) {
        this.co = co;
        if (this.co && !ab.i && ab.if != 4) {
            ab.for = 0;
        }
        if (this.ct && ab.if == 1) {
            ab.a(this.cu, true);
            super.ck.a(super.e, 3);
            ab.i = true;
            this.ct = false;
        }
        else if (!ab.i && this.co) {
            if (ab.if == 2) {
                ab.a(this.cy, true);
                super.ck.a(super.e, 4);
                ab.i = true;
            }
            else if (ab.if == 0) {
                ab.a(this.cx, true);
                super.ck.a(super.e, 2);
                ab.i = true;
                this.ct = true;
            }
            else if (!this.cv) {
                ab.a(this.cq, true);
                super.ck.a(super.e, 0);
                ab.i = true;
                this.cv = true;
            }
            else if (this.cv) {
                ab.i = true;
            }
        }
        else if (this.cv) {
            ab.a(this.cs, true);
            super.ck.a(super.e, 1);
            ab.i = true;
            this.cv = false;
        }
    }
    
    void a(final float[][] array) {
    }
    
    public void int(final a2 a2) {
    }
    
    protected void new(final a2 a2) {
    }
    
    public void a(final char[] array, final a3 a3) {
        if (g.if(array, ac.g) == 0) {
            this.cw = al.a(a3);
            super.if = true;
        }
        else if (g.if(array, ac.K) == 0) {
            super.do = al.a(a3);
            super.if = true;
        }
        else if (g.if(array, ac.t) == 0) {
            if (a3.char == 4) {
                this.cx = a3.int;
            }
        }
        else if (g.if(array, ac.void) == 0) {
            if (a3.char == 4) {
                this.cy = a3.int;
            }
        }
        else if (g.if(array, ac.goto) == 0) {
            if (a3.char == 4) {
                this.cq = a3.int;
            }
        }
        else if (g.if(array, ac.D) == 0) {
            if (a3.char == 4) {
                this.cs = a3.int;
            }
        }
        else if (g.if(array, ac.z) == 0) {
            if (a3.char == 4) {
                this.cu = a3.int;
            }
        }
        else if (g.if(array, ac.k) == 0) {
            if (a3.char == 4) {
                this.cz = a3.int;
            }
        }
        else if (g.if(array, ac.aa) == 0 && a3.char == 2) {
            this.cr = (int)a3.case;
        }
    }
    
    public a3 a(final char[] array) {
        if (g.if(array, ac.g) == 0) {
            super.int.char = 1;
            super.int.long = this.cw;
            return super.int;
        }
        if (g.if(array, ac.K) == 0) {
            super.int.char = 1;
            super.int.long = super.do;
            return super.int;
        }
        if (g.if(array, ac.t) == 0) {
            super.int.char = 4;
            super.int.int = this.cx;
            return super.int;
        }
        if (g.if(array, ac.void) == 0) {
            super.int.char = 4;
            super.int.int = this.cy;
            return super.int;
        }
        if (g.if(array, ac.goto) == 0) {
            super.int.char = 4;
            super.int.int = this.cq;
            return super.int;
        }
        if (g.if(array, ac.D) == 0) {
            super.int.char = 4;
            super.int.int = this.cs;
            return super.int;
        }
        if (g.if(array, ac.z) == 0) {
            super.int.char = 4;
            super.int.int = this.cu;
            return super.int;
        }
        if (g.if(array, ac.k) == 0) {
            super.int.char = 4;
            super.int.int = this.cz;
            return super.int;
        }
        if (g.if(array, ac.o) == 0) {
            super.int.char = 5;
            if (this.cp != null) {
                super.int.goto = 1;
                (super.int.a = new a3[1])[0] = new a3();
                super.int.a[0].char = 4;
                super.int.a[0].int = this.cp.e;
            }
            else {
                super.int.goto = 0;
            }
            return super.int;
        }
        if (g.if(array, ac.aa) == 0) {
            super.int.char = 2;
            super.int.case = this.cr;
            return super.int;
        }
        return ac.a(super.int);
    }
}
