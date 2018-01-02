// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.utils;

public final class StringPool
{
    private static final boolean DEBUG_ADDITIONS = false;
    public static final int NULL_STRING = -1;
    public static final int EMPTY_STRING = 0;
    private static final int INITIAL_CHUNK_SHIFT = 8;
    private static final int INITIAL_CHUNK_SIZE = 256;
    private static final int CHUNK_SHIFT = 13;
    private static final int CHUNK_SIZE = 8192;
    private static final int CHUNK_MASK = 8191;
    private static final int INITIAL_CHUNK_COUNT = 8;
    private int fStringCount;
    private int fStringFreeList;
    private String[][] fString;
    private StringProducer[][] fStringProducer;
    private int[][] fOffset;
    private int[][] fLength;
    private int[][] fCharsOffset;
    private int fStringListCount;
    private int fActiveStringList;
    private int[][] fStringList;
    private static final int INITIAL_BUCKET_SIZE = 4;
    private static final int HASHTABLE_SIZE = 128;
    private int[][] fSymbolTable;
    private SymbolCache fSymbolCache;
    private int fShuffleCount;
    
    public StringPool() {
        this.fStringCount = 0;
        this.fStringFreeList = -1;
        this.fString = new String[8][];
        this.fStringProducer = new StringProducer[8][];
        this.fOffset = new int[8][];
        this.fLength = new int[8][];
        this.fCharsOffset = new int[8][];
        this.fStringListCount = 0;
        this.fActiveStringList = -1;
        this.fStringList = new int[8][];
        this.fSymbolTable = new int[128][];
        this.fSymbolCache = null;
        this.fShuffleCount = 0;
        this.fSymbolCache = new SymbolCache();
        if (this.addSymbol("") != 0) {
            throw new RuntimeException("UTL002 cannot happen");
        }
    }
    
    public void reset() {
        int n = 0;
        int n2 = 0;
        for (int i = 0; i < this.fStringCount; ++i) {
            this.fString[n][n2] = null;
            if (this.fStringProducer[n][n2] != null) {
                this.fStringProducer[n][n2].releaseString(this.fOffset[n][n2], this.fLength[n][n2]);
            }
            this.fStringProducer[n][n2] = null;
            if (++n2 == 8192) {
                ++n;
                n2 = 0;
            }
        }
        for (int j = 0; j < 128; ++j) {
            this.fSymbolTable[j] = null;
        }
        this.fStringCount = 0;
        this.fStringFreeList = -1;
        this.fStringListCount = 0;
        this.fActiveStringList = -1;
        this.fSymbolCache.reset();
        this.fShuffleCount = 0;
        if (this.addSymbol("") != 0) {
            throw new RuntimeException("UTL002 cannot happen");
        }
    }
    
    private boolean ensureCapacity(final int n, final int n2) {
        try {
            return this.fOffset[n][n2] == 0;
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            if (n2 != 0) {
                final String[] array = new String[n2 * 2];
                System.arraycopy(this.fString[n], 0, array, 0, n2);
                this.fString[n] = array;
                final StringProducer[] array2 = new StringProducer[n2 * 2];
                System.arraycopy(this.fStringProducer[n], 0, array2, 0, n2);
                this.fStringProducer[n] = array2;
                final int[] array3 = new int[n2 * 2];
                System.arraycopy(this.fOffset[n], 0, array3, 0, n2);
                this.fOffset[n] = array3;
                final int[] array4 = new int[n2 * 2];
                System.arraycopy(this.fLength[n], 0, array4, 0, n2);
                this.fLength[n] = array4;
                final int[] array5 = new int[n2 * 2];
                System.arraycopy(this.fCharsOffset[n], 0, array5, 0, n2);
                this.fCharsOffset[n] = array5;
                return true;
            }
            final String[][] fString = new String[n * 2][];
            System.arraycopy(this.fString, 0, fString, 0, n);
            this.fString = fString;
            final StringProducer[][] fStringProducer = new StringProducer[n * 2][];
            System.arraycopy(this.fStringProducer, 0, fStringProducer, 0, n);
            this.fStringProducer = fStringProducer;
            final int[][] fOffset = new int[n * 2][];
            System.arraycopy(this.fOffset, 0, fOffset, 0, n);
            this.fOffset = fOffset;
            final int[][] fLength = new int[n * 2][];
            System.arraycopy(this.fLength, 0, fLength, 0, n);
            this.fLength = fLength;
            final int[][] fCharsOffset = new int[n * 2][];
            System.arraycopy(this.fCharsOffset, 0, fCharsOffset, 0, n);
            this.fCharsOffset = fCharsOffset;
        }
        catch (NullPointerException ex2) {}
        this.fString[n] = new String[256];
        this.fStringProducer[n] = new StringProducer[256];
        this.fOffset[n] = new int[256];
        this.fLength[n] = new int[256];
        this.fCharsOffset[n] = new int[256];
        return true;
    }
    
