// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.windows;

import com.kenai.constantine.Constant;

public enum OpenFlags implements Constant
{
    O_APPEND(8), 
    O_ACCMODE(3), 
    O_CREAT(256), 
    O_WRONLY(1), 
    O_EXCL(1024), 
    O_RDONLY(0), 
    O_BINARY(32768), 
    O_RDWR(2), 
    O_TRUNC(512);
    
    private final int value;
    public static final long MIN_VALUE = 0L;
    public static final long MAX_VALUE = 32768L;
    
    private OpenFlags(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
