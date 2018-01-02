// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.operands;

import org.jruby.compiler.ir.representations.InlinerInfo;
import java.util.List;
import java.util.Map;

public abstract class Variable extends Operand implements Comparable
{
    public abstract String getName();
    
    public Operand getSimplifiedOperand(final Map<Operand, Operand> valueMap) {
        final Operand v = valueMap.get(this);
        return (v == null || v.isNonAtomicValue()) ? this : v;
    }
    
    public Operand getValue(final Map<Operand, Operand> valueMap) {
        final Operand v = valueMap.get(this);
        return (v == null) ? this : v;
    }
    
    public void addUsedVariables(final List<Variable> l) {
        l.add(this);
    }
    
    public Operand cloneForInlining(final InlinerInfo ii) {
        return ii.getRenamedVariable(this);
    }
}
