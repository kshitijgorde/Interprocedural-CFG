// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

public class LCMP extends Instruction
{
    public LCMP() {
        super((short)148, (short)1);
    }
    
    public void accept(final Visitor v) {
        v.visitLCMP(this);
    }
}
