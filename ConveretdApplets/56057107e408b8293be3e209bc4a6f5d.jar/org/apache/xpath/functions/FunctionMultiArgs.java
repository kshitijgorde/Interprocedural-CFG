// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import org.apache.xpath.Expression;

public class FunctionMultiArgs extends Function3Args
{
    Expression[] m_args;
    
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
    
    public void checkNumberArgs(final int argNum) throws WrongNumberArgsException {
    }
    
    public void setArg(final Expression arg, final int argNum) throws WrongNumberArgsException {
        if (argNum < 3) {
            super.setArg(arg, argNum);
        }
        else if (this.m_args == null) {
            (this.m_args = new Expression[1])[0] = arg;
        }
        else {
            final Expression[] args = new Expression[this.m_args.length + 1];
            System.arraycopy(this.m_args, 0, args, 0, this.m_args.length);
            args[this.m_args.length] = arg;
            this.m_args = args;
        }
    }
}
