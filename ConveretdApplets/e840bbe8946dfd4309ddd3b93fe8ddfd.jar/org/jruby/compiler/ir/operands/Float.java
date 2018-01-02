// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.operands;

import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.IRModule;
import org.jruby.compiler.ir.IRClass;

public class Float extends Constant
{
    public final Double value;
    
    public Float(final Double val) {
        this.value = val;
    }
    
    public String toString() {
        return this.value + ":float";
    }
    
    public Operand fetchCompileTimeArrayElement(final int argIndex, final boolean getSubArray) {
        return (argIndex == 0) ? this : Nil.NIL;
    }
    
    public IRClass getTargetClass() {
        return IRModule.getCoreClass("Float");
    }
    
    public Constant computeValue(final String methodName, final Constant arg) {
        final Double v1 = this.value;
        final Double v2 = (arg instanceof Fixnum) ? (1.0 * ((Fixnum)arg).value) : ((Float)arg).value;
        if (methodName.equals("+")) {
            return new Float(v1 + v2);
        }
        if (methodName.equals("-")) {
            return new Float(v1 - v2);
        }
        if (methodName.equals("*")) {
            return new Float(v1 * v2);
        }
        if (methodName.equals("/")) {
            return (v2 == 0.0) ? null : new Float(v1 / v2);
        }
        return null;
    }
    
    public Object retrieve(final InterpreterContext interp) {
        if (this.cachedValue == null) {
            this.cachedValue = interp.getRuntime().newFloat(this.value);
        }
        return this.cachedValue;
    }
}
