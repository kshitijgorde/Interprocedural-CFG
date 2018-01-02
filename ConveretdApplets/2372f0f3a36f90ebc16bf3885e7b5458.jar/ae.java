// 
// Decompiled by Procyon v0.5.30
// 

public class ae
{
    public static int a(int i, final int n) {
        int n2 = 0;
        if (n == 0) {
            while (i != 0) {
                ++n2;
                i >>>= 1;
            }
            return n2;
        }
        while (i > 1) {
            ++n2;
            i >>>= 1;
        }
        return n2;
    }
    
    public static int a(int i) {
        int n = 0;
        while (i != 0) {
            n += (i & 0x1);
            i >>>= 1;
        }
        return n;
    }
    
    public static float a(final float n, final int n2) {
        return (float)(n * Math.pow(2.0, n2));
    }
}
