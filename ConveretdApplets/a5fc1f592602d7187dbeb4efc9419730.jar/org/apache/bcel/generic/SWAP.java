// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public class SWAP extends StackInstruction implements StackConsumer, StackProducer
{
    public SWAP() {
        super((short)95);
    }
    
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitStackProducer(this);
        v.visitStackInstruction(this);
        v.visitSWAP(this);
    }
}
