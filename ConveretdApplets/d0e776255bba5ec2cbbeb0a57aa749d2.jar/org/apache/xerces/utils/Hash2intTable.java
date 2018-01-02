// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.utils;

public final class Hash2intTable
{
    private static final int INITIAL_BUCKET_SIZE = 4;
    private static final int HASHTABLE_SIZE = 256;
    private int[][] fHashTable;
    
    public Hash2intTable() {
        this.fHashTable = new int[256][];
    }
    
    public void put(final int n, final int n2, final int n3, final int n4) {
        final int n5 = (n + n2 + n3 + 2) % 256;
        int[] array = this.fHashTable[n5];
        if (array == null) {
            final int[] array2 = new int[17];
            array2[array2[0] = 1] = n;
            array2[2] = n2;
            array2[3] = n3;
            array2[4] = n4;
            this.fHashTable[n5] = array2;
        }
        else {
            int n6 = array[0];
            int n7 = 1 + 4 * n6;
            if (n7 == array.length) {
                final int[] array3 = new int[1 + 4 * (n6 + 4)];
                System.arraycopy(array, 0, array3, 0, n7);
                array = array3;
                this.fHashTable[n5] = array;
            }
            boolean b = false;
            int n8 = 1;
            for (int i = 0; i < n6; ++i) {
                if (array[n8] == n && array[n8 + 1] == n2 && array[n8 + 2] == n3) {
                    array[n8 + 3] = n4;
                    b = true;
                    break;
                }
                n8 += 4;
            }
            if (!b) {
                array[n7++] = n;
                array[n7++] = n2;
                array[n7++] = n3;
                array[n7] = n4;
                array[0] = ++n6;
            }
        }
    }
    
    public int get(final int n, final int n2, final int n3) {
        final int[] array = this.fHashTable[(n + n2 + n3 + 2) % 256];
        if (array == null) {
            return -1;
        }
        final int n4 = array[0];
        int n5 = 1;
        for (int i = 0; i < n4; ++i) {
            if (array[n5] == n && array[n5 + 1] == n2 && array[n5 + 2] == n3) {
                return array[n5 + 3];
            }
            n5 += 4;
        }
        return -1;
    }
}
