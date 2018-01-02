// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class d extends a2
{
    private char[] gE;
    
    public d() {
        this.gE = new char[] { 'p', 'a', 'n', 'o', 'C', 'y', 'l', 'i', 'n', 'd', 'e', 'r', '\0' };
    }
    
    public void if() {
        super.if();
        this.gE = null;
    }
    
    public void a(final an b, final float ca, final aq long1, final h cc, final t cb) {
        super.ea = 0.7853982f;
        super.d5 = -super.ea;
        super.ew = 3.1415927f;
        super.em = -super.ew;
        super.b = b;
        super.cA = ca;
        super.cD = 1.0f / super.cA;
        super.long = long1;
        super.cC = cc;
        super.cB = cb;
        super.goto = super.cB.h;
        super.int = 1;
        super.case = this.gE;
    }
    
    protected void r() {
        final int n = 17;
        super.ej = (int)(n * 2 * super.b.ar / 100.0f);
        super.eh = (int)(n * super.b.ar / 100.0f);
        (super.d9 = new a4[1][])[0] = new a4[super.ej * super.eh];
        (super.ef = new a4[1][])[0] = new a4[super.ej * super.eh];
        final double tan = Math.tan(super.ea);
        final double tan2 = Math.tan(super.d5);
        for (int i = 0; i < super.eh; ++i) {
            for (int j = 0; j < super.ej; ++j) {
                final int n2 = j + i * super.ej;
                super.d9[0][n2] = new a4();
                super.ef[0][n2] = new a4();
                final float n3 = super.ew + j * (-super.ew + super.em) / (super.ej - 1);
                super.d9[0][n2].byte = (float)(tan + i * (-tan + tan2) / (super.eh - 1));
                super.d9[0][n2].try = (float)Math.cos(n3);
                super.d9[0][n2].if = (float)Math.sin(n3);
                super.d9[0][n2].do = j * super.dX.s / (super.ej - 1);
                super.d9[0][n2].a = i * super.dX.r / (super.eh - 1);
                super.ef[0][n2].byte = super.d9[0][n2].byte;
                super.ef[0][n2].try = super.d9[0][n2].try;
                super.ef[0][n2].if = super.d9[0][n2].if;
                super.ef[0][n2].do = super.d9[0][n2].do;
                super.ef[0][n2].a = super.d9[0][n2].a;
            }
        }
    }
    
    protected boolean do(final int n, final int n2) {
        return this.for(this.if(n, n2)) < 0L;
    }
    
    protected long for(final a4 a4) {
        final float n = (float)Math.atan2(a4.if, a4.try);
        final float n2 = (float)Math.atan(a4.byte / Math.sqrt(Math.pow(a4.try, 2.0) + Math.pow(a4.if, 2.0)));
        final float n3 = super.dX.s / (super.ew - super.em);
        return super.dX.a((int)((super.ew - n) * n3), (int)((super.dX.r >> 1) - Math.tan(n2) * n3 * super.dX.r / (float)(Math.tan(super.ea) * n3 - Math.tan(super.d5) * n3)));
    }
}
