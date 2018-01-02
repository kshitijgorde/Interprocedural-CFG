// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix;

import com.kenai.jaffl.mapper.FromNativeConverter;
import com.kenai.jaffl.mapper.ToNativeContext;
import com.kenai.jaffl.Pointer;
import com.kenai.jaffl.mapper.FromNativeContext;
import com.kenai.jaffl.LastError;
import com.kenai.jaffl.struct.StructUtil;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.ByteBuffer;
import com.kenai.constantine.platform.Errno;
import java.io.FileDescriptor;
import com.kenai.jaffl.struct.Struct;
import com.kenai.jaffl.mapper.ToNativeConverter;

abstract class BaseNativePOSIX implements POSIX
{
    private final LibC libc;
    protected final String libraryName;
    protected final POSIXHandler handler;
    protected final JavaLibCHelper helper;
    public static final PointerConverter GROUP;
    public static final ToNativeConverter<FileStat, Struct> FileStatConverter;
    
    BaseNativePOSIX(final String libraryName, final LibCProvider libcProvider, final POSIXHandler handler) {
        this.handler = handler;
        this.libraryName = libraryName;
        this.libc = libcProvider.getLibC();
        this.helper = new JavaLibCHelper(handler);
    }
    
    public final LibC libc() {
        return this.libc;
    }
    
    public int chmod(final String filename, final int mode) {
        return this.libc().chmod(filename, mode);
    }
    
    public int chown(final String filename, final int user, final int group) {
        return this.libc().chown(filename, user, group);
    }
    
    public int exec(final String path, final String[] args) {
        this.handler.unimplementedError("exec unimplemented");
        return -1;
    }
    
    public int execv(final String path, final String[] args) {
        return this.libc().execv(path, (CharSequence[])args);
    }
    
    public FileStat fstat(final FileDescriptor fileDescriptor) {
        final FileStat stat = this.allocateStat();
        final int fd = this.helper.getfd(fileDescriptor);
        if (this.libc().fstat(fd, stat) < 0) {
            this.handler.error(Errno.ENOENT, "" + fd);
        }
        return stat;
    }
    
    public int getegid() {
        return this.libc().getegid();
    }
    
    public int geteuid() {
        return this.libc().geteuid();
    }
    
    public int getgid() {
        return this.libc().getgid();
    }
    
    public String getlogin() {
        return this.libc().getlogin();
    }
    
    public int getpgid() {
        return this.libc().getpgid();
    }
    
    public int getpgrp() {
        return this.libc().getpgrp();
    }
    
    public int getpid() {
        return this.libc().getpid();
    }
    
    public int getppid() {
        return this.libc().getppid();
    }
    
    public Passwd getpwent() {
        return this.libc().getpwent();
    }
    
    public Passwd getpwuid(final int which) {
        return this.libc().getpwuid(which);
    }
    
    public Passwd getpwnam(final String which) {
        return this.libc().getpwnam(which);
    }
    
    public Group getgrent() {
        return this.libc().getgrent();
    }
    
    public Group getgrgid(final int which) {
        return this.libc().getgrgid(which);
    }
    
    public Group getgrnam(final String which) {
        return this.libc().getgrnam(which);
    }
    
    public int setpwent() {
        return this.libc().setpwent();
    }
    
    public int endpwent() {
        return this.libc().endpwent();
    }
    
    public int setgrent() {
        return this.libc().setgrent();
    }
    
    public int endgrent() {
        return this.libc().endgrent();
    }
    
    public int getuid() {
        return this.libc().getuid();
    }
    
    public int setegid(final int egid) {
        return this.libc().setegid(egid);
    }
    
    public int seteuid(final int euid) {
        return this.libc().seteuid(euid);
    }
    
    public int setgid(final int gid) {
        return this.libc().setgid(gid);
    }
    
    public int getfd(final FileDescriptor descriptor) {
        return this.helper.getfd(descriptor);
    }
    
    public int getpgid(final int pid) {
        return this.libc().getpgid(pid);
    }
    
    public int setpgid(final int pid, final int pgid) {
        return this.libc().setpgid(pid, pgid);
    }
    
    public int setpgrp(final int pid, final int pgrp) {
        return this.libc().setpgrp(pid, pgrp);
    }
    
