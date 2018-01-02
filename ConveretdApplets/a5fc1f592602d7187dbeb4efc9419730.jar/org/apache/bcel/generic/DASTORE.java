// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public class DASTORE extends ArrayInstruction implements StackConsumer
{
    public DASTORE() {
        super((short)82);
    }
    
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitArrayInstruction(this);
        v.visitDASTORE(this);
    }
}
