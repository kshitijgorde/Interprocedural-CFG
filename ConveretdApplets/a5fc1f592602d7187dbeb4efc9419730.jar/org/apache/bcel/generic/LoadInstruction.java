// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public abstract class LoadInstruction extends LocalVariableInstruction implements PushInstruction
{
    LoadInstruction(final short canon_tag, final short c_tag) {
        super(canon_tag, c_tag);
    }
    
    protected LoadInstruction(final short opcode, final short c_tag, final int n) {
        super(opcode, c_tag, n);
    }
    
    public void accept(final Visitor v) {
        v.visitStackProducer(this);
        v.visitPushInstruction(this);
        v.visitTypedInstruction(this);
        v.visitLocalVariableInstruction(this);
        v.visitLoadInstruction(this);
    }
}
