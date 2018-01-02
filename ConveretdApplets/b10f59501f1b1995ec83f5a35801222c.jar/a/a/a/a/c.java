// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class c extends ao
{
    private char[] fp;
    
    public c() {
        this.fp = new char[] { 'p', 'a', 'n', 'o', 'C', 'y', 'l', 'i', 'n', 'd', 'e', 'r', '\0' };
    }
    
    public void if() {
        super.if();
        this.fp = null;
    }
    
    public void a(final ac b, final float cz, final ae long1, final f cb, final m ca) {
        super.d9 = 0.7853982f;
        super.d4 = -super.d9;
        super.ev = 3.1415927f;
        super.el = -super.ev;
        super.b = b;
        super.cz = cz;
        super.cC = 1.0f / super.cz;
        super.long = long1;
        super.cB = cb;
        super.cA = ca;
        super.goto = super.cA.h;
        super.int = 1;
        super.case = this.fp;
    }
    
    protected void t() {
        final int n = 17;
        super.ei = (int)(n * 2 * super.b.ap / 100.0f);
        super.eg = (int)(n * super.b.ap / 100.0f);
        (super.d8 = new aq[1][])[0] = new aq[super.ei * super.eg];
        (super.ee = new aq[1][])[0] = new aq[super.ei * super.eg];
        final double tan = Math.tan(super.d9);
        final double tan2 = Math.tan(super.d4);
        for (int i = 0; i < super.eg; ++i) {
            for (int j = 0; j < super.ei; ++j) {
                final int n2 = j + i * super.ei;
                super.d8[0][n2] = new aq();
                super.ee[0][n2] = new aq();
                final float n3 = super.ev + j * (-super.ev + super.el) / (super.ei - 1);
                super.d8[0][n2].byte = (float)(tan + i * (-tan + tan2) / (super.eg - 1));
                super.d8[0][n2].try = (float)Math.cos(n3);
                super.d8[0][n2].if = (float)Math.sin(n3);
                super.d8[0][n2].do = j * super.dW.j / (super.ei - 1);
                super.d8[0][n2].a = i * super.dW.u / (super.eg - 1);
                super.ee[0][n2].byte = super.d8[0][n2].byte;
                super.ee[0][n2].try = super.d8[0][n2].try;
                super.ee[0][n2].if = super.d8[0][n2].if;
                super.ee[0][n2].do = super.d8[0][n2].do;
                super.ee[0][n2].a = super.d8[0][n2].a;
            }
        }
    }
    
    protected boolean do(final int n, final int n2) {
        return this.for(this.if(n, n2)) < 0L;
    }
    
    protected long for(final aq aq) {
        final float n = (float)Math.atan2(aq.if, aq.try);
        final float n2 = (float)Math.atan(aq.byte / Math.sqrt(Math.pow(aq.try, 2.0) + Math.pow(aq.if, 2.0)));
        final float n3 = super.dW.j / (super.ev - super.el);
        return super.dW.a((int)((super.ev - n) * n3), (int)((super.dW.u >> 1) - Math.tan(n2) * n3 * super.dW.u / (float)(Math.tan(super.d9) * n3 - Math.tan(super.d4) * n3)));
    }
}
