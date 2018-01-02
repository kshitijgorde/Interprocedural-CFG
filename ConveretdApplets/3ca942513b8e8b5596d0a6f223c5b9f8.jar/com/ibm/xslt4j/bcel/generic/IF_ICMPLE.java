// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

public class IF_ICMPLE extends IfInstruction
{
    IF_ICMPLE() {
    }
    
    public IF_ICMPLE(final InstructionHandle target) {
        super((short)164, target);
    }
    
    public IfInstruction negate() {
        return new IF_ICMPGT(super.target);
    }
    
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIF_ICMPLE(this);
    }
}
