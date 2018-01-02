// 
// Decompiled by Procyon v0.5.30
// 

class ba
{
    static final float a = 3.1415927f;
    static final float b = 6.2831855f;
    static final float c = 1.5707964f;
    static final float d = 1.0E-4f;
    
    static final float a(final float n, final float n2, final float n3) {
        return Math.min(Math.max(n, n2), n3);
    }
    
    static final float b(final float n, final float n2, final float n3) {
        return (n - n2) % (n3 - n2) + n2;
    }
    
    static final float[] a(final float[] array, final float[] array2) {
        if (array[0] - array2[0] > 3.1415927f) {
            final int n = 0;
            array[n] -= 6.2831855f;
        }
        else if (array2[0] - array[0] > 3.1415927f) {
            final int n2 = 0;
            array[n2] += 6.2831855f;
        }
        if (array[2] - array2[2] > 3.1415927f) {
            final int n3 = 2;
            array[n3] -= 6.2831855f;
        }
        else if (array2[2] - array[2] > 3.1415927f) {
            final int n4 = 2;
            array[n4] += 6.2831855f;
        }
        final float[] array3 = new float[array.length];
        array3[0] = array2[0] - array[0];
        array3[1] = array2[1] - array[1];
        array3[2] = array2[2] - array[2];
        array3[3] = (float)(Math.log(array2[3]) - Math.log(array[3]));
        return array3;
    }
    
    static final float b(final float[] array, final float[] array2) {
        return array[0] * array2[0] + array[1] * array2[1] + array[2] * array2[2];
    }
    
    static final void a(final float[] array, final float[] array2, final float[] array3) {
        array3[0] = array[1] * array2[2] - array[2] * array2[1];
        array3[1] = array[2] * array2[0] - array[0] * array2[2];
        array3[2] = array[0] * array2[1] - array[1] * array2[0];
    }
    
    static final boolean c(final float[] array, final float[] array2) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i] != array2[i]) {
                return false;
            }
        }
        return true;
    }
    
    static final float[] a(final float[] array) {
        final float[] array2 = new float[array.length];
        System.arraycopy(array, 0, array2, 0, array.length);
        return array2;
    }
}
