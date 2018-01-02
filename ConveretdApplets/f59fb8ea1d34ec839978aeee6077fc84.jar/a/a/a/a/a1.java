// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class a1 extends a2
{
    private char[] gc;
    
    public a1() {
        this.gc = new char[] { 'p', 'a', 'n', 'o', 'S', 'p', 'h', 'e', 'r', 'e', '\0' };
    }
    
    public void if() {
        super.if();
        this.gc = null;
    }
    
    public void a(final an b, final float ca, final aq long1, final h cc, final t cb) {
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
        super.case = this.gc;
    }
    
    protected void r() {
        final int eh = (int)(25.0f * super.b.ar / 100.0f);
        super.ej = (int)(eh * 2.5);
        super.eh = eh;
        (super.d9 = new a4[1][])[0] = new a4[super.ej * super.eh];
        (super.ef = new a4[1][])[0] = new a4[super.ej * super.eh];
        for (int i = 0; i < super.eh; ++i) {
            for (int j = 0; j < super.ej; ++j) {
                final int n = j + i * super.ej;
                super.d9[0][n] = new a4();
                super.ef[0][n] = new a4();
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
    
    protected long a(final a4 a4) {
        return super.dX.a((int)((super.ew - (float)Math.atan2(a4.if, a4.try)) * (super.dX.s / (super.ew - super.em))), (int)((super.ea - (float)Math.atan(a4.byte / Math.sqrt(Math.pow(a4.try, 2.0) + Math.pow(a4.if, 2.0)))) * (super.dX.r / (super.ea - super.d5))));
    }
}
