// 
// Decompiled by Procyon v0.5.30
// 

public class ad extends aj
{
    al[] bX;
    float bY;
    boolean bW;
    aa bZ;
    
    public ad() {
        this.bY = 0.0f;
        this.bW = false;
        this.bZ = null;
    }
    
    public void a(final l int1, final float e, final aa byte1, final t g, final v f) {
        super.int = int1;
        super.E = e;
        super.H = 1.0f / super.E;
        super.byte = byte1;
        super.G = g;
        super.F = f;
        super.for = false;
    }
    
    public void a() {
        if (this.bX != null) {
            this.bX[0] = null;
        }
        this.bX = null;
        if (super.bH != null) {
            super.bH.a();
        }
        super.bH = null;
    }
    
    public void if(final n n) {
        float n2 = 0.0f;
        float n3 = 0.0f;
        for (int i = 0; i < n.do; ++i) {
            if (n.try[i].toLowerCase().compareTo("pan") == 0) {
                n2 = new Float(n.new[i]) * super.E;
            }
            else if (n.try[i].toLowerCase().compareTo("tilt") == 0) {
                n3 = new Float(n.new[i]) * super.E;
            }
            else if (n.try[i].toLowerCase().compareTo("visible") == 0) {
                if (n.new[i].compareTo("false") == 0) {
                    super.if = false;
                }
            }
            else if (n.try[i].toLowerCase().compareTo("size") == 0) {
                if (n.new[i].trim().endsWith("%")) {
                    this.bW = true;
                    this.bY = new Float(n.new[i].trim().replace('%', '0')) / 10.0f;
                }
                else {
                    this.bY = new Float(n.new[i]);
                }
            }
            else if (n.try[i].toLowerCase().compareTo("color") == 0) {
                super.bJ = l.a(n.new[i]);
            }
            else if (n.try[i].toLowerCase().compareTo("display") == 0) {
                if (n.new[i].compareTo("true") == 0) {
                    super.bP = true;
                }
            }
            else if (n.try[i].toLowerCase().compareTo("image") == 0) {
                super.bH = new an();
                this.bZ = super.int.n.a(n.new[i], super.byte, false, true, false);
                super.F.a(super.bH);
            }
        }
        (this.bX = new al[1])[0] = new al();
        this.bX[0].try = (float)(Math.cos(n2) * Math.cos(n3));
        this.bX[0].if = (float)(Math.sin(n2) * Math.cos(n3));
        this.bX[0].byte = (float)Math.sin(n3);
        this.new(n);
    }
    
    void a(final float[][] array) {
        super.int.a(array, this.bX[0], this.bX[0]);
    }
    
    void a(final ah ah) {
        if (!super.if) {
            return;
        }
        d.a(this.bX, 1, super.G, super.G.a1 >> 1, super.G.aS >> 1);
        if (!this.bX[0].new) {
            this.a(ah, false);
            return;
        }
        int n;
        if (this.bW) {
            n = (int)(Math.sqrt(super.G.a1 * super.G.a1 + super.G.aS * super.G.aS) * this.bY * 0.005);
        }
        else {
            n = (int)(this.bY * 0.5);
        }
        if (this.bX[0].int + n > ah.goto && this.bX[0].int - n < ah.goto && this.bX[0].for + n > ah.else && this.bX[0].for - n < ah.else) {
            this.a(ah, true);
        }
        else {
            this.a(ah, false);
        }
    }
    
    public boolean a(final long n) {
        boolean a = false;
        final boolean do1 = super.do;
        super.do = false;
        if (!super.bH.for && this.bZ.byte) {
            super.bH = this.bZ.a(super.bH);
            super.for = true;
            super.do = true;
            if (super.try) {
                super.bH.if(n);
            }
        }
        if (!super.try) {
            return false;
        }
        if (super.try && super.for) {
            a = super.bH.a(n);
        }
        return (a & super.if) | do1;
    }
    
    boolean a(final int n) {
        return super.bH == null || super.bH.for;
    }
    
    public void a(final boolean b) {
        if (super.bP && super.if) {
            d.a(this.bX, 1, super.G, super.G.a1 >> 1, super.G.aS >> 1);
            if (super.bH.for && this.bX[0].new) {
                d.a(super.bH, super.G.a5, (int)(this.bX[0].int - (super.bH.long >> 1) + super.G.a5.goto), (int)(this.bX[0].for - (super.bH.e >> 1) + super.G.a5.else), super.bH.long, super.bH.e);
            }
        }
    }
    
    void do(final long n) {
        super.try = true;
        if (super.bH != null) {
            super.bH.if(n);
        }
    }
}
