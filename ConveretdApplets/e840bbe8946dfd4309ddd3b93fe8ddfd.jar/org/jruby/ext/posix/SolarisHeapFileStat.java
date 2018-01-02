// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix;

import com.kenai.jaffl.struct.Struct;

public class SolarisHeapFileStat extends BaseHeapFileStat
{
    public static final int _ST_FSTYPSZ = 16;
    public final Int32 st_dev;
    public final SignedLong[] st_pad1;
    public final Int64 st_ino;
    public final Int32 st_mode;
    public final Int32 st_nlink;
    public final Int32 st_uid;
    public final Int32 st_gid;
    public final Int32 st_rdev;
    public final SignedLong[] st_pad2;
    public final Int64 st_size;
    public final Int32 st_atim_sec;
    public final Int32 st_atim_nsec;
    public final Int32 st_mtim_sec;
    public final Int32 st_mtim_nsec;
    public final Int32 st_ctim_sec;
    public final Int32 st_ctim_nsec;
    public final Int32 st_blksize;
    public final Int64 st_blocks;
    public final Signed8[] st_fstype;
    public final SignedLong[] st_pad4;
    
    public SolarisHeapFileStat() {
        this(null);
    }
    
    public SolarisHeapFileStat(final POSIX posix) {
        super(posix);
        this.st_dev = new Int32(this);
        this.st_pad1 = this.array(new SignedLong[3]);
        this.st_ino = new Int64(this);
        this.st_mode = new Int32(this);
        this.st_nlink = new Int32(this);
        this.st_uid = new Int32(this);
        this.st_gid = new Int32(this);
        this.st_rdev = new Int32(this);
        this.st_pad2 = this.array(new SignedLong[2]);
        this.st_size = new Int64(this);
        this.st_atim_sec = new Int32(this);
        this.st_atim_nsec = new Int32(this);
        this.st_mtim_sec = new Int32(this);
        this.st_mtim_nsec = new Int32(this);
        this.st_ctim_sec = new Int32(this);
        this.st_ctim_nsec = new Int32(this);
        this.st_blksize = new Int32(this);
        this.st_blocks = new Int64(this);
        this.st_fstype = this.array(new Signed8[16]);
        this.st_pad4 = this.array(new SignedLong[8]);
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
