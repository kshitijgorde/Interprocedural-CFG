// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.operands;

import org.jruby.runtime.builtin.IRubyObject;

public abstract class Constant extends Operand
{
    protected IRubyObject cachedValue;
    
    public Constant() {
        this.cachedValue = null;
    }
    
    public boolean isConstant() {
        return true;
    }
}
