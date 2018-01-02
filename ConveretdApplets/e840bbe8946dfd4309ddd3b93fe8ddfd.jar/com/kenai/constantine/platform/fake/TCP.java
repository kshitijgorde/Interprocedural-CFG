// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.fake;

import com.kenai.constantine.Constant;

public enum TCP implements Constant
{
    TCP_MAX_SACK(1), 
    TCP_MSS(2), 
    TCP_MINMSS(3), 
    TCP_MINMSSOVERLOAD(4), 
    TCP_MAXWIN(5), 
    TCP_MAX_WINSHIFT(6), 
    TCP_MAXBURST(7), 
    TCP_MAXHLEN(8), 
    TCP_MAXOLEN(9), 
    TCP_NODELAY(10), 
    TCP_MAXSEG(11), 
    TCP_NOPUSH(12), 
    TCP_NOOPT(13), 
    TCP_KEEPALIVE(14), 
    TCP_NSTATES(15), 
    TCP_RETRANSHZ(16);
    
    private final int value;
    public static final long MIN_VALUE = 1L;
    public static final long MAX_VALUE = 16L;
    
    private TCP(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
