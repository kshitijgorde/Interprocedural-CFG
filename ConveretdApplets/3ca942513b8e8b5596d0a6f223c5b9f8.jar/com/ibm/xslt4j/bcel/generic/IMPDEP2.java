// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

public class IMPDEP2 extends Instruction
{
    public IMPDEP2() {
        super((short)255, (short)1);
    }
    
    public void accept(final Visitor v) {
        v.visitIMPDEP2(this);
    }
}
