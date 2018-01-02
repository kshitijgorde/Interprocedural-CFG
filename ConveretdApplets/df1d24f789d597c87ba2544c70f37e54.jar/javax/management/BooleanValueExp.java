// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

class BooleanValueExp extends QueryEval implements ValueExp
{
    private static final long serialVersionUID = 7754922052666594581L;
    private boolean val;
    
    public BooleanValueExp() {
        this.val = false;
    }
    
    public BooleanValueExp(final Boolean value) {
        this.val = value;
    }
    
    public boolean getValue() {
        return this.val;
    }
    
    public ValueExp apply(final ObjectName name) throws BadStringOperationException, BadBinaryOpValueExpException, BadAttributeValueExpException, InvalidApplicationException {
        return this;
    }
    
    public String toString() {
        return Boolean.toString(this.val);
    }
}
