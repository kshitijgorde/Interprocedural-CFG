// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public class CALOAD extends ArrayInstruction implements StackProducer
{
    public CALOAD() {
        super((short)52);
    }
    
    public void accept(final Visitor v) {
        v.visitStackProducer(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitArrayInstruction(this);
        v.visitCALOAD(this);
    }
}
