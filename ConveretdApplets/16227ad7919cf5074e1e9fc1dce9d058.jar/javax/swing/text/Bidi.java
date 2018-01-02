// 
// Decompiled by Procyon v0.5.30
// 

package javax.swing.text;

final class Bidi
{
    private boolean ltr;
    private byte[] dirs;
    private byte[] levels;
    private int[] l2vMap;
    private int[] v2lMap;
    static final byte L = 0;
    static final byte R = 1;
    static final byte EN = 2;
    static final byte ES = 3;
    static final byte ET = 4;
    static final byte AN = 5;
    static final byte CS = 6;
    static final byte B = 7;
    static final byte S = 8;
    static final byte WS = 9;
    static final byte ON = 10;
    static final byte AR = 11;
    static final byte CM = 12;
    static final byte F = 13;
    static final char LRM = '\u200e';
    static final char RLM = '\u200f';
    static final char LRE = '\u202a';
    static final char RLE = '\u202b';
    static final char PDF = '\u202c';
    static final char LRO = '\u202d';
    static final char RLO = '\u202e';
    static final char NUMLEVELS = '\u0010';
    private static byte[] dirIndices;
    private static byte[] dirValues;
    
    static {
        Bidi.dirIndices = new byte[] { 14, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, -124, 14, 18, 15, 16, 17, 18, 19, 20, 21, 22, 23, 14, 24, 25, 26, 27, 14, 28, 29, 30, -104, 14, 14, 2, 31, 32, 33, 34, 35, 36, 37, 38, 14, 39, 14, 40, 41, -106, 14, 8, 42, 43, 44, 45, 46, 47, 48, 49, -76, 14, -2, 2, -91, 2, 1, 50, -104, 14, -41, 2, 1, 51, -60, 2, 12, 52, 14, 53, 54, 55, 55, 56, 57, 58, 59, 60, 61 };
        Bidi.dirValues = new byte[] { -119, 10, 5, 8, 7, 8, 7, 7, -114, 10, -125, 7, 4, 8, 9, 10, 10, -125, 4, -123, 10, 5, 4, 6, 4, 6, 3, -118, 2, 1, 6, -122, 10, -102, 0, -122, 10, -102, 0, -91, 10, 2, 9, 10, -124, 4, -124, 10, 1, 0, -123, 10, 6, 4, 4, 2, 2, 10, 0, -125, 10, 2, 2, 0, -123, 10, -105, 0, 1, 10, -97, 0, 1, 10, -2, 0, -2, 0, 2, 0, 0, -124, 10, -98, 0, -72, 10, -39, 0, -121, 10, -119, 0, 2, 10, 10, -121, 0, -98, 10, -123, 0, -101, 10, -58, 12, -102, 10, 2, 12, 12, -110, 10, 2, 0, 0, -124, 10, 1, 0, -117, 10, 2, 0, 10, -125, 0, 3, 10, 0, 10, -108, 0, 1, 10, -84, 0, 1, 10, -121, 0, -125, 10, 8, 0, 10, 0, 10, 0, 10, 0, 10, -110, 0, -115, 10, -116, 0, 1, 10, -62, 0, 1, 10, -116, 0, 1, 10, -91, 0, -124, 12, -119, 10, -75, 0, 8, 10, 10, 0, 0, 10, 10, 0, 0, -125, 10, -100, 0, 2, 10, 10, -120, 0, 4, 10, 10, 0, 0, -73, 10, -90, 0, 2, 10, 10, -121, 0, 1, 10, -89, 0, 2, 10, 0, -121, 10, -111, 12, 1, 10, -105, 12, 1, 10, -125, 12, 7, 1, 12, 1, 12, 12, 1, 12, -117, 10, -101, 1, -123, 10, -123, 1, -105, 10, 1, 6, -114, 10, 1, 11, -125, 10, 2, 11, 10, -102, 11, -123, 10, -117, 11, -120, 12, -115, 10, -118, 5, 7, 4, 5, 5, 11, 10, 10, 12, -57, 11, 2, 10, 10, -123, 11, 1, 10, -113, 11, 1, 10, -122, 11, -113, 12, 5, 11, 11, 12, 12, 10, -124, 12, 2, 10, 10, -118, 2, -2, 10, -119, 10, 4, 12, 12, 0, 10, -75, 0, 3, 10, 10, 12, -124, 0, -120, 12, -124, 0, 4, 12, 10, 10, 0, -124, 12, -125, 10, -118, 0, 2, 12, 12, -115, 0, -112, 10, 4, 12, 0, 0, 10, -120, 0, 6, 10, 10, 0, 0, 10, 10, -106, 0, 1, 10, -121, 0, 2, 10, 0, -125, 10, -124, 0, 4, 10, 10, 12, 10, -125, 0, -124, 12, 9, 10, 10, 0, 0, 10, 10, 0, 0, 12, -119, 10, 1, 0, -124, 10, 3, 0, 0, 10, -125, 0, 4, 12, 12, 10, 10, -116, 0, 2, 4, 4, -121, 0, -121, 10, 3, 12, 10, 10, -122, 0, -124, 10, 4, 0, 0, 10, 10, -106, 0, 1, 10, -121, 0, 13, 10, 0, 0, 10, 0, 0, 10, 0, 0, 10, 10, 12, 10, -125, 0, 2, 12, 12, -124, 10, 4, 12, 12, 10, 10, -125, 12, -117, 10, -124, 0, 2, 10, 0, -121, 10, -118, 0, 2, 12, 12, -125, 0, -116, 10, 4, 12, 12, 0, 10, -121, 0, 3, 10, 0, 10, -125, 0, 1, 10, -106, 0, 1, 10, -121, 0, 4, 10, 0, 0, 10, -123, 0, 3, 10, 10, 12, -124, 0, -123, 12, 11, 10, 12, 12, 0, 10, 0, 0, 12, 10, 10, 0, -113, 10, 1, 0, -123, 10, -118, 0, -111, 10, 4, 12, 0, 0, 10, -120, 0, 6, 10, 10, 0, 0, 10, 10, -106, 0, 1, 10, -121, 0, 5, 10, 0, 0, 10, 10, -124, 0, 7, 10, 10, 12, 0, 0, 12, 0, -125, 12, -125, 10, 7, 0, 0, 10, 10, 0, 0, 12, -120, 10, 2, 12, 0, -124, 10, 3, 0, 0, 10, -125, 0, -124, 10, -117, 0, -111, 10, 3, 12, 0, 10, -122, 0, -125, 10, -125, 0, 1, 10, -124, 0, -125, 10, 7, 0, 0, 10, 0, 10, 0, 0, -125, 10, 2, 0, 0, -125, 10, -125, 0, -125, 10, -120, 0, 1, 10, -125, 0, -124, 10, 5, 0, 0, 12, 0, 0, -125, 10, -125, 0, 1, 10, -125, 0, 1, 12, -119, 10, 1, 0, -113, 10, -116, 0, -114, 10, -125, 0, 1, 10, -120, 0, 1, 10, -125, 0, 1, 10, -105, 0, 1, 10, -118, 0, 1, 10, -123, 0, -124, 10, -125, 12, -124, 0, 1, 10, -125, 12, 1, 10, -124, 12, -121, 10, 2, 12, 12, -119, 10, 2, 0, 0, -124, 10, -118, 0, -110, 10, 3, 0, 0, 10, -120, 0, 1, 10, -125, 0, 1, 10, -105, 0, 1, 10, -118, 0, 1, 10, -123, 0, -124, 10, 2, 0, 12, -123, 0, 9, 10, 12, 0, 0, 10, 0, 0, 12, 12, -121, 10, 2, 0, 0, -121, 10, 4, 0, 10, 0, 0, -124, 10, -118, 0, -110, 10, 3, 0, 0, 10, -120, 0, 1, 10, -125, 0, 1, 10, -105, 0, 1, 10, -112, 0, -124, 10, -125, 0, -125, 12, 2, 10, 10, -125, 0, 1, 10, -125, 0, 1, 12, -119, 10, 1, 0, -120, 10, 2, 0, 0, -124, 10, -118, 0, -111, 10, -80, 0, 3, 12, 0, 0, -121, 12, -124, 10, 1, 4, -121, 0, -120, 12, -115, 0, -91, 10, 13, 0, 0, 10, 0, 10, 10, 0, 0, 10, 0, 10, 10, 0, -122, 10, -124, 0, 1, 10, -121, 0, 1, 10, -125, 0, 9, 10, 0, 10, 0, 10, 10, 0, 0, 10, -124, 0, 3, 12, 0, 0, -122, 12, 6, 10, 12, 12, 0, 10, 10, -123, 0, 3, 10, 0, 10, -122, 12, 2, 10, 10, -118, 0, 4, 10, 10, 0, 0, -94, 10, -104, 0, 2, 12, 12, -101, 0, 5, 12, 0, 12, 0, 12, -124, 10, 2, 12, 12, -120, 0, 1, 10, -95, 0, -121, 10, -114, 12, 1, 0, -123, 12, 3, 0, 12, 12, -124, 0, -124, 10, -122, 12, 3, 10, 12, 10, -107, 12, -125, 10, -121, 12, 2, 10, 12, -26, 10, -90, 0, -118, 10, -89, 0, -124, 10, 1, 0, -124, 10, -38, 0, -123, 10, -60, 0, -123, 10, -46, 0, -122, 10, -100, 0, -124, 10, -38, 0, -122, 10, -106, 0, 2, 10, 10, -122, 0, 2, 10, 10, -90, 0, 2, 10, 10, -122, 0, 2, 10, 10, -120, 0, 7, 10, 0, 10, 0, 10, 0, 10, -97, 0, 2, 10, 10, -75, 0, 1, 10, -121, 0, 2, 10, 0, -125, 10, -125, 0, 1, 10, -121, 0, -125, 10, -124, 0, 2, 10, 10, -122, 0, -124, 10, -115, 0, -123, 10, -125, 0, 1, 10, -121, 0, -125, 10, -121, 9, 1, 6, -124, 9, 4, 10, 10, 0, 1, -104, 10, 8, 7, 7, 13, 13, 10, 13, 13, 10, -123, 4, -69, 10, 1, 2, -125, 10, -122, 2, 2, 4, 4, -125, 10, 1, 0, -118, 2, 2, 4, 4, -108, 10, -115, 4, -93, 10, -110, 12, -96, 10, 1, 0, -124, 10, 3, 0, 10, 10, -118, 0, 4, 10, 0, 10, 10, -122, 0, -122, 10, 6, 0, 10, 0, 10, 0, 10, -120, 0, 1, 10, -122, 0, -89, 10, -93, 0, -2, 10, -111, 10, 2, 4, 4, -2, 10, -92, 10, -59, 0, -27, 10, -68, 2, -50, 0, 1, 2, -107, 10, 1, 9, -123, 10, 2, 0, 0, -103, 10, -119, 0, -122, 12, -111, 10, -44, 0, -124, 10, 8, 12, 12, 10, 10, 0, 0, 10, 10, -34, 0, -122, 10, -88, 0, -124, 10, -34, 0, 1, 10, -112, 0, -32, 10, -99, 0, -125, 10, -92, 0, -100, 10, -100, 0, -125, 10, -78, 0, -113, 10, -116, 0, -124, 10, -81, 0, 1, 10, -9, 0, -124, 10, -29, 0, 2, 10, 10, -97, 0, 1, 10, -90, 0, -38, 10, -92, 0, -36, 10, -82, 0, -46, 10, -121, 0, -116, 10, -123, 0, -122, 10, 1, 12, -118, 1, 1, 4, -115, 1, 1, 10, -123, 1, 9, 10, 1, 10, 1, 1, 10, 1, 1, 10, -20, 1, -95, 10, -2, 1, -19, 1, -110, 10, -64, 1, 2, 10, 10, -74, 1, -88, 10, -116, 1, -92, 10, -124, 12, -84, 10, 6, 6, 10, 6, 10, 10, 6, -119, 10, 5, 4, 10, 10, 4, 4, -123, 10, 2, 4, 4, -123, 10, -125, 1, 3, 10, 1, 10, -2, 1, -119, 1, -122, 10, -125, 4, -123, 10, 5, 4, 6, 4, 6, 3, -118, 2, 1, 6, -122, 10, -102, 0, -122, 10, -102, 0, -118, 10, -71, 0, 2, 10, 10, -97, 0, -125, 10, -122, 0, 2, 10, 10, -122, 0, 2, 10, 10, -122, 0, 2, 10, 10, -125, 0, -125, 10, 2, 4, 4, -125, 10, 2, 4, 4, -103, 10 };
        Bidi.dirIndices = RLEUtilities.readRLE(Bidi.dirIndices);
        Bidi.dirValues = RLEUtilities.readRLE(Bidi.dirValues);
    }
    
