// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

public class FALOAD extends ArrayInstruction implements StackProducer
{
    public FALOAD() {
        super((short)48);
    }
    
    public void accept(final Visitor v) {
        v.visitStackProducer(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitArrayInstruction(this);
        v.visitFALOAD(this);
    }
}
