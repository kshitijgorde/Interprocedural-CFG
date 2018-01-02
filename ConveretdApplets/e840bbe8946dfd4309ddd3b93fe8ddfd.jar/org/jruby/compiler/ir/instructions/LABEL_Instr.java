// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Label;

public class LABEL_Instr extends NoOperandInstr
{
    public final Label _lbl;
    
    public LABEL_Instr(final Label l) {
        super(Operation.LABEL);
        this._lbl = l;
    }
    
    public String toString() {
        return this._lbl + ":";
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return new LABEL_Instr(ii.getRenamedLabel(this._lbl));
    }
}
