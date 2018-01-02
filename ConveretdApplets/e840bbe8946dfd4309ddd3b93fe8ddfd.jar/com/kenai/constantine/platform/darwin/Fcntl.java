// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.darwin;

import com.kenai.constantine.Constant;

public enum Fcntl implements Constant
{
    FREAD(1), 
    FWRITE(2), 
    FAPPEND(8), 
    FASYNC(64), 
    FFSYNC(128), 
    FNONBLOCK(4), 
    FNDELAY(4), 
    F_DUPFD(0), 
    F_GETFD(1), 
    F_SETFD(2), 
    F_GETFL(3), 
    F_SETFL(4), 
    F_GETOWN(5), 
    F_SETOWN(6), 
    F_GETLK(7), 
    F_SETLK(8), 
    F_SETLKW(9), 
    F_CHKCLEAN(41), 
    F_PREALLOCATE(42), 
    F_SETSIZE(43), 
    F_RDADVISE(44), 
    F_RDAHEAD(45), 
    F_READBOOTSTRAP(46), 
    F_WRITEBOOTSTRAP(47), 
    F_NOCACHE(48), 
    F_LOG2PHYS(49), 
    F_GETPATH(50), 
    F_FULLFSYNC(51), 
    F_PATHPKG_CHECK(52), 
    F_FREEZE_FS(53), 
    F_THAW_FS(54), 
    F_GLOBAL_NOCACHE(55), 
    F_ADDSIGS(59), 
    F_MARKDEPENDENCY(60), 
    F_RDLCK(1), 
    F_UNLCK(2), 
    F_WRLCK(3), 
    F_ALLOCATECONTIG(2), 
    F_ALLOCATEALL(4);
    
    private final int value;
    public static final long MIN_VALUE = 0L;
    public static final long MAX_VALUE = 128L;
    
    private Fcntl(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
