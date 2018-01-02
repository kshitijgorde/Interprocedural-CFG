// 
// Decompiled by Procyon v0.5.30
// 

final class Class136
{
    private static int anInt1070;
    private static int anInt1071;
    private static int anInt1072;
    private static int[] anIntArray1073;
    private static int anInt1074;
    private static int anInt1075;
    private static int anInt1076;
    private static int anInt1077;
    
    private static final void method2265(final int n, final int n2) {
        if (n2 > n + 4) {
            int n3 = n;
            final int n4 = Class136.anIntArray1073[n3];
            final int n5 = Class136.anIntArray1073[n3 + 1];
            final int n6 = Class136.anIntArray1073[n3 + 2];
            final int n7 = Class136.anIntArray1073[n3 + 3];
            for (int i = n + 4; i < n2; i += 4) {
                final int n8 = Class136.anIntArray1073[i + 1];
                if (n8 < n5) {
                    Class136.anIntArray1073[n3] = Class136.anIntArray1073[i];
                    Class136.anIntArray1073[n3 + 1] = n8;
                    Class136.anIntArray1073[n3 + 2] = Class136.anIntArray1073[i + 2];
                    Class136.anIntArray1073[n3 + 3] = Class136.anIntArray1073[i + 3];
                    n3 += 4;
                    Class136.anIntArray1073[i] = Class136.anIntArray1073[n3];
                    Class136.anIntArray1073[i + 1] = Class136.anIntArray1073[n3 + 1];
                    Class136.anIntArray1073[i + 2] = Class136.anIntArray1073[n3 + 2];
                    Class136.anIntArray1073[i + 3] = Class136.anIntArray1073[n3 + 3];
                }
            }
            Class136.anIntArray1073[n3] = n4;
            Class136.anIntArray1073[n3 + 1] = n5;
            Class136.anIntArray1073[n3 + 2] = n6;
            Class136.anIntArray1073[n3 + 3] = n7;
            method2265(n, n3);
            method2265(n3 + 4, n2);
        }
    }
    
    private static final boolean method2266(final int n) {
        int i = Class136.anInt1077;
        int j = Class136.anInt1072;
        int anInt1076 = Class136.anInt1076;
        while (j >= i) {
            Class136.anInt1076 = ++anInt1076;
            if (anInt1076 >= n) {
                return false;
            }
            int anInt1077 = Class136.anInt1074;
            while (i < Class136.anInt1070) {
                final int n2 = Class136.anIntArray1073[i + 1];
                if (anInt1076 < n2) {
                    break;
                }
                final int n3 = Class136.anIntArray1073[i];
                final int n4 = (Class136.anIntArray1073[i + 2] - n3 << 16) / (Class136.anIntArray1073[i + 3] - n2);
                Class136.anIntArray1073[i] = (n3 << 16) + 32768;
                Class136.anIntArray1073[i + 2] = n4;
                i += 4;
            }
            for (int k = anInt1077; k < i; k += 4) {
                if (anInt1076 >= Class136.anIntArray1073[k + 3]) {
                    Class136.anIntArray1073[k] = Class136.anIntArray1073[anInt1077];
                    Class136.anIntArray1073[k + 1] = Class136.anIntArray1073[anInt1077 + 1];
                    Class136.anIntArray1073[k + 2] = Class136.anIntArray1073[anInt1077 + 2];
                    Class136.anIntArray1073[k + 3] = Class136.anIntArray1073[anInt1077 + 3];
                    anInt1077 += 4;
                }
            }
            if (anInt1077 == Class136.anInt1070) {
                Class136.anInt1070 = 0;
                return false;
            }
            method2274(anInt1077, i);
            Class136.anInt1074 = anInt1077;
            Class136.anInt1077 = i;
            j = anInt1077;
        }
        Class136.anInt1075 = Class136.anIntArray1073[j] >> 16;
        Class136.anInt1071 = Class136.anIntArray1073[j + 4] >> 16;
        final int[] anIntArray1073 = Class136.anIntArray1073;
        final int n5 = j;
        anIntArray1073[n5] += Class136.anIntArray1073[j + 2];
        final int[] anIntArray1074 = Class136.anIntArray1073;
        final int n6 = j + 4;
        anIntArray1074[n6] += Class136.anIntArray1073[j + 6];
        j += 8;
        Class136.anInt1072 = j;
        return true;
    }
    
