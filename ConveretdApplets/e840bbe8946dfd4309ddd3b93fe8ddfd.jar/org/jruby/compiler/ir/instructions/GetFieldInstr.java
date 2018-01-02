// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.RubyClass;
import org.jruby.compiler.ir.operands.Label;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Operand;
import org.jruby.compiler.ir.operands.Variable;

public class GetFieldInstr extends GetInstr
{
    public GetFieldInstr(final Variable dest, final Operand obj, final String fieldName) {
        super(Operation.GET_FIELD, dest, obj, fieldName);
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return new GetFieldInstr(ii.getRenamedVariable(this.result), this.getSource().cloneForInlining(ii), this.getName());
    }
    
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        final IRubyObject object = (IRubyObject)this.getSource().retrieve(interp);
        final RubyClass clazz = object.getMetaClass().getRealClass();
        final RubyClass.VariableAccessor accessor = clazz.getVariableAccessorForRead(this.getName());
        final Object v = (accessor == null) ? null : accessor.get(object);
        this.getResult().store(interp, (v == null) ? interp.getRuntime().getNil() : v);
        return null;
    }
}
