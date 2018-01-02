// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.compiler.ir.operands.Nil;
import org.jruby.compiler.ir.operands.Label;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Variable;

public class ReceiveOptionalArgumentInstr extends NoOperandInstr
{
    int argIndex;
    
    public ReceiveOptionalArgumentInstr(final Variable dest, final int index) {
        super(Operation.RECV_OPT_ARG, dest);
        this.argIndex = index;
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return new ReceiveOptionalArgumentInstr(ii.getRenamedVariable(this.result), this.argIndex);
    }
    
    public String toString() {
        return super.toString() + "(" + this.argIndex + ")";
    }
    
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        final Object v = (interp.getParameterCount() > this.argIndex) ? interp.getParameter(this.argIndex) : Nil.NIL.retrieve(interp);
        this.getResult().store(interp, v);
        return null;
    }
}
