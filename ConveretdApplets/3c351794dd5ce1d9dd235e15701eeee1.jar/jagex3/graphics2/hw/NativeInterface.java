// 
// Decompiled by Procyon v0.5.30
// 

package jagex3.graphics2.hw;

public class NativeInterface
{
    long peer;
    
    public final native void setSunColour(final float p0, final float p1, final float p2, final float p3, final float p4);
    
    private final native void init(final int p0, final int p1);
    
    public final native void release();
    
    public final native void copyPositions(final int[] p0, final int[] p1, final int[] p2, final short[] p3, final int p4, final int p5, final int p6, final long p7);
    
    public final native void copyTexCoords(final float[] p0, final float[] p1, final int p2, final int p3, final int p4, final long p5);
    
    public final native void initTextureMetrics(final int p0, final byte p1, final byte p2);
    
    public final native void copyNormals(final short[] p0, final short[] p1, final short[] p2, final byte[] p3, final float p4, final float p5, final int p6, final int p7, final int p8, final long p9);
    
    public final native void copyLighting(final short[] p0, final byte[] p1, final short[] p2, final short[] p3, final short[] p4, final short[] p5, final byte[] p6, final int p7, final int p8, final short[] p9, final int p10, final int p11, final int p12, final long p13);
    
    public final native void copyColours(final short[] p0, final byte[] p1, final short[] p2, final int p3, final short[] p4, final int p5, final int p6, final int p7, final long p8);
    
    public NativeInterface(final int n, final int n2) {
        try {
            this.init(n, n2);
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public final native void setAmbient(final float p0);
    
    public final native void setSunDirection(final float p0, final float p1, final float p2);
}
