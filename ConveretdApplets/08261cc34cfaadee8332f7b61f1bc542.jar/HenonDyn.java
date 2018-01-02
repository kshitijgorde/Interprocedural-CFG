// 
// Decompiled by Procyon v0.5.30
// 

class HenonDyn extends FormulaD
{
    int iterate(final double n, final double n2, final double n3, final double n4, final double n5, final int n6, final int n7) {
        double n8 = n;
        double n9 = n8 * n8;
        double n10 = n2;
        int n11 = 0;
        do {
            final double n12 = n3 + n9 + n4 * n10;
            n10 = n8;
            n8 = n12;
            n9 = n8 * n8;
            ++n11;
        } while (n9 < 1000.0 && n11 < n6);
        if (n11 == n6) {
            return n7;
        }
        return n11 % n7;
    }
}
