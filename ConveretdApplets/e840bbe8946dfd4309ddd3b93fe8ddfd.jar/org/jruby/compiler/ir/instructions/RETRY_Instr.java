// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.operands.Operand;
import org.jruby.compiler.ir.operands.Variable;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Label;

public class RETRY_Instr extends OneOperandInstr
{
    Label _jumpLabel;
    
    public RETRY_Instr(final Label loopStart) {
        super(Operation.RETRY, null, null);
        this._jumpLabel = loopStart;
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return new RETRY_Instr(ii.getRenamedLabel(this._jumpLabel));
    }
}
