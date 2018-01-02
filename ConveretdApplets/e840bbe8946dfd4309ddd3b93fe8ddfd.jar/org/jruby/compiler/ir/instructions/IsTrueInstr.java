// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.compiler.ir.operands.Label;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.operands.BooleanLiteral;
import org.jruby.compiler.ir.operands.Nil;
import java.util.Map;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Operand;
import org.jruby.compiler.ir.operands.Variable;

public class IsTrueInstr extends OneOperandInstr
{
    public IsTrueInstr(final Variable result, final Operand arg) {
        super(Operation.IS_TRUE, result, arg);
    }
    
    public Operand simplifyAndGetResult(final Map<Operand, Operand> valueMap) {
        this.simplifyOperands(valueMap);
        if (this.argument.isConstant()) {
            return (this.argument == Nil.NIL || this.argument == BooleanLiteral.FALSE) ? BooleanLiteral.FALSE : BooleanLiteral.TRUE;
        }
        return null;
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return new IsTrueInstr(ii.getRenamedVariable(this.result), this.argument.cloneForInlining(ii));
    }
    
    public boolean canRaiseException() {
        return false;
    }
    
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        this.getResult().store(interp, ((IRubyObject)this.getArg().retrieve(interp)).isTrue());
        return null;
    }
}
