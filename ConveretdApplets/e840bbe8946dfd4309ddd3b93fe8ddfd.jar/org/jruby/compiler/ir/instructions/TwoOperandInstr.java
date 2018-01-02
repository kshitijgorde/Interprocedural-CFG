// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import java.util.Map;
import org.jruby.compiler.ir.operands.Variable;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Operand;

public abstract class TwoOperandInstr extends Instr
{
    Operand operand1;
    Operand operand2;
    
    public TwoOperandInstr(final Operation op, final Variable destination, final Operand a1, final Operand a2) {
        super(op, destination);
        this.operand1 = a1;
        this.operand2 = a2;
    }
    
    public String toString() {
        return super.toString() + "(" + this.operand1 + ", " + this.operand2 + ")";
    }
    
    public Operand[] getOperands() {
        return new Operand[] { this.operand1, this.operand2 };
    }
    
    public Operand getOperand1() {
        return this.operand1;
    }
    
    public Operand getOperand2() {
        return this.operand2;
    }
    
    public void simplifyOperands(final Map<Operand, Operand> valueMap) {
        this.operand1 = this.operand1.getSimplifiedOperand(valueMap);
        this.operand2 = this.operand2.getSimplifiedOperand(valueMap);
    }
}
