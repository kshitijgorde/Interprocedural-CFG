// 
// Decompiled by Procyon v0.5.30
// 

abstract class FormulaA
{
    int MaxIt;
    int maxColor;
    double maxIZI2;
    
    FormulaA(final int maxIt, final int maxColor, final double maxIZI2) {
        this.MaxIt = maxIt;
        this.maxColor = maxColor;
        this.maxIZI2 = maxIZI2;
    }
    
    abstract int iterate(final double p0, final double p1);
}
