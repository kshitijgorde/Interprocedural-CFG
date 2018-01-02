// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public class FNEG extends ArithmeticInstruction
{
    public FNEG() {
        super((short)118);
    }
    
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitFNEG(this);
    }
}
