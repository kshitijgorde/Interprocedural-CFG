// 
// Decompiled by Procyon v0.5.30
// 

package C;

public class C
{
    public byte[] Y;
    public byte[] U;
    public byte[] V;
    public int I;
    public int Z;
    
    C(final int n, final int n2) {
        final boolean b = false;
        this.Z = (b ? 1 : 0);
        this.I = (b ? 1 : 0);
        this.Y = new byte[n2 * n];
        this.U = new byte[(n2 >> 1) * (n >> 1)];
        this.V = new byte[this.U.length];
        for (int i = 0; i < this.U.length; ++i) {
            this.V[i] = (this.U[i] = -128);
        }
        for (int j = 0; j < n * n2; ++j) {
            this.Y[j] = -128;
        }
    }
}
