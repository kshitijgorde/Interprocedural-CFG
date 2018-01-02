import java.util.Date;
import java.awt.Color;

// 
// Decompiled by Procyon v0.5.30
// 

public class n extends o
{
    private Color h;
    private Date i;
    
    public n(final Color h, final int n) {
        final int j = 20;
        super.j = j;
        super.k = new l[j];
        super.o = n;
        super.n = n;
        this.h = h;
        this.I();
    }
    
    public void K() {
        this.I();
    }
    
    public void S() {
        this.I();
    }
    
    public void Q(final a s) {
        super.s = s;
    }
    
    private void I() {
        for (int i = 0; i < 12; ++i) {
            super.k[i] = o.R(super.l, super.m, super.n / 2.0, 3.141592653589793 * i / 6.0, this.h);
            super.k[i].f = o.U(this.h, super.s.A(super.k[i].d, super.k[i].e), 1, 2);
        }
        this.i = new Date();
        for (int j = 12; j < 17; ++j) {
            super.k[j] = o.R(super.l, super.m, (j - 12) * super.n / 8.0, 3.141592653589793 * (this.i.getMinutes() + this.i.getSeconds() / 60.0) / 30.0, this.h);
        }
        for (int k = 17; k < 20; ++k) {
            super.k[k] = o.R(super.l, super.m, (k - 16) * super.n / 8.0, 3.141592653589793 * (this.i.getHours() + this.i.getMinutes() / 60.0) / 6.0, this.h);
        }
        for (int l = 12; l < 20; ++l) {
            super.k[l].f = super.s.A(super.k[l].d, super.k[l].e);
        }
    }
}
