// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix;

public interface FileStat
{
    public static final int S_IFIFO = 4096;
    public static final int S_IFCHR = 8192;
    public static final int S_IFDIR = 16384;
    public static final int S_IFBLK = 24576;
    public static final int S_IFREG = 32768;
    public static final int S_IFLNK = 40960;
    public static final int S_IFSOCK = 49152;
    public static final int S_IFMT = 61440;
    public static final int S_ISUID = 2048;
    public static final int S_ISGID = 1024;
    public static final int S_ISVTX = 512;
    public static final int S_IRUSR = 256;
    public static final int S_IWUSR = 128;
    public static final int S_IXUSR = 64;
    public static final int S_IRGRP = 32;
    public static final int S_IWGRP = 16;
    public static final int S_IXGRP = 8;
    public static final int S_IROTH = 4;
    public static final int S_IWOTH = 2;
    public static final int S_IXOTH = 1;
    public static final int ALL_READ = 292;
    public static final int ALL_WRITE = 146;
    public static final int S_IXUGO = 73;
    
    long atime();
    
    long blocks();
    
    long blockSize();
    
    long ctime();
    
    long dev();
    
    String ftype();
    
    int gid();
    
    boolean groupMember(final int p0);
    
    long ino();
    
    boolean isBlockDev();
    
    boolean isCharDev();
    
    boolean isDirectory();
    
    boolean isEmpty();
    
    boolean isExecutable();
    
    boolean isExecutableReal();
    
    boolean isFifo();
    
    boolean isFile();
    
    boolean isGroupOwned();
    
    boolean isIdentical(final FileStat p0);
    
    boolean isNamedPipe();
    
    boolean isOwned();
    
    boolean isROwned();
    
    boolean isReadable();
    
    boolean isReadableReal();
    
    boolean isWritable();
    
    boolean isWritableReal();
    
    boolean isSetgid();
    
    boolean isSetuid();
    
    boolean isSocket();
    
    boolean isSticky();
    
    boolean isSymlink();
    
    int major(final long p0);
    
    int minor(final long p0);
    
    int mode();
    
    long mtime();
    
    int nlink();
    
    long rdev();
    
    long st_size();
    
    int uid();
}