    public int addString(final String s) {
        int fStringFreeList;
        int n;
        int n2;
        if (this.fStringFreeList != -1) {
            fStringFreeList = this.fStringFreeList;
            n = fStringFreeList >> 13;
            n2 = (fStringFreeList & 0x1FFF);
            this.fStringFreeList = this.fOffset[n][n2];
        }
        else {
            fStringFreeList = this.fStringCount++;
            n = fStringFreeList >> 13;
            n2 = (fStringFreeList & 0x1FFF);
            this.ensureCapacity(n, n2);
        }
        this.fString[n][n2] = s;
        this.fStringProducer[n][n2] = null;
        this.fOffset[n][n2] = 0;
        this.fLength[n][n2] = s.length();
        this.fCharsOffset[n][n2] = -1;
        return fStringFreeList;
    }
    
    public int addString(final StringProducer stringProducer, final int n, final int n2) {
        int fStringFreeList;
        int n3;
        int n4;
        if (this.fStringFreeList != -1) {
            fStringFreeList = this.fStringFreeList;
            n3 = fStringFreeList >> 13;
            n4 = (fStringFreeList & 0x1FFF);
            this.fStringFreeList = this.fOffset[n3][n4];
        }
        else {
            fStringFreeList = this.fStringCount++;
            n3 = fStringFreeList >> 13;
            n4 = (fStringFreeList & 0x1FFF);
            this.ensureCapacity(n3, n4);
        }
        this.fString[n3][n4] = null;
        this.fStringProducer[n3][n4] = stringProducer;
        this.fOffset[n3][n4] = n;
        this.fLength[n3][n4] = n2;
        this.fCharsOffset[n3][n4] = -1;
        return fStringFreeList;
    }
    
    public SymbolCache getSymbolCache() {
        return this.fSymbolCache;
    }
    
    public void resetShuffleCount() {
        this.fShuffleCount = 0;
    }
    
    public void updateCacheLine(final int n, final int n2, final int n3) {
        if (++this.fShuffleCount > 200) {
            return;
        }
        this.fSymbolCache.updateCacheLine(this.fCharsOffset[n >> 13][n & 0x1FFF], n2, n3);
    }
    
    public int createNonMatchingSymbol(final int n, final int n2, final int[] array, final int n3) throws Exception {
        int fStringFreeList;
        int n4;
        int n5;
        if (this.fStringFreeList != -1) {
            fStringFreeList = this.fStringFreeList;
            n4 = fStringFreeList >> 13;
            n5 = (fStringFreeList & 0x1FFF);
            this.fStringFreeList = this.fOffset[n4][n5];
        }
        else {
            fStringFreeList = this.fStringCount++;
            n4 = fStringFreeList >> 13;
            n5 = (fStringFreeList & 0x1FFF);
            this.ensureCapacity(n4, n5);
        }
        final String symbol = this.fSymbolCache.createSymbol(fStringFreeList, n, n2, array, n3);
        final int length = symbol.length();
        this.fString[n4][n5] = symbol;
        this.fStringProducer[n4][n5] = null;
        this.fOffset[n4][n5] = -1;
        this.fLength[n4][n5] = length;
        this.fCharsOffset[n4][n5] = n;
        final int hashString = StringHasher.hashString(symbol, length);
        this.hashSymbol(this.fSymbolTable[hashString % 128], hashString, n4, n5);
        return fStringFreeList;
    }
    
    private void hashSymbol(int[] array, final int n, final int n2, final int n3) {
        if (array == null) {
            array = new int[13];
            array[array[0] = 1] = n;
            array[2] = n2;
            array[3] = n3;
            this.fSymbolTable[n % 128] = array;
        }
        else {
            int n4 = array[0];
            int n5 = 1 + n4 * 3;
            if (n5 == array.length) {
                final int[] array2 = new int[1 + (n4 + 4) * 3];
                System.arraycopy(array, 0, array2, 0, n5);
                array = array2;
                this.fSymbolTable[n % 128] = array;
            }
            array[n5++] = n;
            array[n5++] = n2;
            array[n5++] = n3;
            array[0] = ++n4;
        }
    }
    
