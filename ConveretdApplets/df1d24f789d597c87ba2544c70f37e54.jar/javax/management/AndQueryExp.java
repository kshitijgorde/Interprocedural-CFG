// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

class AndQueryExp extends QueryEval implements QueryExp
{
    private static final long serialVersionUID = -1081892073854801359L;
    private QueryExp exp1;
    private QueryExp exp2;
    
    public AndQueryExp() {
    }
    
    public AndQueryExp(final QueryExp first, final QueryExp second) {
        this.exp1 = first;
        this.exp2 = second;
    }
    
    public boolean apply(final ObjectName name) throws BadStringOperationException, BadBinaryOpValueExpException, BadAttributeValueExpException, InvalidApplicationException {
        return this.exp1.apply(name) && this.exp2.apply(name);
    }
    
    public String toString() {
        return new String("(" + this.exp1.toString() + ") && (" + this.exp2.toString()) + ")";
    }
}
