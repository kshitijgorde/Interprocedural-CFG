// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.compiler.ir.operands.Label;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.operands.Operand;
import org.jruby.compiler.ir.operands.GlobalVariable;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Variable;

public class GetGlobalVariableInstr extends GetInstr
{
    public GetGlobalVariableInstr(final Variable dest, final String gvarName) {
        super(Operation.GET_GLOBAL_VAR, dest, new GlobalVariable(gvarName), null);
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return new GetGlobalVariableInstr(ii.getRenamedVariable(this.result), ((GlobalVariable)this.getSource()).name);
    }
    
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        this.getResult().store(interp, this.getSource().retrieve(interp));
        return null;
    }
}