    public int addSymbol(final String s) {
        final int length = s.length();
        final int hashString = StringHasher.hashString(s, length);
        final int[] array = this.fSymbolTable[hashString % 128];
        if (array != null) {
            int n = 1;
            for (int i = 0; i < array[0]; ++i) {
                if (array[n] == hashString) {
                    final int n2 = array[n + 1];
                    final int n3 = array[n + 2];
                    if (length == this.fLength[n2][n3]) {
                        int n4 = this.fCharsOffset[n2][n3];
                        boolean b = true;
                        final char[] symbolChars = this.fSymbolCache.getSymbolChars();
                        for (int j = 0; j < length; ++j) {
                            if (symbolChars[n4++] != s.charAt(j)) {
                                b = false;
                                break;
                            }
                        }
                        if (b) {
                            return (n2 << 13) + n3;
                        }
                    }
                }
                n += 3;
            }
        }
        int fStringFreeList;
        int n5;
        int n6;
        if (this.fStringFreeList != -1) {
            fStringFreeList = this.fStringFreeList;
            n5 = fStringFreeList >> 13;
            n6 = (fStringFreeList & 0x1FFF);
            this.fStringFreeList = this.fOffset[n5][n6];
        }
        else {
            fStringFreeList = this.fStringCount++;
            n5 = fStringFreeList >> 13;
            n6 = (fStringFreeList & 0x1FFF);
            this.ensureCapacity(n5, n6);
        }
        this.fString[n5][n6] = s;
        this.fStringProducer[n5][n6] = null;
        this.fOffset[n5][n6] = -1;
        this.fLength[n5][n6] = length;
        this.fCharsOffset[n5][n6] = this.fSymbolCache.addSymbolToCache(s, length, fStringFreeList);
        this.hashSymbol(array, hashString, n5, n6);
        return fStringFreeList;
    }
    
    public int addSymbol(final StringProducer stringProducer, final int n, final int n2, final int n3) {
        final int[] array = this.fSymbolTable[n3 % 128];
        if (array != null) {
            int n4 = 1;
            for (int i = 0; i < array[0]; ++i) {
                if (array[n4] == n3) {
                    final int n5 = array[n4 + 1];
                    final int n6 = array[n4 + 2];
                    if (stringProducer.equalsString(n, n2, this.fSymbolCache.getSymbolChars(), this.fCharsOffset[n5][n6], this.fLength[n5][n6])) {
                        stringProducer.releaseString(n, n2);
                        return (n5 << 13) + n6;
                    }
                }
                n4 += 3;
            }
        }
        int fStringFreeList;
        int n7;
        int n8;
        if (this.fStringFreeList != -1) {
            fStringFreeList = this.fStringFreeList;
            n7 = fStringFreeList >> 13;
            n8 = (fStringFreeList & 0x1FFF);
            this.fStringFreeList = this.fOffset[n7][n8];
        }
        else {
            fStringFreeList = this.fStringCount++;
            n7 = fStringFreeList >> 13;
            n8 = (fStringFreeList & 0x1FFF);
            this.ensureCapacity(n7, n8);
        }
        final String string = stringProducer.toString(n, n2);
        stringProducer.releaseString(n, n2);
        final int length = string.length();
        this.fString[n7][n8] = string;
        this.fStringProducer[n7][n8] = null;
        this.fOffset[n7][n8] = -1;
        this.fLength[n7][n8] = length;
        this.fCharsOffset[n7][n8] = this.fSymbolCache.addSymbolToCache(string, length, fStringFreeList);
        this.hashSymbol(array, n3, n7, n8);
        return fStringFreeList;
    }
    
    public int lookupSymbol(final StringProducer stringProducer, final int n, final int n2, final int n3) {
        final int[] array = this.fSymbolTable[n3 % 128];
        if (array != null) {
            int n4 = 1;
            for (int i = 0; i < array[0]; ++i) {
                if (array[n4] == n3) {
                    final int n5 = array[n4 + 1];
                    final int n6 = array[n4 + 2];
                    if (stringProducer.equalsString(n, n2, this.fSymbolCache.getSymbolChars(), this.fCharsOffset[n5][n6], this.fLength[n5][n6])) {
                        return (n5 << 13) + n6;
                    }
                }
                n4 += 3;
            }
        }
        return -1;
    }
    
