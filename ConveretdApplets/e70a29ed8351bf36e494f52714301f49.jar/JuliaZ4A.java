// 
// Decompiled by Procyon v0.5.30
// 

class JuliaZ4A extends FormulaA
{
    double Cr;
    double Ci;
    
    JuliaZ4A(final double cr, final double ci, final int n, final int n2, final double n3) {
        super(n, n2, n3);
        this.Cr = cr;
        this.Ci = ci;
    }
    
    int iterate(final double n, final double n2) {
        double n3 = n2;
        double n4 = n;
        double n5 = n3 * n3;
        double n6 = n4 * n4;
        int n7 = 0;
        do {
            final double n8 = n6 - n5;
            final double n9 = n4 * n3;
            final double n10 = 4.0 * n9;
            n3 = n8 * n10 + this.Ci;
            n4 = n8 * n8 - n10 * n9 + this.Cr;
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
