// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

public class IF_ACMPNE extends IfInstruction
{
    IF_ACMPNE() {
    }
    
    public IF_ACMPNE(final InstructionHandle target) {
        super((short)166, target);
    }
    
    public IfInstruction negate() {
        return new IF_ACMPEQ(super.target);
    }
    
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIF_ACMPNE(this);
    }
}