    public int setsid() {
        return this.libc().setsid();
    }
    
    public int setuid(final int uid) {
        return this.libc().setuid(uid);
    }
    
    public int kill(final int pid, final int signal) {
        return this.libc().kill(pid, signal);
    }
    
    public int lchmod(final String filename, final int mode) {
        return this.libc().lchmod(filename, mode);
    }
    
    public int lchown(final String filename, final int user, final int group) {
        return this.libc().lchown(filename, user, group);
    }
    
    public int link(final String oldpath, final String newpath) {
        return this.libc().link(oldpath, newpath);
    }
    
    public FileStat lstat(final String path) {
        final FileStat stat = this.allocateStat();
        if (this.libc().lstat(path, stat) < 0) {
            this.handler.error(Errno.ENOENT, path);
        }
        return stat;
    }
    
    public int mkdir(final String path, final int mode) {
        final int res = this.libc().mkdir(path, mode);
        if (res < 0) {
            final int errno = this.errno();
            this.handler.error(Errno.valueOf(errno), path);
        }
        return res;
    }
    
    public FileStat stat(final String path) {
        final FileStat stat = this.allocateStat();
        if (this.libc().stat(path, stat) < 0) {
            this.handler.error(Errno.ENOENT, path);
        }
        return stat;
    }
    
    public int symlink(final String oldpath, final String newpath) {
        return this.libc().symlink(oldpath, newpath);
    }
    
    public String readlink(final String oldpath) throws IOException {
        final ByteBuffer buffer = ByteBuffer.allocate(256);
        final int result = this.libc().readlink(oldpath, buffer, buffer.capacity());
        if (result == -1) {
            return null;
        }
        buffer.position(0);
        buffer.limit(result);
        return Charset.forName("ASCII").decode(buffer).toString();
    }
    
    public int umask(final int mask) {
        return this.libc().umask(mask);
    }
    
    public int utimes(final String path, final long[] atimeval, final long[] mtimeval) {
        Timeval[] times = null;
        if (atimeval != null && mtimeval != null) {
            times = StructUtil.newArray(DefaultNativeTimeval.class, 2);
            times[0].setTime(atimeval);
            times[1].setTime(mtimeval);
        }
        return this.libc().utimes(path, times);
    }
    
    public int fork() {
        return this.libc().fork();
    }
    
    public int waitpid(final int pid, final int[] status, final int flags) {
        return this.libc().waitpid(pid, status, flags);
    }
    
    public int wait(final int[] status) {
        return this.libc().wait(status);
    }
    
    public int getpriority(final int which, final int who) {
        return this.libc().getpriority(which, who);
    }
    
    public int setpriority(final int which, final int who, final int prio) {
        return this.libc().setpriority(which, who, prio);
    }
    
    public boolean isatty(final FileDescriptor fd) {
        return this.libc().isatty(this.helper.getfd(fd)) != 0;
    }
    
    public int errno() {
        return LastError.getLastError();
    }
    
    public void errno(final int value) {
        LastError.setLastError(value);
    }
    
    public int aspawn(final boolean overlay, final String program, final String[] argv, final String path) {
        this.handler.unimplementedError("aspawn only on windows platforms");
        return -1;
    }
    
    public int spawn(final boolean overlay, final String command, final String program, final String path) {
        this.handler.unimplementedError("spawn only on windows platforms");
        return -1;
    }
    
    public boolean isNative() {
        return true;
    }
    
    public abstract BaseHeapFileStat allocateStat();
    
    static {
        GROUP = new PointerConverter() {
            public Object fromNative(final Object arg, final FromNativeContext ctx) {
                return (arg != null) ? new DefaultNativeGroup((Pointer)arg) : null;
            }
        };
        FileStatConverter = new ToNativeConverter<FileStat, Struct>() {
            public Struct toNative(final FileStat value, final ToNativeContext context) {
                if (!(value instanceof Struct)) {
                    throw new IllegalArgumentException("FileStat instance is not a struct");
                }
                return (Struct)value;
            }
            
            public Class<Struct> nativeType() {
                return Struct.class;
            }
        };
    }
    
    public abstract static class PointerConverter implements FromNativeConverter
    {
        public Class nativeType() {
            return Pointer.class;
        }
    }
}
