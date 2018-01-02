// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.compiler.ir.operands.Label;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.operands.GlobalVariable;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Operand;

public class PutGlobalVarInstr extends PutInstr
{
    public PutGlobalVarInstr(final String varName, final Operand value) {
        super(Operation.PUT_GLOBAL_VAR, new GlobalVariable(varName), null, value);
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return new PutGlobalVarInstr(((GlobalVariable)this.operands[1]).name, this.operands[0].cloneForInlining(ii));
    }
    
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        this.getTarget().store(interp, this.getValue().retrieve(interp));
        return null;
    }
}
