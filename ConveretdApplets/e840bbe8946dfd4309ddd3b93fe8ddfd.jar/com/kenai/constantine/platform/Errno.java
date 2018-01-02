// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform;

import com.kenai.constantine.Constant;

public enum Errno implements Constant
{
    EPERM, 
    ENOENT, 
    ESRCH, 
    EINTR, 
    EIO, 
    ENXIO, 
    E2BIG, 
    ENOEXEC, 
    EBADF, 
    ECHILD, 
    EDEADLK, 
    ENOMEM, 
    EACCES, 
    EFAULT, 
    ENOTBLK, 
    EBUSY, 
    EEXIST, 
    EXDEV, 
    ENODEV, 
    ENOTDIR, 
    EISDIR, 
    EINVAL, 
    ENFILE, 
    EMFILE, 
    ENOTTY, 
    ETXTBSY, 
    EFBIG, 
    ENOSPC, 
    ESPIPE, 
    EROFS, 
    EMLINK, 
    EPIPE, 
    EDOM, 
    ERANGE, 
    EWOULDBLOCK, 
    EAGAIN, 
    EINPROGRESS, 
    EALREADY, 
    ENOTSOCK, 
    EDESTADDRREQ, 
    EMSGSIZE, 
    EPROTOTYPE, 
    ENOPROTOOPT, 
    EPROTONOSUPPORT, 
    ESOCKTNOSUPPORT, 
    EOPNOTSUPP, 
    EPFNOSUPPORT, 
    EAFNOSUPPORT, 
    EADDRINUSE, 
    EADDRNOTAVAIL, 
    ENETDOWN, 
    ENETUNREACH, 
    ENETRESET, 
    ECONNABORTED, 
    ECONNRESET, 
    ENOBUFS, 
    EISCONN, 
    ENOTCONN, 
    ESHUTDOWN, 
    ETOOMANYREFS, 
    ETIMEDOUT, 
    ECONNREFUSED, 
    ELOOP, 
    ENAMETOOLONG, 
    EHOSTDOWN, 
    EHOSTUNREACH, 
    ENOTEMPTY, 
    EUSERS, 
    EDQUOT, 
    ESTALE, 
    EREMOTE, 
    ENOLCK, 
    ENOSYS, 
    EOVERFLOW, 
    EIDRM, 
    ENOMSG, 
    EILSEQ, 
    EBADMSG, 
    EMULTIHOP, 
    ENODATA, 
    ENOLINK, 
    ENOSR, 
    ENOSTR, 
    EPROTO, 
    ETIME, 
    __UNKNOWN_CONSTANT__;
    
    private static final ConstantResolver<Errno> resolver;
    
    public final int value() {
        return Errno.resolver.intValue(this);
    }
    
    public final String description() {
        return Errno.resolver.description(this);
    }
    
    public final String toString() {
        return this.description();
    }
    
    public static final Errno valueOf(final int value) {
        return Errno.resolver.valueOf(value);
    }
    
    static {
        resolver = ConstantResolver.getResolver(Errno.class, 20000, 20999);
    }
}
