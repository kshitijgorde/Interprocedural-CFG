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

public class Function2Args extends FunctionOneArg
{
    Expression m_arg1;
    
    public Expression getArg1() {
        return this.m_arg1;
    }
    
    public void fixupVariables(final Vector vars, final int globalsSize) {
        super.fixupVariables(vars, globalsSize);
        if (null != this.m_arg1) {
            this.m_arg1.fixupVariables(vars, globalsSize);
        }
    }
    
    public void setArg(final Expression arg, final int argNum) throws WrongNumberArgsException {
        if (argNum == 0) {
            super.setArg(arg, argNum);
        }
        else if (1 == argNum) {
            (this.m_arg1 = arg).exprSetParent(this);
        }
        else {
            this.reportWrongNumberArgs();
        }
    }
    
    public void checkNumberArgs(final int argNum) throws WrongNumberArgsException {
        if (argNum != 2) {
            this.reportWrongNumberArgs();
        }
    }
    
    protected void reportWrongNumberArgs() throws WrongNumberArgsException {
        throw new WrongNumberArgsException(XPATHMessages.createXPATHMessage("two", null));
    }
    
    public boolean canTraverseOutsideSubtree() {
        return super.canTraverseOutsideSubtree() || this.m_arg1.canTraverseOutsideSubtree();
    }
    
    public void callArgVisitors(final XPathVisitor visitor) {
        super.callArgVisitors(visitor);
        if (null != this.m_arg1) {
            this.m_arg1.callVisitors(new Arg1Owner(), visitor);
        }
    }
    
    public boolean deepEquals(final Expression expr) {
        if (!super.deepEquals(expr)) {
            return false;
        }
        if (null != this.m_arg1) {
            if (null == ((Function2Args)expr).m_arg1) {
                return false;
            }
            if (!this.m_arg1.deepEquals(((Function2Args)expr).m_arg1)) {
                return false;
            }
        }
        else if (null != ((Function2Args)expr).m_arg1) {
            return false;
        }
        return true;
    }
    
    class Arg1Owner implements ExpressionOwner
    {
        public Expression getExpression() {
            return Function2Args.this.m_arg1;
        }
        
        public void setExpression(final Expression exp) {
            exp.exprSetParent(Function2Args.this);
            Function2Args.this.m_arg1 = exp;
        }
    }
}