    Bidi(final Bidi bidi, final int n, final int n2) {
        final byte[] lineLevels = createLineLevels(bidi.dirs, bidi.levels, bidi.ltr, n, n2);
        this.ltr = bidi.ltr;
        this.dirs = null;
        this.levels = lineLevels;
    }
    
    protected Bidi(final byte[] levels, final boolean ltr) {
        this.ltr = ltr;
        this.dirs = null;
        this.levels = levels;
    }
    
    Bidi(final byte[] dirs, final byte[] levels, final boolean ltr) {
        applyBidiRules(dirs, levels, ltr);
        this.ltr = ltr;
        this.dirs = dirs;
        this.levels = levels;
    }
    
    Bidi(final char[] array) {
        this(array, defaultIsLTR(array, 0, array.length));
    }
    
    Bidi(final char[] array, final boolean b) {
        this(array, getEmbeddingArray(array, b), b);
    }
    
    Bidi(final char[] array, final byte[] levels, final boolean ltr) {
        final byte[] directionCodeArray = getDirectionCodeArray(array, levels);
        for (int i = 0; i < levels.length; ++i) {
            if ((levels[i] & 0x10) != 0x0) {
                final int n = i;
                levels[n] &= 0xF;
                directionCodeArray[i] = (byte)(levels[i] & 0x1);
            }
        }
        applyBidiRules(directionCodeArray, levels, ltr);
        this.ltr = ltr;
        this.dirs = directionCodeArray;
        this.levels = levels;
    }
    
