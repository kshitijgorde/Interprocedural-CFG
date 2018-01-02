// 
// Decompiled by Procyon v0.5.30
// 

package com.c;

public final class z
{
    private static final String version = "1.0.2";
    public static final int NO_COMPRESSION = 0;
    public static final int BEST_SPEED = 1;
    public static final int BEST_COMPRESSION = 9;
    public static final int DEFAULT_COMPRESSION = -1;
    public static final int FILTERED = 1;
    public static final int HUFFMAN_ONLY = 2;
    public static final int DEFAULT_STRATEGY = 0;
    public static final int NO_FLUSH = 0;
    public static final int PARTIAL_FLUSH = 1;
    public static final int SYNC_FLUSH = 2;
    public static final int FULL_FLUSH = 3;
    public static final int FINISH = 4;
    public static final int OK = 0;
    public static final int STREAM_END = 1;
    public static final int NEED_DICT = 2;
    public static final int ERRNO = -1;
    public static final int STREAM_ERROR = -2;
    public static final int DATA_ERROR = -3;
    public static final int MEM_ERROR = -4;
    public static final int BUF_ERROR = -5;
    public static final int VERSION_ERROR = -6;
    
    public static String version() {
        return "1.0.2";
    }
}
