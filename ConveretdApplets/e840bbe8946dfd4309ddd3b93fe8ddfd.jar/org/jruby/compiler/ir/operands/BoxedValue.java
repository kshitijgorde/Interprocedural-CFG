// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.operands;

import org.jruby.compiler.ir.representations.InlinerInfo;
import java.util.List;
import java.util.Map;

public class BoxedValue extends Operand
{
    public final Operand _value;
    
    public BoxedValue(final Operand v) {
        this._value = v;
    }
    
    public String toString() {
        return "boxed(" + this._value + ")";
    }
    
    public boolean isConstant() {
        return this._value.isConstant();
    }
    
    public boolean isNonAtomicValue() {
        return this._value.isNonAtomicValue();
    }
    
    public Operand getSimplifiedOperand(final Map<Operand, Operand> valueMap) {
        final Operand v = this._value.getSimplifiedOperand(valueMap);
        return (v == this._value) ? this : ((v instanceof UnboxedValue) ? ((UnboxedValue)v)._value : new BoxedValue(v));
    }
    
    public void addUsedVariables(final List<Variable> l) {
        this._value.addUsedVariables(l);
    }
    
    public Operand cloneForInlining(final InlinerInfo ii) {
        return new BoxedValue(this._value.cloneForInlining(ii));
    }
}
