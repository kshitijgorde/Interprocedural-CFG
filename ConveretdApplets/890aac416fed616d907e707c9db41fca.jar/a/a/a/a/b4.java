// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class b4 extends a2
{
    private char[] gk;
    int[] gl;
    
    public b4() {
        this.gk = new char[] { 's', 'f', 'x', '\0' };
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
        super.case = this.gk;
        try {
            this.gl = new int[768];
            for (int i = 0; i < 768; ++i) {
                int n = i / 3;
                if (n > 255) {
                    n = 255;
                }
                this.gl[i] = (n | n << 8 | n << 16 | 0xFF000000);
            }
        }
        catch (OutOfMemoryError outOfMemoryError) {
            System.out.println("Not enough memory to play Black and White effect.");
            super.goto = false;
        }
    }
    
    public void int(final bh bh) {
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
            else if (bh.try[i].toLowerCase().compareTo("play") == 0 && bh.new[i].compareTo("false") == 0) {
                super.ek = false;
            }
        }
        this.a();
        super.byte = true;
        super.goto = true;
        super.else = true;
    }
    
    public boolean a(final long n) {
        this.l();
        return false;
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
                int n2 = n + m * super.cC.eF.s;
                final int n3 = super.cC.eF.s - l;
                for (int i = 0; i < p; ++i) {
                    for (int j = 0; j < l; ++j) {
                        final int n4 = super.cC.eF.x[n2];
                        super.cC.eF.x[n2++] = this.gl[(n4 & 0xFF) + ((n4 & 0xFF00) >> 8) + ((n4 & 0xFF0000) >> 16)];
                    }
                    n2 += n3;
                }
            }
            catch (Exception ex) {
                System.out.println("sgdf");
            }
        }
    }
    
    public void if() {
    }
}
