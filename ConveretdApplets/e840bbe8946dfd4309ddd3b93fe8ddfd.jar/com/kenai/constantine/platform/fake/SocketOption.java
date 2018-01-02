// 
// Decompiled by Procyon v0.5.30
// 

package com.kenai.constantine.platform.fake;

import com.kenai.constantine.Constant;

public enum SocketOption implements Constant
{
    SO_DEBUG(1), 
    SO_ACCEPTCONN(2), 
    SO_REUSEADDR(3), 
    SO_KEEPALIVE(4), 
    SO_DONTROUTE(5), 
    SO_BROADCAST(6), 
    SO_USELOOPBACK(7), 
    SO_LINGER(8), 
    SO_OOBINLINE(9), 
    SO_REUSEPORT(10), 
    SO_TIMESTAMP(11), 
    SO_ACCEPTFILTER(12), 
    SO_DONTTRUNC(13), 
    SO_WANTMORE(14), 
    SO_WANTOOBFLAG(15), 
    SO_SNDBUF(16), 
    SO_RCVBUF(17), 
    SO_SNDLOWAT(18), 
    SO_RCVLOWAT(19), 
    SO_SNDTIMEO(20), 
    SO_RCVTIMEO(21), 
    SO_ERROR(22), 
    SO_TYPE(23), 
    SO_NREAD(24), 
    SO_NKE(25), 
    SO_NOSIGPIPE(26), 
    SO_NOADDRERR(27), 
    SO_NWRITE(28), 
    SO_REUSESHAREUID(29), 
    SO_LABEL(30), 
    SO_PEERLABEL(31), 
    SO_ATTACH_FILTER(32), 
    SO_BINDTODEVICE(33), 
    SO_DETACH_FILTER(34), 
    SO_NO_CHECK(35), 
    SO_PASSCRED(36), 
    SO_PEERCRED(37), 
    SO_PEERNAME(38), 
    SO_PRIORITY(39), 
    SO_SECURITY_AUTHENTICATION(40), 
    SO_SECURITY_ENCRYPTION_NETWORK(41), 
    SO_SECURITY_ENCRYPTION_TRANSPORT(42);
    
    private final int value;
    public static final long MIN_VALUE = 1L;
    public static final long MAX_VALUE = 42L;
    
    private SocketOption(final int value) {
        this.value = value;
    }
    
    public final int value() {
        return this.value;
    }
}
