// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public class IFNE extends IfInstruction
{
    IFNE() {
    }
    
    public IFNE(final InstructionHandle target) {
        super((short)154, target);
    }
    
    public IfInstruction negate() {
        return new IFEQ(super.target);
    }
    
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIFNE(this);
    }
}
