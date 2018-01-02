// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.linux;

import com.kenai.constantine.Constant;

public enum OpenFlags implements Constant
{
    O_RDONLY(0), 
    O_WRONLY(1), 
    O_RDWR(2), 
    O_ACCMODE(3), 
    O_NONBLOCK(2048), 
    O_APPEND(1024), 
    O_SYNC(4096), 
    O_ASYNC(8192), 
    O_FSYNC(4096), 
    O_CREAT(64), 
    O_TRUNC(512), 
    O_EXCL(128);
    
    private final int value;
    public static final long MIN_VALUE = 0L;
    public static final long MAX_VALUE = 8192L;
    
    private OpenFlags(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
