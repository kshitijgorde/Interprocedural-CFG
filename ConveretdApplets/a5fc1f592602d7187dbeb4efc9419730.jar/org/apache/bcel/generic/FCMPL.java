// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public class FCMPL extends Instruction implements TypedInstruction, StackProducer, StackConsumer
{
    public FCMPL() {
        super((short)149, (short)1);
    }
    
    public Type getType(final ConstantPoolGen cp) {
        return Type.FLOAT;
    }
    
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitFCMPL(this);
    }
}
