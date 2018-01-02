// 
// Decompiled by Procyon v0.5.30
// 

package xfunctions.functions;

public class ExpressionFunction extends Function
{
    private Expression definition;
    public String[] definitionStrings;
    
    public Expression getDefinition() {
        return this.definition;
    }
    
    public void setDefinition(final Expression definition) {
        this.definition = definition;
    }
    
    public void setDefinition(final Expression definition, final Variable variable) {
        this.definition = definition;
        super.params[0] = variable;
    }
    
    public ExpressionFunction(final String name, final Variable variable, final Expression definition) {
        if (variable == null) {
            throw new IllegalArgumentException("Null parameter in function definition.");
        }
        if (definition == null) {
            throw new IllegalArgumentException("Null function definition.");
        }
        (super.params = new Variable[1])[0] = variable;
        this.definition = definition;
        super.arity = 1;
        this.setName(name);
    }
    
    public ExpressionFunction(final String name, final Variable variable, final Variable variable2, final Expression definition) {
        if (variable == null || variable2 == null) {
            throw new IllegalArgumentException("Null parameter in function definition.");
        }
        if (definition == null) {
            throw new IllegalArgumentException("Null function definition.");
        }
        (super.params = new Variable[2])[0] = variable;
        super.params[1] = variable2;
        this.definition = definition;
        super.arity = 2;
        this.setName(name);
    }
    
    public ExpressionFunction(final String name, final Variable[] params, final Expression definition) {
        if (params == null) {
            throw new IllegalArgumentException("Empty parameter list in function definition.");
        }
        super.arity = params.length;
        for (int i = 0; i < super.arity; ++i) {
            if (params[i] == null) {
                throw new IllegalArgumentException("Null parameter in function definition.");
            }
        }
        super.params = params;
        this.definition = definition;
        this.setName(name);
    }
    
    public double eval(final double value) {
        if (super.arity != 1) {
            throw new IllegalArgumentException("Wrong number of arguments for mathematical function.");
        }
        super.params[0].setValue(value);
        return this.definition.value();
    }
    
    public double eval(final double value, final double value2) {
        if (super.arity != 2) {
            throw new IllegalArgumentException("Wrong number of arguments for mathematical function.");
        }
        super.params[0].setValue(value);
        super.params[1].setValue(value2);
        return this.definition.value();
    }
    
    public double eval(final double[] array) {
        if (super.arity != array.length) {
            throw new IllegalArgumentException("Wrong number of arguments for mathematical function.");
        }
        for (int i = 0; i < super.arity; ++i) {
            super.params[i].setValue(array[i]);
        }
        return this.definition.value();
    }
    
    public Function derivative(final int n) {
        if (n <= 0 || n > super.arity) {
            if (super.arity == 1) {
                throw new IllegalArgumentException("Attempt to take partial derivative number " + n + " of a function of one variable.");
            }
            throw new IllegalArgumentException("Attempt to take partial derivative number " + n + " of a function of " + super.arity + " variables.");
        }
        else {
            if (super.arity == 1) {
                return new ExpressionFunction(String.valueOf(this.getName()) + "'", super.params, this.definition.derivative(super.params[n - 1]));
            }
            return new ExpressionFunction("D" + n + '[' + this.getName() + ']', super.params, this.definition.derivative(super.params[n - 1]));
        }
    }
    
    public void putDefinition(final StringBuffer sb) {
        this.definition.put(sb);
    }
    
    public boolean checkCases() {
        return this.definition.checkCases();
    }
    
    public boolean contains(final MathSymbol mathSymbol) {
        return this.definition.contains(mathSymbol);
    }
}
