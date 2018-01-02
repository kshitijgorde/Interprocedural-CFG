// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class bs extends p
{
    aq[] c2;
    aq[] c3;
    protected char[] c4;
    
    public bs() {
        this.c2 = null;
        this.c3 = null;
        this.c4 = new char[] { 'h', 'o', 't', 's', 'p', 'o', 't', 'R', 'e', 'c', 't', 'a', 'n', 'g', 'l', 'e', '\0' };
    }
    
    public void a(final ac b, final float cz, final ae long1, final f cb, final m ca) {
        super.b = b;
        super.cz = cz;
        super.cC = 1.0f / super.cz;
        super.long = long1;
        super.cB = cb;
        super.int = 2;
        super.cA = ca;
        super.new = new a3();
        super.case = this.c4;
    }
    
    public void if() {
        if (this.c2 != null) {
            for (int i = 0; i < this.c2.length; ++i) {
                this.c2[i] = null;
                this.c3[i] = null;
            }
        }
        this.c2 = null;
        this.c3 = null;
        this.c4 = null;
    }
    
    public void int(final a2 a2) {
        float n = 0.0f;
        float n2 = 0.0f;
        float n3 = 0.0f;
        float n4 = 0.0f;
        for (int i = 0; i < a2.do; ++i) {
            if (a2.try[i].toLowerCase().compareTo("id") == 0) {
                super.f = (String.valueOf(a2.new[i]) + "\u0000").toCharArray();
            }
            else if (a2.try[i].toLowerCase().compareTo("pan") == 0) {
                n = new Float(a2.new[i]) * super.cz;
            }
            else if (a2.try[i].toLowerCase().compareTo("tilt") == 0) {
                n2 = new Float(a2.new[i]) * super.cz;
            }
            else if (a2.try[i].toLowerCase().compareTo("visible") == 0) {
                if (a2.new[i].compareTo("false") == 0) {
                    super.for = false;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("pansize") == 0) {
                n3 = new Float(a2.new[i]) * super.cz;
            }
            else if (a2.try[i].toLowerCase().compareTo("tiltsize") == 0) {
                n4 = new Float(a2.new[i]) * super.cz;
            }
            else if (a2.try[i].toLowerCase().compareTo("color") == 0) {
                super.cH = ac.a(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("bordersize") == 0) {
                super.cI = ac.a(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("display") == 0 && a2.new[i].compareTo("true") == 0) {
                super.cN = true;
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
        this.c2 = new aq[16];
        for (int j = 0; j < 16; ++j) {
            this.c2[j] = new aq();
        }
        this.c3 = new aq[16];
        for (int k = 0; k < 16; ++k) {
            this.c3[k] = new aq();
        }
        final float n16 = n9 * n8 * n5;
        final float n17 = n9 * n8 * n6;
        this.c2[0].try = n14 - n16 - n11 * n6;
        this.c2[0].if = n15 - n17 + n11 * n5;
        this.c2[0].byte = n13 + n9 * n7;
        this.c2[1].try = n14 - n16 - n12 * n6;
        this.c2[1].if = n15 - n17 + n12 * n5;
        this.c2[1].byte = n13 + n9 * n7;
        this.c2[2].try = n14 - n16;
        this.c2[2].if = n15 - n17;
        this.c2[2].byte = n13 + n9 * n7;
        this.c2[3].try = n14 - n16 + n12 * n6;
        this.c2[3].if = n15 - n17 - n12 * n5;
        this.c2[3].byte = n13 + n9 * n7;
        this.c2[4].try = n14 - n16 + n11 * n6;
        this.c2[4].if = n15 - n17 - n11 * n5;
        this.c2[4].byte = n13 + n9 * n7;
        this.c2[5].try = n14 - n10 * n8 * n5 + n11 * n6;
        this.c2[5].if = n15 - n10 * n8 * n6 - n11 * n5;
        this.c2[5].byte = n13 + n10 * n7;
        this.c2[6].try = n14 + n11 * n6;
        this.c2[6].if = n15 - n11 * n5;
        this.c2[6].byte = n13;
        this.c2[7].try = n14 + n10 * n8 * n5 + n11 * n6;
        this.c2[7].if = n15 + n10 * n8 * n6 - n11 * n5;
        this.c2[7].byte = n13 - n10 * n7;
        this.c2[8].try = n14 + n16 + n11 * n6;
        this.c2[8].if = n15 + n17 - n11 * n5;
        this.c2[8].byte = n13 - n9 * n7;
        this.c2[9].try = n14 + n16 + n12 * n6;
        this.c2[9].if = n15 + n17 - n12 * n5;
        this.c2[9].byte = n13 - n9 * n7;
        this.c2[10].try = n14 + n16;
        this.c2[10].if = n15 + n17;
        this.c2[10].byte = n13 - n9 * n7;
        this.c2[11].try = n14 + n16 - n12 * n6;
        this.c2[11].if = n15 + n17 + n12 * n5;
        this.c2[11].byte = n13 - n9 * n7;
        this.c2[12].try = n14 + n16 - n11 * n6;
        this.c2[12].if = n15 + n17 + n11 * n5;
        this.c2[12].byte = n13 - n9 * n7;
        this.c2[13].try = n14 + n10 * n8 * n5 - n11 * n6;
        this.c2[13].if = n15 + n10 * n8 * n6 + n11 * n5;
        this.c2[13].byte = n13 - n10 * n7;
        this.c2[14].try = n14 - n11 * n6;
        this.c2[14].if = n15 + n11 * n5;
        this.c2[14].byte = n13;
        this.c2[15].try = n14 - n10 * n8 * n5 - n11 * n6;
        this.c2[15].if = n15 - n10 * n8 * n6 + n11 * n5;
        this.c2[15].byte = n13 + n10 * n7;
        for (int l = 0; l < 16; ++l) {
            this.c3[l].try = this.c2[l].try;
            this.c3[l].if = this.c2[l].if;
            this.c3[l].byte = this.c2[l].byte;
        }
        this.try(a2);
        super.byte = true;
    }
    
    void a(final float[][] array) {
        for (int i = 0; i < 16; ++i) {
            super.b.a(array, this.c3[i], this.c2[i]);
        }
    }
    
    void if(final ab ab) {
        if (!super.for) {
            return;
        }
        ap.a(this.c2, 16, super.cB, super.cB.eL >> 1, super.cB.eQ >> 1);
        boolean b = false;
        int n = 0;
        int n2 = 15;
        while (n < 16 && !this.c2[n].new) {
            ++n;
        }
        if (n == 16) {
            return;
        }
        while (n2 > n + 2 && !this.c2[n2].new) {
            --n2;
        }
        if (n2 < n + 2) {
            return;
        }
        int i = n;
        int n3 = n2;
        while (i < n2 + 1) {
            while (i < n2 && !this.c2[i].new) {
                ++i;
            }
            if (((this.c2[i].for <= ab.else && ab.else < this.c2[n3].for) || (this.c2[n3].for <= ab.else && ab.else < this.c2[i].for)) && ab.goto < (this.c2[n3].int - this.c2[i].int) * (ab.else - this.c2[i].for) / (this.c2[n3].for - this.c2[i].for) + this.c2[i].int) {
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
        this.n();
        final boolean do1 = super.do;
        super.do = false;
        return do1;
    }
    
    public void new(final boolean b) {
        if (super.cN && super.for) {
            ap.a(this.c2, 16, super.cB, super.cB.eL >> 1, super.cB.eQ >> 1);
            for (int i = 0; i < 16; ++i) {
                if (this.c2[i].new && this.c2[i + 1 & 0xF].new) {
                    this.a(super.cB.eE, (int)this.c2[i].int, (int)this.c2[i].for, (int)this.c2[i + 1 & 0xF].int, (int)this.c2[i + 1 & 0xF].for, super.cH, super.cI);
                }
            }
        }
    }
}
