// 
// Decompiled by Procyon v0.5.30
// 

class ManZ2 extends Formula
{
    public int iterate(final double n, final double n2, final double n3, final double n4) {
        double n5 = n4;
        double n6 = n3;
        double n7 = n5 * n5;
        double n8 = n6 * n6;
        int n9 = 0;
        do {
            n5 = (n6 + n6) * n5 + n2;
            n6 = n8 - n7 + n;
            n8 = n6 * n6;
            n7 = n5 * n5;
            ++n9;
        } while (n8 + n7 < super.maxIZI2 && n9 < super.MaxIt);
        if (n9 == super.MaxIt) {
            return super.maxColor;
        }
        return n9 % super.maxColor;
    }
}
