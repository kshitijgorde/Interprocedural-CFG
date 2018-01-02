// 
// Decompiled by Procyon v0.5.30
// 

abstract class FormulaJ
{
    int MaxIt;
    int maxColor;
    int nStar;
    double maxIZI2;
    double starR;
    double starI;
    double lastR;
    double lastI;
    
    void set(final int maxIt, final int maxColor, final double maxIZI2, final int nStar, final double starR, final double starI) {
        this.MaxIt = maxIt;
        this.maxColor = maxColor;
        this.maxIZI2 = maxIZI2;
        this.nStar = nStar;
        this.starR = starR;
        this.starI = starI;
    }
    
    abstract int iterate(final double p0, final double p1, final double p2, final double p3);
}
