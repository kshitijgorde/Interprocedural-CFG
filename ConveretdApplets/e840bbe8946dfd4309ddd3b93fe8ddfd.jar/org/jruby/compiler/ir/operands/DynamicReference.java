// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.operands;

import org.jruby.compiler.ir.representations.InlinerInfo;
import java.util.List;
import java.util.Map;

public class DynamicReference extends Operand
{
    CompoundString _refName;
    
    public DynamicReference(final CompoundString n) {
        this._refName = n;
    }
    
    public boolean isNonAtomicValue() {
        return true;
    }
    
    public Operand getSimplifiedOperand(final Map<Operand, Operand> valueMap) {
        this._refName = (CompoundString)this._refName.getSimplifiedOperand(valueMap);
        return this;
    }
    
    public void addUsedVariables(final List<Variable> l) {
        this._refName.addUsedVariables(l);
    }
    
    public Operand cloneForInlining(final InlinerInfo ii) {
        return this._refName.cloneForInlining(ii);
    }
}
