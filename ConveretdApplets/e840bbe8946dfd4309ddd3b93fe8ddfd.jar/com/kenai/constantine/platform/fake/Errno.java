// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.fake;

import com.kenai.constantine.Constant;

public enum Errno implements Constant
{
    EPERM(1), 
    ENOENT(2), 
    ESRCH(3), 
    EINTR(4), 
    EIO(5), 
    ENXIO(6), 
    E2BIG(7), 
    ENOEXEC(8), 
    EBADF(9), 
    ECHILD(10), 
    EDEADLK(11), 
    ENOMEM(12), 
    EACCES(13), 
    EFAULT(14), 
    ENOTBLK(15), 
    EBUSY(16), 
    EEXIST(17), 
    EXDEV(18), 
    ENODEV(19), 
    ENOTDIR(20), 
    EISDIR(21), 
    EINVAL(22), 
    ENFILE(23), 
    EMFILE(24), 
    ENOTTY(25), 
    ETXTBSY(26), 
    EFBIG(27), 
    ENOSPC(28), 
    ESPIPE(29), 
    EROFS(30), 
    EMLINK(31), 
    EPIPE(32), 
    EDOM(33), 
    ERANGE(34), 
    EWOULDBLOCK(35), 
    EAGAIN(36), 
    EINPROGRESS(37), 
    EALREADY(38), 
    ENOTSOCK(39), 
    EDESTADDRREQ(40), 
    EMSGSIZE(41), 
    EPROTOTYPE(42), 
    ENOPROTOOPT(43), 
    EPROTONOSUPPORT(44), 
    ESOCKTNOSUPPORT(45), 
    EOPNOTSUPP(46), 
    EPFNOSUPPORT(47), 
    EAFNOSUPPORT(48), 
    EADDRINUSE(49), 
    EADDRNOTAVAIL(50), 
    ENETDOWN(51), 
    ENETUNREACH(52), 
    ENETRESET(53), 
    ECONNABORTED(54), 
    ECONNRESET(55), 
    ENOBUFS(56), 
    EISCONN(57), 
    ENOTCONN(58), 
    ESHUTDOWN(59), 
    ETOOMANYREFS(60), 
    ETIMEDOUT(61), 
    ECONNREFUSED(62), 
    ELOOP(63), 
    ENAMETOOLONG(64), 
    EHOSTDOWN(65), 
    EHOSTUNREACH(66), 
    ENOTEMPTY(67), 
    EUSERS(68), 
    EDQUOT(69), 
    ESTALE(70), 
    EREMOTE(71), 
    ENOLCK(72), 
    ENOSYS(73), 
    EOVERFLOW(74), 
    EIDRM(75), 
    ENOMSG(76), 
    EILSEQ(77), 
    EBADMSG(78), 
    EMULTIHOP(79), 
    ENODATA(80), 
    ENOLINK(81), 
    ENOSR(82), 
    ENOSTR(83), 
    EPROTO(84), 
    ETIME(85);
    
    private final int value;
    public static final long MIN_VALUE = 1L;
    public static final long MAX_VALUE = 85L;
    
    private Errno(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
