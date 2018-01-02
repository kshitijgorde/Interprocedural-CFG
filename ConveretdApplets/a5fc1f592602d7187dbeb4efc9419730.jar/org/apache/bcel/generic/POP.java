// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public class POP extends StackInstruction implements PopInstruction
{
    public POP() {
        super((short)87);
    }
    
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitPopInstruction(this);
        v.visitStackInstruction(this);
        v.visitPOP(this);
    }
}
