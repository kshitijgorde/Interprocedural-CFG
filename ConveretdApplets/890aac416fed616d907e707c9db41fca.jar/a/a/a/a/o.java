// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class o extends a2
{
    private char[] fl;
    
    public o() {
        this.fl = new char[] { 's', 'f', 'x', '\0' };
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
        super.case = this.fl;
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
                int n2 = n + m * ef.s;
                final int n3 = ef.s - l;
                for (int i = 0; i < p; ++i) {
                    for (int j = 0; j < l; ++j) {
                        super.cC.eF.x[n2++] = (-1 - super.cC.eF.x[n2] | 0xFF000000);
                    }
                    n2 += n3;
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public void if() {
    }
}
