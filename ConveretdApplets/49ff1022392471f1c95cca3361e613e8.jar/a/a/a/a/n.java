// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class n extends p
{
    aq[] cA;
    aq[] cC;
    int cB;
    protected char[] cD;
    
    public n() {
        this.cA = null;
        this.cC = null;
        this.cB = 0;
        this.cD = new char[] { 'h', 'o', 't', 's', 'p', 'o', 't', 'P', 'o', 'l', 'y', 'g', 'o', 'n', 'a', 'l', '\0' };
    }
    
    public void a(final ac void1, final float cj, final ae goto1, final f cl, final m ck) {
        super.void = void1;
        super.cj = cj;
        super.cm = 1.0f / super.cj;
        super.goto = goto1;
        super.cl = cl;
        super.for = 2;
        super.ck = ck;
        super.int = new a3();
        super.byte = this.cD;
    }
    
    public void if() {
        for (int i = 0; i < this.cB; ++i) {
            this.cA[i] = null;
            this.cC[i] = null;
        }
        this.cA = null;
        this.cC = null;
    }
    
    public void int(final a2 a2) {
        for (int i = 0; i < a2.do; ++i) {
            if (a2.try[i].toLowerCase().compareTo("id") == 0) {
                super.e = (String.valueOf(a2.new[i]) + "\u0000").toCharArray();
            }
            else if (a2.try[i].toLowerCase().compareTo("visible") == 0) {
                if (a2.new[i].compareTo("false") == 0) {
                    super.do = false;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("color") == 0) {
                super.cr = ac.a(a2.new[i]);
                if ((super.cr & 0xFF000000) == 0x0) {
                    super.cr |= 0xFF000000;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("display") == 0 && a2.new[i].compareTo("true") == 0) {
                super.cw = true;
            }
        }
        this.a();
        for (a2 a3 = a2.if; a3 != null; a3 = a3.for) {
            if (a3.a.toLowerCase().compareTo("polygon") == 0) {
                for (a2 a4 = a3.if; a4 != null; a4 = a4.for) {
                    if (a4.a.toLowerCase().compareTo("vertex") == 0) {
                        float n = 0.0f;
                        float n2 = 0.0f;
                        for (int j = 0; j < a4.do; ++j) {
                            if (a4.try[j].toLowerCase().compareTo("pan") == 0) {
                                n = new Float(a4.new[j]) * super.cj;
                            }
                            else if (a4.try[j].toLowerCase().compareTo("tilt") == 0) {
                                n2 = new Float(a4.new[j]) * super.cj;
                            }
                        }
                        if (this.cB == 0) {
                            (this.cA = new aq[1])[0] = new aq();
                            this.cA[0].try = (float)(Math.cos(n) * Math.cos(n2));
                            this.cA[0].if = (float)(Math.sin(n) * Math.cos(n2));
                            this.cA[0].byte = (float)Math.sin(n2);
                            this.cA[0].do = n;
                            this.cA[0].a = n2;
                            this.cB = 1;
                        }
                        else {
                            int n3 = (int)(9.0 * Math.sqrt((n - this.cA[this.cB - 1].do) * (n - this.cA[this.cB - 1].do) + (n2 - this.cA[this.cB - 1].a) * (n2 - this.cA[this.cB - 1].a)) / 3.141592653589793);
                            if (n3 == 0) {
                                n3 = 1;
                            }
                            final int n4 = this.cB + n3;
                            final aq[] ca = new aq[n4];
                            for (int k = this.cB; k < n4; ++k) {
                                ca[k] = new aq();
                            }
                            for (int l = 0; l < this.cB; ++l) {
                                ca[l] = this.cA[l];
                            }
                            this.cA = ca;
                            this.cA[n4 - 1].try = (float)(Math.cos(n) * Math.cos(n2));
                            this.cA[n4 - 1].if = (float)(Math.sin(n) * Math.cos(n2));
                            this.cA[n4 - 1].byte = (float)Math.sin(n2);
                            this.cA[n4 - 1].do = n;
                            this.cA[n4 - 1].a = n2;
                            if (n3 > 1) {
                                final float n5 = (this.cA[n4 - 1].try - this.cA[this.cB - 1].try) / 9.0f;
                                final float n6 = (this.cA[n4 - 1].if - this.cA[this.cB - 1].if) / 9.0f;
                                final float n7 = (this.cA[n4 - 1].byte - this.cA[this.cB - 1].byte) / 9.0f;
                                for (int cb = this.cB; cb < n4 - 1; ++cb) {
                                    this.cA[cb].try = this.cA[cb - 1].try + n5;
                                    this.cA[cb].if = this.cA[cb - 1].if + n6;
                                    this.cA[cb].byte = this.cA[cb - 1].byte + n7;
                                }
                            }
                            this.cB += n3;
                        }
                    }
                }
            }
        }
        this.cC = new aq[this.cB];
        super.cn = 0.0f;
        super.cg = 0.0f;
        for (int n8 = 0; n8 < this.cB; ++n8) {
            this.cC[n8] = new aq();
            this.cC[n8].try = this.cA[n8].try;
            this.cC[n8].if = this.cA[n8].if;
            this.cC[n8].byte = this.cA[n8].byte;
            super.cn += this.cA[n8].do;
            super.cg += this.cA[n8].a;
        }
        super.cn /= this.cB;
        super.cg /= this.cB;
        this.try(a2);
        super.try = true;
    }
    
    void a(final float[][] array) {
        for (int i = 0; i < this.cB; ++i) {
            super.void.a(array, this.cC[i], this.cA[i]);
        }
    }
    
    void if(final ab ab) {
        if (!super.do) {
            return;
        }
        ap.a(this.cA, this.cB, super.cl, super.cl.et >> 1, super.cl.ey >> 1);
        boolean b = false;
        int n = 0;
        int n2 = this.cB - 1;
        while (n < this.cB && !this.cA[n].new) {
            ++n;
        }
        if (n == this.cB) {
            return;
        }
        while (n2 > n + 2 && !this.cA[n2].new) {
            --n2;
        }
        if (n2 < n + 2) {
            return;
        }
        int i = n;
        int n3 = n2;
        while (i < n2 + 1) {
            while (i < n2 && !this.cA[i].new) {
                ++i;
            }
            if (((this.cA[i].for <= ab.else && ab.else < this.cA[n3].for) || (this.cA[n3].for <= ab.else && ab.else < this.cA[i].for)) && ab.goto < (this.cA[n3].int - this.cA[i].int) * (ab.else - this.cA[i].for) / (this.cA[n3].for - this.cA[i].for) + this.cA[i].int) {
                b = !b;
            }
            n3 = i++;
        }
        if (b) {
            this.a(ab, true);
        }
        else {
            this.a(ab, false);
        }
    }
    
    public boolean a(final long n) {
        final boolean if1 = super.if;
        super.if = false;
        return if1;
    }
    
    public void new(final boolean b) {
        if (super.cw && super.do) {
            ap.a(this.cA, this.cB, super.cl, super.cl.et >> 1, super.cl.ey >> 1);
            final int n = this.cB - 1;
            for (int i = 0; i < n; ++i) {
                if (this.cA[i].new && this.cA[i + 1].new) {
                    ap.a(super.cl.em, (int)this.cA[i].int, (int)this.cA[i].for, (int)this.cA[i + 1].int, (int)this.cA[i + 1].for, super.cr);
                }
            }
            if (this.cA[0].new && this.cA[n].new) {
                ap.a(super.cl.em, (int)this.cA[n].int, (int)this.cA[n].for, (int)this.cA[0].int, (int)this.cA[0].for, super.cr);
            }
        }
    }
}
