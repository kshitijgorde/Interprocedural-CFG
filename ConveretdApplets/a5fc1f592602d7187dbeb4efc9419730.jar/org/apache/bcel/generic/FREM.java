// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public class FREM extends ArithmeticInstruction
{
    public FREM() {
        super((short)114);
    }
    
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitFREM(this);
    }
}
