// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.operands.Operand;
import org.jruby.compiler.ir.operands.Variable;
import org.jruby.compiler.ir.Operation;

public class ALU_Instr extends TwoOperandInstr
{
    public ALU_Instr(final Operation op, final Variable dst, final Operand arg1, final Operand arg2) {
        super(op, dst, arg1, arg2);
    }
    
    public ALU_Instr(final Operation op, final Variable dst, final Operand arg) {
        super(op, dst, arg, null);
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return new ALU_Instr(this.operation, ii.getRenamedVariable(this.result), this.operand1.cloneForInlining(ii), (this.operand2 != null) ? this.operand2.cloneForInlining(ii) : null);
    }
}
