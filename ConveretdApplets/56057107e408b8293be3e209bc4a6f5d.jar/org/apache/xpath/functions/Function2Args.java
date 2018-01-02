// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import org.apache.xpath.Expression;

public class Function2Args extends FunctionOneArg
{
    Expression m_arg1;
    
    public boolean canTraverseOutsideSubtree() {
        return super.canTraverseOutsideSubtree() || this.m_arg1.canTraverseOutsideSubtree();
    }
    
    public void checkNumberArgs(final int argNum) throws WrongNumberArgsException {
        if (argNum != 2) {
            throw new WrongNumberArgsException("2");
        }
    }
    
    public Expression getArg1() {
        return this.m_arg1;
    }
    
    public void setArg(final Expression arg, final int argNum) throws WrongNumberArgsException {
        if (argNum == 0) {
            super.setArg(arg, argNum);
        }
        else {
            if (argNum != 1) {
                throw new WrongNumberArgsException("2");
            }
            this.m_arg1 = arg;
        }
    }
}
