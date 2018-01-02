// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.util.io;

import com.kenai.constantine.platform.OpenFlags;

public class ModeFlags implements Cloneable
{
    public static final int RDONLY;
    public static final int WRONLY;
    public static final int RDWR;
    public static final int CREAT;
    public static final int EXCL;
    public static final int TRUNC;
    public static final int APPEND;
    public static final int NONBLOCK;
    public static final int BINARY;
    public static final int ACCMODE;
    private final int flags;
    
    public ModeFlags() {
        this.flags = ModeFlags.RDONLY;
    }
    
    public ModeFlags(final long flags) throws InvalidValueException {
        this.flags = (int)flags;
        if (this.isReadOnly() && (flags & ModeFlags.APPEND) != 0x0L) {
            throw new InvalidValueException();
        }
    }
    
    public String toJavaModeString() {
        if (this.isWritable() || this.isCreate() || this.isTruncate()) {
            return "rw";
        }
        return "r";
    }
    
    public boolean isReadOnly() {
        return (this.flags & ModeFlags.WRONLY) == 0x0 && (this.flags & ModeFlags.RDWR) == 0x0;
    }
    
    public boolean isReadable() {
        return (this.flags & ModeFlags.RDWR) != 0x0 || this.isReadOnly();
    }
    
    public boolean isBinary() {
        return (this.flags & ModeFlags.BINARY) != 0x0;
    }
    
    public boolean isCreate() {
        return (this.flags & ModeFlags.CREAT) != 0x0;
    }
    
    public boolean isWritable() {
        return (this.flags & ModeFlags.RDWR) != 0x0 || (this.flags & ModeFlags.WRONLY) != 0x0;
    }
    
    public boolean isExclusive() {
        return (this.flags & ModeFlags.EXCL) != 0x0;
    }
    
    public boolean isAppendable() {
        return (this.flags & ModeFlags.APPEND) != 0x0;
    }
    
    public boolean isTruncate() {
        return (this.flags & ModeFlags.TRUNC) != 0x0;
    }
    
    public boolean isSubsetOf(final ModeFlags superset) {
        return (superset.isReadable() || !this.isReadable()) && (superset.isWritable() || !this.isWritable()) && (superset.isAppendable() || !this.isAppendable());
    }
    
    public String toString() {
        return "" + this.flags;
    }
    
    public int getOpenFileFlags() {
        int openFileFlags = 0;
        final int readWrite = this.flags & 0x3;
        if (readWrite == ModeFlags.RDONLY) {
            openFileFlags = 1;
        }
        else if (readWrite == ModeFlags.WRONLY) {
            openFileFlags = 2;
        }
        else {
            openFileFlags = 3;
        }
        if ((this.flags & ModeFlags.APPEND) != 0x0) {
            openFileFlags |= 0x40;
        }
        if ((this.flags & ModeFlags.CREAT) != 0x0) {
            openFileFlags |= 0x80;
        }
        if ((this.flags & ModeFlags.BINARY) == ModeFlags.BINARY) {
            openFileFlags |= 0x4;
        }
        return openFileFlags;
    }
    
    static {
        RDONLY = OpenFlags.O_RDONLY.value();
        WRONLY = OpenFlags.O_WRONLY.value();
        RDWR = OpenFlags.O_RDWR.value();
        CREAT = OpenFlags.O_CREAT.value();
        EXCL = OpenFlags.O_EXCL.value();
        TRUNC = OpenFlags.O_TRUNC.value();
        APPEND = OpenFlags.O_APPEND.value();
        NONBLOCK = OpenFlags.O_NONBLOCK.value();
        BINARY = OpenFlags.O_BINARY.value();
        ACCMODE = (ModeFlags.RDWR | ModeFlags.WRONLY | ModeFlags.RDONLY);
    }
}
