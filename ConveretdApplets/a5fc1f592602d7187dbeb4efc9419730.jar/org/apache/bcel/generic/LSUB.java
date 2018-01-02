// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public class LSUB extends ArithmeticInstruction
{
    public LSUB() {
        super((short)101);
    }
    
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitLSUB(this);
    }
}
