// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public class DUP_X1 extends StackInstruction
{
    public DUP_X1() {
        super((short)90);
    }
    
    public void accept(final Visitor v) {
        v.visitStackInstruction(this);
        v.visitDUP_X1(this);
    }
}