    static void applyBidiRules(final byte[] array, final byte[] array2, final boolean b) {
        final byte[] array3 = array.clone();
        resolveWeakTypes(array3, array2, b);
        resolveNeutralTypes(array3, array2, b);
        resolveImplicitLevels(array3, array, array2, b);
    }
    
    private static int[] computeContiguousOrder(final int[] array, final int n, final int n2) {
        final int[] array2 = new int[n2 - n];
        for (int i = 0; i < array2.length; ++i) {
            array2[i] = i + n;
        }
        for (int j = 0; j < array2.length - 1; ++j) {
            int n3 = j;
            int n4 = array[array2[n3]];
            for (int k = j; k < array2.length; ++k) {
                if (array[array2[k]] < n4) {
                    n3 = k;
                    n4 = array[array2[n3]];
                }
            }
            final int n5 = array2[j];
            array2[j] = array2[n3];
            array2[n3] = n5;
        }
        if (n != 0) {
            for (int l = 0; l < array2.length; ++l) {
                final int[] array3 = array2;
                final int n6 = l;
                array3[n6] -= n;
            }
        }
        int n7;
        for (n7 = 0; n7 < array2.length && array2[n7] == n7; ++n7) {}
        if (n7 == array2.length) {
            return null;
        }
        return getInverseOrder(array2);
    }
    
