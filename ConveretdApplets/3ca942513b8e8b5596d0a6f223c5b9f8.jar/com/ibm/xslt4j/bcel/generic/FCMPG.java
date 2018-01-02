// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

public class FCMPG extends Instruction implements TypedInstruction, StackProducer, StackConsumer
{
    public FCMPG() {
        super((short)150, (short)1);
    }
    
    public Type getType(final ConstantPoolGen cp) {
        return Type.FLOAT;
    }
    
    public void accept(final Visitor v) {
        v.visitTypedInstruction(this);
        v.visitStackProducer(this);
        v.visitStackConsumer(this);
        v.visitFCMPG(this);
    }
}
