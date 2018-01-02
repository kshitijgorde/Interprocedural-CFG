// 
// Decompiled by Procyon v0.5.30
// 

public class z extends aj
{
    al[] bU;
    int bV;
    
    public z() {
        this.bU = null;
        this.bV = 0;
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
        if (this.bU != null) {
            for (int i = 0; i < this.bV; ++i) {
                this.bU[i] = null;
            }
        }
        this.bV = 0;
        this.bU = null;
    }
    
    public void if(final n n) {
        for (int i = 0; i < n.do; ++i) {
            if (n.try[i].toLowerCase().compareTo("visible") == 0) {
                if (n.new[i].compareTo("false") == 0) {
                    super.if = false;
                }
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
        n n2 = n.if;
        int n3 = 0;
        al[] array = null;
        while (n2 != null) {
            if (n2.a.toLowerCase().compareTo("polygon") == 0) {
                for (n n4 = n2.if; n4 != null; n4 = n4.for, ++n3) {}
                array = new al[n3];
                n3 = 0;
                for (n n5 = n2.if; n5 != null; n5 = n5.for) {
                    if (n5.a.toLowerCase().compareTo("vertex") == 0) {
                        float do1 = 0.0f;
                        float a = 0.0f;
                        for (int j = 0; j < n5.do; ++j) {
                            if (n5.try[j].toLowerCase().compareTo("pan") == 0) {
                                do1 = new Float(n5.new[j]) * super.E;
                            }
                            else if (n5.try[j].toLowerCase().compareTo("tilt") == 0) {
                                a = new Float(n5.new[j]) * super.E;
                            }
                        }
                        array[n3] = new al();
                        array[n3].try = (float)(Math.cos(do1) * Math.cos(a));
                        array[n3].if = (float)(Math.sin(do1) * Math.cos(a));
                        array[n3].byte = (float)Math.sin(a);
                        array[n3].do = do1;
                        array[n3].a = a;
                        ++n3;
                    }
                }
            }
            n2 = n2.for;
        }
        this.bV = 0;
        for (int k = 0; k < n3; ++k) {
            int n6 = k + 1;
            if (n6 >= n3) {
                n6 = 0;
            }
            int n7 = (int)((float)Math.atan(Math.sqrt((array[n6].try - array[k].try) * (array[n6].try - array[k].try) + (array[n6].if - array[k].if) * (array[n6].if - array[k].if) + (array[n6].byte - array[k].byte) * (array[n6].byte - array[k].byte)) / 2.0) * 2.0f * 9.0);
            if (n7 == 0) {
                n7 = 1;
            }
            array[k].do = n7;
            this.bV += n7;
        }
        ++this.bV;
        this.bU = new al[this.bV + n3];
        this.bV = 0;
        for (int l = 0; l < n3; ++l) {
            int n8 = l + 1;
            if (n8 >= n3) {
                n8 = 0;
            }
            final float n9 = (array[n8].try - array[l].try) / array[l].do;
            final float n10 = (array[n8].if - array[l].if) / array[l].do;
            final float n11 = (array[n8].byte - array[l].byte) / array[l].do;
            this.bU[this.bV] = new al();
            this.bU[this.bV].try = array[l].try;
            this.bU[this.bV].if = array[l].if;
            this.bU[this.bV].byte = array[l].byte;
            ++this.bV;
            for (int n12 = 1; n12 < this.bU[l].do; ++n12) {
                this.bU[this.bV] = new al();
                this.bU[this.bV].try = this.bU[this.bV - 1].try + n9;
                this.bU[this.bV].if = this.bU[this.bV - 1].if + n10;
                this.bU[this.bV].byte = this.bU[this.bV - 1].byte + n11;
                ++this.bV;
            }
        }
        super.I /= this.bV;
        super.B /= this.bV;
        this.new(n);
        super.for = true;
    }
    
    void a(final float[][] array) {
        for (int i = 0; i < this.bV; ++i) {
            super.int.a(array, this.bU[i], this.bU[i]);
        }
    }
    
    void a(final ah ah) {
        if (!super.if) {
            return;
        }
        d.a(this.bU, this.bV, super.G, super.G.a1 >> 1, super.G.aS >> 1);
        boolean b = false;
        int n = 0;
        int n2 = this.bV - 1;
        while (n < this.bV && !this.bU[n].new) {
            ++n;
        }
        if (n == this.bV) {
            return;
        }
        while (n2 > n + 2 && !this.bU[n2].new) {
            --n2;
        }
        if (n2 < n + 2) {
            return;
        }
        int i = n;
        int n3 = n2;
        while (i < n2 + 1) {
            while (i < n2 && !this.bU[i].new) {
                ++i;
            }
            if (((this.bU[i].for <= ah.else && ah.else < this.bU[n3].for) || (this.bU[n3].for <= ah.else && ah.else < this.bU[i].for)) && ah.goto < (this.bU[n3].int - this.bU[i].int) * (ah.else - this.bU[i].for) / (this.bU[n3].for - this.bU[i].for) + this.bU[i].int) {
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
            d.a(this.bU, this.bV, super.G, super.G.a1 >> 1, super.G.aS >> 1);
            final int n = this.bV - 1;
            for (int i = 0; i < n; ++i) {
                if (this.bU[i].new && this.bU[i + 1].new) {
                    this.a(super.G.a5, (int)this.bU[i].int, (int)this.bU[i].for, (int)this.bU[i + 1].int, (int)this.bU[i + 1].for, super.bJ, super.bL);
                }
            }
            if (this.bU[0].new && this.bU[n].new) {
                this.a(super.G.a5, (int)this.bU[n].int, (int)this.bU[n].for, (int)this.bU[0].int, (int)this.bU[0].for, super.bJ, super.bL);
            }
        }
    }
}
