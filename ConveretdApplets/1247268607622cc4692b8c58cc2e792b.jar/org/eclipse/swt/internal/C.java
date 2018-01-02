// 
// Decompiled by Procyon v0.5.30
// 

package org.eclipse.swt.internal;

public class C extends Platform
{
    public static final int PTR_SIZEOF;
    
    static {
        if ("Linux".equals(System.getProperty("os.name")) && "motif".equals("win32")) {
            try {
                Library.loadLibrary("libXm.so.2", false);
            }
            catch (Throwable t) {}
        }
        Library.loadLibrary("swt");
        PTR_SIZEOF = PTR_sizeof();
    }
    
    public static final native void free(final int p0);
    
    public static final native int getenv(final byte[] p0);
    
    public static final native int malloc(final int p0);
    
    public static final native void memmove(final int p0, final byte[] p1, final int p2);
    
    public static final native void memmove(final int p0, final char[] p1, final int p2);
    
    public static final native void memmove(final int p0, final double[] p1, final int p2);
    
    public static final native void memmove(final int p0, final float[] p1, final int p2);
    
    public static final native void memmove(final int p0, final int[] p1, final int p2);
    
    public static final native void memmove(final int p0, final long[] p1, final int p2);
    
    public static final native void memmove(final int p0, final short[] p1, final int p2);
    
    public static final native void memmove(final byte[] p0, final char[] p1, final int p2);
    
    public static final native void memmove(final byte[] p0, final int p1, final int p2);
    
    public static final native void memmove(final int p0, final int p1, final int p2);
    
    public static final native void memmove(final char[] p0, final int p1, final int p2);
    
    public static final native void memmove(final double[] p0, final int p1, final int p2);
    
    public static final native void memmove(final float[] p0, final int p1, final int p2);
    
    public static final native void memmove(final int[] p0, final byte[] p1, final int p2);
    
    public static final native void memmove(final short[] p0, final int p1, final int p2);
    
    public static final native void memmove(final int[] p0, final int p1, final int p2);
    
    public static final native void memmove(final long[] p0, final int p1, final int p2);
    
    public static final native int memset(final int p0, final int p1, final int p2);
    
    public static final native int PTR_sizeof();
    
    public static final native int strlen(final int p0);
}
