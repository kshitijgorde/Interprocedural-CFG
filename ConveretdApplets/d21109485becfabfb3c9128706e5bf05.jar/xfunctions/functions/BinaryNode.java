// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions.functions;

class BinaryNode extends Expression
{
    static final int PLUS = 1;
    static final int MINUS = 2;
    static final int TIMES = 3;
    static final int DIVIDE = 4;
    static final int POWER = 5;
    static final int AND = 6;
    static final int OR = 7;
    static final int EQ = 8;
    static final int NE = 9;
    static final int LT = 10;
    static final int GT = 11;
    static final int LE = 12;
    static final int GE = 13;
    int opcode;
    Expression param1;
    Expression param2;
    private boolean case1;
    private boolean case2;
    
    BinaryNode(final int opcode, final Expression param1, final Expression param2) {
        if (opcode < 1 || opcode > 13) {
            throw new IllegalArgumentException("Unknown binary operation.");
        }
        this.opcode = opcode;
        this.param1 = param1;
        this.param2 = param2;
    }
    
    public double value() {
        final double value = this.param1.value();
        if (Double.isNaN(value)) {
            return value;
        }
        final double value2 = this.param2.value();
        if (this.opcode == 6) {
            if (value == 0.0) {
                return 0.0;
            }
            return (value2 != 0.0) ? 1 : 0;
        }
        else if (this.opcode == 7) {
            if (value != 0.0) {
                return 1.0;
            }
            return (value2 != 0.0) ? 1 : 0;
        }
        else {
            if (Double.isNaN(value2)) {
                return value2;
            }
            switch (this.opcode) {
                case 1: {
                    return value + value2;
                }
                case 2: {
                    return value - value2;
                }
                case 3: {
                    return value * value2;
                }
                case 4: {
                    this.case2 = this.case1;
                    this.case1 = (value2 > 0.0);
                    return value / value2;
                }
                case 5: {
                    return Math.pow(value, value2);
                }
                case 8: {
                    return (value == value2) ? 1 : 0;
                }
                case 9: {
                    return (value != value2) ? 1 : 0;
                }
                case 10: {
                    return (value < value2) ? 1 : 0;
                }
                case 11: {
                    return (value > value2) ? 1 : 0;
                }
                case 12: {
                    return (value <= value2) ? 1 : 0;
                }
                case 13: {
                    return (value >= value2) ? 1 : 0;
                }
                default: {
                    return 0.0;
                }
            }
        }
    }
    
    public Expression derivative(final Variable variable) {
        Expression derivative;
        if (this.param1.isLocallyConstantWRT(variable)) {
            derivative = null;
        }
        else {
            derivative = this.param1.derivative(variable);
        }
        Expression derivative2;
        if (this.param2.isLocallyConstantWRT(variable)) {
            derivative2 = null;
        }
        else {
            derivative2 = this.param2.derivative(variable);
        }
        if (derivative == null && derivative2 == null) {
            return new ConstantNode(0.0);
        }
        switch (this.opcode) {
            case 1: {
                if (derivative == null) {
                    return derivative2;
                }
                if (derivative2 == null) {
                    return derivative;
                }
                return new BinaryNode(1, derivative, derivative2);
            }
            case 2: {
                if (derivative == null) {
                    return new UnaryNode(1, derivative2);
                }
                if (derivative2 == null) {
                    return derivative;
                }
                return new BinaryNode(2, derivative, derivative2);
            }
            case 3: {
                if (derivative == null) {
                    if (derivative2 instanceof ConstantNode && derivative2.value() == 1.0) {
                        return this.param1.copy();
                    }
                    return new BinaryNode(3, this.param1.copy(), derivative2);
                }
                else {
                    if (derivative2 != null) {
                        return new BinaryNode(1, (derivative2 instanceof ConstantNode && derivative2.value() == 1.0) ? this.param1.copy() : new BinaryNode(3, this.param1.copy(), derivative2), (derivative instanceof ConstantNode && derivative.value() == 1.0) ? this.param2.copy() : new BinaryNode(3, derivative, this.param2.copy()));
                    }
                    if (derivative instanceof ConstantNode && derivative.value() == 1.0) {
                        return this.param2.copy();
                    }
                    return new BinaryNode(3, this.param2.copy(), derivative);
                }
                break;
            }
            case 4: {
                if (derivative2 == null) {
                    return new BinaryNode(4, derivative, this.param2.copy());
                }
                if (derivative != null) {
                    return new BinaryNode(4, new BinaryNode(2, new BinaryNode(3, this.param2.copy(), derivative), new BinaryNode(3, this.param1.copy(), derivative2)), new BinaryNode(5, this.param2.copy(), new ConstantNode(2.0)));
                }
                final BinaryNode binaryNode = new BinaryNode(4, new UnaryNode(1, this.param1.copy()), new BinaryNode(5, this.param2.copy(), new ConstantNode(2.0)));
                if (derivative2 instanceof ConstantNode && derivative2.value() == 1.0) {
                    return binaryNode;
                }
                return new BinaryNode(3, binaryNode, derivative2);
            }
            case 5: {
                if (derivative == null) {
                    Expression copy;
                    if (this.param1 instanceof NamedConstantNode && ((NamedConstantNode)this.param1).value() == 2.718281828459045) {
                        copy = this.copy();
                    }
                    else {
                        copy = new BinaryNode(3, this.copy(), new StandardFunctionNode(11, this.param1.copy()));
                    }
                    if (derivative2 instanceof ConstantNode && ((ConstantNode)derivative2).value == 1.0) {
                        return copy;
                    }
                    return new BinaryNode(3, copy, derivative2);
                }
                else {
                    if (derivative2 != null) {
                        return new BinaryNode(3, this.copy(), new BinaryNode(1, new BinaryNode(3, this.param2.copy(), new BinaryNode(4, derivative, this.param1.copy())), new BinaryNode(3, new StandardFunctionNode(11, this.param1.copy()), derivative2)));
                    }
                    BinaryNode binaryNode2;
                    if (this.param2 instanceof ConstantNode) {
                        if (((ConstantNode)this.param2).value == 0.0) {
                            return new ConstantNode(0.0);
                        }
                        if (((ConstantNode)this.param2).value == 1.0) {
                            return derivative;
                        }
                        if (((ConstantNode)this.param2).value == 2.0) {
                            binaryNode2 = new BinaryNode(3, this.param2.copy(), this.param1.copy());
                        }
                        else {
                            binaryNode2 = new BinaryNode(3, this.param2.copy(), new BinaryNode(5, this.param1.copy(), new ConstantNode(((ConstantNode)this.param2).value - 1.0)));
                        }
                    }
                    else {
                        binaryNode2 = new BinaryNode(3, this.param2.copy(), new BinaryNode(5, this.param1.copy(), new BinaryNode(2, this.param2.copy(), new ConstantNode(1.0))));
                    }
                    if (derivative instanceof ConstantNode && ((ConstantNode)derivative).value == 1.0) {
                        return binaryNode2;
                    }
                    return new BinaryNode(3, binaryNode2, derivative);
                }
                break;
            }
            default: {
                throw new IllegalArgumentException("Attempt to take the derivative of a logical condition.");
            }
        }
    }
    
