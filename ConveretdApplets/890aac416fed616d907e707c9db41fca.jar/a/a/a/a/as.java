// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class as extends a2
{
    private char[] fN;
    private int[] fO;
    private float fI;
    private float fH;
    private boolean fL;
    private boolean fM;
    private int[] fJ;
    String fK;
    
    public as() {
        this.fN = new char[] { 's', 'f', 'x', '\0' };
        this.fI = 0.0f;
        this.fH = 0.0f;
        this.fL = false;
        this.fM = false;
        this.fK = "Not enough memory to Rainbow Snow effect.";
    }
    
    public void a(final an b, final float ca, final aq long1, final h cc, final t cb) {
        super.b = b;
        super.cA = ca;
        super.cD = 1.0f / super.cA;
        super.long = long1;
        super.cC = cc;
        super.cB = cb;
        super.goto = cb.h;
        super.int = 1;
        super.case = this.fN;
        try {
            this.fO = new int[768];
            this.fJ = new int[768];
        }
        catch (OutOfMemoryError outOfMemoryError) {
            System.out.println(this.fK);
            super.goto = false;
        }
    }
    
    static int for(int n) {
        int n2 = 0;
        int min = 0;
        int min2 = 0;
        n = n * 240 >> 8;
        if (n < 0) {
            n = (240 - n) % 240;
        }
        else {
            n %= 240;
        }
        if (n < 80) {
            n2 = Math.min(255, 255 * (80 - n) / 40);
        }
        else if (n > 160) {
            n2 = Math.min(255, 255 * (n - 160) / 40);
        }
        if (n < 160) {
            min = Math.min(255, 255 * (80 - Math.abs(n - 80)) / 40);
        }
        if (n > 80) {
            min2 = Math.min(255, 255 * (80 - Math.abs(n - 160)) / 40);
        }
        return (n2 & 0xFF) << 16 | (min & 0xFF) << 8 | (min2 & 0xFF);
    }
    
    public void int(final bh bh) {
        try {
            try {
                for (int i = 0; i < bh.do; ++i) {
                    if (bh.try[i].toLowerCase().compareTo("id") == 0) {
                        super.f = (String.valueOf(bh.new[i]) + "\u0000").toCharArray();
                    }
                    else if (bh.try[i].toLowerCase().compareTo("layer") == 0) {
                        super.d = new Integer(bh.new[i]);
                    }
                    else if (bh.try[i].toLowerCase().compareTo("visible") == 0) {
                        if (bh.new[i].compareTo("false") == 0) {
                            super.for = false;
                        }
                    }
                    else if (bh.try[i].toLowerCase().compareTo("play") == 0) {
                        if (bh.new[i].compareTo("false") == 0) {
                            super.ek = false;
                        }
                    }
                    else if (bh.try[i].toLowerCase().compareTo("rotatespeed") == 0) {
                        this.fH = Math.abs(new Float(bh.new[i]));
                        if (this.fH != 0.0f) {
                            this.fL = true;
                        }
                    }
                    else if (bh.try[i].toLowerCase().compareTo("rotate") == 0) {
                        this.fI = Math.abs(an.a(bh.new[i]));
                        while (this.fI > 255.0f) {
                            this.fI -= 255.0f;
                        }
                    }
                    else if (bh.try[i].toLowerCase().compareTo("dynamic") == 0 && bh.new[i].compareTo("true") == 0) {
                        this.fM = true;
                    }
                }
                final int n = (int)this.fI;
                for (int j = 0; j < 768; ++j) {
                    int n2 = j / 3;
                    if (n2 > 255) {
                        n2 = 255;
                    }
                    this.fO[j] = (for(n2 + n & 0xFF) | 0xFF000000);
                }
                this.a();
                super.byte = true;
                super.goto = true;
                super.else = true;
            }
            catch (Exception ex) {
                System.out.println(this.fK);
                super.goto = false;
            }
        }
        catch (OutOfMemoryError outOfMemoryError) {
            System.out.println(this.fK);
            super.goto = false;
        }
    }
    
    public boolean a(final long n) {
        this.l();
        return this.fH != 0.0f && super.for && super.byte;
    }
    
    boolean byte(final boolean b) {
        return super.byte = true;
    }
    
    void new(final long n) {
        super.byte = true;
    }
    
    public void new(final boolean b) {
        if (super.goto && super.byte && super.for) {
            try {
                final ap ef = super.cC.eF;
                if (ef.s < -ef.n || ef.r < -ef.m || ef.n > ef.s || ef.m > ef.r) {
                    return;
                }
                int l = ef.l;
                int p = ef.p;
                int n = ef.n;
                int m = ef.m;
                if (n < 0) {
                    l += n;
                    n = 0;
                }
                if (m < 0) {
                    p += m;
                    m = 0;
                }
                if (n + l > ef.s) {
                    l = ef.s - n;
                }
                if (m + p > ef.r) {
                    p = ef.r - m;
                }
                int n2 = n + m * ef.s;
                final int n3 = ef.s - l;
                int n4 = n2;
                if (this.fM) {
                    for (int i = 0; i < 768; ++i) {
                        this.fJ[i] = 0;
                    }
                    for (int j = 0; j < p; ++j) {
                        for (int k = 0; k < l; ++k) {
                            final int n5 = super.cC.eF.x[n4];
                            final int n6 = (n5 & 0xFF) + ((n5 & 0xFF00) >> 8) + ((n5 & 0xFF0000) >> 16);
                            ++this.fJ[n6];
                            super.cC.eF.x[n4] = n6;
                            ++n4;
                        }
                        n4 += n3;
                    }
                    int n7 = 0;
                    int n8 = 0;
                    while (n7 < 256) {
                        this.fJ[n7] = this.fJ[n8++] + this.fJ[n8++] + this.fJ[n8++];
                        ++n7;
                    }
                    final int n9 = p * l;
                    int n10 = 0;
                    int n11 = n9 >> 8;
                    int n12 = 0;
                    for (int n13 = 0; n13 < 256; ++n13) {
                        n10 += this.fJ[n13];
                        this.fJ[n13] = n12;
                        while (n10 > n11) {
                            ++n12;
                            n11 += n9 >> 8;
                        }
                    }
                    final int n14 = (int)this.fI;
                    int n15 = 0;
                    int n16 = 0;
                    while (n15 < 256) {
                        final int n17 = for(this.fJ[n15] + n14 & 0xFF) | 0xFF000000;
                        this.fO[n16++] = n17;
                        this.fO[n16++] = n17;
                        this.fO[n16++] = n17;
                        ++n15;
                    }
                    for (int n18 = 0; n18 < p; ++n18) {
                        for (int n19 = 0; n19 < l; ++n19) {
                            super.cC.eF.x[n2++] = this.fO[super.cC.eF.x[n2]];
                        }
                        n2 += n3;
                    }
                    if (this.fL) {
                        this.fI += this.fH;
                        if (this.fI > 255.0f) {
                            this.fI -= 255.0f;
                        }
                    }
                }
                else {
                    if (this.fL) {
                        final int n20 = (int)this.fI;
                        for (int n21 = 0; n21 < 768; ++n21) {
                            int n22 = n21 / 3;
                            if (n22 > 255) {
                                n22 = 255;
                            }
                            this.fO[n21] = (for(n22 + n20 & 0xFF) | 0xFF000000);
                        }
                        this.fI += this.fH;
                        if (this.fI > 255.0f) {
                            this.fI -= 255.0f;
                        }
                    }
                    for (int n23 = 0; n23 < p; ++n23) {
                        for (int n24 = 0; n24 < l; ++n24) {
                            final int n25 = super.cC.eF.x[n2];
                            super.cC.eF.x[n2++] = this.fO[(n25 & 0xFF) + ((n25 & 0xFF00) >> 8) + ((n25 & 0xFF0000) >> 16)];
                        }
                        n2 += n3;
                    }
                }
            }
            catch (Exception ex) {}
        }
    }
    
    public void if() {
    }
}
