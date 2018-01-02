// 
// Decompiled by Procyon v0.5.30
// 

package shout3d.math;

import shout3d.core.Group;
import shout3d.core.Node;

public class MatUtil
{
    private float a;
    private float b;
    private float c;
    private boolean d;
    static float e;
    static float f;
    static float g;
    static float h;
    static float i;
    static float j;
    static float k;
    static float l;
    static float m;
    static float n;
    static float o;
    static float p;
    static float q;
    static float r;
    static float[] s;
    
    public static final void composeQuats(final float[] array, final float[] array2, final float[] array3) {
        array3[0] = array[3] * array2[0] + array2[3] * array[0] + array[1] * array2[2] - array[2] * array2[1];
        array3[1] = array[3] * array2[1] + array2[3] * array[1] - array[0] * array2[2] + array[2] * array2[0];
        array3[2] = array[3] * array2[2] + array2[3] * array[2] + array[0] * array2[1] - array[1] * array2[0];
        array3[3] = array[3] * array2[3] - array[0] * array2[0] - array[1] * array2[1] - array[2] * array2[2];
        if (array3[3] < 0.0) {
            final int n = 3;
            array3[n] *= (float)(-1.0);
            final int n2 = 0;
            array3[n2] *= (float)(-1.0);
            final int n3 = 1;
            array3[n3] *= (float)(-1.0);
            final int n4 = 2;
            array3[n4] *= (float)(-1.0);
        }
    }
    
    public static final float normalize(final float[] array) {
        return a(array, 0);
    }
    
    public static final float dot(final float[] array, final float[] array2) {
        return array[0] * array2[0] + array[1] * array2[1] + array[2] * array2[2];
    }
    
    public static final float a(final float[] array, final int n) {
        MatUtil.g = array[n] * array[n] + array[n + 1] * array[n + 1] + array[n + 2] * array[n + 2];
        if (MatUtil.g == 1.0f) {
            return 1.0f;
        }
        if (MatUtil.g > 0.0f) {
            MatUtil.e = (float)Math.sqrt(MatUtil.g);
            MatUtil.f = 1.0f / MatUtil.e;
        }
        else {
            MatUtil.e = 0.0f;
            MatUtil.f = 0.0f;
        }
        array[n] *= MatUtil.f;
        final int n2 = n + 1;
        array[n2] *= MatUtil.f;
        final int n3 = n + 2;
        array[n3] *= MatUtil.f;
        return MatUtil.e;
    }
    
    public static final void a(final float[] array, final float[] array2) {
        array2[0] = array[0];
        array2[1] = array[1];
        array2[2] = array[2];
        array2[3] = array[4];
        array2[4] = array[5];
        array2[5] = array[6];
        array2[6] = array[8];
        array2[7] = array[9];
        array2[8] = array[10];
    }
    
    protected static final void a(final float[] array) {
        array[0] = 1.0f;
        array[1] = 0.0f;
        array[3] = (array[2] = 0.0f);
        array[4] = 1.0f;
        array[5] = 0.0f;
        array[7] = (array[6] = 0.0f);
        array[8] = 1.0f;
    }
    
    private static int a(final float[] array, final float n, int i, int n2) {
        while (i <= n2) {
            final int n3 = (i + n2) / 2;
            final float n4 = array[n3];
            int n5;
            if (n4 < n) {
                n5 = -1;
            }
            else if (n4 > n) {
                n5 = 1;
            }
            else {
                final int floatToIntBits = Float.floatToIntBits(n4);
                final int floatToIntBits2 = Float.floatToIntBits(n);
                n5 = ((floatToIntBits == floatToIntBits2) ? 0 : ((floatToIntBits < floatToIntBits2) ? -1 : 1));
            }
            if (n5 < 0) {
                i = n3 + 1;
            }
            else {
                if (n5 <= 0) {
                    return n3;
                }
                n2 = n3 - 1;
            }
        }
        return -(i + 1);
    }
    
    public static final void b(final float[] array, final float[] array2) {
        a(array, array2, array2);
    }
    
    public static final void a(final float[] array, final float[] array2, final float[] array3) {
        for (int i = 0, n = 1, n2 = 2; i < array2.length; i += 3, n += 3, n2 += 3) {
            final float n3 = array2[i];
            final float n4 = array2[n];
            final float n5 = array2[n2];
            array3[i] = n3 * array[0] + n4 * array[3] + n5 * array[6] + array[9];
            array3[n] = n3 * array[1] + n4 * array[4] + n5 * array[7] + array[10];
            array3[n2] = n3 * array[2] + n4 * array[5] + n5 * array[8] + array[11];
        }
    }
    
