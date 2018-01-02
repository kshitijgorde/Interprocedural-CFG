// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.data;

public class ConditionalExpression implements ExpressionCommand
{
    private ExpressionProgram trueCase;
    private ExpressionProgram falseCase;
    
    public ConditionalExpression(final ExpressionProgram trueCase, final ExpressionProgram falseCase) {
        this.trueCase = trueCase;
        this.falseCase = falseCase;
    }
    
    public void apply(final StackOfDouble stackOfDouble, final Cases cases) {
        final double pop = stackOfDouble.pop();
        if (cases != null) {
            cases.addCase((int)pop);
        }
        if (pop != 0.0) {
            stackOfDouble.push(this.trueCase.getValueWithCases(cases));
        }
        else if (this.falseCase != null) {
            stackOfDouble.push(this.falseCase.getValueWithCases(cases));
        }
        else {
            stackOfDouble.push(Double.NaN);
        }
    }
    
    public void compileDerivative(final ExpressionProgram expressionProgram, final int n, final ExpressionProgram expressionProgram2, final Variable variable) {
        expressionProgram.copyExpression(n - 1, expressionProgram2);
        expressionProgram2.addCommandObject(new ConditionalExpression((ExpressionProgram)this.trueCase.derivative(variable), (this.falseCase == null) ? null : ((ExpressionProgram)this.falseCase.derivative(variable))));
    }
    
    public int extent(final ExpressionProgram expressionProgram, final int n) {
        return 1 + expressionProgram.extent(n - 1);
    }
    
    public boolean dependsOn(final Variable variable) {
        return this.trueCase.dependsOn(variable) || (this.falseCase != null && this.falseCase.dependsOn(variable));
    }
    
    public void appendOutputString(final ExpressionProgram expressionProgram, final int n, final StringBuffer sb) {
        sb.append('(');
        expressionProgram.appendOutputString(n - 1, sb);
        sb.append(") ? (");
        sb.append(this.trueCase.toString());
        sb.append(')');
        if (this.falseCase != null) {
            sb.append(" : (");
            sb.append(this.falseCase.toString());
            sb.append(')');
        }
    }
}
