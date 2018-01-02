// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

public class IFLT extends IfInstruction
{
    IFLT() {
    }
    
    public IFLT(final InstructionHandle target) {
        super((short)155, target);
    }
    
    public IfInstruction negate() {
        return new IFGE(super.target);
    }
    
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIFLT(this);
    }
}
