// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class n extends p
{
    aq[] cS;
    aq[] cU;
    int cT;
    protected char[] cV;
    
    public n() {
        this.cS = null;
        this.cU = null;
        this.cT = 0;
        this.cV = new char[] { 'h', 'o', 't', 's', 'p', 'o', 't', 'P', 'o', 'l', 'y', 'g', 'o', 'n', 'a', 'l', '\0' };
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
        super.case = this.cV;
    }
    
    public void if() {
        if (this.cS != null) {
            for (int i = 0; i < this.cT; ++i) {
                this.cS[i] = null;
                this.cU[i] = null;
            }
        }
        this.cS = null;
        this.cU = null;
        this.cV = null;
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
        a2 a3 = a2.if;
        int n = 0;
        while (a3 != null) {
            if (a3.a.toLowerCase().compareTo("polygon") == 0) {
                for (a2 a4 = a3.if; a4 != null; a4 = a4.for, ++n) {}
                this.cS = new aq[n];
                n = 0;
                for (a2 a5 = a3.if; a5 != null; a5 = a5.for) {
                    if (a5.a.toLowerCase().compareTo("vertex") == 0) {
                        float do1 = 0.0f;
                        float a6 = 0.0f;
                        for (int j = 0; j < a5.do; ++j) {
                            if (a5.try[j].toLowerCase().compareTo("pan") == 0) {
                                do1 = new Float(a5.new[j]) * super.cz;
                            }
                            else if (a5.try[j].toLowerCase().compareTo("tilt") == 0) {
                                a6 = new Float(a5.new[j]) * super.cz;
                            }
                        }
                        this.cS[n] = new aq();
                        this.cS[n].try = (float)(Math.cos(do1) * Math.cos(a6));
                        this.cS[n].if = (float)(Math.sin(do1) * Math.cos(a6));
                        this.cS[n].byte = (float)Math.sin(a6);
                        this.cS[n].do = do1;
                        this.cS[n].a = a6;
                        ++n;
                    }
                }
            }
            a3 = a3.for;
        }
        this.cT = 0;
        for (int k = 0; k < n; ++k) {
            int n2 = k + 1;
            if (n2 >= n) {
                n2 = 0;
            }
            int n3 = (int)((float)Math.atan(Math.sqrt((this.cS[n2].try - this.cS[k].try) * (this.cS[n2].try - this.cS[k].try) + (this.cS[n2].if - this.cS[k].if) * (this.cS[n2].if - this.cS[k].if) + (this.cS[n2].byte - this.cS[k].byte) * (this.cS[n2].byte - this.cS[k].byte)) / 2.0) * 2.0f * 9.0);
            if (n3 == 0) {
                n3 = 1;
            }
            this.cS[k].do = n3;
            this.cT += n3;
        }
        ++this.cT;
        this.cU = new aq[this.cT + n];
        this.cT = 0;
        for (int l = 0; l < n; ++l) {
            int n4 = l + 1;
            if (n4 >= n) {
                n4 = 0;
            }
            final float n5 = (this.cS[n4].try - this.cS[l].try) / this.cS[l].do;
            final float n6 = (this.cS[n4].if - this.cS[l].if) / this.cS[l].do;
            final float n7 = (this.cS[n4].byte - this.cS[l].byte) / this.cS[l].do;
            this.cU[this.cT] = new aq();
            this.cU[this.cT].try = this.cS[l].try;
            this.cU[this.cT].if = this.cS[l].if;
            this.cU[this.cT].byte = this.cS[l].byte;
            ++this.cT;
            for (int n8 = 1; n8 < this.cS[l].do; ++n8) {
                this.cU[this.cT] = new aq();
                this.cU[this.cT].try = this.cU[this.cT - 1].try + n5;
                this.cU[this.cT].if = this.cU[this.cT - 1].if + n6;
                this.cU[this.cT].byte = this.cU[this.cT - 1].byte + n7;
                ++this.cT;
            }
        }
        this.cS = new aq[this.cT + 1];
        for (int n9 = 0; n9 < this.cT; ++n9) {
            this.cS[n9] = new aq();
            this.cS[n9].try = this.cU[n9].try;
            this.cS[n9].if = this.cU[n9].if;
            this.cS[n9].byte = this.cU[n9].byte;
        }
        super.cD /= this.cT;
        super.cw /= this.cT;
        this.try(a2);
        super.byte = true;
    }
    
    void a(final float[][] array) {
        for (int i = 0; i < this.cT; ++i) {
            super.b.a(array, this.cU[i], this.cS[i]);
        }
    }
    
    void if(final ab ab) {
        if (!super.for) {
            return;
        }
        ap.a(this.cS, this.cT, super.cB, super.cB.eL >> 1, super.cB.eQ >> 1);
        boolean b = false;
        int n = 0;
        int n2 = this.cT - 1;
        while (n < this.cT && !this.cS[n].new) {
            ++n;
        }
        if (n == this.cT) {
            return;
        }
        while (n2 > n + 2 && !this.cS[n2].new) {
            --n2;
        }
        if (n2 < n + 2) {
            return;
        }
        int i = n;
        int n3 = n2;
        while (i < n2 + 1) {
            while (i < n2 && !this.cS[i].new) {
                ++i;
            }
            if (((this.cS[i].for <= ab.else && ab.else < this.cS[n3].for) || (this.cS[n3].for <= ab.else && ab.else < this.cS[i].for)) && ab.goto < (this.cS[n3].int - this.cS[i].int) * (ab.else - this.cS[i].for) / (this.cS[n3].for - this.cS[i].for) + this.cS[i].int) {
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
            ap.a(this.cS, this.cT, super.cB, super.cB.eL >> 1, super.cB.eQ >> 1);
            final int n = this.cT - 1;
            for (int i = 0; i < n; ++i) {
                if (this.cS[i].new && this.cS[i + 1].new) {
                    this.a(super.cB.eE, (int)this.cS[i].int, (int)this.cS[i].for, (int)this.cS[i + 1].int, (int)this.cS[i + 1].for, super.cH, super.cI);
                }
            }
            if (this.cS[0].new && this.cS[n].new) {
                this.a(super.cB.eE, (int)this.cS[n].int, (int)this.cS[n].for, (int)this.cS[0].int, (int)this.cS[0].for, super.cH, super.cI);
            }
        }
    }
}
