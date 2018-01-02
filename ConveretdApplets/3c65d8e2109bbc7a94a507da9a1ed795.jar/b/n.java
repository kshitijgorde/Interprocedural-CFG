// 
// Decompiled by Procyon v0.5.30
// 

package b;

public class n
{
    public static void a(final float[][] array, final float[][] array2, final int n, final int n2, final int n3, final float[][] array3, final float[][] array4, final float[] array5, final float[] array6) {
        final float[] array7 = new float[10];
        for (int i = 0; i < 5; ++i) {
            array7[i] = array[n][i] + array2[n2][i];
        }
        for (int j = 5; j < 10; ++j) {
            array7[j] = array[n][j] + array2[n3][j];
        }
        a(array7, 0.0012f);
        a(array7, 6.0E-4f);
        if(array7, array5, array3, array4, array6);
        a(array7, array4);
        a(array5);
    }
    
    public static void do(final float[] array, final float n) {
        for (int i = 1; i < 5; ++i) {
            final float n2 = (array[i - 1] - array[i] + n) * 0.5f;
            if (n2 > 0.0f) {
                final int n3 = i - 1;
                array[n3] -= n2;
                final int n4 = i;
                array[n4] += n2;
            }
        }
    }
    
    public static void if(final float[] array, final float n) {
        for (int i = 5; i < 10; ++i) {
            final float n2 = (array[i - 1] - array[i] + n) * 0.5f;
            if (n2 > 0.0f) {
                final int n3 = i - 1;
                array[n3] -= n2;
                final int n4 = i;
                array[n4] += n2;
            }
        }
    }
    
    public static void a(final float[] array, final float n) {
        for (int i = 1; i < 10; ++i) {
            final float n2 = (array[i - 1] - array[i] + n) * 0.5f;
            if (n2 > 0.0f) {
                final int n3 = i - 1;
                array[n3] -= n2;
                final int n4 = i;
                array[n4] += n2;
            }
        }
    }
    
    public static void if(final float[] array, final float[] array2, final float[][] array3, final float[][] array4, final float[] array5) {
        for (int i = 0; i < 10; ++i) {
            array2[i] = array[i] * array5[i];
            for (int j = 0; j < 4; ++j) {
                final int n = i;
                array2[n] += array4[j][i] * array3[j][i];
            }
        }
    }
    
    public static void a(final float[] array, final float[] array2, final float[][] array3, final float[][] array4, final float[] array5) {
        for (int i = 0; i < 10; ++i) {
            array2[i] = array[i];
            for (int j = 0; j < 4; ++j) {
                final int n = i;
                array2[n] -= array4[j][i] * array3[j][i];
            }
            final int n2 = i;
            array2[n2] *= array5[i];
        }
    }
    
    public static void a(final float[] array, final float[][] array2) {
        for (int i = 3; i > 0; --i) {
            j.a(array2[i - 1], array2[i], 10);
        }
        j.a(array, array2[0], 10);
    }
    
    public static void a(final float[] array) {
        for (int i = 0; i < 9; ++i) {
            if (array[i + 1] - array[i] < 0.0f) {
                final float n = array[i + 1];
                array[i + 1] = array[i];
                array[i] = n;
            }
        }
        if (array[0] < 0.005f) {
            array[0] = 0.005f;
            System.out.println("warning LSP Low \n");
        }
        for (int j = 0; j < 9; ++j) {
            if (array[j + 1] - array[j] < 0.0392f) {
                array[j + 1] = array[j] + 0.0392f;
            }
        }
        if (array[9] > 3.135f) {
            array[9] = 3.135f;
            System.out.println("warning LSP High \n");
        }
    }
}
