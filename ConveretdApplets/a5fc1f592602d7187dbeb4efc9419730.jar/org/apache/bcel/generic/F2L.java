// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public class F2L extends ConversionInstruction
{
    public F2L() {
        super((short)140);
    }
    
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitConversionInstruction(this);
        v.visitF2L(this);
    }
}
