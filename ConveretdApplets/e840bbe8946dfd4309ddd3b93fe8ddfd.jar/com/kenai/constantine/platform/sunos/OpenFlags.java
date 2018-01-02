// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.sunos;

import com.kenai.constantine.Constant;

public enum OpenFlags implements Constant
{
    O_RDONLY(0), 
    O_WRONLY(1), 
    O_RDWR(2), 
    O_ACCMODE(3), 
    O_NONBLOCK(128), 
    O_APPEND(8), 
    O_SYNC(16), 
    O_NOFOLLOW(131072), 
    O_CREAT(256), 
    O_TRUNC(512), 
    O_EXCL(1024);
    
    private final int value;
    public static final long MIN_VALUE = 0L;
    public static final long MAX_VALUE = 131072L;
    
    private OpenFlags(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
