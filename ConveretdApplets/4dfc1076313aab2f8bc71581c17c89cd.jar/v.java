// 
// Decompiled by Procyon v0.5.30
// 

public class v
{
    public static int[] p;
    public static int[] d;
    
    public static final void p() {
        for (int i = 0; i <= 4096; ++i) {
            v.p[i] = (int)(Math.asin(Math.sqrt(i / 4096.0)) * 8192.0 / 3.141592653589793 + 0.5);
            v.d[i] = (int)(Math.asin(Math.sqrt(i / 1048576.0)) * 8192.0 / 3.141592653589793 + 0.5);
        }
    }
    
    static {
        v.p = new int[4097];
        v.d = new int[4097];
    }
}
