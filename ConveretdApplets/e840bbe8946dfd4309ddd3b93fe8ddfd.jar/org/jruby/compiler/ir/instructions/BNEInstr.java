// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.compiler.ir.operands.BooleanLiteral;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Label;
import org.jruby.compiler.ir.operands.Operand;

public class BNEInstr extends BranchInstr
{
    public BNEInstr(final Operand v1, final Operand v2, final Label jmpTarget) {
        super(Operation.BNE, v1, v2, jmpTarget);
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return new BNEInstr(this.operand1.cloneForInlining(ii), this.operand2.cloneForInlining(ii), ii.getRenamedLabel(this.target));
    }
    
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        final Operand op1 = this.getOperand1();
        final Operand op2 = this.getOperand2();
        final Object value1 = op1.retrieve(interp);
        if (op2 instanceof BooleanLiteral) {
            final boolean v1True = ((IRubyObject)value1).isTrue();
            final boolean op2True = ((BooleanLiteral)op2).isTrue();
            return ((v1True && !op2True) || (v1True && !op2True)) ? this.target : null;
        }
        final Object value2 = op2.retrieve(interp);
        return (value1 != value2) ? this.target : null;
    }
}
