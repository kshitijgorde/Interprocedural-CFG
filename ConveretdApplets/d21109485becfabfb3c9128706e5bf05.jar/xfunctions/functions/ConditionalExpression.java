// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions.functions;

public class ConditionalExpression extends Expression
{
    Expression condition;
    Expression trueCase;
    Expression falseCase;
    int case1;
    int case2;
    
    ConditionalExpression(final Expression expression, final Expression expression2) {
        this(expression, expression2, null);
    }
    
    ConditionalExpression(final Expression condition, final Expression trueCase, final Expression falseCase) {
        if (condition == null || trueCase == null) {
            throw new IllegalArgumentException("Null data provided for ConditionalExpression.");
        }
        this.condition = condition;
        this.trueCase = trueCase;
        this.falseCase = falseCase;
    }
    
    public double value() {
        this.case2 = this.case1;
        if (this.condition.value() != 0.0) {
            this.case1 = 1;
            return this.trueCase.value();
        }
        if (this.falseCase == null) {
            this.case1 = 0;
            return Double.NaN;
        }
        this.case1 = 2;
        return this.falseCase.value();
    }
    
    public Expression derivative(final Variable variable) {
        if (this.isLocallyConstantWRT(variable)) {
            return new ConstantNode(0.0);
        }
        return new ConditionalExpression(this.condition.copy(), this.trueCase.derivative(variable), (this.falseCase == null) ? null : this.falseCase.derivative(variable));
    }
    
    public boolean isLocallyConstantWRT(final Variable variable) {
        return this.condition.isLocallyConstantWRT(variable) && this.trueCase.isLocallyConstantWRT(variable) && (this.falseCase == null || this.falseCase.isLocallyConstantWRT(variable));
    }
    
    boolean isConstant() {
        return this.condition.isConstant() && this.trueCase.isConstant() && (this.falseCase == null || this.falseCase.isConstant());
    }
    
    public Expression copy() {
        return new ConditionalExpression(this.condition.copy(), this.trueCase.copy(), (this.falseCase == null) ? null : this.falseCase.copy());
    }
    
    public void put(final StringBuffer sb) {
        sb.append("(");
        this.condition.put(sb);
        sb.append(")? ");
        this.trueCase.put(sb);
        if (this.falseCase != null) {
            sb.append(" : ");
            this.falseCase.put(sb);
        }
    }
    
    public boolean checkCases() {
        if (this.case1 != this.case2) {
            return false;
        }
        if (this.case1 == 1) {
            return this.trueCase.checkCases();
        }
        return this.case1 != 2 || this.falseCase.checkCases();
    }
    
    boolean contains(final MathSymbol mathSymbol) {
        return this.condition.contains(mathSymbol) || this.trueCase.contains(mathSymbol) || (this.falseCase != null && this.falseCase.contains(mathSymbol));
    }
}
