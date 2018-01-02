// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.operands.UnboxedValue;
import org.jruby.compiler.ir.operands.BoxedValue;
import java.util.Map;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Operand;
import org.jruby.compiler.ir.operands.Variable;

public class UNBOX_Instr extends OneOperandInstr
{
    public UNBOX_Instr(final Variable dst, final Operand v) {
        super(Operation.UNBOX_VALUE, dst, v);
    }
    
    public String toString() {
        return super.toString() + "(" + this.argument + ")";
    }
    
    public Operand simplifyAndGetResult(final Map<Operand, Operand> valueMap) {
        this.simplifyOperands(valueMap);
        return (this.argument instanceof BoxedValue) ? ((BoxedValue)this.argument)._value : new UnboxedValue(this.argument);
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return new UNBOX_Instr(ii.getRenamedVariable(this.result), this.argument.cloneForInlining(ii));
    }
}
