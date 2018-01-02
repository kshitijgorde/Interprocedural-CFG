// 
// Decompiled by Procyon v0.5.30
// 

package Z;

class SI extends FI
{
    final int I(final I i, final Object o, final float[][] array, final int[] array2, final int n) {
        int n2;
        for (n2 = 0; n2 < n && array2[n2] == 0; ++n2) {}
        if (n2 == n) {
            return 0;
        }
        return FI.I(i, o, array, n);
    }
}
