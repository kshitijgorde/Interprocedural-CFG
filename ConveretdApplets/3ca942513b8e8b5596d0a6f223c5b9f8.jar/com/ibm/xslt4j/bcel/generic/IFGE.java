// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

public class IFGE extends IfInstruction
{
    IFGE() {
    }
    
    public IFGE(final InstructionHandle target) {
        super((short)156, target);
    }
    
    public IfInstruction negate() {
        return new IFLT(super.target);
    }
    
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIFGE(this);
    }
}
