// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

public class ILOAD extends LoadInstruction
{
    ILOAD() {
        super((short)21, (short)26);
    }
    
    public ILOAD(final int n) {
        super((short)21, (short)26, n);
    }
    
    public void accept(final Visitor v) {
        super.accept(v);
        v.visitILOAD(this);
    }
}
