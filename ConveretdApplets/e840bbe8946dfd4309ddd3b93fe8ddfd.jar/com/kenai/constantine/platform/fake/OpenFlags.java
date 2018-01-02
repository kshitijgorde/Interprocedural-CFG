// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.fake;

import com.kenai.constantine.Constant;

public enum OpenFlags implements Constant
{
    O_RDONLY(1), 
    O_WRONLY(2), 
    O_RDWR(4), 
    O_ACCMODE(8), 
    O_NONBLOCK(16), 
    O_APPEND(32), 
    O_SYNC(64), 
    O_SHLOCK(128), 
    O_EXLOCK(256), 
    O_ASYNC(512), 
    O_FSYNC(1024), 
    O_NOFOLLOW(2048), 
    O_CREAT(4096), 
    O_TRUNC(8192), 
    O_EXCL(16384), 
    O_EVTONLY(32768), 
    O_DIRECTORY(65536), 
    O_SYMLINK(131072), 
    O_BINARY(262144), 
    O_NOCTTY(524288);
    
    private final int value;
    public static final long MIN_VALUE = 1L;
    public static final long MAX_VALUE = 524288L;
    
    private OpenFlags(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
