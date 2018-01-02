// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.instructions;

import org.jruby.compiler.ir.representations.InlinerInfo;
import org.jruby.compiler.ir.operands.Variable;
import org.jruby.compiler.ir.Operation;

public class DECLARE_LOCAL_TYPE_Instr extends NoOperandInstr
{
    int _argIndex;
    String _typeName;
    
    public DECLARE_LOCAL_TYPE_Instr(final int index, final String type) {
        super(Operation.DECLARE_TYPE, null);
        this._argIndex = index;
        this._typeName = type;
    }
    
    public String toString() {
        return super.toString() + "(" + this._argIndex + ":" + this._typeName + ")";
    }
    
    public Instr cloneForInlining(final InlinerInfo ii) {
        return this;
    }
}
