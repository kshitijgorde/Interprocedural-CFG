// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import java.util.Map;
import org.jruby.compiler.ir.operands.Variable;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Operand;

public abstract class GetInstr extends Instr
{
    private Operand source;
    private String ref;
    
    public GetInstr(final Operation op, final Variable dest, final Operand source, final String ref) {
        super(op, dest);
        this.source = source;
        this.ref = ref;
    }
    
    public String getName() {
        return this.ref;
    }
    
    public Operand[] getOperands() {
        return new Operand[] { this.source };
    }
    
    public Operand getSource() {
        return this.source;
    }
    
    public String toString() {
        return super.toString() + "(" + this.source + ((this.ref == null) ? "" : (", " + this.ref)) + ")";
    }
    
    public void simplifyOperands(final Map<Operand, Operand> valueMap) {
        this.source = this.source.getSimplifiedOperand(valueMap);
    }
}
