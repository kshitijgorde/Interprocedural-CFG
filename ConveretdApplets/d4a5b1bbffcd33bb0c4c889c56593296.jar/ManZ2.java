// 
// Decompiled by Procyon v0.5.30
// 

class ManZ2 extends Formula
{
    ManZ2(final int n, final int n2, final double n3) {
        super(n, n2, n3);
    }
    
    int iterate(final double n, final double n2) {
        double n3 = n2;
        double n4 = n;
        double n5 = n3 * n3;
        double n6 = n4 * n4;
        int n7 = 1;
        do {
            n3 = (n4 + n4) * n3 + n2;
            n4 = n6 - n5 + n;
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
