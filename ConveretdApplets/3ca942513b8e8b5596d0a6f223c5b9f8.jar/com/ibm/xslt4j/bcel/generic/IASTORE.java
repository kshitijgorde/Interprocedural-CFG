// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

public class IASTORE extends ArrayInstruction implements StackConsumer
{
    public IASTORE() {
        super((short)79);
    }
    
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitExceptionThrower(this);
        v.visitTypedInstruction(this);
        v.visitArrayInstruction(this);
        v.visitIASTORE(this);
    }
}
