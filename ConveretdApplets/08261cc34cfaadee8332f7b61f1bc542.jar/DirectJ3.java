// 
// Decompiled by Procyon v0.5.30
// 

class DirectJ3 extends FormulaD
{
    int iterate(final double n, final double n2, final double n3, final double n4, final double n5, final int n6, final int n7) {
        double n8 = n2;
        double n9 = n;
        double n10 = n8 * n8;
        double n11 = n9 * n9;
        int n12 = 0;
        do {
            n8 *= 3.0 * n11 - n10 - 1.08;
            n9 = (n11 - 3.0 * n10 - 1.08) * n9 + 1.032;
            n11 = n9 * n9;
            n10 = n8 * n8;
            ++n12;
        } while (n11 + n10 < 242.8415 && n12 < n6);
        if (n12 == n6) {
            return n7;
        }
        return n12 % n7;
    }
}
