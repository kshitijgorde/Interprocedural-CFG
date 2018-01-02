// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public class ALOAD extends LoadInstruction
{
    ALOAD() {
        super((short)25, (short)42);
    }
    
    public ALOAD(final int n) {
        super((short)25, (short)42, n);
    }
    
    public void accept(final Visitor v) {
        super.accept(v);
        v.visitALOAD(this);
    }
}
