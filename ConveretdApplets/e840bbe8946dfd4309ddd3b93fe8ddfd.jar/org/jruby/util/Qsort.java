// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util;

import java.util.ArrayList;
import java.util.Comparator;

public class Qsort
{
    private static final int SIZE_THRESHOLD = 16;
    
    public static void sort(final Object[] a, final Comparator c) {
        if (a.length < 16) {
            insertionsort(a, 0, a.length, c);
            return;
        }
        quicksort_loop(a, 0, a.length, c);
    }
    
    public static void sort(final Object[] a, final int begin, final int end, final Comparator c) {
        if (begin < end) {
            if (end - begin < 16) {
                insertionsort(a, begin, end, c);
                return;
            }
            quicksort_loop(a, begin, end, c);
        }
    }
    
    private static void endTest(final Object[] a, final int lo, final int hi, final Comparator c) {
        if (c.compare(a[lo], a[lo + 1]) <= 0) {
            if (c.compare(a[hi - 2], a[hi - 1]) > 0) {
                bubbleUp(a, lo, hi - 1, c);
            }
        }
        else if (c.compare(a[hi - 2], a[hi - 1]) > 0) {
            insertionsort(a, lo, hi, c);
        }
        else {
            bubbleDown(a, lo, hi - 1, c);
        }
    }
    
    private static boolean seqtest(final Object[] a, final int lo, final int hi, final Comparator c) {
        for (int i = lo + 1; i < hi - 2; ++i) {
            if (c.compare(a[i], a[i + 1]) > 0) {
                return false;
            }
        }
        endTest(a, lo, hi, c);
        return true;
    }
    
    private static boolean revtest(final Object[] a, final int lo, final int hi, final Comparator c) {
        for (int i = lo + 1; i < hi - 2; ++i) {
            if (c.compare(a[i], a[i + 1]) < 0) {
                return false;
            }
        }
        int i = lo;
        int j = hi - 1;
        while (i < j) {
            swap(a, i++, j--);
        }
        endTest(a, lo, hi, c);
        return true;
    }
    
    private static void quicksort_loop(final Object[] a, int lo, int hi, final Comparator c) {
        final ArrayList<int[]> stack = new ArrayList<int[]>(16);
        int[] entry = { lo, hi };
        while (!stack.isEmpty() || entry != null) {
            if (entry == null) {
                entry = stack.remove(stack.size() - 1);
            }
            lo = entry[0];
            hi = entry[1];
            final int midi = lo + (hi - lo) / 2;
            Object mid = a[midi];
            Object m1;
            Object m2;
            if (hi - lo >= 200) {
                final int t = (hi - lo) / 8;
                m1 = med3(a[lo + t], a[lo + t * 2], a[lo + t * 3], c);
                m2 = med3(a[midi + t], a[midi + t * 2], a[midi + t * 3], c);
            }
            else {
                final int t = (hi - lo) / 4;
                m1 = a[lo + t];
                m2 = a[midi + t];
            }
            mid = med3(m1, mid, m2, c);
            if (hi - lo >= 63) {
                if (c.compare(m1, mid) <= 0 && c.compare(mid, m2) <= 0) {
                    if (seqtest(a, lo, hi, c)) {
                        entry = null;
                        continue;
                    }
                }
                else if (c.compare(m1, mid) >= 0 && c.compare(mid, m2) >= 0 && revtest(a, lo, hi, c)) {
                    entry = null;
                    continue;
                }
            }
            final int[] p = partition(a, lo, hi, mid, c);
            if (hi - p[1] > 16 && p[0] - lo > 16) {
                entry[0] = p[1];
                entry[1] = hi;
                stack.add(entry);
                entry = new int[] { lo, p[0] };
            }
            else if (hi - p[1] > 16) {
                entry[0] = p[1];
                entry[1] = hi;
                insertionsort(a, lo, p[0], c);
            }
            else if (p[0] - lo > 16) {
                entry[0] = lo;
                entry[1] = p[0];
                insertionsort(a, p[1], hi, c);
            }
            else {
                insertionsort(a, lo, p[0], c);
                insertionsort(a, p[1], hi, c);
                entry = null;
            }
        }
    }
    
    private static int[] partition(final Object[] a, int lo1, final int hi, final Object x, final Comparator comp) {
        int i;
        int lo2 = i = lo1;
        int j = hi;
        int c = 0;
        while (true) {
            if (i < j && (c = comp.compare(a[i], x)) <= 0) {
                if (c == 0) {
                    if (i > lo2) {
                        swap(a, lo2++, i);
                    }
                    else {
                        ++lo2;
                    }
                }
                ++i;
            }
            else {
                --j;
                while (j >= i && (c = comp.compare(x, a[j])) < 0) {
                    --j;
                }
                if (i > j) {
                    break;
                }
                if (c == 0) {
                    swap(a, i, j);
                    if (i > lo2) {
                        swap(a, lo2++, i);
                    }
                    else {
                        ++lo2;
                    }
                }
                else {
                    swap(a, i, j);
                }
                ++i;
            }
        }
        for (c = ((i >= hi) ? (hi - 1) : i); c > lo1 && comp.compare(x, a[c]) < 0; --c) {}
        --lo2;
        while (lo2 >= lo1 && c > lo2) {
            swap(a, lo1++, c--);
        }
        return new int[] { (c > lo2) ? (c + 1) : lo1, i };
    }
    
    private static Object med3(final Object lo, final Object mid, final Object hi, final Comparator c) {
        if (c.compare(mid, lo) < 0) {
            if (c.compare(hi, mid) < 0) {
                return mid;
            }
            if (c.compare(hi, lo) < 0) {
                return hi;
            }
            return lo;
        }
        else {
            if (c.compare(hi, mid) >= 0) {
                return mid;
            }
            if (c.compare(hi, lo) < 0) {
                return lo;
            }
            return hi;
        }
    }
    
    private static void insertionsort(final Object[] a, final int lo, final int hi, final Comparator c) {
        for (int i = lo + 1; i < hi; ++i) {
            int j;
            Object t;
            for (j = i, t = a[j]; j > lo && c.compare(t, a[j - 1]) < 0; --j) {
                a[j] = a[j - 1];
            }
            a[j] = t;
        }
    }
    
    private static void bubbleDown(final Object[] a, int lo, final int hi, final Comparator c) {
        Object x;
        for (x = a[lo]; lo < hi && c.compare(x, a[lo + 1]) > 0; a[lo] = a[++lo]) {}
        a[lo] = x;
    }
    
    private static void bubbleUp(final Object[] a, final int lo, int hi, final Comparator c) {
        Object x;
        for (x = a[hi]; hi > lo && c.compare(x, a[hi - 1]) < 0; a[hi] = a[--hi]) {}
        a[hi] = x;
    }
    
    private static void swap(final Object[] a, final int i, final int j) {
        final Object t = a[i];
        a[i] = a[j];
        a[j] = t;
    }
}
