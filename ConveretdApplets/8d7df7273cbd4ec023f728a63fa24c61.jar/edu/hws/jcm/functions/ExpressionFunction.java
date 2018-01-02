// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.functions;

import edu.hws.jcm.data.StackOfDouble;
import edu.hws.jcm.data.Function;
import edu.hws.jcm.data.Cases;
import edu.hws.jcm.data.MathObject;
import edu.hws.jcm.data.Parser;
import edu.hws.jcm.data.Variable;
import edu.hws.jcm.data.Expression;

public class ExpressionFunction extends FunctionParserExtension
{
    private Expression definition;
    private Variable[] params;
    
    public ExpressionFunction(final String s, final String s2) {
        this(s, new String[] { "x" }, s2, null);
    }
    
    public ExpressionFunction(final String name, final String[] array, final String s, final Parser parser) {
        this.setName(name);
        if (array == null) {
            this.params = new Variable[0];
        }
        else {
            this.params = new Variable[array.length];
            for (int i = 0; i < array.length; ++i) {
                this.params[i] = new Variable(array[i]);
            }
        }
        this.redefine(s, parser);
        if (parser != null && name != null) {
            parser.add(this);
        }
    }
    
    public ExpressionFunction(final String name, final Variable[] array, final Expression definition) {
        this.setName(name);
        this.params = ((array == null) ? new Variable[0] : array);
        this.definition = definition;
    }
    
    private ExpressionFunction() {
    }
    
    public void redefine(final String s) {
        this.redefine(s, null);
    }
    
    public void redefine(final String s, Parser parser) {
        if (parser == null) {
            parser = new Parser();
        }
        else {
            parser = new Parser(parser);
        }
        for (int i = 0; i < this.params.length; ++i) {
            parser.add(this.params[i]);
        }
        this.definition = parser.parse(s);
    }
    
    public String getDefinitionString() {
        return this.definition.toString();
    }
    
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append((super.name == null) ? "unnamed function of (" : ("function " + super.name + "("));
        for (int i = 0; i < this.params.length; ++i) {
            sb.append(this.params[i].getName());
            if (i < this.params.length - 1) {
                sb.append(",");
            }
        }
        sb.append(") given by ");
        sb.append(this.definition.toString());
        return sb.toString();
    }
    
    public int getArity() {
        return this.params.length;
    }
    
    public double getVal(final double[] array) {
        return this.getValueWithCases(array, null);
    }
    
    public double getValueWithCases(final double[] array, final Cases cases) {
        synchronized (this.params) {
            if (array == null) {
                if (this.params.length > 0) {
                    throw new IllegalArgumentException("Internal Error:  Number of arguments provided to function does not match its arity.");
                }
            }
            else {
                if (array.length != this.params.length) {
                    throw new IllegalArgumentException("Internal Error:  Number of arguments provided to function does not match its arity.");
                }
                for (int i = 0; i < this.params.length; ++i) {
                    this.params[i].setVal(array[i]);
                }
            }
            // monitorexit(this.params)
            return this.definition.getValueWithCases(cases);
        }
    }
    
    public Function derivative(final int n) {
        if (n <= 0 || n > this.getArity()) {
            throw new IllegalArgumentException("Internal Error:  Attempt to take the derivative of a function of " + this.getArity() + " variables with respect to argument number " + n + ".");
        }
        final ExpressionFunction expressionFunction = new ExpressionFunction();
        if (super.name != null) {
            if (this.getArity() == 1) {
                expressionFunction.setName(String.valueOf(this.getName()) + "'");
            }
            else {
                expressionFunction.setName("D" + n + "[" + this.getName() + "]");
            }
        }
        expressionFunction.params = this.params;
        expressionFunction.definition = this.definition.derivative(this.params[n - 1]);
        return expressionFunction;
    }
    
    public Function derivative(final Variable variable) {
        final ExpressionFunction expressionFunction = new ExpressionFunction();
        if (super.name != null) {
            expressionFunction.setName("D" + variable.getName() + "[" + this.getName() + "]");
        }
        expressionFunction.params = this.params;
        expressionFunction.definition = this.definition.derivative(variable);
        return expressionFunction;
    }
    
    public boolean dependsOn(final Variable variable) {
        return this.definition.dependsOn(variable);
    }
    
    public void apply(final StackOfDouble stackOfDouble, final Cases cases) {
        for (int i = this.getArity() - 1; i >= 0; --i) {
            this.params[i].setVal(stackOfDouble.pop());
        }
        stackOfDouble.push(this.definition.getValueWithCases(cases));
    }
}
