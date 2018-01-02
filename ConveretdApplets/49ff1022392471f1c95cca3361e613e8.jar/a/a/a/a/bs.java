// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class bs extends p
{
    aq[] cK;
    aq[] cL;
    protected char[] cM;
    
    public bs() {
        this.cK = null;
        this.cL = null;
        this.cM = new char[] { 'h', 'o', 't', 's', 'p', 'o', 't', 'R', 'e', 'c', 't', 'a', 'n', 'g', 'l', 'e', '\0' };
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
        super.byte = this.cM;
    }
    
    public void if() {
        for (int i = 0; i < 16; ++i) {
            this.cK[i] = null;
            this.cL[i] = null;
        }
        this.cK = null;
        this.cL = null;
    }
    
    public void int(final a2 a2) {
        float n = 0.0f;
        float n2 = 0.0f;
        float n3 = 0.0f;
        float n4 = 0.0f;
        for (int i = 0; i < a2.do; ++i) {
            if (a2.try[i].toLowerCase().compareTo("id") == 0) {
                super.e = (String.valueOf(a2.new[i]) + "\u0000").toCharArray();
            }
            else if (a2.try[i].toLowerCase().compareTo("pan") == 0) {
                n = new Float(a2.new[i]) * super.cj;
            }
            else if (a2.try[i].toLowerCase().compareTo("tilt") == 0) {
                n2 = new Float(a2.new[i]) * super.cj;
            }
            else if (a2.try[i].toLowerCase().compareTo("visible") == 0) {
                if (a2.new[i].compareTo("false") == 0) {
                    super.do = false;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("pansize") == 0) {
                n3 = new Float(a2.new[i]) * super.cj;
            }
            else if (a2.try[i].toLowerCase().compareTo("tiltsize") == 0) {
                n4 = new Float(a2.new[i]) * super.cj;
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
        final float n5 = (float)Math.cos(n);
        final float n6 = (float)Math.sin(n);
        final float n7 = (float)Math.cos(n2);
        final float n8 = (float)Math.sin(n2);
        final float n9 = (float)Math.tan(n4);
        final float n10 = n9 / 2.0f;
        final float n11 = (float)Math.tan(n3);
        final float n12 = n11 / 2.0f;
        final float n13 = n8;
        final float n14 = n5 * n7;
        final float n15 = n6 * n7;
        this.cK = new aq[16];
        for (int j = 0; j < 16; ++j) {
            this.cK[j] = new aq();
        }
        this.cL = new aq[16];
        for (int k = 0; k < 16; ++k) {
            this.cL[k] = new aq();
        }
        final float n16 = n9 * n8 * n5;
        final float n17 = n9 * n8 * n6;
        this.cK[0].try = n14 - n16 - n11 * n6;
        this.cK[0].if = n15 - n17 + n11 * n5;
        this.cK[0].byte = n13 + n9 * n7;
        this.cK[1].try = n14 - n16 - n12 * n6;
        this.cK[1].if = n15 - n17 + n12 * n5;
        this.cK[1].byte = n13 + n9 * n7;
        this.cK[2].try = n14 - n16;
        this.cK[2].if = n15 - n17;
        this.cK[2].byte = n13 + n9 * n7;
        this.cK[3].try = n14 - n16 + n12 * n6;
        this.cK[3].if = n15 - n17 - n12 * n5;
        this.cK[3].byte = n13 + n9 * n7;
        this.cK[4].try = n14 - n16 + n11 * n6;
        this.cK[4].if = n15 - n17 - n11 * n5;
        this.cK[4].byte = n13 + n9 * n7;
        this.cK[5].try = n14 - n10 * n8 * n5 + n11 * n6;
        this.cK[5].if = n15 - n10 * n8 * n6 - n11 * n5;
        this.cK[5].byte = n13 + n10 * n7;
        this.cK[6].try = n14 + n11 * n6;
        this.cK[6].if = n15 - n11 * n5;
        this.cK[6].byte = n13;
        this.cK[7].try = n14 + n10 * n8 * n5 + n11 * n6;
        this.cK[7].if = n15 + n10 * n8 * n6 - n11 * n5;
        this.cK[7].byte = n13 - n10 * n7;
        this.cK[8].try = n14 + n16 + n11 * n6;
        this.cK[8].if = n15 + n17 - n11 * n5;
        this.cK[8].byte = n13 - n9 * n7;
        this.cK[9].try = n14 + n16 + n12 * n6;
        this.cK[9].if = n15 + n17 - n12 * n5;
        this.cK[9].byte = n13 - n9 * n7;
        this.cK[10].try = n14 + n16;
        this.cK[10].if = n15 + n17;
        this.cK[10].byte = n13 - n9 * n7;
        this.cK[11].try = n14 + n16 - n12 * n6;
        this.cK[11].if = n15 + n17 + n12 * n5;
        this.cK[11].byte = n13 - n9 * n7;
        this.cK[12].try = n14 + n16 - n11 * n6;
        this.cK[12].if = n15 + n17 + n11 * n5;
        this.cK[12].byte = n13 - n9 * n7;
        this.cK[13].try = n14 + n10 * n8 * n5 - n11 * n6;
        this.cK[13].if = n15 + n10 * n8 * n6 + n11 * n5;
        this.cK[13].byte = n13 - n10 * n7;
        this.cK[14].try = n14 - n11 * n6;
        this.cK[14].if = n15 + n11 * n5;
        this.cK[14].byte = n13;
        this.cK[15].try = n14 - n10 * n8 * n5 - n11 * n6;
        this.cK[15].if = n15 - n10 * n8 * n6 + n11 * n5;
        this.cK[15].byte = n13 + n10 * n7;
        for (int l = 0; l < 16; ++l) {
            this.cL[l].try = this.cK[l].try;
            this.cL[l].if = this.cK[l].if;
            this.cL[l].byte = this.cK[l].byte;
        }
        this.try(a2);
        super.try = true;
    }
    
    void a(final float[][] array) {
        for (int i = 0; i < 16; ++i) {
            super.void.a(array, this.cL[i], this.cK[i]);
        }
    }
    
    void if(final ab ab) {
        if (!super.do) {
            return;
        }
        ap.a(this.cK, 16, super.cl, super.cl.et >> 1, super.cl.ey >> 1);
        boolean b = false;
        int n = 0;
        int n2 = 15;
        while (n < 16 && !this.cK[n].new) {
            ++n;
        }
        if (n == 16) {
            return;
        }
        while (n2 > n + 2 && !this.cK[n2].new) {
            --n2;
        }
        if (n2 < n + 2) {
            return;
        }
        int i = n;
        int n3 = n2;
        while (i < n2 + 1) {
            while (i < n2 && !this.cK[i].new) {
                ++i;
            }
            if (((this.cK[i].for <= ab.else && ab.else < this.cK[n3].for) || (this.cK[n3].for <= ab.else && ab.else < this.cK[i].for)) && ab.goto < (this.cK[n3].int - this.cK[i].int) * (ab.else - this.cK[i].for) / (this.cK[n3].for - this.cK[i].for) + this.cK[i].int) {
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
            ap.a(this.cK, 16, super.cl, super.cl.et >> 1, super.cl.ey >> 1);
            for (int i = 0; i < 16; ++i) {
                if (this.cK[i].new && this.cK[i + 1 & 0xF].new) {
                    ap.a(super.cl.em, (int)this.cK[i].int, (int)this.cK[i].for, (int)this.cK[i + 1 & 0xF].int, (int)this.cK[i + 1 & 0xF].for, super.cr);
                }
            }
        }
    }
}
