// 
// Decompiled by Procyon v0.5.30
// 

package org.xmodel.xpath.function;

import org.xmodel.xpath.XPath;
import java.util.regex.PatternSyntaxException;
import org.xmodel.xpath.expression.ExpressionException;
import org.xmodel.xpath.expression.IContext;
import org.xmodel.xpath.expression.IExpression;
import java.util.regex.Pattern;

public class ReplaceFunction extends Function
{
    Pattern £;
    boolean ¤;
    
    public ReplaceFunction() {
        this.¤ = false;
    }
    
    @Override
    public String getName() {
        return "replace";
    }
    
    @Override
    public IExpression.ResultType getType() {
        return IExpression.ResultType.STRING;
    }
    
    @Override
    public String evaluateString(final IContext context) throws ExpressionException {
        this.assertArgs(3, 4);
        this.assertType(context, IExpression.ResultType.STRING);
        final Pattern g = this.G(context);
        if (g == null) {
            return "";
        }
        return g.matcher(this.getArgument(0).evaluateString(context)).replaceAll(this.getArgument(2).evaluateString(context));
    }
    
    private Pattern G(final IContext context) throws ExpressionException {
        try {
            if (!this.¤) {
                this.£ = Pattern.compile(this.getArgument(1).evaluateString(context), this.F(context));
                this.¤ = true;
            }
            return this.£;
        }
        catch (PatternSyntaxException ex) {
            throw new ExpressionException(this, "Invalid regular expression.", ex);
        }
    }
    
    private int F(final IContext context) throws ExpressionException {
        final IExpression argument = this.getArgument(3);
        if (argument == null) {
            return 0;
        }
        int n = 0;
        final String evaluateString = argument.evaluateString(context);
        if (evaluateString.contains("s")) {
            n |= 0x20;
        }
        if (evaluateString.contains("m")) {
            n |= 0x8;
        }
        if (evaluateString.contains("i")) {
            n |= 0x2;
        }
        if (evaluateString.contains("x")) {
            n |= 0x4;
        }
        return n;
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context, final String s, final String s2) {
        if (expression != this.getArgument(0)) {
            this.¤ = false;
        }
        this.getParent().notifyChange(this, context);
    }
    
    @Override
    public void notifyChange(final IExpression expression, final IContext context) {
        if (expression != this.getArgument(0)) {
            this.¤ = false;
        }
        this.getParent().notifyChange(this, context);
    }
    
    public static void main(final String[] array) throws Exception {
        System.out.println("->" + XPath.createExpression("replace( 'abcabc', '(a)(.+)(a)', '$1$3')").evaluateString(null));
    }
}
