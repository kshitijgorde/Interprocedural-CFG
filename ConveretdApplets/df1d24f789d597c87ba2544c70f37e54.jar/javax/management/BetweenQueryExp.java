// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

import org.jboss.mx.util.QueryExpSupport;

class BetweenQueryExp extends QueryEval implements QueryExp
{
    private static final long serialVersionUID = -2933597532866307444L;
    private ValueExp exp1;
    private ValueExp exp2;
    private ValueExp exp3;
    
    public BetweenQueryExp() {
    }
    
    public BetweenQueryExp(final ValueExp test, final ValueExp lower, final ValueExp upper) {
        this.exp1 = test;
        this.exp2 = lower;
        this.exp3 = upper;
    }
    
    public boolean apply(final ObjectName name) throws BadStringOperationException, BadBinaryOpValueExpException, BadAttributeValueExpException, InvalidApplicationException {
        final ValueExp calcTest = this.exp1.apply(name);
        final ValueExp calcLower = this.exp2.apply(name);
        final ValueExp calcUpper = this.exp3.apply(name);
        if (calcTest instanceof NumericValueExp && calcLower instanceof NumericValueExp && calcUpper instanceof NumericValueExp) {
            final double valueTest = ((NumericValueExp)calcTest).getDoubleValue();
            final double valueLower = ((NumericValueExp)calcLower).getDoubleValue();
            final double valueUpper = ((NumericValueExp)calcUpper).getDoubleValue();
            return valueLower <= valueTest && valueTest <= valueUpper;
        }
        if (calcTest instanceof StringValueExp && calcLower instanceof StringValueExp && calcUpper instanceof StringValueExp) {
            final String valueTest2 = calcTest.toString();
            final String valueLower2 = calcLower.toString();
            final String valueUpper2 = calcUpper.toString();
            return valueLower2.compareTo(valueTest2) <= 0 && valueUpper2.compareTo(valueTest2) >= 0;
        }
        throw new BadBinaryOpValueExpException(calcTest);
    }
    
    public void setMBeanServer(final MBeanServer server) {
        QueryExpSupport.server.set(server);
    }
    
    public String toString() {
        return new String("(" + this.exp2.toString() + ") <= (" + this.exp1.toString() + ") <= (" + this.exp3.toString()) + ")";
    }
}
