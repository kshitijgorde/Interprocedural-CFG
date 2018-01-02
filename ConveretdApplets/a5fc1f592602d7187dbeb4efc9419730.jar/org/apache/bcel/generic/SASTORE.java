// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public class SASTORE extends ArrayInstruction implements StackConsumer
{
    public SASTORE() {
        super((short)86);
    }
    
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitArrayInstruction(this);
        v.visitSASTORE(this);
    }
}
