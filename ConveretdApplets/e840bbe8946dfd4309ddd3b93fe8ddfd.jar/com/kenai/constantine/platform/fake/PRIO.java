// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.fake;

import com.kenai.constantine.Constant;

public enum PRIO implements Constant
{
    PRIO_MIN(1), 
    PRIO_PROCESS(2), 
    PRIO_PGRP(3), 
    PRIO_USER(4), 
    PRIO_MAX(5);
    
    private final int value;
    public static final long MIN_VALUE = 1L;
    public static final long MAX_VALUE = 5L;
    
    private PRIO(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
