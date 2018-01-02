// 
// Decompiled by Procyon v0.5.30
// 

public class i extends o
{
    protected int X;
    protected o[] Y;
    protected int Z;
    
    public i() {
        this.Y = new o[100];
    }
    
    public void D(final o o) {
        this.Y[this.X++] = o;
    }
    
    public void J() {
        super.r = 0;
        final o[] y = this.Y;
        final int z = 0;
        this.Z = z;
        y[z].J();
    }
    
    public l N() {
        ++super.r;
        while (!this.Y[this.Z].P()) {
            this.Y[++this.Z].J();
        }
        return this.Y[this.Z].N();
    }
    
    public void K() {
        ++super.q;
        super.j = 0;
        for (int i = 0; i < this.X; ++i) {
            this.Y[i].K();
            super.j += this.Y[i].j;
        }
    }
    
    public void S() {
        super.q = 0;
        super.j = 0;
        for (int i = 0; i < this.X; ++i) {
            this.Y[i].S();
            super.j += this.Y[i].j;
        }
    }
    
    public void Q(final a s) {
        super.s = s;
        for (int i = 0; i < this.X; ++i) {
            this.Y[i].Q(s);
        }
    }
    
    public void L(final String s, final String s2) {
        for (int i = 0; i < this.X; ++i) {
            this.Y[i].L(s, s2);
        }
    }
    
    public o M(final int n, final int n2) {
        for (int i = 0; i < this.X; ++i) {
            final o m = this.Y[i].M(n, n2);
            if (m != null) {
                return m;
            }
        }
        return null;
    }
}
