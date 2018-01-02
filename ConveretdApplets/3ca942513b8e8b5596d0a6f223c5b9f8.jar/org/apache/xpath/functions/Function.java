// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import org.apache.xpath.compiler.Compiler;
import org.apache.xpath.ExpressionOwner;
import org.apache.xpath.XPathVisitor;
import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;
import org.apache.xpath.res.XPATHMessages;
import org.apache.xpath.Expression;

public abstract class Function extends Expression
{
    static final long serialVersionUID = 6927661240854599768L;
    
    public void setArg(final Expression arg, final int argNum) throws WrongNumberArgsException {
        this.reportWrongNumberArgs();
    }
    
    public void checkNumberArgs(final int argNum) throws WrongNumberArgsException {
        if (argNum != 0) {
            this.reportWrongNumberArgs();
        }
    }
    
    protected void reportWrongNumberArgs() throws WrongNumberArgsException {
        throw new WrongNumberArgsException(XPATHMessages.createXPATHMessage("zero", null));
    }
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        System.out.println("Error! Function.execute should not be called!");
        return null;
    }
    
    public void callArgVisitors(final XPathVisitor visitor) {
    }
    
    public void callVisitors(final ExpressionOwner owner, final XPathVisitor visitor) {
        if (visitor.visitFunction(owner, this)) {
            this.callArgVisitors(visitor);
        }
    }
    
    public boolean deepEquals(final Expression expr) {
        return this.isSameClass(expr);
    }
    
    public void postCompileStep(final Compiler compiler) {
    }
}
