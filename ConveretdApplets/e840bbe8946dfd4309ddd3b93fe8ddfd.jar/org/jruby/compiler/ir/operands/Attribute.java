// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.operands;

import org.jruby.compiler.ir.representations.InlinerInfo;
import java.util.Map;

public class Attribute extends Operand
{
    Operand _target;
    
    public Operand getSimplifiedOperand(final Map<Operand, Operand> valueMap) {
        throw new RuntimeException("Unused & not implemented yet!");
    }
    
    public boolean isNonAtomicValue() {
        return true;
    }
    
    public Operand cloneForInlining(final InlinerInfo ii) {
        throw new RuntimeException("Unused & not implemented yet!");
    }
}
