// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class n extends p
{
    aq[] cT;
    aq[] cV;
    int cU;
    protected char[] cW;
    
    public n() {
        this.cT = null;
        this.cV = null;
        this.cU = 0;
        this.cW = new char[] { 'h', 'o', 't', 's', 'p', 'o', 't', 'P', 'o', 'l', 'y', 'g', 'o', 'n', 'a', 'l', '\0' };
    }
    
    public void a(final ac b, final float ca, final ae long1, final f cc, final m cb) {
        super.b = b;
        super.cA = ca;
        super.cD = 1.0f / super.cA;
        super.long = long1;
        super.cC = cc;
        super.int = 2;
        super.cB = cb;
        super.new = new a3();
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
    
    public void int(final a2 a2) {
        for (int i = 0; i < a2.do; ++i) {
            if (a2.try[i].toLowerCase().compareTo("id") == 0) {
                super.f = (String.valueOf(a2.new[i]) + "\u0000").toCharArray();
            }
            else if (a2.try[i].toLowerCase().compareTo("visible") == 0) {
                if (a2.new[i].compareTo("false") == 0) {
                    super.for = false;
                }
            }
            else if (a2.try[i].toLowerCase().compareTo("color") == 0) {
                super.cI = ac.a(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("bordersize") == 0) {
                super.cJ = ac.a(a2.new[i]);
            }
            else if (a2.try[i].toLowerCase().compareTo("display") == 0 && a2.new[i].compareTo("true") == 0) {
                super.cO = true;
            }
        }
        this.a();
        a2 a3 = a2.if;
        int n = 0;
        while (a3 != null) {
            if (a3.a.toLowerCase().compareTo("polygon") == 0) {
                for (a2 a4 = a3.if; a4 != null; a4 = a4.for, ++n) {}
                this.cT = new aq[n];
                n = 0;
                for (a2 a5 = a3.if; a5 != null; a5 = a5.for) {
                    if (a5.a.toLowerCase().compareTo("vertex") == 0) {
                        float do1 = 0.0f;
                        float a6 = 0.0f;
                        for (int j = 0; j < a5.do; ++j) {
                            if (a5.try[j].toLowerCase().compareTo("pan") == 0) {
                                do1 = new Float(a5.new[j]) * super.cA;
                            }
                            else if (a5.try[j].toLowerCase().compareTo("tilt") == 0) {
                                a6 = new Float(a5.new[j]) * super.cA;
                            }
                        }
                        this.cT[n] = new aq();
                        this.cT[n].try = (float)(Math.cos(do1) * Math.cos(a6));
                        this.cT[n].if = (float)(Math.sin(do1) * Math.cos(a6));
                        this.cT[n].byte = (float)Math.sin(a6);
                        this.cT[n].do = do1;
                        this.cT[n].a = a6;
                        ++n;
                    }
                }
            }
            a3 = a3.for;
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
        this.cV = new aq[this.cU + n];
        this.cU = 0;
        for (int l = 0; l < n; ++l) {
            int n4 = l + 1;
            if (n4 >= n) {
                n4 = 0;
            }
            final float n5 = (this.cT[n4].try - this.cT[l].try) / this.cT[l].do;
            final float n6 = (this.cT[n4].if - this.cT[l].if) / this.cT[l].do;
            final float n7 = (this.cT[n4].byte - this.cT[l].byte) / this.cT[l].do;
            this.cV[this.cU] = new aq();
            this.cV[this.cU].try = this.cT[l].try;
            this.cV[this.cU].if = this.cT[l].if;
            this.cV[this.cU].byte = this.cT[l].byte;
            ++this.cU;
            for (int n8 = 1; n8 < this.cT[l].do; ++n8) {
                this.cV[this.cU] = new aq();
                this.cV[this.cU].try = this.cV[this.cU - 1].try + n5;
                this.cV[this.cU].if = this.cV[this.cU - 1].if + n6;
                this.cV[this.cU].byte = this.cV[this.cU - 1].byte + n7;
                ++this.cU;
            }
        }
        this.cT = new aq[this.cU + 1];
        for (int n9 = 0; n9 < this.cU; ++n9) {
            this.cT[n9] = new aq();
            this.cT[n9].try = this.cV[n9].try;
            this.cT[n9].if = this.cV[n9].if;
            this.cT[n9].byte = this.cV[n9].byte;
        }
        super.cE /= this.cU;
        super.cx /= this.cU;
        this.try(a2);
        super.byte = true;
    }
    
    void a(final float[][] array) {
        for (int i = 0; i < this.cU; ++i) {
            super.b.a(array, this.cV[i], this.cT[i]);
        }
    }
    
    void if(final ab ab) {
        if (!super.for) {
            return;
        }
        ap.a(this.cT, this.cU, super.cC, super.cC.eM >> 1, super.cC.eR >> 1);
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
            if (((this.cT[i].for <= ab.else && ab.else < this.cT[n3].for) || (this.cT[n3].for <= ab.else && ab.else < this.cT[i].for)) && ab.goto < (this.cT[n3].int - this.cT[i].int) * (ab.else - this.cT[i].for) / (this.cT[n3].for - this.cT[i].for) + this.cT[i].int) {
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
        this.l();
        final boolean do1 = super.do;
        super.do = false;
        return do1;
    }
    
    public void new(final boolean b) {
        if (super.cO && super.for) {
            ap.a(this.cT, this.cU, super.cC, super.cC.eM >> 1, super.cC.eR >> 1);
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
