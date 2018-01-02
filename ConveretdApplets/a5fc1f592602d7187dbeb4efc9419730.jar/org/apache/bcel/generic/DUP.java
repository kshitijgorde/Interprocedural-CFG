// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public class DUP extends StackInstruction implements PushInstruction
{
    public DUP() {
        super((short)89);
    }
    
    public void accept(final Visitor v) {
        v.visitStackProducer(this);
        v.visitPushInstruction(this);
        v.visitStackInstruction(this);
        v.visitDUP(this);
    }
}
