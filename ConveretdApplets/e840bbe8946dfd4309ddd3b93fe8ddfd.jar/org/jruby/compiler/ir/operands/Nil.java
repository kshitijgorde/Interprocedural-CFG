// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.operands;

import org.jruby.interpreter.InterpreterContext;

public class Nil extends Constant
{
    public static final Nil NIL;
    
    public String toString() {
        return "nil";
    }
    
    public Operand fetchCompileTimeArrayElement(final int argIndex, final boolean getSubArray) {
        return Nil.NIL;
    }
    
    public Object retrieve(final InterpreterContext interp) {
        if (this.cachedValue == null) {
            this.cachedValue = interp.getRuntime().getNil();
        }
        return this.cachedValue;
    }
    
    static {
        NIL = new Nil();
    }
}