    public int addNewSymbol(final String s, final int n) {
        final int[] array = this.fSymbolTable[n % 128];
        int fStringFreeList;
        int n2;
        int n3;
        if (this.fStringFreeList != -1) {
            fStringFreeList = this.fStringFreeList;
            n2 = fStringFreeList >> 13;
            n3 = (fStringFreeList & 0x1FFF);
            this.fStringFreeList = this.fOffset[n2][n3];
        }
        else {
            fStringFreeList = this.fStringCount++;
            n2 = fStringFreeList >> 13;
            n3 = (fStringFreeList & 0x1FFF);
            this.ensureCapacity(n2, n3);
        }
        final int length = s.length();
        this.fString[n2][n3] = s;
        this.fStringProducer[n2][n3] = null;
        this.fOffset[n2][n3] = -1;
        this.fLength[n2][n3] = length;
        this.fCharsOffset[n2][n3] = this.fSymbolCache.addSymbolToCache(s, length, fStringFreeList);
        this.hashSymbol(array, n, n2, n3);
        return fStringFreeList;
    }
    
    public int addSymbol(final int n) {
        if (n < 0 || n >= this.fStringCount) {
            return -1;
        }
        final int n2 = n >> 13;
        final int n3 = n & 0x1FFF;
        if (this.fOffset[n2][n3] == -1) {
            return n;
        }
        String string = this.fString[n2][n3];
        if (string == null) {
            string = this.fStringProducer[n2][n3].toString(this.fOffset[n2][n3], this.fLength[n2][n3]);
            this.fStringProducer[n2][n3].releaseString(this.fOffset[n2][n3], this.fLength[n2][n3]);
            this.fString[n2][n3] = string;
            this.fStringProducer[n2][n3] = null;
        }
        return this.addSymbol(string);
    }
    
    public CharArrayRange createCharArrayRange() {
        return new CharArrayRange();
    }
    
    public void getCharArrayRange(final int n, final CharArrayRange charArrayRange) {
        if (n < 0 || n >= this.fStringCount) {
            charArrayRange.chars = null;
            charArrayRange.offset = -1;
            charArrayRange.length = -1;
            return;
        }
        final int n2 = n >> 13;
        final int n3 = n & 0x1FFF;
        charArrayRange.chars = this.fSymbolCache.getSymbolChars();
        charArrayRange.offset = this.fCharsOffset[n2][n3];
        charArrayRange.length = this.fLength[n2][n3];
    }
    
    public boolean equalNames(final int n, final int n2) {
        return n == n2;
    }
    
    private boolean ensureListCapacity(final int n, final int n2) {
        try {
            return this.fStringList[n][n2] == 0;
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            if (n2 != 0) {
                final int[] array = new int[n2 * 2];
                System.arraycopy(this.fStringList[n], 0, array, 0, n2);
                this.fStringList[n] = array;
                return true;
            }
            final int[][] fStringList = new int[n * 2][];
            System.arraycopy(this.fStringList, 0, fStringList, 0, n);
            this.fStringList = fStringList;
        }
        catch (NullPointerException ex2) {}
        this.fStringList[n] = new int[256];
        return true;
    }
    
    public int startStringList() {
        this.fActiveStringList = this.fStringListCount;
        return this.fStringListCount;
    }
    
    public boolean addStringToList(final int n, final int n2) {
        if (n2 == -1 || n != this.fActiveStringList) {
            return false;
        }
        final int n3 = this.fStringListCount >> 13;
        final int n4 = this.fStringListCount & 0x1FFF;
        this.ensureListCapacity(n3, n4);
        this.fStringList[n3][n4] = n2;
        ++this.fStringListCount;
        return true;
    }
    
    public void finishStringList(final int n) {
        if (n != this.fActiveStringList) {
            return;
        }
        final int n2 = this.fStringListCount >> 13;
        final int n3 = this.fStringListCount & 0x1FFF;
        this.ensureListCapacity(n2, n3);
        this.fStringList[n2][n3] = -1;
        this.fActiveStringList = -1;
        ++this.fStringListCount;
    }
    
