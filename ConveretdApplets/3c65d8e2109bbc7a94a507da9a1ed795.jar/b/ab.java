// 
// Decompiled by Procyon v0.5.30
// 

package b;

public class ab
{
    public static int a(final int n) {
        int n2 = n >> 1;
        int n3 = 1;
        for (int i = 0; i <= 5; ++i) {
            n2 >>= 1;
            n3 += (n2 & 0x1);
        }
        return n3 & 0x1;
    }
    
    public static int a(final int n, final int n2) {
        int n3 = n >> 1;
        int n4 = 1;
        for (int i = 0; i <= 5; ++i) {
            n3 >>= 1;
            n4 += (n3 & 0x1);
        }
        return n4 + n2 & 0x1;
    }
}
