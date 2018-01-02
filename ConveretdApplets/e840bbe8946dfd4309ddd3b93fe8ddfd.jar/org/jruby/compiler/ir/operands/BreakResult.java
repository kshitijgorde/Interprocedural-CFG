// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.operands;

import org.jruby.compiler.ir.representations.InlinerInfo;

public class BreakResult extends Operand
{
    public final Operand _result;
    public final Label _jumpTarget;
    
    public BreakResult(final Operand v, final Label l) {
        this._result = v;
        this._jumpTarget = l;
    }
    
    public String toString() {
        return "BRK(" + this._result + ", " + this._jumpTarget + ")";
    }
    
    public Operand cloneForInlining(final InlinerInfo ii) {
        return new BreakResult(this._result.cloneForInlining(ii), ii.getRenamedLabel(this._jumpTarget));
    }
}
