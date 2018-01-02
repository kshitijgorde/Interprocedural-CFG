// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.darwin;

import com.kenai.constantine.Constant;

public enum TCP implements Constant
{
    TCP_MAX_SACK(3), 
    TCP_MSS(512), 
    TCP_MINMSS(216), 
    TCP_MINMSSOVERLOAD(1000), 
    TCP_MAXWIN(65535), 
    TCP_MAX_WINSHIFT(14), 
    TCP_MAXBURST(4), 
    TCP_MAXHLEN(60), 
    TCP_MAXOLEN(40), 
    TCP_NODELAY(1), 
    TCP_MAXSEG(2), 
    TCP_NOPUSH(4), 
    TCP_NOOPT(8), 
    TCP_KEEPALIVE(16);
    
    private final int value;
    public static final long MIN_VALUE = 1L;
    public static final long MAX_VALUE = 65535L;
    
    private TCP(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
