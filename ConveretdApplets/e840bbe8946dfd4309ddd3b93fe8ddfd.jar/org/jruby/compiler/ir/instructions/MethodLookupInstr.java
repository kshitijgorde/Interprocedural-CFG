// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.compiler.ir.operands.Label;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.operands.Operand;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.MethodHandle;
import org.jruby.compiler.ir.operands.Variable;

public class MethodLookupInstr extends OneOperandInstr
{
    public MethodLookupInstr(final Variable dest, final MethodHandle mh) {
        super(Operation.METHOD_LOOKUP, dest, mh);
    }
    
    public MethodLookupInstr(final Variable dest, final Operand methodName, final Operand receiver) {
        super(Operation.METHOD_LOOKUP, dest, new MethodHandle(methodName, receiver));
    }
    
    public MethodHandle getMethodHandle() {
        return (MethodHandle)this.getArg();
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return new MethodLookupInstr(ii.getRenamedVariable(this.getResult()), (MethodHandle)this.getArg().cloneForInlining(ii));
    }
    
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        this.getResult().store(interp, this.getMethodHandle().retrieve(interp));
        return null;
    }
}
