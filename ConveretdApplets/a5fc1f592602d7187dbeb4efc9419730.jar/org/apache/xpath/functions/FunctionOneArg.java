// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import org.apache.xpath.XPathVisitor;
import java.util.Vector;
import org.apache.xpath.res.XPATHMessages;
import org.apache.xpath.ExpressionNode;
import org.apache.xpath.Expression;
import org.apache.xpath.ExpressionOwner;

public class FunctionOneArg extends Function implements ExpressionOwner
{
    Expression m_arg0;
    
    public Expression getArg0() {
        return this.m_arg0;
    }
    
    public void setArg(final Expression arg, final int argNum) throws WrongNumberArgsException {
        if (0 == argNum) {
            (this.m_arg0 = arg).exprSetParent(this);
        }
        else {
            this.reportWrongNumberArgs();
        }
    }
    
    public void checkNumberArgs(final int argNum) throws WrongNumberArgsException {
        if (argNum != 1) {
            this.reportWrongNumberArgs();
        }
    }
    
    protected void reportWrongNumberArgs() throws WrongNumberArgsException {
        throw new WrongNumberArgsException(XPATHMessages.createXPATHMessage("one", null));
    }
    
    public boolean canTraverseOutsideSubtree() {
        return this.m_arg0.canTraverseOutsideSubtree();
    }
    
    public void fixupVariables(final Vector vars, final int globalsSize) {
        if (null != this.m_arg0) {
            this.m_arg0.fixupVariables(vars, globalsSize);
        }
    }
    
    public void callArgVisitors(final XPathVisitor visitor) {
        if (null != this.m_arg0) {
            this.m_arg0.callVisitors(this, visitor);
        }
    }
    
    public Expression getExpression() {
        return this.m_arg0;
    }
    
    public void setExpression(final Expression exp) {
        exp.exprSetParent(this);
        this.m_arg0 = exp;
    }
    
    public boolean deepEquals(final Expression expr) {
        if (!super.deepEquals(expr)) {
            return false;
        }
        if (null != this.m_arg0) {
            if (null == ((FunctionOneArg)expr).m_arg0) {
                return false;
            }
            if (!this.m_arg0.deepEquals(((FunctionOneArg)expr).m_arg0)) {
                return false;
            }
        }
        else if (null != ((FunctionOneArg)expr).m_arg0) {
            return false;
        }
        return true;
    }
}
