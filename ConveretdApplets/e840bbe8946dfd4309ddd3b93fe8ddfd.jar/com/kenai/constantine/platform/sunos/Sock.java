// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.sunos;

import com.kenai.constantine.Constant;

public enum Sock implements Constant
{
    SOCK_STREAM(2), 
    SOCK_DGRAM(1), 
    SOCK_RAW(4), 
    SOCK_RDM(5), 
    SOCK_SEQPACKET(6);
    
    private final int value;
    public static final long MIN_VALUE = 1L;
    public static final long MAX_VALUE = 6L;
    
    private Sock(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
