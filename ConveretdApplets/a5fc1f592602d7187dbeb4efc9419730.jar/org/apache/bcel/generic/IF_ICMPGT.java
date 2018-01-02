// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public class IF_ICMPGT extends IfInstruction
{
    IF_ICMPGT() {
    }
    
    public IF_ICMPGT(final InstructionHandle target) {
        super((short)163, target);
    }
    
    public IfInstruction negate() {
        return new IF_ICMPLE(super.target);
    }
    
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIF_ICMPGT(this);
    }
}
