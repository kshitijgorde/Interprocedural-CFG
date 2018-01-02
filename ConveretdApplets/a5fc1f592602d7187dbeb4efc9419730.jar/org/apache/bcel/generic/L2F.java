// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public class L2F extends ConversionInstruction
{
    public L2F() {
        super((short)137);
    }
    
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitConversionInstruction(this);
        v.visitL2F(this);
    }
}
