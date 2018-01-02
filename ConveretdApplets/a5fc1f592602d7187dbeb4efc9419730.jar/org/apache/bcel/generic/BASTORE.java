// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public class BASTORE extends ArrayInstruction implements StackConsumer
{
    public BASTORE() {
        super((short)84);
    }
    
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitArrayInstruction(this);
        v.visitBASTORE(this);
    }
}
