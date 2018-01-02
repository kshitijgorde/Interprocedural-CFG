// 
// Decompiled by Procyon v0.5.30
// 

public class ao extends an
{
    public int a(final v v, final Object o, final float[][] array, final int[] array2, final int n) {
        int n2 = 0;
        for (int i = 0; i < n; ++i) {
            if (array2[i] != 0) {
                array[n2++] = array[i];
            }
        }
        if (n2 != 0) {
            return an.a(v, o, array, n2, 1);
        }
        return 0;
    }
}
