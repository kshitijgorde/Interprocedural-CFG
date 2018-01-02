// 
// Decompiled by Procyon v0.5.30
// 

class ManExp extends FormulaD
{
    public int iterate(final double n, final double n2, final double n3, final double n4, final double n5, final int n6, final int n7) {
        double n8 = n4;
        double n9 = n3;
        int n10 = 0;
        do {
            final double exp = Math.exp(n9);
            final double n11 = Math.sin(n8) * exp;
            final double n12 = Math.cos(n8) * exp;
            n9 = n * n12 - n2 * n11;
            n8 = n2 * n12 + n * n11;
            ++n10;
        } while (n9 < 50.0 && n10 < n6);
        if (n10 == n6) {
            return n7;
        }
        return n10 % n7;
    }
}
