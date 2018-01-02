// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class an extends ao
{
    private char[] fh;
    
    public an() {
        this.fh = new char[] { 'p', 'a', 'n', 'o', 'S', 'p', 'h', 'e', 'r', 'e', '\0' };
    }
    
    public void if() {
        super.if();
        this.fh = null;
    }
    
    public void a(final ac b, final float cz, final ae long1, final f cb, final m ca) {
        super.d9 = 1.5707964f;
        super.d4 = -super.d9;
        super.ev = 3.1415927f;
        super.el = -super.ev;
        super.b = b;
        super.cz = cz;
        super.cC = 1.0f / super.cz;
        super.long = long1;
        super.cB = cb;
        super.cA = ca;
        super.goto = ca.h;
        super.int = 1;
        super.case = this.fh;
    }
    
    protected void t() {
        final int eg = (int)(25.0f * super.b.ap / 100.0f);
        super.ei = (int)(eg * 2.5);
        super.eg = eg;
        (super.d8 = new aq[1][])[0] = new aq[super.ei * super.eg];
        (super.ee = new aq[1][])[0] = new aq[super.ei * super.eg];
        for (int i = 0; i < super.eg; ++i) {
            for (int j = 0; j < super.ei; ++j) {
                final int n = j + i * super.ei;
                super.d8[0][n] = new aq();
                super.ee[0][n] = new aq();
                final float n2 = super.ev + j * (-super.ev + super.el) / (super.ei - 1);
                super.d8[0][n].do = j * super.dW.j / (super.ei - 1);
                final int n3 = (super.eg - 4) * 4;
                int n4;
                if (i == 0) {
                    n4 = 0;
                }
                else if (i == super.eg - 1) {
                    n4 = n3 - 1;
                }
                else if (i == 1) {
                    n4 = 1;
                }
                else if (i == 2) {
                    n4 = 2;
                }
                else if (i == super.eg - 2) {
                    n4 = n3 - 2;
                }
                else if (i == super.eg - 3) {
                    n4 = n3 - 4;
                }
                else {
                    n4 = (i - 2) * 4;
                }
                final float n5 = super.d9 + n4 * (-super.d9 + super.d4) / (n3 - 1);
                super.d8[0][n].a = n4 * super.dW.u / (n3 - 1);
                super.d8[0][n].byte = (float)Math.sin(n5);
                super.d8[0][n].try = (float)(Math.cos(n2) * Math.cos(n5));
                super.d8[0][n].if = (float)(Math.sin(n2) * Math.cos(n5));
                super.ee[0][n].byte = super.d8[0][n].byte;
                super.ee[0][n].try = super.d8[0][n].try;
                super.ee[0][n].if = super.d8[0][n].if;
                super.ee[0][n].do = super.d8[0][n].do;
                super.ee[0][n].a = super.d8[0][n].a;
            }
        }
    }
    
    protected boolean do(final int n, final int n2) {
        return super.dW != null && this.a(this.if(n, n2)) < 0L;
    }
    
    protected long a(final aq aq) {
        return super.dW.a((int)((super.ev - (float)Math.atan2(aq.if, aq.try)) * (super.dW.j / (super.ev - super.el))), (int)((super.d9 - (float)Math.atan(aq.byte / Math.sqrt(Math.pow(aq.try, 2.0) + Math.pow(aq.if, 2.0)))) * (super.dW.u / (super.d9 - super.d4))));
    }
}
