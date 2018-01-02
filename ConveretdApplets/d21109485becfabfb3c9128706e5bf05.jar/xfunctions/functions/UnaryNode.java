// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions.functions;

class UnaryNode extends Expression
{
    static final int MINUS = 1;
    static final int NOT = 2;
    int opcode;
    Expression param;
    
    UnaryNode(final int opcode, final Expression param) {
        if (opcode != 1 && opcode != 2) {
            throw new IllegalArgumentException("Unknown unary operation.");
        }
        this.opcode = opcode;
        this.param = param;
    }
    
    public double value() {
        final double value = this.param.value();
        if (Double.isNaN(value)) {
            return value;
        }
        if (this.opcode == 1) {
            return -value;
        }
        return (value == 0.0) ? 1 : 0;
    }
    
    public Expression derivative(final Variable variable) {
        if (this.opcode == 2) {
            throw new IllegalArgumentException("Attempt to take the derivative of a logical condition.");
        }
        if (this.param.isLocallyConstantWRT(variable)) {
            return new ConstantNode(0.0);
        }
        return new UnaryNode(1, this.param.derivative(variable));
    }
    
    public boolean isLocallyConstantWRT(final Variable variable) {
        return this.param.isLocallyConstantWRT(variable);
    }
    
    boolean isConstant() {
        return this.param.isConstant();
    }
    
    public Expression copy() {
        return new UnaryNode(this.opcode, this.param.copy());
    }
    
    public void put(final StringBuffer sb) {
        if (this.opcode == 2) {
            sb.append("NOT (");
            this.param.put(sb);
            sb.append(')');
        }
        else if (this.param instanceof ConstantNode || this.param instanceof VariableNode || this.param instanceof FunctionNode || this.param instanceof NamedConstantNode) {
            sb.append('-');
            this.param.put(sb);
        }
        else {
            sb.append("-(");
            this.param.put(sb);
            sb.append(')');
        }
    }
    
    public boolean checkCases() {
        return this.param.checkCases();
    }
    
    boolean contains(final MathSymbol mathSymbol) {
        return this.param.contains(mathSymbol);
    }
}
