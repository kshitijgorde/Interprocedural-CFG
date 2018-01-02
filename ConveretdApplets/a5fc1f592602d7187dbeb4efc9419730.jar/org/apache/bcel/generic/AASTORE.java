// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public class AASTORE extends ArrayInstruction implements StackConsumer
{
    public AASTORE() {
        super((short)83);
    }
    
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitArrayInstruction(this);
        v.visitAASTORE(this);
    }
}
