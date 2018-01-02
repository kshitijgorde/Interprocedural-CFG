// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.compiler.ir.operands.LocalVariable;
import org.jruby.compiler.ir.operands.Label;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.operands.Variable;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Operand;
import org.jruby.compiler.ir.IRExecutionScope;
import org.jruby.compiler.ir.IRMethod;

public class StoreToBindingInstr extends OneOperandInstr
{
    private IRMethod targetMethod;
    private int bindingSlot;
    private String slotName;
    
    public StoreToBindingInstr(final IRExecutionScope scope, final String slotName, final Operand value) {
        super(Operation.BINDING_STORE, null, value);
        this.slotName = slotName;
        this.targetMethod = scope.getClosestMethodAncestor();
        this.bindingSlot = this.targetMethod.assignBindingSlot(slotName);
    }
    
    public String getSlotName() {
        return this.slotName;
    }
    
    public String toString() {
        return "\tBINDING(" + this.targetMethod + ")." + this.slotName + " = " + this.getArg();
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return new StoreToBindingInstr(this.targetMethod, this.slotName, this.getArg().cloneForInlining(ii));
    }
    
    public boolean canRaiseException() {
        return false;
    }
    
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        final LocalVariable v = (LocalVariable)this.getArg();
        if (this.bindingSlot == -1) {
            this.bindingSlot = this.targetMethod.getBindingSlot(v.getName());
        }
        interp.setSharedBindingVariable(this.bindingSlot, interp.getLocalVariable(v.getLocation()));
        return null;
    }
}
