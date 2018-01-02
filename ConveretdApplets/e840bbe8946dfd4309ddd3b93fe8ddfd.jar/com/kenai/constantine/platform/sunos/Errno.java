// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.sunos;

import java.util.EnumMap;
import java.util.Map;
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
    EDEADLK(45), 
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
    EWOULDBLOCK(11), 
    EAGAIN(11), 
    EINPROGRESS(150), 
    EALREADY(149), 
    ENOTSOCK(95), 
    EDESTADDRREQ(96), 
    EMSGSIZE(97), 
    EPROTOTYPE(98), 
    ENOPROTOOPT(99), 
    EPROTONOSUPPORT(120), 
    ESOCKTNOSUPPORT(121), 
    EOPNOTSUPP(122), 
    EPFNOSUPPORT(123), 
    EAFNOSUPPORT(124), 
    EADDRINUSE(125), 
    EADDRNOTAVAIL(126), 
    ENETDOWN(127), 
    ENETUNREACH(128), 
    ENETRESET(129), 
    ECONNABORTED(130), 
    ECONNRESET(131), 
    ENOBUFS(132), 
    EISCONN(133), 
    ENOTCONN(134), 
    ESHUTDOWN(143), 
    ETOOMANYREFS(144), 
    ETIMEDOUT(145), 
    ECONNREFUSED(146), 
    ELOOP(90), 
    ENAMETOOLONG(78), 
    EHOSTDOWN(147), 
    EHOSTUNREACH(148), 
    ENOTEMPTY(93), 
    EUSERS(94), 
    EDQUOT(49), 
    ESTALE(151), 
    EREMOTE(66), 
    ENOLCK(46), 
    ENOSYS(89), 
    EOVERFLOW(79), 
    EIDRM(36), 
    ENOMSG(35), 
    EILSEQ(88), 
    EBADMSG(77), 
    EMULTIHOP(74), 
    ENODATA(61), 
    ENOLINK(67), 
    ENOSR(63), 
    ENOSTR(60), 
    EPROTO(71), 
    ETIME(62);
    
    private final int value;
    public static final long MIN_VALUE = 1L;
    public static final long MAX_VALUE = 151L;
    
    private Errno(final int value) {
        this.value = value;
    }
    
    public final String toString() {
        return StringTable.descriptions.get(this);
    }
    
    public final int value() {
        return this.value;
    }
    
    static final class StringTable
    {
        public static final Map<Errno, String> descriptions;
        
        public static final Map<Errno, String> generateTable() {
            final Map<Errno, String> map = new EnumMap<Errno, String>(Errno.class);
            map.put(Errno.EPERM, "Not owner");
            map.put(Errno.ENOENT, "No such file or directory");
            map.put(Errno.ESRCH, "No such process");
            map.put(Errno.EINTR, "Interrupted system call");
            map.put(Errno.EIO, "I/O error");
            map.put(Errno.ENXIO, "No such device or address");
            map.put(Errno.E2BIG, "Arg list too long");
            map.put(Errno.ENOEXEC, "Exec format error");
            map.put(Errno.EBADF, "Bad file number");
            map.put(Errno.ECHILD, "No child processes");
            map.put(Errno.EDEADLK, "Deadlock situation detected/avoided");
            map.put(Errno.ENOMEM, "Not enough space");
            map.put(Errno.EACCES, "Permission denied");
            map.put(Errno.EFAULT, "Bad address");
            map.put(Errno.ENOTBLK, "Block device required");
            map.put(Errno.EBUSY, "Device busy");
            map.put(Errno.EEXIST, "File exists");
            map.put(Errno.EXDEV, "Cross-device link");
            map.put(Errno.ENODEV, "No such device");
            map.put(Errno.ENOTDIR, "Not a directory");
            map.put(Errno.EISDIR, "Is a directory");
            map.put(Errno.EINVAL, "Invalid argument");
            map.put(Errno.ENFILE, "File table overflow");
            map.put(Errno.EMFILE, "Too many open files");
            map.put(Errno.ENOTTY, "Inappropriate ioctl for device");
            map.put(Errno.ETXTBSY, "Text file busy");
            map.put(Errno.EFBIG, "File too large");
            map.put(Errno.ENOSPC, "No space left on device");
            map.put(Errno.ESPIPE, "Illegal seek");
            map.put(Errno.EROFS, "Read-only file system");
            map.put(Errno.EMLINK, "Too many links");
            map.put(Errno.EPIPE, "Broken pipe");
            map.put(Errno.EDOM, "Argument out of domain");
            map.put(Errno.ERANGE, "Result too large");
            map.put(Errno.EWOULDBLOCK, "Resource temporarily unavailable");
            map.put(Errno.EAGAIN, "Resource temporarily unavailable");
            map.put(Errno.EINPROGRESS, "Operation now in progress");
            map.put(Errno.EALREADY, "Operation already in progress");
            map.put(Errno.ENOTSOCK, "Socket operation on non-socket");
            map.put(Errno.EDESTADDRREQ, "Destination address required");
            map.put(Errno.EMSGSIZE, "Message too long");
            map.put(Errno.EPROTOTYPE, "Protocol wrong type for socket");
            map.put(Errno.ENOPROTOOPT, "Option not supported by protocol");
            map.put(Errno.EPROTONOSUPPORT, "Protocol not supported");
            map.put(Errno.ESOCKTNOSUPPORT, "Socket type not supported");
            map.put(Errno.EOPNOTSUPP, "Operation not supported on transport endpoint");
            map.put(Errno.EPFNOSUPPORT, "Protocol family not supported");
            map.put(Errno.EAFNOSUPPORT, "Address family not supported by protocol family");
            map.put(Errno.EADDRINUSE, "Address already in use");
            map.put(Errno.EADDRNOTAVAIL, "Cannot assign requested address");
            map.put(Errno.ENETDOWN, "Network is down");
            map.put(Errno.ENETUNREACH, "Network is unreachable");
            map.put(Errno.ENETRESET, "Network dropped connection because of reset");
            map.put(Errno.ECONNABORTED, "Software caused connection abort");
            map.put(Errno.ECONNRESET, "Connection reset by peer");
            map.put(Errno.ENOBUFS, "No buffer space available");
            map.put(Errno.EISCONN, "Transport endpoint is already connected");
            map.put(Errno.ENOTCONN, "Transport endpoint is not connected");
            map.put(Errno.ESHUTDOWN, "Cannot send after socket shutdown");
            map.put(Errno.ETOOMANYREFS, "Too many references: cannot splice");
            map.put(Errno.ETIMEDOUT, "Connection timed out");
            map.put(Errno.ECONNREFUSED, "Connection refused");
            map.put(Errno.ELOOP, "Number of symbolic links encountered during path name traversal exceeds MAXSYMLINKS");
            map.put(Errno.ENAMETOOLONG, "File name too long");
            map.put(Errno.EHOSTDOWN, "Host is down");
            map.put(Errno.EHOSTUNREACH, "No route to host");
            map.put(Errno.ENOTEMPTY, "Directory not empty");
            map.put(Errno.EUSERS, "Too many users");
            map.put(Errno.EDQUOT, "Disc quota exceeded");
            map.put(Errno.ESTALE, "Stale NFS file handle");
            map.put(Errno.EREMOTE, "Object is remote");
            map.put(Errno.ENOLCK, "No record locks available");
            map.put(Errno.ENOSYS, "Operation not applicable");
            map.put(Errno.EOVERFLOW, "Value too large for defined data type");
            map.put(Errno.EIDRM, "Identifier removed");
            map.put(Errno.ENOMSG, "No message of desired type");
            map.put(Errno.EILSEQ, "Illegal byte sequence");
            map.put(Errno.EBADMSG, "Not a data message");
            map.put(Errno.EMULTIHOP, "Multihop attempted");
            map.put(Errno.ENODATA, "No data available");
            map.put(Errno.ENOLINK, "Link has been severed");
            map.put(Errno.ENOSR, "Out of stream resources");
            map.put(Errno.ENOSTR, "Not a stream device");
            map.put(Errno.EPROTO, "Protocol error");
            map.put(Errno.ETIME, "Timer expired");
            return map;
        }
        
        static {
            descriptions = generateTable();
        }
    }
}
