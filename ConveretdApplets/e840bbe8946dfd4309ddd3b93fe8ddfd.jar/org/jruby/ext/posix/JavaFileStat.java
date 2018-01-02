// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix;

import java.io.IOException;
import java.io.File;

public final class JavaFileStat implements FileStat
{
    private final POSIXHandler handler;
    private final POSIX posix;
    short st_mode;
    int st_blksize;
    long st_size;
    int st_ctime;
    int st_mtime;
    
    public JavaFileStat(final POSIX posix, final POSIXHandler handler) {
        this.handler = handler;
        this.posix = posix;
    }
    
    public void setup(final String path) {
        final File file = new JavaSecuredFile(path);
        this.st_blksize = 4096;
        this.st_mode = this.calculateMode(file, this.st_mode);
        this.st_size = file.length();
        this.st_mtime = (int)(file.lastModified() / 1000L);
        if (file.getParentFile() != null) {
            this.st_ctime = (int)(file.getParentFile().lastModified() / 1000L);
        }
        else {
            this.st_ctime = this.st_mtime;
        }
    }
    
    private short calculateMode(final File file, short st_mode) {
        if (file.canRead()) {
            st_mode |= 0x124;
        }
        if (file.canWrite()) {
            st_mode |= 0x92;
            st_mode &= 0xFFFFFFED;
        }
        if (file.isDirectory()) {
            st_mode |= 0x4000;
        }
        else if (file.isFile()) {
            st_mode |= (short)32768;
        }
        try {
            st_mode = this.calculateSymlink(file, st_mode);
        }
        catch (IOException ex) {}
        return st_mode;
    }
    
    private short calculateSymlink(File file, short st_mode) throws IOException {
        if (file.getAbsoluteFile().getParentFile() == null) {
            return st_mode;
        }
        final File absoluteParent = file.getAbsoluteFile().getParentFile();
        final File canonicalParent = absoluteParent.getCanonicalFile();
        if (canonicalParent.getAbsolutePath().equals(absoluteParent.getAbsolutePath()) && !file.getAbsolutePath().equalsIgnoreCase(file.getCanonicalPath())) {
            st_mode |= (short)40960;
            return st_mode;
        }
        file = new JavaSecuredFile(canonicalParent.getAbsolutePath() + "/" + file.getName());
        if (!file.getAbsolutePath().equalsIgnoreCase(file.getCanonicalPath())) {
            st_mode |= (short)40960;
        }
        return st_mode;
    }
    
    public long atime() {
        return this.st_mtime;
    }
    
    public long blocks() {
        this.handler.unimplementedError("stat.st_blocks");
        return -1L;
    }
    
    public long blockSize() {
        return this.st_blksize;
    }
    
    public long ctime() {
        return this.st_ctime;
    }
    
    public long dev() {
        this.handler.unimplementedError("stat.st_dev");
        return -1L;
    }
    
    public String ftype() {
        if (this.isFile()) {
            return "file";
        }
        if (this.isDirectory()) {
            return "directory";
        }
        return "unknown";
    }
    
    public int gid() {
        this.handler.unimplementedError("stat.st_gid");
        return -1;
    }
    
    public boolean groupMember(final int gid) {
        return this.posix.getgid() == gid || this.posix.getegid() == gid;
    }
    
    public long ino() {
        return 0L;
    }
    
    public boolean isBlockDev() {
        this.handler.unimplementedError("block device detection");
        return false;
    }
    
    public boolean isCharDev() {
        return false;
    }
    
    public boolean isDirectory() {
        return (this.mode() & 0x4000) != 0x0;
    }
    
    public boolean isEmpty() {
        return this.st_size() == 0L;
    }
    
    public boolean isExecutable() {
        this.handler.warn(POSIXHandler.WARNING_ID.DUMMY_VALUE_USED, "executable? does not in this environment and will return a dummy value", "executable");
        return true;
    }
    
    public boolean isExecutableReal() {
        this.handler.warn(POSIXHandler.WARNING_ID.DUMMY_VALUE_USED, "executable_real? does not work in this environmnt and will return a dummy value", "executable_real");
        return true;
    }
    
    public boolean isFifo() {
        this.handler.unimplementedError("fifo file detection");
        return false;
    }
    
    public boolean isFile() {
        return (this.mode() & 0x8000) != 0x0;
    }
    
    public boolean isGroupOwned() {
        return this.groupMember(this.gid());
    }
    
    public boolean isIdentical(final FileStat other) {
        this.handler.unimplementedError("identical file detection");
        return false;
    }
    
    public boolean isNamedPipe() {
        this.handler.unimplementedError("piped file detection");
        return false;
    }
    
    public boolean isOwned() {
        return this.posix.geteuid() == this.uid();
    }
    
    public boolean isROwned() {
        return this.posix.getuid() == this.uid();
    }
    
    public boolean isReadable() {
        final int mode = this.mode();
        return (mode & 0x100) != 0x0 || (mode & 0x20) != 0x0 || (mode & 0x4) != 0x0;
    }
    
    public boolean isReadableReal() {
        return this.isReadable();
    }
    
    public boolean isSymlink() {
        return (this.mode() & 0xA000) == 0xA000;
    }
    
    public boolean isWritable() {
        final int mode = this.mode();
        return (mode & 0x80) != 0x0 || (mode & 0x10) != 0x0 || (mode & 0x2) != 0x0;
    }
    
    public boolean isWritableReal() {
        return this.isWritable();
    }
    
    public boolean isSetgid() {
        this.handler.unimplementedError("setgid detection");
        return false;
    }
    
    public boolean isSetuid() {
        this.handler.unimplementedError("setuid detection");
        return false;
    }
    
    public boolean isSocket() {
        this.handler.unimplementedError("socket file type detection");
        return false;
    }
    
    public boolean isSticky() {
        this.handler.unimplementedError("sticky bit detection");
        return false;
    }
    
    public int major(final long dev) {
        this.handler.unimplementedError("major device");
        return -1;
    }
    
    public int minor(final long dev) {
        this.handler.unimplementedError("minor device");
        return -1;
    }
    
    public int mode() {
        return this.st_mode & 0xFFFF;
    }
    
    public long mtime() {
        return this.st_mtime;
    }
    
    public int nlink() {
        this.handler.unimplementedError("stat.nlink");
        return -1;
    }
    
    public long rdev() {
        this.handler.unimplementedError("stat.rdev");
        return -1L;
    }
    
    public long st_size() {
        return this.st_size;
    }
    
    public int uid() {
        return -1;
    }
}
