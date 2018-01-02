// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions.functions;

class FunctionNode extends Expression
{
    Function function;
    Expression[] params;
    
    FunctionNode(final Function function, final Expression[] params) {
        if (params.length != function.getArity()) {
            throw new IllegalArgumentException("Attempt to create a function with incorrect number of paramters.");
        }
        this.function = function;
        this.params = params;
    }
    
    public double value() {
        final int arity = this.function.getArity();
        if (arity == 1) {
            return this.function.eval(this.params[0].value());
        }
        if (arity == 2) {
            return this.function.eval(this.params[0].value(), this.params[1].value());
        }
        final double[] array = new double[this.params.length];
        for (int i = 0; i < this.params.length; ++i) {
            array[i] = this.params[i].value();
            if (Double.isNaN(array[i])) {
                return array[i];
            }
        }
        return this.function.eval(array);
    }
    
    public Expression derivative(final Variable variable) {
        Expression expression = null;
        for (int i = 0; i < this.params.length; ++i) {
            if (!this.params[i].isLocallyConstantWRT(variable)) {
                final Function derivative = this.function.derivative(i + 1);
                final Expression[] array = new Expression[this.params.length];
                for (int j = 0; j < this.params.length; ++j) {
                    array[j] = this.params[j].copy();
                }
                final Expression derivative2 = this.params[i].derivative(variable);
                Expression expression2;
                if (derivative2 instanceof ConstantNode && derivative2.value() == 1.0) {
                    expression2 = new FunctionNode(derivative, array);
                }
                else {
                    expression2 = new BinaryNode(3, new FunctionNode(derivative, array), this.params[i].derivative(variable));
                }
                if (expression == null) {
                    expression = expression2;
                }
                else {
                    expression = new BinaryNode(1, expression, expression2);
                }
            }
        }
        if (expression == null) {
            return new ConstantNode(0.0);
        }
        return expression;
    }
    
    public boolean isLocallyConstantWRT(final Variable variable) {
        for (int i = 0; i < this.params.length; ++i) {
            if (!this.params[i].isLocallyConstantWRT(variable)) {
                return false;
            }
        }
        return true;
    }
    
    boolean isConstant() {
        for (int i = 0; i < this.params.length; ++i) {
            if (!this.params[i].isConstant()) {
                return false;
            }
        }
        return true;
    }
    
    public Expression copy() {
        final Expression[] array = new Expression[this.params.length];
        for (int i = 0; i < this.params.length; ++i) {
            array[i] = this.params[i].copy();
        }
        return new FunctionNode(this.function, array);
    }
    
    public void put(final StringBuffer sb) {
        sb.append(this.function.getName());
        sb.append('(');
        for (int i = 0; i < this.params.length; ++i) {
            this.params[i].put(sb);
            if (i < this.params.length - 1) {
                sb.append(", ");
            }
            else {
                sb.append(')');
            }
        }
    }
    
    public boolean checkCases() {
        for (int i = 0; i < this.params.length; ++i) {
            if (!this.params[i].checkCases()) {
                return false;
            }
        }
        return this.function.checkCases();
    }
    
    boolean contains(final MathSymbol mathSymbol) {
        if (this.function == mathSymbol || this.function.contains(mathSymbol)) {
            return true;
        }
        for (int i = 0; i < this.params.length; ++i) {
            if (this.params[i].contains(mathSymbol)) {
                return true;
            }
        }
        return false;
    }
}
