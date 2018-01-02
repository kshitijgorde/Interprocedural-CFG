// 
// Decompiled by Procyon v0.5.30
// 

package anon.mixminion.fec;

import anon.util.ByteArrayUtil;

public class Pure16Code extends PureCode
{
    protected static final FECMath fecMath;
    
    public Pure16Code(final int n, final int n2) {
        super(n, n2, Pure16Code.fecMath.createEncodeMatrix(n, n2));
    }
    
    public void encode(final byte[][] array, final int[] array2, final byte[][] array3, final int[] array4, final int[] array5, final int n) {
        if (n % 2 != 0) {
            throw new IllegalArgumentException("For 16 bit codes, buffers must be 16 bit aligned.");
        }
        final char[][] array6 = new char[array.length][];
        final int[] array7 = new int[array.length];
        final int n2 = n / 2;
        final char[] array8 = new char[n2];
        for (int i = 0; i < array6.length; ++i) {
            array6[i] = new char[n2];
            ByteArrayUtil.byteArrayToCharArray(array[i], array2[i], array6[i], 0, n);
            array7[i] = 0;
        }
        for (int j = 0; j < array3.length; ++j) {
            if (array5[j] < super.k) {
                System.arraycopy(array[array5[j]], array2[array5[j]], array3[j], array4[j], n);
            }
            else {
                this.encode(array6, array7, array8, 0, array5[j], n2);
                ByteArrayUtil.charArrayToByteArray(array8, 0, array3[j], array4[j], n);
            }
        }
    }
    
    protected void encode(final char[][] array, final int[] array2, final char[] array3, final int n, final int n2, final int n3) {
        final int n4 = n2 * super.k;
        ByteArrayUtil.bzero(array3, n, n3);
        for (int i = 0; i < super.k; ++i) {
            Pure16Code.fecMath.addMul(array3, n, array[i], array2[i], super.encMatrix[n4 + i], n3);
        }
    }
    
    public void decode(final byte[][] array, final int[] array2, final int[] array3, final int n, final boolean b) {
        if (n % 2 != 0) {
            throw new IllegalArgumentException("For 16 bit codes, buffers must be 16 bit aligned.");
        }
        if (!b) {
            FECCode.shuffle(array, array2, array3, super.k);
        }
        final char[][] array4 = new char[array.length][];
        final int[] array5 = new int[array.length];
        final int n2 = n / 2;
        for (int i = 0; i < array4.length; ++i) {
            array4[i] = new char[n2];
            ByteArrayUtil.byteArrayToCharArray(array[i], array2[i], array4[i], 0, n);
            array5[i] = 0;
        }
        final char[][] decode = this.decode(array4, array5, array3, n2);
        for (int j = 0; j < decode.length; ++j) {
            if (decode[j] != null) {
                ByteArrayUtil.charArrayToByteArray(decode[j], 0, array[j], array2[j], n);
                array3[j] = j;
            }
        }
    }
    
    protected char[][] decode(final char[][] array, final int[] array2, final int[] array3, final int n) {
        final char[] decodeMatrix = Pure16Code.fecMath.createDecodeMatrix(super.encMatrix, array3, super.k, super.n);
        final char[][] array4 = new char[super.k][];
        for (int i = 0; i < super.k; ++i) {
            if (array3[i] >= super.k) {
                array4[i] = new char[n];
                for (int j = 0; j < super.k; ++j) {
                    Pure16Code.fecMath.addMul(array4[i], 0, array[j], array2[j], decodeMatrix[i * super.k + j], n);
                }
            }
        }
        return array4;
    }
    
    public String toString() {
        return new String("Pure16Code[k=" + super.k + ",n=" + super.n + "]");
    }
    
    static {
        fecMath = new FECMath(16);
    }
}
