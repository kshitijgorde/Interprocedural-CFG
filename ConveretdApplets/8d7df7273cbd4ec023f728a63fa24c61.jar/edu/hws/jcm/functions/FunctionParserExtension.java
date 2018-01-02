// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.functions;

import edu.hws.jcm.data.Variable;
import edu.hws.jcm.data.ExpressionProgram;
import edu.hws.jcm.data.Cases;
import edu.hws.jcm.data.StackOfDouble;
import edu.hws.jcm.data.ParseError;
import edu.hws.jcm.data.ParserContext;
import edu.hws.jcm.data.Parser;
import edu.hws.jcm.data.ExpressionCommand;
import edu.hws.jcm.data.ParserExtension;
import edu.hws.jcm.data.Function;

public abstract class FunctionParserExtension implements Function, ParserExtension, ExpressionCommand
{
    protected String name;
    private boolean parensCanBeOptional;
    
    public void setParensCanBeOptional(final boolean parensCanBeOptional) {
        this.parensCanBeOptional = parensCanBeOptional;
    }
    
    public void setName(final String name) {
        this.name = name;
    }
    
    public String getName() {
        return this.name;
    }
    
    public void doParse(final Parser parser, final ParserContext parserContext) {
        final int next = parserContext.next();
        final String tokenString = parserContext.tokenString;
        if (next == 4 && (tokenString.equals("(") || (tokenString.equals("[") && (parserContext.options & 0x8) != 0x0) || (tokenString.equals("{") && (parserContext.options & 0x10) != 0x0))) {
            final String s = tokenString.equals("(") ? ")" : (tokenString.equals("[") ? "]" : "}");
            for (int i = 0; i < this.getArity(); ++i) {
                if (parser.parseExpression(parserContext)) {
                    throw new ParseError("An argument of a function cannot be a logical-valued expression.", parserContext);
                }
                final int next2 = parserContext.next();
                if (i == this.getArity() - 1) {
                    if (next2 == 4 && parserContext.tokenString.equals(",")) {
                        throw new ParseError("Too many parameters for function \"" + this.getName() + "\".", parserContext);
                    }
                    if (next2 != 4 || !parserContext.tokenString.equals(s)) {
                        throw new ParseError("Expected a \"" + s + "\" at the end of the paramter list for function \"" + this.getName() + "\".", parserContext);
                    }
                }
                else if (next2 != 4 || !parserContext.tokenString.equals(",")) {
                    throw new ParseError("Exprected a comma followed by another argument for function \"" + this.getName() + "\".", parserContext);
                }
            }
        }
        else {
            if (this.getArity() != 1 || (parserContext.options & 0x200) == 0x0 || !this.parensCanBeOptional) {
                throw new ParseError("Parentheses required around parameter list of function \"" + this.getName() + "\".", parserContext);
            }
            if (parser.parseTerm(parserContext)) {
                throw new ParseError("The argument of a function must be a numerical expression.", parserContext);
            }
        }
        parserContext.prog.addCommandObject(this);
    }
    
    public void apply(final StackOfDouble stackOfDouble, final Cases cases) {
        final double[] array = new double[this.getArity()];
        for (int i = this.getArity() - 1; i >= 0; --i) {
            array[i] = stackOfDouble.pop();
        }
        stackOfDouble.push(this.getVal(array));
    }
    
    public void compileDerivative(final ExpressionProgram expressionProgram, final int n, final ExpressionProgram expressionProgram2, final Variable variable) {
        final int[] array = new int[this.getArity()];
        int n2 = 1;
        for (int i = 0; i < this.getArity(); ++i) {
            array[this.getArity() - i - 1] = n - n2;
            if (i < this.getArity() - 1) {
                n2 += expressionProgram.extent(n - n2);
            }
        }
        int n3 = 0;
        if (this.dependsOn(variable)) {
            n3 = 1;
            for (int j = 0; j < array.length; ++j) {
                expressionProgram.copyExpression(array[j], expressionProgram2);
            }
            expressionProgram2.addCommandObject((ExpressionCommand)this.derivative(variable));
        }
        for (int k = 0; k < this.getArity(); ++k) {
            if (expressionProgram.dependsOn(array[k], variable)) {
                for (int l = 0; l < array.length; ++l) {
                    expressionProgram.copyExpression(array[l], expressionProgram2);
                }
                expressionProgram2.addCommandObject((ExpressionCommand)this.derivative(k + 1));
                expressionProgram.compileDerivative(array[k], expressionProgram2, variable);
                expressionProgram2.addCommand(-3);
                if (n3 != 0) {
                    expressionProgram2.addCommand(-1);
                }
                n3 = 1;
            }
        }
        if (n3 == 0) {
            expressionProgram.addConstant(0.0);
        }
    }
    
    public int extent(final ExpressionProgram expressionProgram, final int n) {
        int n2 = 1;
        for (int i = 0; i < this.getArity(); ++i) {
            n2 += expressionProgram.extent(n - n2);
        }
        return n2;
    }
    
    public void appendOutputString(final ExpressionProgram expressionProgram, final int n, final StringBuffer sb) {
        final int[] array = new int[this.getArity()];
        int n2 = 1;
        for (int i = 0; i < this.getArity(); ++i) {
            array[this.getArity() - i - 1] = n - n2;
            if (i < this.getArity() - 1) {
                n2 += expressionProgram.extent(n - n2);
            }
        }
        final String name = this.getName();
        sb.append((name == null) ? "(unnamed function)" : name);
        sb.append('(');
        for (int j = 0; j < this.getArity(); ++j) {
            expressionProgram.appendOutputString(array[j], sb);
            if (j < this.getArity() - 1) {
                sb.append(", ");
            }
        }
        sb.append(')');
    }
    
    public abstract Function derivative(final int p0);
    
    public abstract Function derivative(final Variable p0);
    
    public abstract int getArity();
    
    public abstract boolean dependsOn(final Variable p0);
    
    public abstract double getValueWithCases(final double[] p0, final Cases p1);
    
    public abstract double getVal(final double[] p0);
}
