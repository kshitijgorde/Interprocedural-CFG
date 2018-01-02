// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.RubyArray;
import org.jruby.compiler.ir.operands.Label;
import org.jruby.runtime.builtin.IRubyObject;
import org.jruby.interpreter.InterpreterContext;
import org.jruby.compiler.ir.representations.InlinerInfo;
import java.util.Map;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Operand;
import org.jruby.compiler.ir.operands.Variable;

public class GetArrayInstr extends OneOperandInstr
{
    public final int index;
    public final boolean all;
    
    public GetArrayInstr(final Variable dest, final Operand array, final int index, final boolean getRestOfArray) {
        super(Operation.GET_ARRAY, dest, array);
        this.index = index;
        this.all = getRestOfArray;
    }
    
    public String toString() {
        return "\t" + this.result + " = " + this.argument + "[" + this.index + (this.all ? ":END" : "") + "] (GET_ARRAY)";
    }
    
    public Operand simplifyAndGetResult(final Map<Operand, Operand> valueMap) {
        this.simplifyOperands(valueMap);
        final Operand val = this.argument.getValue(valueMap);
        return val.fetchCompileTimeArrayElement(this.index, this.all);
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return new GetArrayInstr(ii.getRenamedVariable(this.result), this.argument.cloneForInlining(ii), this.index, this.all);
    }
    
    public Label interpret(final InterpreterContext interp, final IRubyObject self) {
        final RubyArray array = (RubyArray)this.getArg().retrieve(interp);
        this.getResult().store(interp, array.entry(this.index));
        return null;
    }
}
