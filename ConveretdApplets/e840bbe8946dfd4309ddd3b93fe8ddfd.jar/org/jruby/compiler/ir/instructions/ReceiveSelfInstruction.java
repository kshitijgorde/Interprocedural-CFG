// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.compiler.ir.operands.Operand;
import org.jruby.compiler.ir.operands.Label;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Variable;

public class ReceiveSelfInstruction extends NoOperandInstr
{
    public ReceiveSelfInstruction(final Variable destination) {
        super(Operation.RECV_SELF, destination);
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return new CopyInstr(ii.getRenamedVariable(this.result), ii.getCallReceiver());
    }
    
    public String toString() {
        return super.toString();
    }
    
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        final Operand destination = this.getResult();
        destination.store(interp, self);
        return null;
    }
}
