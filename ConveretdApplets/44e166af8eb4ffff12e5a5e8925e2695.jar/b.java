// 
// Decompiled by Procyon v0.5.30
// 

public class b extends i
{
    public b(final int l, final int m) {
        super.l = l;
        super.m = m;
    }
    
    public void D(final o o) {
        super.D(o);
        super.j += o.j;
        super.n += o.n;
        super.o = Math.max(super.o, o.o);
        this.O(super.l, super.m);
    }
    
    public void O(final int l, final int m) {
        int n = l - (super.n >> 1);
        for (int i = 0; i < super.X; ++i) {
            final int n3;
            final int n2 = n + (n3 = super.Y[i].n >> 1);
            super.Y[i].O(n2, m);
            n = n2 + n3;
        }
        super.l = l;
        super.m = m;
    }
}
