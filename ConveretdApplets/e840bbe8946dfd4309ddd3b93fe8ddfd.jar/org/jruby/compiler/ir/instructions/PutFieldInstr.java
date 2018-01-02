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

public class PutFieldInstr extends PutInstr
{
    public PutFieldInstr(final Operand obj, final String fieldName, final Operand value) {
        super(Operation.PUT_FIELD, obj, fieldName, value);
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return new PutFieldInstr(this.operands[1].cloneForInlining(ii), this.ref, this.operands[0].cloneForInlining(ii));
    }
    
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        final IRubyObject object = (IRubyObject)this.getTarget().retrieve(interp);
        final RubyClass clazz = object.getMetaClass().getRealClass();
        clazz.getVariableAccessorForWrite(this.getName()).set(object, this.getValue().retrieve(interp));
        return null;
    }
}
