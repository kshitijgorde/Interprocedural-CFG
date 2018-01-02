// 
// Decompiled by Procyon v0.5.30
// 

class ManZ4A extends FormulaA
{
    ManZ4A(final int n, final int n2, final double n3) {
        super(n, n2, n3);
    }
    
    public int iterate(final double n, final double n2) {
        double n3 = n2;
        double n4 = n;
        double n5 = n3 * n3;
        double n6 = n4 * n4;
        int n7 = 0;
        do {
            final double n8 = n6 - n5;
            final double n9 = n4 * n3;
            final double n10 = 4.0 * n9;
            n3 = n8 * n10 + n2;
            n4 = n8 * n8 - n10 * n9 + n;
            n6 = n4 * n4;
            n5 = n3 * n3;
            ++n7;
        } while (n6 + n5 < super.maxIZI2 && n7 < super.MaxIt);
        if (n7 == super.MaxIt) {
            return super.maxColor;
        }
        return n7 % super.maxColor;
    }
}
