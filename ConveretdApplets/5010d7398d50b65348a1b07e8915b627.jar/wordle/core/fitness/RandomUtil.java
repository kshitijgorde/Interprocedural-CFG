// 
// Decompiled by Procyon v0.5.30
// 

package wordle.core.fitness;

import wordle.core.e.b;

public class RandomUtil
{
    public static double a(final double n) {
        return 0.5 + Math.pow(b.a(), n) * ((b.a() < 0.5) ? -1 : 1);
    }
    
    public static double a() {
        return (b.a() < 0.5) ? -1 : 1;
    }
}
