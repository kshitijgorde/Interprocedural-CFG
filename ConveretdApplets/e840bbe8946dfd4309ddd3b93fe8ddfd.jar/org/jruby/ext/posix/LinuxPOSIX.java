// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix;

import com.kenai.jaffl.Pointer;
import com.kenai.jaffl.mapper.FromNativeContext;
import com.kenai.constantine.platform.Errno;
import java.io.FileDescriptor;
import org.jruby.ext.posix.util.Platform;

final class LinuxPOSIX extends BaseNativePOSIX
{
    private volatile boolean use_fxstat64;
    private volatile boolean use_lxstat64;
    private volatile boolean use_xstat64;
    private final int statVersion;
    public static final PointerConverter PASSWD;
    
    LinuxPOSIX(final String libraryName, final LibCProvider libcProvider, final POSIXHandler handler) {
        super(libraryName, libcProvider, handler);
        this.use_fxstat64 = true;
        this.use_lxstat64 = true;
        this.use_xstat64 = true;
        this.statVersion = (Platform.IS_32_BIT ? 3 : 0);
    }
    
    public BaseHeapFileStat allocateStat() {
        if (Platform.IS_32_BIT) {
            return new LinuxHeapFileStat(this);
        }
        return new Linux64HeapFileStat(this);
    }
    
    private final FileStat old_fstat(final FileDescriptor fileDescriptor) {
        try {
            return super.fstat(fileDescriptor);
        }
        catch (UnsatisfiedLinkError ex2) {
            this.handler.unimplementedError("fstat");
            return null;
        }
    }
    
    public FileStat fstat(final FileDescriptor fileDescriptor) {
        if (this.use_fxstat64) {
            try {
                final FileStat stat = this.allocateStat();
                final int fd = this.helper.getfd(fileDescriptor);
                if (((LinuxLibC)this.libc()).__fxstat64(this.statVersion, fd, stat) < 0) {
                    this.handler.error(Errno.ENOENT, "" + fd);
                }
                return stat;
            }
            catch (UnsatisfiedLinkError ex) {
                this.use_fxstat64 = false;
                return this.old_fstat(fileDescriptor);
            }
        }
        return this.old_fstat(fileDescriptor);
    }
    
    private final FileStat old_lstat(final String path) {
        try {
            return super.lstat(path);
        }
        catch (UnsatisfiedLinkError ex) {
            this.handler.unimplementedError("lstat");
            return null;
        }
    }
    
    public FileStat lstat(final String path) {
        if (this.use_lxstat64) {
            try {
                final FileStat stat = this.allocateStat();
                if (((LinuxLibC)this.libc()).__lxstat64(this.statVersion, path, stat) < 0) {
                    this.handler.error(Errno.ENOENT, path);
                }
                return stat;
            }
            catch (UnsatisfiedLinkError ex) {
                this.use_lxstat64 = false;
                return this.old_lstat(path);
            }
        }
        return this.old_lstat(path);
    }
    
    private final FileStat old_stat(final String path) {
        try {
            return super.stat(path);
        }
        catch (UnsatisfiedLinkError ex) {
            this.handler.unimplementedError("stat");
            return null;
        }
    }
    
    public FileStat stat(final String path) {
        if (this.use_xstat64) {
            try {
                final FileStat stat = this.allocateStat();
                if (((LinuxLibC)this.libc()).__xstat64(this.statVersion, path, stat) < 0) {
                    this.handler.error(Errno.ENOENT, path);
                }
                return stat;
            }
            catch (UnsatisfiedLinkError ex) {
                this.use_xstat64 = false;
                return this.old_stat(path);
            }
        }
        return this.old_stat(path);
    }
    
    static {
        PASSWD = new PointerConverter() {
            public Object fromNative(final Object arg, final FromNativeContext ctx) {
                return (arg != null) ? new LinuxPasswd((Pointer)arg) : null;
            }
        };
    }
}
