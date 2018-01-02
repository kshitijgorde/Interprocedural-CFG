// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public class BALOAD extends ArrayInstruction implements StackProducer
{
    public BALOAD() {
        super((short)51);
    }
    
    public void accept(final Visitor v) {
        v.visitStackProducer(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitArrayInstruction(this);
        v.visitBALOAD(this);
    }
}
