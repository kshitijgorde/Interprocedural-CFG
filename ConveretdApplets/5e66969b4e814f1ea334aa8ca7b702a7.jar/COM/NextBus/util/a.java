// 
// Decompiled by Procyon v0.5.30
// 

package COM.NextBus.util;

public class a
{
    public a() {
    }
    
    public static void a(final boolean b, final String s) {
        if (!b) {
            throw new RuntimeException("Assertion Failed: " + s);
        }
    }
    
    public a(final int n, final long n2) {
    }
}