    public static final void c(final float[] array, final float[] array2) {
        array2[0] = array[0];
        array2[1] = array[1];
        array2[2] = array[2];
        array2[3] = array[4];
        array2[4] = array[5];
        array2[5] = array[6];
        array2[6] = array[8];
        array2[7] = array[9];
        array2[8] = array[10];
        array2[9] = array[12];
        array2[10] = array[13];
        array2[11] = array[14];
    }
    
    private static void a(final float[] array, final Object[] array2, final int n, final int n2) {
        final float n3 = array[n];
        array[n] = array[n2];
        array[n2] = n3;
        if (array2 != null) {
            final Object o = array2[n];
            array2[n] = array2[n2];
            array2[n2] = o;
        }
    }
    
    private static void a(final float[] array, final Object[] array2, int n, int n2, final int n3) {
        for (int i = 0; i < n3; ++i, ++n, ++n2) {
            a(array, array2, n, n2);
        }
    }
    
    public static final void b(final float[] array) {
        a(array);
        array[9] = 0.0f;
        array[11] = (array[10] = 0.0f);
    }
    
    public static final boolean d(final float[] array, final float[] array2) {
        return g(array, array2);
    }
    
    protected static final void e(final float[] array, final float[] array2) {
        array2[0] = array[0];
        array2[1] = array[1];
        array2[2] = array[2];
        array2[3] = 0.0f;
        array2[4] = array[3];
        array2[5] = array[4];
        array2[6] = array[5];
        array2[7] = 0.0f;
        array2[8] = array[6];
        array2[9] = array[7];
        array2[10] = array[8];
        array2[11] = 0.0f;
        array2[12] = array[9];
        array2[13] = array[10];
        array2[14] = array[11];
        array2[15] = 1.0f;
    }
    
    private static int a(final float[] array, final int n, final int n2, final int n3) {
        if (array[n] < array[n2]) {
            if (array[n2] < array[n3]) {
                return n2;
            }
            if (array[n] < array[n3]) {
                return n3;
            }
            return n;
        }
        else {
            if (array[n2] > array[n3]) {
                return n2;
            }
            if (array[n] > array[n3]) {
                return n3;
            }
            return n;
        }
    }
    
    public static final void axisToQuat(final float[] array, final float[] array2) {
        final float n = (float)Math.sin(array[3] * 0.5f);
        array2[0] = array[0] * n;
        array2[1] = array[1] * n;
        array2[2] = array[2] * n;
        array2[3] = (float)Math.cos(array[3] * 0.5f);
    }
    
    public static float[] a(final Node[] array, final boolean b) {
        float[] array2 = { 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f };
        if (array == null) {
            return array2;
        }
        if (b) {
            for (int i = array.length - 1; i >= 0; --i) {
                if (array[i] instanceof Group) {
                    array2 = h(array2, ((Group)array[i]).b());
                }
            }
        }
        else {
            for (int j = 0; j < array.length; ++j) {
                if (array[j] instanceof Group) {
                    array2 = h(array2, ((Group)array[j]).c());
                }
            }
        }
        return array2;
    }
    
    public static final void f(final float[] array, final float[] array2) {
        array2[0] = array[0];
        array2[1] = array[3];
        array2[2] = array[6];
        array2[3] = array[1];
        array2[4] = array[4];
        array2[5] = array[7];
        array2[6] = array[2];
        array2[7] = array[5];
        array2[8] = array[8];
    }
    
    public static float[] getMatrixAlongPath(final Node[] array, final boolean b) {
        final float[] a = a(array, b);
        final float[] array2 = { 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f, 0.0f, 0.0f, 0.0f, 0.0f, 1.0f };
        e(a, array2);
        return array2;
    }
    
    public static final void multDirMatrix(final float[] array, final float[] array2) {
        b(array, array2, array2);
    }
    
