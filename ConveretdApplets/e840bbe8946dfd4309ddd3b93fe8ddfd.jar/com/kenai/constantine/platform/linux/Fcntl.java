// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.linux;

import com.kenai.constantine.Constant;

public enum Fcntl implements Constant
{
    FAPPEND(1024), 
    FASYNC(8192), 
    FFSYNC(4096), 
    FNONBLOCK(2048), 
    FNDELAY(2048), 
    F_DUPFD(0), 
    F_GETFD(1), 
    F_SETFD(2), 
    F_GETFL(3), 
    F_SETFL(4), 
    F_GETOWN(9), 
    F_SETOWN(8), 
    F_GETLK(12), 
    F_SETLK(13), 
    F_SETLKW(14), 
    F_RDLCK(0), 
    F_UNLCK(2), 
    F_WRLCK(1);
    
    private final int value;
    public static final long MIN_VALUE = 0L;
    public static final long MAX_VALUE = 8192L;
    
    private Fcntl(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
