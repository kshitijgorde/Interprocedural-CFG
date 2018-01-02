// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.openbsd;

import com.kenai.constantine.Constant;

public enum WaitFlags implements Constant
{
    WNOHANG(1), 
    WUNTRACED(2), 
    WSTOPPED(127), 
    WCONTINUED(8);
    
    private final int value;
    public static final long MIN_VALUE = 1L;
    public static final long MAX_VALUE = 127L;
    
    private WaitFlags(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
