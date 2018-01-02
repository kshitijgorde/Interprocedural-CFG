// 
// Decompiled by Procyon v0.5.30
// 

package org.kim.cadclick.pr3d;

public final class k
{
    public static void if(final float[] array, final float[] array2, final float[] array3, final float[] array4) {
        if (array == null || array2 == null || array3 == null || array4 == null) {
            return;
        }
        for (int i = 0; i < 3; ++i) {
            array[i] = array3[i] - array4[i] / 2.0f;
            array2[i] = array3[i] + array4[i] / 2.0f;
        }
    }
    
    public static void a(final float[] array, final float[] array2, final float[] array3, final float[] array4) {
        if (array == null || array2 == null || array3 == null || array4 == null) {
            return;
        }
        final float[] array5 = new float[3];
        final float[] array6 = new float[3];
        if(array5, array6, array3, array4);
        if (array[0] > array5[0]) {
            array[0] = array5[0];
        }
        if (array[1] > array5[1]) {
            array[1] = array5[1];
        }
        if (array[2] > array5[2]) {
            array[2] = array5[2];
        }
        if (array2[0] < array6[0]) {
            array2[0] = array6[0];
        }
        if (array2[1] < array6[1]) {
            array2[1] = array6[1];
        }
        if (array2[2] < array6[2]) {
            array2[2] = array6[2];
        }
    }
    
    public static void a(final float[] array, final float[] array2, final float[] array3) {
        if (array == null || array2 == null || array3 == null) {
            return;
        }
        final int length = array3.length;
        int i = 0;
        float n2;
        float n = n2 = array3[i];
        float n4;
        float n3 = n4 = array3[i + 1];
        float n6;
        float n5 = n6 = array3[i + 2];
        i += 3;
        while (i < length) {
            final float n7 = array3[i++];
            if (n7 > n2) {
                n2 = n7;
            }
            if (n7 < n) {
                n = n7;
            }
            final float n8 = array3[i++];
            if (n8 > n4) {
                n4 = n8;
            }
            if (n8 < n3) {
                n3 = n8;
            }
            final float n9 = array3[i++];
            if (n9 > n6) {
                n6 = n9;
            }
            if (n9 < n5) {
                n5 = n9;
            }
        }
        array[0] = (n2 + n) / 2.0f;
        array[1] = (n4 + n3) / 2.0f;
        array[2] = (n6 + n5) / 2.0f;
        array2[0] = Math.abs(n2 - n);
        array2[1] = Math.abs(n4 - n3);
        array2[2] = Math.abs(n6 - n5);
    }
    
    public static float[] a(final float n, final float n2, final float n3, final float[] array, final boolean b) {
        final float[] array2 = { n * array[0] + n2 * array[3] + n3 * array[6], n * array[1] + n2 * array[4] + n3 * array[7], n * array[2] + n2 * array[5] + n3 * array[8] };
        if (b) {
            final float[] array3 = array2;
            final int n4 = 0;
            array3[n4] += array[9];
            final float[] array4 = array2;
            final int n5 = 1;
            array4[n5] += array[10];
            final float[] array5 = array2;
            final int n6 = 2;
            array5[n6] += array[11];
        }
        return array2;
    }
    
    public static void a(final float[] array, final boolean b) {
        final float n = b ? 1 : -1;
        final float n2 = (float)Math.sqrt(array[0] * array[0] + array[1] * array[1] + array[2] * array[2]);
        if (n2 != 0.0f) {
            final float n3 = n / n2;
            final int n4 = 0;
            array[n4] *= n3;
            final int n5 = 1;
            array[n5] *= n3;
            final int n6 = 2;
            array[n6] *= n3;
        }
    }
    
    public static float[] a(final float[] array, final float[] array2, final boolean b) {
        final float[] array3 = { array[1] * array2[2] - array[2] * array2[1], array[2] * array2[0] - array[0] * array2[2], array[0] * array2[1] - array[1] * array2[0] };
        a(array3, b);
        return array3;
    }
    
    public static float a(final float[] array, final float[] array2) {
        return (float)Math.acos((array[0] * array2[0] + array[1] * array2[1] + array[2] * array2[2]) / ((float)Math.sqrt(array[0] * array[0] + array[1] * array[1] + array[2] * array[2]) * (float)Math.sqrt(array2[0] * array2[0] + array2[1] * array2[1] + array2[2] * array2[2])));
    }
    
    public static float[] if(final float[] array) {
        final float[] array2 = { 0.0f, -1.0f, 0.0f, 3.1415927f };
        final float n = (float)Math.acos((array[0] + array[4] + array[8] - 1.0f) / 2.0);
        final float n2 = 2.0f * (float)Math.sin(n);
        if (n2 != 0.0f) {
            array2[0] = (array[5] - array[7]) / n2;
            array2[1] = (array[6] - array[2]) / n2;
            array2[2] = (array[1] - array[3]) / n2;
            array2[3] = n;
        }
        if (Float.isNaN(n2)) {
            array2[0] = 0.0f;
            array2[1] = -1.0f;
            array2[2] = 0.0f;
            array2[3] = 3.1415927f;
        }
        return array2;
    }
    
    public static final float[] if(final float[] array, final float[] array2) {
        final float[] array3 = { array[0], array[3], array[6], array[1], array[4], array[7], array[2], array[5], array[8], -array[9], -array[10], -array[11] };
        if (array2 != null) {
            System.arraycopy(array3, 0, array2, 0, 12);
        }
        return array3;
    }
    
