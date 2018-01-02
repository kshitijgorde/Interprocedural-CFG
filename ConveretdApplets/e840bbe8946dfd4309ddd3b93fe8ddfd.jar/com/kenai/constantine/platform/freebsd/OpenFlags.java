// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.freebsd;

import com.kenai.constantine.Constant;

public enum OpenFlags implements Constant
{
    O_RDONLY(0), 
    O_WRONLY(1), 
    O_RDWR(2), 
    O_ACCMODE(3), 
    O_NONBLOCK(4), 
    O_APPEND(8), 
    O_SYNC(128), 
    O_SHLOCK(16), 
    O_EXLOCK(32), 
    O_ASYNC(64), 
    O_FSYNC(128), 
    O_NOFOLLOW(256), 
    O_CREAT(512), 
    O_TRUNC(1024), 
    O_EXCL(2048);
    
    private final int value;
    public static final long MIN_VALUE = 0L;
    public static final long MAX_VALUE = 2048L;
    
    private OpenFlags(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
