// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public class IADD extends ArithmeticInstruction
{
    public IADD() {
        super((short)96);
    }
    
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitIADD(this);
    }
}
