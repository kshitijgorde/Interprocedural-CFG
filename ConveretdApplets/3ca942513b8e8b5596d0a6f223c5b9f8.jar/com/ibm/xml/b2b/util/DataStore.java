// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xml.b2b.util;

public final class DataStore
{
    private static final boolean CHECK_HANDLE_TYPE = true;
    private static final boolean DEBUG_ALLOCATE = false;
    private static final boolean DEBUG_RECLAIM = false;
    private static final int INITIAL_LIST_COUNT = 4;
    private IntArrayStore fArrayStore;
    private int CHUNK_SHIFT;
    private int CHUNK_SIZE;
    private int CHUNK_MASK;
    private int INITIAL_CHUNK_COUNT;
    private int fHandleCount;
    private int[][] fHandleMap;
    private int fCurrentRecordChunk;
    private int[][] fRecordHeap;
    private int fListCount;
    private int[][] fListMap;
    private int fMutipleElementListCount;
    private int[][] fMutipleElementList;
    
    public DataStore() {
        this.CHUNK_SHIFT = 8;
        this.CHUNK_SIZE = 1 << this.CHUNK_SHIFT;
        this.CHUNK_MASK = this.CHUNK_SIZE - 1;
        this.INITIAL_CHUNK_COUNT = 1 << 16 - this.CHUNK_SHIFT;
        this.fHandleMap = new int[this.INITIAL_CHUNK_COUNT][];
        this.fRecordHeap = new int[this.INITIAL_CHUNK_COUNT][];
        this.fListMap = new int[this.INITIAL_CHUNK_COUNT][];
        this.fMutipleElementList = new int[this.INITIAL_CHUNK_COUNT][];
        this.fArrayStore = new IntArrayStore();
        this.fHandleMap[0] = this.fArrayStore.allocIntArray(this.CHUNK_SIZE);
        (this.fRecordHeap[0] = this.fArrayStore.allocIntArray(this.CHUNK_SIZE))[0] = 1;
    }
    
    public void reset(final boolean b) {
        if (!b) {
            for (int n = this.fHandleCount >>> this.CHUNK_SHIFT - 1, i = 1; i < n; ++i) {
                if (this.fHandleMap[i] != null) {
                    this.fArrayStore.deallocIntArray(this.fHandleMap[i], this.CHUNK_SIZE);
                    this.fHandleMap[i] = null;
                }
            }
            for (int n2 = this.fCurrentRecordChunk + 1, j = 1; j < n2; ++j) {
                if (this.fRecordHeap[j] != null) {
                    this.fArrayStore.deallocIntArray(this.fRecordHeap[j], this.CHUNK_SIZE);
                    this.fRecordHeap[j] = null;
                }
            }
            for (int n3 = this.fListCount >>> this.CHUNK_SHIFT - 1, k = 0; k < n3; ++k) {
                if (this.fListMap[k] != null) {
                    this.fArrayStore.deallocIntArray(this.fListMap[k], this.CHUNK_SIZE);
                    this.fListMap[k] = null;
                }
            }
            for (int fMutipleElementListCount = this.fMutipleElementListCount, l = 0; l < fMutipleElementListCount; ++l) {
                if (this.fMutipleElementList[l] == null) {
                    this.fArrayStore.deallocIntArray(this.fMutipleElementList[l], this.fMutipleElementList[l].length);
                    this.fMutipleElementList[l] = null;
                }
            }
        }
        this.fArrayStore.reset(b);
        this.fRecordHeap[0][0] = 1;
        this.fHandleCount = 0;
        this.fCurrentRecordChunk = 0;
        this.fListCount = 0;
        this.fMutipleElementListCount = 0;
    }
    
