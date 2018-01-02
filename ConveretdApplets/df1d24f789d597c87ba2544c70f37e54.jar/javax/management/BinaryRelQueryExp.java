// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

class BinaryRelQueryExp extends QueryEval implements QueryExp
{
    private static final long serialVersionUID = -5690656271650491000L;
    private int relOp;
    private ValueExp exp1;
    private ValueExp exp2;
    
    public BinaryRelQueryExp() {
    }
    
    public BinaryRelQueryExp(final int operation, final ValueExp first, final ValueExp second) {
        this.relOp = operation;
        this.exp1 = first;
        this.exp2 = second;
    }
    
    public boolean apply(final ObjectName name) throws BadStringOperationException, BadBinaryOpValueExpException, BadAttributeValueExpException, InvalidApplicationException {
        final ValueExp testFirst = this.exp1.apply(name);
        final ValueExp testSecond = this.exp2.apply(name);
        if (testFirst instanceof NumericValueExp && testSecond instanceof NumericValueExp) {
            switch (this.relOp) {
                case 0: {
                    return ((NumericValueExp)testFirst).getDoubleValue() > ((NumericValueExp)testSecond).getDoubleValue();
                }
                case 2: {
                    return ((NumericValueExp)testFirst).getDoubleValue() >= ((NumericValueExp)testSecond).getDoubleValue();
                }
                case 1: {
                    return ((NumericValueExp)testFirst).getDoubleValue() < ((NumericValueExp)testSecond).getDoubleValue();
                }
                case 3: {
                    return ((NumericValueExp)testFirst).getDoubleValue() <= ((NumericValueExp)testSecond).getDoubleValue();
                }
                case 4: {
                    return ((NumericValueExp)testFirst).getDoubleValue() == ((NumericValueExp)testSecond).getDoubleValue();
                }
            }
        }
        else if (testFirst instanceof StringValueExp && testSecond instanceof StringValueExp) {
            switch (this.relOp) {
                case 0: {
                    return testFirst.toString().compareTo(testSecond.toString()) > 0;
                }
                case 2: {
                    return testFirst.toString().compareTo(testSecond.toString()) >= 0;
                }
                case 1: {
                    return testFirst.toString().compareTo(testSecond.toString()) < 0;
                }
                case 3: {
                    return testFirst.toString().compareTo(testSecond.toString()) <= 0;
                }
                case 4: {
                    return testFirst.toString().compareTo(testSecond.toString()) == 0;
                }
                default: {
                    throw new BadStringOperationException("TODO");
                }
            }
        }
        else if (testFirst instanceof BooleanValueExp && testSecond instanceof BooleanValueExp) {
            switch (this.relOp) {
                case 4: {
                    return ((BooleanValueExp)testFirst).getValue() == ((BooleanValueExp)testSecond).getValue();
                }
            }
        }
        throw new BadBinaryOpValueExpException(testFirst);
    }
    
    public String toString() {
        final StringBuffer buffer = new StringBuffer();
        buffer.append("(");
        buffer.append(this.exp1);
        buffer.append(")");
        switch (this.relOp) {
            case 0: {
                buffer.append(" > ");
                break;
            }
            case 2: {
                buffer.append(" >= ");
                break;
            }
            case 1: {
                buffer.append(" < ");
                break;
            }
            case 3: {
                buffer.append(" <= ");
                break;
            }
            case 4: {
                buffer.append(" == ");
                break;
            }
        }
        buffer.append("(");
        buffer.append(this.exp2);
        buffer.append(")");
        return buffer.toString();
    }
}
