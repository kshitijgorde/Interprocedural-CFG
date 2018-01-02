// 
// Decompiled by Procyon v0.5.30
// 

package blackmagic.finance.math;

public class CompoundCalculators
{
    static /* synthetic */ Class class$blackmagic$finance$math$CompoundCalculators;
    
    private static void CheckCommonAssertions(final double n, final int n2, final int n3, final double n4) {
        assert n >= 0.0 : "pInitialPrinciple is not >= 0";
        assert n2 >= 1 : "pPaymentsPerYear is not >= 1";
        assert n3 >= 0 : "pPaymentsPerYear is not >= 0";
        assert n4 >= 0.0 && n4 <= 1.0 : "pAnnualTaxRate is not between 0 and 1";
    }
    
    public static double CompoundedAmount(final double n, final double n2, final int n3, final int n4, final double n5) {
        CheckCommonAssertions(n, n3, n4, n5);
        assert n2 >= 0.0 : "pAnnualInterestRate is not >= 0";
        return n * InitialPrincipleMultiplier(n2, n3, n4, n5);
    }
    
    public static double AnnualInterestRate(final double n, final int n2, final int n3, final double n4, final double n5) {
        CheckCommonAssertions(n, n2, n3, n4);
        assert n5 >= n : "pCompoundedAmount is not >= pInitialPrinciple";
        return (root.root(n5 / n, n2 * n3) - 1.0) * n2 / (1.0 - n4);
    }
    
    public static double InitialPrinciple(final double n, final int n2, final int n3, final double n4, final double n5) {
        return n5 / InitialPrincipleMultiplier(n, n2, n3, n4);
    }
    
    public static int YearsToCompound(final double n, final double n2, final int n3, final double n4, final double n5) {
        double compoundBase;
        double n6;
        double pow;
        int n7;
        for (compoundBase = CompoundBase(n2, n3, n4), n6 = n5 / n, pow = compoundBase, n7 = 1; pow < n6; pow = Math.pow(compoundBase, n7 * n3)) {
            ++n7;
        }
        return n7;
    }
    
    private static double InitialPrincipleMultiplier(final double n, final int n2, final int n3, final double n4) {
        return Math.pow(CompoundBase(n, n2, n4), n2 * n3);
    }
    
    private static double CompoundBase(final double n, final int n2, final double n3) {
        return 1.0 + n * (1.0 - n3) / n2;
    }
    
    static /* synthetic */ Class class$(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        $assertionsDisabled = !((CompoundCalculators.class$blackmagic$finance$math$CompoundCalculators == null) ? (CompoundCalculators.class$blackmagic$finance$math$CompoundCalculators = class$("blackmagic.finance.math.CompoundCalculators")) : CompoundCalculators.class$blackmagic$finance$math$CompoundCalculators).desiredAssertionStatus();
    }
}
