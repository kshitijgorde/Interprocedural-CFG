// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

public class ASTORE extends StoreInstruction
{
    ASTORE() {
        super((short)58, (short)75);
    }
    
    public ASTORE(final int n) {
        super((short)58, (short)75, n);
    }
    
    public void accept(final Visitor v) {
        super.accept(v);
        v.visitASTORE(this);
    }
}
