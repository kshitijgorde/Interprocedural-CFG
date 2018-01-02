// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.image;

final class JPEGHuffmanTable extends JPEGVariableSizeSegment
{
    JPEGHuffmanTable[] allTables;
    int tableClass;
    int tableIdentifier;
    int[] dhMaxCodes;
    int[] dhMinCodes;
    int[] dhValPtrs;
    int[] dhValues;
    int[] ehCodes;
    byte[] ehCodeLengths;
    static byte[] DCLuminanceTable;
    static byte[] DCChrominanceTable;
    static byte[] ACLuminanceTable;
    static byte[] ACChrominanceTable;
    
    static {
        JPEGHuffmanTable.DCLuminanceTable = new byte[] { -1, -60, 0, 31, 0, 0, 1, 5, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
        JPEGHuffmanTable.DCChrominanceTable = new byte[] { -1, -60, 0, 31, 1, 0, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
        JPEGHuffmanTable.ACLuminanceTable = new byte[] { -1, -60, 0, -75, 16, 0, 2, 1, 3, 3, 2, 4, 3, 5, 5, 4, 4, 0, 0, 1, 125, 1, 2, 3, 0, 4, 17, 5, 18, 33, 49, 65, 6, 19, 81, 97, 7, 34, 113, 20, 50, -127, -111, -95, 8, 35, 66, -79, -63, 21, 82, -47, -16, 36, 51, 98, 114, -126, 9, 10, 22, 23, 24, 25, 26, 37, 38, 39, 40, 41, 42, 52, 53, 54, 55, 56, 57, 58, 67, 68, 69, 70, 71, 72, 73, 74, 83, 84, 85, 86, 87, 88, 89, 90, 99, 100, 101, 102, 103, 104, 105, 106, 115, 116, 117, 118, 119, 120, 121, 122, -125, -124, -123, -122, -121, -120, -119, -118, -110, -109, -108, -107, -106, -105, -104, -103, -102, -94, -93, -92, -91, -90, -89, -88, -87, -86, -78, -77, -76, -75, -74, -73, -72, -71, -70, -62, -61, -60, -59, -58, -57, -56, -55, -54, -46, -45, -44, -43, -42, -41, -40, -39, -38, -31, -30, -29, -28, -27, -26, -25, -24, -23, -22, -15, -14, -13, -12, -11, -10, -9, -8, -7, -6 };
        JPEGHuffmanTable.ACChrominanceTable = new byte[] { -1, -60, 0, -75, 17, 0, 2, 1, 2, 4, 4, 3, 4, 7, 5, 4, 4, 0, 1, 2, 119, 0, 1, 2, 3, 17, 4, 5, 33, 49, 6, 18, 65, 81, 7, 97, 113, 19, 34, 50, -127, 8, 20, 66, -111, -95, -79, -63, 9, 35, 51, 82, -16, 21, 98, 114, -47, 10, 22, 36, 52, -31, 37, -15, 23, 24, 25, 26, 38, 39, 40, 41, 42, 53, 54, 55, 56, 57, 58, 67, 68, 69, 70, 71, 72, 73, 74, 83, 84, 85, 86, 87, 88, 89, 90, 99, 100, 101, 102, 103, 104, 105, 106, 115, 116, 117, 118, 119, 120, 121, 122, -126, -125, -124, -123, -122, -121, -120, -119, -118, -110, -109, -108, -107, -106, -105, -104, -103, -102, -94, -93, -92, -91, -90, -89, -88, -87, -86, -78, -77, -76, -75, -74, -73, -72, -71, -70, -62, -61, -60, -59, -58, -57, -56, -55, -54, -46, -45, -44, -43, -42, -41, -40, -39, -38, -30, -29, -28, -27, -26, -25, -24, -23, -22, -14, -13, -12, -11, -10, -9, -8, -7, -6 };
    }
    
    public JPEGHuffmanTable(final byte[] array) {
        super(array);
    }
    
    public JPEGHuffmanTable(final LEDataInputStream leDataInputStream) {
        super(leDataInputStream);
        this.initialize();
    }
    
    public JPEGHuffmanTable[] getAllTables() {
        return this.allTables;
    }
    
    public static JPEGHuffmanTable getDefaultACChrominanceTable() {
        final JPEGHuffmanTable jpegHuffmanTable = new JPEGHuffmanTable(JPEGHuffmanTable.ACChrominanceTable);
        jpegHuffmanTable.initialize();
        return jpegHuffmanTable;
    }
    
    public static JPEGHuffmanTable getDefaultACLuminanceTable() {
        final JPEGHuffmanTable jpegHuffmanTable = new JPEGHuffmanTable(JPEGHuffmanTable.ACLuminanceTable);
        jpegHuffmanTable.initialize();
        return jpegHuffmanTable;
    }
    
    public static JPEGHuffmanTable getDefaultDCChrominanceTable() {
        final JPEGHuffmanTable jpegHuffmanTable = new JPEGHuffmanTable(JPEGHuffmanTable.DCChrominanceTable);
        jpegHuffmanTable.initialize();
        return jpegHuffmanTable;
    }
    
    public static JPEGHuffmanTable getDefaultDCLuminanceTable() {
        final JPEGHuffmanTable jpegHuffmanTable = new JPEGHuffmanTable(JPEGHuffmanTable.DCLuminanceTable);
        jpegHuffmanTable.initialize();
        return jpegHuffmanTable;
    }
    
    public int[] getDhMaxCodes() {
        return this.dhMaxCodes;
    }
    
    public int[] getDhMinCodes() {
        return this.dhMinCodes;
    }
    
    public int[] getDhValPtrs() {
        return this.dhValPtrs;
    }
    
    public int[] getDhValues() {
        return this.dhValues;
    }
    
    public int getTableClass() {
        return this.tableClass;
    }
    
    public int getTableIdentifier() {
        return this.tableIdentifier;
    }
    
    void initialize() {
        int i = this.getSegmentLength() - 2;
        int n = 4;
        final int[] array = new int[16];
        final JPEGHuffmanTable[] array2 = new JPEGHuffmanTable[8];
        int n2 = 0;
        while (i > 0) {
            final int tableClass = (this.reference[n] & 0xFF) >> 4;
            final byte tableIdentifier = (byte)(this.reference[n] & 0xF);
            ++n;
            int n3 = 0;
            for (int j = 0; j < array.length; ++j) {
                final int n4 = this.reference[n + j] & 0xFF;
                array[j] = n4;
                n3 += n4;
            }
            n += 16;
            i -= 17;
            final int[] dhValues = new int[n3];
            for (int k = 0; k < n3; ++k) {
                dhValues[k] = (this.reference[n + k] & 0xFF);
            }
            n += n3;
            i -= n3;
            int[] array3 = new int[50];
            int n5 = 0;
            for (int l = 0; l < 16; ++l) {
                for (int n6 = 0; n6 < array[l]; ++n6) {
                    if (n5 >= array3.length) {
                        final int[] array4 = new int[array3.length + 50];
                        System.arraycopy(array3, 0, array4, 0, array3.length);
                        array3 = array4;
                    }
                    array3[n5] = l + 1;
                    ++n5;
                }
            }
            if (n5 < array3.length) {
                final int[] array5 = new int[n5];
                System.arraycopy(array3, 0, array5, 0, n5);
                array3 = array5;
            }
            int[] array6 = new int[50];
            int n7 = 0;
            int n8 = 0;
            int n9 = array3[0];
            int n10 = 0;
            while (n10 < n5) {
                while (n10 < n5 && array3[n10] == n9) {
                    if (n7 >= array6.length) {
                        final int[] array7 = new int[array6.length + 50];
                        System.arraycopy(array6, 0, array7, 0, array6.length);
                        array6 = array7;
                    }
                    array6[n7] = n8;
                    ++n7;
                    ++n8;
                    ++n10;
                }
                n8 *= 2;
                ++n9;
            }
            if (n7 < array6.length) {
                final int[] array8 = new int[n7];
                System.arraycopy(array6, 0, array8, 0, n7);
                array6 = array8;
            }
            int n11 = 0;
            final int[] dhMaxCodes = new int[16];
            final int[] dhMinCodes = new int[16];
            final int[] dhValPtrs = new int[16];
            for (int n12 = 0; n12 < 16; ++n12) {
                final int n13 = array[n12];
                if (n13 == 0) {
                    dhMaxCodes[n12] = -1;
                }
                else {
                    dhValPtrs[n12] = n11;
                    dhMinCodes[n12] = array6[n11];
                    n11 += n13;
                    dhMaxCodes[n12] = array6[n11 - 1];
                }
            }
            final int[] ehCodes = new int[256];
            final byte[] ehCodeLengths = new byte[256];
            for (int n14 = 0; n14 < n7; ++n14) {
                ehCodes[dhValues[n14]] = array6[n14];
                ehCodeLengths[dhValues[n14]] = (byte)array3[n14];
            }
            final JPEGHuffmanTable jpegHuffmanTable = new JPEGHuffmanTable(this.reference);
            jpegHuffmanTable.tableClass = tableClass;
            jpegHuffmanTable.tableIdentifier = tableIdentifier;
            jpegHuffmanTable.dhValues = dhValues;
            jpegHuffmanTable.dhMinCodes = dhMinCodes;
            jpegHuffmanTable.dhMaxCodes = dhMaxCodes;
            jpegHuffmanTable.dhValPtrs = dhValPtrs;
            jpegHuffmanTable.ehCodes = ehCodes;
            jpegHuffmanTable.ehCodeLengths = ehCodeLengths;
            array2[n2] = jpegHuffmanTable;
            ++n2;
        }
        System.arraycopy(array2, 0, this.allTables = new JPEGHuffmanTable[n2], 0, n2);
    }
    
    public int signature() {
        return 65476;
    }
}
