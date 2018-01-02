// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import org.apache.xpath.ExpressionOwner;
import org.apache.xpath.XPathVisitor;
import org.apache.xpath.res.XPATHMessages;
import org.apache.xpath.ExpressionNode;
import java.util.Vector;
import org.apache.xpath.Expression;

public class Function3Args extends Function2Args
{
    static final long serialVersionUID = 7915240747161506646L;
    Expression m_arg2;
    
    public Expression getArg2() {
        return this.m_arg2;
    }
    
    public void fixupVariables(final Vector vars, final int globalsSize) {
        super.fixupVariables(vars, globalsSize);
        if (null != this.m_arg2) {
            this.m_arg2.fixupVariables(vars, globalsSize);
        }
    }
    
    public void setArg(final Expression arg, final int argNum) throws WrongNumberArgsException {
        if (argNum < 2) {
            super.setArg(arg, argNum);
        }
        else if (2 == argNum) {
            (this.m_arg2 = arg).exprSetParent(this);
        }
        else {
            this.reportWrongNumberArgs();
        }
    }
    
    public void checkNumberArgs(final int argNum) throws WrongNumberArgsException {
        if (argNum != 3) {
            this.reportWrongNumberArgs();
        }
    }
    
    protected void reportWrongNumberArgs() throws WrongNumberArgsException {
        throw new WrongNumberArgsException(XPATHMessages.createXPATHMessage("three", null));
    }
    
    public boolean canTraverseOutsideSubtree() {
        return super.canTraverseOutsideSubtree() || this.m_arg2.canTraverseOutsideSubtree();
    }
    
    public void callArgVisitors(final XPathVisitor visitor) {
        super.callArgVisitors(visitor);
        if (null != this.m_arg2) {
            this.m_arg2.callVisitors(new Arg2Owner(), visitor);
        }
    }
    
    public boolean deepEquals(final Expression expr) {
        if (!super.deepEquals(expr)) {
            return false;
        }
        if (null != this.m_arg2) {
            if (null == ((Function3Args)expr).m_arg2) {
                return false;
            }
            if (!this.m_arg2.deepEquals(((Function3Args)expr).m_arg2)) {
                return false;
            }
        }
        else if (null != ((Function3Args)expr).m_arg2) {
            return false;
        }
        return true;
    }
    
    class Arg2Owner implements ExpressionOwner
    {
        public Expression getExpression() {
            return Function3Args.this.m_arg2;
        }
        
        public void setExpression(final Expression exp) {
            exp.exprSetParent(Function3Args.this);
            Function3Args.this.m_arg2 = exp;
        }
    }
}
