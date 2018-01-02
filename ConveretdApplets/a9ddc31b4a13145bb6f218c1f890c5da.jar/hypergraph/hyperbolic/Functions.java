// 
// Decompiled by Procyon v0.5.30
// 

package hypergraph.hyperbolic;

public class Functions
{
    public static double sinh(final double n) {
        final double exp = Math.exp(n);
        return 0.5 * (exp - 1.0 / exp);
    }
    
    public static double cosh(final double n) {
        final double exp = Math.exp(n);
        return 0.5 * (exp + 1.0 / exp);
    }
    
    public static double arsinh(final double n) {
        return Math.log(n + Math.sqrt(n * n + 1.0));
    }
    
    public static double arcosh(final double n) {
        return Math.log(n + Math.sqrt(n * n - 1.0));
    }
}
