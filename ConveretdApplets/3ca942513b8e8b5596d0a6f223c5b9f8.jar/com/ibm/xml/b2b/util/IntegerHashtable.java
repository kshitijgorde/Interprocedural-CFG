// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util;

public final class IntegerHashtable
{
    private static final int HASHTABLE_SIZE = 128;
    private static final int INITIAL_BUCKET_COUNT = 2;
    private static final int HEADER_SIZE = 1;
    private int[][] table;
    private int keyCount;
    
    public IntegerHashtable() {
        this.table = new int[128][];
    }
    
    public int size() {
        return this.keyCount;
    }
    
    public int get(final int n) {
        if (this.keyCount == 0) {
            return -1;
        }
        final int[] array = this.table[n % 128];
        if (array != null) {
            for (int n2 = 1 + (array[0] << 1), i = 1; i < n2; i += 2) {
                if (n == array[i]) {
                    return array[i + 1];
                }
            }
        }
        return -1;
    }
    
    public int put(final int n, final int n2) {
        final int n3 = n % 128;
        int[] array = this.table[n3];
        if (array != null) {
            final int n4 = 1 + (array[0] << 1);
            for (int i = 1; i < n4; i += 2) {
                if (n == array[i]) {
                    final int n5 = array[i + 1];
                    array[i + 1] = n2;
                    return n5;
                }
            }
            ++array[0];
            if (n4 >= array.length) {
                final int[] array2 = new int[(array.length << 1) - 1];
                System.arraycopy(array, 0, array2, 0, array.length);
                array = array2;
                this.table[n3] = array;
            }
            array[n4] = n;
            array[n4 + 1] = n2;
        }
        else {
            this.table[n3] = new int[] { 1, n, n2, 0, 0 };
        }
        ++this.keyCount;
        return -1;
    }
    
    public void getKeys(final int[] array) {
        for (int n = 0, n2 = 0; this.keyCount > n && n2 < 128; ++n2) {
            final int[] array2 = this.table[n2];
            if (array2 != null) {
                for (int n3 = 1 + (array2[0] << 1), i = 1; i < n3; i += 2) {
                    array[n++] = array2[i];
                }
            }
        }
    }
    
    public void clear(final boolean b) {
        for (int n = 0; this.keyCount > 0 && n < 128; ++n) {
            final int[] array = this.table[n];
            if (array != null) {
                this.keyCount -= array[0];
                if (b) {
                    array[0] = 0;
                }
                else {
                    this.table[n] = null;
                }
            }
        }
    }
}
