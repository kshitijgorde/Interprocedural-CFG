// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

public class DUP_X2 extends StackInstruction
{
    public DUP_X2() {
        super((short)91);
    }
    
    public void accept(final Visitor v) {
        v.visitStackInstruction(this);
        v.visitDUP_X2(this);
    }
}
