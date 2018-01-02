// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions.functions;

public abstract class Expression
{
    public abstract double value();
    
    public abstract Expression derivative(final Variable p0);
    
    public abstract boolean isLocallyConstantWRT(final Variable p0);
    
    abstract boolean isConstant();
    
    public abstract Expression copy();
    
    public abstract void put(final StringBuffer p0);
    
    abstract boolean contains(final MathSymbol p0);
    
    public boolean checkCases() {
        return true;
    }
}