    public static final void b(final float[] array, final float[] array2, final float[] array3) {
        for (int i = 0, n = 1, n2 = 2; i < array2.length; i += 3, n += 3, n2 += 3) {
            final float n3 = array2[i];
            final float n4 = array2[n];
            final float n5 = array2[n2];
            array3[i] = n3 * array[0] + n4 * array[4] + n5 * array[8];
            array3[n] = n3 * array[1] + n4 * array[5] + n5 * array[9];
            array3[n2] = n3 * array[2] + n4 * array[6] + n5 * array[10];
        }
    }
    
    public static void b(final float[] array, final Object[] array2, final int n, int n2) {
        final int floatToIntBits = Float.floatToIntBits(-0.0f);
        int n3 = 0;
        int i = n;
        while (i < n2) {
            if (array[i] != array[i]) {
                array[i] = array[--n2];
                array[n2] = Float.NaN;
            }
            else {
                if (array[i] == 0.0f && Float.floatToIntBits(array[i]) == floatToIntBits) {
                    array[i] = 0.0f;
                    ++n3;
                }
                ++i;
            }
        }
        c(array, array2, n, n2 - n);
        if (n3 != 0) {
            int a = a(array, 0.0f, n, n2 - 1);
            while (--a >= 0 && array[a] == 0.0f) {}
            for (int j = 0; j < n3; ++j) {
                array[++a] = -0.0f;
            }
        }
    }
    
    public static final void quatToAxis(final float[] array, final float[] array2) {
        final float n = (float)Math.acos(array[3]);
        final float n2 = (float)Math.sin(n);
        if (n2 > -1.0E-6f && n2 < 1.0E-6f) {
            array2[1] = (array2[0] = 0.0f);
            array2[2] = 1.0f;
            array2[3] = 0.0f;
            return;
        }
        array2[0] = array[0] / n2;
        array2[1] = array[1] / n2;
        array2[2] = array[2] / n2;
        array2[3] = 2.0f * n;
    }
    
    private static void c(final float[] array, final Object[] array2, final int n, final int n2) {
        if (n2 < 7) {
            for (int i = n; i < n2 + n; ++i) {
                for (int n3 = i; n3 > n && array[n3 - 1] > array[n3]; --n3) {
                    a(array, array2, n3, n3 - 1);
                }
            }
            return;
        }
        int n4 = n + n2 / 2;
        if (n2 > 7) {
            int a = n;
            int a2 = n + n2 - 1;
            if (n2 > 40) {
                final int n5 = n2 / 8;
                a = a(array, a, a + n5, a + 2 * n5);
                n4 = a(array, n4 - n5, n4, n4 + n5);
                a2 = a(array, a2 - 2 * n5, a2 - n5, a2);
            }
            n4 = a(array, a, n4, a2);
        }
        final float n6 = array[n4];
        int n8;
        int n7 = n8 = n;
        int n10;
        int n9 = n10 = n + n2 - 1;
        while (true) {
            if (n8 <= n9) {
                if (array[n8] <= n6) {
                    if (array[n8] == n6) {
                        a(array, array2, n7++, n8);
                    }
                    ++n8;
                    continue;
                }
            }
            while (n9 >= n8 && array[n9] >= n6) {
                if (array[n9] == n6) {
                    a(array, array2, n9, n10--);
                }
                --n9;
            }
            if (n8 > n9) {
                break;
            }
            a(array, array2, n8++, n9--);
        }
        final int n11 = n + n2;
        final int min = Math.min(n7 - n, n8 - n7);
        a(array, array2, n, n8 - min, min);
        final int min2 = Math.min(n10 - n9, n11 - n10 - 1);
        a(array, array2, n8, n11 - min2, min2);
        final int n12;
        if ((n12 = n8 - n7) > 1) {
            c(array, array2, n, n12);
        }
        final int n13;
        if ((n13 = n10 - n9) > 1) {
            c(array, array2, n11 - n13, n13);
        }
    }
    
