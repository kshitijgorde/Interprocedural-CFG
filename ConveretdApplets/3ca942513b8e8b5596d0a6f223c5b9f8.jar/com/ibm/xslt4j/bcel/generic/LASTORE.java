// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

public class LASTORE extends ArrayInstruction implements StackConsumer
{
    public LASTORE() {
        super((short)80);
    }
    
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitArrayInstruction(this);
        v.visitLASTORE(this);
    }
}
