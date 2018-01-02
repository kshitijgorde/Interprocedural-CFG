// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform;

import com.kenai.constantine.Constant;

public enum OpenFlags implements Constant
{
    O_RDONLY, 
    O_WRONLY, 
    O_RDWR, 
    O_ACCMODE, 
    O_NONBLOCK, 
    O_APPEND, 
    O_SYNC, 
    O_SHLOCK, 
    O_EXLOCK, 
    O_ASYNC, 
    O_FSYNC, 
    O_NOFOLLOW, 
    O_CREAT, 
    O_TRUNC, 
    O_EXCL, 
    O_EVTONLY, 
    O_DIRECTORY, 
    O_SYMLINK, 
    O_BINARY, 
    O_NOCTTY, 
    __UNKNOWN_CONSTANT__;
    
    private static final ConstantResolver<OpenFlags> resolver;
    
    public final int value() {
        return OpenFlags.resolver.intValue(this);
    }
    
    public final String description() {
        return OpenFlags.resolver.description(this);
    }
    
    public final String toString() {
        return this.description();
    }
    
    public static final OpenFlags valueOf(final int value) {
        return OpenFlags.resolver.valueOf(value);
    }
    
    static {
        resolver = ConstantResolver.getBitmaskResolver(OpenFlags.class);
    }
}
