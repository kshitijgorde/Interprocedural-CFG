// 
// Decompiled by Procyon v0.5.30
// 

public class x extends aj
{
    al[] bT;
    
    public x() {
        this.bT = null;
    }
    
    public void a(final l int1, final float e, final aa byte1, final t g, final v f) {
        super.int = int1;
        super.E = e;
        super.H = 1.0f / super.E;
        super.byte = byte1;
        super.G = g;
        super.F = f;
    }
    
    public void a() {
        if (this.bT != null) {
            for (int i = 0; i < this.bT.length; ++i) {
                this.bT[i] = null;
            }
        }
        this.bT = null;
    }
    
    public void if(final n n) {
        float n2 = 0.0f;
        float n3 = 0.0f;
        float n4 = 0.0f;
        float n5 = 0.0f;
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
            else if (n.try[i].toLowerCase().compareTo("pansize") == 0) {
                n4 = new Float(n.new[i]) * super.E;
            }
            else if (n.try[i].toLowerCase().compareTo("tiltsize") == 0) {
                n5 = new Float(n.new[i]) * super.E;
            }
            else if (n.try[i].toLowerCase().compareTo("color") == 0) {
                super.bJ = l.a(n.new[i]);
            }
            else if (n.try[i].toLowerCase().compareTo("bordersize") == 0) {
                super.bL = l.a(n.new[i]);
            }
            else if (n.try[i].toLowerCase().compareTo("display") == 0 && n.new[i].compareTo("true") == 0) {
                super.bP = true;
            }
        }
        final float n6 = (float)Math.cos(n2);
        final float n7 = (float)Math.sin(n2);
        final float n8 = (float)Math.cos(n3);
        final float n9 = (float)Math.sin(n3);
        final float n10 = (float)Math.tan(n5);
        final float n11 = n10 / 2.0f;
        final float n12 = (float)Math.tan(n4);
        final float n13 = n12 / 2.0f;
        final float n14 = n9;
        final float n15 = n6 * n8;
        final float n16 = n7 * n8;
        this.bT = new al[16];
        for (int j = 0; j < 16; ++j) {
            this.bT[j] = new al();
        }
        final float n17 = n10 * n9 * n6;
        final float n18 = n10 * n9 * n7;
        this.bT[0].try = n15 - n17 - n12 * n7;
        this.bT[0].if = n16 - n18 + n12 * n6;
        this.bT[0].byte = n14 + n10 * n8;
        this.bT[1].try = n15 - n17 - n13 * n7;
        this.bT[1].if = n16 - n18 + n13 * n6;
        this.bT[1].byte = n14 + n10 * n8;
        this.bT[2].try = n15 - n17;
        this.bT[2].if = n16 - n18;
        this.bT[2].byte = n14 + n10 * n8;
        this.bT[3].try = n15 - n17 + n13 * n7;
        this.bT[3].if = n16 - n18 - n13 * n6;
        this.bT[3].byte = n14 + n10 * n8;
        this.bT[4].try = n15 - n17 + n12 * n7;
        this.bT[4].if = n16 - n18 - n12 * n6;
        this.bT[4].byte = n14 + n10 * n8;
        this.bT[5].try = n15 - n11 * n9 * n6 + n12 * n7;
        this.bT[5].if = n16 - n11 * n9 * n7 - n12 * n6;
        this.bT[5].byte = n14 + n11 * n8;
        this.bT[6].try = n15 + n12 * n7;
        this.bT[6].if = n16 - n12 * n6;
        this.bT[6].byte = n14;
        this.bT[7].try = n15 + n11 * n9 * n6 + n12 * n7;
        this.bT[7].if = n16 + n11 * n9 * n7 - n12 * n6;
        this.bT[7].byte = n14 - n11 * n8;
        this.bT[8].try = n15 + n17 + n12 * n7;
        this.bT[8].if = n16 + n18 - n12 * n6;
        this.bT[8].byte = n14 - n10 * n8;
        this.bT[9].try = n15 + n17 + n13 * n7;
        this.bT[9].if = n16 + n18 - n13 * n6;
        this.bT[9].byte = n14 - n10 * n8;
        this.bT[10].try = n15 + n17;
        this.bT[10].if = n16 + n18;
        this.bT[10].byte = n14 - n10 * n8;
        this.bT[11].try = n15 + n17 - n13 * n7;
        this.bT[11].if = n16 + n18 + n13 * n6;
        this.bT[11].byte = n14 - n10 * n8;
        this.bT[12].try = n15 + n17 - n12 * n7;
        this.bT[12].if = n16 + n18 + n12 * n6;
        this.bT[12].byte = n14 - n10 * n8;
        this.bT[13].try = n15 + n11 * n9 * n6 - n12 * n7;
        this.bT[13].if = n16 + n11 * n9 * n7 + n12 * n6;
        this.bT[13].byte = n14 - n11 * n8;
        this.bT[14].try = n15 - n12 * n7;
        this.bT[14].if = n16 + n12 * n6;
        this.bT[14].byte = n14;
        this.bT[15].try = n15 - n11 * n9 * n6 - n12 * n7;
        this.bT[15].if = n16 - n11 * n9 * n7 + n12 * n6;
        this.bT[15].byte = n14 + n11 * n8;
        this.new(n);
        super.for = true;
    }
    
    void a(final float[][] array) {
        for (int i = 0; i < 16; ++i) {
            super.int.a(array, this.bT[i], this.bT[i]);
        }
    }
    
    void a(final ah ah) {
        if (!super.if) {
            return;
        }
        d.a(this.bT, 16, super.G, super.G.a1 >> 1, super.G.aS >> 1);
        boolean b = false;
        int n = 0;
        int n2 = 15;
        while (n < 16 && !this.bT[n].new) {
            ++n;
        }
        if (n == 16) {
            return;
        }
        while (n2 > n + 2 && !this.bT[n2].new) {
            --n2;
        }
        if (n2 < n + 2) {
            return;
        }
        int i = n;
        int n3 = n2;
        while (i < n2 + 1) {
            while (i < n2 && !this.bT[i].new) {
                ++i;
            }
            if (((this.bT[i].for <= ah.else && ah.else < this.bT[n3].for) || (this.bT[n3].for <= ah.else && ah.else < this.bT[i].for)) && ah.goto < (this.bT[n3].int - this.bT[i].int) * (ah.else - this.bT[i].for) / (this.bT[n3].for - this.bT[i].for) + this.bT[i].int) {
                b = !b;
            }
            n3 = i++;
        }
        if (b) {
            this.a(ah, true);
        }
        else {
            this.a(ah, false);
        }
    }
    
    public boolean a(final long n) {
        final boolean do1 = super.do;
        super.do = false;
        return do1;
    }
    
    public void a(final boolean b) {
        if (super.bP && super.if) {
            d.a(this.bT, 16, super.G, super.G.a1 >> 1, super.G.aS >> 1);
            for (int i = 0; i < 16; ++i) {
                if (this.bT[i].new && this.bT[i + 1 & 0xF].new) {
                    this.a(super.G.a5, (int)this.bT[i].int, (int)this.bT[i].for, (int)this.bT[i + 1 & 0xF].int, (int)this.bT[i + 1 & 0xF].for, super.bJ, super.bL);
                }
            }
        }
    }
}
