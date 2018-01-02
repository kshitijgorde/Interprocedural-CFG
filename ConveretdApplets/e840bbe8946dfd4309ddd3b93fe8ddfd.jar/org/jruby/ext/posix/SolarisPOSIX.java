// 
// Decompiled by Procyon v0.5.30
// 

package org.jruby.ext.posix;

import com.kenai.jaffl.Pointer;
import com.kenai.jaffl.mapper.FromNativeContext;
import com.kenai.constantine.platform.Errno;
import java.io.FileDescriptor;
import org.jruby.ext.posix.util.Platform;

final class SolarisPOSIX extends BaseNativePOSIX
{
    public static final PointerConverter PASSWD;
    
    SolarisPOSIX(final String libraryName, final LibCProvider libc, final POSIXHandler handler) {
        super(libraryName, libc, handler);
    }
    
    public BaseHeapFileStat allocateStat() {
        return Platform.IS_32_BIT ? new SolarisHeapFileStat(this) : new Solaris64FileStat(this);
    }
    
    public FileStat fstat(final FileDescriptor fileDescriptor) {
        final FileStat stat = this.allocateStat();
        final int fd = this.helper.getfd(fileDescriptor);
        if ((Platform.IS_64_BIT ? this.libc().fstat(fd, stat) : this.libc().fstat64(fd, stat)) < 0) {
            this.handler.error(Errno.ENOENT, "" + fd);
        }
        return stat;
    }
    
    public int lchmod(final String filename, final int mode) {
        this.handler.unimplementedError("lchmod");
        return -1;
    }
    
    public FileStat lstat(final String path) {
        final FileStat stat = this.allocateStat();
        if ((Platform.IS_64_BIT ? this.libc().lstat(path, stat) : this.libc().lstat64(path, stat)) < 0) {
            this.handler.error(Errno.ENOENT, path);
        }
        return stat;
    }
    
    public FileStat stat(final String path) {
        final FileStat stat = this.allocateStat();
        if ((Platform.IS_64_BIT ? this.libc().stat(path, stat) : this.libc().stat64(path, stat)) < 0) {
            this.handler.error(Errno.ENOENT, path);
        }
        return stat;
    }
    
    static {
        PASSWD = new PointerConverter() {
            public Object fromNative(final Object arg, final FromNativeContext ctx) {
                return (arg != null) ? new SolarisPasswd((Pointer)arg) : null;
            }
        };
    }
}
