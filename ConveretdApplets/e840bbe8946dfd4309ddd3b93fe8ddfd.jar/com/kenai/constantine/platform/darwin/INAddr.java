// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.darwin;

import com.kenai.constantine.Constant;

public enum INAddr implements Constant
{
    INADDR_ANY(0), 
    INADDR_BROADCAST(-1), 
    INADDR_NONE(-1), 
    INADDR_LOOPBACK(2130706433), 
    INADDR_UNSPEC_GROUP(-536870912), 
    INADDR_ALLHOSTS_GROUP(-536870911), 
    INADDR_ALLRTRS_GROUP(-536870910), 
    INADDR_MAX_LOCAL_GROUP(-536870657);
    
    private final int value;
    public static final long MIN_VALUE = 0L;
    public static final long MAX_VALUE = -1L;
    
    private INAddr(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
