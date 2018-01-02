// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

public class DUP2_X2 extends StackInstruction
{
    public DUP2_X2() {
        super((short)94);
    }
    
    public void accept(final Visitor v) {
        v.visitStackInstruction(this);
        v.visitDUP2_X2(this);
    }
}
