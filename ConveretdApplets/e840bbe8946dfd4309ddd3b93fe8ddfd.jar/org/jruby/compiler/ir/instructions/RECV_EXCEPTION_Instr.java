// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.compiler.ir.operands.Label;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Variable;

public class RECV_EXCEPTION_Instr extends NoOperandInstr
{
    public RECV_EXCEPTION_Instr(final Variable dest) {
        super(Operation.RECV_EXCEPTION, dest);
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return new RECV_EXCEPTION_Instr(ii.getRenamedVariable(this.result));
    }
    
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        this.getResult().store(interp, interp.getException());
        return null;
    }
}
