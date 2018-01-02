// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.operands;

import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.IRModule;
import org.jruby.compiler.ir.IRClass;
import java.util.Map;
import java.util.Arrays;
import java.util.List;

public class Array extends Operand
{
    public final Operand[] elts;
    
    public Array() {
        this.elts = new Operand[0];
    }
    
    public Array(final List<Operand> elts) {
        this(elts.toArray(new Operand[elts.size()]));
    }
    
    public Array(final Operand[] elts) {
        this.elts = ((elts == null) ? new Operand[0] : elts);
    }
    
    public boolean isBlank() {
        return this.elts.length == 0;
    }
    
    public String toString() {
        return "Array:" + (this.isBlank() ? "" : Arrays.toString(this.elts));
    }
    
    public boolean isConstant() {
        for (final Operand o : this.elts) {
            if (!o.isConstant()) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isNonAtomicValue() {
        return true;
    }
    
    public Operand getSimplifiedOperand(final Map<Operand, Operand> valueMap) {
        for (int i = 0; i < this.elts.length; ++i) {
            this.elts[i] = this.elts[i].getSimplifiedOperand(valueMap);
        }
        return this;
    }
    
    public Operand fetchCompileTimeArrayElement(final int argIndex, final boolean getSubArray) {
        if (!getSubArray) {
            return (argIndex < this.elts.length) ? this.elts[argIndex] : Nil.NIL;
        }
        if (argIndex < this.elts.length) {
            final Operand[] newElts = new Operand[this.elts.length - argIndex];
            System.arraycopy(this.elts, argIndex, newElts, 0, newElts.length);
            return new Array(newElts);
        }
        return new Array();
    }
    
    public IRClass getTargetClass() {
        return IRModule.getCoreClass("Array");
    }
    
    public Operand toArray() {
        return this;
    }
    
    public void addUsedVariables(final List<Variable> l) {
        for (final Operand o : this.elts) {
            o.addUsedVariables(l);
        }
    }
    
    public Operand cloneForInlining(final InlinerInfo ii) {
        if (this.isConstant()) {
            return this;
        }
        final Operand[] newElts = new Operand[this.elts.length];
        for (int i = 0; i < this.elts.length; ++i) {
            newElts[i] = this.elts[i].cloneForInlining(ii);
        }
        return new Array(newElts);
    }
    
    public Object retrieve(final InterpreterContext interp) {
        final IRubyObject[] elements = new IRubyObject[this.elts.length];
        for (int i = 0; i < elements.length; ++i) {
            elements[i] = (IRubyObject)this.elts[i].retrieve(interp);
        }
        return interp.getRuntime().newArray(elements);
    }
}
