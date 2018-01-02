// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public class FADD extends ArithmeticInstruction
{
    public FADD() {
        super((short)98);
    }
    
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitFADD(this);
    }
}
