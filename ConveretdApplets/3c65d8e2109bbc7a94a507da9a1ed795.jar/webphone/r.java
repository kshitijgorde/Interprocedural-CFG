// 
// Decompiled by Procyon v0.5.30
// 

package webphone;

import java.nio.ByteBuffer;

public class r
{
    public static final int if = 65537;
    public static final int a = 2;
    public static final int for = 4;
    public static final int do = 8;
    private static long[] int;
    
    public static byte[] a(final short n) {
        final ByteBuffer allocate = ByteBuffer.allocate(2);
        allocate.putShort(n);
        return allocate.array();
    }
    
    public static short if(final byte[] array) {
        return ByteBuffer.wrap(array).getShort();
    }
    
    public static String if(final short n) {
        return Integer.toHexString(n);
    }
    
    public static short do(final String s) {
        return Short.parseShort(s, 16);
    }
    
    public static byte[] for(final int n) {
        final ByteBuffer allocate = ByteBuffer.allocate(4);
        allocate.putInt(n);
        return allocate.array();
    }
    
    public static int a(final byte[] array) {
        return ByteBuffer.wrap(array).getInt();
    }
    
    public static String do(final int n) {
        return Integer.toHexString(n);
    }
    
    public static int if(final String s) {
        return Integer.parseInt(s, 16);
    }
    
    public static byte[] a(final long n) {
        final ByteBuffer allocate = ByteBuffer.allocate(8);
        allocate.putLong(n);
        return allocate.array();
    }
    
    public static long do(final byte[] array) {
        return ByteBuffer.wrap(array).getLong();
    }
    
    public static String if(final long n) {
        return Long.toHexString(n);
    }
    
    public static long a(final String s) {
        return Long.parseLong(s, 16);
    }
    
    public static String for(final byte[] array) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < array.length; ++i) {
            if (i % 4 == 0) {
                sb.append("\n");
            }
            sb.append(a(array[i]) + " ");
        }
        return sb.toString();
    }
    
    public static String a(final byte[] array, final int n) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; ++i) {
            if (i % 4 == 0) {
                sb.append("\n");
            }
            sb.append(a(array[i]) + " ");
        }
        return sb.toString();
    }
    
    public static String a(final byte b) {
        final StringBuffer sb = new StringBuffer();
        for (int i = 7; i >= 0; --i) {
            sb.append(b >>> i & 0x1);
        }
        return sb.toString();
    }
    
    public static int if(final int n) {
        if (n >= 32) {
            throw new RuntimeException("Number of bits exceeds Java int.");
        }
        return (int)r.int[n];
    }
    
    public static long a(final int n) {
        if (n >= 64) {
            throw new RuntimeException("Number of bits exceeds Java long.");
        }
        return r.int[n];
    }
    
    static {
        r.int = new long[64];
        for (int i = 1; i < 64; ++i) {
            r.int[i] = (1L << i) - 1L;
        }
    }
}
