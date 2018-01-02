// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import org.apache.xpath.Expression;

public class Function3Args extends Function2Args
{
    Expression m_arg2;
    
    public boolean canTraverseOutsideSubtree() {
        return super.canTraverseOutsideSubtree() || this.m_arg2.canTraverseOutsideSubtree();
    }
    
    public void checkNumberArgs(final int argNum) throws WrongNumberArgsException {
        if (argNum != 3) {
            throw new WrongNumberArgsException("3");
        }
    }
    
    public Expression getArg2() {
        return this.m_arg2;
    }
    
    public void setArg(final Expression arg, final int argNum) throws WrongNumberArgsException {
        if (argNum < 2) {
            super.setArg(arg, argNum);
        }
        else {
            if (argNum != 2) {
                throw new WrongNumberArgsException("3");
            }
            this.m_arg2 = arg;
        }
    }
}
