// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

public class IFLE extends IfInstruction
{
    IFLE() {
    }
    
    public IFLE(final InstructionHandle target) {
        super((short)158, target);
    }
    
    public IfInstruction negate() {
        return new IFGT(super.target);
    }
    
    public void accept(final Visitor v) {
        v.visitStackConsumer(this);
        v.visitBranchInstruction(this);
        v.visitIfInstruction(this);
        v.visitIFLE(this);
    }
}
