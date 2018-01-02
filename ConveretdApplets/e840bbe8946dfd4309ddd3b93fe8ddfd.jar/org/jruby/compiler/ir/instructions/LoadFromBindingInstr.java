// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.compiler.ir.Interp;
import org.jruby.compiler.ir.operands.LocalVariable;
import org.jruby.compiler.ir.operands.Label;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import java.util.Map;
import org.jruby.compiler.ir.operands.Operand;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.IRExecutionScope;
import org.jruby.compiler.ir.operands.Variable;
import org.jruby.compiler.ir.IRMethod;

public class LoadFromBindingInstr extends Instr
{
    private IRMethod sourceMethod;
    private int bindingSlot;
    private String slotName;
    
    public LoadFromBindingInstr(final Variable v, final IRExecutionScope scope, final String slotName) {
        super(Operation.BINDING_LOAD, v);
        this.slotName = slotName;
        this.sourceMethod = scope.getClosestMethodAncestor();
        this.bindingSlot = this.sourceMethod.assignBindingSlot(slotName);
    }
    
    public String getSlotName() {
        return this.slotName;
    }
    
    public Operand[] getOperands() {
        return new Operand[0];
    }
    
    public void simplifyOperands(final Map<Operand, Operand> valueMap) {
    }
    
    public String toString() {
        return "\t" + this.result + " = BINDING(" + this.sourceMethod + ")." + this.getSlotName();
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return new LoadFromBindingInstr(ii.getRenamedVariable(this.result), this.sourceMethod, this.getSlotName());
    }
    
    public boolean canRaiseException() {
        return false;
    }
    
    @Interp
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        final LocalVariable v = (LocalVariable)this.getResult();
        if (this.bindingSlot == -1) {
            this.bindingSlot = this.sourceMethod.getBindingSlot(this.getSlotName());
        }
        interp.setLocalVariable(v.getLocation(), interp.getSharedBindingVariable(this.bindingSlot));
        return null;
    }
}
