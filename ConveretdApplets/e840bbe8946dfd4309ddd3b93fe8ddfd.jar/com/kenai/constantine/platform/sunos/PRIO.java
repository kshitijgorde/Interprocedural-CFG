// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.sunos;

import com.kenai.constantine.Constant;

public enum PRIO implements Constant
{
    PRIO_PROCESS(0), 
    PRIO_PGRP(1), 
    PRIO_USER(2);
    
    private final int value;
    public static final long MIN_VALUE = 0L;
    public static final long MAX_VALUE = 2L;
    
    private PRIO(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
