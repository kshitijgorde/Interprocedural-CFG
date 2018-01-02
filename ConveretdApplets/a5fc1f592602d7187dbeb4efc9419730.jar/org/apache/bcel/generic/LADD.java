// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public class LADD extends ArithmeticInstruction
{
    public LADD() {
        super((short)97);
    }
    
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitLADD(this);
    }
}
