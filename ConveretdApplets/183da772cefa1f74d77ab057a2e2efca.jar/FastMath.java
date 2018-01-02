// 
// Decompiled by Procyon v0.5.30
// 

public class FastMath
{
    public static final int TWOPI_BITS = 8;
    public static final int TWOPI = 256;
    public static final int PIHALF = 64;
    public static final int TWOPI_MASK = 255;
    private static int[] costable;
    
    public static int sin(final int n) {
        return FastMath.costable[Math.abs(64 - n) & 0xFF];
    }
    
    public static int cos(final int n) {
        return FastMath.costable[Math.abs(n) & 0xFF];
    }
    
    static {
        FastMath.costable = new int[256];
        for (int i = 0; i < 256; ++i) {
            FastMath.costable[i] = (int)(Math.cos(6.283185307179586 * i / 256.0) * 256.0);
        }
    }
}
