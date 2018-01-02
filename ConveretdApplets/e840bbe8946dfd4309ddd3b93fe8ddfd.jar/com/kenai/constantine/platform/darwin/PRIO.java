// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.darwin;

import com.kenai.constantine.Constant;

public enum PRIO implements Constant
{
    PRIO_MIN(-20), 
    PRIO_PROCESS(0), 
    PRIO_PGRP(1), 
    PRIO_USER(2), 
    PRIO_MAX(20);
    
    private final int value;
    public static final long MIN_VALUE = -20L;
    public static final long MAX_VALUE = 20L;
    
    private PRIO(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
