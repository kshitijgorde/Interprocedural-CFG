// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class u extends w
{
    a4[] cT;
    a4[] cV;
    int cU;
    protected char[] cW;
    
    public u() {
        this.cT = null;
        this.cV = null;
        this.cU = 0;
        this.cW = new char[] { 'h', 'o', 't', 's', 'p', 'o', 't', 'P', 'o', 'l', 'y', 'g', 'o', 'n', 'a', 'l', '\0' };
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
        super.case = this.cW;
    }
    
    public void if() {
        if (this.cT != null) {
            for (int i = 0; i < this.cU; ++i) {
                this.cT[i] = null;
                this.cV[i] = null;
            }
        }
        this.cT = null;
        this.cV = null;
        this.cW = null;
    }
    
    public void int(final bh bh) {
        for (int i = 0; i < bh.do; ++i) {
            if (bh.try[i].toLowerCase().compareTo("id") == 0) {
                super.f = (String.valueOf(bh.new[i]) + "\u0000").toCharArray();
            }
            else if (bh.try[i].toLowerCase().compareTo("visible") == 0) {
                if (bh.new[i].compareTo("false") == 0) {
                    super.for = false;
                }
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
        bh bh2 = bh.if;
        int n = 0;
        while (bh2 != null) {
            if (bh2.a.toLowerCase().compareTo("polygon") == 0) {
                for (bh bh3 = bh2.if; bh3 != null; bh3 = bh3.for, ++n) {}
                this.cT = new a4[n];
                n = 0;
                for (bh bh4 = bh2.if; bh4 != null; bh4 = bh4.for) {
                    if (bh4.a.toLowerCase().compareTo("vertex") == 0) {
                        float do1 = 0.0f;
                        float a = 0.0f;
                        for (int j = 0; j < bh4.do; ++j) {
                            if (bh4.try[j].toLowerCase().compareTo("pan") == 0) {
                                do1 = new Float(bh4.new[j]) * super.cA;
                            }
                            else if (bh4.try[j].toLowerCase().compareTo("tilt") == 0) {
                                a = new Float(bh4.new[j]) * super.cA;
                            }
                        }
                        this.cT[n] = new a4();
                        this.cT[n].try = (float)(Math.cos(do1) * Math.cos(a));
                        this.cT[n].if = (float)(Math.sin(do1) * Math.cos(a));
                        this.cT[n].byte = (float)Math.sin(a);
                        this.cT[n].do = do1;
                        this.cT[n].a = a;
                        ++n;
                    }
                }
            }
            bh2 = bh2.for;
        }
        this.cU = 0;
        for (int k = 0; k < n; ++k) {
            int n2 = k + 1;
            if (n2 >= n) {
                n2 = 0;
            }
            int n3 = (int)((float)Math.atan(Math.sqrt((this.cT[n2].try - this.cT[k].try) * (this.cT[n2].try - this.cT[k].try) + (this.cT[n2].if - this.cT[k].if) * (this.cT[n2].if - this.cT[k].if) + (this.cT[n2].byte - this.cT[k].byte) * (this.cT[n2].byte - this.cT[k].byte)) / 2.0) * 2.0f * 9.0);
            if (n3 == 0) {
                n3 = 1;
            }
            this.cT[k].do = n3;
            this.cU += n3;
        }
        ++this.cU;
        this.cV = new a4[this.cU + n];
        this.cU = 0;
        for (int l = 0; l < n; ++l) {
            int n4 = l + 1;
            if (n4 >= n) {
                n4 = 0;
            }
            final float n5 = (this.cT[n4].try - this.cT[l].try) / this.cT[l].do;
            final float n6 = (this.cT[n4].if - this.cT[l].if) / this.cT[l].do;
            final float n7 = (this.cT[n4].byte - this.cT[l].byte) / this.cT[l].do;
            this.cV[this.cU] = new a4();
            this.cV[this.cU].try = this.cT[l].try;
            this.cV[this.cU].if = this.cT[l].if;
            this.cV[this.cU].byte = this.cT[l].byte;
            ++this.cU;
            for (int n8 = 1; n8 < this.cT[l].do; ++n8) {
                this.cV[this.cU] = new a4();
                this.cV[this.cU].try = this.cV[this.cU - 1].try + n5;
                this.cV[this.cU].if = this.cV[this.cU - 1].if + n6;
                this.cV[this.cU].byte = this.cV[this.cU - 1].byte + n7;
                ++this.cU;
            }
        }
        this.cT = new a4[this.cU + 1];
        for (int n9 = 0; n9 < this.cU; ++n9) {
            this.cT[n9] = new a4();
            this.cT[n9].try = this.cV[n9].try;
            this.cT[n9].if = this.cV[n9].if;
            this.cT[n9].byte = this.cV[n9].byte;
        }
        super.cE /= this.cU;
        super.cx /= this.cU;
        this.try(bh);
        super.byte = true;
    }
    
    void a(final float[][] array) {
        for (int i = 0; i < this.cU; ++i) {
            super.b.a(array, this.cV[i], this.cT[i]);
        }
    }
    
    void if(final am am) {
        if (!super.for) {
            return;
        }
        a3.a(this.cT, this.cU, super.cC, super.cC.eM >> 1, super.cC.eR >> 1);
        boolean b = false;
        int n = 0;
        int n2 = this.cU - 1;
        while (n < this.cU && !this.cT[n].new) {
            ++n;
        }
        if (n == this.cU) {
            return;
        }
        while (n2 > n + 2 && !this.cT[n2].new) {
            --n2;
        }
        if (n2 < n + 2) {
            return;
        }
        int i = n;
        int n3 = n2;
        while (i < n2 + 1) {
            while (i < n2 && !this.cT[i].new) {
                ++i;
            }
            if (((this.cT[i].for <= am.else && am.else < this.cT[n3].for) || (this.cT[n3].for <= am.else && am.else < this.cT[i].for)) && am.goto < (this.cT[n3].int - this.cT[i].int) * (am.else - this.cT[i].for) / (this.cT[n3].for - this.cT[i].for) + this.cT[i].int) {
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
            a3.a(this.cT, this.cU, super.cC, super.cC.eM >> 1, super.cC.eR >> 1);
            final int n = this.cU - 1;
            for (int i = 0; i < n; ++i) {
                if (this.cT[i].new && this.cT[i + 1].new) {
                    this.a(super.cC.eF, (int)this.cT[i].int, (int)this.cT[i].for, (int)this.cT[i + 1].int, (int)this.cT[i + 1].for, super.cI, super.cJ);
                }
            }
            if (this.cT[0].new && this.cT[n].new) {
                this.a(super.cC.eF, (int)this.cT[n].int, (int)this.cT[n].for, (int)this.cT[0].int, (int)this.cT[0].for, super.cI, super.cJ);
            }
        }
    }
}
