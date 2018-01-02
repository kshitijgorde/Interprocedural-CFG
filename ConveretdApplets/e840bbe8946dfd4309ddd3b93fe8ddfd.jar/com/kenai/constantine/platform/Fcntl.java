// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform;

import com.kenai.constantine.Constant;

public enum Fcntl implements Constant
{
    F_DUPFD, 
    F_GETFD, 
    F_SETFD, 
    F_GETFL, 
    F_SETFL, 
    F_GETOWN, 
    F_SETOWN, 
    F_GETLK, 
    F_SETLK, 
    F_SETLKW, 
    F_CHKCLEAN, 
    F_PREALLOCATE, 
    F_SETSIZE, 
    F_RDADVISE, 
    F_RDAHEAD, 
    F_READBOOTSTRAP, 
    F_WRITEBOOTSTRAP, 
    F_NOCACHE, 
    F_LOG2PHYS, 
    F_GETPATH, 
    F_FULLFSYNC, 
    F_PATHPKG_CHECK, 
    F_FREEZE_FS, 
    F_THAW_FS, 
    F_GLOBAL_NOCACHE, 
    F_ADDSIGS, 
    F_MARKDEPENDENCY, 
    F_RDLCK, 
    F_UNLCK, 
    F_WRLCK, 
    F_ALLOCATECONTIG, 
    F_ALLOCATEALL, 
    __UNKNOWN_CONSTANT__;
    
    private static final ConstantResolver<Fcntl> resolver;
    
    public final int value() {
        return Fcntl.resolver.intValue(this);
    }
    
    public final String description() {
        return Fcntl.resolver.description(this);
    }
    
    public final String toString() {
        return this.description();
    }
    
    public static final Fcntl valueOf(final int value) {
        return Fcntl.resolver.valueOf(value);
    }
    
    static {
        resolver = ConstantResolver.getResolver(Fcntl.class, 20000, 20999);
    }
}
