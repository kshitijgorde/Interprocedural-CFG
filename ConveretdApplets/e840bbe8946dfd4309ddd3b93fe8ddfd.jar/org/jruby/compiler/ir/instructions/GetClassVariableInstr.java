// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.RubyModule;
import org.jruby.compiler.ir.operands.Label;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Operand;
import org.jruby.compiler.ir.operands.Variable;

public class GetClassVariableInstr extends GetInstr
{
    public GetClassVariableInstr(final Variable dest, final Operand scope, final String varName) {
        super(Operation.GET_CVAR, dest, scope, varName);
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return new GetClassVariableInstr(ii.getRenamedVariable(this.result), this.getSource().cloneForInlining(ii), this.getName());
    }
    
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        this.getResult().store(interp, ((RubyModule)this.getSource().retrieve(interp)).getClassVar(this.getName()));
        return null;
    }
}
