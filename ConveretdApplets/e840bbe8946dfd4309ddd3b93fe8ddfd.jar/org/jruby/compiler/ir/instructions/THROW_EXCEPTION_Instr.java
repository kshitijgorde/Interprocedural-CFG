// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.exceptions.RaiseException;
import org.jruby.RubyException;
import org.jruby.compiler.ir.operands.Label;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.operands.Variable;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Operand;

public class THROW_EXCEPTION_Instr extends OneOperandInstr
{
    public THROW_EXCEPTION_Instr(final Operand exc) {
        super(Operation.THROW, null, exc);
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return new THROW_EXCEPTION_Instr(this.getArg().cloneForInlining(ii));
    }
    
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        throw new RaiseException((RubyException)this.getArg().retrieve(interp));
    }
}
