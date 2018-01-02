// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

public class IFEQ extends IfInstruction
{
    IFEQ() {
    }
    
    public IFEQ(final InstructionHandle target) {
        super((short)153, target);
    }
    
    public IfInstruction negate() {
        return new IFNE(super.target);
    }
    
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIFEQ(this);
    }
}
