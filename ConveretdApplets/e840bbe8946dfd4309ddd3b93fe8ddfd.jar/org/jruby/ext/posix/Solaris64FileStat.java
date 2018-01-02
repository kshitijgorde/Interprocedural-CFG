// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix;

import com.kenai.jaffl.struct.Struct;

public class Solaris64FileStat extends BaseHeapFileStat
{
    public static final int _ST_FSTYPSZ = 16;
    public final UnsignedLong st_dev;
    public final Int64 st_ino;
    public final Int32 st_mode;
    public final Int32 st_nlink;
    public final Int32 st_uid;
    public final Int32 st_gid;
    public final UnsignedLong st_rdev;
    public final Int64 st_size;
    public final SignedLong st_atim_sec;
    public final SignedLong st_atim_nsec;
    public final SignedLong st_mtim_sec;
    public final SignedLong st_mtim_nsec;
    public final SignedLong st_ctim_sec;
    public final SignedLong st_ctim_nsec;
    public final Int32 st_blksize;
    public final Int64 st_blocks;
    public final Signed8[] st_fstype;
    
    public Solaris64FileStat() {
        this(null);
    }
    
    public Solaris64FileStat(final POSIX posix) {
        super(posix);
        this.st_dev = new UnsignedLong(this);
        this.st_ino = new Int64(this);
        this.st_mode = new Int32(this);
        this.st_nlink = new Int32(this);
        this.st_uid = new Int32(this);
        this.st_gid = new Int32(this);
        this.st_rdev = new UnsignedLong(this);
        this.st_size = new Int64(this);
        this.st_atim_sec = new SignedLong(this);
        this.st_atim_nsec = new SignedLong(this);
        this.st_mtim_sec = new SignedLong(this);
        this.st_mtim_nsec = new SignedLong(this);
        this.st_ctim_sec = new SignedLong(this);
        this.st_ctim_nsec = new SignedLong(this);
        this.st_blksize = new Int32(this);
        this.st_blocks = new Int64(this);
        this.st_fstype = this.array(new Signed8[16]);
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
