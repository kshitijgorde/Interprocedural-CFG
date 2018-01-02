// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.compiler.ir.operands.Label;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.operands.Variable;
import org.jruby.compiler.ir.Operation;

public class ThreadPollInstr extends NoOperandInstr
{
    public ThreadPollInstr() {
        super(Operation.THREAD_POLL, null);
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return this;
    }
    
    public boolean canRaiseException() {
        return false;
    }
    
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        interp.getContext().callThreadPoll();
        return null;
    }
}
