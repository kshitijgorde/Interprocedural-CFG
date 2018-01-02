// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.operands;

import org.jruby.interpreter.InterpreterContext;

public class BooleanLiteral extends Constant
{
    public static final BooleanLiteral TRUE;
    public static final BooleanLiteral FALSE;
    
    public boolean isTrue() {
        return this == BooleanLiteral.TRUE;
    }
    
    public boolean isFalse() {
        return this == BooleanLiteral.FALSE;
    }
    
    public BooleanLiteral logicalNot() {
        return this.isTrue() ? BooleanLiteral.FALSE : BooleanLiteral.TRUE;
    }
    
    public String toString() {
        return this.isTrue() ? "true" : "false";
    }
    
    public Object retrieve(final InterpreterContext interp) {
        if (this.cachedValue == null) {
            this.cachedValue = interp.getRuntime().newBoolean(this.isTrue());
        }
        return this.cachedValue;
    }
    
    static {
        TRUE = new BooleanLiteral();
        FALSE = new BooleanLiteral();
    }
}
