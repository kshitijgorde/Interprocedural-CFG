// 
// Decompiled by Procyon v0.5.30
// 

public class m extends i
{
    private o g;
    
    public void D(final o o) {
        super.D(o);
        super.p += o.p;
    }
    
    public void J() {
        this.g.J();
    }
    
    public l N() {
        return this.g.N();
    }
    
    public boolean P() {
        return this.g.P();
    }
    
    public void K() {
        if (this.g.q == this.g.p) {
            ++super.Z;
            if (super.Z == super.X) {
                super.Z = 0;
            }
            (this.g = super.Y[super.Z]).S();
        }
        this.g.K();
        super.j = this.g.j;
        ++super.q;
    }
    
    public void S() {
        super.q = 0;
        if (super.X > 0) {
            final o[] y = super.Y;
            final int z = 0;
            super.Z = z;
            (this.g = y[z]).S();
            super.j = this.g.j;
        }
    }
    
    public o M(final int n, final int n2) {
        return this.g.M(n, n2);
    }
}
