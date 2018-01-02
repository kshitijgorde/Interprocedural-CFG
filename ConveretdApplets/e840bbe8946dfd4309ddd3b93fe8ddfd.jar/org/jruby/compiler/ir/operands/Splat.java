// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.operands;

import org.jruby.compiler.ir.representations.InlinerInfo;
import java.util.List;
import java.util.Map;

public class Splat extends Operand
{
    Operand _array;
    
    public Splat(final Operand a) {
        this._array = a;
    }
    
    public boolean isConstant() {
        return this._array.isConstant();
    }
    
    public String toString() {
        return "*" + this._array;
    }
    
    public boolean isNonAtomicValue() {
        return true;
    }
    
    public Operand getSimplifiedOperand(final Map<Operand, Operand> valueMap) {
        this._array = this._array.getSimplifiedOperand(valueMap);
        if (this._array instanceof Variable) {
            this._array = ((Variable)this._array).getValue(valueMap);
        }
        return this;
    }
    
    public Operand fetchCompileTimeArrayElement(final int argIndex, final boolean getSubArray) {
        if (this._array instanceof Array) {
            return ((Array)this._array).fetchCompileTimeArrayElement(argIndex, getSubArray);
        }
        if (this._array instanceof Range) {
            return ((Range)this._array).fetchCompileTimeArrayElement(argIndex, getSubArray);
        }
        return null;
    }
    
    public void addUsedVariables(final List<Variable> l) {
        this._array.addUsedVariables(l);
    }
    
    public Operand cloneForInlining(final InlinerInfo ii) {
        return this.isConstant() ? this : new Splat(this._array.cloneForInlining(ii));
    }
}
