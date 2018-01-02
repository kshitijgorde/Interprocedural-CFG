// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public class ISTORE extends StoreInstruction
{
    ISTORE() {
        super((short)54, (short)59);
    }
    
    public ISTORE(final int n) {
        super((short)54, (short)59, n);
    }
    
    public void accept(final Visitor v) {
        super.accept(v);
        v.visitISTORE(this);
    }
}
