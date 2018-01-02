// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public class BREAKPOINT extends Instruction
{
    public BREAKPOINT() {
        super((short)202, (short)1);
    }
    
    public void accept(final Visitor v) {
        v.visitBREAKPOINT(this);
    }
}
