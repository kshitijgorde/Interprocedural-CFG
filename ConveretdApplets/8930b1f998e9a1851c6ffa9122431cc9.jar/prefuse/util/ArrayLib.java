// 
// Decompiled by Procyon v0.5.30
// 

package prefuse.util;

import java.util.StringTokenizer;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Comparator;
import java.util.Random;

public abstract class ArrayLib
{
    public static final int SORT_THRESHOLD = 30;
    
    public static final void shuffle(final int[] array, final Random random) {
        shuffle(array, 0, array.length, random);
    }
    
    public static final void shuffle(final int[] array, final int n, final int n2, final Random random) {
        int n3 = n + n2;
        while (--n3 > 0) {
            final int n4 = array[n3];
            final int nextInt = random.nextInt(n3);
            array[n3] = array[nextInt];
            array[nextInt] = n4;
        }
    }
    
    public static final void shuffle(final long[] array, final Random random) {
        shuffle(array, 0, array.length, random);
    }
    
    public static final void shuffle(final long[] array, final int n, final int n2, final Random random) {
        for (int i = n + n2; i > 1; --i) {
            final long n3 = array[i];
            final int nextInt = random.nextInt(i);
            array[i] = array[nextInt];
            array[nextInt] = n3;
        }
    }
    
    public static final void shuffle(final float[] array, final Random random) {
        shuffle(array, 0, array.length, random);
    }
    
    public static final void shuffle(final float[] array, final int n, final int n2, final Random random) {
        for (int i = n + n2; i > 1; --i) {
            final float n3 = array[i];
            final int nextInt = random.nextInt(i);
            array[i] = array[nextInt];
            array[nextInt] = n3;
        }
    }
    
    public static final void shuffle(final double[] array, final Random random) {
        shuffle(array, 0, array.length, random);
    }
    
    public static final void shuffle(final double[] array, final int n, final int n2, final Random random) {
        for (int i = n + n2; i > 1; --i) {
            final double n3 = array[i];
            final int nextInt = random.nextInt(i);
            array[i] = array[nextInt];
            array[nextInt] = n3;
        }
    }
    
    public static final void shuffle(final Object[] array, final Random random) {
        shuffle(array, 0, array.length, random);
    }
    
    public static final void shuffle(final Object[] array, final int n, final int n2, final Random random) {
        for (int i = n + n2; i > 1; --i) {
            final Object o = array[i];
            final int nextInt = random.nextInt(i);
            array[i] = array[nextInt];
            array[nextInt] = o;
        }
    }
    
