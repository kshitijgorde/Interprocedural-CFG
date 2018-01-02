// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.sunos;

import com.kenai.constantine.Constant;

public enum WaitFlags implements Constant
{
    WNOHANG(64), 
    WUNTRACED(4), 
    WSTOPPED(4), 
    WEXITED(1), 
    WCONTINUED(8), 
    WNOWAIT(128);
    
    private final int value;
    public static final long MIN_VALUE = 1L;
    public static final long MAX_VALUE = 128L;
    
    private WaitFlags(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
