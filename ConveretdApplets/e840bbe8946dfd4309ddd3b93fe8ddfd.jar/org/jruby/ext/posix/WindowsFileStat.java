// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix;

import com.kenai.jaffl.struct.Struct;

public class WindowsFileStat extends BaseHeapFileStat
{
    public final Signed32 st_dev;
    public final Signed16 st_ino;
    public final Signed16 st_mode;
    public final Signed16 st_nlink;
    public final Signed16 st_uid;
    public final Signed16 st_gid;
    public final Signed32 st_rdev;
    public final Signed64 st_size;
    public final Signed64 st_atime;
    public final Signed64 st_mtime;
    public final Signed64 st_ctime;
    
    public WindowsFileStat(final POSIX posix) {
        super(posix);
        this.st_dev = new Signed32(this);
        this.st_ino = new Signed16(this);
        this.st_mode = new Signed16(this);
        this.st_nlink = new Signed16(this);
        this.st_uid = new Signed16(this);
        this.st_gid = new Signed16(this);
        this.st_rdev = new Signed32(this);
        this.st_size = new Signed64(this);
        this.st_atime = new Signed64(this);
        this.st_mtime = new Signed64(this);
        this.st_ctime = new Signed64(this);
    }
    
    public long atime() {
        return this.st_atime.get();
    }
    
    public long blockSize() {
        return 512L;
    }
    
    public long blocks() {
        return (this.st_size.get() + 512L - 1L) / 512L;
    }
    
    public long ctime() {
        return this.st_ctime.get();
    }
    
    public long dev() {
        return this.st_dev.get();
    }
    
    public int gid() {
        return this.st_gid.get();
    }
    
    public long ino() {
        return this.st_ino.get();
    }
    
    public int mode() {
        return this.st_mode.get() & 0xFFFFFFED & 0xFFFF;
    }
    
    public long mtime() {
        return this.st_mtime.get();
    }
    
    public int nlink() {
        return this.st_nlink.get();
    }
    
    public long rdev() {
        return this.st_rdev.get();
    }
    
    public long st_size() {
        return this.st_size.get();
    }
    
    public int uid() {
        return this.st_uid.get();
    }
    
    public boolean groupMember(final int gid) {
        return true;
    }
    
    public boolean isExecutable() {
        if (this.isOwned()) {
            return (this.mode() & 0x40) != 0x0;
        }
        if (this.isGroupOwned()) {
            return (this.mode() & 0x8) != 0x0;
        }
        return (this.mode() & 0x1) == 0x0;
    }
    
    public boolean isExecutableReal() {
        if (this.isROwned()) {
            return (this.mode() & 0x40) != 0x0;
        }
        if (this.groupMember(this.gid())) {
            return (this.mode() & 0x8) != 0x0;
        }
        return (this.mode() & 0x1) == 0x0;
    }
    
    public boolean isOwned() {
        return true;
    }
    
    public boolean isROwned() {
        return true;
    }
    
    public boolean isReadable() {
        if (this.isOwned()) {
            return (this.mode() & 0x100) != 0x0;
        }
        if (this.isGroupOwned()) {
            return (this.mode() & 0x20) != 0x0;
        }
        return (this.mode() & 0x4) == 0x0;
    }
    
    public boolean isReadableReal() {
        if (this.isROwned()) {
            return (this.mode() & 0x100) != 0x0;
        }
        if (this.groupMember(this.gid())) {
            return (this.mode() & 0x20) != 0x0;
        }
        return (this.mode() & 0x4) == 0x0;
    }
    
    public boolean isWritable() {
        if (this.isOwned()) {
            return (this.mode() & 0x80) != 0x0;
        }
        if (this.isGroupOwned()) {
            return (this.mode() & 0x10) != 0x0;
        }
        return (this.mode() & 0x2) == 0x0;
    }
    
    public boolean isWritableReal() {
        if (this.isROwned()) {
            return (this.mode() & 0x80) != 0x0;
        }
        if (this.groupMember(this.gid())) {
            return (this.mode() & 0x10) != 0x0;
        }
        return (this.mode() & 0x2) == 0x0;
    }
    
    public java.lang.String toString() {
        return "st_dev: " + this.st_dev.get() + ", st_mode: " + Integer.toOctalString(this.mode()) + ", st_nlink: " + this.st_nlink.get() + ", st_rdev: " + this.st_rdev.get() + ", st_size: " + this.st_size.get() + ", st_uid: " + this.st_uid.get() + ", st_gid: " + this.st_gid.get() + ", st_atime: " + this.st_atime.get() + ", st_ctime: " + this.st_ctime.get() + ", st_mtime: " + this.st_mtime.get() + ", st_ino: " + this.st_ino.get();
    }
}
