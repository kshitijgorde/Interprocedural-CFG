// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import java.util.Map;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Operand;

public abstract class PutInstr extends Instr
{
    public final int VALUE = 0;
    public final int TARGET = 1;
    Operand[] operands;
    String ref;
    
    public PutInstr(final Operation op, final Operand target, final String ref, final Operand value) {
        super(op);
        this.operands = new Operand[] { value, target };
        this.ref = ref;
    }
    
    public Operand[] getOperands() {
        return this.operands;
    }
    
    public String getName() {
        return this.ref;
    }
    
    public Operand getTarget() {
        return this.operands[1];
    }
    
    public Operand getValue() {
        return this.operands[0];
    }
    
    public String toString() {
        return super.toString() + "(" + this.operands[1] + ((this.ref == null) ? "" : (", " + this.ref)) + ") = " + this.operands[0];
    }
    
    public void simplifyOperands(final Map<Operand, Operand> valueMap) {
        this.operands[0] = this.operands[0].getSimplifiedOperand(valueMap);
        this.operands[1] = this.operands[1].getSimplifiedOperand(valueMap);
    }
}
