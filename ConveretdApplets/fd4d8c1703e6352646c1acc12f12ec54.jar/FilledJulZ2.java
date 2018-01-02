// 
// Decompiled by Procyon v0.5.30
// 

class FilledJulZ2 extends FormulaFJ
{
    int iterate(final double n, final double n2, final double n3, final double n4) {
        double lastI = n2;
        double lastR = n;
        double n5 = lastI * lastI;
        double n6 = lastR * lastR;
        int n7 = 0;
        do {
            lastI = (lastR + lastR) * lastI + n4;
            lastR = n6 - n5 + n3;
            n6 = lastR * lastR;
            n5 = lastI * lastI;
            ++n7;
            if (n6 + n5 > super.maxIZI2) {
                return n7 % super.maxColor;
            }
        } while ((lastR - super.Rorb) * (lastR - super.Rorb) + (lastI - super.Iorb) * (lastI - super.Iorb) > super.eps && n7 < super.MaxIt);
        super.lastR = lastR;
        super.lastI = lastI;
        if (n7 == super.MaxIt) {
            return super.maxColor;
        }
        return n7 % super.maxColor;
    }
}
