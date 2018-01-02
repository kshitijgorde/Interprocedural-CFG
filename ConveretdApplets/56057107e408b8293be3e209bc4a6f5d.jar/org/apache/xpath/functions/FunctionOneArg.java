// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.xpath.functions;

import org.apache.xpath.Expression;

public class FunctionOneArg extends Function
{
    Expression m_arg0;
    
    public boolean canTraverseOutsideSubtree() {
        return this.m_arg0.canTraverseOutsideSubtree();
    }
    
    public void checkNumberArgs(final int argNum) throws WrongNumberArgsException {
        if (argNum != 1) {
            throw new WrongNumberArgsException("1");
        }
    }
    
    public Expression getArg0() {
        return this.m_arg0;
    }
    
    public void setArg(final Expression arg, final int argNum) throws WrongNumberArgsException {
        if (argNum == 0) {
            this.m_arg0 = arg;
            return;
        }
        throw new WrongNumberArgsException("1");
    }
}
