// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xerces.utils;

public final class SymbolCache
{
    public static final int CHAR_OFFSET = 0;
    public static final int INDEX_OFFSET = 1;
    public static final int NEXT_OFFSET = 2;
    public static final int CACHE_RECORD_SIZE = 3;
    public static final int INITIAL_CACHE_RECORD_COUNT = 4;
    public char[] fSymbolChars;
    public int fSymbolCharsOffset;
    public int[][] fCacheLines;
    public int fCacheLineCount;
    
    public SymbolCache() {
        this.fSymbolChars = new char[8192];
        (this.fCacheLines = new int[8][])[this.fCacheLineCount++] = new int[13];
    }
    
    public void reset() {
        this.fSymbolCharsOffset = 0;
        this.fCacheLineCount = 0;
        this.fCacheLines[this.fCacheLineCount++] = new int[13];
    }
    
    public char[] getSymbolChars() {
        return this.fSymbolChars;
    }
    
    public String createSymbol(final int n, final int n2, final int n3, final int[] array, final int n4) {
        final String s = new String(this.fSymbolChars, n2, this.fSymbolCharsOffset - n2);
        try {
            array[n4 + 1] = n;
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            throw new RuntimeException("untested 1");
        }
        return s;
    }
    
    public int addSymbolToCache(final String s, final int n, final int n2) {
        final int fSymbolCharsOffset = this.fSymbolCharsOffset;
        if (n == 0) {
            return fSymbolCharsOffset;
        }
        int n3 = 0;
        char c = s.charAt(n3++);
        try {
            this.fSymbolChars[this.fSymbolCharsOffset] = c;
        }
        catch (ArrayIndexOutOfBoundsException ex) {
            final char[] fSymbolChars = new char[this.fSymbolChars.length * 2];
            System.arraycopy(this.fSymbolChars, 0, fSymbolChars, 0, this.fSymbolChars.length);
            (this.fSymbolChars = fSymbolChars)[this.fSymbolCharsOffset] = c;
        }
        ++this.fSymbolCharsOffset;
        int n4 = 0;
        int[] array = this.fCacheLines[n4];
        int n5 = array[0];
        int i = 0;
        int n6 = 1;
        while (i != n5) {
            if (array[n6] != c) {
                ++i;
                n6 += 3;
            }
            else if (n3 == n) {
                if (array[n6 + 1] != -1) {
                    throw new RuntimeException("addSymbolToCache");
                }
                array[n6 + 1] = n2;
                return fSymbolCharsOffset;
            }
            else {
                c = s.charAt(n3++);
                try {
                    this.fSymbolChars[this.fSymbolCharsOffset] = c;
                }
                catch (ArrayIndexOutOfBoundsException ex2) {
                    final char[] fSymbolChars2 = new char[this.fSymbolChars.length * 2];
                    System.arraycopy(this.fSymbolChars, 0, fSymbolChars2, 0, this.fSymbolChars.length);
                    (this.fSymbolChars = fSymbolChars2)[this.fSymbolCharsOffset] = c;
                }
                ++this.fSymbolCharsOffset;
                n4 = array[n6 + 2];
                try {
                    array = this.fCacheLines[n4];
                }
                catch (ArrayIndexOutOfBoundsException ex3) {
                    if (n4 != -1) {
                        final int[] array2 = this.fCacheLines[n4];
                        throw new RuntimeException("untested 2");
                    }
                    n4 = this.fCacheLineCount++;
                    array[n6 + 2] = n4;
                    array = new int[13];
                    try {
                        this.fCacheLines[n4] = array;
                    }
                    catch (ArrayIndexOutOfBoundsException ex4) {
                        final int[][] fCacheLines = new int[n4 * 2][];
                        System.arraycopy(this.fCacheLines, 0, fCacheLines, 0, n4);
                        (this.fCacheLines = fCacheLines)[n4] = array;
                    }
                }
                n5 = array[0];
                i = 0;
                n6 = 1;
            }
        }
        while (true) {
            final int[] array3 = array;
            final int n7 = 0;
            ++array3[n7];
            try {
                array[n6] = c;
            }
            catch (ArrayIndexOutOfBoundsException ex5) {
                final int[] array4 = new int[1 + (n6 - 1) * 2];
                System.arraycopy(array, 0, array4, 0, n6);
                array = (this.fCacheLines[n4] = array4);
                array[n6] = c;
            }
            if (n3 == n) {
                break;
            }
            n4 = this.fCacheLineCount++;
            array[n6 + 1] = -1;
            array[n6 + 2] = n4;
            array = new int[13];
            try {
                this.fCacheLines[n4] = array;
            }
            catch (ArrayIndexOutOfBoundsException ex6) {
                final int[][] fCacheLines2 = new int[n4 * 2][];
                System.arraycopy(this.fCacheLines, 0, fCacheLines2, 0, n4);
                (this.fCacheLines = fCacheLines2)[n4] = array;
            }
            n6 = 1;
            c = s.charAt(n3++);
            try {
                this.fSymbolChars[this.fSymbolCharsOffset] = c;
            }
            catch (ArrayIndexOutOfBoundsException ex7) {
                final char[] fSymbolChars3 = new char[this.fSymbolChars.length * 2];
                System.arraycopy(this.fSymbolChars, 0, fSymbolChars3, 0, this.fSymbolChars.length);
                (this.fSymbolChars = fSymbolChars3)[this.fSymbolCharsOffset] = c;
            }
            ++this.fSymbolCharsOffset;
        }
        array[n6 + 1] = n2;
        array[n6 + 2] = -1;
        return fSymbolCharsOffset;
    }
    
    public void updateCacheLine(int n, final int n2, int n3) {
        int[] array = this.fCacheLines[0];
        char c = this.fSymbolChars[n++];
        int n4 = array[0];
        int n5 = 1 + (n4 - 1) * 3;
        int n6 = 0;
        while (true) {
            if (c != array[n5]) {
                n5 -= 3;
                ++n6;
            }
            else {
                if (n6 > 4) {
                    final int n7 = array[n5 + 1];
                    final int n8 = array[n5 + 2];
                    System.arraycopy(array, n5 + 3, array, n5, n6 * 3);
                    n5 = 1 + (n4 - 1) * 3;
                    array[n5] = c;
                    array[n5 + 1] = n7;
                    array[n5 + 2] = n8;
                }
                if (--n3 == 0) {
                    break;
                }
                array = this.fCacheLines[array[n5 + 2]];
                c = this.fSymbolChars[n++];
                n4 = array[0];
                n5 = 1 + (n4 - 1) * 3;
                n6 = 0;
            }
        }
    }
}
