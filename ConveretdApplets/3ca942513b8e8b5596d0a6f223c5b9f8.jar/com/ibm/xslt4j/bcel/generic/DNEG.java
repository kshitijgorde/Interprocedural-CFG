// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

public class DNEG extends ArithmeticInstruction
{
    public DNEG() {
        super((short)119);
    }
    
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitArithmeticInstruction(this);
        v.visitDNEG(this);
    }
}
