// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import java.util.Map;
import org.jruby.compiler.ir.operands.Operand;
import org.jruby.compiler.ir.operands.Variable;
import org.jruby.compiler.ir.Operation;

public abstract class NoOperandInstr extends Instr
{
    public NoOperandInstr(final Operation op, final Variable dest) {
        super(op, dest);
    }
    
    public NoOperandInstr(final Operation op) {
        super(op);
    }
    
    public Operand[] getOperands() {
        return Operand.EMPTY_ARRAY;
    }
    
    public void simplifyOperands(final Map<Operand, Operand> valueMap) {
    }
}
