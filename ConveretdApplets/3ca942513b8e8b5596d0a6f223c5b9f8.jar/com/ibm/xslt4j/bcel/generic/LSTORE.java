// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

public class LSTORE extends StoreInstruction
{
    LSTORE() {
        super((short)55, (short)63);
    }
    
    public LSTORE(final int n) {
        super((short)55, (short)63, n);
    }
    
    public void accept(final Visitor v) {
        super.accept(v);
        v.visitLSTORE(this);
    }
}
