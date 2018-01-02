// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public abstract class StoreInstruction extends LocalVariableInstruction implements PopInstruction
{
    StoreInstruction(final short canon_tag, final short c_tag) {
        super(canon_tag, c_tag);
    }
    
    protected StoreInstruction(final short opcode, final short c_tag, final int n) {
        super(opcode, c_tag, n);
    }
    
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitPopInstruction(this);
        v.visitStoreInstruction(this);
        v.visitTypedInstruction(this);
        v.visitLocalVariableInstruction(this);
        v.visitStoreInstruction(this);
    }
}
