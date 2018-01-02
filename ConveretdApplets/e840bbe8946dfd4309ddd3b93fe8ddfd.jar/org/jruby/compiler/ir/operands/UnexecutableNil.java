// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.operands;

import org.jruby.interpreter.InterpreterContext;

public class UnexecutableNil extends Nil
{
    public static final UnexecutableNil U_NIL;
    
    public String toString() {
        return "nil(unexecutable)";
    }
    
    public Operand fetchCompileTimeArrayElement(final int argIndex, final boolean getSubArray) {
        return UnexecutableNil.U_NIL;
    }
    
    public Object retrieve(final InterpreterContext interp) {
        throw new RuntimeException(this.getClass().getSimpleName() + " should not be directly interpreted");
    }
    
    static {
        U_NIL = new UnexecutableNil();
    }
}
