// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public class DLOAD extends LoadInstruction
{
    DLOAD() {
        super((short)24, (short)38);
    }
    
    public DLOAD(final int n) {
        super((short)24, (short)38, n);
    }
    
    public void accept(final Visitor v) {
        super.accept(v);
        v.visitDLOAD(this);
    }
}
