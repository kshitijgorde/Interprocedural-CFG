// 
// Decompiled by Procyon v0.5.30
// 

class DEJuliaZ2bas extends FormulaJ
{
    public int iterate(final double n, final double n2, final double n3, final double n4) {
        double lastI = n2;
        double lastR = n;
        double n5 = lastI * lastI;
        double n6 = lastR * lastR;
        double n7 = 0.7;
        double n8 = 0.7;
        final double n9 = 18.0;
        int n10 = 0;
        do {
            final double n11 = lastR * n7 - lastI * n8;
            n8 = lastR * n8 + lastI * n7;
            n7 = n11;
            lastI = (lastR + lastR) * lastI + n4;
            lastR = n6 - n5 + n3;
            n6 = lastR * lastR;
            n5 = lastI * lastI;
            ++n10;
            if (n6 + n5 > 100.0) {
                double n12 = -n9 * (Math.log(Math.log(n6 + n5) * Math.sqrt((n6 + n5) / (n7 * n7 + n8 * n8))) - n10 * 0.693);
                if (n12 < 0.0) {
                    n12 = 0.0;
                }
                return (int)n12 % super.maxColor;
            }
        } while (Math.abs(lastR - super.starR) + Math.abs(lastI - super.starI) > 1.0E-5 && n10 < super.MaxIt);
        super.lastR = lastR;
        super.lastI = lastI;
        if (n10 == super.MaxIt) {
            return super.maxColor;
        }
        return n10 % super.nStar * (super.maxColor / super.nStar);
    }
}
