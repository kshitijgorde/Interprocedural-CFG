// 
// Decompiled by Procyon v0.5.30
// 

package anon.mixminion.fec;

import anon.util.ByteArrayUtil;

public class PureCode extends FECCode
{
    public static final int FEC_MAGIC = -20181524;
    protected static final FECMath fecMath;
    protected char[] encMatrix;
    
    public PureCode(final int n, final int n2) {
        this(n, n2, PureCode.fecMath.createEncodeMatrix(n, n2));
    }
    
    public PureCode(final int n, final int n2, final char[] encMatrix) {
        super(n, n2);
        this.encMatrix = encMatrix;
    }
    
    public void encode(final byte[][] array, final int[] array2, final byte[][] array3, final int[] array4, final int[] array5, final int n) {
        for (int i = 0; i < array3.length; ++i) {
            this.encode(array, array2, array3[i], array4[i], array5[i], n);
        }
    }
    
    protected void encode(final byte[][] array, final int[] array2, final byte[] array3, final int n, final int n2, final int n3) {
        if (n2 < super.k) {
            System.arraycopy(array[n2], array2[n2], array3, n, n3);
        }
        else {
            final int n4 = n2 * super.k;
            ByteArrayUtil.bzero(array3, n, n3);
            for (int i = 0; i < super.k; ++i) {
                PureCode.fecMath.addMul(array3, n, array[i], array2[i], (byte)this.encMatrix[n4 + i], n3);
            }
        }
    }
    
    public void decode(final byte[][] array, final int[] array2, final int[] array3, final int n, final boolean b) {
        if (!b) {
            FECCode.shuffle(array, array2, array3, super.k);
        }
        final char[] decodeMatrix = PureCode.fecMath.createDecodeMatrix(this.encMatrix, array3, super.k, super.n);
        final byte[][] array4 = new byte[super.k][];
        for (int i = 0; i < super.k; ++i) {
            if (array3[i] >= super.k) {
                array4[i] = new byte[n];
                for (int j = 0; j < super.k; ++j) {
                    PureCode.fecMath.addMul(array4[i], 0, array[j], array2[j], (byte)decodeMatrix[i * super.k + j], n);
                }
            }
        }
        for (int k = 0; k < super.k; ++k) {
            if (array3[k] >= super.k) {
                System.arraycopy(array4[k], 0, array[k], array2[k], n);
                array3[k] = k;
            }
        }
    }
    
    public String toString() {
        return new String("PureCode[k=" + super.k + ",n=" + super.n + "]");
    }
    
    static {
        fecMath = new FECMath(8);
    }
}
