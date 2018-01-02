// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

class NotQueryExp extends QueryEval implements QueryExp
{
    private static final long serialVersionUID = 5269643775896723397L;
    private QueryExp exp;
    
    public NotQueryExp() {
    }
    
    public NotQueryExp(final QueryExp expression) {
        this.exp = expression;
    }
    
    public boolean apply(final ObjectName name) throws BadStringOperationException, BadBinaryOpValueExpException, BadAttributeValueExpException, InvalidApplicationException {
        return !this.exp.apply(name);
    }
    
    public String toString() {
        return new String("!(" + this.exp.toString() + ")");
    }
}
