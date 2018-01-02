// 
// Decompiled by Procyon v0.5.30
// 

package org.apache.bcel.generic;

import org.apache.bcel.ExceptionConstants;

public class ATHROW extends Instruction implements UnconditionalBranch, ExceptionThrower
{
    public ATHROW() {
        super((short)191, (short)1);
    }
    
    public Class[] getExceptions() {
        return new Class[] { ExceptionConstants.THROWABLE };
    }
    
    public void accept(final Visitor v) {
        v.visitUnconditionalBranch(this);
        v.visitExceptionThrower(this);
        v.visitATHROW(this);
    }
}
