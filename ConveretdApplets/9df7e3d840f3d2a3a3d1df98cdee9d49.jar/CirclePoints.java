import java.util.Vector;

// 
// Decompiled by Procyon v0.5.30
// 

class CirclePoints
{
    private static void sort(final Object[] array, final Object[] array2, final int n, final int n2, final boolean b, final Comparer comparer) {
        if (array == null || array.length < 2) {
            return;
        }
        int i = n;
        int j = n2;
        final Object o = array[(n + n2) / 2];
        do {
            if (b) {
                while (i < n2) {
                    if (comparer.compare(o, array[i]) <= 0) {
                        break;
                    }
                    ++i;
                }
                while (j > n) {
                    if (comparer.compare(o, array[j]) >= 0) {
                        break;
                    }
                    --j;
                }
            }
            else {
                while (i < n2) {
                    if (comparer.compare(o, array[i]) >= 0) {
                        break;
                    }
                    ++i;
                }
                while (j > n && comparer.compare(o, array[j]) > 0) {
                    --j;
                }
            }
            if (i < j) {
                final Object o2 = array[i];
                array[i] = array[j];
                array[j] = o2;
                if (array2 != null) {
                    final Object o3 = array2[i];
                    array2[i] = array2[j];
                    array2[j] = o3;
                }
            }
            if (i <= j) {
                ++i;
                --j;
            }
        } while (i <= j);
        if (n < j) {
            sort(array, array2, n, j, b, comparer);
        }
        if (i < n2) {
            sort(array, array2, i, n2, b, comparer);
        }
    }
    
    public static Vector circle(final int n, final int n2, final double n3, final int n4) {
        final double n5 = n;
        final double n6 = n2;
        final double n7 = n4;
        final Vector vector = new Vector<Object>();
        final Vector[] array = new Vector[8];
        for (int i = 0; i < array.length; ++i) {
            array[i] = new Vector();
        }
        double n8 = 0.0;
        double n9 = n3;
        double n10 = n7 - n3;
        plotPoints(array, n5, n6, n8, n9);
        while (n8 < n9) {
            n8 += n4;
            if (n10 < 0.0) {
                n10 += 2.0 * n8 + n7;
            }
            else {
                n9 -= n7;
                n10 += 2.0 * (n8 - n9) + n7;
            }
            plotPoints(array, n5, n6, n8, n9);
        }
        final Vector[] sort_by_x = sort_by_x(array);
        for (int j = 0; j < sort_by_x.length; ++j) {
            for (int k = 0; k < sort_by_x[j].size(); ++k) {
                if (!vector.contains(sort_by_x[j].elementAt(k))) {
                    vector.addElement(sort_by_x[j].elementAt(k));
                }
            }
        }
        return vector;
    }
    
    private static Object[] sort_by_y(final Object[] array, final int n, final int n2, final int n3) {
        sort(array, null, n, n2, n3 > 1 && n3 < 6, new YComparer());
        return array;
    }
    
    private static Vector[] sort_by_x(final Vector[] array) {
        for (int i = 0; i < 8; ++i) {
            Object[] array2 = new DoublePoint[array[i].size()];
            for (int j = 0; j < array2.length; ++j) {
                array2[j] = array[i].elementAt(j);
            }
            sort(array2, null, 0, array2.length - 1, i <= 3, new XComparer());
            boolean b = false;
            int n = 0;
            double n2 = ((DoublePoint)array2[0]).x;
        Label_0175:
            while (!b) {
                while (true) {
                    for (int k = n; k != array2.length; ++k) {
                        if (((DoublePoint)array2[k]).x != n2) {
                            array2 = sort_by_y(array2, n, k - 1, i);
                            n2 = ((DoublePoint)array2[k]).x;
                            n = k;
                            array2 = sort_by_y(array2, n, k - 1, i);
                            continue Label_0175;
                        }
                    }
                    b = true;
                    continue;
                }
            }
            final Vector<DoublePoint> vector = new Vector<DoublePoint>();
            for (int l = 0; l < array2.length; ++l) {
                vector.addElement((DoublePoint)array2[l]);
            }
            array[i] = vector;
        }
        return array;
    }
    
    private static void plotPoints(final Vector[] array, final double n, final double n2, final double n3, final double n4) {
        array[3].addElement(new DoublePoint(n + n4, n2 - n3));
        array[0].addElement(new DoublePoint(n - n4, n2 - n3));
        array[4].addElement(new DoublePoint(n + n4, n2 + n3));
        array[7].addElement(new DoublePoint(n - n4, n2 + n3));
        array[2].addElement(new DoublePoint(n + n3, n2 - n4));
        array[1].addElement(new DoublePoint(n - n3, n2 - n4));
        array[5].addElement(new DoublePoint(n + n3, n2 + n4));
        array[6].addElement(new DoublePoint(n - n3, n2 + n4));
    }
}
