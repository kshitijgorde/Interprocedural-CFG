// 
// Decompiled by Procyon v0.5.30
// 

package com.fluendo.jheora;

public class Constants
{
    public static final int CURRENT_ENCODE_VERSION = 1;
    public static final int BLOCK_HEIGHT_WIDTH = 8;
    public static final int HFRAGPIXELS = 8;
    public static final int VFRAGPIXELS = 8;
    public static final int BLOCK_SIZE = 64;
    public static final int UMV_BORDER = 16;
    public static final int STRIDE_EXTRA = 32;
    public static final int Q_TABLE_SIZE = 64;
    public static final int BASE_FRAME = 0;
    public static final int NORMAL_FRAME = 1;
    public static final int MAX_MODES = 8;
    public static final int MODE_BITS = 3;
    public static final int MODE_METHODS = 8;
    public static final int MODE_METHOD_BITS = 3;
    public static final int[] dequant_index;
    
    static {
        dequant_index = new int[] { 0, 1, 8, 16, 9, 2, 3, 10, 17, 24, 32, 25, 18, 11, 4, 5, 12, 19, 26, 33, 40, 48, 41, 34, 27, 20, 13, 6, 7, 14, 21, 28, 35, 42, 49, 56, 57, 50, 43, 36, 29, 22, 15, 23, 30, 37, 44, 51, 58, 59, 52, 45, 38, 31, 39, 46, 53, 60, 61, 54, 47, 55, 62, 63 };
    }
}
