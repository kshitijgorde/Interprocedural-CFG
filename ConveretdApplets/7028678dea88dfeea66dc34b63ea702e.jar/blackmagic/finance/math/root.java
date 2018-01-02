// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.finance.math;

class root
{
    private static double vGuess;
    private static double vExponent;
    private static double vPower;
    
    public static double root(final double n) {
        return root(n, 2.0);
    }
    
    public static double root(final double vPower, final double vExponent) {
        root.vExponent = vExponent;
        root.vPower = vPower;
        return calculateRoot(1.0);
    }
    
    private static double calculateRoot(final double n) {
        final double n2 = n - f(n) / fPrime(n);
        if (closeEnough(n2, n)) {
            return n2;
        }
        return calculateRoot(n2);
    }
    
    private static boolean closeEnough(final double n, final double n2) {
        return Math.abs(n - n2) < Math.abs(n2 * 1.0E-8);
    }
    
    private static double f(final double n) {
        return Math.pow(n, root.vExponent) - root.vPower;
    }
    
    private static double fPrime(final double n) {
        return root.vExponent * Math.pow(n, root.vExponent - 1.0);
    }
}
