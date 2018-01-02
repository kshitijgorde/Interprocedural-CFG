// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

class InQueryExp extends QueryEval implements QueryExp
{
    private static final long serialVersionUID = -5801329450358952434L;
    private ValueExp val;
    private ValueExp[] valueList;
    
    public InQueryExp() {
    }
    
    public InQueryExp(final ValueExp test, final ValueExp[] list) {
        this.val = test;
        this.valueList = list;
    }
    
    public boolean apply(final ObjectName name) throws BadStringOperationException, BadBinaryOpValueExpException, BadAttributeValueExpException, InvalidApplicationException {
        final ValueExp calcTest = this.val.apply(name);
        for (int i = 0; i < this.valueList.length; ++i) {
            final ValueExp calcList = this.valueList[i].apply(name);
            if (calcTest instanceof NumericValueExp) {
                if (((NumericValueExp)calcTest).getDoubleValue() == ((NumericValueExp)calcList).getDoubleValue()) {
                    return true;
                }
            }
            else if (calcTest instanceof StringValueExp) {
                if (calcTest.toString().equals(calcList.toString())) {
                    return true;
                }
            }
            else if (calcTest instanceof BooleanValueExp && ((BooleanValueExp)calcTest).getValue() == ((BooleanValueExp)calcList).getValue()) {
                return true;
            }
        }
        return false;
    }
    
    public String toString() {
        final StringBuffer buffer = new StringBuffer("(");
        buffer.append(this.val.toString());
        buffer.append(" in ");
        for (int i = 1; i < this.valueList.length; ++i) {
            buffer.append(this.valueList[i].toString());
            buffer.append(" ");
        }
        buffer.append(")");
        return buffer.toString();
    }
}
