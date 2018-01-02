// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.compiler.ir.representations.InlinerInfo;
import java.util.Map;
import org.jruby.compiler.ir.Operation;
import org.jruby.compiler.ir.operands.Operand;

public class ExceptionRegionEndMarkerInstr extends Instr
{
    private static Operand[] _empty;
    
    public ExceptionRegionEndMarkerInstr() {
        super(Operation.EXC_REGION_END);
    }
    
    public Operand[] getOperands() {
        return ExceptionRegionEndMarkerInstr._empty;
    }
    
    public void simplifyOperands(final Map<Operand, Operand> valueMap) {
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return this;
    }
    
    static {
        ExceptionRegionEndMarkerInstr._empty = new Operand[0];
    }
}
