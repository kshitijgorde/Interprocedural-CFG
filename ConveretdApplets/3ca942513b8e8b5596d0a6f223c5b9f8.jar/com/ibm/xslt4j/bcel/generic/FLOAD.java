// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

public class FLOAD extends LoadInstruction
{
    FLOAD() {
        super((short)23, (short)34);
    }
    
    public FLOAD(final int n) {
        super((short)23, (short)34, n);
    }
    
    public void accept(final Visitor v) {
        super.accept(v);
        v.visitFLOAD(this);
    }
}
