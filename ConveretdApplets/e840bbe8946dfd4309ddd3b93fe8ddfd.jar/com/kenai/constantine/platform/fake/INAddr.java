// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.fake;

import com.kenai.constantine.Constant;

public enum INAddr implements Constant
{
    INADDR_ANY(1), 
    INADDR_BROADCAST(2), 
    INADDR_NONE(3), 
    INADDR_LOOPBACK(4), 
    INADDR_UNSPEC_GROUP(5), 
    INADDR_ALLHOSTS_GROUP(6), 
    INADDR_ALLRTRS_GROUP(7), 
    INADDR_MAX_LOCAL_GROUP(8);
    
    private final int value;
    public static final long MIN_VALUE = 1L;
    public static final long MAX_VALUE = 8L;
    
    private INAddr(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
