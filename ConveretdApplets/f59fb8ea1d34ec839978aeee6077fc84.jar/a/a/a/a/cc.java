// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class cc extends w
{
    a4[] c3;
    a4[] c4;
    protected char[] c5;
    
    public cc() {
        this.c3 = null;
        this.c4 = null;
        this.c5 = new char[] { 'h', 'o', 't', 's', 'p', 'o', 't', 'R', 'e', 'c', 't', 'a', 'n', 'g', 'l', 'e', '\0' };
    }
    
    public void a(final an b, final float ca, final aq long1, final h cc, final t cb) {
        super.b = b;
        super.cA = ca;
        super.cD = 1.0f / super.cA;
        super.long = long1;
        super.cC = cc;
        super.int = 2;
        super.cB = cb;
        super.new = new bi();
        super.case = this.c5;
    }
    
    public void if() {
        if (this.c3 != null) {
            for (int i = 0; i < this.c3.length; ++i) {
                this.c3[i] = null;
                this.c4[i] = null;
            }
        }
        this.c3 = null;
        this.c4 = null;
        this.c5 = null;
    }
    
    public void int(final bh bh) {
        float n = 0.0f;
        float n2 = 0.0f;
        float n3 = 0.0f;
        float n4 = 0.0f;
        for (int i = 0; i < bh.do; ++i) {
            if (bh.try[i].toLowerCase().compareTo("id") == 0) {
                super.f = (String.valueOf(bh.new[i]) + "\u0000").toCharArray();
            }
            else if (bh.try[i].toLowerCase().compareTo("pan") == 0) {
                n = new Float(bh.new[i]) * super.cA;
            }
            else if (bh.try[i].toLowerCase().compareTo("tilt") == 0) {
                n2 = new Float(bh.new[i]) * super.cA;
            }
            else if (bh.try[i].toLowerCase().compareTo("visible") == 0) {
                if (bh.new[i].compareTo("false") == 0) {
                    super.for = false;
                }
            }
            else if (bh.try[i].toLowerCase().compareTo("pansize") == 0) {
                n3 = new Float(bh.new[i]) * super.cA;
            }
            else if (bh.try[i].toLowerCase().compareTo("tiltsize") == 0) {
                n4 = new Float(bh.new[i]) * super.cA;
            }
            else if (bh.try[i].toLowerCase().compareTo("color") == 0) {
                super.cI = an.a(bh.new[i]);
            }
            else if (bh.try[i].toLowerCase().compareTo("bordersize") == 0) {
                super.cJ = an.a(bh.new[i]);
            }
            else if (bh.try[i].toLowerCase().compareTo("display") == 0 && bh.new[i].compareTo("true") == 0) {
                super.cO = true;
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
        this.c3 = new a4[16];
        for (int j = 0; j < 16; ++j) {
            this.c3[j] = new a4();
        }
        this.c4 = new a4[16];
        for (int k = 0; k < 16; ++k) {
            this.c4[k] = new a4();
        }
        final float n16 = n9 * n8 * n5;
        final float n17 = n9 * n8 * n6;
        this.c3[0].try = n14 - n16 - n11 * n6;
        this.c3[0].if = n15 - n17 + n11 * n5;
        this.c3[0].byte = n13 + n9 * n7;
        this.c3[1].try = n14 - n16 - n12 * n6;
        this.c3[1].if = n15 - n17 + n12 * n5;
        this.c3[1].byte = n13 + n9 * n7;
        this.c3[2].try = n14 - n16;
        this.c3[2].if = n15 - n17;
        this.c3[2].byte = n13 + n9 * n7;
        this.c3[3].try = n14 - n16 + n12 * n6;
        this.c3[3].if = n15 - n17 - n12 * n5;
        this.c3[3].byte = n13 + n9 * n7;
        this.c3[4].try = n14 - n16 + n11 * n6;
        this.c3[4].if = n15 - n17 - n11 * n5;
        this.c3[4].byte = n13 + n9 * n7;
        this.c3[5].try = n14 - n10 * n8 * n5 + n11 * n6;
        this.c3[5].if = n15 - n10 * n8 * n6 - n11 * n5;
        this.c3[5].byte = n13 + n10 * n7;
        this.c3[6].try = n14 + n11 * n6;
        this.c3[6].if = n15 - n11 * n5;
        this.c3[6].byte = n13;
        this.c3[7].try = n14 + n10 * n8 * n5 + n11 * n6;
        this.c3[7].if = n15 + n10 * n8 * n6 - n11 * n5;
        this.c3[7].byte = n13 - n10 * n7;
        this.c3[8].try = n14 + n16 + n11 * n6;
        this.c3[8].if = n15 + n17 - n11 * n5;
        this.c3[8].byte = n13 - n9 * n7;
        this.c3[9].try = n14 + n16 + n12 * n6;
        this.c3[9].if = n15 + n17 - n12 * n5;
        this.c3[9].byte = n13 - n9 * n7;
        this.c3[10].try = n14 + n16;
        this.c3[10].if = n15 + n17;
        this.c3[10].byte = n13 - n9 * n7;
        this.c3[11].try = n14 + n16 - n12 * n6;
        this.c3[11].if = n15 + n17 + n12 * n5;
        this.c3[11].byte = n13 - n9 * n7;
        this.c3[12].try = n14 + n16 - n11 * n6;
        this.c3[12].if = n15 + n17 + n11 * n5;
        this.c3[12].byte = n13 - n9 * n7;
        this.c3[13].try = n14 + n10 * n8 * n5 - n11 * n6;
        this.c3[13].if = n15 + n10 * n8 * n6 + n11 * n5;
        this.c3[13].byte = n13 - n10 * n7;
        this.c3[14].try = n14 - n11 * n6;
        this.c3[14].if = n15 + n11 * n5;
        this.c3[14].byte = n13;
        this.c3[15].try = n14 - n10 * n8 * n5 - n11 * n6;
        this.c3[15].if = n15 - n10 * n8 * n6 + n11 * n5;
        this.c3[15].byte = n13 + n10 * n7;
        for (int l = 0; l < 16; ++l) {
            this.c4[l].try = this.c3[l].try;
            this.c4[l].if = this.c3[l].if;
            this.c4[l].byte = this.c3[l].byte;
        }
        this.try(bh);
        super.byte = true;
    }
    
    void a(final float[][] array) {
        for (int i = 0; i < 16; ++i) {
            super.b.a(array, this.c4[i], this.c3[i]);
        }
    }
    
    void if(final am am) {
        if (!super.for) {
            return;
        }
        a3.a(this.c3, 16, super.cC, super.cC.eM >> 1, super.cC.eR >> 1);
        boolean b = false;
        int n = 0;
        int n2 = 15;
        while (n < 16 && !this.c3[n].new) {
            ++n;
        }
        if (n == 16) {
            return;
        }
        while (n2 > n + 2 && !this.c3[n2].new) {
            --n2;
        }
        if (n2 < n + 2) {
            return;
        }
        int i = n;
        int n3 = n2;
        while (i < n2 + 1) {
            while (i < n2 && !this.c3[i].new) {
                ++i;
            }
            if (((this.c3[i].for <= am.else && am.else < this.c3[n3].for) || (this.c3[n3].for <= am.else && am.else < this.c3[i].for)) && am.goto < (this.c3[n3].int - this.c3[i].int) * (am.else - this.c3[i].for) / (this.c3[n3].for - this.c3[i].for) + this.c3[i].int) {
                b = !b;
            }
            n3 = i++;
        }
        if (b) {
            this.a(am, true);
        }
        else {
            this.a(am, false);
        }
    }
    
    public boolean a(final long n) {
        this.l();
        final boolean do1 = super.do;
        super.do = false;
        return do1;
    }
    
    public void new(final boolean b) {
        if (super.cO && super.for) {
            a3.a(this.c3, 16, super.cC, super.cC.eM >> 1, super.cC.eR >> 1);
            for (int i = 0; i < 16; ++i) {
                if (this.c3[i].new && this.c3[i + 1 & 0xF].new) {
                    this.a(super.cC.eF, (int)this.c3[i].int, (int)this.c3[i].for, (int)this.c3[i + 1 & 0xF].int, (int)this.c3[i + 1 & 0xF].for, super.cI, super.cJ);
                }
            }
        }
    }
}
