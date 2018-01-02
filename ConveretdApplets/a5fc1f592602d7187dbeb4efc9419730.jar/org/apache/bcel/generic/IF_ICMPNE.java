// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public class IF_ICMPNE extends IfInstruction
{
    IF_ICMPNE() {
    }
    
    public IF_ICMPNE(final InstructionHandle target) {
        super((short)160, target);
    }
    
    public IfInstruction negate() {
        return new IF_ICMPEQ(super.target);
    }
    
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIF_ICMPNE(this);
    }
}
