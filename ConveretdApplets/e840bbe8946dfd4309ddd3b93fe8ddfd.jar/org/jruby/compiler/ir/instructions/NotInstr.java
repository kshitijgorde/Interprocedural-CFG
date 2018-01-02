// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.compiler.ir.operands.Label;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.operands.BooleanLiteral;
import java.util.Map;
import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Operand;
import org.jruby.compiler.ir.operands.Variable;

public class NotInstr extends OneOperandInstr
{
    public NotInstr(final Variable dst, final Operand arg) {
        super(Operation.NOT, dst, arg);
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return new NotInstr(ii.getRenamedVariable(this.result), this.argument.cloneForInlining(ii));
    }
    
    public Operand simplifyAndGetResult(final Map<Operand, Operand> valueMap) {
        this.simplifyOperands(valueMap);
        return (this.argument instanceof BooleanLiteral) ? ((BooleanLiteral)this.argument).logicalNot() : null;
    }
    
    public boolean canRaiseException() {
        return false;
    }
    
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        final boolean not = !((IRubyObject)this.getArg().retrieve(interp)).isTrue();
        this.getResult().store(interp, interp.getRuntime().newBoolean(not));
        return null;
    }
}
