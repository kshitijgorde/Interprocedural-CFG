// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import org.apache.xpath.res.XPATHMessages;
import org.apache.xpath.ExpressionNode;
import org.apache.xpath.ExpressionOwner;
import org.apache.xpath.XPathVisitor;
import javax.xml.transform.TransformerException;
import org.apache.xpath.objects.XNull;
import org.apache.xpath.ExtensionsProvider;
import org.apache.xpath.objects.XObject;
import org.apache.xpath.XPathContext;
import org.apache.xpath.Expression;
import java.util.Vector;

public class FuncExtFunction extends Function
{
    String m_namespace;
    String m_extensionName;
    Object m_methodKey;
    Vector m_argVec;
    
    public void fixupVariables(final Vector vars, final int globalsSize) {
        if (null != this.m_argVec) {
            for (int nArgs = this.m_argVec.size(), i = 0; i < nArgs; ++i) {
                final Expression arg = this.m_argVec.elementAt(i);
                arg.fixupVariables(vars, globalsSize);
            }
        }
    }
    
    public String getNamespace() {
        return this.m_namespace;
    }
    
    public String getFunctionName() {
        return this.m_extensionName;
    }
    
    public Object getMethodKey() {
        return this.m_methodKey;
    }
    
    public Expression getArg(final int n) {
        if (n >= 0 && n < this.m_argVec.size()) {
            return this.m_argVec.elementAt(n);
        }
        return null;
    }
    
    public int getArgCount() {
        return this.m_argVec.size();
    }
    
    public FuncExtFunction(final String namespace, final String extensionName, final Object methodKey) {
        this.m_argVec = new Vector();
        this.m_namespace = namespace;
        this.m_extensionName = extensionName;
        this.m_methodKey = methodKey;
    }
    
    public XObject execute(final XPathContext xctxt) throws TransformerException {
        final Vector argVec = new Vector();
        for (int nArgs = this.m_argVec.size(), i = 0; i < nArgs; ++i) {
            final Expression arg = this.m_argVec.elementAt(i);
            final XObject xobj = arg.execute(xctxt);
            xobj.allowDetachToRelease(false);
            argVec.addElement(xobj);
        }
        final ExtensionsProvider extProvider = (ExtensionsProvider)xctxt.getOwnerObject();
        final Object val = extProvider.extFunction(this, argVec);
        XObject result;
        if (null != val) {
            result = XObject.create(val, xctxt);
        }
        else {
            result = new XNull();
        }
        return result;
    }
    
    public void setArg(final Expression arg, final int argNum) throws WrongNumberArgsException {
        this.m_argVec.addElement(arg);
    }
    
    public void checkNumberArgs(final int argNum) throws WrongNumberArgsException {
    }
    
    public void callArgVisitors(final XPathVisitor visitor) {
        for (int i = 0; i < this.m_argVec.size(); ++i) {
            final Expression exp = this.m_argVec.elementAt(i);
            exp.callVisitors(new ArgExtOwner(exp), visitor);
        }
    }
    
    public void exprSetParent(final ExpressionNode n) {
        super.exprSetParent(n);
        for (int nArgs = this.m_argVec.size(), i = 0; i < nArgs; ++i) {
            final Expression arg = this.m_argVec.elementAt(i);
            arg.exprSetParent(n);
        }
    }
    
    protected void reportWrongNumberArgs() throws WrongNumberArgsException {
        final String fMsg = XPATHMessages.createXPATHMessage("ER_INCORRECT_PROGRAMMER_ASSERTION", new Object[] { "Programmer's assertion:  the method FunctionMultiArgs.reportWrongNumberArgs() should never be called." });
        throw new RuntimeException(fMsg);
    }
    
    class ArgExtOwner implements ExpressionOwner
    {
        Expression m_exp;
        
        ArgExtOwner(final Expression exp) {
            this.m_exp = exp;
        }
        
        public Expression getExpression() {
            return this.m_exp;
        }
        
        public void setExpression(final Expression exp) {
            exp.exprSetParent(FuncExtFunction.this);
            this.m_exp = exp;
        }
    }
}
