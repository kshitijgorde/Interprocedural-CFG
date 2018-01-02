// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.compiler.ir.operands.Label;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import java.util.Map;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Operand;
import org.jruby.compiler.ir.operands.Variable;

public class CopyInstr extends OneOperandInstr
{
    public CopyInstr(final Variable d, final Operand s) {
        super(Operation.COPY, d, s);
    }
    
    public Operand simplifyAndGetResult(final Map<Operand, Operand> valueMap) {
        this.simplifyOperands(valueMap);
        return this.argument;
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return new CopyInstr(ii.getRenamedVariable(this.result), this.argument.cloneForInlining(ii));
    }
    
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        this.getResult().store(interp, this.getArg().retrieve(interp));
        return null;
    }
    
    public boolean canRaiseException() {
        return false;
    }
}
