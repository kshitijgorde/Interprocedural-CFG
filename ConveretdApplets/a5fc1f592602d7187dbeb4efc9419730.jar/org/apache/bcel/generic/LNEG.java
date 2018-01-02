// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public class LNEG extends ArithmeticInstruction
{
    public LNEG() {
        super((short)117);
    }
    
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitLNEG(this);
    }
}
