// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

public class ReturnaddressType extends Type
{
    public static final ReturnaddressType NO_TARGET;
    private InstructionHandle returnTarget;
    
    static {
        NO_TARGET = new ReturnaddressType();
    }
    
    private ReturnaddressType() {
        super((byte)16, "<return address>");
    }
    
    public ReturnaddressType(final InstructionHandle returnTarget) {
        super((byte)16, "<return address targeting " + returnTarget + ">");
        this.returnTarget = returnTarget;
    }
    
    public boolean equals(final Object rat) {
        return rat instanceof ReturnaddressType && ((ReturnaddressType)rat).returnTarget.equals(this.returnTarget);
    }
    
    public InstructionHandle getTarget() {
        return this.returnTarget;
    }
}
