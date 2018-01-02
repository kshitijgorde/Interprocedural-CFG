// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.operands;

import org.jruby.RubyRange;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import java.util.List;
import java.util.Map;
import org.jruby.compiler.ir.IRModule;
import org.jruby.compiler.ir.IRClass;

public class Range extends Operand
{
    Operand begin;
    Operand end;
    boolean exclusive;
    
    public Range(final Operand begin, final Operand end, final boolean exclusive) {
        this.begin = begin;
        this.end = end;
        this.exclusive = exclusive;
    }
    
    public String toString() {
        return "(" + this.begin + (this.exclusive ? ".." : "...") + this.end + "):Range";
    }
    
    public boolean isConstant() {
        return this.begin.isConstant() && this.end.isConstant();
    }
    
    public Operand fetchCompileTimeArrayElement(final int argIndex, final boolean getSubArray) {
        if (!this.isConstant()) {
            return null;
        }
        return null;
    }
    
    public IRClass getTargetClass() {
        return IRModule.getCoreClass("Range");
    }
    
    public boolean isNonAtomicValue() {
        return true;
    }
    
    public Operand getSimplifiedOperand(final Map<Operand, Operand> valueMap) {
        this.begin = this.begin.getSimplifiedOperand(valueMap);
        this.end = this.end.getSimplifiedOperand(valueMap);
        return this;
    }
    
    public void addUsedVariables(final List<Variable> l) {
        this.begin.addUsedVariables(l);
        this.end.addUsedVariables(l);
    }
    
    public Operand cloneForInlining(final InlinerInfo ii) {
        return this.isConstant() ? this : new Range(this.begin.cloneForInlining(ii), this.end.cloneForInlining(ii), this.exclusive);
    }
    
    public Object retrieve(final InterpreterContext interp) {
        return RubyRange.newRange(interp.getRuntime(), interp.getContext(), (IRubyObject)this.begin.retrieve(interp), (IRubyObject)this.end.retrieve(interp), this.exclusive);
    }
}
