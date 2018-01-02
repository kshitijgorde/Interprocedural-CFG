// 
// Decompiled by Procyon v0.5.30
// 

abstract class FormulaFJ
{
    int MaxIt;
    int maxColor;
    double maxIZI2;
    double eps;
    double Rorb;
    double Iorb;
    double lastR;
    double lastI;
    
    void set(final int maxIt, final int maxColor, final double maxIZI2, final double eps, final double rorb, final double iorb) {
        this.MaxIt = maxIt;
        this.maxColor = maxColor;
        this.maxIZI2 = maxIZI2;
        this.eps = eps;
        this.Rorb = rorb;
        this.Iorb = iorb;
    }
    
    abstract int iterate(final double p0, final double p1, final double p2, final double p3);
}
