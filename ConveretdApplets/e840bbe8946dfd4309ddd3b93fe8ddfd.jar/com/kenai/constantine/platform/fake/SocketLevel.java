// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.fake;

import com.kenai.constantine.Constant;

public enum SocketLevel implements Constant
{
    SOL_SOCKET(1), 
    SOL_IP(2), 
    SOL_TCP(3), 
    SOL_UDP(4);
    
    private final int value;
    public static final long MIN_VALUE = 1L;
    public static final long MAX_VALUE = 4L;
    
    private SocketLevel(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
