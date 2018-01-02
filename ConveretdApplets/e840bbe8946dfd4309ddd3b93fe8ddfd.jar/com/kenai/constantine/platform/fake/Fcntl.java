// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.fake;

import com.kenai.constantine.Constant;

public enum Fcntl implements Constant
{
    F_DUPFD(1), 
    F_GETFD(2), 
    F_SETFD(3), 
    F_GETFL(4), 
    F_SETFL(5), 
    F_GETOWN(6), 
    F_SETOWN(7), 
    F_GETLK(8), 
    F_SETLK(9), 
    F_SETLKW(10), 
    F_CHKCLEAN(11), 
    F_PREALLOCATE(12), 
    F_SETSIZE(13), 
    F_RDADVISE(14), 
    F_RDAHEAD(15), 
    F_READBOOTSTRAP(16), 
    F_WRITEBOOTSTRAP(17), 
    F_NOCACHE(18), 
    F_LOG2PHYS(19), 
    F_GETPATH(20), 
    F_FULLFSYNC(21), 
    F_PATHPKG_CHECK(22), 
    F_FREEZE_FS(23), 
    F_THAW_FS(24), 
    F_GLOBAL_NOCACHE(25), 
    F_ADDSIGS(26), 
    F_MARKDEPENDENCY(27), 
    F_RDLCK(28), 
    F_UNLCK(29), 
    F_WRLCK(30), 
    F_ALLOCATECONTIG(31), 
    F_ALLOCATEALL(32);
    
    private final int value;
    public static final long MIN_VALUE = 1L;
    public static final long MAX_VALUE = 32L;
    
    private Fcntl(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
