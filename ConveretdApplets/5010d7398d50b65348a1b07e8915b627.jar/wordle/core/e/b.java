// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core.e;

import java.util.Random;

public final class b
{
    private static Random a;
    
    static {
        b.a = new Random();
    }
    
    public static double a() {
        return b.a.nextDouble();
    }
    
    public static int a(final int n) {
        return b.a.nextInt(n);
    }
}