    static Bidi createBidi(final char[] array) {
        return new Bidi(array);
    }
    
    Bidi createLineBidi(final int n, final int n2) {
        final byte[] array = new byte[n2 - n];
        System.arraycopy(this.levels, n, array, 0, array.length);
        if (this.dirs != null) {
            final byte b = (byte)(this.ltr ? 0 : 1);
            int length = array.length;
            while (--length >= 0 && array[length] % 2 != b) {
                if (this.dirs[n + length] != 9) {
                    break;
                }
                array[length] = b;
            }
        }
        return new Bidi(array, this.ltr);
    }
    
    static byte[] createLineLevels(final byte[] array, final byte[] array2, final boolean b, final int n, final int n2) {
        final byte[] array3 = new byte[n2 - n];
        System.arraycopy(array2, n, array3, 0, array3.length);
        final byte b2 = (byte)(b ? 0 : 1);
        for (int n3 = n2 - n - 1; n3 >= 0 && array3[n3] != b2 && array[n + n3] == 9; --n3) {
            array3[n3] = b2;
        }
        return array3;
    }
    
    static int[] createVisualToLogicalMap(final byte[] array) {
        final int length = array.length;
        final int[] array2 = new int[length];
        byte b = 17;
        byte b2 = 0;
        for (int i = 0; i < length; ++i) {
            array2[i] = i;
            final byte b3 = array[i];
            if (b3 > b2) {
                b2 = b3;
            }
            if ((b3 & 0x1) != 0x0 && b3 < b) {
                b = b3;
            }
        }
        while (b2 >= b) {
            int n = 0;
            while (true) {
                if (n >= length || array[n] >= b2) {
                    int j = n++;
                    if (j == array.length) {
                        break;
                    }
                    while (n < length && array[n] >= b2) {
                        ++n;
                    }
                    for (int n2 = n - 1; j < n2; ++j, --n2) {
                        final int n3 = array2[j];
                        array2[j] = array2[n2];
                        array2[n2] = n3;
                    }
                }
                else {
                    ++n;
                }
            }
            --b2;
        }
        return array2;
    }
    
