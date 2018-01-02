// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix;

public final class LinuxHeapFileStat extends BaseHeapFileStat
{
    public final Int64 st_dev;
    public final Int16 __pad1;
    public final Int32 st_ino;
    public final Int32 st_mode;
    public final Int32 st_nlink;
    public final Int32 st_uid;
    public final Int32 st_gid;
    public final Int64 st_rdev;
    public final Int16 __pad2;
    public final Int64 st_size;
    public final Int32 st_blksize;
    public final Int32 st_blocks;
    public final Int32 __unused4;
    public final Int32 st_atim_sec;
    public final Int32 st_atim_nsec;
    public final Int32 st_mtim_sec;
    public final Int32 st_mtim_nsec;
    public final Int32 st_ctim_sec;
    public final Int32 st_ctim_nsec;
    public final Int64 __unused5;
    
    public LinuxHeapFileStat() {
        this(null);
    }
    
    public LinuxHeapFileStat(final POSIX posix) {
        super(posix);
        this.st_dev = new Int64(this);
        this.__pad1 = new Int16(this);
        this.st_ino = new Int32(this);
        this.st_mode = new Int32(this);
        this.st_nlink = new Int32(this);
        this.st_uid = new Int32(this);
        this.st_gid = new Int32(this);
        this.st_rdev = new Int64(this);
        this.__pad2 = new Int16(this);
        this.st_size = new Int64(this);
        this.st_blksize = new Int32(this);
        this.st_blocks = new Int32(this);
        this.__unused4 = new Int32(this);
        this.st_atim_sec = new Int32(this);
        this.st_atim_nsec = new Int32(this);
        this.st_mtim_sec = new Int32(this);
        this.st_mtim_nsec = new Int32(this);
        this.st_ctim_sec = new Int32(this);
        this.st_ctim_nsec = new Int32(this);
        this.__unused5 = new Int64(this);
    }
    
    public long atime() {
        return this.st_atim_sec.get();
    }
    
    public long blocks() {
        return this.st_blocks.get();
    }
    
    public long blockSize() {
        return this.st_blksize.get();
    }
    
    public long ctime() {
        return this.st_ctim_sec.get();
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
        return this.st_mode.get() & 0xFFFF;
    }
    
    public long mtime() {
        return this.st_mtim_sec.get();
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
}
