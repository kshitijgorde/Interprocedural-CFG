// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.darwin;

import com.kenai.constantine.Constant;

public enum WaitFlags implements Constant
{
    WNOHANG(1), 
    WUNTRACED(2), 
    WSTOPPED(8), 
    WEXITED(4), 
    WCONTINUED(16), 
    WNOWAIT(32);
    
    private final int value;
    public static final long MIN_VALUE = 1L;
    public static final long MAX_VALUE = 32L;
    
    private WaitFlags(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
