// 
// Decompiled by Procyon v0.5.30
// 

class Biquad extends FormulaD
{
    int iterate(final double n, final double n2, final double n3, final double n4, final double n5, final int n6, final int n7) {
        double n8 = 0.0;
        int n9 = 0;
        do {
            final double n10 = n8 + n;
            final double n11 = n10 * n10 + n2;
            n8 = n11 * n11;
            ++n9;
        } while (n8 < 1000.0 && n9 < n6);
        final int n12 = n9;
        int n13 = 0;
        if (n < 0.0) {
            final double sqrt = Math.sqrt(-n);
            double n14 = sqrt * sqrt;
            do {
                final double n15 = n14 + n;
                final double n16 = n15 * n15 + n2;
                n14 = n16 * n16;
                ++n13;
            } while (n14 < 1000.0 && n13 < n6);
            if (n13 == n6) {
                if (n12 == n6) {
                    return n7;
                }
                return 2 * n7 / 3;
            }
            else {
                if (n12 == n6) {
                    return n7 / 2;
                }
                return (n13 + n12) / 2 % n7;
            }
        }
        else {
            if (n12 == n6) {
                return n7 / 2;
            }
            return n12 % n7;
        }
    }
}
