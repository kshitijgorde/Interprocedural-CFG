// 
// Decompiled by Procyon v0.5.30
// 

package anon.mixminion.fec;

public abstract class FECCode
{
    protected int k;
    protected int n;
    
    protected FECCode(final int k, final int n) {
        this.k = k;
        this.n = n;
    }
    
    public abstract void encode(final byte[][] p0, final int[] p1, final byte[][] p2, final int[] p3, final int[] p4, final int p5);
    
    public abstract void decode(final byte[][] p0, final int[] p1, final int[] p2, final int p3, final boolean p4);
    
    protected static final void shuffle(final byte[][] array, final int[] array2, final int[] array3, final int n) {
        int i = 0;
        while (i < n) {
            if (array3[i] >= n || array3[i] == i) {
                ++i;
            }
            else {
                final int n2 = array3[i];
                if (array3[n2] == n2) {
                    throw new IllegalArgumentException("Shuffle error at " + i);
                }
                final byte[] array4 = array[i];
                array[i] = array[n2];
                array[n2] = array4;
                final int n3 = array2[i];
                array2[i] = array2[n2];
                array2[n2] = n3;
                final int n4 = array3[i];
                array3[i] = array3[n2];
                array3[n2] = n4;
            }
        }
    }
}
