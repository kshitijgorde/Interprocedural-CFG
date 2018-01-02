// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public class L2I extends ConversionInstruction
{
    public L2I() {
        super((short)136);
    }
    
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitConversionInstruction(this);
        v.visitL2I(this);
    }
}
