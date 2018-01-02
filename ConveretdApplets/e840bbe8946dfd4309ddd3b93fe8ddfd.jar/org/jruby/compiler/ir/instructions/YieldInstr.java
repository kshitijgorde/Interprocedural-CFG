// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import java.util.Map;
import org.jruby.compiler.ir.Interp;
import org.jruby.compiler.ir.operands.Label;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Variable;
import org.jruby.compiler.ir.operands.Operand;

public class YieldInstr extends Instr
{
    Operand yieldArg;
    
    public YieldInstr(final Variable result, final Operand arg) {
        super(Operation.YIELD, result);
        this.yieldArg = arg;
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return this;
    }
    
    @Interp
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        Object resultValue;
        if (this.yieldArg == null) {
            resultValue = interp.getBlock().call(interp.getContext());
        }
        else {
            resultValue = interp.getBlock().yield(interp.getContext(), (IRubyObject)this.yieldArg.retrieve(interp));
        }
        this.getResult().store(interp, resultValue);
        return null;
    }
    
    public String toString() {
        return super.toString() + "(" + this.yieldArg + ")";
    }
    
    public Operand[] getOperands() {
        final Operand[] array2;
        if (this.yieldArg == null) {
            final Operand[] array = new Operand[0];
        }
        else {
            array2 = new Operand[] { this.yieldArg };
        }
        return array2;
    }
    
    public void simplifyOperands(final Map<Operand, Operand> valueMap) {
        if (this.yieldArg != null) {
            this.yieldArg = this.yieldArg.getSimplifiedOperand(valueMap);
        }
    }
}
