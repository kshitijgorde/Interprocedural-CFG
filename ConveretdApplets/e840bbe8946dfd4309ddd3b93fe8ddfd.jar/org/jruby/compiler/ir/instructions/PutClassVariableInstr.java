// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.compiler.ir.operands.MetaObject;
import org.jruby.RubyModule;
import org.jruby.compiler.ir.operands.Label;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Operand;

public class PutClassVariableInstr extends PutInstr
{
    public PutClassVariableInstr(final Operand scope, final String varName, final Operand value) {
        super(Operation.PUT_CVAR, scope, varName, value);
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return new PutClassVariableInstr(this.operands[1].cloneForInlining(ii), this.ref, this.operands[0].cloneForInlining(ii));
    }
    
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        final IRubyObject value = (IRubyObject)this.getValue().retrieve(interp);
        final RubyModule module = (RubyModule)this.getTarget().retrieve(interp);
        assert module != null : "MODULE should always be something";
        if (!(this.getValue() instanceof MetaObject) || !((MetaObject)this.getValue()).isModule()) {
            module.setClassVar(this.getName(), value);
        }
        return null;
    }
}
