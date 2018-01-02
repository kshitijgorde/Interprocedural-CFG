// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix;

public final class Linux64HeapFileStat extends BaseHeapFileStat
{
    public final Int64 st_dev;
    public final Int64 st_ino;
    public final Int64 st_nlink;
    public final Int32 st_mode;
    public final Int32 st_uid;
    public final Int32 st_gid;
    public final Int64 st_rdev;
    public final Int64 st_size;
    public final Int64 st_blksize;
    public final Int64 st_blocks;
    public final Int64 st_atime;
    public final Int64 st_atimensec;
    public final Int64 st_mtime;
    public final Int64 st_mtimensec;
    public final Int64 st_ctime;
    public final Int64 st_ctimensec;
    public final Int64 __unused4;
    public final Int64 __unused5;
    public final Int64 __unused6;
    
    public Linux64HeapFileStat() {
        super(null);
        this.st_dev = new Int64(this);
        this.st_ino = new Int64(this);
        this.st_nlink = new Int64(this);
        this.st_mode = new Int32(this);
        this.st_uid = new Int32(this);
        this.st_gid = new Int32(this);
        this.st_rdev = new Int64(this);
        this.st_size = new Int64(this);
        this.st_blksize = new Int64(this);
        this.st_blocks = new Int64(this);
        this.st_atime = new Int64(this);
        this.st_atimensec = new Int64(this);
        this.st_mtime = new Int64(this);
        this.st_mtimensec = new Int64(this);
        this.st_ctime = new Int64(this);
        this.st_ctimensec = new Int64(this);
        this.__unused4 = new Int64(this);
        this.__unused5 = new Int64(this);
        this.__unused6 = new Int64(this);
    }
    
    public Linux64HeapFileStat(final POSIX posix) {
        super(posix);
        this.st_dev = new Int64(this);
        this.st_ino = new Int64(this);
        this.st_nlink = new Int64(this);
        this.st_mode = new Int32(this);
        this.st_uid = new Int32(this);
        this.st_gid = new Int32(this);
        this.st_rdev = new Int64(this);
        this.st_size = new Int64(this);
        this.st_blksize = new Int64(this);
        this.st_blocks = new Int64(this);
        this.st_atime = new Int64(this);
        this.st_atimensec = new Int64(this);
        this.st_mtime = new Int64(this);
        this.st_mtimensec = new Int64(this);
        this.st_ctime = new Int64(this);
        this.st_ctimensec = new Int64(this);
        this.__unused4 = new Int64(this);
        this.__unused5 = new Int64(this);
        this.__unused6 = new Int64(this);
    }
    
    public long atime() {
        return this.st_atime.get();
    }
    
    public long blockSize() {
        return this.st_blksize.get();
    }
    
    public long blocks() {
        return this.st_blocks.get();
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
        return this.st_mode.get();
    }
    
    public long mtime() {
        return this.st_mtime.get();
    }
    
    public int nlink() {
        return (int)this.st_nlink.get();
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