    public static final float[] do(final float[] array, final float[] array2, final float[] array3) {
        final float[] array4 = { array[0] * array2[0] + array[1] * array2[3] + array[2] * array2[6], array[0] * array2[1] + array[1] * array2[4] + array[2] * array2[7], array[0] * array2[2] + array[1] * array2[5] + array[2] * array2[8], array[3] * array2[0] + array[4] * array2[3] + array[5] * array2[6], array[3] * array2[1] + array[4] * array2[4] + array[5] * array2[7], array[3] * array2[2] + array[4] * array2[5] + array[5] * array2[8], array[6] * array2[0] + array[7] * array2[3] + array[8] * array2[6], array[6] * array2[1] + array[7] * array2[4] + array[8] * array2[7], array[6] * array2[2] + array[7] * array2[5] + array[8] * array2[8], array[9] * array2[0] + array[10] * array2[3] + array[11] * array2[6] + array2[9], array[9] * array2[1] + array[10] * array2[4] + array[11] * array2[7] + array2[10], array[9] * array2[2] + array[10] * array2[5] + array[11] * array2[8] + array2[11] };
        if (array3 != null) {
            System.arraycopy(array4, 0, array3, 0, 12);
        }
        return array4;
    }
    
    public static final void a(final float n, final float n2, final float n3, final float n4, final float[] array) {
        final float n5 = (float)Math.sqrt(n * n + n2 * n2 + n3 * n3);
        if (n5 < 1.0E-8) {
            a(array);
            return;
        }
        final float n6 = n / n5;
        final float n7 = n2 / n5;
        final float n8 = n3 / n5;
        final float n9 = (float)Math.sin(-n4);
        final float n10 = (float)Math.cos(-n4);
        final float n11 = 1.0f - n10;
        final float n12 = n6 * n8;
        final float n13 = n6 * n7;
        final float n14 = n7 * n8;
        array[0] = n11 * n6 * n6 + n10;
        array[1] = n11 * n13 - n9 * n8;
        array[2] = n11 * n12 + n9 * n7;
        array[3] = n11 * n13 + n9 * n8;
        array[4] = n11 * n7 * n7 + n10;
        array[5] = n11 * n14 - n9 * n6;
        array[6] = n11 * n12 - n9 * n7;
        array[7] = n11 * n14 + n9 * n6;
        array[8] = n11 * n8 * n8 + n10;
    }
    
    public static float[] do(final float[] array, final float[] array2) {
        final float[] array3 = new float[12];
        a(array2[0], array2[1], array2[2], array2[3], array3);
        final float[] array4 = new float[12];
        a(array[0], array[1], array[2], array[3], array4);
        return if(array3, array4, new float[] { 0.0f, 0.0f, 1.0f });
    }
    
    public static float[] for(final float[] array, final float[] array2, final float[] array3) {
        final float[] array4 = new float[12];
        a(array[0], array[1], array[2], array[3], array4);
        final float[] array5 = new float[12];
        a(array3[0], array3[1], array3[2], array3[3], array5);
        final float[] array6 = new float[12];
        do(array5, array4, array6);
        final float[] array7 = new float[12];
        a(array2[0], array2[1], array2[2], array2[3], array7);
        return if(array6, array7, new float[] { -1.0f, 0.0f, 0.0f });
    }
    
    public static float[] if(final float[] array, final float[] array2, final float[] array3) {
        final float[] a = a(array3[0], array3[1], array3[2], array, true);
        final float[] a2 = a(array3[0], array3[1], array3[2], array2, true);
        final float[] a3 = a(a, a2, true);
        final float[] array4 = new float[4];
        System.arraycopy(a3, 0, array4, 0, 3);
        float a4 = a(a, a2);
        if (a4 > 3.1415927f) {
            a4 -= 3.1415927f;
            final float[] array5 = array4;
            final int n = 0;
            array5[n] *= -1.0f;
            final float[] array6 = array4;
            final int n2 = 1;
            array6[n2] *= -1.0f;
            final float[] array7 = array4;
            final int n3 = 2;
            array7[n3] *= -1.0f;
        }
        array4[3] = a4;
        return array4;
    }
    
    public static final void a(final float[] array) {
        System.arraycopy(new float[] { 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f }, 0, array, 0, 12);
    }
    
    public static final float[] a(final float n, final int n2, final int n3) {
        final float[] array = new float[12];
        final float n4 = (float)Math.sqrt(n2 * n2 + n3 * n3);
        final float n5 = (float)Math.sqrt(n * n + n4 * n4);
        final float n6 = n / n5;
        final float n7 = n4 / n5;
        array[0] = n6 + n3 / n4 * (n3 / n4) * (1.0f - n6);
        array[1] = -1.0f * (n2 / n4) * (n3 / n4) * (1.0f - n6);
        array[2] = n2 / n4 * n7;
        array[3] = -1.0f * (n2 / n4) * (n3 / n4) * (1.0f - n6);
        array[4] = n6 + n2 / n4 * (n2 / n4) * (1.0f - n6);
        array[5] = n3 / n4 * n7;
        array[6] = -1.0f * (n2 / n4) * n7;
        array[7] = -1.0f * (n3 / n4) * n7;
        array[8] = n6;
        return array;
    }
}
