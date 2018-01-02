// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

public class IF_ICMPLT extends IfInstruction
{
    IF_ICMPLT() {
    }
    
    public IF_ICMPLT(final InstructionHandle target) {
        super((short)161, target);
    }
    
    public IfInstruction negate() {
        return new IF_ICMPGE(super.target);
    }
    
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIF_ICMPLT(this);
    }
}
