// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.compiler.ir.Interp;
import org.jruby.runtime.Block;
import org.jruby.compiler.ir.operands.Label;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Variable;

public class ReceiveClosureInstr extends NoOperandInstr
{
    public ReceiveClosureInstr(final Variable dest) {
        super(Operation.RECV_CLOSURE, dest);
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return new CopyInstr(ii.getRenamedVariable(this.result), ii.getCallClosure());
    }
    
    @Interp
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        this.getResult().store(interp, interp.getRuntime().newProc(Block.Type.PROC, interp.getBlock()));
        return null;
    }
}
