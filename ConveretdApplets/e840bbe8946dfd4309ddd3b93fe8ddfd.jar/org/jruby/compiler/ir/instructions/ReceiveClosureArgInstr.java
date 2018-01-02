// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.compiler.ir.Interp;
import org.jruby.compiler.ir.operands.Label;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Variable;

public class ReceiveClosureArgInstr extends NoOperandInstr
{
    public final int argIndex;
    public final boolean restOfArgArray;
    
    public ReceiveClosureArgInstr(final Variable dest, final int argIndex, final boolean restOfArgArray) {
        super(Operation.RECV_CLOSURE_ARG, dest);
        this.argIndex = argIndex;
        this.restOfArgArray = restOfArgArray;
    }
    
    public String toString() {
        return super.toString() + "(" + this.argIndex + (this.restOfArgArray ? ", ALL" : "") + ")";
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        throw new RuntimeException("Not implemented yet!");
    }
    
    @Interp
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        this.getResult().store(interp, interp.getParameter(this.argIndex));
        return null;
    }
}
