// 
// Decompiled by Procyon v0.5.30
// 

class JulZ3basin extends FormulaJ
{
    int iterate(final double n, final double n2, final double n3, final double n4) {
        double lastI = n2;
        double lastR = n;
        double n5 = lastI * lastI;
        double n6 = lastR * lastR;
        int n7 = 0;
        do {
            lastI = (3.0 * n6 - n5) * lastI + n4;
            lastR = lastR * (n6 - 3.0 * n5) + n3;
            n6 = lastR * lastR;
            n5 = lastI * lastI;
            ++n7;
            if (n6 + n5 > super.maxIZI2) {
                return n7 % super.maxColor;
            }
        } while (Math.abs(lastR - super.starR) + Math.abs(lastI - super.starI) > 1.0E-5 && n7 < super.MaxIt);
        super.lastR = lastR;
        super.lastI = lastI;
        if (n7 == super.MaxIt) {
            return super.maxColor;
        }
        return n7 % super.nStar * (super.maxColor / super.nStar);
    }
}
