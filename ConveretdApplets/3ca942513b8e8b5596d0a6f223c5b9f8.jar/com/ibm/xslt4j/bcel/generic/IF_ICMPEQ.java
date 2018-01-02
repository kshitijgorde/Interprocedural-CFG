// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

public class IF_ICMPEQ extends IfInstruction
{
    IF_ICMPEQ() {
    }
    
    public IF_ICMPEQ(final InstructionHandle target) {
        super((short)159, target);
    }
    
    public IfInstruction negate() {
        return new IF_ICMPNE(super.target);
    }
    
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIF_ICMPEQ(this);
    }
}
