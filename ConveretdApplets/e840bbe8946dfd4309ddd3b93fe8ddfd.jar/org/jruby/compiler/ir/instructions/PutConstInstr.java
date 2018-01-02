// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.RubyModule;
import org.jruby.compiler.ir.operands.Label;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.operands.MetaObject;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Operand;
import org.jruby.compiler.ir.IRScope;

public class PutConstInstr extends PutInstr
{
    public PutConstInstr(final IRScope scope, final String constName, final Operand val) {
        super(Operation.PUT_CONST, MetaObject.create(scope), constName, val);
    }
    
    public PutConstInstr(final Operand scopeOrObj, final String constName, final Operand val) {
        super(Operation.PUT_CONST, scopeOrObj, constName, val);
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return new PutConstInstr(this.operands[1].cloneForInlining(ii), this.ref, this.operands[0].cloneForInlining(ii));
    }
    
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        final IRubyObject value = (IRubyObject)this.getValue().retrieve(interp);
        final RubyModule module = (RubyModule)this.getTarget().retrieve(interp);
        assert module != null : "MODULE should always be something";
        module.setConstant(this.getName(), value);
        return null;
    }
}
