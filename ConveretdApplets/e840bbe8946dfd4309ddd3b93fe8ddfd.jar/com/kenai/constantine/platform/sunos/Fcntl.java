// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.sunos;

import com.kenai.constantine.Constant;

public enum Fcntl implements Constant
{
    F_DUPFD(0), 
    F_GETFD(1), 
    F_SETFD(2), 
    F_GETFL(3), 
    F_SETFL(4), 
    F_GETOWN(23), 
    F_SETOWN(24), 
    F_GETLK(33), 
    F_SETLK(34), 
    F_SETLKW(35), 
    F_RDLCK(1), 
    F_UNLCK(3), 
    F_WRLCK(2);
    
    private final int value;
    public static final long MIN_VALUE = 0L;
    public static final long MAX_VALUE = 35L;
    
    private Fcntl(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
