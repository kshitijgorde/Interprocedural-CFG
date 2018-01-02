// 
// Decompiled by Procyon v0.5.30
// 

package javax.management;

class BinaryOpValueExp extends QueryEval implements ValueExp
{
    private static final long serialVersionUID = 1216286847881456786L;
    private int op;
    private ValueExp exp1;
    private ValueExp exp2;
    
    public BinaryOpValueExp() {
    }
    
    public BinaryOpValueExp(final int operation, final ValueExp first, final ValueExp second) {
        this.op = operation;
        this.exp1 = first;
        this.exp2 = second;
    }
    
    public ValueExp apply(final ObjectName name) throws BadStringOperationException, BadBinaryOpValueExpException, BadAttributeValueExpException, InvalidApplicationException {
        final ValueExp testFirst = this.exp1.apply(name);
        final ValueExp testSecond = this.exp2.apply(name);
        if (testFirst instanceof NumericValueExp && testSecond instanceof NumericValueExp) {
            if (((NumericValueExp)testFirst).isInteger()) {
                switch (this.op) {
                    case 0: {
                        return Query.value(((NumericValueExp)testFirst).getLongValue() + ((NumericValueExp)testSecond).getLongValue());
                    }
                    case 1: {
                        return Query.value(((NumericValueExp)testFirst).getLongValue() - ((NumericValueExp)testSecond).getLongValue());
                    }
                    case 2: {
                        return Query.value(((NumericValueExp)testFirst).getLongValue() * ((NumericValueExp)testSecond).getLongValue());
                    }
                    case 3: {
                        return Query.value(((NumericValueExp)testFirst).getLongValue() / ((NumericValueExp)testSecond).getLongValue());
                    }
                }
            }
            else {
                switch (this.op) {
                    case 0: {
                        return Query.value(((NumericValueExp)testFirst).getDoubleValue() + ((NumericValueExp)testSecond).getDoubleValue());
                    }
                    case 1: {
                        return Query.value(((NumericValueExp)testFirst).getDoubleValue() - ((NumericValueExp)testSecond).getDoubleValue());
                    }
                    case 2: {
                        return Query.value(((NumericValueExp)testFirst).getDoubleValue() * ((NumericValueExp)testSecond).getDoubleValue());
                    }
                    case 3: {
                        return Query.value(((NumericValueExp)testFirst).getDoubleValue() / ((NumericValueExp)testSecond).getDoubleValue());
                    }
                }
            }
        }
        else if (testFirst instanceof StringValueExp && testSecond instanceof StringValueExp) {
            switch (this.op) {
                case 0: {
                    return Query.value(new String(testFirst.toString() + testSecond.toString()));
                }
                default: {
                    throw new BadStringOperationException("TODO");
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
        switch (this.op) {
            case 0: {
                buffer.append(" + ");
                break;
            }
            case 1: {
                buffer.append(" - ");
                break;
            }
            case 2: {
                buffer.append(" * ");
                break;
            }
            case 3: {
                buffer.append(" / ");
                break;
            }
        }
        buffer.append("(");
        buffer.append(this.exp2);
        buffer.append(")");
        return buffer.toString();
    }
}
