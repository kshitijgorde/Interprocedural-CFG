// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

public class FSTORE extends StoreInstruction
{
    FSTORE() {
        super((short)56, (short)67);
    }
    
    public FSTORE(final int n) {
        super((short)56, (short)67, n);
    }
    
    public void accept(final Visitor v) {
        super.accept(v);
        v.visitFSTORE(this);
    }
}