    static boolean defaultIsLTR(final char[] array, int i, final int n) {
        while (i < n) {
            final char c = array[i++];
            switch (getDirectionCode(c)) {
                case 0: {
                    return true;
                }
                case 1:
                case 11: {
                    return false;
                }
                case 13: {
                    return c == '\u202d' || c == '\u202a';
                }
                default: {
                    continue;
                }
            }
        }
        return true;
    }
    
    static int[] getContiguousOrder(final int[] array) {
        if (array != null) {
            return computeContiguousOrder(array, 0, array.length);
        }
        return null;
    }
    
    static byte getDirectionCode(final char c) {
        return Bidi.dirValues[(Bidi.dirIndices[c >> 7] << 7) + (c & '\u007f')];
    }
    
    static byte[] getDirectionCodeArray(final char[] array, final byte[] array2) {
        byte b = 10;
        byte b2 = -1;
        final byte[] array3 = new byte[array.length];
        for (int i = 0; i < array.length; ++i) {
            if (array2[i] != b2) {
                b = 10;
                b2 = array2[i];
            }
            byte directionCode = getDirectionCode(array[i]);
            switch (directionCode) {
                case 0:
                case 1:
                case 11: {
                    b = directionCode;
                    break;
                }
                case 7: {
                    b = 10;
                    break;
                }
                case 12: {
                    directionCode = b;
                    break;
                }
                case 13: {
                    directionCode = 10;
                    break;
                }
            }
            array3[i] = directionCode;
        }
        return array3;
    }
    
    static byte[] getEmbeddingArray(final char[] array, final boolean b) {
        final byte[] array2 = new byte[array.length];
        byte b2 = (byte)(b ? 0 : 1);
        int n = 0;
        int n2 = 0;
        final byte[] array3 = new byte[16];
        final char[] array4 = new char[16];
        for (int i = 0; i < array.length; ++i) {
            final char c = array[i];
            switch (c) {
                case 8234:
                case 8237: {
                    if (n2 > 0) {
                        ++n2;
                        break;
                    }
                    final byte b3 = (byte)((b2 & 0xE) + 2);
                    if (b3 >= 16) {
                        ++n2;
                        break;
                    }
                    array4[n] = c;
                    array2[i] = (array3[n++] = b2);
                    if (c == '\u202d') {
                        b2 = (byte)(b3 + 16);
                        continue;
                    }
                    b2 = b3;
                    continue;
                }
                case 8235:
                case 8238: {
                    if (n2 > 0) {
                        ++n2;
                        break;
                    }
                    final byte b4 = (byte)((b2 & 0xF) + 1 | 0x1);
                    if (b4 >= 16) {
                        ++n2;
                        break;
                    }
                    array4[n] = c;
                    array2[i] = (array3[n++] = b2);
                    if (c == '\u202e') {
                        b2 = (byte)(b4 + 16);
                        continue;
                    }
                    b2 = b4;
                    continue;
                }
                case 8236: {
                    if (n2 > 0) {
                        --n2;
                        break;
                    }
                    if (n <= 0) {
                        break;
                    }
                    if (i < array.length - 1 && array[i + 1] == array4[n - 1]) {
                        array2[i + 1] = (array2[i] = b2);
                        ++i;
                        continue;
                    }
                    b2 = array3[--n];
                    break;
                }
            }
            array2[i] = b2;
        }
        return array2;
    }
    
    static int[] getInverseOrder(final int[] array) {
        if (array == null) {
            return null;
        }
        final int[] array2 = new int[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[array[i]] = i;
        }
        return array2;
    }
    
    int getLength() {
        return this.levels.length;
    }
    
    int getLevelAt(final int n) {
        return this.levels[n];
    }
    
