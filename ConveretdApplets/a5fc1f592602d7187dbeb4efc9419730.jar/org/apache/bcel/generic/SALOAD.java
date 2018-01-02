// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public class SALOAD extends ArrayInstruction implements StackProducer
{
    public SALOAD() {
        super((short)53);
    }
    
    public void accept(final Visitor v) {
        v.visitStackProducer(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitArrayInstruction(this);
        v.visitSALOAD(this);
    }
}
