// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.operands;

import org.jruby.compiler.ir.Interp;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import java.util.List;
import org.jruby.compiler.ir.IRClass;
import java.util.Map;

public abstract class Operand
{
    public static final Operand[] EMPTY_ARRAY;
    
    public boolean isConstant() {
        return false;
    }
    
    public boolean isNonAtomicValue() {
        return false;
    }
    
    public Operand getSimplifiedOperand(final Map<Operand, Operand> valueMap) {
        return this;
    }
    
    public Operand getValue(final Map<Operand, Operand> valueMap) {
        return this;
    }
    
    public Operand fetchCompileTimeArrayElement(final int index, final boolean getSubArray) {
        return null;
    }
    
    public IRClass getTargetClass() {
        return null;
    }
    
    public void addUsedVariables(final List<Variable> l) {
    }
    
    public Operand cloneForInlining(final InlinerInfo ii) {
        return this;
    }
    
    @Interp
    public Object retrieve(final InterpreterContext interp) {
        throw new RuntimeException(this.getClass().getSimpleName() + " should not be directly interpreted");
    }
    
    @Interp
    public Object store(final InterpreterContext interp, final Object value) {
        throw new RuntimeException(this.getClass().getSimpleName() + " should not be directly interpreted");
    }
    
    static {
        EMPTY_ARRAY = new Operand[0];
    }
}
