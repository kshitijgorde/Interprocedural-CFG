// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jkate;

import com.jcraft.jogg.Buffer;

public class RLE
{
    private static final int KATE_RLE_RUN_LENGTH_BITS_BASIC = 4;
    private static final int KATE_RLE_RUN_LENGTH_BITS_BASIC_IN_DELTA = 3;
    private static final int KATE_RLE_RUN_LENGTH_BITS_BASIC_STARTEND = 3;
    private static final int KATE_RLE_RUN_LENGTH_BITS_BASIC_STOP = 6;
    private static final int KATE_RLE_RUN_LENGTH_BITS_DELTA = 6;
    private static final int KATE_RLE_RUN_LENGTH_BITS_BASIC_STARTEND_START = 9;
    private static final int KATE_RLE_RUN_LENGTH_BITS_BASIC_STARTEND_END = 8;
    private static final int KATE_RLE_RUN_LENGTH_BITS_BASIC_STOP_START = 8;
    private static final int KATE_RLE_RUN_LENGTH_BITS_BASIC_IN_DELTA_STOP = 3;
    private static final int KATE_RLE_RUN_LENGTH_BITS_DELTA_STOP = 5;
    private static final int KATE_RLE_RUN_LENGTH_BITS_BASIC_ZERO = 8;
    private static final int KATE_RLE_RUN_LENGTH_BITS_BASIC_NON_ZERO = 3;
    private static final int KATE_RLE_TYPE_EMPTY = 0;
    private static final int KATE_RLE_TYPE_BASIC = 1;
    private static final int KATE_RLE_TYPE_DELTA = 2;
    private static final int KATE_RLE_TYPE_BASIC_STOP = 3;
    private static final int KATE_RLE_TYPE_BASIC_STARTEND = 4;
    private static final int KATE_RLE_TYPE_DELTA_STOP = 5;
    private static final int KATE_RLE_TYPE_BASIC_ZERO = 6;
    private static final int KATE_RLE_TYPE_BITS = 3;
    
    private static int decodeLineEmpty(final Buffer buffer, final int n, final byte[] array, final int n2, final int n3, final byte b) {
        for (int i = 0; i < n; ++i) {
            array[n2 + i] = b;
        }
        return 0;
    }
    
    private static int decodeLineBasic(final Buffer buffer, final int n, final byte[] array, final int n2, final int n3, final byte b) {
        int n4 = 0;
        int n5;
        for (int i = n; i > 0; i -= n5) {
            n5 = 1 + buffer.read(4);
            if (n5 == 0 || n5 > i) {
                return -1;
            }
            final byte b2 = (byte)buffer.read(n3);
            for (int j = 0; j < n5; ++j) {
                array[n2 + n4++] = b2;
            }
        }
        return 0;
    }
    
    private static int decodeLineDelta(final Buffer buffer, final int n, final byte[] array, final int n2, final int n3, final byte b) {
        int n4 = 0;
        int i = n;
        while (i > 0) {
            if (buffer.read1() != 0) {
                final int n5 = 1 + buffer.read(6);
                if (n5 == 0 || n5 > i) {
                    return -1;
                }
                if (n2 > 0) {
                    for (int j = 0; j < n5; ++j) {
                        array[n2 + n4] = array[n2 + n4 - n];
                        ++n4;
                    }
                }
                else {
                    for (int k = 0; k < n5; ++k) {
                        array[n2 + n4++] = b;
                    }
                }
                i -= n5;
            }
            else {
                final int n6 = 1 + buffer.read(3);
                if (n6 == 0 || n6 > i) {
                    return -1;
                }
                final byte b2 = (byte)buffer.read(n3);
                for (int l = 0; l < n6; ++l) {
                    array[n2 + n4++] = b2;
                }
                i -= n6;
            }
        }
        return 0;
    }
    
    private static int decodeLineBasicStartEnd(final Buffer buffer, final int n, final byte[] array, final int n2, final int n3, final byte b) {
        int i = n;
        int n4 = 0;
        final int read = buffer.read(9);
        if (read > 0) {
            if (read > i) {
                return -1;
            }
            for (int j = 0; j < read; ++j) {
                array[n2 + n4++] = b;
            }
            i -= read;
        }
        final int read2 = buffer.read(8);
        if (read2 > 0) {
            if (read2 > i) {
                return -1;
            }
            for (int k = 0; k < read2; ++k) {
                array[n2 + n - 1 - k] = b;
            }
            i -= read2;
        }
        while (i > 0) {
            final int n5 = 1 + buffer.read(3);
            if (n5 == 0 || n5 > i) {
                return -1;
            }
            final byte b2 = (byte)buffer.read(n3);
            for (int l = 0; l < n5; ++l) {
                array[n2 + n4++] = b2;
            }
            i -= n5;
        }
        return 0;
    }
    
