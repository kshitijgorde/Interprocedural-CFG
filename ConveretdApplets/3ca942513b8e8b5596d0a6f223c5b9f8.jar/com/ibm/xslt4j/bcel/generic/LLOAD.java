// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

public class LLOAD extends LoadInstruction
{
    LLOAD() {
        super((short)22, (short)30);
    }
    
    public LLOAD(final int n) {
        super((short)22, (short)30, n);
    }
    
    public void accept(final Visitor v) {
        super.accept(v);
        v.visitLLOAD(this);
    }
}
