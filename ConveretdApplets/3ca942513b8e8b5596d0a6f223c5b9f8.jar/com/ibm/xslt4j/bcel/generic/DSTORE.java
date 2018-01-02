// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

public class DSTORE extends StoreInstruction
{
    DSTORE() {
        super((short)57, (short)71);
    }
    
    public DSTORE(final int n) {
        super((short)57, (short)71, n);
    }
    
    public void accept(final Visitor v) {
        super.accept(v);
        v.visitDSTORE(this);
    }
}
