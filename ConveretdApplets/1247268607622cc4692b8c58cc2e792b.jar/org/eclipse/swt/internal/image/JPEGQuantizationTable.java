// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal.image;

final class JPEGQuantizationTable extends JPEGVariableSizeSegment
{
    public static byte[] DefaultLuminanceQTable;
    public static byte[] DefaultChrominanceQTable;
    
    static {
        JPEGQuantizationTable.DefaultLuminanceQTable = new byte[] { -1, -37, 0, 67, 0, 16, 11, 10, 16, 24, 40, 51, 61, 12, 12, 14, 19, 26, 58, 60, 55, 14, 13, 16, 24, 40, 57, 69, 56, 14, 17, 22, 29, 51, 87, 80, 62, 18, 22, 37, 56, 68, 109, 103, 77, 24, 35, 55, 64, 81, 104, 113, 92, 49, 64, 78, 87, 103, 121, 120, 101, 72, 92, 95, 98, 112, 100, 103, 99 };
        JPEGQuantizationTable.DefaultChrominanceQTable = new byte[] { -1, -37, 0, 67, 1, 17, 18, 24, 47, 99, 99, 99, 99, 18, 21, 26, 66, 99, 99, 99, 99, 24, 26, 56, 99, 99, 99, 99, 99, 47, 66, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99 };
    }
    
    public JPEGQuantizationTable(final byte[] array) {
        super(array);
    }
    
    public JPEGQuantizationTable(final LEDataInputStream leDataInputStream) {
        super(leDataInputStream);
    }
    
    public static JPEGQuantizationTable defaultChrominanceTable() {
        final byte[] array = new byte[JPEGQuantizationTable.DefaultChrominanceQTable.length];
        System.arraycopy(JPEGQuantizationTable.DefaultChrominanceQTable, 0, array, 0, array.length);
        return new JPEGQuantizationTable(array);
    }
    
    public static JPEGQuantizationTable defaultLuminanceTable() {
        final byte[] array = new byte[JPEGQuantizationTable.DefaultLuminanceQTable.length];
        System.arraycopy(JPEGQuantizationTable.DefaultLuminanceQTable, 0, array, 0, array.length);
        return new JPEGQuantizationTable(array);
    }
    
    public int[] getQuantizationTablesKeys() {
        int[] array = new int[4];
        int n = 0;
        int i = this.getSegmentLength() - 2;
        int n2 = 4;
        while (i > 64) {
            final byte b = (byte)(this.reference[n2] & 0xF);
            if ((this.reference[n2] & 0xFF) >> 4 == 0) {
                n2 += 65;
                i -= 65;
            }
            else {
                n2 += 129;
                i -= 129;
            }
            if (n >= array.length) {
                final int[] array2 = new int[array.length + 4];
                System.arraycopy(array, 0, array2, 0, array.length);
                array = array2;
            }
            array[n] = b;
            ++n;
        }
        final int[] array3 = new int[n];
        System.arraycopy(array, 0, array3, 0, n);
        return array3;
    }
    
    public int[][] getQuantizationTablesValues() {
        int[][] array = new int[4][];
        int n = 0;
        int i = this.getSegmentLength() - 2;
        int n2 = 4;
        while (i > 64) {
            final int[] array2 = new int[64];
            if ((this.reference[n2] & 0xFF) >> 4 == 0) {
                for (int j = 0; j < array2.length; ++j) {
                    array2[j] = (this.reference[n2 + j + 1] & 0xFF);
                }
                n2 += 65;
                i -= 65;
            }
            else {
                for (int k = 0; k < array2.length; ++k) {
                    final int n3 = (k - 1) * 2;
                    array2[k] = (this.reference[n2 + n3 + 1] & 0xFF) * 256 + (this.reference[n2 + n3 + 2] & 0xFF);
                }
                n2 += 129;
                i -= 129;
            }
            if (n >= array.length) {
                final int[][] array3 = new int[array.length + 4][];
                System.arraycopy(array, 0, array3, 0, array.length);
                array = array3;
            }
            array[n] = array2;
            ++n;
        }
        final int[][] array4 = new int[n][];
        System.arraycopy(array, 0, array4, 0, n);
        return array4;
    }
    
    public void scaleBy(final int n) {
        int n2 = n;
        if (n2 <= 0) {
            n2 = 1;
        }
        if (n2 > 100) {
            n2 = 100;
        }
        int n3;
        if (n2 < 50) {
            n3 = 5000 / n2;
        }
        else {
            n3 = 200 - n2 * 2;
        }
        int i = this.getSegmentLength() - 2;
        int n4 = 4;
        while (i > 64) {
            if ((this.reference[n4] & 0xFF) >> 4 == 0) {
                for (int j = n4 + 1; j <= n4 + 64; ++j) {
                    int n5 = ((this.reference[j] & 0xFF) * n3 + 50) / 100;
                    if (n5 <= 0) {
                        n5 = 1;
                    }
                    if (n5 > 255) {
                        n5 = 255;
                    }
                    this.reference[j] = (byte)n5;
                }
                n4 += 65;
                i -= 65;
            }
            else {
                for (int k = n4 + 1; k <= n4 + 128; k += 2) {
                    int n6 = (((this.reference[k] & 0xFF) * 256 + (this.reference[k + 1] & 0xFF)) * n3 + 50) / 100;
                    if (n6 <= 0) {
                        n6 = 1;
                    }
                    if (n6 > 32767) {
                        n6 = 32767;
                    }
                    this.reference[k] = (byte)(n6 >> 8);
                    this.reference[k + 1] = (byte)(n6 & 0xFF);
                }
                n4 += 129;
                i -= 129;
            }
        }
    }
    
    public int signature() {
        return 65499;
    }
}
