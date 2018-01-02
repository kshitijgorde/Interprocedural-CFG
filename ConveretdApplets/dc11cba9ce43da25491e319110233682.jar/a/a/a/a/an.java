// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class an extends ao
{
    private char[] fi;
    
    public an() {
        this.fi = new char[] { 'p', 'a', 'n', 'o', 'S', 'p', 'h', 'e', 'r', 'e', '\0' };
    }
    
    public void if() {
        super.if();
        this.fi = null;
    }
    
    public void a(final ac b, final float ca, final ae long1, final f cc, final m cb) {
        super.ea = 1.5707964f;
        super.d5 = -super.ea;
        super.ew = 3.1415927f;
        super.em = -super.ew;
        super.b = b;
        super.cA = ca;
        super.cD = 1.0f / super.cA;
        super.long = long1;
        super.cC = cc;
        super.cB = cb;
        super.goto = cb.h;
        super.int = 1;
        super.case = this.fi;
    }
    
    protected void r() {
        final int eh = (int)(25.0f * super.b.ar / 100.0f);
        super.ej = (int)(eh * 2.5);
        super.eh = eh;
        (super.d9 = new aq[1][])[0] = new aq[super.ej * super.eh];
        (super.ef = new aq[1][])[0] = new aq[super.ej * super.eh];
        for (int i = 0; i < super.eh; ++i) {
            for (int j = 0; j < super.ej; ++j) {
                final int n = j + i * super.ej;
                super.d9[0][n] = new aq();
                super.ef[0][n] = new aq();
                final float n2 = super.ew + j * (-super.ew + super.em) / (super.ej - 1);
                super.d9[0][n].do = j * super.dX.s / (super.ej - 1);
                final int n3 = (super.eh - 4) * 4;
                int n4;
                if (i == 0) {
                    n4 = 0;
                }
                else if (i == super.eh - 1) {
                    n4 = n3 - 1;
                }
                else if (i == 1) {
                    n4 = 1;
                }
                else if (i == 2) {
                    n4 = 2;
                }
                else if (i == super.eh - 2) {
                    n4 = n3 - 2;
                }
                else if (i == super.eh - 3) {
                    n4 = n3 - 4;
                }
                else {
                    n4 = (i - 2) * 4;
                }
                final float n5 = super.ea + n4 * (-super.ea + super.d5) / (n3 - 1);
                super.d9[0][n].a = n4 * super.dX.r / (n3 - 1);
                super.d9[0][n].byte = (float)Math.sin(n5);
                super.d9[0][n].try = (float)(Math.cos(n2) * Math.cos(n5));
                super.d9[0][n].if = (float)(Math.sin(n2) * Math.cos(n5));
                super.ef[0][n].byte = super.d9[0][n].byte;
                super.ef[0][n].try = super.d9[0][n].try;
                super.ef[0][n].if = super.d9[0][n].if;
                super.ef[0][n].do = super.d9[0][n].do;
                super.ef[0][n].a = super.d9[0][n].a;
            }
        }
    }
    
    protected boolean do(final int n, final int n2) {
        return super.dX != null && this.a(this.if(n, n2)) < 0L;
    }
    
    protected long a(final aq aq) {
        return super.dX.a((int)((super.ew - (float)Math.atan2(aq.if, aq.try)) * (super.dX.s / (super.ew - super.em))), (int)((super.ea - (float)Math.atan(aq.byte / Math.sqrt(Math.pow(aq.try, 2.0) + Math.pow(aq.if, 2.0)))) * (super.dX.r / (super.ea - super.d5))));
    }
}
