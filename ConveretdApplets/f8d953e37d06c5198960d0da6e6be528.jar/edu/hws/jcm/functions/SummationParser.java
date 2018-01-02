// 
// Decompiled by Procyon v0.5.30
// 

package edu.hws.jcm.functions;

import edu.hws.jcm.data.Cases;
import edu.hws.jcm.data.StackOfDouble;
import edu.hws.jcm.data.ExpressionCommand;
import edu.hws.jcm.data.ExpressionProgram;
import edu.hws.jcm.data.MathObject;
import edu.hws.jcm.data.Variable;
import edu.hws.jcm.data.ParseError;
import edu.hws.jcm.data.ParserContext;
import edu.hws.jcm.data.Parser;
import edu.hws.jcm.data.ParserExtension;

public class SummationParser implements ParserExtension
{
    private String name;
    
    public SummationParser() {
        this.name = "sum";
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
        if (next != 4 || (!tokenString.equals("(") && (tokenString.equals("[") || (parserContext.options & 0x8) == 0x0) && (!tokenString.equals("{") || (parserContext.options & 0x10) == 0x0))) {
            throw new ParseError("Parentheses required around parameters of summation.", parserContext);
        }
        final String s = tokenString.equals("(") ? ")" : (tokenString.equals("[") ? "]" : "}");
        if (parserContext.next() != 3) {
            throw new ParseError("Expected the summation variable as the first argument of " + this.name + ".", parserContext);
        }
        final String tokenString2 = parserContext.tokenString;
        if (parserContext.next() != 4 || !parserContext.tokenString.equals(",")) {
            throw new ParseError("Exprected a comma after the index variable, " + tokenString2 + ".", parserContext);
        }
        parser.parseExpression(parserContext);
        if (parserContext.next() != 4 || !parserContext.tokenString.equals(",")) {
            throw new ParseError("Exprected a comma after the lower limit expression for " + this.name + ".", parserContext);
        }
        parser.parseExpression(parserContext);
        if (parserContext.next() != 4 || !parserContext.tokenString.equals(",")) {
            throw new ParseError("Exprected a comma after the upper limit expression for " + this.name + ".", parserContext);
        }
        final Variable variable = new Variable(tokenString2);
        parserContext.mark();
        parserContext.add(variable);
        final ExpressionProgram prog = parserContext.prog;
        parserContext.prog = new ExpressionProgram();
        parser.parseExpression(parserContext);
        if (parserContext.next() != 4 || !parserContext.tokenString.equals(s)) {
            throw new ParseError("Expected a \"" + s + "\" at the end of the paramter list for " + this.name + ".", parserContext);
        }
        parserContext.revert();
        prog.addCommandObject(new Cmd(variable, parserContext.prog));
        parserContext.prog = prog;
    }
    
    private static class Cmd implements ExpressionCommand
    {
        private Variable sumVar;
        private ExpressionProgram sumExpr;
        
        Cmd(final Variable sumVar, final ExpressionProgram sumExpr) {
            this.sumVar = sumVar;
            this.sumExpr = sumExpr;
        }
        
        public void apply(final StackOfDouble stackOfDouble, final Cases cases) {
            final double n = Math.round(stackOfDouble.pop()) + 0.1;
            final double n2 = Math.round(stackOfDouble.pop());
            if ((Double.isNaN(n) && Double.isNaN(n2)) || n - n2 > 1000000.0) {
                stackOfDouble.push(Double.NaN);
            }
            double n3 = 0.0;
            for (double val = n2; val <= n; ++val) {
                this.sumVar.setVal(val);
                n3 += this.sumExpr.getVal();
            }
            stackOfDouble.push(n3);
        }
        
        public void compileDerivative(final ExpressionProgram expressionProgram, final int n, final ExpressionProgram expressionProgram2, final Variable variable) {
            if (!this.sumExpr.dependsOn(variable)) {
                expressionProgram2.addConstant(0.0);
            }
            else {
                expressionProgram.copyExpression(n - 1 - expressionProgram.extent(n - 1), expressionProgram2);
                expressionProgram.copyExpression(n - 1, expressionProgram2);
                expressionProgram2.addCommandObject(new Cmd(this.sumVar, (ExpressionProgram)this.sumExpr.derivative(variable)));
            }
        }
        
        public int extent(final ExpressionProgram expressionProgram, final int n) {
            final int extent = expressionProgram.extent(n - 1);
            return extent + expressionProgram.extent(n - 1 - extent) + 1;
        }
        
        public boolean dependsOn(final Variable variable) {
            return this.sumExpr.dependsOn(variable);
        }
        
        public void appendOutputString(final ExpressionProgram expressionProgram, final int n, final StringBuffer sb) {
            final int extent = expressionProgram.extent(n - 1);
            sb.append("sum(");
            sb.append(this.sumVar.getName());
            sb.append(", ");
            expressionProgram.appendOutputString(n - 1 - extent, sb);
            sb.append(", ");
            expressionProgram.appendOutputString(n - 1, sb);
            sb.append(", ");
            sb.append(this.sumExpr.toString());
            sb.append(")");
        }
    }
}
