// 
// Decompiled by Procyon v0.5.30
// 

public final class DoubleManipulator
{
    public static final int ROUNDED_HIGH = 1;
    public static final int ROUNDED_LOW = 2;
    public static final int ROUNDED_EXACT = 3;
    public static final int ROUNDED_NONE = 4;
    private static int defaultRound;
    private static int defaultPolicy;
    
    public static double roundTo(final double n) {
        return roundTo(n, DoubleManipulator.defaultRound, DoubleManipulator.defaultPolicy);
    }
    
    public static double roundTo(final double n, final int n2) {
        return roundTo(n, n2, DoubleManipulator.defaultPolicy);
    }
    
    public static double roundTo(final double n, final int n2, final int n3) {
        if (n == 0.0) {
            return 0.0;
        }
        final double pow = Math.pow(10.0, n2);
        double n4 = n * pow;
        switch (n3) {
            default: {
                n4 = 0.0;
                return n4 / pow;
            }
            case 4: {
                return n4 / pow;
            }
            case 1: {
                n4 = Math.ceil(n4);
                return n4 / pow;
            }
            case 2: {
                n4 = Math.floor(n4);
                return n4 / pow;
            }
            case 3: {
                n4 = Math.rint(n4);
                return n4 / pow;
            }
        }
    }
    
    public static void setDefaultRoundedValue(final int defaultRound) {
        DoubleManipulator.defaultRound = defaultRound;
    }
    
    public static void setDefaultPolicyValue(final int defaultPolicy) {
        if (defaultPolicy < 0 || defaultPolicy > 4) {
            System.out.println("Bad policy value");
            DoubleManipulator.defaultPolicy = 3;
        }
        DoubleManipulator.defaultPolicy = defaultPolicy;
    }
    
    static {
        DoubleManipulator.defaultRound = 6;
        DoubleManipulator.defaultPolicy = 3;
    }
}