    public static final float[] multMatrix44byMatrix44(final float[] array, final float[] array2) {
        System.arraycopy(array, 0, MatUtil.s, 0, 16);
        array[0] = MatUtil.s[0] * array2[0] + MatUtil.s[1] * array2[4] + MatUtil.s[2] * array2[8] + MatUtil.s[3] * array2[12];
        array[1] = MatUtil.s[0] * array2[1] + MatUtil.s[1] * array2[5] + MatUtil.s[2] * array2[9] + MatUtil.s[3] * array2[13];
        array[2] = MatUtil.s[0] * array2[2] + MatUtil.s[1] * array2[6] + MatUtil.s[2] * array2[10] + MatUtil.s[3] * array2[14];
        array[3] = MatUtil.s[0] * array2[3] + MatUtil.s[1] * array2[7] + MatUtil.s[2] * array2[11] + MatUtil.s[3] * array2[15];
        array[4] = MatUtil.s[4] * array2[0] + MatUtil.s[5] * array2[4] + MatUtil.s[6] * array2[8] + MatUtil.s[7] * array2[12];
        array[5] = MatUtil.s[4] * array2[1] + MatUtil.s[5] * array2[5] + MatUtil.s[6] * array2[9] + MatUtil.s[7] * array2[13];
        array[6] = MatUtil.s[4] * array2[2] + MatUtil.s[5] * array2[6] + MatUtil.s[6] * array2[10] + MatUtil.s[7] * array2[14];
        array[7] = MatUtil.s[4] * array2[3] + MatUtil.s[5] * array2[7] + MatUtil.s[6] * array2[11] + MatUtil.s[7] * array2[15];
        array[8] = MatUtil.s[8] * array2[0] + MatUtil.s[9] * array2[4] + MatUtil.s[10] * array2[8] + MatUtil.s[11] * array2[12];
        array[9] = MatUtil.s[8] * array2[1] + MatUtil.s[9] * array2[5] + MatUtil.s[10] * array2[9] + MatUtil.s[11] * array2[13];
        array[10] = MatUtil.s[8] * array2[2] + MatUtil.s[9] * array2[6] + MatUtil.s[10] * array2[10] + MatUtil.s[11] * array2[14];
        array[11] = MatUtil.s[8] * array2[3] + MatUtil.s[9] * array2[7] + MatUtil.s[10] * array2[11] + MatUtil.s[11] * array2[15];
        array[12] = MatUtil.s[12] * array2[0] + MatUtil.s[13] * array2[4] + MatUtil.s[14] * array2[8] + MatUtil.s[15] * array2[12];
        array[13] = MatUtil.s[12] * array2[1] + MatUtil.s[13] * array2[5] + MatUtil.s[14] * array2[9] + MatUtil.s[15] * array2[13];
        array[14] = MatUtil.s[12] * array2[2] + MatUtil.s[13] * array2[6] + MatUtil.s[14] * array2[10] + MatUtil.s[15] * array2[14];
        array[15] = MatUtil.s[12] * array2[3] + MatUtil.s[13] * array2[7] + MatUtil.s[14] * array2[11] + MatUtil.s[15] * array2[15];
        return array;
    }
    
    public static final void c(final float[] array) {
        array[0] = 1.0f;
        array[2] = (array[1] = 0.0f);
        array[4] = (array[3] = 0.0f);
        array[5] = 1.0f;
        array[7] = (array[6] = 0.0f);
        array[9] = (array[8] = 0.0f);
        array[10] = 1.0f;
        array[12] = (array[11] = 0.0f);
        array[14] = (array[13] = 0.0f);
        array[15] = 1.0f;
    }
    
    public static final float[] d(final float[] array) {
        return new float[] { array[0], array[1], array[2], 0.0f, array[3], array[4], array[5], 0.0f, array[6], array[7], array[8], 0.0f, array[9], array[10], array[11], 1.0f };
    }
    
    static {
        MatUtil.s = new float[16];
    }
    
    public static final void multVecMatrix(final float[] array, final float[] array2) {
        final float n = array2[0] * array[0] + array2[1] * array[4] + array2[2] * array[8] + array[12];
        final float n2 = array2[0] * array[1] + array2[1] * array[5] + array2[2] * array[9] + array[13];
        final float n3 = array2[0] * array[2] + array2[1] * array[6] + array2[2] * array[10] + array[14];
        array2[0] = n;
        array2[1] = n2;
        array2[2] = n3;
    }
    
