// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.compiler.ir.operands.Label;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.operands.Operand;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Variable;

public class JUMP_INDIRECT_Instr extends OneOperandInstr
{
    public JUMP_INDIRECT_Instr(final Variable tgt) {
        super(Operation.JUMP_INDIRECT, null, tgt);
    }
    
    public Variable getJumpTarget() {
        return (Variable)this.getArg();
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return new JUMP_INDIRECT_Instr(ii.getRenamedVariable(this.getJumpTarget()));
    }
    
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        return (Label)this.getArg().retrieve(interp);
    }
}
