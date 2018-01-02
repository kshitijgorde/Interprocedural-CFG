// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

public class IFNULL extends IfInstruction
{
    IFNULL() {
    }
    
    public IFNULL(final InstructionHandle target) {
        super((short)198, target);
    }
    
    public IfInstruction negate() {
        return new IFNONNULL(super.target);
    }
    
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIFNULL(this);
    }
}
