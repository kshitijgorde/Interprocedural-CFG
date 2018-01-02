// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions.functions;

public class StandardFunctionNode extends Expression
{
    Expression param;
    StandardFunction function;
    private long case1;
    private long case2;
    
    StandardFunctionNode(final int n, final Expression param) {
        this.param = param;
        this.function = StandardFunction.function(n);
    }
    
    public double value() {
        final double value = this.param.value();
        this.case1 = this.case2;
        if (Double.isNaN(value)) {
            this.case2 = Long.MIN_VALUE;
            return value;
        }
        final double eval = this.function.eval(value);
        this.case2 = this.function.getCaseCode();
        return eval;
    }
    
    public Expression derivative(final Variable variable) {
        return this.function.derivative(this.param, variable);
    }
    
    public boolean isLocallyConstantWRT(final Variable variable) {
        final int opcode = this.function.getOpcode();
        return opcode == 17 || opcode == 16 || opcode == 19 || opcode == 18 || this.param.isLocallyConstantWRT(variable);
    }
    
    boolean isConstant() {
        return this.param.isConstant();
    }
    
    public Expression copy() {
        return new StandardFunctionNode(this.function.getOpcode(), this.param.copy());
    }
    
    public void put(final StringBuffer sb) {
        sb.append(this.function.getName());
        sb.append('(');
        this.param.put(sb);
        sb.append(')');
    }
    
    public boolean checkCases() {
        return this.case1 == this.case2 && this.param.checkCases();
    }
    
    boolean contains(final MathSymbol mathSymbol) {
        return this.function == mathSymbol || this.param.contains(mathSymbol);
    }
}
