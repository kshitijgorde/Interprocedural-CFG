// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.compiler.ir.operands.Label;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Operand;
import org.jruby.compiler.ir.operands.Variable;

public class EQQInstr extends TwoOperandInstr
{
    public EQQInstr(final Variable result, final Operand v1, final Operand v2) {
        super(Operation.EQQ, result, v1, v2);
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return new EQQInstr(ii.getRenamedVariable(this.result), this.operand1.cloneForInlining(ii), this.operand2.cloneForInlining(ii));
    }
    
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        final IRubyObject receiver = (IRubyObject)this.getOperand1().retrieve(interp);
        final IRubyObject value = (IRubyObject)this.getOperand2().retrieve(interp);
        this.getResult().store(interp, receiver.callMethod(interp.getContext(), "===", value));
        return null;
    }
}
