// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public class IUSHR extends ArithmeticInstruction
{
    public IUSHR() {
        super((short)124);
    }
    
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitIUSHR(this);
    }
}
