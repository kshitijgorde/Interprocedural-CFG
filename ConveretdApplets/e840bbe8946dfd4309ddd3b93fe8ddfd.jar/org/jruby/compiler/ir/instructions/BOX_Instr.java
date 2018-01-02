// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.operands.BoxedValue;
import org.jruby.compiler.ir.operands.UnboxedValue;
import java.util.Map;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Operand;
import org.jruby.compiler.ir.operands.Variable;

public class BOX_Instr extends OneOperandInstr
{
    public BOX_Instr(final Variable dst, final Operand v) {
        super(Operation.BOX_VALUE, dst, v);
    }
    
    public Operand[] getOperands() {
        return new Operand[] { this.argument };
    }
    
    public String toString() {
        return super.toString() + "(" + this.argument + ")";
    }
    
    public Operand simplifyAndGetResult(final Map<Operand, Operand> valueMap) {
        this.simplifyOperands(valueMap);
        return (this.argument instanceof UnboxedValue) ? ((UnboxedValue)this.argument)._value : new BoxedValue(this.argument);
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return new BOX_Instr(ii.getRenamedVariable(this.result), this.argument.cloneForInlining(ii));
    }
}
