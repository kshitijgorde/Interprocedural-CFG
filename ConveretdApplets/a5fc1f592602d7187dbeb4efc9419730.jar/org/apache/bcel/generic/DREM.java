// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public class DREM extends ArithmeticInstruction
{
    public DREM() {
        super((short)115);
    }
    
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitDREM(this);
    }
}
