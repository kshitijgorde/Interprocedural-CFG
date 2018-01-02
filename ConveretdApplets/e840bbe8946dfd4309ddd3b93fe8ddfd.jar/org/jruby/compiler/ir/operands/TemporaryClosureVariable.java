// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.compiler.ir.operands;

public class TemporaryClosureVariable extends TemporaryVariable
{
    final int closureId;
    
    public TemporaryClosureVariable(final int closureId, final int offset) {
        super(offset);
        this.closureId = closureId;
    }
    
    public String getPrefix() {
        return "%cl_" + this.closureId + "_";
    }
}