    private static final void method2267(final int[] array, final int n, int n2) {
        final int n3 = Class136.anInt1070 + (n2 << 1);
        if (Class136.anIntArray1073 == null || Class136.anIntArray1073.length < n3) {
            final int[] anIntArray1073 = new int[n3];
            for (int i = 0; i < Class136.anInt1070; ++i) {
                anIntArray1073[i] = Class136.anIntArray1073[i];
            }
            Class136.anIntArray1073 = anIntArray1073;
        }
        n2 += n;
        int n4 = n2 - 2;
        for (int j = n; j < n2; j += 2) {
            final int n5 = array[n4 + 1];
            final int n6 = array[j + 1];
            if (n5 < n6) {
                Class136.anIntArray1073[Class136.anInt1070++] = array[n4];
                Class136.anIntArray1073[Class136.anInt1070++] = n5;
                Class136.anIntArray1073[Class136.anInt1070++] = array[j];
                Class136.anIntArray1073[Class136.anInt1070++] = n6;
            }
            else if (n6 < n5) {
                Class136.anIntArray1073[Class136.anInt1070++] = array[j];
                Class136.anIntArray1073[Class136.anInt1070++] = n6;
                Class136.anIntArray1073[Class136.anInt1070++] = array[n4];
                Class136.anIntArray1073[Class136.anInt1070++] = n5;
            }
            n4 = j;
        }
    }
    
    private static final void method2268(final ha ha, final int[] array, final int n, final int n2, final int n3, final int[] array2, final int[] array3) {
        final int[] array4 = new int[4];
        ha.K(array4);
        if (array2 != null && array4[3] - array4[1] != array2.length) {
            throw new IllegalStateException();
        }
        method2271();
        method2267(array, n, n2);
        method2273(array4[1]);
        while (method2266(array4[3])) {
            int anInt1075 = Class136.anInt1075;
            int anInt1076 = Class136.anInt1071;
            final int anInt1077 = Class136.anInt1076;
            if (array2 != null) {
                final int n4 = anInt1077 - array4[1];
                if (anInt1075 < array2[n4] + array4[0]) {
                    anInt1075 = array2[n4] + array4[0];
                }
                if (anInt1076 > array2[n4] + array3[n4] + array4[0]) {
                    anInt1076 = array2[n4] + array3[n4] + array4[0];
                }
                if (anInt1076 - anInt1075 <= 0) {
                    continue;
                }
            }
            ha.U(anInt1075, anInt1077, anInt1076 - anInt1075, n3, 1);
        }
    }
    
    public static void method2269() {
        Class136.anIntArray1073 = null;
    }
    
    static final void method2270(final ha ha, final int[] array, final int n) {
        method2268(ha, array, 0, array.length, n, null, null);
    }
    
    private static final void method2271() {
        Class136.anInt1070 = 0;
    }
    
    static final void method2272(final ha ha, final int[] array, final int n, final int[] array2, final int[] array3) {
        method2268(ha, array, 0, array.length, n, array2, array3);
    }
    
    private static final void method2273(final int n) {
        if (Class136.anInt1070 < 0) {
            Class136.anInt1074 = (Class136.anInt1077 = (Class136.anInt1072 = 0));
            Class136.anInt1076 = 2147483646;
        }
        else {
            method2265(0, Class136.anInt1070);
            int n2 = Class136.anIntArray1073[1];
            if (n2 < n) {
                n2 = n;
            }
            final boolean anInt1074 = false;
            int i;
            for (i = 0; i < Class136.anInt1070; i += 4) {
                final int n3 = Class136.anIntArray1073[i + 1];
                if (n2 < n3) {
                    break;
                }
                final int n4 = Class136.anIntArray1073[i];
                final int n5 = (Class136.anIntArray1073[i + 2] - n4 << 16) / (Class136.anIntArray1073[i + 3] - n3);
                Class136.anIntArray1073[i] = (n4 << 16) + 32768 + (n2 - n3) * n5;
                Class136.anIntArray1073[i + 2] = n5;
            }
            Class136.anInt1074 = (anInt1074 ? 1 : 0);
            Class136.anInt1077 = i;
            Class136.anInt1072 = i;
            Class136.anInt1076 = n2 - 1;
        }
    }
    
    private static final void method2274(final int n, int i) {
        while (i >= n + 8) {
            boolean b = true;
            for (int j = n + 4; j < i; j += 4) {
                final int n2 = Class136.anIntArray1073[j - 4];
                final int n3 = Class136.anIntArray1073[j];
                if (n2 > n3) {
                    b = false;
                    Class136.anIntArray1073[j - 4] = n3;
                    Class136.anIntArray1073[j] = n2;
                    final int n4 = Class136.anIntArray1073[j - 2];
                    Class136.anIntArray1073[j - 2] = Class136.anIntArray1073[j + 2];
                    Class136.anIntArray1073[j + 2] = n4;
                    final int n5 = Class136.anIntArray1073[j - 1];
                    Class136.anIntArray1073[j - 1] = Class136.anIntArray1073[j + 3];
                    Class136.anIntArray1073[j + 3] = n5;
                }
            }
            if (b) {
                break;
            }
            i -= 4;
        }
    }
}