    int getLevelLimit(int n) {
        final byte b = this.levels[n];
        while (++n < this.levels.length && this.levels[n] == b) {}
        return n;
    }
    
    byte[] getLevels() {
        return this.levels;
    }
    
    int[] getLogicalToVisualMap() {
        if (this.l2vMap == null) {
            this.l2vMap = getInverseOrder(this.getVisualToLogicalMap());
        }
        return this.l2vMap;
    }
    
    static int[] getNormalizedOrder(final int[] array, final byte[] array2, final int n, final int n2) {
        if (array == null) {
            return null;
        }
        if (n == 0 && n2 == array.length) {
            return array;
        }
        byte b;
        int n3;
        int n4;
        if (array2 == null) {
            b = 0;
            n3 = 1;
            n4 = 1;
        }
        else if (array2[n] == array2[n2 - 1]) {
            b = array2[n];
            n4 = (((b & 0x1) == 0x0) ? 1 : 0);
            int n5;
            for (n5 = n; n5 < n2 && array2[n5] >= b; ++n5) {
                if (n4 != 0) {
                    n4 = ((array2[n5] == b) ? 1 : 0);
                }
            }
            n3 = ((n5 == n2) ? 1 : 0);
        }
        else {
            n3 = 0;
            b = 0;
            n4 = 0;
        }
        if (n3 == 0) {
            return computeContiguousOrder(array, n, n2);
        }
        if (n4 != 0) {
            return null;
        }
        final int[] array3 = new int[n2 - n];
        int n6;
        if ((b & 0x1) != 0x0) {
            n6 = array[n2 - 1];
        }
        else {
            n6 = array[n];
        }
        if (n6 == 0) {
            System.arraycopy(array, n, array3, 0, n2 - n);
        }
        else {
            for (int i = 0; i < array3.length; ++i) {
                array3[i] = array[i + n] - n6;
            }
        }
        return array3;
    }
    
    int[] getVisualToLogicalMap() {
        if (this.v2lMap == null) {
            this.v2lMap = createVisualToLogicalMap(this.levels);
        }
        return this.v2lMap;
    }
    
    boolean isDirectionLTR() {
        return this.ltr;
    }
    
    static void reorderVisually(final byte[] array, final Object[] array2) {
        final int length = array.length;
        byte b = 17;
        byte b2 = 0;
        for (final byte b3 : array) {
            if (b3 > b2) {
                b2 = b3;
            }
            if ((b3 & 0x1) != 0x0 && b3 < b) {
                b = b3;
            }
        }
        while (b2 >= b) {
            int n = 0;
            while (true) {
                if (n >= length || array[n] >= b2) {
                    int j = n++;
                    if (j == array.length) {
                        break;
                    }
                    while (n < length && array[n] >= b2) {
                        ++n;
                    }
                    for (int n2 = n - 1; j < n2; ++j, --n2) {
                        final Object o = array2[j];
                        array2[j] = array2[n2];
                        array2[n2] = o;
                    }
                }
                else {
                    ++n;
                }
            }
            --b2;
        }
    }
    
    static boolean requiresBidi(final char c) {
        if (c < '\u0591') {
            return false;
        }
        if (c > '\u202e') {
            return false;
        }
        final byte directionCode = getDirectionCode(c);
        return directionCode == 1 || directionCode == 11 || directionCode == 13;
    }
    
    private static void resolveImplicitLevels(final byte[] array, final byte[] array2, final byte[] array3, final boolean b) {
        final byte b2 = (byte)(b ? 0 : 1);
        final int length = array.length;
        byte b3 = -1;
        for (int i = 0; i < length; ++i) {
            byte b5;
            final byte b4 = b5 = array3[i];
            switch (array[i]) {
                case 0: {
                    b5 = (byte)(b4 + 1 & 0x1E);
                    break;
                }
                case 1:
                case 11: {
                    b5 = (byte)(b4 | 0x1);
                    break;
                }
                case 5: {
                    b5 = (byte)((b4 & 0xE) + 2);
                    break;
                }
                case 2: {
                    if ((b4 & 0x1) != 0x0) {
                        ++b5;
                        break;
                    }
                    if (i == 0 || b3 != b4) {
                        break;
                    }
                    final byte b6 = array[i - 1];
                    if (b6 == 2) {
                        b5 = array3[i - 1];
                        break;
                    }
                    if (b6 != 0) {
                        b5 += 2;
                        break;
                    }
                    break;
                }
                case 7:
                case 8: {
                    b5 = b2;
                    for (int n = i - 1; n >= 0 && array2[n] == 9; --n) {
                        array3[n] = b2;
                    }
                    break;
                }
            }
            if (b5 != b4) {
                array3[i] = b5;
            }
            b3 = b4;
        }
        for (int n2 = length - 1; n2 >= 0 && array2[n2] == 9; --n2) {
            array3[n2] = b2;
        }
    }
    
