// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class as extends e
{
    boolean cU;
    boolean cO;
    boolean cR;
    boolean cX;
    float cS;
    ai cW;
    aq cT;
    aq cV;
    aq cN;
    float[][] cQ;
    static final char[] cP;
    
    static {
        cP = new char[] { 'v', 'o', 'l', 'u', 'm', 'e', '\0' };
    }
    
    public as() {
        this.cU = true;
        this.cO = true;
        this.cR = false;
        this.cX = false;
        this.cS = 1.0f;
        this.cW = null;
    }
    
    public void a(final ac void1, final float cj, final ae goto1, final f cl, final m m) {
        super.void = void1;
        super.cj = cj;
        super.cm = 1.0f / cj;
        super.goto = goto1;
        super.cl = cl;
        super.for = 4;
        super.byte = "sound\u0000".toCharArray();
    }
    
    public void int(final a2 a2) {
        for (int i = 0; i < a2.do; ++i) {
            if (a2.try[i].toLowerCase().compareTo("loop") == 0) {
                if (a2.new[i].toLowerCase().compareTo("false") == 0) {
                    this.cU = false;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("play") == 0) {
                if (a2.new[i].toLowerCase().compareTo("false") == 0) {
                    this.cO = false;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("pan") == 0) {
                super.cn = new Float(a2.new[i]) * super.cj;
                this.cX = true;
            }
            else if (a2.try[i].toLowerCase().compareTo("tilt") == 0) {
                super.cg = new Float(a2.new[i]) * super.cj;
                this.cX = true;
            }
            else if (a2.try[i].toLowerCase().compareTo("volume") == 0) {
                this.cS = new Float(a2.new[i]) / 100.0f;
                if (this.cS < 0.0f) {
                    this.cS = 0.0f;
                }
                else if (this.cS > 1.0f) {
                    this.cS = 1.0f;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("file") == 0) {
                this.cW = super.void.n.a(a2.new[i], super.goto, super.void, super.else, super.new);
            }
            else if (a2.try[i].toLowerCase().compareTo("id") == 0) {
                super.e = (String.valueOf(a2.new[i]) + "\u0000").toCharArray();
            }
        }
        if (this.cW != null) {
            this.cW.ao = this.cO;
            this.cW.al = this.cU;
            this.cW.a(this.cS);
        }
        this.a();
        this.cT = new aq();
        this.cV = new aq();
        this.cN = new aq();
        this.cQ = super.void.a();
        this.cT.byte = (float)Math.sin(super.cg);
        this.cT.try = (float)(Math.cos(super.cn) * Math.cos(super.cg));
        this.cT.if = (float)(Math.sin(super.cn) * Math.cos(super.cg));
        this.cN.byte = this.cT.byte;
        this.cN.try = this.cT.try;
        this.cN.if = this.cT.if;
        this.cR = true;
    }
    
    public void if() {
        if (this.cW != null) {
            this.cW.if();
        }
    }
    
    public boolean a(final long n) {
        if (this.cW != null) {
            this.cW.else = super.else;
            this.cW.a(n);
        }
        return false;
    }
    
    void new(final long n) {
        super.else = true;
        if (this.cW != null) {
            this.cW.do(n);
        }
    }
    
    void a(final float[][] array) {
        super.void.a(array, this.cN, this.cT);
    }
    
    void try(final boolean b) {
        this.cW.int(b);
    }
    
    public void new(final boolean b) {
        if (this.cX && this.cR) {
            super.void.a(-super.cl.cn, super.cl.cg, 0.0f, this.cQ);
            super.void.a(this.cQ, this.cT, this.cV);
            this.cW.a((float)Math.atan2(this.cV.if, this.cV.try), (float)Math.asin(this.cV.byte));
        }
    }
    
    public void a(final char[] array, final a3 a3) {
        if (g.if(array, ac.G) == 0) {
            super.cn = al.do(a3);
            this.cT.byte = (float)Math.sin(super.cg);
            this.cT.try = (float)(Math.cos(super.cn) * Math.cos(super.cg));
            this.cT.if = (float)(Math.sin(super.cn) * Math.cos(super.cg));
            this.cN.byte = this.cT.byte;
            this.cN.try = this.cT.try;
            this.cN.if = this.cT.if;
        }
        else if (g.if(array, ac.try) == 0) {
            super.cg = al.do(a3);
            this.cT.byte = (float)Math.sin(super.cg);
            this.cT.try = (float)(Math.cos(super.cn) * Math.cos(super.cg));
            this.cT.if = (float)(Math.sin(super.cn) * Math.cos(super.cg));
            this.cN.byte = this.cT.byte;
            this.cN.try = this.cT.try;
            this.cN.if = this.cT.if;
        }
        else if (g.if(array, ac.W) == 0) {
            this.cO = al.a(a3);
            if (this.cO) {
                this.cW.void();
            }
            else {
                this.cW.c();
            }
        }
        else if (g.if(array, ac.i) == 0) {
            this.cO = false;
            this.cW.b();
        }
        else if (g.if(array, ac.new) == 0) {
            if (!this.cO) {
                return;
            }
            this.cO = false;
            this.cW.c();
        }
        else if (g.if(array, ac.f) == 0) {
            this.cU = al.a(a3);
            this.cW.for(this.cU);
        }
        else if (g.if(array, as.cP) == 0) {
            this.cS = al.do(a3) / 100.0f;
            if (this.cS < 0.0f) {
                this.cS = 0.0f;
            }
            else if (this.cS > 1.0f) {
                this.cS = 1.0f;
            }
            this.cW.a(this.cS);
        }
    }
    
    public a3 a(final char[] array) {
        if (g.if(array, ac.G) == 0) {
            super.int.char = 3;
            super.int.else = super.cn;
            return super.int;
        }
        if (g.if(array, ac.try) == 0) {
            super.int.char = 3;
            super.int.else = super.cg;
            return super.int;
        }
        if (g.if(array, as.cP) == 0) {
            super.int.char = 3;
            super.int.else = this.cS * 100.0f;
            return super.int;
        }
        if (g.if(array, ac.W) == 0) {
            super.int.char = 1;
            super.int.long = this.cO;
            return super.int;
        }
        if (g.if(array, ac.f) == 0) {
            super.int.char = 1;
            super.int.long = this.cU;
            return super.int;
        }
        return null;
    }
}