    private static int decodeLineBasicStop(final Buffer buffer, final int n, final byte[] array, final int n2, final int n3, final byte b) {
        int i = n;
        int n4 = 0;
        final int read = buffer.read(8);
        if (read > 0) {
            if (read > i) {
                return -1;
            }
            for (int j = 0; j < read; ++j) {
                array[n2 + n4++] = b;
            }
            i -= read;
        }
        while (i > 0) {
            final int read2 = buffer.read(6);
            if (read2 > i) {
                return -1;
            }
            if (read2 == 0) {
                for (int k = 0; k < read2; ++k) {
                    array[n2 + n4++] = b;
                }
                break;
            }
            final byte b2 = (byte)buffer.read(n3);
            for (int l = 0; l < read2; ++l) {
                array[n2 + n4++] = b2;
            }
            i -= read2;
        }
        return 0;
    }
    
    private static int decodeLineDeltaStop(final Buffer buffer, final int n, final byte[] array, final int n2, final int n3, final byte b) {
        int i = n;
        int n4 = 0;
        while (i > 0) {
            int read;
            if (buffer.read1() != 0) {
                read = 1 + buffer.read(5);
                if (read == 0 || read > i) {
                    return -1;
                }
                if (n2 > 0) {
                    for (int j = 0; j < read; ++j) {
                        array[n2 + n4] = array[n2 + n4 - n];
                        ++n4;
                    }
                }
                else {
                    for (int k = 0; k < read; ++k) {
                        array[n2 + n4++] = b;
                    }
                }
            }
            else {
                read = buffer.read(3);
                if (read == 0) {
                    for (int l = 0; l < read; ++l) {
                        array[n2 + n4++] = b;
                    }
                    break;
                }
                if (read > i) {
                    return -1;
                }
                final byte b2 = (byte)buffer.read(n3);
                for (int n5 = 0; n5 < read; ++n5) {
                    array[n2 + n4++] = b2;
                }
            }
            i -= read;
        }
        return 0;
    }
    
    private static int decodeLineBasicZero(final Buffer buffer, final int n, final byte[] array, final int n2, final int n3, final byte b) {
        int i = n;
        int n4 = 0;
        while (i > 0) {
            final byte b2 = (byte)buffer.read(n3);
            int n5;
            if (b2 == b) {
                n5 = 1 + buffer.read(8);
            }
            else {
                n5 = 1 + buffer.read(3);
            }
            if (n5 == 0 || n5 > i) {
                return -1;
            }
            for (int j = 0; j < n5; ++j) {
                array[n2 + n4++] = b2;
            }
            i -= n5;
        }
        return 0;
    }
    
    public static byte[] decodeRLE(final Buffer buffer, final int n, int i, final int n2) {
        final byte[] array = new byte[n * i];
        int n3 = 0;
        final byte b = (byte)buffer.read(n2);
        while (i > 0) {
            int n4 = 0;
            switch (buffer.read(3)) {
                case 0: {
                    n4 = decodeLineEmpty(buffer, n, array, n3, n2, b);
                    break;
                }
                case 2: {
                    n4 = decodeLineDelta(buffer, n, array, n3, n2, b);
                    break;
                }
                case 1: {
                    n4 = decodeLineBasic(buffer, n, array, n3, n2, b);
                    break;
                }
                case 4: {
                    n4 = decodeLineBasicStartEnd(buffer, n, array, n3, n2, b);
                    break;
                }
                case 3: {
                    n4 = decodeLineBasicStop(buffer, n, array, n3, n2, b);
                    break;
                }
                case 5: {
                    n4 = decodeLineDeltaStop(buffer, n, array, n3, n2, b);
                    break;
                }
                case 6: {
                    n4 = decodeLineBasicZero(buffer, n, array, n3, n2, b);
                    break;
                }
                default: {
                    return null;
                }
            }
            if (n4 != 0) {
                return null;
            }
            n3 += n;
            --i;
        }
        return array;
    }
}
