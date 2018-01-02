// 
// Decompiled by Procyon v0.5.30
// 

public final class L
{
    private static float[] a;
    
    public static String a(final int n) {
        String s;
        for (s = Integer.toHexString(n); s.length() < 4; s = "0" + s) {}
        return s.toUpperCase();
    }
    
    static {
        L.a = new float[100000];
        for (int i = 0; i < L.a.length; ++i) {
            L.a[i] = (float)Math.random();
        }
    }
}
