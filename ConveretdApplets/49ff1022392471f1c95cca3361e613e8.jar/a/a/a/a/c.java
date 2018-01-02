// 
// Decompiled by Procyon v0.5.30
// 

package a.a.a.a;

public class c extends ao
{
    private char[] e9;
    
    public c() {
        this.e9 = new char[] { 'p', 'a', 'n', 'o', 'C', 'y', 'l', 'i', 'n', 'd', 'e', 'r', '\0' };
    }
    
    public void a(final ac void1, final float cj, final ae goto1, final f cl, final m ck) {
        super.dR = 0.7853982f;
        super.dM = -super.dR;
        super.ed = 3.1415927f;
        super.d3 = -super.ed;
        super.void = void1;
        super.cj = cj;
        super.cm = 1.0f / super.cj;
        super.goto = goto1;
        super.cl = cl;
        super.ck = ck;
        super.else = super.ck.l;
        super.for = 1;
        super.byte = this.e9;
    }
    
    protected void r() {
        final int n = 17;
        super.d0 = (int)(n * 2 * super.void.ai / 100.0f);
        super.dY = (int)(n * super.void.ai / 100.0f);
        (super.dQ = new aq[1][])[0] = new aq[super.d0 * super.dY];
        (super.dW = new aq[1][])[0] = new aq[super.d0 * super.dY];
        final double tan = Math.tan(super.dR);
        final double tan2 = Math.tan(super.dM);
        for (int i = 0; i < super.dY; ++i) {
            for (int j = 0; j < super.d0; ++j) {
                final int n2 = j + i * super.d0;
                super.dQ[0][n2] = new aq();
                super.dW[0][n2] = new aq();
                final float n3 = super.ed + j * (-super.ed + super.d3) / (super.d0 - 1);
                super.dQ[0][n2].byte = (float)(tan + i * (-tan + tan2) / (super.dY - 1));
                super.dQ[0][n2].try = (float)Math.cos(n3);
                super.dQ[0][n2].if = (float)Math.sin(n3);
                super.dQ[0][n2].do = j * super.dE.i / (super.d0 - 1);
                super.dQ[0][n2].a = i * super.dE.t / (super.dY - 1);
                super.dW[0][n2].byte = super.dQ[0][n2].byte;
                super.dW[0][n2].try = super.dQ[0][n2].try;
                super.dW[0][n2].if = super.dQ[0][n2].if;
                super.dW[0][n2].do = super.dQ[0][n2].do;
                super.dW[0][n2].a = super.dQ[0][n2].a;
            }
        }
    }
    
    protected boolean do(final int n, final int n2) {
        return this.for(this.if(n, n2)) < 0L;
    }
    
    protected long for(final aq aq) {
        final float n = (float)Math.atan2(aq.if, aq.try);
        final float n2 = (float)Math.atan(aq.byte / Math.sqrt(Math.pow(aq.try, 2.0) + Math.pow(aq.if, 2.0)));
        final float n3 = super.dE.i / (super.ed - super.d3);
        return super.dE.a((int)((super.ed - n) * n3), (int)((super.dE.t >> 1) - Math.tan(n2) * n3 * super.dE.t / (float)(Math.tan(super.dR) * n3 - Math.tan(super.dM) * n3)));
    }
}
