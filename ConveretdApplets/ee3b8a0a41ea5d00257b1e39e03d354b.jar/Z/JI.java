// 
// Decompiled by Procyon v0.5.30
// 

package Z;

class JI extends FI
{
    final int I(final I i, final Object o, final float[][] array, final int[] array2, final int n) {
        int n2 = 0;
        for (int j = 0; j < n; ++j) {
            if (array2[j] != 0) {
                array[n2++] = array[j];
            }
        }
        if (n2 != 0) {
            return FI.I(i, o, array, n2, 1);
        }
        return 0;
    }
}
