import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

public class do
{
    public static void calculateStdDev(final double[] array, double[] array2, final int n, int n2) {
        boolean b = false;
        if (n2 < 1) {
            n2 = 1;
        }
        if (array == array2) {
            array2 = new double[array.length];
            b = true;
        }
        final double[] array3 = new double[array.length];
        calculateSimpleAvg(array, array3, 0, n2);
        for (int i = n + n2 - 1; i < array.length; ++i) {
            double n3 = 0.0;
            for (int j = i; j > i - n2; --j) {
                final double n4 = array[j] - array3[i];
                n3 += n4 * n4;
            }
            array2[i] = Math.sqrt(n3 / n2);
        }
        _(array2, n, 0.0);
        if (b) {
            System.arraycopy(array2, 0, array, 0, array2.length);
        }
    }
    
    public static void calculateSimpleAvg(final double[] array, double[] array2, final int n, int n2) {
        double n3 = 0.0;
        boolean b = false;
        if (n2 < 1) {
            n2 = 1;
        }
        if (array == array2) {
            array2 = new double[array.length];
            b = true;
        }
        for (int n4 = n; n4 < array.length && n4 < array2.length; ++n4) {
            n3 += array[n4];
            if (n4 - n2 >= n) {
                n3 -= array[n4 - n2];
                array2[n4] = n3 / n2;
            }
            else {
                array2[n4] = n3 / (n4 - n + 1);
            }
        }
        _(array2, n, 0.0);
        if (b) {
            System.arraycopy(array2, 0, array, 0, array2.length);
        }
    }
    
    public static void calculateExpAvg(final double[] array, double[] array2, final int n, int n2) {
        boolean b = false;
        if (n2 < 1) {
            n2 = 1;
        }
        if (array == array2) {
            array2 = new double[array.length];
            b = true;
        }
        if (n2 < 0) {
            n2 = 1;
        }
        final double n3 = 2.0 / (n2 + 1);
        if (n < array.length && n < array.length) {
            array2[n] = array[n];
        }
        for (int n4 = n + 1; n4 < array.length && n4 < array2.length; ++n4) {
            array2[n4] = array2[n4 - 1] * (1.0 - n3) + array[n4] * n3;
        }
        _(array2, n, 0.0);
        if (b) {
            System.arraycopy(array2, 0, array, 0, array2.length);
        }
    }
    
    public static void calculateMax(final double[] array, double[] array2, final int n, int n2) {
        boolean b = false;
        if (n2 < 1) {
            n2 = 1;
        }
        if (array == array2) {
            array2 = new double[array.length];
            b = true;
        }
        for (int n3 = n + n2 - 1; n3 < array.length && n3 < array2.length; ++n3) {
            double max = array[n3];
            for (int i = 1; i < n2; ++i) {
                max = Math.max(max, array[n3 - i]);
            }
            array2[n3] = max;
        }
        _(array2, n, 0.0);
        if (b) {
            System.arraycopy(array2, 0, array, 0, array2.length);
        }
    }
    
    public static void calculateMin(final double[] array, double[] array2, final int n, int n2) {
        boolean b = false;
        if (n2 < 1) {
            n2 = 1;
        }
        if (array == array2) {
            array2 = new double[array.length];
            b = true;
        }
        for (int n3 = n + n2 - 1; n3 < array.length && n3 < array2.length; ++n3) {
            double min = array[n3];
            for (int i = 1; i < n2; ++i) {
                min = Math.min(min, array[n3 - i]);
            }
            array2[n3] = min;
        }
        _(array2, n, 0.0);
        if (b) {
            System.arraycopy(array2, 0, array, 0, array2.length);
        }
    }
    
    public static void a(final double[] array, double[] array2, final int n, int n2) {
        double n3 = 0.0;
        boolean b = false;
        if (n2 < 1) {
            n2 = 1;
        }
        if (array == array2) {
            array2 = new double[array.length];
            b = true;
        }
        for (int n4 = n; n4 < array.length && n4 < array2.length; ++n4) {
            n3 += array[n4];
            if (n4 - n2 >= n) {
                n3 -= array[n4 - n2];
            }
            array2[n4] = n3;
        }
        _(array2, n, 0.0);
        if (b) {
            System.arraycopy(array2, 0, array, 0, array2.length);
        }
    }
    
    public static void _(final double[] array, final int n, final double n2) {
        for (int n3 = 0; n3 < array.length && n3 < n; ++n3) {
            if (n < array.length) {
                array[n3] = array[n];
            }
            else {
                array[n3] = n2;
            }
        }
    }
    
    public static Vector _(final double[] array, final double n, final int n2) {
        if (array == null || array.length == 0 || n2 >= array.length) {
            return new Vector();
        }
        final Vector<Integer> vector = new Vector<Integer>(Math.max(20, array.length / 10));
        for (int i = Math.max(1, n2); i < array.length; ++i) {
            if (array[i] > n) {
                for (int j = i - 1; j >= n2; --j) {
                    if (array[j] < n) {
                        vector.addElement(new Integer(i));
                        break;
                    }
                    if (array[j] > n) {
                        break;
                    }
                }
            }
        }
        return vector;
    }
    
    public static Vector _(final double[] array, final double[] array2, final int n) {
        if (array == null || array2 == null || array.length == 0 || array2.length == 0 || array.length != array2.length || n >= array.length) {
            return new Vector();
        }
        final Vector<Integer> vector = new Vector<Integer>(Math.max(20, array.length / 10));
        for (int i = Math.max(1, n); i < array.length; ++i) {
            if (array[i] > array2[i]) {
                for (int j = i - 1; j >= n; --j) {
                    if (array[j] < array2[j]) {
                        vector.addElement(new Integer(i));
                        break;
                    }
                    if (array[j] > array2[j]) {
                        break;
                    }
                }
            }
        }
        return vector;
    }
    
    public static Vector a(final double[] array, final double n, final int n2) {
        if (array == null || array.length == 0 || n2 >= array.length) {
            return new Vector();
        }
        final Vector<Integer> vector = new Vector<Integer>(Math.max(20, array.length / 10));
        for (int i = Math.max(1, n2); i < array.length; ++i) {
            if (array[i] < n) {
                for (int j = i - 1; j >= n2; --j) {
                    if (array[j] > n) {
                        vector.addElement(new Integer(i));
                        break;
                    }
                    if (array[j] < n) {
                        break;
                    }
                }
            }
        }
        return vector;
    }
    
    public static Vector a(final double[] array, final double[] array2, final int n) {
        if (array == null || array2 == null || array.length == 0 || array2.length == 0 || array.length != array2.length || n >= array.length) {
            return new Vector();
        }
        final Vector<Integer> vector = new Vector<Integer>(Math.max(20, array.length / 10));
        for (int i = Math.max(1, n); i < array.length; ++i) {
            if (array[i] < array2[i]) {
                for (int j = i - 1; j >= n; --j) {
                    if (array[j] > array2[j]) {
                        vector.addElement(new Integer(i));
                        break;
                    }
                    if (array[j] < array2[j]) {
                        break;
                    }
                }
            }
        }
        return vector;
    }
}
