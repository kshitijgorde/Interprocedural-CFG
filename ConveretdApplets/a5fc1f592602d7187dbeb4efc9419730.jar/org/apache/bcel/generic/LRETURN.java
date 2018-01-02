// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public class LRETURN extends ReturnInstruction
{
    public LRETURN() {
        super((short)173);
    }
    
    public void accept(final Visitor v) {
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitStackConsumer(this);
        v.visitReturnInstruction(this);
        v.visitLRETURN(this);
    }
}
