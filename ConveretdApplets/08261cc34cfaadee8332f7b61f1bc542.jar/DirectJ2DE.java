// 
// Decompiled by Procyon v0.5.30
// 

class DirectJ2DE extends FormulaD
{
    int iterate(final double n, final double n2, final double n3, final double n4, final double n5, final int n6, final int n7) {
        double n8 = n2;
        double n9 = n;
        double n10 = n8 * n8;
        double n11 = n9 * n9;
        double n12 = 0.7;
        double n13 = 0.7;
        final double n14 = 18.0;
        int n15 = 0;
        do {
            final double n16 = n9 * n12 - n8 * n13;
            n13 = n9 * n13 + n8 * n12;
            n12 = n16;
            n8 = (n9 + n9) * n8 + n4;
            n9 = n11 - n10 + n3;
            n11 = n9 * n9;
            n10 = n8 * n8;
            ++n15;
        } while (n11 + n10 < 100.0 && n15 < n6);
        if (n15 == n6) {
            return n7;
        }
        double n17 = -n14 * (Math.log(Math.log(n11 + n10) * Math.sqrt((n11 + n10) / (n12 * n12 + n13 * n13))) - n15 * 0.693);
        if (n17 < 0.0) {
            n17 = 0.0;
        }
        return (int)n17 % n7;
    }
}
