// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import org.apache.xpath.ExpressionOwner;
import org.apache.xpath.XPathVisitor;
import org.apache.xpath.res.XPATHMessages;
import java.util.Vector;
import org.apache.xpath.ExpressionNode;
import org.apache.xpath.Expression;

public class FunctionMultiArgs extends Function3Args
{
    static final long serialVersionUID = 7117257746138417181L;
    Expression[] m_args;
    
    public Expression[] getArgs() {
        return this.m_args;
    }
    
    public void setArg(final Expression arg, final int argNum) throws WrongNumberArgsException {
        if (argNum < 3) {
            super.setArg(arg, argNum);
        }
        else {
            if (null == this.m_args) {
                (this.m_args = new Expression[1])[0] = arg;
            }
            else {
                final Expression[] args = new Expression[this.m_args.length + 1];
                System.arraycopy(this.m_args, 0, args, 0, this.m_args.length);
                args[this.m_args.length] = arg;
                this.m_args = args;
            }
            arg.exprSetParent(this);
        }
    }
    
    public void fixupVariables(final Vector vars, final int globalsSize) {
        super.fixupVariables(vars, globalsSize);
        if (null != this.m_args) {
            for (int i = 0; i < this.m_args.length; ++i) {
                this.m_args[i].fixupVariables(vars, globalsSize);
            }
        }
    }
    
    public void checkNumberArgs(final int argNum) throws WrongNumberArgsException {
    }
    
    protected void reportWrongNumberArgs() throws WrongNumberArgsException {
        final String fMsg = XPATHMessages.createXPATHMessage("ER_INCORRECT_PROGRAMMER_ASSERTION", new Object[] { "Programmer's assertion:  the method FunctionMultiArgs.reportWrongNumberArgs() should never be called." });
        throw new RuntimeException(fMsg);
    }
    
    public boolean canTraverseOutsideSubtree() {
        if (super.canTraverseOutsideSubtree()) {
            return true;
        }
        for (int n = this.m_args.length, i = 0; i < n; ++i) {
            if (this.m_args[i].canTraverseOutsideSubtree()) {
                return true;
            }
        }
        return false;
    }
    
    public void callArgVisitors(final XPathVisitor visitor) {
        super.callArgVisitors(visitor);
        if (null != this.m_args) {
            for (int n = this.m_args.length, i = 0; i < n; ++i) {
                this.m_args[i].callVisitors(new ArgMultiOwner(i), visitor);
            }
        }
    }
    
    public boolean deepEquals(final Expression expr) {
        if (!super.deepEquals(expr)) {
            return false;
        }
        final FunctionMultiArgs fma = (FunctionMultiArgs)expr;
        if (null != this.m_args) {
            final int n = this.m_args.length;
            if (null == fma || fma.m_args.length != n) {
                return false;
            }
            for (int i = 0; i < n; ++i) {
                if (!this.m_args[i].deepEquals(fma.m_args[i])) {
                    return false;
                }
            }
        }
        else if (null != fma.m_args) {
            return false;
        }
        return true;
    }
    
    class ArgMultiOwner implements ExpressionOwner
    {
        int m_argIndex;
        
        ArgMultiOwner(final int index) {
            this.m_argIndex = index;
        }
        
        public Expression getExpression() {
            return FunctionMultiArgs.this.m_args[this.m_argIndex];
        }
        
        public void setExpression(final Expression exp) {
            exp.exprSetParent(FunctionMultiArgs.this);
            FunctionMultiArgs.this.m_args[this.m_argIndex] = exp;
        }
    }
}
