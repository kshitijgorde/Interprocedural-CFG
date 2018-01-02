// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.operands;

import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.IRModule;
import org.jruby.compiler.ir.IRClass;
import java.math.BigInteger;

public class Fixnum extends Constant
{
    public final Long value;
    
    public Fixnum(final Long val) {
        this.value = val;
    }
    
    public Fixnum(final BigInteger val) {
        this.value = val.longValue();
    }
    
    public String toString() {
        return this.value + ":fixnum";
    }
    
    public Operand fetchCompileTimeArrayElement(final int argIndex, final boolean getSubArray) {
        return (argIndex == 0) ? this : Nil.NIL;
    }
    
    public IRClass getTargetClass() {
        return IRModule.getCoreClass("Fixnum");
    }
    
    public Constant computeValue(final String methodName, final Constant arg) {
        if (arg instanceof Fixnum) {
            if (methodName.equals("+")) {
                return new Fixnum(Long.valueOf(this.value + ((Fixnum)arg).value));
            }
            if (methodName.equals("-")) {
                return new Fixnum(Long.valueOf(this.value - ((Fixnum)arg).value));
            }
            if (methodName.equals("*")) {
                return new Fixnum(Long.valueOf(this.value * ((Fixnum)arg).value));
            }
            if (methodName.equals("/")) {
                final Long divisor = ((Fixnum)arg).value;
                return (divisor == 0L) ? null : new Fixnum(Long.valueOf(this.value / divisor));
            }
        }
        else if (arg instanceof Float) {
            if (methodName.equals("+")) {
                return new Float(this.value + ((Float)arg).value);
            }
            if (methodName.equals("-")) {
                return new Float(this.value - ((Float)arg).value);
            }
            if (methodName.equals("*")) {
                return new Float(this.value * ((Float)arg).value);
            }
            if (methodName.equals("/")) {
                final Double divisor2 = ((Float)arg).value;
                return (divisor2 == 0.0) ? null : new Float(this.value / divisor2);
            }
        }
        return null;
    }
    
    public Object retrieve(final InterpreterContext interp) {
        if (this.cachedValue == null) {
            this.cachedValue = interp.getRuntime().newFixnum(this.value);
        }
        return this.cachedValue;
    }
}
