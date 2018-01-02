// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public class IALOAD extends ArrayInstruction implements StackProducer
{
    public IALOAD() {
        super((short)46);
    }
    
    public void accept(final Visitor v) {
        v.visitStackProducer(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitArrayInstruction(this);
        v.visitIALOAD(this);
    }
}