    public int stringListLength(final int n) {
        int n2 = n >> 13;
        int n3 = n & 0x1FFF;
        int n4 = 0;
        while (this.fStringList[n2][n3] != -1) {
            ++n4;
            if (++n3 == 8192) {
                ++n2;
                n3 = 0;
            }
        }
        return n4;
    }
    
    public boolean stringInList(final int n, final int n2) {
        int n3 = n >> 13;
        int n4 = n & 0x1FFF;
        while (this.fStringList[n3][n4] != n2) {
            if (this.fStringList[n3][n4] == -1) {
                return false;
            }
            if (++n4 != 8192) {
                continue;
            }
            ++n3;
            n4 = 0;
        }
        return true;
    }
    
    public String stringListAsString(final int n) {
        int n2 = n >> 13;
        int n3 = n & 0x1FFF;
        final StringBuffer sb = new StringBuffer();
        char c = '(';
        while (this.fStringList[n2][n3] != -1) {
            sb.append(c);
            c = '|';
            sb.append(this.toString(this.fStringList[n2][n3]));
            if (++n3 == 8192) {
                ++n2;
                n3 = 0;
            }
        }
        if (c == '|') {
            sb.append(')');
        }
        return sb.toString();
    }
    
    public int[] stringListAsIntArray(final int n) {
        int n2 = n >> 13;
        int n3 = n & 0x1FFF;
        final int stringListLength = this.stringListLength(n);
        final int[] array = new int[stringListLength];
        for (int i = 0; i < stringListLength; ++i) {
            array[i] = this.fStringList[n2][n3];
            if (++n3 == 8192) {
                ++n2;
                n3 = 0;
            }
        }
        return array;
    }
    
    private void releaseStringInternal(final int n, final int n2) {
        this.fString[n][n2] = null;
        this.fStringProducer[n][n2] = null;
        this.fLength[n][n2] = 0;
        this.fOffset[n][n2] = this.fStringFreeList;
        this.fStringFreeList = (n << 13) + n2;
    }
    
    public void releaseString(final int n) {
        if (n < 0 || n >= this.fStringCount) {
            return;
        }
        final int n2 = n >> 13;
        final int n3 = n & 0x1FFF;
        if (this.fOffset[n2][n3] != -1) {
            if (this.fStringProducer[n2][n3] != null) {
                this.fStringProducer[n2][n3].releaseString(this.fOffset[n2][n3], this.fLength[n2][n3]);
            }
            this.releaseStringInternal(n2, n3);
        }
    }
    
    public String toString(final int n) {
        if (n >= 0 && n < this.fString[0].length) {
            final String s = this.fString[0][n];
            if (s != null) {
                return s;
            }
        }
        if (n < 0 || n >= this.fStringCount) {
            return null;
        }
        final int n2 = n >> 13;
        final int n3 = n & 0x1FFF;
        final String s2 = this.fString[n2][n3];
        if (s2 != null) {
            return s2;
        }
        final String string = this.fStringProducer[n2][n3].toString(this.fOffset[n2][n3], this.fLength[n2][n3]);
        this.fStringProducer[n2][n3].releaseString(this.fOffset[n2][n3], this.fLength[n2][n3]);
        this.fString[n2][n3] = string;
        this.fStringProducer[n2][n3] = null;
        return string;
    }
    
    public String orphanString(final int n) {
        if (n < 0 || n >= this.fStringCount) {
            return null;
        }
        final int n2 = n >> 13;
        final int n3 = n & 0x1FFF;
        String string = this.fString[n2][n3];
        if (string == null) {
            string = this.fStringProducer[n2][n3].toString(this.fOffset[n2][n3], this.fLength[n2][n3]);
            this.fStringProducer[n2][n3].releaseString(this.fOffset[n2][n3], this.fLength[n2][n3]);
            this.releaseStringInternal(n2, n3);
        }
        else if (this.fOffset[n2][n3] != -1) {
            this.releaseStringInternal(n2, n3);
        }
        return string;
    }
    
    public class CharArrayRange
    {
        public char[] chars;
        public int offset;
        public int length;
    }
    
    public interface StringProducer
    {
        String toString(final int p0, final int p1);
        
        void releaseString(final int p0, final int p1);
        
        boolean equalsString(final int p0, final int p1, final char[] p2, final int p3, final int p4);
    }
}
