// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.operands;

import org.jruby.interpreter.InterpreterContext;

public class ArgIndex extends Operand
{
    public final int index;
    
    public ArgIndex(final int index) {
        this.index = index;
    }
    
    public int getIndex() {
        return this.index;
    }
    
    public String toString() {
        return Integer.toString(this.index);
    }
    
    public Object retrieve(final InterpreterContext interp) {
        assert false : "Should not retreive ArgIndex as operand";
        return null;
    }
}
