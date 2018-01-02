// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public class IMPDEP1 extends Instruction
{
    public IMPDEP1() {
        super((short)254, (short)1);
    }
    
    public void accept(final Visitor v) {
        v.visitIMPDEP1(this);
    }
}
