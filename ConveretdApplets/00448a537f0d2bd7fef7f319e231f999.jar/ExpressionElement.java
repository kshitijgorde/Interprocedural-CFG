// 
// Decompiled by Procyon v0.5.30
// 

class ExpressionElement
{
    public int token;
    public double value;
    public int nesting;
    
    public ExpressionElement(final int new_token, final double new_value, final int new_nesting) {
        this.token = new_token;
        this.value = new_value;
        this.nesting = new_nesting;
    }
}