    public static final boolean g(final float[] array, final float[] array2) {
        MatUtil.i = array[0] * array[4];
        MatUtil.j = array[1] * array[5];
        MatUtil.k = array[2] * array[3];
        MatUtil.l = array[4] * array[6];
        MatUtil.m = array[3] * array[8];
        MatUtil.n = array[5] * array[7];
        final float n = MatUtil.i * array[8] + MatUtil.j * array[6] + MatUtil.k * array[7] - array[2] * MatUtil.l - array[1] * MatUtil.m - array[0] * MatUtil.n;
        if (n == 0.0f) {
            return false;
        }
        MatUtil.h = 1.0f / n;
        array2[0] = (array[4] * array[8] - MatUtil.n) * MatUtil.h;
        array2[1] = (array[2] * array[7] - array[1] * array[8]) * MatUtil.h;
        array2[2] = (MatUtil.j - array[2] * array[4]) * MatUtil.h;
        array2[3] = (array[5] * array[6] - MatUtil.m) * MatUtil.h;
        array2[4] = (array[0] * array[8] - array[2] * array[6]) * MatUtil.h;
        array2[5] = (MatUtil.k - array[0] * array[5]) * MatUtil.h;
        array2[6] = (array[3] * array[7] - MatUtil.l) * MatUtil.h;
        array2[7] = (array[1] * array[6] - array[0] * array[7]) * MatUtil.h;
        array2[8] = (MatUtil.i - array[1] * array[3]) * MatUtil.h;
        return true;
    }
    
    public static final float[] h(final float[] array, final float[] array2) {
        System.arraycopy(array, 0, MatUtil.s, 0, 12);
        array[0] = MatUtil.s[0] * array2[0] + MatUtil.s[1] * array2[3] + MatUtil.s[2] * array2[6];
        array[1] = MatUtil.s[0] * array2[1] + MatUtil.s[1] * array2[4] + MatUtil.s[2] * array2[7];
        array[2] = MatUtil.s[0] * array2[2] + MatUtil.s[1] * array2[5] + MatUtil.s[2] * array2[8];
        array[3] = MatUtil.s[3] * array2[0] + MatUtil.s[4] * array2[3] + MatUtil.s[5] * array2[6];
        array[4] = MatUtil.s[3] * array2[1] + MatUtil.s[4] * array2[4] + MatUtil.s[5] * array2[7];
        array[5] = MatUtil.s[3] * array2[2] + MatUtil.s[4] * array2[5] + MatUtil.s[5] * array2[8];
        array[6] = MatUtil.s[6] * array2[0] + MatUtil.s[7] * array2[3] + MatUtil.s[8] * array2[6];
        array[7] = MatUtil.s[6] * array2[1] + MatUtil.s[7] * array2[4] + MatUtil.s[8] * array2[7];
        array[8] = MatUtil.s[6] * array2[2] + MatUtil.s[7] * array2[5] + MatUtil.s[8] * array2[8];
        array[9] = MatUtil.s[9] * array2[0] + MatUtil.s[10] * array2[3] + MatUtil.s[11] * array2[6] + array2[9];
        array[10] = MatUtil.s[9] * array2[1] + MatUtil.s[10] * array2[4] + MatUtil.s[11] * array2[7] + array2[10];
        array[11] = MatUtil.s[9] * array2[2] + MatUtil.s[10] * array2[5] + MatUtil.s[11] * array2[8] + array2[11];
        return array;
    }
    
    public static final void i(final float[] array, final float[] array2) {
        c(array, array2, array2);
    }
    
    public static final void c(final float[] array, final float[] array2, final float[] array3) {
        for (int i = 0, n = 1, n2 = 2; i < array2.length; i += 3, n += 3, n2 += 3) {
            final float n3 = array2[i];
            final float n4 = array2[n];
            final float n5 = array2[n2];
            array3[i] = n3 * array[0] + n4 * array[3] + n5 * array[6];
            array3[n] = n3 * array[1] + n4 * array[4] + n5 * array[7];
            array3[n2] = n3 * array[2] + n4 * array[5] + n5 * array[8];
        }
    }
    
    public static final boolean j(final float[] array, final float[] array2) {
        if (!d(array, array2)) {
            return false;
        }
        MatUtil.o = -array[9];
        MatUtil.p = -array[10];
        MatUtil.q = -array[11];
        array2[9] = MatUtil.o * array2[0] + MatUtil.p * array2[3] + MatUtil.q * array2[6];
        array2[10] = MatUtil.o * array2[1] + MatUtil.p * array2[4] + MatUtil.q * array2[7];
        array2[11] = MatUtil.o * array2[2] + MatUtil.p * array2[5] + MatUtil.q * array2[8];
        return true;
    }
    
    protected static final void k(final float[] array, final float[] array2) {
        f(array, array2);
    }
}
