// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix;

public abstract class BaseHeapFileStat extends HeapStruct implements FileStat
{
    protected final POSIX posix;
    
    public BaseHeapFileStat(final POSIX posix) {
        this.posix = posix;
    }
    
    public java.lang.String ftype() {
        if (this.isFile()) {
            return "file";
        }
        if (this.isDirectory()) {
            return "directory";
        }
        if (this.isCharDev()) {
            return "characterSpecial";
        }
        if (this.isBlockDev()) {
            return "blockSpecial";
        }
        if (this.isFifo()) {
            return "fifo";
        }
        if (this.isSymlink()) {
            return "link";
        }
        if (this.isSocket()) {
            return "socket";
        }
        return "unknown";
    }
    
    public boolean groupMember(final int gid) {
        return this.posix.getgid() == gid || this.posix.getegid() == gid;
    }
    
    public boolean isBlockDev() {
        return (this.mode() & 0xF000) == 0x6000;
    }
    
    public boolean isCharDev() {
        return (this.mode() & 0xF000) == 0x2000;
    }
    
    public boolean isDirectory() {
        return (this.mode() & 0xF000) == 0x4000;
    }
    
    public boolean isEmpty() {
        return this.st_size() == 0L;
    }
    
    public boolean isExecutable() {
        if (this.posix.geteuid() == 0) {
            return (this.mode() & 0x49) != 0x0;
        }
        if (this.isOwned()) {
            return (this.mode() & 0x40) != 0x0;
        }
        if (this.isGroupOwned()) {
            return (this.mode() & 0x8) != 0x0;
        }
        return (this.mode() & 0x1) != 0x0;
    }
    
    public boolean isExecutableReal() {
        if (this.posix.getuid() == 0) {
            return (this.mode() & 0x49) != 0x0;
        }
        if (this.isROwned()) {
            return (this.mode() & 0x40) != 0x0;
        }
        if (this.groupMember(this.gid())) {
            return (this.mode() & 0x8) != 0x0;
        }
        return (this.mode() & 0x1) != 0x0;
    }
    
    public boolean isFile() {
        return (this.mode() & 0xF000) == 0x8000;
    }
    
    public boolean isFifo() {
        return (this.mode() & 0xF000) == 0x1000;
    }
    
    public boolean isGroupOwned() {
        return this.groupMember(this.gid());
    }
    
    public boolean isIdentical(final FileStat other) {
        return this.dev() == other.dev() && this.ino() == other.ino();
    }
    
    public boolean isNamedPipe() {
        return (this.mode() & 0x1000) != 0x0;
    }
    
    public boolean isOwned() {
        return this.posix.geteuid() == this.uid();
    }
    
    public boolean isROwned() {
        return this.posix.getuid() == this.uid();
    }
    
    public boolean isReadable() {
        if (this.posix.geteuid() == 0) {
            return true;
        }
        if (this.isOwned()) {
            return (this.mode() & 0x100) != 0x0;
        }
        if (this.isGroupOwned()) {
            return (this.mode() & 0x20) != 0x0;
        }
        return (this.mode() & 0x4) != 0x0;
    }
    
    public boolean isReadableReal() {
        if (this.posix.getuid() == 0) {
            return true;
        }
        if (this.isROwned()) {
            return (this.mode() & 0x100) != 0x0;
        }
        if (this.groupMember(this.gid())) {
            return (this.mode() & 0x20) != 0x0;
        }
        return (this.mode() & 0x4) != 0x0;
    }
    
    public boolean isSetgid() {
        return (this.mode() & 0x400) != 0x0;
    }
    
    public boolean isSetuid() {
        return (this.mode() & 0x800) != 0x0;
    }
    
    public boolean isSocket() {
        return (this.mode() & 0xF000) == 0xC000;
    }
    
    public boolean isSticky() {
        return (this.mode() & 0x200) != 0x0;
    }
    
    public boolean isSymlink() {
        return (this.mode() & 0xF000) == 0xA000;
    }
    
    public boolean isWritable() {
        if (this.posix.geteuid() == 0) {
            return true;
        }
        if (this.isOwned()) {
            return (this.mode() & 0x80) != 0x0;
        }
        if (this.isGroupOwned()) {
            return (this.mode() & 0x10) != 0x0;
        }
        return (this.mode() & 0x2) != 0x0;
    }
    
    public boolean isWritableReal() {
        if (this.posix.getuid() == 0) {
            return true;
        }
        if (this.isROwned()) {
            return (this.mode() & 0x80) != 0x0;
        }
        if (this.groupMember(this.gid())) {
            return (this.mode() & 0x10) != 0x0;
        }
        return (this.mode() & 0x2) != 0x0;
    }
    
    public int major(final long dev) {
        return (int)(dev >> 24) & 0xFF;
    }
    
    public int minor(final long dev) {
        return (int)(dev & 0xFFFFFFL);
    }
}
