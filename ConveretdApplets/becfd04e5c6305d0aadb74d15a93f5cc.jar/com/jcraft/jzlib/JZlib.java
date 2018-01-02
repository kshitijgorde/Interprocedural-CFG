// 
// Decompiled by Procyon v0.5.30
// 

package com.jcraft.jzlib;

import com.postx.util.StringConversion;

public final class JZlib
{
    private static final String version = "1.0.2";
    public static final int Z_NO_COMPRESSION = 0;
    public static final int Z_BEST_SPEED = 1;
    public static final int Z_BEST_COMPRESSION = 9;
    public static final int Z_DEFAULT_COMPRESSION = -1;
    public static final int Z_FILTERED = 1;
    public static final int Z_HUFFMAN_ONLY = 2;
    public static final int Z_DEFAULT_STRATEGY = 0;
    public static final int Z_NO_FLUSH = 0;
    public static final int Z_PARTIAL_FLUSH = 1;
    public static final int Z_SYNC_FLUSH = 2;
    public static final int Z_FULL_FLUSH = 3;
    public static final int Z_FINISH = 4;
    public static final int Z_OK = 0;
    public static final int Z_STREAM_END = 1;
    public static final int Z_NEED_DICT = 2;
    public static final int Z_ERRNO = -1;
    public static final int Z_STREAM_ERROR = -2;
    public static final int Z_DATA_ERROR = -3;
    public static final int Z_MEM_ERROR = -4;
    public static final int Z_BUF_ERROR = -5;
    public static final int Z_VERSION_ERROR = -6;
    
    static void CHECK_ERR(final ZStream zStream, final int n, final String s) {
        if (n != 0) {
            if (zStream.msg != null) {
                System.err.print(zStream.msg + " ");
            }
            System.err.println(s + " error: " + n);
        }
    }
    
    public static String zlibDecompress(final byte[] next_in) {
        final int avail_out = 16384;
        final ZStream zStream = new ZStream();
        final byte[] next_out = new byte[avail_out];
        final StringBuffer sb = new StringBuffer();
        zStream.next_in = next_in;
        zStream.next_in_index = 0;
        zStream.avail_in = next_in.length;
        CHECK_ERR(zStream, zStream.inflateInit(), "inflateInit");
        while (true) {
            zStream.next_out = next_out;
            zStream.next_out_index = 0;
            zStream.avail_out = avail_out;
            final int inflate = zStream.inflate(0);
            sb.append(StringConversion.bytesToString(next_out, 0, avail_out - zStream.avail_out));
            if (inflate == 1) {
                break;
            }
            CHECK_ERR(zStream, inflate, "inflate data");
        }
        CHECK_ERR(zStream, zStream.inflateEnd(), "inflateEnd");
        return sb.toString();
    }
    
    public static String version() {
        return "1.0.2";
    }
}
