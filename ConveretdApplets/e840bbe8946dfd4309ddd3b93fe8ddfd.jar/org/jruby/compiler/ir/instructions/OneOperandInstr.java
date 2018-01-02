// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import java.util.Map;
import org.jruby.compiler.ir.operands.Variable;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Operand;

public abstract class OneOperandInstr extends Instr
{
    Operand argument;
    
    public OneOperandInstr(final Operation op, final Variable dest, final Operand argument) {
        super(op, dest);
        assert argument != null : "One operand instructions must have a non-null argument";
        this.argument = argument;
    }
    
    public String toString() {
        return super.toString() + "(" + this.argument + ")";
    }
    
    public Operand getArg() {
        return this.argument;
    }
    
    public Operand[] getOperands() {
        return new Operand[] { this.argument };
    }
    
    public void simplifyOperands(final Map<Operand, Operand> valueMap) {
        this.argument = this.argument.getSimplifiedOperand(valueMap);
    }
}
