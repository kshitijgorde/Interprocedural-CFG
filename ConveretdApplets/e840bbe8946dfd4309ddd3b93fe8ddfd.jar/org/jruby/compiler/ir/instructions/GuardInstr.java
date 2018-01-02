// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.Operation;

public abstract class GuardInstr extends NoOperandInstr
{
    public GuardInstr(final Operation op) {
        super(op);
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return this;
    }
}