    public int allocateHandle(final int n, final int n2) {
        final int n3 = this.fHandleCount++;
        final int n4 = n3 >>> this.CHUNK_SHIFT - 1;
        if (n4 > 0) {
            if (n4 >= this.INITIAL_CHUNK_COUNT && (n4 & n4 - 1) == 0x0 && this.fHandleMap.length <= n4) {
                this.fHandleMap = this.reallocIntArray2(this.fHandleMap, n4, n4 << 1);
            }
            if (this.fHandleMap[n4] == null) {
                this.fHandleMap[n4] = this.fArrayStore.allocIntArray(this.CHUNK_SIZE);
            }
        }
        final int n5 = n3 << 1 & this.CHUNK_MASK;
        this.fHandleMap[n4][n5] = n;
        this.fHandleMap[n4][n5 + 1] = n2;
        return n3;
    }
    
    public int getHandleType(final int n) {
        if (n < 0 || n >= this.fHandleCount) {
            throw new ArrayIndexOutOfBoundsException(n);
        }
        return this.fHandleMap[n >>> this.CHUNK_SHIFT - 1][n << 1 & this.CHUNK_MASK];
    }
    
    public int getHandleValue(final int n, final int n2) {
        if (n < 0 || n >= this.fHandleCount) {
            throw new ArrayIndexOutOfBoundsException(n);
        }
        final int n3 = n >>> this.CHUNK_SHIFT - 1;
        final int n4 = n << 1 & this.CHUNK_MASK;
        if (this.fHandleMap[n3][n4] != n2) {
            throw new ArrayIndexOutOfBoundsException(n);
        }
        return this.fHandleMap[n3][n4 + 1];
    }
    
    public int allocateRecord(final int n) {
        final int fCurrentRecordChunk = this.fCurrentRecordChunk;
        final int[] array = this.fRecordHeap[fCurrentRecordChunk];
        final int n2 = array[0];
        if (n2 + n <= this.CHUNK_SIZE) {
            array[0] = n2 + n;
            return (fCurrentRecordChunk << this.CHUNK_SHIFT) + n2;
        }
        for (int i = 0; i < fCurrentRecordChunk; ++i) {
            final int n3 = this.fRecordHeap[i][0];
            if (n3 + n <= this.CHUNK_SIZE) {
                this.fRecordHeap[i][0] = n3 + n;
                return (i << this.CHUNK_SHIFT) + n3;
            }
        }
        final int n4 = ++this.fCurrentRecordChunk;
        final int n5 = 1;
        if (n4 >= this.INITIAL_CHUNK_COUNT && (n4 & n4 - 1) == 0x0 && this.fRecordHeap.length <= n4) {
            this.fRecordHeap = this.reallocIntArray2(this.fRecordHeap, n4, n4 << 1);
        }
        if (this.fRecordHeap[n4] == null) {
            this.fRecordHeap[n4] = this.fArrayStore.allocIntArray(this.CHUNK_SIZE);
        }
        this.fRecordHeap[n4][0] = n5 + n;
        return (n4 << this.CHUNK_SHIFT) + n5;
    }
    
    public int getRecordValue(final int n, final int n2) {
        return this.fRecordHeap[n >>> this.CHUNK_SHIFT][(n & this.CHUNK_MASK) + n2];
    }
    
    public int setRecordValue(final int n, final int n2, final int n3) {
        final int n4 = n >>> this.CHUNK_SHIFT;
        final int n5 = n & this.CHUNK_MASK;
        final int n6 = this.fRecordHeap[n4][n5 + n2];
        this.fRecordHeap[n4][n5 + n2] = n3;
        return n6;
    }
    
    public int[] getRecordArray(final int n) {
        return this.fRecordHeap[n >>> this.CHUNK_SHIFT];
    }
    
    public int getRecordBase(final int n) {
        return n & this.CHUNK_MASK;
    }
    
