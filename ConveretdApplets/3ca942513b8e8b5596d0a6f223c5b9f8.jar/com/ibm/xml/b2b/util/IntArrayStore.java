// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util;

final class IntArrayStore
{
    private static final boolean DEBUG_COUNTERS = false;
    private static final boolean DEBUG_ALLOCATE = false;
    private static final int RECLAIM_LIMIT = 68;
    private static final int RECLAIM_BUCKET_LENGTH = 16;
    private static int[] fgReclaimMap;
    private int[] fReclaimListCount;
    private int[][][] fReclaimList;
    private int[] fReclaimHits;
    private int[] fReclaimMisses;
    
    public IntArrayStore() {
        this.fReclaimListCount = new int[9];
        this.fReclaimList = new int[][][] { new int[16][], new int[16][], new int[16][], new int[16][], new int[16][], new int[16][], new int[16][], new int[16][], new int[16][] };
    }
    
    public void reset(final boolean b) {
    }
    
    public int[] allocIntArray(final int n) {
        int n2;
        if (n < 68 && (n2 = IntArrayStore.fgReclaimMap[n]) != 0) {
            int n3 = this.fReclaimListCount[--n2];
            if (n3-- > 0) {
                final int[] array = this.fReclaimList[n2][n3];
                this.fReclaimListCount[n2] = n3;
                for (int i = 0; i < n; ++i) {
                    array[i] = 0;
                }
                return array;
            }
        }
        return new int[n];
    }
    
    public int[] reallocIntArray(final int[] array, final int n, final int n2) {
        int[] array2 = null;
        int n3;
        if (n2 < 68 && (n3 = IntArrayStore.fgReclaimMap[n2]) != 0) {
            int n4 = this.fReclaimListCount[--n3];
            if (n4-- > 0) {
                array2 = this.fReclaimList[n3][n4];
                this.fReclaimListCount[n3] = n4;
                for (int i = n; i < n2; ++i) {
                    array2[i] = 0;
                }
            }
        }
        if (array2 == null) {
            array2 = new int[n2];
        }
        System.arraycopy(array, 0, array2, 0, n);
        int n5;
        if (n < 68 && (n5 = IntArrayStore.fgReclaimMap[n]) != 0) {
            int n6 = this.fReclaimListCount[--n5];
            if (n6 < 16) {
                this.fReclaimList[n5][n6] = array;
                this.fReclaimListCount[n5] = ++n6;
                return array2;
            }
        }
        return array2;
    }
    
    public void deallocIntArray(final int[] array, final int n) {
        int n2;
        if (n < 68 && (n2 = IntArrayStore.fgReclaimMap[n]) != 0) {
            int n3 = this.fReclaimListCount[--n2];
            if (n3 < 16) {
                this.fReclaimList[n2][n3] = array;
                this.fReclaimListCount[n2] = ++n3;
            }
        }
    }
    
    public void dumpCounters() {
    }
    
    static {
        IntArrayStore.fgReclaimMap = new int[] { 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 2, 0, 3, 0, 0, 0, 0, 0, 0, 0, 4, 0, 0, 0, 0, 0, 0, 0, 5, 0, 0, 0, 0, 0, 0, 0, 6, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 8, 0, 0, 0, 0, 0, 0, 0, 9 };
    }
}
