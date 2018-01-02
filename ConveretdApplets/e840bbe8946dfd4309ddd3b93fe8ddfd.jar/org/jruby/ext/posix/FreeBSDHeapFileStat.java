// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix;

public final class FreeBSDHeapFileStat extends BaseHeapFileStat
{
    public final dev_t st_dev;
    public final Int32 st_ino;
    public final Int16 st_mode;
    public final Int16 st_nlink;
    public final Int32 st_uid;
    public final Int32 st_gid;
    public final dev_t st_rdev;
    public final time_t st_atime;
    public final Long st_atimensec;
    public final time_t st_mtime;
    public final Long st_mtimensec;
    public final time_t st_ctime;
    public final Long st_ctimensec;
    public final Int64 st_size;
    public final Int64 st_blocks;
    public final Int32 st_blksize;
    public final Int32 st_flags;
    public final Int32 st_gen;
    public final Int32 st_lspare;
    public final time_t st_birthtime;
    public final Long st_birthtimensec;
    public final Int64 st_qspare0;
    
    public FreeBSDHeapFileStat() {
        this(null);
    }
    
    public FreeBSDHeapFileStat(final POSIX posix) {
        super(posix);
        this.st_dev = new dev_t();
        this.st_ino = new Int32(this);
        this.st_mode = new Int16(this);
        this.st_nlink = new Int16(this);
        this.st_uid = new Int32(this);
        this.st_gid = new Int32(this);
        this.st_rdev = new dev_t();
        this.st_atime = new time_t();
        this.st_atimensec = new Long(this);
        this.st_mtime = new time_t();
        this.st_mtimensec = new Long(this);
        this.st_ctime = new time_t();
        this.st_ctimensec = new Long(this);
        this.st_size = new Int64(this);
        this.st_blocks = new Int64(this);
        this.st_blksize = new Int32(this);
        this.st_flags = new Int32(this);
        this.st_gen = new Int32(this);
        this.st_lspare = new Int32(this);
        this.st_birthtime = new time_t();
        this.st_birthtimensec = new Long(this);
        this.st_qspare0 = new Int64(this);
    }
    
    public long atime() {
        return this.st_atime.get();
    }
    
    public long blocks() {
        return this.st_blocks.get();
    }
    
    public long blockSize() {
        return this.st_blksize.get();
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
        return this.st_mode.get() & 0xFFFF;
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
    
    public final class time_t extends Long
    {
    }
    
    public final class dev_t extends Int32
    {
    }
}