    public int addToList(int n, final int n2) {
        if (n == -1) {
            n = this.fListCount++;
            final int n3 = n >>> this.CHUNK_SHIFT - 1;
            final int n4 = n << 1 & this.CHUNK_MASK;
            if (n3 == this.fListMap.length) {
                this.fListMap = this.reallocIntArray2(this.fListMap, n3, n3 << 1);
            }
            if (this.fListMap[n3] == null) {
                this.fListMap[n3] = this.fArrayStore.allocIntArray(this.CHUNK_SIZE);
            }
            this.fListMap[n3][n4] = 0;
            this.fListMap[n3][n4 + 1] = n2;
            return n;
        }
        final int n5 = n >>> this.CHUNK_SHIFT - 1;
        final int n6 = n << 1 & this.CHUNK_MASK;
        int[] array;
        int n8;
        if (this.fListMap[n5][n6] >= 0) {
            final int n7 = this.fMutipleElementListCount++;
            if (n7 == this.fMutipleElementList.length) {
                this.fMutipleElementList = this.reallocIntArray2(this.fMutipleElementList, n7, n7 << 1);
            }
            if (this.fMutipleElementList[n7] == null) {
                array = this.fArrayStore.allocIntArray(5);
                this.fMutipleElementList[n7] = array;
            }
            else {
                array = this.fMutipleElementList[n7];
            }
            array[array[0] = 1] = this.fListMap[n5][n6 + 1];
            n8 = 1;
            this.fListMap[n5][n6] = -1;
            this.fListMap[n5][n6 + 1] = n7;
        }
        else {
            final int n9 = this.fListMap[n5][n6 + 1];
            array = this.fMutipleElementList[n9];
            n8 = array[0];
            if (n8 >= 4 && (n8 & n8 - 1) == 0x0 && array.length <= n8 + 1) {
                int i;
                for (i = 4; i < n8; i <<= 2) {}
                if (i == n8) {
                    array = this.fArrayStore.reallocIntArray(array, 1 + n8, 1 + (n8 << 2));
                    this.fMutipleElementList[n9] = array;
                }
            }
        }
        ++n8;
        array[n8] = n2;
        ++array[0];
        return n;
    }
    
    public void addValueToListInRecord(final int n, final int n2, final int n3) {
        final int n4 = n >>> this.CHUNK_SHIFT;
        final int n5 = n & this.CHUNK_MASK;
        final int n6 = this.fRecordHeap[n4][n5 + n2];
        final int addToList = this.addToList(n6, n3);
        if (addToList != n6) {
            this.fRecordHeap[n4][n5 + n2] = addToList;
        }
    }
    
    public void replaceValueOfList(final int n, final int n2) {
        final int n3 = n >>> this.CHUNK_SHIFT - 1;
        final int n4 = n << 1 & this.CHUNK_MASK;
        if (this.fListMap[n3][n4] < 0) {}
        this.fListMap[n3][n4] = 0;
        this.fListMap[n3][n4 + 1] = n2;
    }
    
    public int[] getListArray(final int n) {
        final int n2 = n >>> this.CHUNK_SHIFT - 1;
        final int n3 = n << 1 & this.CHUNK_MASK;
        if (this.fListMap[n2][n3] >= 0) {
            return this.fListMap[n2];
        }
        return this.fMutipleElementList[this.fListMap[n2][n3 + 1]];
    }
    
    public int getListBase(final int n) {
        final int n2 = n >>> this.CHUNK_SHIFT - 1;
        final int n3 = n << 1 & this.CHUNK_MASK;
        if (this.fListMap[n2][n3] >= 0) {
            return 1 + n3;
        }
        return 1;
    }
    
    public int getListCount(final int n) {
        final int n2 = n >>> this.CHUNK_SHIFT - 1;
        final int n3 = n << 1 & this.CHUNK_MASK;
        if (this.fListMap[n2][n3] >= 0) {
            return 1;
        }
        return this.fMutipleElementList[this.fListMap[n2][n3 + 1]][0];
    }
    
    private int[][] reallocIntArray2(final int[][] array, final int n, final int n2) {
        final int[][] array2 = new int[n2][];
        System.arraycopy(array, 0, array2, 0, n);
        return array2;
    }
}
