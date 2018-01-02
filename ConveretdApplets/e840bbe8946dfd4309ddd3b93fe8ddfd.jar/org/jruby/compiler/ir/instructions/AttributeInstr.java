// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.Operation;

public class AttributeInstr extends NoOperandInstr
{
    public AttributeInstr(final Operation op) {
        super(op);
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return this;
    }
}
