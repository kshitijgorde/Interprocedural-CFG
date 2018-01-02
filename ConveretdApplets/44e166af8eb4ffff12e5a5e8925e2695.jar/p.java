// 
// Decompiled by Procyon v0.5.30
// 

public class p extends o
{
    private boolean \u00cf;
    private o \u00d0;
    
    public p(final o \u00f0, final boolean \u00ef, final int p3) {
        this.\u00d0 = \u00f0;
        this.\u00cf = \u00ef;
        super.p = p3;
        super.n = \u00f0.n;
        super.o = \u00f0.o;
        super.l = \u00f0.l;
        super.m = \u00f0.m;
    }
    
    public void K() {
        ++super.q;
        this.t();
    }
    
    public void S() {
        final int j = this.\u00d0.j;
        super.j = j;
        super.k = new l[j];
        super.q = 0;
        this.t();
    }
    
    private void t() {
        final int n = (super.l + (super.n >> 1)) * super.q / (super.p - 1);
        final int n2 = 16 + super.q * 16 / (super.p - 1);
        this.\u00d0.J();
        if (!this.\u00cf) {
            final int n3 = n + (super.l - (super.n >> 1));
            for (int i = 0; i < super.j; ++i) {
                super.k[i] = this.\u00d0.N();
                if (super.k[i].d < n3) {
                    super.k[i] = new l(n3 + (n3 - super.k[i].d) * (n3 - super.k[i].d) / n2, super.k[i].e + (super.k[i].e - super.m) * (n3 - super.k[i].d) / 256, super.k[i].f);
                }
            }
            return;
        }
        for (int j = 0; j < super.j; ++j) {
            super.k[j] = this.\u00d0.N();
            if (super.k[j].d > n) {
                super.k[j] = new l(n - (super.k[j].d - n) * (super.k[j].d - n) / n2, super.k[j].e + (super.k[j].e - super.m) * (super.k[j].d - n) / 256, super.k[j].f);
            }
        }
    }
}
