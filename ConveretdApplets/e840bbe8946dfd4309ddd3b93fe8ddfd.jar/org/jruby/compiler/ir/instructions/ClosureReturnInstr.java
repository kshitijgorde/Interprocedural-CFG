// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.compiler.ir.Interp;
import org.jruby.compiler.ir.operands.Label;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.operands.Variable;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Operand;

public class ClosureReturnInstr extends OneOperandInstr
{
    public ClosureReturnInstr(final Operand rv) {
        super(Operation.CLOSURE_RETURN, null, rv);
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        throw new RuntimeException("Not implemented yet!");
    }
    
    @Interp
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        interp.setReturnValue(this.getArg().retrieve(interp));
        return interp.getMethodExitLabel();
    }
}
