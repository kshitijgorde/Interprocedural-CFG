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
import org.jruby.compiler.ir.operands.Operand;

public class ReturnInstr extends OneOperandInstr
{
    public ReturnInstr(final Operand returnValue) {
        super(Operation.RETURN, null, returnValue);
        assert returnValue != null : "RETURN must have argument operand";
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return new CopyInstr(ii.getCallResultVariable(), this.getArg().cloneForInlining(ii));
    }
    
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        interp.setReturnValue(this.getArg().retrieve(interp));
        return interp.getMethodExitLabel();
    }
}
