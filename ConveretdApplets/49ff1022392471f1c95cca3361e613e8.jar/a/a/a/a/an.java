// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class an extends ao
{
    private char[] e1;
    
    public an() {
        this.e1 = new char[] { 'p', 'a', 'n', 'o', 'S', 'p', 'h', 'e', 'r', 'e', '\0' };
    }
    
    public void a(final ac void1, final float cj, final ae goto1, final f cl, final m ck) {
        super.dR = 1.5707964f;
        super.dM = -super.dR;
        super.ed = 3.1415927f;
        super.d3 = -super.ed;
        super.void = void1;
        super.cj = cj;
        super.cm = 1.0f / super.cj;
        super.goto = goto1;
        super.cl = cl;
        super.ck = ck;
        super.else = ck.l;
        super.for = 1;
        super.byte = this.e1;
    }
    
    protected void r() {
        final int dy = (int)(25.0f * super.void.ai / 100.0f);
        super.d0 = (int)(dy * 2.5);
        super.dY = dy;
        (super.dQ = new aq[1][])[0] = new aq[super.d0 * super.dY];
        (super.dW = new aq[1][])[0] = new aq[super.d0 * super.dY];
        for (int i = 0; i < super.dY; ++i) {
            for (int j = 0; j < super.d0; ++j) {
                final int n = j + i * super.d0;
                super.dQ[0][n] = new aq();
                super.dW[0][n] = new aq();
                final float n2 = super.ed + j * (-super.ed + super.d3) / (super.d0 - 1);
                super.dQ[0][n].do = j * super.dE.i / (super.d0 - 1);
                final int n3 = (super.dY - 4) * 4;
                int n4;
                if (i == 0) {
                    n4 = 0;
                }
                else if (i == super.dY - 1) {
                    n4 = n3 - 1;
                }
                else if (i == 1) {
                    n4 = 1;
                }
                else if (i == 2) {
                    n4 = 2;
                }
                else if (i == super.dY - 2) {
                    n4 = n3 - 2;
                }
                else if (i == super.dY - 3) {
                    n4 = n3 - 4;
                }
                else {
                    n4 = (i - 2) * 4;
                }
                final float n5 = super.dR + n4 * (-super.dR + super.dM) / (n3 - 1);
                super.dQ[0][n].a = n4 * super.dE.t / (n3 - 1);
                super.dQ[0][n].byte = (float)Math.sin(n5);
                super.dQ[0][n].try = (float)(Math.cos(n2) * Math.cos(n5));
                super.dQ[0][n].if = (float)(Math.sin(n2) * Math.cos(n5));
                super.dW[0][n].byte = super.dQ[0][n].byte;
                super.dW[0][n].try = super.dQ[0][n].try;
                super.dW[0][n].if = super.dQ[0][n].if;
                super.dW[0][n].do = super.dQ[0][n].do;
                super.dW[0][n].a = super.dQ[0][n].a;
            }
        }
    }
    
    protected boolean do(final int n, final int n2) {
        return super.dE != null && this.a(this.if(n, n2)) < 0L;
    }
    
    protected long a(final aq aq) {
        return super.dE.a((int)((super.ed - (float)Math.atan2(aq.if, aq.try)) * (super.dE.i / (super.ed - super.d3))), (int)((super.dR - (float)Math.atan(aq.byte / Math.sqrt(Math.pow(aq.try, 2.0) + Math.pow(aq.if, 2.0)))) * (super.dE.t / (super.dR - super.dM))));
    }
}