    public static final double max(final double[] array) {
        double n = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < array.length; ++i) {
            if (array[i] > n) {
                n = array[i];
            }
        }
        return n;
    }
    
    public static final double min(final double[] array) {
        double n = Double.POSITIVE_INFINITY;
        for (int i = 0; i < array.length; ++i) {
            if (array[i] < n) {
                n = array[i];
            }
        }
        return n;
    }
    
    public static final double sum(final double[] array) {
        double n = 0.0;
        for (int i = 0; i < array.length; ++i) {
            n += array[i];
        }
        return n;
    }
    
    public static final int binarySearch(final int[] array, final int n) {
        int i = 0;
        int length = array.length;
        int n2 = length / 2;
        while (i < length) {
            if (array[n2] == n) {
                return n2;
            }
            if (array[n2] < n) {
                i = n2 + 1;
            }
            else {
                length = n2;
            }
            n2 = i + (length - i) / 2;
        }
        return -1 * (n2 + 1);
    }
    
    public static final int binarySearch(final int[] array, final int n, final int n2) {
        int i = 0;
        int n3 = n2;
        int n4 = n3 / 2;
        while (i < n3) {
            if (array[n4] == n) {
                return n4;
            }
            if (array[n4] < n) {
                i = n4 + 1;
            }
            else {
                n3 = n4;
            }
            n4 = i + (n3 - i) / 2;
        }
        return -1 * (n4 + 1);
    }
    
    public static final int binarySearch(final int[] array, final int n, final int n2, final int n3) {
        int i = n2;
        int n4 = n3;
        int n5 = i + (n4 - i) / 2;
        while (i < n4) {
            if (array[n5] == n) {
                return n5;
            }
            if (array[n5] < n) {
                i = n5 + 1;
            }
            else {
                n4 = n5;
            }
            n5 = i + (n4 - i) / 2;
        }
        return -1 * (n5 + 1);
    }
    
    public static final int binarySearch(final Object[] array, final Object o) {
        int i = 0;
        int length = array.length;
        int n = length / 2;
        while (i < length) {
            final int compareTo = ((Comparable)array[n]).compareTo(o);
            if (compareTo == 0) {
                return n;
            }
            if (compareTo < 0) {
                i = n + 1;
            }
            else {
                length = n;
            }
            n = i + (length - i) / 2;
        }
        return -1 * (n + 1);
    }
    
    public static final int binarySearch(final Object[] array, final Object o, final int n) {
        int i = 0;
        int n2 = n;
        int n3 = n2 / 2;
        while (i < n2) {
            final int compareTo = ((Comparable)array[n3]).compareTo(o);
            if (compareTo == 0) {
                return n3;
            }
            if (compareTo < 0) {
                i = n3 + 1;
            }
            else {
                n2 = n3;
            }
            n3 = i + (n2 - i) / 2;
        }
        return -1 * (n3 + 1);
    }
    
    public static final int binarySearch(final Object[] array, final Object o, final int n, final int n2) {
        int i = n;
        int n3 = n2;
        int n4 = i + (n3 - i) / 2;
        while (i < n3) {
            final int compareTo = ((Comparable)array[n4]).compareTo(o);
            if (compareTo == 0) {
                return n4;
            }
            if (compareTo < 0) {
                i = n4 + 1;
            }
            else {
                n3 = n4;
            }
            n4 = i + (n3 - i) / 2;
        }
        return -1 * (n4 + 1);
    }
    
    public static final int binarySearch(final Object[] array, final Object o, final Comparator comparator) {
        int i = 0;
        int length = array.length;
        int n = length / 2;
        while (i < length) {
            final int compare = comparator.compare(array[n], o);
            if (compare == 0) {
                return n;
            }
            if (compare < 0) {
                i = n + 1;
            }
            else {
                length = n;
            }
            n = i + (length - i) / 2;
        }
        return -1 * (n + 1);
    }
    
    public static final int binarySearch(final Object[] array, final Object o, final Comparator comparator, final int n) {
        int i = 0;
        int n2 = n;
        int n3 = n2 / 2;
        while (i < n2) {
            final int compare = comparator.compare(array[n3], o);
            if (compare == 0) {
                return n3;
            }
            if (compare < 0) {
                i = n3 + 1;
            }
            else {
                n2 = n3;
            }
            n3 = i + (n2 - i) / 2;
        }
        return -1 * (n3 + 1);
    }
    
    public static final int binarySearch(final Object[] array, final Object o, final Comparator comparator, final int n, final int n2) {
        int i = n;
        int n3 = n2;
        int n4 = i + (n3 - i) / 2;
        while (i < n3) {
            final int compare = comparator.compare(array[n4], o);
            if (compare == 0) {
                return n4;
            }
            if (compare < 0) {
                i = n4 + 1;
            }
            else {
                n3 = n4;
            }
            n4 = i + (n3 - i) / 2;
        }
        return -1 * (n4 + 1);
    }
    
    public static final int find(final int[] array, final int n) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i] == n) {
                return i;
            }
        }
        return -1;
    }
    
    public static final int find(final int[] array, final int n, final int n2) {
        for (int i = 0; i < n2; ++i) {
            if (array[i] == n) {
                return i;
            }
        }
        return -1;
    }
    
    public static final int find(final int[] array, final int n, final int n2, final int n3) {
        for (int i = n2; i < n3; ++i) {
            if (array[i] == n) {
                return i;
            }
        }
        return -1;
    }
    
    public static final int[] resize(final int[] array, final int n) {
        if (array.length >= n) {
            return array;
        }
        final int[] array2 = new int[n];
        System.arraycopy(array, 0, array2, 0, array.length);
        return array2;
    }
    
    public static final float[] resize(final float[] array, final int n) {
        if (array.length >= n) {
            return array;
        }
        final float[] array2 = new float[n];
        System.arraycopy(array, 0, array2, 0, array.length);
        return array2;
    }
    
    public static final double[] resize(final double[] array, final int n) {
        if (array.length >= n) {
            return array;
        }
        final double[] array2 = new double[n];
        System.arraycopy(array, 0, array2, 0, array.length);
        return array2;
    }
    
    public static final Object[] resize(final Object[] array, final int n) {
        if (array.length >= n) {
            return array;
        }
        final Object[] array2 = new Object[n];
        System.arraycopy(array, 0, array2, 0, array.length);
        return array2;
    }
    
    public static final int[] trim(final int[] array, final int n) {
        if (array.length == n) {
            return array;
        }
        final int[] array2 = new int[n];
        System.arraycopy(array, 0, array2, 0, n);
        return array2;
    }
    
    public static final float[] trim(final float[] array, final int n) {
        if (array.length == n) {
            return array;
        }
        final float[] array2 = new float[n];
        System.arraycopy(array, 0, array2, 0, n);
        return array2;
    }
    
    public static final double[] trim(final double[] array, final int n) {
        if (array.length == n) {
            return array;
        }
        final double[] array2 = new double[n];
        System.arraycopy(array, 0, array2, 0, n);
        return array2;
    }
    
    public static final Object[] trim(final Object[] array, final int n) {
        if (array.length == n) {
            return array;
        }
        final Object[] array2 = new Object[n];
        System.arraycopy(array, 0, array2, 0, n);
        return array2;
    }
    
    public static final void sort(final int[] array, final double[] array2) {
        mergesort(array, array2, 0, array.length - 1);
    }
    
    public static final void sort(final int[] array, final double[] array2, final int n) {
        mergesort(array, array2, 0, n - 1);
    }
    
    public static final void sort(final int[] array, final double[] array2, final int n, final int n2) {
        mergesort(array, array2, n, n2 - 1);
    }
    
    protected static final void insertionsort(final int[] array, final double[] array2, final int n, final int n2) {
        for (int i = n + 1; i <= n2; ++i) {
            final int n3 = array[i];
            final double n4 = array2[i];
            int n5;
            for (n5 = i - 1; n5 >= n && array[n5] > n3; --n5) {
                array[n5 + 1] = array[n5];
                array2[n5 + 1] = array2[n5];
            }
            array[n5 + 1] = n3;
            array2[n5 + 1] = n4;
        }
    }
    
    protected static final void mergesort(final int[] array, final double[] array2, final int n, final int n2) {
        if (n >= n2) {
            return;
        }
        if (n2 - n + 1 < 30) {
            insertionsort(array, array2, n, n2);
        }
        else {
            final int n3 = (n + n2) / 2;
            mergesort(array, array2, n, n3);
            mergesort(array, array2, n3 + 1, n2);
            merge(array, array2, n, n3, n2);
        }
    }
    
    protected static final void merge(final int[] array, final double[] array2, final int n, final int n2, final int n3) {
        final int[] array3 = new int[n3 - n + 1];
        final double[] array4 = new double[n3 - n + 1];
        int i = n;
        int j = n2 + 1;
        int n4 = 0;
        while (i <= n2 && j <= n3) {
            if (array[i] < array[j]) {
                array4[n4] = array2[i];
                array3[n4] = array[i++];
            }
            else {
                array4[n4] = array2[j];
                array3[n4] = array[j++];
            }
            ++n4;
        }
        while (i <= n2) {
            array4[n4] = array2[i];
            array3[n4] = array[i];
            ++i;
            ++n4;
        }
        while (j <= n3) {
            array4[n4] = array2[j];
            array3[n4] = array[j];
            ++j;
            ++n4;
        }
        for (int k = 0, n5 = n; k < array3.length; ++k, ++n5) {
            array2[n5] = array4[k];
            array[n5] = array3[k];
        }
    }
    
    public static final void sort(final int[] array, final int[] array2) {
        mergesort(array, array2, 0, array.length - 1);
    }
    
    public static final void sort(final int[] array, final int[] array2, final int n) {
        mergesort(array, array2, 0, n - 1);
    }
    
    public static final void sort(final int[] array, final int[] array2, final int n, final int n2) {
        mergesort(array, array2, n, n2 - 1);
    }
    
    protected static final void insertionsort(final int[] array, final int[] array2, final int n, final int n2) {
        for (int i = n + 1; i <= n2; ++i) {
            final int n3 = array[i];
            final int n4 = array2[i];
            int n5;
            for (n5 = i - 1; n5 >= n && array[n5] > n3; --n5) {
                array[n5 + 1] = array[n5];
                array2[n5 + 1] = array2[n5];
            }
            array[n5 + 1] = n3;
            array2[n5 + 1] = n4;
        }
    }
    
    protected static final void mergesort(final int[] array, final int[] array2, final int n, final int n2) {
        if (n >= n2) {
            return;
        }
        if (n2 - n + 1 < 30) {
            insertionsort(array, array2, n, n2);
        }
        else {
            final int n3 = (n + n2) / 2;
            mergesort(array, array2, n, n3);
            mergesort(array, array2, n3 + 1, n2);
            merge(array, array2, n, n3, n2);
        }
    }
    
    protected static final void merge(final int[] array, final int[] array2, final int n, final int n2, final int n3) {
        final int[] array3 = new int[n3 - n + 1];
        final int[] array4 = new int[n3 - n + 1];
        int i = n;
        int j = n2 + 1;
        int n4 = 0;
        while (i <= n2 && j <= n3) {
            if (array[i] < array[j]) {
                array4[n4] = array2[i];
                array3[n4] = array[i++];
            }
            else {
                array4[n4] = array2[j];
                array3[n4] = array[j++];
            }
            ++n4;
        }
        while (i <= n2) {
            array4[n4] = array2[i];
            array3[n4] = array[i];
            ++i;
            ++n4;
        }
        while (j <= n3) {
            array4[n4] = array2[j];
            array3[n4] = array[j];
            ++j;
            ++n4;
        }
        for (int k = 0, n5 = n; k < array3.length; ++k, ++n5) {
            array2[n5] = array4[k];
            array[n5] = array3[k];
        }
    }
    
    public static final void sort(final int[] array, final Object[] array2, final int n, final int n2) {
        final int n3 = n2 - n;
        if (n3 < 30) {
            insertionsort(array, array2, n, n2 - 1);
            return;
        }
        final int[] array3 = new int[n3];
        final Object[] array4 = new Object[n3];
        for (int i = 0, n4 = n; i < n3; ++i, ++n4) {
            array3[i] = array[n4];
            array4[i] = array2[n4];
        }
        mergesort(array3, array, array4, array2, n, n2, -n);
    }
    
    public static final void sort(final int[] array, final Object[] array2, final int[] array3, final Object[] array4, final int n, final int n2) {
        final int n3 = n2 - n;
        if (n3 < 30) {
            insertionsort(array, array2, n, n2 - 1);
            return;
        }
        for (int i = 0, n4 = n; i < n3; ++i, ++n4) {
            array3[i] = array[n4];
            array4[i] = array2[n4];
        }
        mergesort(array3, array, array4, array2, n, n2, -n);
    }
    
    protected static final void insertionsort(final int[] array, final Object[] array2, final int n, final int n2) {
        for (int i = n + 1; i <= n2; ++i) {
            final int n3 = array[i];
            final Object o = array2[i];
            int n4;
            for (n4 = i - 1; n4 >= n && array[n4] > n3; --n4) {
                array[n4 + 1] = array[n4];
                array2[n4 + 1] = array2[n4];
            }
            array[n4 + 1] = n3;
            array2[n4 + 1] = o;
        }
    }
    
    protected static void mergesort(final int[] array, final int[] array2, final Object[] array3, final Object[] array4, int n, int n2, final int n3) {
        final int n4 = n2 - n;
        if (n4 < 30) {
            insertionsort(array2, array4, n, n2 - 1);
            return;
        }
        final int n5 = n;
        final int n6 = n2;
        n += n3;
        n2 += n3;
        final int n7 = n + n2 >> 1;
        mergesort(array2, array, array4, array3, n, n7, -n3);
        mergesort(array2, array, array4, array3, n7, n2, -n3);
        if (array[n7 - 1] <= array[n7]) {
            System.arraycopy(array, n, array2, n5, n4);
            System.arraycopy(array3, n, array4, n5, n4);
            return;
        }
        int i = n5;
        int n8 = n;
        int n9 = n7;
        while (i < n6) {
            if (n9 >= n2 || (n8 < n7 && array[n8] <= array[n9])) {
                array4[i] = array3[n8];
                array2[i] = array[n8++];
            }
            else {
                array4[i] = array3[n9];
                array2[i] = array[n9++];
            }
            ++i;
        }
    }
    
    protected static final void merge(final int[] array, final Object[] array2, final int n, final int n2, final int n3) {
        final int[] array3 = new int[n3 - n + 1];
        final Object[] array4 = new Object[n3 - n + 1];
        int i = n;
        int j = n2 + 1;
        int n4 = 0;
        while (i <= n2 && j <= n3) {
            if (array[i] < array[j]) {
                array4[n4] = array2[i];
                array3[n4] = array[i++];
            }
            else {
                array4[n4] = array2[j];
                array3[n4] = array[j++];
            }
            ++n4;
        }
        while (i <= n2) {
            array4[n4] = array2[i];
            array3[n4] = array[i];
            ++i;
            ++n4;
        }
        while (j <= n3) {
            array4[n4] = array2[j];
            array3[n4] = array[j];
            ++j;
            ++n4;
        }
        for (int k = 0, n5 = n; k < array3.length; ++k, ++n5) {
            array2[n5] = array4[k];
            array[n5] = array3[k];
        }
    }
    
    public static final void sort(final double[] array, final int[] array2) {
        mergesort(array, array2, 0, array.length - 1);
    }
    
    public static final void sort(final double[] array, final int[] array2, final int n) {
        mergesort(array, array2, 0, n - 1);
    }
    
    public static final void sort(final double[] array, final int[] array2, final int n, final int n2) {
        mergesort(array, array2, n, n2 - 1);
    }
    
    protected static final void insertionsort(final double[] array, final int[] array2, final int n, final int n2) {
        for (int i = n + 1; i <= n2; ++i) {
            final double n3 = array[i];
            final int n4 = array2[i];
            int n5;
            for (n5 = i - 1; n5 >= n && array[n5] > n3; --n5) {
                array[n5 + 1] = array[n5];
                array2[n5 + 1] = array2[n5];
            }
            array[n5 + 1] = n3;
            array2[n5 + 1] = n4;
        }
    }
    
    protected static final void mergesort(final double[] array, final int[] array2, final int n, final int n2) {
        if (n >= n2) {
            return;
        }
        if (n2 - n + 1 < 30) {
            insertionsort(array, array2, n, n2);
        }
        else {
            final int n3 = (n + n2) / 2;
            mergesort(array, array2, n, n3);
            mergesort(array, array2, n3 + 1, n2);
            merge(array, array2, n, n3, n2);
        }
    }
    
    protected static final void merge(final double[] array, final int[] array2, final int n, final int n2, final int n3) {
        final double[] array3 = new double[n3 - n + 1];
        final int[] array4 = new int[n3 - n + 1];
        int i = n;
        int j = n2 + 1;
        int n4 = 0;
        while (i <= n2 && j <= n3) {
            if (array[i] < array[j]) {
                array4[n4] = array2[i];
                array3[n4] = array[i++];
            }
            else {
                array4[n4] = array2[j];
                array3[n4] = array[j++];
            }
            ++n4;
        }
        while (i <= n2) {
            array4[n4] = array2[i];
            array3[n4] = array[i];
            ++i;
            ++n4;
        }
        while (j <= n3) {
            array4[n4] = array2[j];
            array3[n4] = array[j];
            ++j;
            ++n4;
        }
        for (int k = 0, n5 = n; k < array3.length; ++k, ++n5) {
            array2[n5] = array4[k];
            array[n5] = array3[k];
        }
    }
    
    public static final void sort(final float[] array, final int[] array2) {
        mergesort(array, array2, 0, array.length - 1);
    }
    
    public static final void sort(final float[] array, final int[] array2, final int n) {
        mergesort(array, array2, 0, n - 1);
    }
    
    public static final void sort(final float[] array, final int[] array2, final int n, final int n2) {
        mergesort(array, array2, n, n2 - 1);
    }
    
    protected static final void insertionsort(final float[] array, final int[] array2, final int n, final int n2) {
        for (int i = n + 1; i <= n2; ++i) {
            final float n3 = array[i];
            final int n4 = array2[i];
            int n5;
            for (n5 = i - 1; n5 >= n && array[n5] > n3; --n5) {
                array[n5 + 1] = array[n5];
                array2[n5 + 1] = array2[n5];
            }
            array[n5 + 1] = n3;
            array2[n5 + 1] = n4;
        }
    }
    
    protected static final void mergesort(final float[] array, final int[] array2, final int n, final int n2) {
        if (n >= n2) {
            return;
        }
        if (n2 - n + 1 < 30) {
            insertionsort(array, array2, n, n2);
        }
        else {
            final int n3 = (n + n2) / 2;
            mergesort(array, array2, n, n3);
            mergesort(array, array2, n3 + 1, n2);
            merge(array, array2, n, n3, n2);
        }
    }
    
    protected static final void merge(final float[] array, final int[] array2, final int n, final int n2, final int n3) {
        final float[] array3 = new float[n3 - n + 1];
        final int[] array4 = new int[n3 - n + 1];
        int i = n;
        int j = n2 + 1;
        int n4 = 0;
        while (i <= n2 && j <= n3) {
            if (array[i] < array[j]) {
                array4[n4] = array2[i];
                array3[n4] = array[i++];
            }
            else {
                array4[n4] = array2[j];
                array3[n4] = array[j++];
            }
            ++n4;
        }
        while (i <= n2) {
            array4[n4] = array2[i];
            array3[n4] = array[i];
            ++i;
            ++n4;
        }
        while (j <= n3) {
            array4[n4] = array2[j];
            array3[n4] = array[j];
            ++j;
            ++n4;
        }
        for (int k = 0, n5 = n; k < array3.length; ++k, ++n5) {
            array2[n5] = array4[k];
            array[n5] = array3[k];
        }
    }
    
    public static final void sort(final Object[] array, final int[] array2, final Comparator comparator) {
        mergesort(array, array2, 0, array.length - 1, comparator);
    }
    
    public static final void sort(final Object[] array, final int[] array2, final int n, final Comparator comparator) {
        mergesort(array, array2, 0, n - 1, comparator);
    }
    
    public static final void sort(final Object[] array, final int[] array2, final int n, final int n2, final Comparator comparator) {
        mergesort(array, array2, n, n2 - 1, comparator);
    }
    
    protected static final void insertionsort(final Object[] array, final int[] array2, final int n, final int n2, final Comparator comparator) {
        for (int i = n + 1; i <= n2; ++i) {
            final Object o = array[i];
            final int n3 = array2[i];
            int n4;
            for (n4 = i - 1; n4 >= n && comparator.compare(array[n4], o) > 0; --n4) {
                array[n4 + 1] = array[n4];
                array2[n4 + 1] = array2[n4];
            }
            array[n4 + 1] = o;
            array2[n4 + 1] = n3;
        }
    }
    
    protected static final void mergesort(final Object[] array, final int[] array2, final int n, final int n2, final Comparator comparator) {
        if (n >= n2) {
            return;
        }
        if (n2 - n + 1 < 30) {
            insertionsort(array, array2, n, n2, comparator);
        }
        else {
            final int n3 = (n + n2) / 2;
            mergesort(array, array2, n, n3, comparator);
            mergesort(array, array2, n3 + 1, n2, comparator);
            merge(array, array2, n, n3, n2, comparator);
        }
    }
    
    protected static final void merge(final Object[] array, final int[] array2, final int n, final int n2, final int n3, final Comparator comparator) {
        final Object[] array3 = new Object[n3 - n + 1];
        final int[] array4 = new int[n3 - n + 1];
        int i = n;
        int j = n2 + 1;
        int n4 = 0;
        while (i <= n2 && j <= n3) {
            if (comparator.compare(array[i], array[j]) < 0) {
                array4[n4] = array2[i];
                array3[n4] = array[i++];
            }
            else {
                array4[n4] = array2[j];
                array3[n4] = array[j++];
            }
            ++n4;
        }
        while (i <= n2) {
            array4[n4] = array2[i];
            array3[n4] = array[i];
            ++i;
            ++n4;
        }
        while (j <= n3) {
            array4[n4] = array2[j];
            array3[n4] = array[j];
            ++j;
            ++n4;
        }
        for (int k = 0, n5 = n; k < array3.length; ++k, ++n5) {
            array2[n5] = array4[k];
            array[n5] = array3[k];
        }
    }
    
    public static int[] getIntArray(final String s) {
        try {
            final StringTokenizer stringTokenizer = new StringTokenizer(new BufferedReader(new FileReader(s)).readLine());
            final int countTokens = stringTokenizer.countTokens();
            int n = 0;
            int[] trim = new int[countTokens];
            while (stringTokenizer.hasMoreTokens()) {
                final String nextToken = stringTokenizer.nextToken();
                if (nextToken.startsWith("#")) {
                    continue;
                }
                trim[n++] = Integer.parseInt(nextToken);
            }
            if (n != countTokens) {
                trim = trim(trim, n);
            }
            return trim;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
