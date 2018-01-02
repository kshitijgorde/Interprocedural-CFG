// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.compiler.ir.operands.Variable;
import org.jruby.compiler.ir.operands.Operand;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Label;

public abstract class BranchInstr extends TwoOperandInstr
{
    Label target;
    
    public BranchInstr(final Operation op, final Operand v1, final Operand v2, final Label jmpTarget) {
        super(op, null, v1, v2);
        this.target = jmpTarget;
    }
    
    public Label getJumpTarget() {
        return this.target;
    }
    
    public boolean canRaiseException() {
        return false;
    }
    
    public String toString() {
        return "\t" + this.operation + "(" + this.operand1 + ", " + this.operand2 + ", " + this.target + ")";
    }
}