    public boolean isLocallyConstantWRT(final Variable variable) {
        return this.param1.isLocallyConstantWRT(variable) && this.param2.isLocallyConstantWRT(variable);
    }
    
    boolean isConstant() {
        return this.param1.isConstant() && this.param2.isConstant();
    }
    
    public Expression copy() {
        return new BinaryNode(this.opcode, this.param1.copy(), this.param2.copy());
    }
    
    public void put(final StringBuffer sb) {
        if (this.opcode > 7) {
            this.param1.put(sb);
        }
        else if (this.opcode >= 6 || (this.param1 instanceof BinaryNode && (((BinaryNode)this.param1).opcode < this.opcode || (((BinaryNode)this.param1).opcode == this.opcode && this.opcode == 5))) || this.param1 instanceof UnaryNode) {
            sb.append('(');
            this.param1.put(sb);
            sb.append(')');
        }
        else {
            this.param1.put(sb);
        }
        switch (this.opcode) {
            case 1: {
                sb.append(" + ");
                break;
            }
            case 2: {
                sb.append(" - ");
                break;
            }
            case 3: {
                sb.append(" * ");
                break;
            }
            case 4: {
                sb.append(" / ");
                break;
            }
            case 5: {
                sb.append("^");
                break;
            }
            case 6: {
                sb.append(" AND ");
                break;
            }
            case 7: {
                sb.append(" OR ");
                break;
            }
            case 8: {
                sb.append(" = ");
                break;
            }
            case 9: {
                sb.append(" <> ");
                break;
            }
            case 10: {
                sb.append(" < ");
                break;
            }
            case 11: {
                sb.append(" > ");
                break;
            }
            case 12: {
                sb.append(" <= ");
                break;
            }
            case 13: {
                sb.append(" >= ");
                break;
            }
        }
        if (this.opcode > 7) {
            this.param2.put(sb);
        }
        else if (this.opcode >= 6 || (this.param2 instanceof BinaryNode && (((BinaryNode)this.param2).opcode < this.opcode || (((BinaryNode)this.param2).opcode == this.opcode && (this.opcode == 2 || this.opcode == 4 || this.opcode == 5)))) || this.param2 instanceof UnaryNode) {
            sb.append('(');
            this.param2.put(sb);
            sb.append(')');
        }
        else {
            this.param2.put(sb);
        }
    }
    
    public boolean checkCases() {
        if (this.opcode == 4) {
            return this.case1 == this.case2 && this.param1.checkCases() && this.param2.checkCases();
        }
        return this.param1.checkCases() && this.param2.checkCases();
    }
    
    boolean contains(final MathSymbol mathSymbol) {
        return this.param1.contains(mathSymbol) || this.param2.contains(mathSymbol);
    }
}
