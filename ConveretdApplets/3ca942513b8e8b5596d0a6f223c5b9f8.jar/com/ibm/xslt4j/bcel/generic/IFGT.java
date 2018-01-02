// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

public class IFGT extends IfInstruction
{
    IFGT() {
    }
    
    public IFGT(final InstructionHandle target) {
        super((short)157, target);
    }
    
    public IfInstruction negate() {
        return new IFLE(super.target);
    }
    
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIFGT(this);
    }
}
