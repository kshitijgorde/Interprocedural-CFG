// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.operands;

import org.jruby.compiler.ir.representations.InlinerInfo;
import java.util.List;
import java.util.Map;

public class SValue extends Operand
{
    Operand _array;
    
    public SValue(final Operand a) {
        this._array = a;
    }
    
    public boolean isConstant() {
        return this._array.isConstant();
    }
    
    public String toString() {
        return "SValue(" + this._array + ")";
    }
    
    public Operand getSimplifiedOperand(final Map<Operand, Operand> valueMap) {
        this._array = this._array.getSimplifiedOperand(valueMap);
        if (this._array instanceof Array) {
            final Array a = (Array)this._array;
            return (a.elts.length == 1) ? a.elts[0] : a;
        }
        return this;
    }
    
    public Operand fetchCompileTimeArrayElement(final int argIndex, final boolean getSubArray) {
        return null;
    }
    
    public boolean isNonAtomicValue() {
        return true;
    }
    
    public void addUsedVariables(final List<Variable> l) {
        this._array.addUsedVariables(l);
    }
    
    public Operand cloneForInlining(final InlinerInfo ii) {
        return this.isConstant() ? this : new SValue(this._array.cloneForInlining(ii));
    }
}
