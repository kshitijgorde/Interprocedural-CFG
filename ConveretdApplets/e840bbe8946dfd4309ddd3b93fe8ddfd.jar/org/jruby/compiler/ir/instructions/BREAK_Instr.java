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

public class BREAK_Instr extends OneOperandInstr
{
    public BREAK_Instr(final Operand rv) {
        super(Operation.BREAK, null, rv);
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return new BREAK_Instr(this.argument.cloneForInlining(ii));
    }
    
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        interp.setReturnValue(this.getArg().retrieve(interp));
        return interp.getMethodExitLabel();
    }
}
