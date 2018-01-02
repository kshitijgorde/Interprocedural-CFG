// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public class DUP2 extends StackInstruction implements PushInstruction
{
    public DUP2() {
        super((short)92);
    }
    
    public void accept(final Visitor v) {
        v.visitStackProducer(this);
        v.visitPushInstruction(this);
        v.visitStackInstruction(this);
        v.visitDUP2(this);
    }
}
