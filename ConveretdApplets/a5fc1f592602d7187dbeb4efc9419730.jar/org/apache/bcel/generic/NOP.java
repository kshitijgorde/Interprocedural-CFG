// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public class NOP extends Instruction
{
    public NOP() {
        super((short)0, (short)1);
    }
    
    public void accept(final Visitor v) {
        v.visitNOP(this);
    }
}
