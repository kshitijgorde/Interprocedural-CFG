// 
// Decompiled by Procyon v0.5.30
// 

public class Parameter
{
    private Chain thisParam;
    private Parameter nextParam;
    
    public Parameter(final Chain thisParam) {
        this.thisParam = thisParam;
        this.nextParam = null;
    }
    
    public void setChain(final Chain thisParam) {
        this.thisParam = thisParam;
    }
    
    public void addNextParameter(final Chain chain) {
        this.nextParam = new Parameter(chain);
    }
    
    public Chain getChain() {
        return this.thisParam;
    }
    
    public Parameter getNextParameter() {
        return this.nextParam;
    }
    
    public String toString() {
        return this.thisParam.toString();
    }
}
