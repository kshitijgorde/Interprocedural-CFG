// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public class DADD extends ArithmeticInstruction
{
    public DADD() {
        super((short)99);
    }
    
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitDADD(this);
    }
}
