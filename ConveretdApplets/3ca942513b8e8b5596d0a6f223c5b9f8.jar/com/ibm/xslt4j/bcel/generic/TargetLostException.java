// 
// Decompiled by Procyon v0.5.30
// 

package com.ibm.xslt4j.bcel.generic;

public final class TargetLostException extends Exception
{
    private InstructionHandle[] targets;
    
    TargetLostException(final InstructionHandle[] t, final String mesg) {
        super(mesg);
        this.targets = t;
    }
    
    public InstructionHandle[] getTargets() {
        return this.targets;
    }
}