    private static void resolveNeutralTypes(final byte[] array, final byte[] array2, final boolean b) {
        int i = 0;
        final int length = array.length;
        while (i < length) {
            final byte b2 = array2[i];
            final boolean b3 = (b2 & 0x1) != 0x0;
            int n;
            for (n = i + 1; n < length && array2[n] == b2; ++n) {}
            byte b4 = (byte)(b3 ? 1 : 0);
            byte b5 = (byte)(b3 ? 1 : 0);
            int j = i - 1;
            while (i < n) {
                switch (array[i]) {
                    case 0: {
                        b4 = 0;
                        break;
                    }
                    case 1:
                    case 11: {
                        b4 = 1;
                        break;
                    }
                    case 7:
                    case 8: {
                        b4 = (byte)(b3 ? 1 : 0);
                        break;
                    }
                    case 9:
                    case 10:
                    case 12: {
                        Label_0308: {
                            if (i > j) {
                                b5 = (byte)(b3 ? 1 : 0);
                                j = i + 1;
                                while (j < n) {
                                    switch (array[j]) {
                                        case 0: {
                                            b5 = (byte)(((b4 == 0) ? false : b3) ? 1 : 0);
                                            break Label_0308;
                                        }
                                        case 1:
                                        case 5:
                                        case 11: {
                                            b5 = (byte)(((b4 == 0) ? b3 : true) ? 1 : 0);
                                            break Label_0308;
                                        }
                                        case 2: {
                                            b5 = b4;
                                            break Label_0308;
                                        }
                                        case 8: {
                                            b5 = (byte)(b3 ? 1 : 0);
                                            break Label_0308;
                                        }
                                        default: {
                                            ++j;
                                            continue;
                                        }
                                    }
                                }
                            }
                        }
                        array[i] = b5;
                        break;
                    }
                }
                ++i;
            }
        }
    }
    
    private static void resolveWeakTypes(final byte[] array, final byte[] array2, final boolean b) {
        int i = 0;
        int j = 0;
        while (j < array.length) {
            for (byte b2 = array2[j++]; j < array.length && array2[j] == b2; ++j) {}
            int n = -1;
            byte b3 = array[i];
            int n2 = (b3 == 11) ? 1 : 0;
            while (i < j) {
                int k = i + 1;
                byte b4 = (byte)((k == j) ? -1 : array[k]);
                if (b4 == 2 && n2 != 0) {
                    b4 = 5;
                }
                byte b5 = b3;
                switch (b3) {
                    case 0:
                    case 1: {
                        n2 = 0;
                        break;
                    }
                    case 11: {
                        n2 = 1;
                        break;
                    }
                    case 3: {
                        if (n == 2 && b4 == 2) {
                            b5 = 2;
                            break;
                        }
                        b5 = 10;
                        break;
                    }
                    case 6: {
                        if (n == 2 && b4 == 2) {
                            b5 = 2;
                            break;
                        }
                        if (n == 5 && b4 == 5) {
                            b5 = 5;
                            break;
                        }
                        b5 = 10;
                        break;
                    }
                    case 4: {
                        if (n == 2 || b4 == 2) {
                            b5 = 2;
                            break;
                        }
                        if (b4 == 4 && n2 == 0) {
                            for (int l = k + 1; l < j; ++l) {
                                final byte b6 = array[l];
                                if (b6 != 4) {
                                    int n3;
                                    for (n3 = ((b6 == 2) ? 2 : 10); k < l; array[k++] = (byte)n3) {}
                                    b5 = (byte)n3;
                                    b4 = b6;
                                    break;
                                }
                            }
                            break;
                        }
                        b5 = 10;
                        break;
                    }
                }
                array[i] = b5;
                i = k;
                n = b5;
                b3 = b4;
            }
        }
    }
}
