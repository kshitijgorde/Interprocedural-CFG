// 
// Decompiled by Procyon v0.5.30
// 

public class Hyper
{
    public static double cosh(final double n) {
        return (Math.exp(n) + Math.exp(-n)) / 2.0;
    }
    
    public static double cotanh(final double n) {
        return cosh(n) / sinh(n);
    }
    
    public static double sech(final double n) {
        return 1.0 / cosh(n);
    }
    
    public static double sinh(final double n) {
        return (Math.exp(n) - Math.exp(-n)) / 2.0;
    }
    
    public static double tanh(final double n) {
        return sinh(n) / cosh(n);
    }
}
