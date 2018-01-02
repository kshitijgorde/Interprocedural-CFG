// 
// Decompiled by Procyon v0.5.30
// 

package Z;

public class B
{
    private byte[][] I;
    private int[] Z;
    private int C;
    byte[] B;
    
    public final void I() {
        this.C = 0;
        this.I = null;
        this.B = null;
    }
    
    final int I(final Z z) {
        final int c = z.C(32);
        if (c < 0) {
            this.Z();
            return -1;
        }
        z.I(this.B = new byte[c + 1], c);
        this.C = z.C(32);
        if (this.C < 0) {
            this.Z();
            return -1;
        }
        this.I = new byte[this.C + 1][];
        this.Z = new int[this.C + 1];
        for (int i = 0; i < this.C; ++i) {
            final int c2 = z.C(32);
            if (c2 < 0) {
                this.Z();
                return -1;
            }
            this.Z[i] = c2;
            z.I(this.I[i] = new byte[c2 + 1], c2);
        }
        if (z.C(1) != 1) {
            this.Z();
            return -1;
        }
        return 0;
    }
    
    private void Z() {
        for (int i = 0; i < this.C; ++i) {
            this.I[i] = null;
        }
        this.I = null;
        this.B = null;
    }
}
